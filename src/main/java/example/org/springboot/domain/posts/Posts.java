package example.org.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter //lombok의 어노테이션. 클래스 내 모https://yg-entertainment.recruiter.co.kr/app/jobnotice/view?systemKindCode=MRS2&jobnoticeSn=91337든 필드의 Getter 메소드 자동생성
@NoArgsConstructor //lombok의 어노테이션. 기본 생성자 자동 추가 public Posts() {}와 같은 효과
@Entity //JPA의 어노테이션. 테이블과 링크될 클래스임. 카멜케이스 이름(클래스)을 언더스코어 네이밍(테이블)으로 매칭 (SalesManager.java -> sales_manager table)

//Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다!!

public class Posts { //DB 테이블과 매칭될 클래스(Entity 클래스)

    @Id //PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성 규칙 GenerationType.IDENTITY 옵션을 추가해야 auto_increment 가능 (스프링 부트 2.0)
    private Long id;

    @Column(length = 500, nullable = false) //테이블의 칼럼 (굳이 선언하지 않아도 클래스의 필드는 모두 칼럼. 기본값 외에 추가 변경이 필요한 옵션이 있을 경우를 위해 사용)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}