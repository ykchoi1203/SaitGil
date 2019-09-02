package wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper{

	public EncryptWrapper(HttpServletRequest request) {
		
		
		super(request);
	}
	
	
	@Override
	public String getParameter(String key) {
		String value = "";
		
		if(key != null && (key.equals("userPwd") || key.equals("newPwd"))) {
			
			value = getSha512(super.getParameter(key));
		} else {
			value = super.getParameter(key);
		}
		
		return value;
	}
	
	
	
	
	
	public String getSha512(String userPwd) {
		String encPwd = "";
		
		MessageDigest md = null; 
		
		try {
			md = MessageDigest.getInstance("SHA-512");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] bytes = userPwd.getBytes(Charset.forName("utf-8"));
		
		md.update(bytes);
		
		encPwd = Base64.getEncoder().encodeToString(md.digest());
		
		
		return encPwd;
		
	}

}
