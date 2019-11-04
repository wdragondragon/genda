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
		//0�� 1ȫ 2��ȫ 3���� 4 ������ 5����  6�ζ���
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
		File FuhaoFile = new File("�����ļ�/�����ļ�/�����ļ�.txt");
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
		String topSymbol = "����";
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
			System.out.println("��ʧ��2");
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
			showText.setText("û�и���");
		}
	}
	public static SubscriptInstance[] subscriptInstances;

	public void changecolortip(){
		String article = QQZaiwenListener.wenbenstr;
		int articleLength = article.length();
		String symbol = "����";
		String codeTemp;
		String strTemp;
		weizhi = 0;
		subscriptInstances = new SubscriptInstance[article.length()];
		try{
			/**
			 * ����article���ȵ�SubscriptInstance����
			 * ����ÿ��SubscriptInstance���г�ʼ��
			 * �жϸ��±��ַ��Ƿ��ڵ�������У�����ޣ����ж��Ƿ�Ϊ�������ĸ������ֱ������codeTempΪ�ַ�����
			 * ���캯������ʵ�������SubscriptInstance���췽��
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
				//��ȡǰһ�ַ�����̱��볤�ȡ�
				int preCodeLengthTemp = j==0?0:subscriptInstances[j-1].getCodeLengthTemp();
				//�ж�ÿ�������Ƿ��д�
				for(int i=9;i>=0;i--){
					if(articleLength>=j+i+2&&
							wordsCodeList.get(i).containsKey(strTemp=article.substring(j,j+i+2)))
					{
						/**
						 * ��ʱ������룬����� _
						 * �ж����һ���Ƿ�Ϊ�ո�_���������������һ���ַ��Ƿ�Ϊ��������һ������������滻��codeTemp
						 * 
						 * ����һ�ַ�����볤�Ӹôʱ��룬����i���������ʵĽ�βλ��j+i+1�±����һ��
						 * ��һ�����и�����Ϣ��װ��SubscriptInstance�ڲ���PreInfo��
						 * ע��SubscriptInstance���ж����һ�����ֶΣ�preInfoMap
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
					 * ���жϸ��±����̱��볤����������
					 * 
					 * ��->��֧1���������ã���ǰ�������û�������ʣ��±�j��Ϊ���֣������±�����Ϊ��һ����̱��볤��+���±굥�ֱ��볤�ȣ�
					 * 
					 * ��->��֧2��[˵���ô���Ϊĳ�ʵ����һ��]�����±���̱��볤�ȣ��Ƿ���ڣ���һ����̱��볤��+���ַ����볤�ȣ�
					 * 		��->˵����һ���Ĵʲ�Ϊ��̱��룬����һ��ɾ���������ô���̱�������Ϊ���ߡ�
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
			 * ����������������һ�������󣬴Ӻ���ǰ������Ϊ���һ��Ϊ��̱��룬һֱ����һ������Ϊ���·����
			 * 
			 * ����->ִ��ѭ����ǰ���������ÿ��ѭ��������i����ѱ������һ��bestPre����һ�κ�bestPre�����һ������Ϊi
			 * 		��bestPre����Ϊ��ʹ�ô���ʼλuseWordSign����Ϊtrue,�ٽ�bestPre��iȫ�������鸲�Ǳ��useSign����Ϊtrue
			 *
			 * ������->ִ�������������󣬱���i���������һ��pre���ж��Ƿ����㣨pre>bestPre��û�д��鸲�ǹ�pre��
			 * 		��->���е�pre����һ��������Ϊi��������ʹ�ô���ʼλuseWordSign����Ϊtrue
			 * 
			 * ֱ�ӽ�������ǰ��bestPre
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
		String lastStr = codeTemp.substring(lengthTemp-1,lengthTemp);	//��ȡ�������һ���ַ�
		String regex = "234567890";
		int nonPreferred = 0;//����ѡ���
		if(lastStr.equals("_")){
			lengthTemp -= 1;
			nonPreferred = 0;
		}
		else if(regex.indexOf(lastStr)!=-1){		//�ж����һ�ַ��Ƿ�Ϊ����
			lengthTemp -= 1;
			nonPreferred = 1;
		}else nonPreferred = 0;
		if(lengthTemp<3){//0�� 1ȫ 2��ȫ 3���� 4 ������ 5����  6�ζ���
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
