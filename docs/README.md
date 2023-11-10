# 구현할 기능 목록

## 도메인 기능

### 계획

#### 주문
- 주문 검증 기능
    - 메뉴당 주문 개수가 0 또는 음수인 경우 예외를 던진다
       - 에러 메시지 : "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
    - 총 주문 개수가 정상 범위(1개 이상 20개 이하)를 벗어난 경우, 예외를 던진다
    - 음료로만 구성되어 있을 경우, 예외를 던진다
- 주문한 총 디저트 개수를 세는 기능
- 주문한 총 메인 메뉴를 세는 기능

#### 메뉴
- 각 메뉴의 가격을 알려주는 기능
- 각 메뉴가 '디저트'인지 확인하는 기능
- 각 메뉴가 '메인'인지 확인하는 기능
- 각 메뉴가 '음료'인지 확인하는 기능

#### 날짜
- 날짜 검증 기능
    - 날짜가 정상 범위(1 이상 31 이하)를 벗어난 경우, 예외를 던진다 
      - 에러 출력 메시지 : "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
- 해당 날짜가 주말(금요일 ~ 토요일)인지 확인하는 기능 구현
- 해당 날짜가 평일(일요일 ~ 목요일)인지 확인하는 기능 구현

### 할인

#### 할인 적용 기준
- 총 주문 금액이 10,000원 이상인지 확인하는 기능

#### 크리스마스 디데이 할인
- 주문 일짜가 1일 ~ 25일 사이인지 확인하는 기능
- 주문 일에 따라 할인 금액을 반환하는 기능 (1일 1,000원, 25일까지 하루마다 100원씩 증가)

#### 평일 할인
- 주문 일짜가 평일인지 확인하는 기능
- 디저트 메뉴 개수에 따라 할인 금액을 반환하는 기능 구현 (디저트 메뉴 1개 당 2,023원)

#### 주말 할인
- 주문 일짜가 주말인지 확인하는 기능 구현
- 메인 메뉴 개수에 따라 할인하는 기능 구현 (메인 메뉴 1개 당 2,023원)

#### 특별 할인
- 주문 일짜가 3, 10, 17, 24, 25, 31 중 하나인지 확인하는 기능 구현
- 할인 금액 (1,000원)을 반환하는 기능 구현

### 증정
- 총 주문 금액이 120,000원이 넘는지 확인하는 기능 구현
- 증점품(샴페인 1개)를 반환하는 기능

### 배지
- 총 혜택 금액에 따라 적절한 배지를 주는 기능 구현

## 입출력 기능

#### 입력 기능

- 식당 방문 날짜 입력 기능
  - 숫자(정수)를 입력하지 않을 경우 예외를 던진다 
    - 에러 메시지 : "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."

- 주문할 메뉴 & 메뉴 개수 입력 기능
  - 메뉴판에 없는 메뉴를 입력할 경우 예외를 던진다
    - 에러 메시지 : "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
  - 중복 메뉴를 입력한 경우 예외를 던진다
    - 에러 메시지 : "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
  - 메뉴 형식이 예시와 다른 경우 예외를 던진다
    - 에러 메시지 : "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."

#### 출력 기능

- 주문 메뉴 출력 기능
   - 주문 메뉴의 출력 순서 제한은 없다
- 할인 전 총주문 금액 출력 기능
- 증정 메뉴 출력 기능
   - 증정 메뉴가 없을 경우, "없음"으로 출력 
- 혜택 내역 출력 기능
   - 적용된 이벤트 내역만 보여주어야 한다
   - 적용된 이벤트 내역이 하나도 없을 경우, "없음"으로 출력
   - 혜탣 내역에 여러 개의 이벤트가 적용된 경우, 출력 순서는 자유롭게 한다
- 총혜택 금액 출력 기능
- 할인 후 예상 결제 금액 출력 기능
- 12월 이벤트 배지 출력 기능
   - 이벤트 배지가 부여되지 않은 경우, "없음"으로 출력
- 에러 메시지 출력 기능

## 제어 로직
- 전반적인 '12월 이벤트 플래너' 실행 로직 작성