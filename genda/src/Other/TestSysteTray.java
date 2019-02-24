package Other;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class TestSysteTray {
	public static void main(String args[]) {
		TrayIcon trayIcon = null;
		if (SystemTray.isSupported()) // 判断系统是否支持系统托盘
		{
			SystemTray tray = SystemTray.getSystemTray(); // 创建系统托盘


			Image image = Toolkit.getDefaultToolkit().getImage("images/config_3.ico");//载入图片 图片位置是程序所在的目录
			ActionListener listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 创建一个窗体
					JFrame frame = new JFrame();
					frame.setBounds(400, 400, 200, 200);
					JLabel label = new JLabel("welcome   JDK1.6");
					frame.add(label);
					frame.setVisible(true);
				}
			};

			// 创建弹出菜单
			PopupMenu popup = new PopupMenu();//这个是右键才能触发的菜单
			MenuItem defaultItem = new MenuItem("打开");
			defaultItem.addActionListener(listener);
			MenuItem exitItem = new MenuItem("退出");
			exitItem.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

			popup.add(defaultItem);
			popup.add(exitItem);
			trayIcon = new TrayIcon(image, "最小化窗口", popup);// 创建trayIcon
			trayIcon.addActionListener(listener);//给小图标加上监听器，默认的就是监听双击。
//如果偶想监听单击啥的  就加mouselistener
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
	}

}