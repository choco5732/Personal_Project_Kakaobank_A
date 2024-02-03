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
- CleanArchitecture, MVVM을 100% 구현했습니다. <br><br>
- Data, Domain, Presentation으로 패키지를 나누어 개발했습니다.<br><br>
- 모든 비즈니스 로직은 viewmodel로 처리했습니다. <br><br>
  ex) presentation(viewModel) -> domain(usecase) -> data(repository) <br><br>
- 간단한 Toast 메시지 출력조차 ViewModel을 이용해 처리했습니다.<br>
    View에서는 오로지 ViewModel로부터 받는 데이터로 뷰만 담당하게 구성했습니다.

# 🎯 작동 영상
![Screen_recording_20240204_072326 (1)](https://github.com/choco5732/Searcher/assets/81561579/0dea41ce-5d3b-4aec-92f0-298946cfa19a)

<H1> 💡 배운 점</H1>
- Retrofit2와 Okhttp3를 통해 안드로이드에서 HTTP통신을 구현해볼 수 있었습니다.
- MVVM을 패턴을 도입해, 기존의 MVC 아키텍처의 문제점을 개선시켜 볼 수 있었습니다.
- 단순 몇가지 기능만 ViewModel로 구현하는게 아닌 CleanArchitecture를 기반으로 단순 UI처리부터 모든 비즈니스 로직을 ViewModel로 처리해 MVVM을 완벽히 구현해볼 수 있었습니다.



