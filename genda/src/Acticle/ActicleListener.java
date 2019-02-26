package Acticle;
import genda1.*;
import gendaClient.battleClient;

import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Collections;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import Login.Login;
import SetWin.SetFrameQianshuiListener;
import Tips.*;
public class ActicleListener implements TreeSelectionListener ,ActionListener{
	JTextArea dazi;
	static JTextPane wenben;
	JTree tree;
	GendaListener gendalistener;
	File open;
	RandomAccessFile in = null,out=null;
	String []danziFileName,wenzhangFileName;
	byte s[] ;
	public static String all,wen;
	int i;
	public static long length = 0;
	JTextField number;
	private Acticle acticle;
	public static int fontnum=0,fontweizhi=0;
	public static  ArrayList<String> chouqulist= new ArrayList<String>();
	public static  ArrayList<String> chouqubufenlist= new ArrayList<String>();
	public static int y; 
	static Window win;
//	String s;
	public void setWenbenText(JTextPane t){
		wenben = t;
	}
	public void setTree(JTree t){
		tree = t;
	}
	public void setDanziFileName(String []t) {
		danziFileName = t;
	}
	public void setWenzhangFileName(String []t){
		wenzhangFileName = t;
	}

	public void setNumber(JTextField number){
		this.number = number;
	}
	public void setWin(Window win){
		this.win = win;
	}
	void getNumber(){
		try{
			fontnum = Integer.parseInt(number.getText());}
		catch(Exception e){
			JOptionPane.showMessageDialog(new JTextArea(),"字数框输入数字");
		}	
	}
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		if(node.isLeaf()){
			try {
				in=null;
				fontweizhi = 0;
				for(i=0;i<danziFileName.length;i++)	
					if(node.toString().equals(danziFileName[i])){
						open = new File("文章//单字类",node.toString());
						in = new RandomAccessFile(open,"r");
						SendWenben.title = node.toString();
						break;
					}
				for(i=0;i<wenzhangFileName.length;i++){
					if(in!=null)break;
					if(node.toString().equals(wenzhangFileName[i])){
						SendWenben.title = node.toString();
						if(SendWenben.title.substring(0, 4).equals("跟打进度")){
							readjindu();
						}
						open = new File("文章//文章类",SendWenben.title);
						in = new RandomAccessFile(open,"r");
						break;
					}
				}
			}catch (Exception e) {}
			try {
				getNumber();
				length = in.length();
				s = new byte[(int)length];
				in.readFully(s);
				all = new String(s);
				all = all.replaceAll("\\s*", "");
				showwen();
			} catch (IOException e) {}
		}
	}
	public static void showwen(){
		if(fontnum>all.length())
			wen = all.substring(fontweizhi,all.length());
		else
			wen = all.substring(fontweizhi, fontweizhi+fontnum);
		wenben.setText(wen);
//		fontweizhi += fontnum;
		win.sendwen.setText(String.valueOf(fontweizhi)+"/"+String.valueOf(all.length())+":"+String.format("%.2f",(double)fontweizhi*100/all.length())+"%");
	}
	void readjindu() throws IOException{
		try {
			open = new File("文章//文章类",SendWenben.title);
			Reader read = new FileReader(open);
			BufferedReader br = new BufferedReader(read);
			SendWenben.title = br.readLine();
			fontweizhi = Integer.valueOf(br.readLine());
			br.close();
			read.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(SendWenben.sendwenSign==1){
			if(e.getActionCommand()=="下一段"||e.getActionCommand()=="顺序下一段"){
				try{
					if(fontweizhi>=all.length()){
						JOptionPane.showMessageDialog(new JTextArea(),"发文结束");
						win.sendwen.setVisible(false);
						SendWenben.sendwenSign = 0;
						return;
					}
					if(fontweizhi+fontnum>=all.length()){
						wen = all.substring(fontweizhi, all.length());
						fontweizhi = all.length();
					}
					else{
						wen = all.substring(fontweizhi, fontweizhi+fontnum);
						fontweizhi+= fontnum;
					}
					QQZaiwenListener.wenbenstr = wen;
					QQZaiwenListener.wenbenstr = RegexText.qukong(RegexText.huanfu(QQZaiwenListener.wenbenstr));
					Window.f3listener.F3();
					
					RegexText.duan1++;	//发文增段
					win.sendwen.setText(String.valueOf(fontweizhi)+"/"+String.valueOf(all.length())+":"+String.format("%.2f",(double)fontweizhi*100/all.length())+"%");
					try{
						DataOutputStream out = new DataOutputStream(battleClient.socket.getOutputStream());
						out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+RegexText.duan1+"#"+Window.wenben.getText()+"%0"+"%"+Login.zhanghao.getText());
					}
					catch(Exception ex){
						System.out.println("无法发送文本内容acticlelistner,121");
					}
					if(SetFrameQianshuiListener.qianshui==0)ShareListener.send();
				}
				catch(Exception ex){System.out.println("发文处失败");}
			}
			else if(e.getActionCommand()=="保存跟打进度"){
				try{
					String jindufile = "跟打进度"+SendWenben.title+".txt";
					open = new File("文章//文章类",jindufile);
					FileOutputStream testfile = new FileOutputStream(open);
					testfile.write(new String("").getBytes());
					byte baocun[] = (SendWenben.title+"\r\n"+String.valueOf(fontweizhi-fontnum)).getBytes();
					testfile.write(baocun);
					testfile.close();
					JOptionPane.showMessageDialog(new JTextArea(),"已保存当前跟打进度");
				}catch(Exception ex){JOptionPane.showMessageDialog(new JTextArea(),"保存进度失败");}
			}
		}
		else if(e.getActionCommand().equals("抽取模式发文")||e.getActionCommand().equals("抽取下一段")){
			if(SendWenben.sendwenSign2==1){
				getNumber();
				QQZaiwenListener.wenbenstr = randomCommon(all,fontnum);
				Window.f3listener.F3();
				if(SendWenben.sendwenSign2==0){
					acticle.setVisible(false);
					RegexText.duan1 = 1;
					SendWenben.sendwenSign2 = 1;
				}
				else
					RegexText.duan1++;	//发文增段
			}
			else if(SendWenben.sendwenSign3==1){
				chouqubufenlist.clear();
				Collections.shuffle(chouqulist);
				String str = "";
				for(int i=0;i<(chouqulist.size()<y?chouqulist.size():y);i++){
					str += chouqulist.get(i);
					chouqubufenlist.add(chouqulist.get(i));
				}
				QQZaiwenListener.wenbenstr = str;
				Window.f3listener.F3();
				RegexText.duan1++;
			}
			if(SetFrameQianshuiListener.qianshui==0)ShareListener.send();
		}
		else if(e.getActionCommand().equals("词库练习")){
			String temp = "";
			chouqulist.clear();
			chouqubufenlist.clear();
			try{
				String num[] = win.acticle.cikuchouqucanshu.getText().split(":");
				int x = Integer.parseInt(num[0]);
				y = Integer.parseInt(num[1]);
				if(x==2){
					for(String str:Tips.alltable.keySet()){
						if(Tips.alltable.get(str)==2){
							chouqulist.add(str);
						}
					}
				}
				else if(x==3){
					for(String str:Tips.alltable.keySet()){
						if(Tips.alltable.get(str)==3){
							chouqulist.add(str);
						}
					}
				}
				Collections.shuffle(chouqulist);
//				System.out.println(chouqulist.size()+" "+y);
				for(int i=0;i<(chouqulist.size()<y?chouqulist.size():y);i++){
					temp += chouqulist.get(i);
					chouqubufenlist.add(chouqulist.get(i));
				}
				QQZaiwenListener.wenbenstr = temp;
				win.f3listener.F3();
				RegexText.duan1 = 1;
				SendWenben.sendwenSign3 = 1;
				acticle.setVisible(false);
//				else if(x==2&&num[1].equals("c")){
//					
//				}
//				else if(x==3&&num[1].equals("c")){
//					
//				}
//				System.out.println(temp);
			}catch(Exception ex){ex.printStackTrace();}
		}
		else
			win.dazi.requestFocusInWindow();
		
	}
	public static String randomCommon(String wen, int n){
		int min = 0;
		int max = wen.length();
		char c[] = wen.toCharArray();
		StringBuilder resultstr = new StringBuilder();
	    if (n > (max - min + 1) || max < min) {  
	           return null;  
	       }  
	    int[] result = new int[n];  
	    int count = 0;  
	    while(count < n) {  
	        int num = (int) (Math.random() * (max - min)) + min;  
	        boolean flag = true;  
	        for (int j = 0; j < n; j++) {  
	            if(num == result[j]){  
	                flag = false;  
	                break;  
	            }  
	        }  
	        if(flag){  
	            result[count] = num;  
	            count++;  
	        }  
	    }
	    for(int i = 0;i<result.length;i++)
	    	resultstr.append(String.valueOf(c[result[i]]));    
	    return resultstr.toString();  
	} 
	public void setActicle(Acticle acticle){
		this.acticle = acticle;
	}
}
