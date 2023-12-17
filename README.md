# 📒 일상 사진 공유 

## 프로젝트 소개
- 사용자들이 일상에서 경험한 순간들을 사진으로 담아 다른 사용자들과 공유하는 사이트입니다. 
- 사용자들은 사진을 찍어 업로드하면 다른 사용자들은 이를 감상하고 좋아요를 누르거나 댓글을 남길 수 있습니다.
- 이를 통해 각 사용자는 자신만의 다이어리가 만들어지며, 다양한 사진 컨텐츠를 즐길 수 있습니다.
<br>

## 1. 개발 환경
- Front-end : React
- Back-end : Java, Spring, MyBatis, MySQL
<br>

## 2. 보안
- Form Validation
  - 필수 항목 검증 : 필수 항목에 대한 누락 여부를 체크하여 사용자가 필수 정보를 빠뜨리지 않도록 도움을 줍니다.
  - 정규식 검증 : 특정 항목이 사용자의 입력 요구 사항에 부합하는지 정규식을 통해 검증하여 데이터의 일관성을 유지합니다.
  - 스크립트 방어 : 특수문자나 악의적인 스크립트를 방어하여 보안을 강화합니다.

- reCAPTCHA
  -  봇 방지: 사용자와 봇을 구분하여 악의적인 봇의 접근을 차단하고 안전한 환경을 유지합니다.
    
<br>

## 3. 개발 기간
- Index, Photo - 2023-12-14 ~ 2023-12-17

<br>

## 4. 페이지별 기능

### [index]
- 배너의 시작하기 버튼을 누르면 Photo로 이동합니다.

| Index page |
| -------- |
|    ![1](https://github.com/fxzz/sns/assets/3148006/91b9c3fc-f12e-43ee-b9f9-29a1156805ca) |

<br>

### [Photo]
- 최근에 올라온 사진이 가장 먼저 나타납니다.
- 페이지 전환이나 로딩 없는 Cursor paging
- 제목이나 내용이 길면 ... 으로 표현

| Photo | 
| -------- |
| ![ezgif com-video-to-gif-converted](https://github.com/fxzz/sns/assets/3148006/a89901e6-effe-4edc-b6af-07507991ba83)  | 


<br>

### [Photo - 공유하기]
- 제목과 내용을 쓰고 이미지를 선택하면 일상을 공유합니다.
- 유효성 검사 : 제목과 내용 이미지는 필수, 일부 특수문자 사용 불가
- 자동화 봇 방지를 위해 reCAPTCHA v2 사용

| 공유하기 - 성공 | 
| -------- |
| ![d](https://github.com/fxzz/sns/assets/3148006/aee9e713-10cb-4cae-b68c-fe2c3ae473ba)  | 

<br>

| 공유하기 - 검증 실패 | 
| -------- |
| ![ezgif com-video-to-gif-converted](https://github.com/fxzz/sns/assets/3148006/e8b3745f-a328-4e4a-bdb0-34e0ed84bae1)  | 


