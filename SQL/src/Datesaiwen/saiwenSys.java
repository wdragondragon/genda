package Datesaiwen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import denglu.Dateinit;
import denglu.RWThread148;
import denglu.Recordnum;

public class saiwenSys {
	public static int everydaysaiwen(DataOutputStream out,String username){
    	Date date = Dateinit.getdate();
		String sql = "select * from everydaysaiwen where saiwendate=?";
		String sql1 = "select * from saiwenchengji where name=?";
		PreparedStatement ptmt;
		try {
			ptmt = Recordnum.con.prepareStatement(sql1);
			ptmt.setString(1, username);
			ResultSet rs=ptmt.executeQuery();
			if(rs.next()){
				try {
					out.writeUTF("已打过");
					return 0;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ptmt = Recordnum.con.prepareStatement(sql);
			ptmt.setDate(1, date);
			rs=ptmt.executeQuery();
			if(rs.next()){
				String saiwen = rs.getString(2);
				System.out.print(username+"获取赛文\r");
				try {
					out.writeUTF(saiwen);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
    }
	public static void getachievement(DataInputStream in){
		
	}
}
