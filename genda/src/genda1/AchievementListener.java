package genda1;

import gendaClient.*;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.IOException;



import javax.swing.*;
import Tips.*;
import keep.KeyPassword;
import Login.Login;
import QQ.QQ;

public class AchievementListener extends AbstractAction{
	JLabel qqName;
	JButton achievementButton;
	GendaListener gendaListener;
	JTextArea chengji,dazi;
	JTextPane wenben;
	double sudu,second,length,Keymethod,right,left,KeyNumber,mistake,deleteNumber,deleteTextNumber,repeat,fengzhi;
	public static double Keyaccuracy;
	public static double dacilv;
	String gendageshi;
	TableAdd table;
	int space;
	private Window win;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			sendchengji();
//			dazi.setText("");
//			dazi.setEditable(true);
//			dazi.requestFocusInWindow();
//			wenben.setCaretPosition(0);
//			gendaListener.setSign(0);
//			gendaListener.KeyNumber = 0;
//			gendaListener.deleteNumber = 0;
//			gendaListener.deleteTextNumber = 0;
//			gendaListener.left = 0;
//			gendaListener.right = 0;
//			gendaListener.repeat = 0;
//			gendaListener.record = "";
		
	}
	public void sendchengji(){
		if(gendaListener.sign==2){
			try{			//判断对战中
				if(!battleClient.socket.isClosed()){
					if(Window.reducesudu.getText()!="")
						battleSend.out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+RegexText.duan1+"#"+Window.wenben.getText()+"%"+String.valueOf(sudu-30*battleSend.Mistake-Integer.parseInt(Window.reducesudu.getText()))+"%"+Login.zhanghao.getText());	
					else
						battleSend.out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+RegexText.duan1+"#"+Window.wenben.getText()+"%"+String.valueOf(sudu-30*battleSend.Mistake)+"%"+Login.zhanghao.getText());	
				}
			}
			catch(Exception ex){
				System.out.println("无法发送文本内容F1");
			}
			setClipboardString(gendageshi);//将成绩段放入剪切板
			QQ qq = new QQ();
			try {			//发送成绩给QQ窗口
				qq.sendMessage(2,qqName.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("载文错误");
			}
		}
	}
	public String getGeshi(){
		sudu = gendaListener.getSudu();
		KeyNumber = gendaListener.getKeyNumber();
		second = gendaListener.getSecond();
		length = gendaListener.getLength();
		mistake = gendaListener.getMistake();
		deleteNumber = gendaListener.getDeleteNumber();
		deleteTextNumber = gendaListener.getDeleteTextNumber();
		right = gendaListener.getRight();
		left = gendaListener.getLeft();
		repeat = gendaListener.getRepeat();
		space = gendaListener.getSpace();
		fengzhi = gendaListener.getFenzhi();
		if(right!=0)
			Keymethod = left/right;
		else 
			Keymethod = 1;
		Keyaccuracy = (KeyNumber-deleteNumber*2-deleteTextNumber*(1.0*Window.tipschange.alllength/QQZaiwenListener.wenbenstr.length()))/KeyNumber;
		dacilv = ((double)(gendaListener.daciall)/(QQZaiwenListener.wenbenstr.length()+deleteTextNumber));
		gendageshi = 
				"第"+RegexText.duan1+
				"段 速度"+String.format("%.2f", sudu)+
				" 击键"+String.format("%.2f",KeyNumber/second)+
				" 码长"+String.format("%.2f", KeyNumber/length)+
				" 标顶理论"+String.format("%.2f", Tips.dingalllength/QQZaiwenListener.wenbenstr.length())+
				" 文章难度"+String.format("%.2f", Tips.dengji)+
				" 字数"+(int)(length)+" 回改"+(int)(deleteTextNumber)+
				" 退格"+(int)(deleteNumber)+
				" 错字"+(int)(mistake)+
				" 键数"+(int)(KeyNumber)+
				" 选重"+(int)(repeat)+
				" 键准"+String.format("%.2f",Keyaccuracy*100)+
				"% 键法"+String.format("%.2f",Keymethod*100)+
				"%(左"+String.valueOf((int)left)+":右"+String.valueOf((int)right)+":空格"+String.valueOf(space)+")"+
				" 打词率"+String.format("%.2f", dacilv*100)+
				"% 选重率"+String.format("%.2f", repeat/length*100)+
//				"% 峰值"+String.format("%.2f", fengzhi)+
				"% 拖拉机跟打器v1.49";
//		ReadyListener.ReadyDuan++;
		table.addRow();
		try{
			sendhistory();
		}catch(Exception e){
			System.out.println("历史成绩错误");
		}
		try{
			if(Window.everydaySign){
				sendsaiwen();
				win.setAlwaysOnTop(false);
				Window.everydaySign = false;
			}
		}catch(Exception e){
			System.out.println("每日赛文成绩错误 ACHI 112");
		}
		try{			//判断对战中
			if(!battleClient.socket.isClosed()){
				gendageshi = gendageshi+" 让速"+Window.reducesudu.getText()+
						" 重打"+battleSend.Mistake+
						" 最终速度(显示速度-重打*30-让速)"+String.format("%.2f",sudu-30*battleSend.Mistake-Integer.parseInt(Window.reducesudu.getText()));
				gendageshi = gendageshi+" 正在对战中 "+battleReadThread.Whowin;
			}
		}
		catch(Exception ex){
			System.out.println("无法设置对战后缀");
		}
		return gendageshi;
	}
	public void sendsaiwen(){
		if(Login.dengluSign == 1){
			String message = "成绩"+
					"%"+Login.zhanghao.getText()+
					"%"+String.format("%.2f", sudu)+
					"%"+String.format("%.2f",KeyNumber/second)+
					"%"+String.format("%.2f", KeyNumber/length)+
					"%"+(int)(length)+
					"%"+(int)(deleteTextNumber)+
					"%"+(int)(deleteNumber)+
					"%"+(int)(mistake)+
					"%"+(int)(repeat)+
					"%"+String.format("%.2f",Keyaccuracy*100)+
					"%"+String.format("%.2f",Keymethod*100)+
					"%"+String.format("%.2f", dacilv*100)+
					"%"+String.valueOf(GendaListener.comp.getSecond())+
					"%"+String.format("%.2f", Tips.dengji);
			message = KeyPassword.convertMD5(message);
			try {
				Login.out.writeUTF(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void sendhistory(){
		if(Login.dengluSign == 1){
			String message = "历史"+
					"%"+Login.zhanghao.getText()+			
					"%"+String.format("%.2f", sudu)+
					"%"+String.format("%.2f",KeyNumber/second)+
					"%"+String.format("%.2f", KeyNumber/length)+
					"%"+(int)(length)+
					"%"+(int)(deleteTextNumber)+
					"%"+(int)(deleteNumber)+
					"%"+(int)(mistake)+
					"%"+(int)(repeat)+
					"%"+String.format("%.2f",Keyaccuracy*100)+
					"%"+String.format("%.2f",Keymethod*100)+
					"%"+String.format("%.2f", dacilv*100)+
					"%"+String.valueOf(GendaListener.comp.getSecond())+
					"%"+QQZaiwenListener.wenbenstr+
					"%"+String.valueOf(RegexText.duan1)+
					"%"+String.format("%.2f", Tips.dengji);//14
			message = KeyPassword.convertMD5(message);
			try {
				Login.out.writeUTF(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void setWin(Window win){
		this.win = win;
	}
	public void setGendaListener(GendaListener t){
		gendaListener = t;
	}
	public void setChengjiText(JTextArea t){
		chengji = t;
	}
	public void setDaziText(JTextArea t){
		dazi = t;
	}
	public void setWenbenText(JTextPane t){
		wenben = t;
	}
	public void setQQName(JLabel qqName2){
		qqName = qqName2;
	}
	public void setTable(TableAdd t){
		table = t;
	}
    public static void setClipboardString(String text) {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 封装文本内容
        Transferable trans = new StringSelection(text);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
        clipboard = null;
    }
    public static String getClipboardString() {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 获取剪贴板中的内容
        Transferable trans = clipboard.getContents(null);
      
        if (trans != null) {
            // 判断剪贴板中的内容是否支持文本
            if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    // 获取剪贴板中的文本内容
                    String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
                    clipboard = null;
                    return text;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        clipboard = null;
        return null;
    }
}
