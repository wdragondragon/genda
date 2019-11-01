package Other;

import java.util.HashMap;

public class testInt {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer,Integer> h = new HashMap<Integer,Integer>();
		Integer a = new Integer(1);
		Integer b = new Integer(1);
		h.put(a,1);
		h.put(b,2);
		System.out.println(a==b);
		System.out.println(h.get(a));
		
	}
}
