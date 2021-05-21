# 1. 변수
* var 변수명(이름) = 값
  * 변수 선언과 동시에 값을 넣으면 자동으로 변수 타입이 결정된다.
* var 변수명 : 타입
  * 이런식으로 타입을 직접 지정할 수 도있다.
* $를 이용해서 변수값을 가져올 수 있다.
```kotlin
var name = "홍길동"
Log.d("BasicSyntax", "제 이름은 $name 입니다")
```
* val   
  * 읽기 전용 변수로, 한번 입력된 값은 추후 변경이 불가능하다.
* const val
  * val 과 같이 읽기전용인 것은 동일하지만, 런타임시에 할당되는 val과 달리 const val은 컴파일 시에 값이 결정되기 때문에 Int, Long 같은 기본 형과 String 형만 입력할 수 있다.  
  * 내가 만든 클래스를 할당할 수 없다는 의미.
  * const val 은 companion object에 넣어서 static final 처럼 쓸 수 있다. (그냥 const val 만 쓰면 클래스의 프로퍼티로 사용하지 못한다.)
  * 참고
    * https://kimch3617.tistory.com/entry/Kotlin%EC%97%90%EC%84%9C-val-%EC%99%80-const-val-%EC%9D%98-%EC%B0%A8%EC%9D%B4
    * https://www.bsidesoft.com/8187

# 2. when 문
* switch - case 와 동일하다.
```kotlin
when( 파라미터 (생략가능) ) {
  비교값 1 -> {
  
  }
  비교값 2 -> {
  
  }
  else -> {
  
  }
}
```

# 3. 배열
* 처음에 배열 크기를 정해서 생성한다. 추후 배열 크기 조절 불가능
```kotlin
var students = IntArray(10)
var stringArray = Array(10, {item => ""})
var dayArray = arrayOf("MON","TUE","WED","THU","FRI","SAT","SUN")
```
* 배열에 값 넣는 법
```kotlin
students[0]=90
students.set(1, 91) // (인덱스, 값)
```
* 배열에서 값 얻는 법
```kotlin
var score1 = students[0]
var score2 = students.get(1)
```

# 4. 컬렉션
* 처음에 크기를 고정하지 않고 생성가능 하다. 
* List, Map, Set을 지원한다.

## List
* 코틀린 리스트는 앞에 뮤터블이 붙는다. 접두어가 없는 리스트도 있지만 잘 사용하지 않는다.
```kotlin
var list = mutableListOf("Mon","Tue","Wed")
list.add("Thu")
list.set(1,"MON")
var value = list.get(1)
list.removeAt(1) // 첫번째값을 삭제하면 두번째값의 인덱스가 1이 된다.

var stringList = mutableListOf<String>() // 빈 리스트를 생성할때는 꼭 타입을 지정해줘야한다. 
var listSize = list.size
```

## Set
* 중복을 허용하지 않는 리스트
* 인덱스로 조회할 수 없고, get함수도 제공하지 않는다.
```kotlin
var set = mutableSetOf<String>()
set.add("hello")
set.remove("hello")
```

## Map
* 키, 값으로 생성가능
* 키 값은 중복을 허용하지 않는다.
```kotlin
var map = mutableMapOf<String, String>()
map.put("hello","me")
map.get("hello")
map.remove("hello")
```  
  
# 5. 이뮤터블 컬렉션
* 접두어 mutable을 빼고 만들면 이뮤터블 컬렉션이다.
* 처음 값을 입력 후, 추후 수정이 불가능한 컬렉션이다.
* add 나 set을 제공하지 않는다.
```kotlin
val immutableList = listOf("JAN","FEB", "MAR")
```  
  
# 6. for문
```kotlin
for (i in 1..10) {
  // [1, 10]
  Log.d("for", "현재 숫자는 ${i}")
}

for (i in 1 until 10) {
  //  [1, 10)
  Log.d("for", "현재 숫자는 ${i}")
}

for (i in 1..10 step 3) {
  // 1, 4, 7, 10
  Log.d("for", "현재 숫자는 ${i}")
} 

for (i in 10 downTo 0) {
  // 10 부터 0까지 감소
  Log.d("for", "현재 숫자는 ${i}")
} 

for ( i in list ) {
  // list 안의 값이 나온다.
  Log.d("for", "현재 리스트 값은 ${i}")  
}
```
  
# 7. 함수

```kotlin
fun 함수명(파라미터 이름 : 타입): 반환 타입 {
  return 값
}
```
  
* 함수 인자
  * 함수에 입력되는 파라미터는 마치 변수를 정의하듯이 '이름: 타입' 형태
  * 여러개의 파라미터가 정의될때는 콤마로 구분한다.
  * 코틀린에서 함수 파라미터로 입력되는 모든 값은 이뮤터블 값으로 앞에 val 가 생략된 형태라고 생각할 수 있다.
  * 등호를 이용해서 기본값을 설정할 수 있다. 
```kotlin
fun 함수명 ((val 생략) name1: String, name2: Int = 30, name3: Double) { 실행코드 }
```

* 함수를 사용할때 마치 파이썬 처럼 파라미터 이름을 직접 지정해서 사용할 수 있다.
```kotlin
fun newFunction(name: String, age: Int = 29, weight: Double = 65) {
}

new Function("cykei", weight = 48)
```
