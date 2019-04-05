package genda1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import keep.*;
public class SystemListener implements ActionListener,MouseListener,MouseMotionListener{
	static Window win;
	static int x=0;
	static int y=0;
	static int width=0;
	static int height=0;
	int locationPointx;
	int locationPointy;
	static int MaxSign = 0;
	Point pressedPoint ;
	SystemListener(Window win){
		this.win = win;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="关"){
			if(win.one.isVisible()){
				JOptionPane.showMessageDialog(new JTextArea(),"请先关闭在线对战");return;
			}
			if(MaxSign==1){
				JOptionPane.showMessageDialog(new JTextArea(),"请先关闭全屏模式");return;
			}
			UIManager.put("OptionPane.yesButtonText", "关闭");
			UIManager.put("OptionPane.noButtonText", "隐藏");
			int n = JOptionPane.showConfirmDialog(null, "要关闭跟打器，还是选择隐藏到托盘", "关闭提示", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				try {
					readWrite.keep(win);//保存设置
				} catch (IOException e1) {System.out.println("保存失败");}
				System.exit(0);
			}
			else{
				win.setVisible(false);
			}
		}
		else if(e.getActionCommand()=="最大化"){
			if(MaxSign == 0){
//				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕分辨率
//				GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment(); 
//				Rectangle screenSize=ge.getMaximumWindowBounds(); 
//				int w=screenSize.width; 
//				int h=screenSize.height;
//				win.setLocation(0, 0); 
//				win.setSize(screenSize.width,screenSize.height); 		
				max();
			}
			else{
				min();
			}
		}
		else if(e.getActionCommand().equals("最小化")){
			System.out.println("最小化");
			win.setExtendedState(JFrame.ICONIFIED); 
		}
	}
	public static void max(){
		x = win.getX();
		y = win.getY();
		width = win.getWidth();
		height = win.getHeight();
		Dimension   screenSize   =   Toolkit.getDefaultToolkit().getScreenSize();   
		Rectangle   bounds   =   new   Rectangle(screenSize);   
		Insets   insets   =   Toolkit.getDefaultToolkit().getScreenInsets(win.getGraphicsConfiguration());   
		bounds.x   +=   insets.left;   
		bounds.y   +=   insets.top;   
		bounds.width   -=   insets.left   +   insets.right;   
		bounds.height   -=   insets.top   +   insets.bottom;   
		win.setBounds(bounds);
		MaxSign = 1;
	}
	public static void min(){
		win.setLocation(x, y); 
		win.setSize(width,height);
		x=0;y=0;width=0;height=0;
		MaxSign = 0;
	}
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		win.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR)); //改变窗口大小指针 
	}

	@Override
	public void mouseExited(MouseEvent e) {
		win.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	//恢复默认指针
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressedPoint = e.getPoint(); //记录鼠标坐标
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(MaxSign==0){
			Point point = e.getPoint();// 获取当前坐标
			locationPointx = win.getWidth();
			locationPointy = win.getHeight();
			int i = locationPointx + point.x - pressedPoint.x;// 计算移动后的新坐标
			int j = locationPointy + point.y - pressedPoint.y;
			win.setSize(i, j);// 改变窗体大小
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}

}
