package Datesaiwen;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import denglu.Dateinit;
import denglu.Recordnum;


public class addDatesaiwen {
	public static void adddatesaiwen(){
		try {
			//判断今日有无赛文
			Date date = Dateinit.getdate();
			String sql = "select * from everydaysaiwen where saiwendate=?";
			PreparedStatement ptmt;
			ptmt = Recordnum.con.prepareStatement(sql);
			ptmt.setDate(1, date);
			ResultSet rs=ptmt.executeQuery();
			if(rs.next())return;
			System.out.println(date.toString());
			//无赛文则加赛文
			sql="insert into everydaysaiwen values(?,?,?)";
			Load load = new Load();
			String saiwen = load.getRamdomWenben();
			String author = "随机生成";
			ptmt = Recordnum.con.prepareStatement(sql);
			ptmt.setDate(1, date);
			ptmt.setString(2,saiwen);
			ptmt.setString(3,author);
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
