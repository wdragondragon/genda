package QQ;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import com.sun.jna.platform.DesktopWindow;
import com.sun.jna.platform.WindowUtils;
import com.sun.jna.platform.win32.WinDef.HWND;
public class GetAllWindow
{
	public List<String> name = new ArrayList<String>();
//    public static void main(String[] args)
	public void demo()
	{
		try
		{
			final List<DesktopWindow> list = WindowUtils.getAllWindows(true);
			for (DesktopWindow dd : list)
			{
				HWND wnd = dd.getHWND();
				Rectangle rr = WindowUtils.getWindowLocationAndSize(wnd);
				if (rr.contains(-32000, -32000)||WindowUtils.getWindowTitle(wnd).equals(""))
					continue;
				name.add(WindowUtils.getWindowTitle(wnd));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
