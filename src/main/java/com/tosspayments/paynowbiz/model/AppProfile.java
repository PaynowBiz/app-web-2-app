package com.tosspayments.paynowbiz.model;

public class AppProfile {
	//암호화 안되는 파라미터
	private String type;            //호출타입(WEB, APP)
	private String work;            //결제유형(일반결제 normal, 간편결제 simple, 간편결제취소 cancel, 거래내역 transaction)
	private String reqtype;         //요청타입(PARAM, JSON)
	private String amount_modify;   //금액변경여부(가능 Y, 불가능 N)
	private String partnercd;       //고객코드
	private String partnernm;       //고객명
	private String url;             //URL
	
	//암호화 되는 파라미터
	private String method;          //연동방식(인증 auth, 미인증 unauth)
	private String version;         //버전(1.0)
	private String subid;           //영업사원아이디
	private String name;            //이름
	private String phone;           //휴대폰번호
	private String autoreg;         //영업사원아이디 자동등록
	private String displaytype;     //화면유형(금액입력화면 0, 상품선택화면 1)
	private String cashpay;         //현금결제(사용 Y, 미사용 N)
	private String divisionpayskip; //분할결제SKIP(사용 Y, 미사용 N)
	private String memberskip;      //고객선택화면SKIP(사용 Y, 미사용 N)
	private String needpaymentinfo; //카드승인번호요청여부(사용  Y, 미사용 N)
	private String reserved1;       //예약필드1
	private String reserved2;       //예약필드2
	private String reserved3;       //예약필드3
	private String reserved4;       //예약필드4
	private String reserved5;       //예약필드5
	private String oid;             //가맹점주문번호
	private String callbackurl;     //리턴URL (WEB2APP인 경우 결과를 응답받을 가맹점 URL)
	private String buyer_phone;     //구매자휴대폰번호
	private String buyer_email;     //구매자이메일주소
	private String paynowbiz_oid;   //페이나우비즈주문번호(결제시 리턴)
	private String tid;             //토스페이먼츠거래번호
	private int amount;             //결제금액
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getReqtype() {
		return reqtype;
	}
	public void setReqtype(String reqtype) {
		this.reqtype = reqtype;
	}
	public String getAmount_modify() {
		return amount_modify;
	}
	public void setAmount_modify(String amount_modify) {
		this.amount_modify = amount_modify;
	}
	public String getPartnercd() {
		return partnercd;
	}
	public void setPartnercd(String partnercd) {
		this.partnercd = partnercd;
	}
	public String getPartnernm() {
		return partnernm;
	}
	public void setPartnernm(String partnernm) {
		this.partnernm = partnernm;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSubid() {
		return subid;
	}
	public void setSubid(String subid) {
		this.subid = subid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAutoreg() {
		return autoreg;
	}
	public void setAutoreg(String autoreg) {
		this.autoreg = autoreg;
	}
	public String getDisplaytype() {
		return displaytype;
	}
	public void setDisplaytype(String displaytype) {
		this.displaytype = displaytype;
	}
	public String getCashpay() {
		return cashpay;
	}
	public void setCashpay(String cashpay) {
		this.cashpay = cashpay;
	}
	public String getDivisionpayskip() {
		return divisionpayskip;
	}
	public void setDivisionpayskip(String divisionpayskip) {
		this.divisionpayskip = divisionpayskip;
	}
	public String getMemberskip() {
		return memberskip;
	}
	public void setMemberskip(String memberskip) {
		this.memberskip = memberskip;
	}
	public String getNeedpaymentinfo() {
		return needpaymentinfo;
	}
	public void setNeedpaymentinfo(String needpaymentinfo) {
		this.needpaymentinfo = needpaymentinfo;
	}
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	public String getReserved2() {
		return reserved2;
	}
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
	public String getReserved3() {
		return reserved3;
	}
	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}
	public String getReserved4() {
		return reserved4;
	}
	public void setReserved4(String reserved4) {
		this.reserved4 = reserved4;
	}
	public String getReserved5() {
		return reserved5;
	}
	public void setReserved5(String reserved5) {
		this.reserved5 = reserved5;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getCallbackurl() {
		return callbackurl;
	}
	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}
	public String getBuyer_phone() {
		return buyer_phone;
	}
	public void setBuyer_phone(String buyer_phone) {
		this.buyer_phone = buyer_phone;
	}
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	public String getPaynowbiz_oid() {
		return paynowbiz_oid;
	}
	public void setPaynowbiz_oid(String paynowbiz_oid) {
		this.paynowbiz_oid = paynowbiz_oid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}