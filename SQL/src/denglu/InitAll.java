package denglu;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InitAll {
	static void getaver(){
		String username = "";
		String sql = "SELECT username,zhucedate FROM client";
		String sql2 = "SELECT sudu,duan FROM history WHERE name=?";
		String sql3 = "update client set aver=?,n=?,zhucedate=? where username=?";
		int zhucesign = 0;
		Date date;
		try {
			PreparedStatement ptmt = null;
			PreparedStatement ptmt2 = null;
			ptmt = Recordnum.con.prepareStatement(sql2);
			ptmt2 = Recordnum.con.prepareStatement(sql3);
			ResultSet rs=Recordnum.con.createStatement().executeQuery(sql);
			while(rs.next()){
				username = rs.getString(1);
				if((date=rs.getDate(2))==null)	//判断注册日期是否为空
					zhucesign=1;
				ptmt.setString(1, username);
				ResultSet rs1=ptmt.executeQuery();
				Double aver = 0.0,temp = 0.0;
				int n = 0,duan;
				while(rs1.next()){			//计算赛文成绩之和，计算成绩数量
					temp = rs1.getDouble(1);
					duan = rs1.getInt(2);
					if(duan==999||duan==0){
						aver += temp;
						n++;
					}
				}
				if(n!=0)			//计算赛文成绩平均
					aver /= n;
				ptmt2.setDouble(1, aver);
				ptmt2.setInt(2, n);
				if(zhucesign==1){			//日期为空，需要注册标记
					ptmt2.setDate(3, Dateinit.getdate());
					zhucesign=0;
				}
				else			//日期不为空，直接放入
					ptmt2.setDate(3, date);
				ptmt2.setString(4, username);
				ptmt2.execute();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
