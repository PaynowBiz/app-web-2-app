package com.tosspayments.paynowbiz.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.tosspayments.paynowbiz.model.AppProfile;

public class Construct {

	String json = "{\"ACode\":\"A1\",\"BCode\":\"B2\"}}";  
	public final BaseUtil base = new BaseUtil();
	public final Map<String, AppProfile> map = new HashMap<String, AppProfile>();
	
	/***
	 * @category App To App 프로파일
	 */
	@PostConstruct
	public void appToAppInit() {
		/************************
		 * 간편결제 요청 프로파일
		 *************************/
		AppProfile simple = new AppProfile();
		simple.setType("APP");              //호출타입 (WEB, APP)
		simple.setWork("simple");           //결제유형 (간편결제) (고정)
		simple.setReqtype("JSON");          //요청타입 (PARAM, JSON)
		simple.setPartnercd("");            //고객코드
		simple.setPartnernm("");            //고객명
		simple.setMethod("unauth");         //연동APP (인증앱 auth, 미인증앱 unauth)
		simple.setUrl("paymentSimple");     //URL (고정)
		simple.setVersion("1.0");           //버전 (고정)
		simple.setSubid("tossp301");        //영업사원 아이디
		simple.setName("유토스");           //영업사원 이름
		simple.setAutoreg("Y");             //영업사원 아이디 자동등록 (등록 Y, 미등록 N)
		simple.setNeedpaymentinfo("Y");     //카드 승인번호 리턴 (사용 Y, 미사용 N)
		simple.setReserved1(json);          //예약필드1
		simple.setReserved2("");            //예약필드2
		simple.setReserved3("");            //예약필드3
		simple.setReserved4("");            //예약필드4
		simple.setReserved5("");            //예약필드5
		simple.setOid("t"+base.getRequestApiTime()); //가맹점주문번호
		simple.setCallbackurl("https://paynowbiz.tosspayments.com/returnurl.jsp"); //리턴URL (WEB2APP인 경우 결과를 응답받을 가맹점 URL)
		simple.setBuyer_phone("01012345678"); //구매자휴대폰번호
		simple.setBuyer_email("");            //구매자이메일주소
		simple.setAmount(10000);              //결제금액
		map.put("simple", simple);
		
		/************************
		 * 간편결제 취소 요청 프로파일
		 *************************/
		AppProfile cancel = new AppProfile();
		cancel.setType("APP");              //호출타입 (WEB, APP)
		cancel.setWork("cancel");           //결제유형 (간편결제취소) (고정)
		cancel.setReqtype("JSON");          //요청타입 (PARAM, JSON)
		cancel.setMethod("unauth");         //연동APP (인증앱 auth, 미인증앱 unauth)
		cancel.setVersion("1.0");           //버전 (고정)
		cancel.setUrl("paymentCancel");     //URL (고정)
		cancel.setSubid("tosspayments301"); //영업사원 아이디
		cancel.setAutoreg("Y");             //영업사원 아이디 자동등록 (등록 Y, 미등록 N)
		cancel.setOid("t"+base.getRequestApiTime());     //가맹점주문번호
		cancel.setCallbackurl("https://paynowbiz.tosspayments.com/returnurl.jsp"); //리턴URL (WEB2APP인 경우 결과를 응답받을 가맹점 URL)
		cancel.setPaynowbiz_oid("결제후리턴된PaynowBizOid"); //결제후 리턴된 PaynowBiz oid
		cancel.setTid("결제후리턴된TosspaymentsTid"); //결제후 리턴된 TossPayments tid
		map.put("cancel", cancel);
		
		/*************************
		 * 일반결제 요청 프로파일
		 *************************/
		AppProfile normal = new AppProfile();
		normal.setType("APP");              //호출타입 (WEB, APP)
		normal.setWork("normal");           //결제유형 (일반결제)
		normal.setReqtype("PARAM");         //요청타입 (PARAM, JSON)
		normal.setPartnercd("");            //고객코드
		normal.setPartnernm("");            //고객명
		normal.setMethod("unauth");         //연동APP (인증앱 auth, 미인증앱 unauth)
		normal.setVersion("1.0");           //버전 (고정)
		normal.setUrl("payment");           //URL (고정)
		normal.setSubid("tossp301");        //영업사원 아이디
		normal.setName("유토스");           //영업사원 이름
		normal.setAutoreg("Y");             //영업사원 아이디 자동 등록 (등록 Y, 미등록 N)
		normal.setNeedpaymentinfo("Y");     //카드 승인번호 리턴 (사용 Y, 미사용 N)
		normal.setReserved1("");            //예약필드1
		normal.setReserved2("");            //예약필드2
		normal.setReserved3("");            //예약필드3
		normal.setReserved4("");            //예약필드4
		normal.setReserved5("");            //예약필드5
		normal.setOid("t"+base.getRequestApiTime()); //가맹점주문번호
		normal.setCallbackurl("https://paynowbiz.tosspayments.com/returnurl.jsp"); //리턴URL (WEB2APP인 경우 결과를 응답받을 가맹점 URL)
		normal.setBuyer_phone("01012345678"); //구매자 휴대폰번호
		normal.setBuyer_email("");            //구매자 이메일주소
		normal.setAmount(10000);              //결제금액
		normal.setAmount_modify("N");         //금액변경 (가능 Y, 불가능 N)
		normal.setDisplaytype("0");           //화면유형 (금액입력화면 0, 상품선택화면 1)
		normal.setCashpay("Y");               //현금결제 (사용 Y, 미사용 N)
		normal.setDivisionpayskip("Y");       //분할결제SKIP (사용 Y, 미사용 N)
		normal.setMemberskip("Y");            //고객선택화면SKIP (사용 Y, 미사용 N)
		map.put("normal", normal);

		/************************
		 * 거래내역 요청 프로파일
		 *************************/
		AppProfile transaction = new AppProfile();
		transaction.setType("APP");              //호출타입 (WEB, APP)
		transaction.setWork("transaction");      //결제유형 (거래내역)
		transaction.setReqtype("JSON");          //요청타입 (PARAM, JSON)
		transaction.setMethod("unauth");         //연동APP (인증앱 auth, 미인증앱 unauth)
		transaction.setVersion("1.0");           //버전 (고정)
		transaction.setUrl("transaction");       //URL (고정)
		transaction.setSubid("tosspayments301"); //영업사원 아이디
		transaction.setAutoreg("Y");             //영업사원 아이디 자동등록 (등록 Y, 미등록 N)
		map.put("transaction", transaction);
	}
}