package denglu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VTXT {

	 public static String url="jdbc:mysql://localhost:3306/students?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";//�������ݿ��url��test�����Լ���һ�����ݿⰡ�����ǡ�
	    public  static String user="root";//mysql��¼��
	    public  static String pass="951753";//mysql��¼����
	    public static Connection con;//
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection(url,user,pass);
		String sql="select * from history";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		File fs = new File("��ʷ�����¼.txt");
		FileOutputStream fos = new FileOutputStream(fs); 
        OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
		BufferedWriter  bufferWriter = new BufferedWriter(writer);
		String qitou = "����\t����\t�ٶ�\t����\t�볤\t����\t�ظ�\t�˸�\t����\tѡ��\t��׼\tʱ��\t����\r\n";
		bufferWriter.write(qitou);
		bufferWriter.flush();
		while(rs.next()){
			String message = rs.getString(1)+"\t"
					+rs.getDate(2).toString()+"\t"
					+String.format("%.2f",rs.getDouble(3))+"\t"
					+String.format("%.2f",rs.getDouble(4))+"\t"
					+String.format("%.2f",rs.getDouble(5))+"\t"
					+String.valueOf(rs.getInt(6))+"\t"
					+String.valueOf(rs.getInt(7))+"\t"
					+String.valueOf(rs.getInt(8))+"\t"
					+String.valueOf(rs.getInt(9))+"\t"
					+String.valueOf(rs.getInt(10))+"\t"
					+String.format("%.2f",rs.getDouble(11))+"\t"
					+String.format("%.2f",rs.getDouble(12))+"\t"
					+String.format("%.2f",rs.getDouble(13))+"\t"
					+rs.getString(14)+"\t"
					+rs.getInt(15)+"\r\n";
			System.out.print(message);
			bufferWriter.write(message);
			bufferWriter.flush();
		}
		bufferWriter.close();
		writer.close();
		fos.close();
	}

}