package net.nurigo.java_sdk.examples.Message;

import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleSend
 * @brief This sample code demonstrate how to send sms through CoolSMS Rest API PHP
 */
public class ExampleSend {
  public String send(int authNo, String partnerPhone) {
    String api_key = "NCSU4D6P4ZEACPF7";
    String api_secret = "9EL9BZI5OIK5JYQY6AR3APZBUJXGUJTQ";
    Message coolsms = new Message(api_key, api_secret);

    // 4 params(to, from, type, text) are mandatory. must be filled
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("to", partnerPhone);
    params.put("from", "01046062929");
    params.put("type", "SMS");
    params.put("text", "사잇길에서 전송하는 커플코드입니다. [" + authNo + "]를 입력해주세요.");
    params.put("app_version", "test app 1.2"); // application name and version

    try {
      JSONObject obj = (JSONObject) coolsms.send(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
    
    return "완료";
  }
  
}