package denglu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recordnum extends Thread{
	   /**
     * 用以实现用户的注册和登录
     */
    public static String url="jdbc:mysql://localhost:3306/students?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";//连接数据库的url，test是我自己的一个数据库啊宝宝们。
    public  static String user="root";//mysql登录名
    public  static String pass="951753";//mysql登录密码
    public static Connection con;//
    public  static ServerSocket server;//服务器
    public static ArrayList<String> banben = new ArrayList<String>();
    
    public static String zxbb = "";
    public static int port = 1230;
//    public static HashMap<String,Socket> online = new HashMap<String,Socket>();
	public static void main(String args[]){
		Recordnum record = new Recordnum(); //接受登录，改变字数区
		record.start();	//启动
		LinkMyself linkmyself = new LinkMyself();
		linkmyself.start();	//自连与数据库重连
	}
	public void run() {
		try{
			server = new ServerSocket(port);
			System.out.print("SQL服务端口开启\r");
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			System.out.print("数据库连接成功\r");
			
			Dateinit dateinit = new Dateinit();
			dateinit.start();	//每日跟打动态重置
			InitAll.getaver();//重新计算一次平均速度
			resertonline();//重置登录状态
			System.out.print("速度平均成功\r");
			while(true){
				Socket socket = server.accept();//被动等待客户端的连接
				System.out.println("连接");
//				socket.setSoTimeout(0);
				new ReadFirst(socket).start();
			}
        }
		catch(Exception e){System.out.print("数据库失败\r");e.printStackTrace();}  
	}
	void resertonline() throws SQLException{
		String sql="update client set online=?";
    	PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
	    ptmt.setInt(1,0);
	    ptmt.execute();
	}
	class ReadFirst extends Thread{	//防止死锁
		DataInputStream in;
		DataOutputStream out;
		Socket socket;
		ReadFirst(Socket socket){
			this.socket = socket;
		}
		public void run(){
			try {
				socket.setSoTimeout(1000);
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				InetAddress ip = socket.getInetAddress();
				if(ip.toString().equals("/125.38.13.200")){
					System.out.println(ip.toString());
					socket.close();
					return;
				}
				String what = in.readUTF();
				socket.setSoTimeout(0);
				System.out.println(what);
			
				zxbb = getBanben();
				for(int i = 705;i<709;i++)
					banben.add("版本1."+String.valueOf(i));
				if(banben.contains(what)){
					out.writeUTF("版本正确"+what+"最新版本"+zxbb);
					//新的线程
					new RWThread148(socket).start();
				}
				else if(what.equals("自连")){System.out.println("自连");socket.close();socket = null;}
				else if(what.equals("排名1")){
					System.out.println("排名1");
					getAll(1);
					socketclose(socket);
				}
				else if(what.equals("排名2")){
					System.out.println("排名2");
					getAll(2);
					socketclose(socket);
				}
				else if(what.equals("排名3")){
					System.out.println("排名3");
					getAll(3);
					socketclose(socket);
				}
				else if(what.substring(0,2).equals("添加好友")){
//					addfriend(what.substring(2));
				}
				else if(what.substring(0,2).equals("内容")){
					System.out.println("内容");
					sendwenben(what);
					socketclose(socket);
				}
				else if(what.equals("跟打记录")){
					System.out.println("跟打记录");
					getAllhistory();
					socketclose(socket);
				}
				else if(what.equals("赛文排行")){
					System.out.println("赛文排行");
					getRank();
					socketclose(socket);
				}
				else if(what.equals("全部文本记录")){
					System.out.println("获取全部文本记录");
					ObjectOutputStream outputToClient = new ObjectOutputStream(out);
		            ObjectInputStream inputByClient = new ObjectInputStream(in);
					List<String> idlist = (List<String>) inputByClient.readObject();
					
					outputToClient.writeObject(getAllWenben(idlist));
					inputByClient.close();
					outputToClient.close();
					socketclose(socket);
				}
				else{
					out.writeUTF("版本强制更新：\n因为版本错误未能及时同步问题，进行同步版本\n自动跳转下载或加群号974172771");
					socketclose(socket);
				}
			} catch (IOException | ClassNotFoundException e) {
				try {
					socket.close();
					socket = null;
				} catch (IOException e1) {}
			}
		}
		List<String> getAllWenben(List<String> idlist){
			List<String> wenbenlist = new ArrayList<String>();
			try{
				int id;
				String sql="select wenben from history where id=?";
				PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
				for(String i:idlist){
					id = Integer.valueOf(i);
					ptmt.setInt(1, id);
					ResultSet rs=ptmt.executeQuery();
				    if(rs.next()){
				    	String wen = rs.getString(1);
				    	wenbenlist.add(wen);
				    }
				}
			}catch(Exception e){System.out.println("发送文本失败");e.printStackTrace();}
			return wenbenlist;
		}
		String getBanben(){
			try{
				String sql="SELECT * FROM banben";
				PreparedStatement ptmt;
				ptmt = con.prepareStatement(sql);
				ResultSet rs=ptmt.executeQuery();
				if(rs.next()){
					return rs.getString(1);
				}
			}catch(Exception e){
				
			}
			return "";
		}
		void getRank(){
			try{
				String sql="SELECT * FROM saiwenchengji WHERE date=? ORDER BY sudu DESC";
//				Statement ptmt=Recordnum.con.createStatement();
//				ResultSet rs=ptmt.executeQuery(sql);
				PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
				ptmt.setDate(1,Dateinit.getdate());
				ResultSet rs=ptmt.executeQuery();
				System.out.println("获取赛文排名"+Dateinit.getdate().toString());
				while(rs.next()){
					out.writeUTF(String.valueOf(rs.getString(1)));
	        		out.writeUTF(String.valueOf(rs.getDouble(3)));
	        		out.writeUTF(String.valueOf(rs.getDouble(4)));
	        		out.writeUTF(String.valueOf(rs.getDouble(5)));
	        		out.writeUTF(String.valueOf(rs.getInt(6)));
	        		out.writeUTF(String.valueOf(rs.getInt(7)));
	        		out.writeUTF(String.valueOf(rs.getInt(8)));
	        		out.writeUTF(String.valueOf(rs.getInt(9)));
	        		out.writeUTF(String.valueOf(rs.getInt(10)));
	        		out.writeUTF(String.valueOf(rs.getDouble(11)));
	        		out.writeUTF(String.valueOf(rs.getDouble(12)));
	        		out.writeUTF(String.valueOf(rs.getDouble(13)));
	        		out.writeUTF(String.valueOf(rs.getDouble(14)));
				}
			}catch(Exception e){System.out.println("发送文本失败");e.printStackTrace();}
		}
		void sendwenben(String message){
			try{
				String sql="select wenben from history where id=?";
				PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
				int pos = message.indexOf('%',0)+1;
				int id = Integer.parseInt(message.substring(pos));
				System.out.println(id);
			    ptmt.setInt(1, id);
			    ResultSet rs=ptmt.executeQuery();
			    if(rs.next()){
			    	String wen = rs.getString(1);
			    	out.writeUTF(wen);
			    	System.out.println(wen);
			    }
			}catch(Exception e){System.out.println("发送文本失败");e.printStackTrace();}
		}

		void getAll(int i){
			String sql = "";
			if(i==1)
				sql = "SELECT * FROM client ORDER BY num DESC";
			else if(i==2)
				sql = "SELECT * FROM client ORDER BY datenum DESC";
			else if(i==3)
				sql = "SELECT * FROM client ORDER BY aver DESC";
			PreparedStatement ptmt;
			try {
				ptmt = con.prepareStatement(sql);
				ResultSet rs=ptmt.executeQuery();
				System.out.println("获取排名");
				while(rs.next()){					
					//检测用户名是否被使用过
		        	try {
		        		out.writeUTF(rs.getString(1));
		        		out.writeUTF(String.valueOf(rs.getInt(3)));
		        		out.writeUTF(String.valueOf(rs.getInt(4)));
		        		out.writeUTF(String.valueOf(rs.getInt(5)));
		        		out.writeUTF(String.valueOf(rs.getInt(6)));
		        		out.writeUTF(String.format("%.2f",rs.getDouble(8)));
		        		out.writeUTF(String.valueOf(rs.getInt(7)));
		        		Date last = rs.getDate(11);
		        		if(last==null)
		        			out.writeUTF("不详");
		        		else
		        			out.writeUTF(last.toString());
		        		out.writeUTF(rs.getDate(10).toString());
					} catch (IOException e) {System.out.print("发送排名失败r");}
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		void getAllhistory(){
			try{
				String name;
				String sql = "SELECT * FROM history WHERE name=? ORDER BY id DESC";
				PreparedStatement ptmt;
				try {
					name = in.readUTF();
					ptmt = con.prepareStatement(sql);
					ptmt.setString(1, name);
					ResultSet rs=ptmt.executeQuery();
					while(rs.next()){			
						//检测用户名是否被使用过
			        	try {
			        		out.writeUTF(String.valueOf(rs.getDate(2)));
			        		out.writeUTF(String.valueOf(rs.getDouble(3)));
			        		out.writeUTF(String.valueOf(rs.getDouble(4)));
			        		out.writeUTF(String.valueOf(rs.getDouble(5)));
			        		out.writeUTF(String.valueOf(rs.getInt(6)));
			        		out.writeUTF(String.valueOf(rs.getInt(7)));
			        		out.writeUTF(String.valueOf(rs.getInt(8)));
			        		out.writeUTF(String.valueOf(rs.getInt(9)));
			        		out.writeUTF(String.valueOf(rs.getInt(10)));
			        		out.writeUTF(String.valueOf(rs.getDouble(11)));
			        		out.writeUTF(String.valueOf(rs.getDouble(12)));
			        		out.writeUTF(String.valueOf(rs.getDouble(13)));
			        		out.writeUTF(String.valueOf(rs.getDouble(14)));
			        		out.writeUTF(String.valueOf(rs.getInt(16)));
			        		out.writeUTF(String.valueOf(rs.getInt(18)));
						} catch (IOException e) {System.out.print("发送排名失败r");}
			        }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		void socketclose(Socket socket){
			try {
				socket.close();
				socket = null;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.print("超时无法关闭socket\r");
			}
			System.out.print("连接后超时\r");
		}
//		void addfriend(String username){
//			try{
//				String addfriendname = in.readUTF();
//				String sql = "select * from client where username=?";
//				PreparedStatement ptmt;
//				ptmt = Recordnum.con.prepareStatement(sql);
//				ptmt.setString(1, addfriendname);
//				ResultSet rs=ptmt.executeQuery();
//				if(!rs.next()){out.writeUTF("不存在");return;}
//				if(!Recordnum.online.containsKey(addfriendname)){out.writeUTF("不在线");return;}
//				else{
//					Socket addsocket = Recordnum.online.get(addfriendname);
//					DataInputStream innotice = new DataInputStream(addsocket.getInputStream());
//					DataOutputStream outnotice = out = new DataOutputStream(addsocket.getOutputStream());
//					outnotice.writeUTF("添加好友"+username+"要添加你为好友");
//				}
//				sql="select * from client where username=?";
//			}
//			catch(Exception e){e.printStackTrace();}
//		}
	}
}
