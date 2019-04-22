package Check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoCheck {
	public static String buildcheckstr(String str,String model){
		char d[] = str.toCharArray();
		char[] c = model.toCharArray();
		for (int i = 0; i < d.length; i++)
            d[i] = (char) (d[i] ^ c[i%5]); 
		String check = new String(d);
		return check;
	}
	public static boolean checkstr(String str,String model){
		
		String []regex = {"速度(.*?) ","击键(.*?) ","码长(.*?) "};
		Double a=0.0;
		for(int i = 0;i<regex.length;i++){
			Pattern pattern = Pattern.compile(regex[i]);//匹配模式
			Matcher m = pattern.matcher(str);//判断是否符合匹配
			if(m.find()){
				String temp = m.group(1);
				if(temp.indexOf("/")!=-1)
					temp = temp.split("/")[0];
				a+=Double.parseDouble(String.format("%.2f", Double.parseDouble((temp))));
				
			}
		}
		String Check = str.substring(str.indexOf("校验")+2,str.length()); 
		String temp = String.format("%.2f", a);
//		System.out.println(temp);
		String check = buildcheckstr(temp,model);
//		System.out.println(check+":"+Check);
		if(check.equals(Check))return true;
		else return false;
	}
}
