package com.tosspayments.paynowbiz.util;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;

public class BaseUtil {

	/**
	 * @category URI
	 * @param    param
	 * @param    paramEnc
	 * @param    hashStr
	 * @return   String
	 * @author   YooYoungJu
	 */
	public String getUri(HashMap<String, Object> param, HashMap<String, Object> paramEnc, String hashStr) {
		System.out.println("[ST][getUri]");
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			String dataStr = "";
			
			// 요청타입
			if ("PARAM".equals(param.get("reqtype"))) {
				dataStr = getParam(paramEnc);
			} else if("JSON".equals(param.get("reqtype"))) {
				dataStr = getJson(paramEnc);
			}
			System.out.printf("dataStr = %s", dataStr);
			System.out.println();
			
			String data = new AESUtil(Constants.PAYNOWBIZ_APIKEY).encryptEcb(dataStr);
			System.out.println("data : " + data);
			String hmac = getHmac(Constants.PAYNOWBIZ_MERTID + Constants.PAYNOWBIZ_CERTKEY + hashStr);
			System.out.println("hmac : " + hmac);
			
			// 연동APP
			if("auth".equals(param.get("method"))) {
				sb.append("paynowauth://paynowbiz.tosspayments.com/"); //인증APP스킴
			} else {
				sb.append("paynow://paynowbiz.tosspayments.com/"); //미인증APP스킴
			}
			sb.append(param.get("url"));
			param.remove("url"); //param 에서 url 제거
			
			result = sb.toString() + "?" + getParam(param) + "&data=" + URLEncoder.encode(data, "UTF-8") + "&hmac=" + URLEncoder.encode(hmac, "UTF-8");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("[EN][getUri]");
		}
		return result;
	}
	
	/**
	 * @category HMAC
	 * @param    data
	 * @author   YooYoungJu
	 */
	public String getHmac(String data) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] digest = md.digest(data.getBytes());
		
		return new String(Base64.getEncoder().encode(digest));
	}
	
	/**
	 * @category 파람
	 * @param    data
	 * @author   YooYoungJu
	 */	
	public String getParam(HashMap<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		Iterator<String> iter = data.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			sb.append(key);
			sb.append("="+data.get(key));
			if(iter.hasNext())
				sb.append("&");
		}
		return sb.toString();
	}
	
	/**
	 * @category JSON
	 * @param    data
	 * @author   YooYoungJu
	 */	
	public String getJson(HashMap<String, Object> data) throws Exception {
		return new JSONObject(data).toJSONString();
	}
	
	/**
     * @category API요청시간
     */
	public String getRequestApiTime(){
		LocalDateTime date = LocalDateTime.now(ZoneId.of("GMT+09:00"));
		return date.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
	}
}
