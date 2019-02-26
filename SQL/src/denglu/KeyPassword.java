package denglu;

public class KeyPassword {
    public static String convertMD5(String inStr){  
    	String key = "tjyyub";
    	char c[] = key.toCharArray();
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++)
            a[i] = (char) (a[i] ^ c[i%6]);  
        String s = new String(a);  
        return s;
    }  
}