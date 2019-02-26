package Other;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5 {
	public static void main(String args[]) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String str = "951753";
		String newstr = EncoderByMd5("951753");
		System.out.println(newstr);
		EncoderByMd5(newstr);
//		newstr = EncoderByMd5(newstr);
//		System.out.println(newstr);
//		EncoderByMd5(newstr);
	}
	public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newstr;
	}
}
