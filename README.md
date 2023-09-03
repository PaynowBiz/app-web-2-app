# 가맹점APP(Web)에서 페이나우비즈APP 연동을 위한 가이드

[1. 개요](#1-개요) <br>
[2. 암호화 방식 및 위변조 방지](#2-암호화-방식-및-위변조-방지) <br>
[3. URL](#3-url) <br>
[4. 시작하기](#4-시작하기) <br>
[5. 요청 정보](#5-요청-정보) <br>
　[5-1. 간편결제 paymentSimple](#5-1-간편결제-paymentsimple) <br>
　[5-2. 간편취소 paymentCancel](#5-2-간편취소-paymentcancel) <br>
　[5-3. 일반결제 payment](#5-3-일반결제-payment) <br>
　[5-4. 거래내역 transaction](#5-4-거래내역-transaction) <br>
[6. 응답 정보](#6-응답-정보) <br>
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
　➡️ 해쉬 알고리즘 SHA-256<br>
<br>

## 3. URL
* **`APP`**://paynowbiz.tosspayments.com/**`url`**/mertid=&type=&reqtype=&partnercd=&partnernm=&data=&hmac=<br>

|Entity|Required|
|-----|:-----|
|**`APP`**|`paynowauth` : 인증APP([Android](https://play.google.com/store/apps/details?id=com.lguopg.paynowauth&hl=ko-KR) / [iOS](https://apps.apple.com/kr/app/id1261678163))<br> `paynow` : 미인증APP([Android](https://play.google.com/store/apps/details?id=com.lguopg.paynow&hl=ko-KR) / [iOS](https://apps.apple.com/kr/app/id640949462))|
|**`url`**|`paymentSimple` : 간편결제<br>`paymentCancel` : 간편취소<br>`payment` :일반결제<br> `transaction` 거래내역|
|**`mertid`**|PaynowBiz 상점ID|
|**`type`**|`APP` App에서 넘기는 경우<br>`WEB` Web에서 넘기는 경우|
|**`reqtype`**|`JSON` 요청정보를 json으로 넘기는 경우<br>`PARAM` 요청정보를 Get방식으로 넘기는 경우|
|**`partnercd`**|거래처코드를 넘기는 경우|
|**`partnernm`**|거래처명을 넘기는 경우|
|**`data`**|나머지 요청 정보를 암호화 한 값|
|**`hmac`**|해쉬 알고리즘을 적용한 값|
<br>

## 4. 시작하기
 0) IDE Tool 에서 소스코드를 Clone 한다.
 1) Constants.java 에서 [#7-연동관련-문의하기] 로 받은 `mertid` `certKey` `apiKey` 를 차례대로 입력 한다.
 2) [customer](#6-3-거래처관리-servicecode--customer) : 거래처 등록/수정/삭제
