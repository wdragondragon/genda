package Tips;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SubscriptInstance {
	private int next;//下一跳
	private HashMap<Integer,PreInfo> preInfoMap;//上一跳
	private String word;
	private String wordCode;
	private int type;//0单 1全 2次全 3三简 4 次三简 5二简  6次二简
	private boolean useSign;
	private boolean useWordSign;
	private int codeLengthTemp;
	public class PreInfo{
		//同长度不同上跳表，用于动态词提
		private HashMap<Integer,Integer> preAndType = new HashMap<Integer,Integer>();
		private String wordsCode;//最短编码路径的编码
		private String words;//最短编码路径的词条
		PreInfo(int pre,String words,String wordsCode,int type){
			this.preAndType.put(pre, type);
			this.words = words;
			this.wordsCode = wordsCode;
		}
		public String getWordsCode() {
			return wordsCode;
		}
		public void setWordsCode(String wordsCode) {
			this.wordsCode = wordsCode;
		}
		public String getWords() {
			return words;
		}
		public void setWords(String words) {
			this.words = words;
		}
		public HashMap<Integer,Integer> getPre(){
			return preAndType;
		}
		public int getMinPre(){
			List<Integer> list = new ArrayList<>(preAndType.keySet());
			Collections.sort(list);
			return list.get(0);
		}
		public boolean containPre(int pre){
			return this.preAndType.containsKey(pre);
		}
		public int getType(int pre) {
			return preAndType.get(pre);
		}
		public void addPre(int pre ,int type){
			preAndType.put(pre, type);
		}
	}
	public SubscriptInstance(int i){
		next = i;
		preInfoMap = new HashMap<Integer,PreInfo>();
		wordCode = "";
		word = "";
		useSign = false;
		useWordSign = false;
		codeLengthTemp = 0;
	}
	public SubscriptInstance(int i,String word,String wordCode){
		this(i);
		this.wordCode = wordCode;
		this.word = word;
	}
	public void addPre(int length,int pre,String words,String wordsCode,int type) {
		if(!preInfoMap.containsKey(length))
			this.preInfoMap.put(length, new PreInfo(pre,words,wordsCode,type));
		else
			this.preInfoMap.get(length).addPre(pre, type);
	}
	public PreInfo getShortCodePreInfo(){
		return preInfoMap.get(codeLengthTemp);
	}
	public void removePre(int length){
		this.preInfoMap.remove(length);
	}
	public boolean isUseWordSign() {
		return useWordSign;
	}
	public void setUseWordSign(boolean useWordSign) {
		this.useWordSign = useWordSign;
	}
	public String getWordCode() {
		return wordCode;
	}
	public void setWordCode(String wordCode) {
		this.wordCode = wordCode;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getNext() {
		return next;
	}
	public int getCodeLengthTemp() {
		return codeLengthTemp;
	}
	public void setCodeLengthTemp(int codeLengthTemp) {
		this.codeLengthTemp = codeLengthTemp;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public HashMap<Integer,PreInfo> getPreInfoMap() {
		return preInfoMap;
	}
	public boolean isUseSign() {
		return useSign;
	}
	public void setUseSign(boolean useSign) {
		this.useSign = useSign;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
