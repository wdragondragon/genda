package genda1;

import java.util.List;

import Acticle.SendWenben;
import SetWin.SetCharListener;
import SetWin.SetspaceListener;

public class RegexText {
	int gang = 0;
	int kong = 0;
	int i,j,k,length,sign1=0,sign2=0;
	char [] a;
	String duan;
	public static int duan1;
	String regetText(String str){
		SendWenben.title = "";
		a = str.toCharArray();
		length = str.length();
		for(j=length-1;j>0;j--){
			if(a[j]=='第'){
				for(k=j-1;k>=j-5;k--){
					if(a[k]=='-'){gang++;}
				}
				if(gang>=5){
					sign1 = k;
					kong=0;
//					duan = String.copyValueOf(a, j+1,5);
					duan = "";
					while(a[j]!='段'){
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
				a[j]='#';
				if(kong>=2){
					sign2  = j;
					while(a[j]!='\n'&&j>0)
						j--;
					if(j>0)
						a[j]='#';
					else
						j=-1;
					for(j=j+1;j<sign2;j++)
						SendWenben.title+=String.valueOf(a[j]);
					kong = 0;
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
		str = qukong(str);
		str = huanfu(str);

		duan = duan.replaceAll(regex,"");
		duan1 = Integer.valueOf(duan);
		System.out.println("标题:"+SendWenben.title+"\n"+str);
		return str;
	}
	public static String huanfu(String str){
		String initchar = ";:,.!?";
		String afterchar = "；：，。！？";
		char []a = str.toCharArray();
		int b ;
		char y[] = afterchar.toCharArray();
		if(SetCharListener.charsign==1)
			for(int i =0;i<a.length;i++)
				if((b = initchar.indexOf(a[i]))!=-1)
					a[i] = y[b];
		str = String.valueOf(a);
		return str;
	}
	public static String qukong(String str){
		if(SetspaceListener.spacesign==1)
			str = str.replaceAll("\\s*", "");
		str = str.replaceAll("　","");
		return str;
	}
	public static String biaoding(String str){
		char []a = str.toCharArray();
		String ding = "，。、！?↓,.!?";
		for(int i=0;i<a.length-1;i++){
			if(a[i]=='_'&&ding.indexOf(String.valueOf(a[i+1]))!=-1){
				a[i]='#';
			}
		}
		str = new String(a);
		str = str.replaceAll("#","");
		return str;
	}
	public static boolean isMaxInt(Integer num,List<Integer> a){
		Integer max = 0;
		if(num==null)return false;
		for(Integer i:a){
			if(i!=null&&i>max)max=i;
		}
		if(num==max)return true;
		else return false;
	}
}
