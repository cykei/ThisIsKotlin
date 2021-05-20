## 1. 에뮬레이터 설정
* avd os는 recommand를 설치하지 말고 x86_64를 설치하자. 권장설치인 x86보다 가볍고 좋다. 
* 왠만하면 해상도가 가장 낮은 기기를 선택하자 (Nexus 4 또는 Nexus 5). 낮은 해상도 일수록 avd가 cpu에 주는 부하가 낮다.

## 2. 코틀린 익스텐션과 뷰바인딩
* 2021년 9월 이후부터 코틀린 익스텐션이 서서히 사라져나간다.
  * 관련 내용 : https://android-developers.googleblog.com/2020/11/the-future-of-kotlin-android-extensions.html![image](https://user-images.githubusercontent.com/17683390/118988902-8f0e4780-b9bc-11eb-8c6a-853b012f38b7.png)
* 코틀린 익스텐션의 단점
  * 코틀린에서만 제공해서 java랑 호환이 안된다.
	* 일부상황에서 뷰를 찾을 수 없는 오류가 발생한다.
	* layout마다 같은 이름의 뷰 id를 쓴다면 다른 layout의 뷰가 참조될 수 있다. 
  * 코틀린익스텐션을 사용한 경우 타 모듈에서 뷰에대한 접근이 불가능하다.
* 코틀린 익스텐션의 대체안으로 "뷰바인딩" 기법이 나왔다. 이것을 사용하도록 하자. 

## 3. 뷰바인딩
* /SayHello를 참조
* 동작 원리
  * 앱 실행시 모든 layout_xml 파일을 객체화 환다.
  * .kt 에서 메모리에 적재된 xml 객체를 가져다 쓰기만 하면된다. 
  * xml 객체가 모두 들어가 있는 AcitivtyMainBinding 클래스가 제공되기 때문에 이 클래스를 이용하면 xml을 가져올 수 있다.
  
### 1) 뷰바인딩을 사용하고 싶다면 build.gradle 에 다음을 추가한다.
``` java
  android { 
      ...
      buildFeatures{
          viewBinding true
      }
 }
```

### 2) MainActivity 에서 ActivityMainBinding 객체를 선언해서 사용한다.

``` java
  class MainActivity:AppCompatActivity(){  
      override fun onCreate(savedInstanceState: Bundle?) {  
          super.onCreate(savedInstanceState)  
          val binding = ActivityMainBinding.inflate(layoutInflater)  
          setContentView(binding.root)  
          
          binding.btnSay.setOnClickListener {  
              binding.textSay.text = "Hello Kotlin!"  
          }  
      }  
  }  
 
```
