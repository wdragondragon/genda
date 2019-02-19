package denglu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class RWThread146 extends Thread {
	public Socket socket;
	DataInputStream in = null;
	DataOutputStream out = null;
	int num[] = {0,0,0,0};
	int dengluSign = 0;
	public int recordnum = 0;
	public int recordNumlast = 0;
	String username;
	public RWThread146(Socket socket){
		this.socket = socket;
	}
	public void run(){
		String message;
		try{
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			System.out.print("用户进入\r");
			while(true){
				message = in.readUTF();//接收客户端发来的消息
//				System.out.println(message);
				if(message.equals("心跳")){
					continue;
				}
				if(message.equals("断开")){
					socket.close();
					System.out.println("退出登录");
					socket.sendUrgentData(0);
				}
				int pos = 0;
				for(int i=0;i<4;i++){				//分析收到信息,分块化
					pos =  message.indexOf('%',pos)+1;
					if(pos!=-1)
						num[i] = pos;
					else break;
				}
				if(num[0]==-1)continue;
				int caozuohao = Integer.parseInt(message.substring(num[0],num[1]-1));
				username = message.substring(num[1],num[2]-1);
				String password = message.substring(num[2],num[3]-1);
				String record = message.substring(num[3]);
				if(!record.equals("无")&&!record.equals("-999"))
					recordnum = Integer.parseInt(record);
				if(dengluSign==0){
					System.out.print(username+"进入操作\r");
					caozuo(caozuohao,username,password,recordnum);
				}
				else{
					recordNumlast = recordnum;//保存最后一次字数
				}
			}
		}
		catch(Exception e){ 		//退出时，将数据库里的字数修改
			System.out.print(username+"用户退出\r");
			if(dengluSign==1){
				try {
					if(recordNumlast<0)
						recordNumlast = 0;
					changeRecord(recordNumlast);
					this.stop();
				} catch (SQLException e1) {System.out.print(username+"保存字数失败\r");}
			}
		}
	}
	public void changeRecord(int recordnum) throws SQLException{
		String sql="update client set num =? where username=?";//搜索存在用户名，并改民字数
        PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
        ptmt.setInt(1,recordnum);
        ptmt.setString(2, username);
        ptmt.execute();
	}
	public void caozuo(int i,String username,String password,int recordnum) throws SQLException{
        switch(i){
        case 1:
            denglu(username,password,recordnum,this);
            break;
        case 2:
            zhuce(username,password);
            break;
        }
	}
    public void zhuce(String username,String password) throws SQLException{
    	System.out.print(username+"正在注册\r");
    	String sql="select username from client where username=?";//检测用户名是否被使用过
        PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
        ptmt.setString(1, username);
        ResultSet rs=ptmt.executeQuery();
        if(rs.next()){						//检测用户名是否被使用过
        	System.out.print(username+"用户名已被注册\r");
        	try {
				out.writeUTF("2");
			} catch (IOException e) {System.out.print(username+"通知相同用户名失败\r");}
        }
        else{
	        sql="insert into client (username,password,num) values(?,?,?)";
	        ptmt=Recordnum.con.prepareStatement(sql);
	        ptmt.setString(1, username);
	        ptmt.setString(2, password);
	        ptmt.setInt(3,0);
	        ptmt.execute();
	        try {
	        	System.out.print(username+"注册成功！\r");
				out.writeUTF("1");
			} catch (IOException e) {System.out.print(username+"通知注册成功失败\r");}
        }
    }
    //用户登录
    public void denglu(String username,String password,int recordnum,Thread thread) throws SQLException{
    	int num = 0;
    	System.out.print(username+"正在登录\r");
        String sql="select * from client where username=? and password=?";
        PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
        ptmt.setString(1, username);
        ptmt.setString(2, password);
        ResultSet rs=ptmt.executeQuery();
        //从登录用户给出的账号密码来检测查询在数据库表中是否存在相同的账号密码
        if(rs.next()){
            System.out.print(username+"登录成功！\r");
            num = rs.getInt(3);
        	try {
            	String message = "%1%"+String.valueOf(num);
				out.writeUTF(message);
				recordNumlast = num;
				dengluSign = 1;
			} catch (IOException e) {System.out.print(username+"发送登录成功失败\r");}
        }else{
            System.out.print(username+"姓名或密码错误！\n请重新登录：\r");
            String message = "%2%0";
            try {
				out.writeUTF(message);
			} catch (IOException e) {System.out.print(username+"发送登录失败失败\r");}
        }
    }
}