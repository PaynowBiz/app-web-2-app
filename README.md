# 가맹점APP(Web)에서 페이나우비즈APP 연동을 위한 가이드

[1. 개요](#1-개요) <br>
[2. 암호화 방식 및 위변조 방지](#2-암호화-방식-및-위변조-방지) <br>
[3. URL](#3-URL) <br>
[4. 시작하기](#4-시작하기) <br>
[5. 요청 정보](#5-요청-정보) <br>
　[5-1. 간편결제 paymentSimple](#5-1-간편결제-paymentSimple) <br>
　[5-2. 간편취소 paymentCancel](#5-2-간편취소-paymentCancel) <br>
　[5-3. 일반결제 payment](#5-3-일반결제-payment) <br>
　[5-4. 거래내역 transaction](#5-4-거래내역-transaction) <br>
[6. 응답 정보](#6-응답-정보) <br>
　[6-1. 간편결제 paymentSimple](#6-1-간편결제-paymentSimple) <br>
　[6-2. 간편취소 paymentCancel](#6-2-간편취소-paymentCancel) <br>
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
* {연동APP}://paynowbiz.tosspayments.com/{url}/mertid={상점Id}&type={호출타입}&reqtype={요청타입}&partnercd={거래처코드}&partnernm={거래처명}&data={암호화}&hmac={위변조방지}
>**`연동App`** `paynowauth` 인증APP(Android / iOS), `paynow` : 미인증APP(Android / iOS)<br>
>**`url`**[서비스코드] **`data`**[요청할 정보를 암호화 한 값]
