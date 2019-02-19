package denglu;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import Datesaiwen.addDatesaiwen;

public class Dateinit extends Thread{
	static int y,m,d;
	static Date date1,date2;
	static Calendar cal;
	public void run(){
		date1 = getdate();
		while(true){
			
			try {
				sleep(10*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addDatesaiwen.adddatesaiwen();
			
			date2 = date1;
			date1 = getdate();
			if(date1.toString().equals(date2.toString()))continue;
			else {
				try{
					String sql="update client set datenum=0";//搜索存在用户名，并改民字数
					PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
					ptmt.execute();
					String sql1 = "delete from saiwenchengji";
					Statement stmt = Recordnum.con.createStatement();
					stmt.executeUpdate(sql1);
				}
				catch(Exception e){}
			}
		}
	}
	public static Date getdate(){
		cal=Calendar.getInstance(); 
		y=cal.get(Calendar.YEAR); 
		m=cal.get(Calendar.MONTH); 
		d=cal.get(Calendar.DATE);
		return new Date(y-1900,m,d);
	}
}
