# 가맹점APP(Web)에서 페이나우비즈APP 연동을 위한 가이드
[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https://github.com/PaynowBiz/app-web-2-app)](https://github.com/PaynowBiz/app-web-2-app)
[![토스페이먼츠](https://img.shields.io/badge/토스페이먼츠-blue?style=flat&logoColor=white)](https://www.tosspayments.com)

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
　[6-4. 거래내역 transaction](#6-4-거래내역-transaction) <br>
[7. 연동관련 문의하기](#7-연동관련-문의하기) <br>

## 1. 개요
 가맹점 APP(WEB)에서 PaynowBiz APP을 연동해서 사용 하려는 경우 호출하는 연동 가이드 입니다.
* [테스트 페이지](https://paynowbiz.tosspayments.com/sample/web2appView.do) 에서 테스트로 발급된 상점ID로 테스트해 볼수 있습니다.
<br>

## 2. 암호화 방식 및 위변조 방지
* 암호화 방식<br>
　➡️ 암호화 알고리즘 : AES-128<br>
　➡️ 암호화 모드 : ECB<br>
　➡️ 암호화 패딩 : PKCS5, PKCS7<br>
* 위변조 방지<br>
　➡️ 해쉬 알고리즘 : SHA-256<br>
<br>

## 3. URL스키마
* **`APP`**://paynowbiz.tosspayments.com/**`url`**/mertid=&type=&reqtype=&partnercd=&partnernm=&data=&hmac=<br>

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
|**`hmac`**|해쉬 알고리즘을 적용한 값|
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
||`data`|||<span style="background-color:beige;">_아래 정보를 암호화_</span>|
|`version`|필수|3|1.0|버전|
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
|`hmac`|필수|-|영문,숫자|해쉬 알고리즘을 적용한 값|
<br>

## 7. 연동관련 문의하기
 >이메일 : techsupport@tosspayments.com<br>
 >디스코드 : https://discord.gg/b9GFMxqJVN<br>
