# **MADCAMP_1주차**
> **장지원, 김찬우**


### **Enviorment**
- **Language**: Kotlin
- **program** : Android Studio VERSION 11 
- **API** : 33

### **Overview**
- Contact, Gallery, Map 기능을 가진 3개의 탭으로 구성된 안드로이드 어플리케이션이다.   
- 각각의 탭은 Fragment로 구현하였으며, tablayout을 이용하여 탭 간 전환을 구현하였다.

### **TAB 1: Contact**
- 휴대전화의 연락처와 연동하여 이름과 전화번호를 보여주는 탭이다.
- listview를 기반으로 adapter를 통해 customize하였다.
- 상단의 검색바는 이름을 기준으로 해당하는 연락처를 필터링해준다.
- 검색바는 searchview를 통해 구현하였다.

### **TAB 2: Gallery**
- 휴대전화의 갤러리와 연동하여 원하는 사진을 선택하여 가져오는 탭이다.
- 선택하는 사진의 개수에 상관없이 원하는 만큼 화면에 추가할 수 있다.
- recycleview의 grid layout을 사용하여 커스터마이징하였다.
- 화면에 추가된 사진을 누르면 해당 사진만 확대하여 보여주는 팝업 기능을 추가하였다.

### **TAB 3: Map**
- Naver Map API를 연동하여 지도를 보여준다.
- 현재 위치를 지도 상에 나타내고 마커로 표시하였다.
- 지도에서 새로운 위치를 클릭할 때마다 바뀌는 경도, 위도, 주소 값을 구하였고 이것을 마커와 토스트 메시지로 표시하였다.

### **UI** ###
- 애플리케이션 아이콘 변경
- 탭 레이아웃의 각 탭을 아이콘으로 변경
-  


### **Error Handling** ###
- 처음에는 Activity에서 3개의 Tab을 intent로 연결하여 구현했으나 Activity의 동작이 무거워서 애뮬레이터가 갑자기 중단되는 상황이 발생하였다. 그래서 각 탭을 Fragment로 바꿨다.

- 
