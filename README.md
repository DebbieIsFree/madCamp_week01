# **MADCAMP_1주차**
> **장지원, 김찬우**


### **Enviorment**
- **Language**: Kotlin
- 

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
- recycleview의 grid layout을 사용하여 커스터마이징하였다.


### **TAB 3: Map**