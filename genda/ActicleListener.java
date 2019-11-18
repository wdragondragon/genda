package Acticle;
import genda1.*;
import gendaClient.battleClient;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import Login.Login;
public class ActicleListener implements TreeSelectionListener ,ActionListener{
	JTextArea dazi;
	JTextPane wenben;
	JTree tree;
	GendaListener gendalistener;
	File open;
	RandomAccessFile in = null,out=null;
	String []danziFileName,wenzhangFileName;
	byte s[] ;
	String all,wen;
	int i;
	public static long length = 0;
	JTextField number;
	public static int fontnum=0,fontweizhi=0;
	Window win;
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
						open = new File("文章//文章类",node.toString());
						in = new RandomAccessFile(open,"r");
						SendWenben.title = node.toString();
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
				if(fontnum>all.length())
					wen = all.substring(fontweizhi,fontnum);
				else
					wen = all.substring(fontweizhi, fontweizhi+fontnum);
				
				wenben.setText(wen);
				fontweizhi += fontnum;
			} catch (IOException e) {}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(SendWenben.sendwenSign==1){
			if(e.getActionCommand()=="下一段"){
				try{
					if(fontweizhi>=all.length()){
						JOptionPane.showMessageDialog(new JTextArea(),"发文结束");
						win.sendwen.setVisible(false);
						SendWenben.sendwenSign = 0;
						return;
					}
					if(fontweizhi+fontnum>all.length())
						wen = all.substring(fontweizhi, all.length());
					else
						wen = all.substring(fontweizhi, fontweizhi+fontnum);
					QQZaiwenListener.wenbenstr = wen;
					Window.f3listener.F3();
					fontweizhi+= fontnum;
					RegexText.duan1++;	//发文增段
					win.sendwen.setText(String.valueOf(fontweizhi/2)+"/"+String.valueOf(length/2)+":"+String.format("%.2f",(double)fontweizhi*100/length)+"%");
					try{
						DataOutputStream out = new DataOutputStream(battleClient.socket.getOutputStream());
						out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+Window.wenben.getText()+"%0"+"%"+Login.zhanghao.getText());
					}
					catch(Exception ex){
						System.out.println("无法发送文本内容acticlelistner,121");
					}
				}
				catch(Exception ex){System.out.println("发文处失败");}
			}
			else if(e.getActionCommand()=="保存跟打进度"){
				try{
					open = new File("文章//文章类","跟打进度.txt");
					out = new RandomAccessFile(open,"rw");
					wen = all.substring(fontweizhi-fontnum, all.length());
					byte baocun[] = wen.getBytes();
					out.write(baocun);
					out.close();
					JOptionPane.showMessageDialog(new JTextArea(),"已保存当前跟打进度");
				}catch(Exception ex){JOptionPane.showMessageDialog(new JTextArea(),"保存进度失败");}
			}
		}
		else 
			win.dazi.requestFocusInWindow();
	}
}
