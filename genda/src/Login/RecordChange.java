package Login;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import genda1.Window;

public class RecordChange {
	public static void recordChange() throws IOException{
		Login.out = new DataOutputStream(Login.socket.getOutputStream());
		String message = "%2%"+Login.zhanghao.getText()+"%"+Login.mima.getText()+"%"+String.valueOf(Window.fontallnum)+"%"+String.valueOf(Window.rightnum)+"%"+String.valueOf(Window.misnum);
		Login.out.writeUTF(message);
	}
}