<H1>📽️ 프로젝트</H1>
<b>Searcher앱은 Retrofit과 카카오API를 이용한 이미지 검색 앱 입니다</b>



      
<H1>🎯 기술 스택</H1>

|제목|내용|
|------|---|
|Language|Kotlin|
|jetpack|AAC ViewModel, LiveData, ViewBinding|
|network|Retrofit2, OkHttp3, Coroutine|
|data|SharedPreference|
|ImageLoading|Glide|

<H1> 🎄 Architecture</H1>
- CleanArchitecture, MVVM을 완벽히 구현했습니다.
- Data, Domain, Presentation으로 패키지를 나누어 개발했습니다.
![image](https://github.com/choco5732/Searcher/assets/81561579/de15e16d-b2e6-4579-b6a3-28ba643d8eff)

- 모든 비즈니스 로직은 viewmodel로 처리했습니다. 
  ex) usecase와 entity, repository를 만들어 retrofit 통
- 간단한 Toast 메시지 출력조차 ViewModel을 이용해 처리했습니다.
  View에서는 오로지 ViewModel로부터 받는 데이터를 가지고 뷰 코드로만 구성했습니다.

# 🎯 작동 영상
![Screen_recording_20240204_072326 (1)](https://github.com/choco5732/Searcher/assets/81561579/0dea41ce-5d3b-4aec-92f0-298946cfa19a)



