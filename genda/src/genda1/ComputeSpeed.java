package genda1;
import java.math.BigInteger;
import java.util.*;
public class ComputeSpeed {
	Calendar calendar = Calendar.getInstance();
	private long time1=0,time2=0;
	private double sudu,second;
	private long stopTime1=0,stopTime2=0;
	public double getSpeed(double length,int mistake){
		BigInteger
			timeOne = new BigInteger(String.valueOf(time1)),
			timeTwo = new BigInteger(String.valueOf(time2)),
			time = new BigInteger("0");
		time = timeTwo.subtract(timeOne);
		second = time.doubleValue();
		sudu = (length-5*mistake)/(second/60000.0);
		second = second/1000;
		return sudu;
	}
	public double getSecond(){
		BigInteger
			timeOne = new BigInteger(String.valueOf(time1)),
			timeTwo = new BigInteger(String.valueOf(time2)),
			time = new BigInteger("0");
		time = timeTwo.subtract(timeOne);
		second = time.doubleValue();
		second = second/1000;
		return second;
	}
	public void substractStopTime(){
		BigInteger
		timeStopOne = new BigInteger(String.valueOf(stopTime1)),
		timeStopTwo = new BigInteger(String.valueOf(stopTime2)),
		timeOne = new BigInteger(String.valueOf(time1)),
		time = new BigInteger("0");
		time = timeStopTwo.subtract(timeStopOne);
		timeOne = timeOne.add(time);
		time1 = Long.valueOf(String.valueOf(timeOne));
		stopTime1 = 0L;
		stopTime2 = 0L;
	}
	public void setTimeOne(){
		calendar.setTime(new Date());
		time1 = calendar.getTimeInMillis();
	}
	public void setTimeTwo(){
		calendar.setTime(new Date());
		time2 = calendar.getTimeInMillis();
	}
	public void setTimeOne(long time){
		time1 = time;
	}
	public void setTimeTwo(long time){
		time2 = time;
	}
	public long getTimeTwo(){
		return time2;
	}
	public long getTimeOne(){
		return time1;
	}
	public void setStopTimeOne(){
		calendar.setTime(new Date());
		stopTime1 = calendar.getTimeInMillis();
	}
	public void setStopTimeTwo(){
		calendar.setTime(new Date());
		stopTime2 = calendar.getTimeInMillis();
		substractStopTime();
	}
}
