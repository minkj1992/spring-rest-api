# Spring RESTful Api
> `Spring`을 활용한 RESTful(`Self-descriptive`, `HATEOAS`)한 API 생성

## 기술 스택
- `Spring boot`
- `Spring framework`
- `Spring data JPA`
- `Spring HATEOAS`
- `Spring REST Docs`
- `Spring Security OAuth2` 
- `TDD`

## 개발 목표

## 개발 과정

### 1. 개발환경 세팅
- `mvn package`
### 2. `Event` Domain 구현
- `Event` 클래스 생성
- Test
    - `@Builder`를 활용해서 테스트 케이스 생성
    ```java
        @Test
        public void builder() {
            Event event = Event.builder().build();
        }
    ```
    - `@EqualsAndHashCode(of="id")`
        - id 값만으로 EqualsAndHashCode를 비교하라 라는 의미
        - 필요하다면 다른 필드를 더 추가할 수도 있지만 다른 Entity와의 묶음을 만드는 것은 좋지 않음
        - Equals 와 HashCode를 구현할 때 모든 필드를 기본적으로 다 사용함
        - 나중에 Entity 간에 연관관계가 있을 때 **상호참조**하는 관계가 되면 `EqualsAndHashCode`를 구현한 코드 안에서 서로간의 메소드를 계속 호출하다가 스택오버플로우가 발생할 수도 있음

- `EqualsAndHashCode()`
```java
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Event)) {
            return false;
        } else {
            Event other = (Event)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label139: {
                    Object this$name = this.getName();
                    Object other$name = other.getName();
                    if (this$name == null) {
                        if (other$name == null) {
                            break label139;
                        }
                    } else if (this$name.equals(other$name)) {
                        break label139;
                    }

                    return false;
                }

                Object this$description = this.getDescription();
                Object other$description = other.getDescription();
                if (this$description == null) {
                    if (other$description != null) {
                        return false;
                    }
                } else if (!this$description.equals(other$description)) {
                    return false;
                }
                
                
                (중략 ....)
                
                
                    }
                }
            }
        }
    }
```
- 이처럼 모든 field에 대하여 Equals를 구현해버린다.
- `@EqualsAndHashCode(of="id")`
```java

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Event)) {
            return false;
        } else {
            Event other = (Event)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                return true;
            }
        }
    }

```
### 3. 이벤트 생성 API
- `import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest`
    