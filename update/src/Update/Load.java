package Update;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.*;

public class Load extends JFrame{
	public static void main(String args[]) throws IOException{
		
		
		JFrame jf = new Load();
		jf.setVisible(true);
		jf.setBounds(100,100,250,100);
		jf.setTitle("正在更新");
		JLabel notice = new JLabel("0kb");
		jf.setLayout(null);
		notice.setBounds(90,20,70,30);
		jf.add(notice);
		URL url = new URL("http://39.96.83.89/new.exe");
		URLConnection urlcon = url.openConnection(); //模拟浏览器发出请求
//		urlcon.setRequestProperty("User-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
		urlcon.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");
		InputStream inexe = urlcon.getInputStream();
//		BufferedReader bufferRead = new BufferedReader(inexe);
		byte[] a = new byte[1024];
		int len = 0;
		int all = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		File file = new File("跟打器.exe");
		OutputStream infile = new FileOutputStream(file);
		while ((len = inexe.read(a)) != -1) {
			infile.write(a, 0, len);
			all += len;
			notice.setText(all/1024+"kb");
		}
		notice.setText("下载完成");
		JOptionPane.showMessageDialog(new JTextArea(),"下载完成");
		
		infile.close();
		inexe.close();
		Runtime.getRuntime().exec("跟打器.exe");
		System.exit(0);
	}
	
}
