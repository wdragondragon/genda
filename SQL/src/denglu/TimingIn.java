package denglu;

public class TimingIn extends Thread{
	RWThread148 th;
	TimingIn(RWThread148 th){	
		this.th = th;
	}
	public void run(){
		try{
			sleep(10*1000);
			th.changeRecord(th.recordNumlast,th.recordrightnum,th.recordmisnum,th.recorddatenumlast);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
