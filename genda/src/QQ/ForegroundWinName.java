package QQ;

import javax.swing.*;

import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.misc.basicStructures.HWND;
import org.xvolks.jnative.util.User32;

public class ForegroundWinName {
	java.lang.String QQGroupName;
	JLabel QQname;

	public void setQQGroupName() {
		try {
			HWND hWnd = User32.GetForegroundWindow();
			QQGroupName = User32.GetWindowText(hWnd);
			QQname.setText(QQGroupName);

		} catch (IllegalAccessException | NativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setQQname(JLabel qqName2) {
		QQname = qqName2;
	}
}