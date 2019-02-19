package GendaServer;



import java.io.*;
import java.net.*;
import java.util.*;



/*服务器类*/
public class Server extends Thread{
	public List<Socket> socketList = new ArrayList<Socket>();//存放所有连接的客户端的集合
	public List<Socket> guanzhanList = new ArrayList<Socket>();//存放所有连接的客户端的集合
	public ServerSocket server;//服务器
	public int portNum;//端口号
	public int socketSum = 0;  //端口数
	public int mumber[] = {0,0};//两人是什么身份 房主或成员
	public double sudu[] = {0.0,0.0}; //两人速度
	public int win[] = {0,0};  //两人赢的次数
	public String name[] = {"",""};
	public ComputeSpeed comp[]= {null,null}; //计算时间
	public Server(int portNum){
		this.portNum = portNum;
	}
	public void run(){
		try { 
			server = new ServerSocket(portNum);
			System.out.print("房间号为"+portNum+"开启成功\r");
			while(true){
				Socket socket = server.accept();//被动等待客户端的连接
				socket.setSoTimeout(0);
				new ReadFirst(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("端口意外关闭\r");
		}
	}
	class ReadFirst extends Thread{	//防止死锁
		Socket socket;
		DataOutputStream out = null;
		DataInputStream in = null;
		ReadFirst(Socket t){
			try{
				socket = t;
				socket.setSoTimeout(1000);
				in = new DataInputStream(socket.getInputStream());
				String what = in.readUTF();
				socket.setSoTimeout(0);
				if(what.equals("对战")){
					if(socketSum<2){
						socketSum++;//该端口中的总用户数
						System.out.print("第"+socketSum+"个对战客户端连接"+portNum+"成功！！\r");
						socketList.add(socket);//连接的客户端存放到集合里面
						new RWThread(socket).start();//新的线程
						if(socketList.size()==2){
							for(int i=0;i<socketList.size();i++){//发送给所有连接的客户端
								//在链表头的socket设为房主
								if(socketList.lastIndexOf(socketList.get(i))==0){
									try {
										win[0]=0;win[1]=0;
										out = new DataOutputStream(socketList.get(i).getOutputStream());
										out.writeUTF("%0%无%无%无%无%房间已满，请准备开始,载文后等待对方跟打框变空白\n对方跟打框变白时，对方载文成功，即将开始跟打\n你是房主\n%无");
									} catch (IOException e1) {
										//e1.printStackTrace();
										System.out.print("无法通知房主为房主\r");
									}
								}
								//链表中的其他成员都为普通成员.
								else{
									mumber[socketList.lastIndexOf(socketList.get(i))] = 1;
									out = new DataOutputStream(socketList.get(i).getOutputStream());
									out.writeUTF("%0%无%无%无%无%房间已满，请准备开始,载文后等待对方跟打框变空白\n对方跟打框变白时，对方载文成功，即将开始跟打\n%无");
								}
							}
						}
					}
					else {
						out = new DataOutputStream(socket.getOutputStream());
						out.writeUTF("%0%无%无%无%无%房间已满\n%无");
						socket.close();
					}
				}
				else if(what.equals("自连")){System.out.println("自连");}
				else if(what.equals("观战")){
					guanzhanList.add(socket);
				}
			}
			catch (IOException e) {
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.print("超时无法关闭socket\r");
				}
				System.out.print("连接后超时\r");
			}
		}
	}
	class RWThread extends Thread{//接收和发送消息的线程   发送1 房主 2跟打进度 3文本  4计分 5系统 6开始
		
		public Socket socket;
		DataInputStream in = null;
		DataOutputStream out = null;
		int num[] = {0,0,0,0,0};
		String system = "";
		int began = 0;
		public RWThread(Socket socket){
			this.socket = socket;
		}
		@SuppressWarnings("unused")
		public void run() {
			super.run();
			String message;
			try {
				in = new DataInputStream(socket.getInputStream());
				while(true){
					message = in.readUTF();//接收客户端发来的消息
					System.out.println(message);
					int pos = 0;
					for(int i=0;i<5;i++){
						pos =  message.indexOf('%',pos)+1;
						if(pos!=-1)
							num[i] = pos;
						else break;
					}
					if(began==0){
						began =Integer.parseInt(message.substring(num[0],num[1]-1));
						if(began==1){
							system = "对方已准备\n";
							for(int i=0;i<socketList.size();i++){//发送给所有连接的客户端;
								if(socket!=socketList.get(i)){ 
									out = new DataOutputStream(socketList.get(i).getOutputStream());
									out.writeUTF("%"+String.valueOf(mumber[socketList.lastIndexOf(socket)])+"%无"+"%无"+"%无"+"%无%"+system+"%无");
								}
							}	
						}
					}
					else {
						began = Integer.parseInt(message.substring(num[0],num[1]-1));
						if(began==0){
							system = "对方取消准备\n";
							for(int i=0;i<socketList.size();i++){//发送给所有连接的客户端;
								if(socket!=socketList.get(i)){ 
									out = new DataOutputStream(socketList.get(i).getOutputStream());
									out.writeUTF("%"+String.valueOf(mumber[socketList.lastIndexOf(socket)])+"%无"+"%无"+"%无"+"%无%"+system+"%无");
								}
							}
						}
					}
					String jindu = message.substring(num[1],num[2]-1);
					String wenben = message.substring(num[2],num[3]-1);
					sudu[socketList.lastIndexOf(socket)] = Double.parseDouble(message.substring(num[3],num[4]-1));
					name[socketList.lastIndexOf(socket)] = message.substring(num[4]);
					if(sudu[0]>sudu[1]&&sudu[0]!=0&&sudu[1]!=0){//宣布赢者，重置速度		
						win[0]++;
						sudu[0] = 0.0;sudu[1]=0.0;
						System.out.println(sudu[0]+":"+sudu[1]);
						for(int i=0;i<socketList.size();i++){//发送给所有连接的客户端;
							out = new DataOutputStream(socketList.get(i).getOutputStream());
							out.writeUTF("%"+String.valueOf(mumber[socketList.lastIndexOf(socket)])+"%"+jindu+"%"+wenben+"%"+name[0]+":"+name[1]+"/%"+String.valueOf(win[0])+":"+String.valueOf(win[1])+"%无"+"%无");
						}
						
					}
					else if(sudu[0]<sudu[1]&&sudu[0]!=0&&sudu[1]!=0){
						System.out.println(sudu[1]);
						win[1]++;
						System.out.println(name[0]+":"+name[1]+"/"+sudu[0]+":"+sudu[1]);
						sudu[0] = 0.0;sudu[1]=0.0;
						for(int i=0;i<socketList.size();i++){//发送给所有连接的客户端;
							out = new DataOutputStream(socketList.get(i).getOutputStream());
							out.writeUTF("%"+String.valueOf(mumber[socketList.lastIndexOf(socket)])+"%"+jindu+"%"+wenben+"%"+name[0]+":"+name[1]+"/%"+String.valueOf(win[0])+":"+String.valueOf(win[1])+"%无"+"%无");
						}
						
					}
					if(message!=null){//判断是否要发送信息
						if(began==1){
							for(int i=0;i<socketList.size();i++){//发送给所有连接的客户端;
								if(socket!=socketList.get(i)){ 
									out = new DataOutputStream(socketList.get(i).getOutputStream());
									out.writeUTF("%"+String.valueOf(mumber[socketList.lastIndexOf(socket)])+"%"+jindu+"%"+wenben+"%"+name[0]+":"+name[1]+"/%"+String.valueOf(win[0])+":"+String.valueOf(win[1])+"%无"+"%无");
								}
							}						
						}
						else{
							for(int i=0;i<socketList.size();i++){//发送给所有连接的客户端;
								if(socket!=socketList.get(i)){
									out = new DataOutputStream(socketList.get(i).getOutputStream());
									out.writeUTF("%"+String.valueOf(mumber[socketList.lastIndexOf(socket)])+"%"+"%"+wenben+"%"+name[0]+":"+name[1]+"/%"+String.valueOf(win[0])+":"+String.valueOf(win[1])+"%无"+"%无");
								}
							}
						}
					}
					else{
						socket.close();
						socket.sendUrgentData(0);
					}
				}
			} catch (IOException e) {
				//e.printStackTrace();
				for(int i=0;i<socketList.size();i++){//发送给所有连接的客户端
					if(socket!=socketList.get(i)){
						try {
							out = new DataOutputStream(socketList.get(i).getOutputStream());
							out.writeUTF("%0%无%无%无%无%另一玩家断开\n%无");
						} catch (IOException e1) {
							System.out.print("无法向玩家"+i+"发送玩家"+(socketSum-1)+"断开信息\r");
						}
					}
				}
				System.out.print("玩家离开"+portNum/1111+"房间"+portNum/1111+"剩余"+(--socketSum)+"玩家\r");//客户断开后sockeSum减一
				socketList.remove(socket);//从泛型链表中删除已断开的客户
			}
		}
	}
}
