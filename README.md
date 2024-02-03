<H1>📽️ 프로젝트</H1>
<b>Searcher</b>앱은 Retrofit과 카카오API를 이용해 이미지를 검색하는 앱입니다.<br> 
SharedPreference를 이용해 마지막 검색어를 검색창에 띄워주었으며, 
Viewmodel을 이용해 View에서는 오로지 UI를 보여주는 코드로 구성했고, UI처리나 비즈니스 로직은 ViewModel로 처리해 MVVM을 구현했습니다.<br> 
또한 data, domain, presentation 프로젝트를 나누어 CleanArchitecture를 구현했습니다.


      
<H1>🎯 기술 스택</H1>

|제목|내용|
|------|---|
|Language|Kotlin|
|jetpack|AAC ViewModel, LiveData, ViewBinding|
|network|OkHttp3, Retrofit2, Coroutine|
|data|SharedPreference|
|ImageLoading|Glide|

<H1> 🎄 Architecture</H1>
- `MVVM` `CleanArchitecture`

1. 키 값을 local.properties에 숨겨뒀습니다.
   Secrets Gradle 플러그인 사용 -> (https://developers.google.com/maps/documentation/android-sdk/secrets-gradle-plugin?hl=ko)

   ![image](https://github.com/choco5732/Personal_Project_Retrofit/assets/81561579/c0b15bb2-3589-4158-81cb-a97a8976ddad)

   혹시 다른 컴퓨터에서 사용이 안된다면 인터페이스 코드에 키값을 주석으로 적었는데 그걸로 수정하면 앱이 정상적으로 실행될 것입니다.
   (주석은 튜터님 평가가 끝나면 삭제할 예정입니다!)

2. 검색버튼을 눌러도 리사이클러뷰에 반응이 없다면
   EditText를 한번 클릭하고 키보드가 생기면 키보드를 내리면 정상적으로 리사이클러뷰에 이미지가 출력됩니다.
   조만간 원인을 파악해 수정토록 하겠습니다!

   ![image](https://github.com/choco5732/Personal_Project_Retrofit/assets/81561579/90966f87-f40a-4dcd-a612-ecdb5e5e267f)


# 작동 사진
1. 검색어를 입력하고 검색하면 리사이클러뷰에 그리드 형식으로 이미지들이 출력됩니다.
   이미지를 클릭하면 체크 표시가 되고 라이브러리에 추가됩니다.
   
![image](https://github.com/choco5732/Personal_Project_Retrofit/assets/81561579/a73df7f9-a98d-49b4-9273-de0d9a538899)


3. 라이브러리에 사진이 추가되었고 클릭 시 라이브러리에서 사진이 삭제됩니다.
   
![image](https://github.com/choco5732/Personal_Project_Retrofit/assets/81561579/cdffdcf4-47b9-44b0-9692-54ffa7222bac)

![image](https://github.com/choco5732/Personal_Project_Retrofit/assets/81561579/3e5055a1-923f-4ab6-a88e-4575ad55d8c7)
