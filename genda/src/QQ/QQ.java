package QQ;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.xvolks.jnative.misc.basicStructures.HWND;
import org.xvolks.jnative.util.User32;

public class QQ {
	public static void sendMessage(int i,String Name) throws Exception {
    	Robot robot = new Robot();
    	HWND hWnd=User32.FindWindow("TXGuiFoundation", Name);
    	HWND genda = User32.FindWindow(null, "跟打");
    	robot.delay(80);
    	if(hWnd.getValue()>0){
    		java.lang.String winname = User32.GetWindowText(hWnd);
    		System.out.println(winname);
    		User32.SetForegroundWindow(hWnd);	//切换到聊天窗口
        	if(i==1){
    			robot.keyPress(KeyEvent.VK_TAB);
    			robot.keyRelease(KeyEvent.VK_TAB);
    			
    			robot.keyPress(KeyEvent.VK_CONTROL);
    			robot.keyPress(KeyEvent.VK_A);
    			robot.keyPress(KeyEvent.VK_C);
    			
    			robot.keyRelease(KeyEvent.VK_A);
        		robot.keyRelease(KeyEvent.VK_C);
        		robot.keyRelease(KeyEvent.VK_CONTROL);
        		
    			}
        	else if(i==2){
        		robot.keyPress(KeyEvent.VK_CONTROL);
        		robot.keyPress(KeyEvent.VK_V);
        		robot.keyRelease(KeyEvent.VK_V);
        		robot.keyRelease(KeyEvent.VK_CONTROL);
        					
        		robot.keyPress(KeyEvent.VK_ENTER);
        		robot.keyRelease(KeyEvent.VK_ENTER);
        	}
        	do{
        		Thread.sleep(300);
    			User32.SetForegroundWindow(genda);
    			hWnd = User32.GetForegroundWindow();
    			winname = User32.GetWindowText(hWnd);
    			System.out.println(winname);
    		}while(winname==Name);
       }
    }
}
