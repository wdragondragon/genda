package Tips;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class TipsFrame extends JFrame implements MouseListener {
	public static HashMap<String,String> bianma = new HashMap<String,String>();
	public static JTextArea show ;
	public TipsFrame(){
		setTitle("Ó¢ÎÄ×Öµä");
		
		setVisible(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		show = new JTextArea();
		show.setFont(new Font("Î¢ÈíÑÅºÚ",0,30));
		show.setLineWrap(true);
		
		JScrollPane show1 = new JScrollPane(show);
		setBounds(10,10,300,300);
		add(show1);
		try {
			zidian();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void zidian() throws IOException{
		
		File open = new File("±àÂëÎÄ¼þ//Ó¢ÎÄ×Öµä//","Ó¢ÎÄ×Öµä.txt");
		FileInputStream fis = new FileInputStream(open); 
		InputStreamReader read = new InputStreamReader(fis, "UTF-8");
		BufferedReader  bufferRead = new BufferedReader(read);
		
		String str ;
		int i;
		while((str=bufferRead.readLine())!=null){
			char a[] = str.toCharArray();
			str = "";
			for(i=0;i<a.length;i++){
				if(a[i]!=' ')break;
			}
			for(;i<a.length;i++){
				str += String.valueOf((a[i]));
			}
			try{
				String [] arr = str.split("\\s+");
				bianma.put(arr[0], arr[1]);
//				System.out.println(arr[0]+":"+arr[1]);
			}
			catch(Exception e){}
		}
		bufferRead.close();
		read.close();
		fis.close();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(this.isShowing())
			setVisible(false);
		else
			setVisible(true);
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}