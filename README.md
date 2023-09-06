# 가맹점APP(Web)에서 페이나우비즈APP 연동 가이드
[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https://github.com/PaynowBiz/app-web-2-app)](https://github.com/PaynowBiz/app-web-2-app)
[![토스페이먼츠](https://img.shields.io/badge/토스페이먼츠-blue?style=flat&logoColor=white)](https://www.tosspayments.com)
[![페이나우비즈](https://img.shields.io/badge/페이나우비즈-bluesky?style=flat&logoColor=white)](https://paynowbiz.tosspayments.com)
![JAVA](https://img.shields.io/badge/Java%20v1.8-6DB33F?style=flat&logo=OpenJDK&logoColor=white)
![SpringBoot](https://img.shields.io/badge/Spring%20Boot%20v2.7.15-6DB33F?style=flat&logo=Spring%20Boot&logoColor=white)

[1. 개요](#1-개요) <br>
[2. 암호화 방식 및 위변조 방지](#2-암호화-방식-및-위변조-방지) <br>
[3. URL스키마](#3-url스키마) <br>
[4. 시작하기](#4-시작하기) <br>
[5. 요청정보](#5-요청정보) <br>
　[5-1. 간편결제 paymentSimple](#5-1-간편결제-paymentsimple) <br>
　[5-2. 간편취소 paymentCancel](#5-2-간편취소-paymentcancel) <br>
　[5-3. 일반결제 payment](#5-3-일반결제-payment) <br>
　[5-4. 거래내역 transaction](#5-4-거래내역-transaction) <br>
[6. 응답정보](#6-응답정보) <br>
　[6-1. 간편결제 paymentSimple](#6-1-간편결제-paymentsimple) <br>
　[6-2. 간편취소 paymentCancel](#6-2-간편취소-paymentcancel) <br>
　[6-3. 일반결제 payment](#6-3-일반결제-payment) <br>
　[6-5. 응답코드](#6-5-응답코드) <br> 
　[6-6. 결제수단](#6-6-결제수단) <br> 
　[6-7. 결제방식](#6-7-결제방식) <br> 
[7. 연동관련 문의하기](#7-연동관련-문의하기) <br>

## 1. 개요
 가맹점 APP(WEB)에서 PaynowBiz APP을 연동해서 사용 하려는 경우 호출하는 연동 가이드 입니다.
* [테스트 페이지](https://paynowbiz.tosspayments.com/sample/web2appView.do) 에서 테스트로 발급된 상점ID로 테스트해 볼수 있습니다.
<br>

## 2. 암호화 방식 및 위변조 방지
* [암호화 방식](https://github.com/PaynowBiz/app-web-2-app/blob/main/src/main/java/com/tosspayments/paynowbiz/util/AESUtil.java#L28)<br>
　➡️ 암호화 알고리즘 : AES-128<br>
　➡️ 암호화 모드 : ECB<br>
　➡️ 암호화 패딩 : PKCS5<br>
* [위변조 방지](https://github.com/PaynowBiz/app-web-2-app/blob/main/src/main/java/com/tosspayments/paynowbiz/util/BaseUtil.java#L69)<br>
　➡️ 해쉬 알고리즘 : SHA-256<br>
<br>

## 3. URL스키마
ex) **`APP`**://paynowbiz.tosspayments.com/**`url`**/mertid=&type=&reqtype=&partnercd=&partnernm=&data=&hmac=<br>

|Entity|Description|
|-----|:-----|
|**`APP`**|`paynowauth` 인증APP([Android](https://play.google.com/store/apps/details?id=com.lguopg.paynowauth&hl=ko-KR) / [iOS](https://apps.apple.com/kr/app/id1261678163))<br> `paynow` 미인증APP([Android](https://play.google.com/store/apps/details?id=com.lguopg.paynow&hl=ko-KR) / [iOS](https://apps.apple.com/kr/app/id640949462))|
|**`url`**|`paymentSimple` 간편결제<br>`paymentCancel` 간편취소<br>`payment` 일반결제<br> `transaction` 거래내역|
|**`mertid`**|PaynowBiz 상점ID|
|**`type`**|`APP` App에서 넘기는 경우<br>`WEB` Web에서 넘기는 경우|
|**`reqtype`**|`JSON` 요청정보를 json으로 넘기는 경우<br>`PARAM` 요청정보를 get방식으로 넘기는 경우|
|**`partnercd`**|거래처코드를 넘기는 경우|
|**`partnernm`**|거래처명을 넘기는 경우|
|**`data`**|나머지 요청 정보를 암호화 한 값|
|**`hmac`**|`url`별 해쉬 알고리즘을 적용한 값|
<br>

## 4. 시작하기
 0) IDE Tool 에서 소스코드를 Clone 합니다.
 1) [Constants.java](https://github.com/PaynowBiz/app-web-2-app/blob/main/src/main/java/com/tosspayments/paynowbiz/util/Constants.java) 에서 [문의하기](#7-연동관련-문의하기) 로 받은 `mertid` `certKey` `apiKey` 를 차례대로 입력 합니다.
 2) 서버를 실행 시키고, 브라우져에서 아래 URL을 입력해 봅니다.
    1. 간편결제 : [http://localhost:8080/app/simple](http://localhost:8080/app/simple)
    2. 간편취소 : [http://localhost:8080/app/cancel](http://localhost:8080/app/cancel)
    3. 일반결제 : [http://localhost:8080/app/normal](http://localhost:8080/app/normal)
    4. 거래내역 : [http://localhost:8080/app/transaction](http://localhost:8080/app/transaction)
 3) [Construct.java](https://github.com/PaynowBiz/app-web-2-app/blob/main/src/main/java/com/tosspayments/paynowbiz/util/Constuct.java) 에서 연동 하고자 하는 `url` 로 설정값을 변경해서 호출 하면 됩니다.
<br>

## 5. 요청정보
## 5-1. 간편결제 `paymentSimple`
Entity|Required|Length|Restriction|Description
|-----|:-----:|-----:|-----|-----|
|`mertid`|필수|15|영문,숫자|PaynowBiz상점ID|
|`type`|필수|3|영문|호출타입(APP, WEB)|
|`reqtype`|필수|5|영문|요청타입(JSON, PARAM)|
|`partnercd`|선택|128||거래처코드|
|`partnernm`|선택|128||거래처명|
||`data`|||_`아래 정보를 암호화`_|
|`version`|필수|3|1.0(고정값)|버전|
|`certkey`|필수|16|영문,숫자|인증키|
|`subid`|필수|11|영문,숫자|영업사원ID|
|`name`|필수|20|문자|영업사원명|
|`phone`|선택|11|숫자|영업사원휴대폰번호|
|`autoreg`|필수|1|영문|영업사원ID자동등록여부<br>`Y`자동등록<br>`N`기등록된상태|
|`oid`|필수|30|영문,숫자|가맹점에서 생성한 주문번호|
|`amount`|필수|11|숫자|결제금액|
|`buyer_phone`|선택|11|숫자|구매자연락처|
|`buyer_email`|선택|128|문자|구매자이메일|
|`needpaymentinfo`|선택|1|영문|카드승인번호요청여부<br>`Y`reserved5에결제정보전달<br>`N`결제정보 미전달|
|`callbackurl`|선택|-|문자|`APP`<br>`WEB` 결제완료 후 결제정보를 넘길 콜백URL|
|`reserved1`|선택|128|문자|예약필드1|
|`reserved2`|선택|128|문자|예약필드2|
|`reserved3`|선택|128|문자|예약필드3|
|`reserved4`|선택|128|문자|예약필드4|
|`reserved5`|선택|128|문자|예약필드5|
||`hmac`|필수|영문,숫자|[_`해쉬 알고리즘을 적용한 값`_](https://github.com/PaynowBiz/app-web-2-app/blob/main/src/main/java/com/tosspayments/paynowbiz/util/BaseUtil.java#L69)|
<br>

## 5-2. 간편취소 `paymentCancel`
Entity|Required|Length|Restriction|Description
|-----|:-----:|-----:|-----|-----|
|`mertid`|필수|15|영문,숫자|PaynowBiz상점ID|
|`type`|필수|3|영문|호출타입(APP, WEB)|
|`reqtype`|필수|5|영문|요청타입(JSON, PARAM)|
||`data`|||_`아래 정보를 암호화`_|
|`version`|필수|3|1.0(고정값)|버전|
|`certkey`|필수|16|영문,숫자|인증키|
|`subid`|필수|11|영문,숫자|영업사원ID|
|`oid`|필수|30|영문,숫자|가맹점에서 생성한 주문번호|
|`paynowbiz_oid`|필수|18|영문,숫자|PaynowBiz에서 생성한 주문번호|
|`tid`|필수|24|영문,숫자|TossPayments에서 생성한 거래번호|
|`callbackurl`|선택|-|문자|`APP`<br>`WEB` 결제취소 후 결제정보를 넘길 콜백URL|
||`hmac`|필수|영문,숫자|[_`해쉬 알고리즘을 적용한 값`_](https://github.com/PaynowBiz/app-web-2-app/blob/main/src/main/java/com/tosspayments/paynowbiz/util/BaseUtil.java#L69)|
<br>

## 5-3. 일반결제 `payment`
Entity|Required|Length|Restriction|Description
|-----|:-----:|-----:|-----|-----|
|`mertid`|필수|15|영문,숫자|PaynowBiz상점ID|
|`type`|필수|3|영문|호출타입(APP, WEB)|
|`reqtype`|필수|5|영문|요청타입(JSON, PARAM)|
|`amount_modify`|선택|1|영문|금액변경여부(default : Y)<br>`Y` 변경가능, `N` 변경불가|
|`partnercd`|선택|128||거래처코드|
|`partnernm`|선택|128||거래처명|
||`data`|||_`아래 정보를 암호화`_|
|`version`|필수|3|1.0(고정값)|버전|
|`certkey`|필수|16|영문,숫자|인증키|
|`subid`|필수|11|영문,숫자|영업사원ID|
|`name`|필수|20|문자|영업사원명|
|`phone`|선택|11|숫자|영업사원휴대폰번호|
|`autoreg`|필수|1|영문|영업사원ID자동등록여부<br>`Y`자동등록<br>`N`기등록된상태|
|`displaytype`|선택|1|숫자|화면유형<br>`0` 금액입력화면, `1` 상품선택화면|
|`cashpay`|선택|1|영문|현금결제가능여부<br>`Y` 현금결제가능, `N` 현금결제불가|
|`divisionpayskip`|선택|1|영문|분할결제Skip여부(default : N)<br>`Y` 분할결제불가, `N` 분할결제가능|
|`memberskip`|선택|1|영문|고객선택화면Skip여부(default : N)<br>`Y` 고객선택안함, `N` 고객선택(스마트팜 상점만 해당)|
|`amount`|필수|11|숫자|결제금액|
|`buyer_phone`|선택|11|숫자|구매자연락처|
|`buyer_email`|선택|128|문자|구매자이메일|
|`needpaymentinfo`|선택|1|영문|카드승인번호요청여부<br>`Y`reserved5에결제정보전달<br>`N`결제정보 미전달|
|`callbackurl`|선택|-|문자|`APP`<br>`WEB` 결제완료 후 결제정보를 넘길 콜백URL|
|`reserved1`|선택|128|문자|예약필드1|
|`reserved2`|선택|128|문자|예약필드2|
|`reserved3`|선택|128|문자|예약필드3|
|`reserved4`|선택|128|문자|예약필드4|
|`reserved5`|선택|128|문자|예약필드5|
||`hmac`|필수|영문,숫자|[_`해쉬 알고리즘을 적용한 값`_](https://github.com/PaynowBiz/app-web-2-app/blob/main/src/main/java/com/tosspayments/paynowbiz/util/BaseUtil.java#L69)|
<br>

## 5-4. 거래내역 `transaction`
Entity|Required|Length|Restriction|Description
|-----|:-----:|-----:|-----|-----|
|`mertid`|필수|15|영문,숫자|PaynowBiz상점ID|
|`type`|필수|3|영문|호출타입(APP, WEB)|
|`reqtype`|필수|5|영문|요청타입(JSON, PARAM)|
||`data`|||_`아래 정보를 암호화`_|
|`version`|필수|3|1.0(고정값)|버전|
|`certkey`|필수|16|영문,숫자|인증키|
|`subid`|필수|11|영문,숫자|영업사원ID|
|`callbackurl`|선택|-|문자|`APP`<br>`WEB` 콜백URL|
||`hmac`|필수|영문,숫자|[_`해쉬 알고리즘을 적용한 값`_](https://github.com/PaynowBiz/app-web-2-app/blob/main/src/main/java/com/tosspayments/paynowbiz/util/BaseUtil.java#L69)|
<br>


## 6. 응답정보
## 6-1. 간편결제 `paymentSimple`
Entity|Required|Length|Restriction|Description
|-----|:-----:|-----:|-----|-----|
|`respcd`|필수|4|숫자|[응답코드](#6-5-응답코드)|
|`respmsg`|필수|-|문자|[응답메시지](#6-5-응답코드)|
|`version`|필수|3|1.0(고정값)|버전|
|`oid`|필수|30|영문,숫자|가맹점에서 생성한 주문번호|
|`paynowbiz_oid`|필수|18|영문,숫자|PaynowBiz에서 생성한 주문번호|
|`amount`|필수|11|숫자|결제금액|
|`pay_type`|선택|6|영문,숫자|[결제수단](#6-6-결제수단)|
|`tid`|필수|24|영문,숫자|TossPayments에서 생성한 거래번호|
|`vbv_eci`|선택|3|숫자|[결제방식](#6-7-결제방식)|
|`reserved1`|선택|128|문자|예약필드1|
|`reserved2`|선택|128|문자|예약필드2|
|`reserved3`|선택|128|문자|예약필드3|
|`reserved4`|선택|128|문자|예약필드4|
|`reserved5`|선택|128|문자|예약필드5<br>`needpaymentinfo` = `Y` 일때 카드결제정보<br>{"cardno":"12345678****123",<br>"authno":"12345678",<br>"authdate":"2023-09-04T15:05:22"}|
<br>

## 6-2. 간편취소 `paymentCancel`
Entity|Required|Length|Restriction|Description
|-----|:-----:|-----:|-----|-----|
|`respcd`|필수|4|숫자|[응답코드](#6-5-응답코드)|
|`respmsg`|필수|-|문자|[응답메시지](#6-5-응답코드)|
|`version`|필수|3|1.0(고정값)|버전|
|`oid`|필수|30|영문,숫자|가맹점에서 생성한 주문번호|
|`paynowbiz_oid`|필수|18|영문,숫자|PaynowBiz에서 생성한 주문번호|
|`amount`|필수|9|숫자|결제취소금액|
|`tid`|필수|24|영문,숫자|TossPayments에서 생성한 거래번호|
<br>

## 6-3. 일반결제 `payment`
Entity|Required|Length|Restriction|Description
|-----|:-----:|-----:|-----|-----|
|`respcd`|필수|4|숫자|[응답코드](#6-5-응답코드)|
|`respmsg`|필수|-|문자|[응답메시지](#6-5-응답코드)|
|`version`|필수|3|1.0(고정값)|버전|
|`oid`|필수|30|영문,숫자|가맹점에서 생성한 주문번호|
|`paynowbiz_oid`|필수|18|영문,숫자|PaynowBiz에서 생성한 주문번호|
|`amount`|필수|11|숫자|결제금액|
|`pay_type`|선택|6|영문,숫자|[결제수단](#6-6-결제수단)|
|`pay_plan_yn`|선택|1|영문,숫자|분할결제여부<br> Android : `Y`, `N`<br>iOS : `1`, `0`|
|`pay_plan_list`|선택|-|문자|분할결제여부 = `Y` or `1` 일때<br>Android : `pay_type:tid:amount:vbv_eci`<br>iOS : `[{pay_type, tid, amount, vbv_eci}]`|
||`pay_type`|6|영문,숫자|[결제수단](#6-6-결제수단)|
||`tid`|24|영문,숫자|TossPayments에서 생성한 거래번호|
||`amount`|9|숫자|결제금액|
||`vbv_eci`|3|숫자|[결제방식](#6-7-결제방식)|
|`tid`|필수|24|영문,숫자|TossPayments에서 생성한 거래번호|
|`vbv_eci`|선택|3|숫자|[결제방식](#6-7-결제방식)|
|`reserved1`|선택|128|문자|예약필드1|
|`reserved2`|선택|128|문자|예약필드2|
|`reserved3`|선택|128|문자|예약필드3|
|`reserved4`|선택|128|문자|예약필드4|
|`reserved5`|선택|128|문자|예약필드5<br>`needpaymentinfo` = `Y` 일때 카드결제정보<br>{"cardno":"12345678****123",<br>"authno":"12345678",<br>"authdate":"2023-09-04T15:05:22"}|
<br>

## 6-5. 응답코드
Code|Description
|-----|-----|
|`0000`|결제(취소)성공|
|`0100`|사용자취소|
|`1000`|인증실패|
|`1001`|결제실패|
|`2000`|필수값누락|
|`9999`|정의되지않은에러|
<br>

## 6-6. 결제수단
Code|Description
|-----|-----|
|`SC0010`|카드결제|
|`SC0100`|현금결제|
<br>

## 6-7. 결제방식
Code|Description
|-----|-----|
|`010`|key-in|
|`020`|swipe|
|`030`|IC Chip|
<br>

## 7. 연동관련 문의하기
 >이메일 : techsupport@tosspayments.com<br>
 >디스코드 : https://discord.gg/b9GFMxqJVN<br>
