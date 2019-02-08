package genda1;
import java.math.BigInteger;
import java.util.*;
public class ComputeSpeed {
	Calendar calendar = Calendar.getInstance();
	long time1=0,time2=0;
	double sudu,second;
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
		return second;
	}
	public void setTimeOne(){
		calendar.setTime(new Date());
		time1 = calendar.getTimeInMillis();
	}
	public void setTimeTwo(){
		calendar.setTime(new Date());
		time2 = calendar.getTimeInMillis();
	}
}
