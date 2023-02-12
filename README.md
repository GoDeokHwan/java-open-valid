# java-open-valid

Java Open Valid 라이브러리 

# @PhoneNumber
전화번호 검사
( ex > 01012341234, 024321234, 15442222, 010-2134-1234, 02-123-1234, 1544-2222) 와 같은 형태로 들어오면 통과

### 허용 하는 Param

- message : 패턴에 맞지 않으면 알려줄 메시지

# @Password
비밀번호 검사 ( ex > AS123cbx#@$)

### 허용 하는 Param

- message : 패턴에 맞지 않으면 알려줄 메시지
- capitalLetter : 대문자를 필수 포함 여부 (default : true)
- smallLetter : 소문자를 필수 포함 여부 (default : true)
- specialSymbol : 특수 문자를 필수 포함 여부 (default : true)
- number : 숫자를 필수 포함 여부 (default : true)