package com.tosspayments.paynowbiz.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tosspayments.paynowbiz.model.AppProfile;
import com.tosspayments.paynowbiz.util.Constants;
import com.tosspayments.paynowbiz.util.Construct;

@RestController
public class AppToAppController extends Construct {

	String hashStr = "";
	
	/**
	 * @category JSON 가져오기
	 * @param id
	 * @return AppProfile
	 */
	@GetMapping("app/json/{id}")
	public AppProfile getAppProfile(@PathVariable("id") String id) {
		return map.get(id);
	}

	/***
	 * @category 간편결제
	 * @return String
	 */
	@GetMapping("app/simple")
	public String getSimple() {
		String result = "";
		HashMap<String, Object> param = new HashMap<String, Object>();
		HashMap<String, Object> paramEnc = new HashMap<String, Object>();
		
		try {
			//암호화 X
			param.put("mertid",             Constants.PAYNOWBIZ_MERTID);
			param.put("type",               map.get("simple").getType());
			param.put("reqtype",            map.get("simple").getReqtype());
			param.put("partnercd",          map.get("simple").getPartnercd());
			param.put("partnernm",          map.get("simple").getPartnernm());
			param.put("url",                map.get("simple").getUrl());
			
			//암호화 ㅇ 
			paramEnc.put("certkey",         Constants.PAYNOWBIZ_CERTKEY);
			paramEnc.put("version",         map.get("simple").getVersion());
			paramEnc.put("subid",           map.get("simple").getSubid());
			paramEnc.put("autoreg",         map.get("simple").getAutoreg());
			paramEnc.put("name",            map.get("simple").getName());
			paramEnc.put("phone",           map.get("simple").getPhone());
			paramEnc.put("amount",          map.get("simple").getAmount());
			paramEnc.put("needpaymentinfo", map.get("simple").getNeedpaymentinfo());
			paramEnc.put("callbackurl",     map.get("simple").getCallbackurl());
			paramEnc.put("oid",             map.get("simple").getOid());
			paramEnc.put("buyer_phone",     map.get("simple").getBuyer_phone());
			paramEnc.put("buyer_email",     map.get("simple").getBuyer_email());
			paramEnc.put("reserved1",       map.get("simple").getReserved1());
			paramEnc.put("reserved2",       map.get("simple").getReserved2());
			paramEnc.put("reserved3",       map.get("simple").getReserved3());
			paramEnc.put("reserved4",       map.get("simple").getReserved4());
			paramEnc.put("reserved5",       map.get("simple").getReserved5());
				
			hashStr = map.get("simple").getSubid() + map.get("simple").getAmount() + map.get("simple").getOid();
			System.out.printf("param[%s], paramEnc[%s], hashStr[%s]", param, paramEnc, hashStr);
			System.out.println();
			
			result = base.getUri(param, paramEnc, hashStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/***
	 * @category 간편결제 취소
	 * @return String
	 */
	@GetMapping("app/cancel")
	public String getCancel() {
		String result = "";
		HashMap<String, Object> param = new HashMap<String, Object>();
		HashMap<String, Object> paramEnc = new HashMap<String, Object>();
		
		try {
			//암호화 X
			param.put("mertid",             Constants.PAYNOWBIZ_MERTID);
			param.put("type",               map.get("cancel").getType());
			param.put("reqtype",            map.get("cancel").getReqtype());
			
			//암호화 ㅇ 
			paramEnc.put("certkey",         Constants.PAYNOWBIZ_CERTKEY);
			paramEnc.put("version",         map.get("cancel").getVersion());
			paramEnc.put("subid",           map.get("cancel").getSubid());
			paramEnc.put("autoreg",         map.get("cancel").getAutoreg());
			paramEnc.put("needpaymentinfo", map.get("cancel").getNeedpaymentinfo());
			paramEnc.put("callbackurl",     map.get("cancel").getCallbackurl());
			paramEnc.put("oid",             map.get("cancel").getOid());
			paramEnc.put("paynowbiz_oid",   map.get("cancel").getPaynowbiz_oid());
			paramEnc.put("tid",             map.get("cancel").getTid());
			
			hashStr = map.get("cancel").getSubid() + map.get("cancel").getTid() + map.get("cancel").getOid();
			System.out.printf("param[%s], paramEnc[%s], hashStr[%s]", param, paramEnc, hashStr);
			System.out.println();
			
			result = base.getUri(param, paramEnc, hashStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/***
	 * @category 일반결제
	 * @return String
	 */
	@GetMapping("app/normal")
	public String getNormal() {
		String result = "";
		HashMap<String, Object> param = new HashMap<String, Object>();
		HashMap<String, Object> paramEnc = new HashMap<String, Object>();
		
		try {
			//암호화 X
			param.put("mertid",             Constants.PAYNOWBIZ_MERTID);
			param.put("type",               map.get("normal").getType());
			param.put("reqtype",            map.get("normal").getReqtype());
			param.put("partnercd",          map.get("normal").getPartnercd());
			param.put("partnernm",          map.get("normal").getPartnernm());
			param.put("amount_modify",      map.get("normal").getAmount_modify());
			
			//암호화 ㅇ 
			paramEnc.put("certkey",         Constants.PAYNOWBIZ_CERTKEY);
			paramEnc.put("version",         map.get("normal").getVersion());
			paramEnc.put("subid",           map.get("normal").getSubid());
			paramEnc.put("autoreg",         map.get("normal").getAutoreg());
			paramEnc.put("name",            map.get("normal").getName());
			paramEnc.put("phone",           map.get("normal").getPhone());
			paramEnc.put("amount",          map.get("normal").getAmount());
			paramEnc.put("needpaymentinfo", map.get("normal").getNeedpaymentinfo());
			paramEnc.put("callbackurl",     map.get("normal").getCallbackurl());
			paramEnc.put("oid",             map.get("normal").getOid());
			paramEnc.put("buyer_phone",     map.get("normal").getBuyer_phone());
			paramEnc.put("buyer_email",     map.get("normal").getBuyer_email());
			paramEnc.put("reserved1",       map.get("normal").getReserved1());
			paramEnc.put("reserved2",       map.get("normal").getReserved2());
			paramEnc.put("reserved3",       map.get("normal").getReserved3());
			paramEnc.put("reserved4",       map.get("normal").getReserved4());
			paramEnc.put("reserved5",       map.get("normal").getReserved5());
			paramEnc.put("displaytype",     map.get("normal").getDisplaytype());
			paramEnc.put("cashpay",         map.get("normal").getCashpay());
			paramEnc.put("divisionpayskip", map.get("normal").getDivisionpayskip());
			paramEnc.put("memberskip",      map.get("normal").getMemberskip());
			
			hashStr = map.get("normal").getSubid();
			System.out.printf("param[%s], paramEnc[%s], hashStr[%s]", param, paramEnc, hashStr);
			System.out.println();

			result = base.getUri(param, paramEnc, hashStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/***
	 * @category 거래내역
	 * @return String
	 */
	@GetMapping("app/transaction")
	public String getTransaction() {
		String result = "";
		HashMap<String, Object> param = new HashMap<String, Object>();
		HashMap<String, Object> paramEnc = new HashMap<String, Object>();
		
		try {
			//암호화 X
			param.put("mertid",     Constants.PAYNOWBIZ_MERTID);
			param.put("type",       map.get("transaction").getType());
			param.put("reqtype",    map.get("transaction").getReqtype());
			param.put("url",        map.get("transaction").getUrl());
			
			//암호화 ㅇ 
			paramEnc.put("certkey", Constants.PAYNOWBIZ_CERTKEY);
			paramEnc.put("version", map.get("transaction").getVersion());
			paramEnc.put("subid",   map.get("transaction").getSubid());
			paramEnc.put("autoreg", map.get("transaction").getAutoreg());
			
			hashStr = map.get("transaction").getSubid();
			System.out.printf("param[%s], paramEnc[%s], hashStr[%s]", param, paramEnc, hashStr);
			System.out.println();
			
			result = base.getUri(param, paramEnc, hashStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}