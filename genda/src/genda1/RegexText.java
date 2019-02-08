package genda1;

public class RegexText {
	int qi = 0;
	int di = 0;
	int gang = 0;
	int make = 0;
	int kong = 0;
	int i,j,k,length,sign1=0,sign2=0,sign3=0;
	char [] a;
	String str,duan;
	public static int duan1;
	String regetText(String str){
		
		a = str.toCharArray();
		length = str.length();
		for(j=length-1;j>0;j--){
			if(a[j]=='µÚ'){
				for(k=j-1;k>=j-5;k--){
					if(a[k]=='-'){gang++;}
				}
				if(gang>=5){
					sign1 = k;
					kong=0;
//					duan = String.copyValueOf(a, j+1,5);
					duan = "";
					while(a[j]!='¶Î'){
						duan = duan+a[j];j++;
					}
				}
				gang=0;
			}
			if(sign1!=0)
				break;

		}
		for(j=sign1;j>0;j--){
			if(a[j]=='\n')
			{
				kong++;
				if(kong>=2){
					sign2 = j;
					kong = 0;
					a[j]='#';
					break;}
			}
		}
		for(i=0;i<sign2;i++)
			a[i]='#';
		for(i=sign1;i<length;i++)
			a[i]='#';
		
		sign2=0;
		sign1=0;
		
		str = String.valueOf(a);
		String regex = "[^0123456789]+";
		str = str.replaceAll("#","");
		str = str.replaceAll("\\s*", ""); 
		duan = duan.replaceAll(regex,"");
		duan1 = Integer.valueOf(duan);
//		System.out.println(str);
		return str;
	}
}
