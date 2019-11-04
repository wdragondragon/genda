package Tips;

import genda1.QQZaiwenListener;
import genda1.RegexText;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import java.util.Scanner;

import javax.swing.JLabel;

import Tips.SubscriptInstance.PreInfo;


public class Tips{
	int i = 0;
	JLabel showText;
	public static double allCodeLength = 0.0;
	public static int weizhi = 0;
	public static String weizhistr = "";
	
	public static HashMap<String,Integer> alltable;
	public static HashMap<String,Integer> citable;
	public static HashMap<String,Integer> shoutable;
	
	public static HashMap<String,String> wordCode;
	public static HashMap<String,String> symbolCode;
	public static ArrayList<String> symbolEntry;
	public static ArrayList<HashMap<String,String>> wordsCodeList;
	public static StringBuilder allCode;
	public int max = 0;
	enum Type{
		//0单 1全 2次全 3三简 4 次三简 5二简  6次二简
		dan(0),q(1),cq(2),sj(3),csj(4),ej(5),cej(6);
		Type(int code){
			this.code = code;
		}
		private int code;
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
	}
	public void Fuhao() throws IOException{
		String str;
		symbolCode = new HashMap<String,String>();
		File FuhaoFile = new File("编码文件/符号文件/符号文件.txt");
		FileInputStream fis = new FileInputStream(FuhaoFile); 
        InputStreamReader read = new InputStreamReader(fis, "UTF-8");
		BufferedReader  bufferRead = new BufferedReader(read);
		while((str=bufferRead.readLine())!=null){
			String[] splited = str.split("\\s+");
			String ch = splited[0];
			String bm = splited[1];
			symbolCode.put(ch, bm);
		}
		bufferRead.close();
		read.close();
		fis.close();
	}
	public Tips(JLabel showText){
		String regex = "234567890";
		String topSymbol = "，。";
		String str;
		File more = new File(ChooseFile.cizufilename);
		try {
			Fuhao();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.showText = showText;
		wordsCodeList = new ArrayList<HashMap<String,String>>();
		wordCode = new HashMap<String,String>();
		alltable =  new HashMap<String,Integer>();
		citable =  new HashMap<String,Integer>();
		shoutable =  new HashMap<String,Integer>();
		symbolEntry = new ArrayList<String>();
		try{
			for(i=0;i<10;i++){
				HashMap<String,String>moretiphash = new HashMap<String,String>();
				wordsCodeList.add(moretiphash);
			}
			FileInputStream fis = new FileInputStream(more); 
	        InputStreamReader read = new InputStreamReader(fis, "UTF-8");
			BufferedReader  bufferRead = new BufferedReader(read);
			while((str=bufferRead.readLine())!=null){
				boolean cixuanSign = false;
				String[] splited = str.split("\\s+");
				String ch = splited[0];
				String bm = splited[1];
				String temp;
			    int chlength = splited[0].length();
			    int length = splited[1].length();
			    temp = bm.substring(bm.length()-1);
			    if(temp.equals("_"))length -= 1;
			    else if(regex.indexOf(temp)!=-1){
			    	cixuanSign=true;
			    	length -=1;
			    }
			    i = -1;
			    if(chlength==1){
			    	if(wordCode.containsKey(splited[0])){
						if(wordCode.get(splited[0]).length()>length){
							wordCode.put(ch, bm);
							alltable.put(ch, length);
						}
						else if(wordCode.get(splited[0]).length()==length){
							if(temp.equals("2")){
								wordCode.put(ch, bm);
								alltable.put(ch, length);
							}
						}
					}
					else{
						wordCode.put(ch, bm);
					}
			    }
			    else if(chlength>=2&&chlength<=11){
			    	i = chlength - 2;
			    }
			    if(i!=-1){
				    if(wordsCodeList.get(i).containsKey(splited[0])){
			    		if(wordsCodeList.get(i).get(splited[0]).length()>length){
			    			wordsCodeList.get(i).put(ch,bm);
			    			alltable.put(ch, length);
			    			if(cixuanSign)
			    				citable.put(ch, length);
			    			else
			    				shoutable.put(ch, length);
			    		}
			    	}
			    	else{
			    		wordsCodeList.get(i).put(ch,bm);
			    		alltable.put(ch, length);
			    		if(cixuanSign)
		    				citable.put(ch, length);
		    			else
		    				shoutable.put(ch, length);
			    	}
			    }
			    if(topSymbol.indexOf(ch.substring(0,1))!=-1){
			    	symbolEntry.add(ch);
			    }
			}
			bufferRead.close();
			read.close();
			fis.close();
		}catch(Exception e){
			System.out.println("打开失败2");
			e.printStackTrace();
		}
	}
	public void changeTips(String ch) {
		// TODO Auto-generated method stub
		if(wordCode.containsKey(ch)){
			String bm = wordCode.get(ch);
			showText.setText(ch+":"+bm);
		}
		else{
			showText.setText("没有该字");
		}
	}
	public static SubscriptInstance[] subscriptInstances;

	public void changecolortip(){
		String article = QQZaiwenListener.wenbenstr;
		int articleLength = article.length();
		String symbol = "。，";
		String codeTemp;
		String strTemp;
		weizhi = 0;
		subscriptInstances = new SubscriptInstance[article.length()];
		try{
			/**
			 * 创建article长度的SubscriptInstance数组
			 * 并对每个SubscriptInstance进行初始化
			 * 判断该下标字符是否在单字码表中，如果无，则判断是否为数组或字母，是则直接设置codeTemp为字符自身
			 * 构造函数创建实例。详见SubscriptInstance构造方法
			 * 
			 * 
			 */
			for(int i=articleLength-1;i>=0;i--){
				strTemp = article.substring(i,i+1);
				char charTemp = strTemp.toCharArray()[0];
				codeTemp = wordCode.get(strTemp);
				if(codeTemp==null){
					if((charTemp>='a'&&charTemp<='z')
							||(charTemp>='A'&&charTemp<='Z')
							||(charTemp>='0'&&charTemp<='9')){
						codeTemp=strTemp;
					}else if(symbolCode.containsKey(strTemp)){
						codeTemp = symbolCode.get(strTemp);
					}else{
						weizhi++;
						codeTemp=strTemp+"?";
					}
				}else if(articleLength>i+1
						&&codeTemp.substring(codeTemp.length()-1).equals("_")
						&&symbol.contains(subscriptInstances[i+1].getWord())
						&&!(articleLength>i+2
								&&symbolEntry.contains(subscriptInstances[i+1].getWord()
										+subscriptInstances[i+2].getWord())))
				{
					codeTemp = codeTemp.substring(0,codeTemp.length()-1);
				}
				SubscriptInstance subscriptInstance = new SubscriptInstance(i,strTemp,codeTemp);
				subscriptInstances[i] = subscriptInstance;
			}
			subscriptInstances[0].setCodeLengthTemp(subscriptInstances[0].getWordCode().length());
			for(int j=0;j<articleLength;j++){
				//获取前一字符的最短编码长度。
				int preCodeLengthTemp = j==0?0:subscriptInstances[j-1].getCodeLengthTemp();
				//判断每个长度是否有词
				for(int i=9;i>=0;i--){
					if(articleLength>=j+i+2&&
							wordsCodeList.get(i).containsKey(strTemp=article.substring(j,j+i+2)))
					{
						/**
						 * 临时放入编码，往后加 _
						 * 判断最后一码是否为空格_并且这个词条的下一个字符是否为，。其中一个，如果是则替换掉codeTemp
						 * 
						 * 用上一字符最短码长加该词编码，并将i添加在这个词的结尾位置j+i+1下标的上一跳
						 * 上一跳中有各种信息封装在SubscriptInstance内部类PreInfo中
						 * 注：SubscriptInstance中有多个上一跳，字段：preInfoMap
						 */
						codeTemp = wordsCodeList.get(i).get(strTemp);	
						
						if(articleLength>j+i+2&&codeTemp.substring(codeTemp.length()-1).equals("_")
								&&symbol.contains(subscriptInstances[j+i+2].getWord())
								&&!(articleLength>j+i+3
									&&symbolEntry.contains(subscriptInstances[j+i+2].getWord()
											+subscriptInstances[j+i+3].getWord())))
						{
							codeTemp = codeTemp.substring(0,codeTemp.length()-1);
						}
						int nextCodeLengthTemp = preCodeLengthTemp+codeTemp.length();
						subscriptInstances[j+i+1].addPre(nextCodeLengthTemp,j,strTemp,codeTemp,getType(codeTemp));
						if(subscriptInstances[j+i+1].getCodeLengthTemp()==0||
								subscriptInstances[j+i+1].getCodeLengthTemp()>nextCodeLengthTemp)
						{
							subscriptInstances[j+i+1].setCodeLengthTemp(nextCodeLengthTemp);
						}
					}
					/**
					 * （判断该下标的最短编码长度有无设置
					 * 
					 * 无->分支1：若无设置，即前面遍历都没有遇到词，下标j处为单字，将该下标设置为上一跳最短编码长度+该下标单字编码长度）
					 * 
					 * 有->分支2：[说明该处必为某词的最后一字]（该下标最短编码长度）是否大于（上一跳最短编码长度+该字符编码长度）
					 * 		是->说明上一跳的词不为最短编码，将上一跳删除，并将该处最短编码设置为后者。
					 */
					if(j>0){
						String word = subscriptInstances[j].getWord();
						String wordCode = subscriptInstances[j].getWordCode();
						int wordCodeLength = wordCode.length();
						int thisCodeLength = subscriptInstances[j].getCodeLengthTemp();
						int nextCodeLengthTemp = preCodeLengthTemp+wordCodeLength;
						if(thisCodeLength==0){
							subscriptInstances[j].setCodeLengthTemp(nextCodeLengthTemp);
						}else if(thisCodeLength>nextCodeLengthTemp){
							subscriptInstances[j].addPre(thisCodeLength, j,word , wordCode, 0);
							subscriptInstances[j].getShortCodePreInfo().setWords(word);
							subscriptInstances[j].getShortCodePreInfo().setWordsCode(wordCode);
							subscriptInstances[j].setCodeLengthTemp(nextCodeLengthTemp);
						}
					}
				}
			}
			/**
			 * 结束了所有增加上一跳操作后，从后往前跳（因为最后一格为最短编码，一直往上一跳绝对为最短路径）
			 * 
			 * 优先->执行循环从前往后遍历，每次循环优先找i的最佳编码的上一跳bestPre，跳一次后将bestPre点的下一跳设置为i
			 * 		将bestPre设置为已使用词组始位useWordSign设置为true,再将bestPre到i全部将词组覆盖标记useSign设置为true
			 *
			 * 次优先->执行完优先下跳后，遍历i点的所有上一跳pre，判断是否满足（pre>bestPre且没有词组覆盖过pre）
			 * 		是->所有的pre的下一跳都设置为i，并将已使用词组始位useWordSign设置为true
			 * 
			 * 直接将遍历提前到bestPre
			 */
			for(int i = article.length()-1;i>=0;i--){
				boolean sign = true;
				SubscriptInstance subscriptInstance = subscriptInstances[i];
				int codeLengthTemp = subscriptInstance.getCodeLengthTemp();
				PreInfo preInfo = subscriptInstance.getPreInfoMap().get(codeLengthTemp);
				int pre = 0;
				if(preInfo==null||preInfo.getPre().size()==0)
					sign = false;
				else
					pre= preInfo.getMinPre();
				if(sign&&!subscriptInstances[pre].isUseWordSign()
						&&!(!subscriptInstances[pre].isUseSign()
						&&subscriptInstances[i].isUseSign())
						){
					strTemp = preInfo.getWords();
					codeTemp = preInfo.getWordsCode();
					subscriptInstances[pre].setType(subscriptInstances[i].getShortCodePreInfo().getType(pre));
					subscriptInstances[pre].setNext(i);
					subscriptInstances[pre].setUseWordSign(true);	
					for(int i2=pre;i2<=i;i2++){
						subscriptInstances[pre].setUseSign(true);
					}
				}
				for(Integer key :subscriptInstance.getPreInfoMap().keySet()){
					PreInfo preinfo = subscriptInstances[i].getPreInfoMap().get(key);
					for(int preTemp:preinfo.getPre().keySet()){
						if(preTemp>pre&&!subscriptInstances[preTemp].isUseWordSign()){
							subscriptInstances[preTemp].setNext(i);
							subscriptInstances[preTemp].setType(preinfo.getType(preTemp));
						}
					}
				}
				if(sign)
					i = pre;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public int getType(String codeTemp){
		int lengthTemp = codeTemp.length();
		String lastStr = codeTemp.substring(lengthTemp-1,lengthTemp);	//获取编码最后一个字符
		String regex = "234567890";
		int nonPreferred = 0;//非首选标记
		if(lastStr.equals("_")){
			lengthTemp -= 1;
			nonPreferred = 0;
		}
		else if(regex.indexOf(lastStr)!=-1){		//判断最后一字符是否为多重
			lengthTemp -= 1;
			nonPreferred = 1;
		}else nonPreferred = 0;
		if(lengthTemp<3){//0单 1全 2次全 3三简 4 次三简 5二简  6次二简
			if(nonPreferred==0)return Type.ej.code;
			else return Type.cej.code;
		}else if(lengthTemp<4){
			if(nonPreferred==0)return Type.sj.code;
			else return Type.csj.code;
		}else {
			if(nonPreferred==0)return Type.q.code;
			else return Type.cq.code;
		}
	}
	public void compalllength(){
		allCode = new StringBuilder();
		for(int i=0;i<subscriptInstances.length;i++){
			if(subscriptInstances[i].isUseWordSign()){
				System.out.println(i+":"+subscriptInstances[i].getNext());
				i=subscriptInstances[i].getNext();
				
				allCode.append(subscriptInstances[i].getShortCodePreInfo().getWordsCode());
			}
			else
				allCode.append(subscriptInstances[i].getWordCode());
		}
		allCodeLength = allCode.length();
	}
}
