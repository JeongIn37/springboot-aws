package example.org.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //스프링 부트의 자동 설정, Bean 읽기, 생성을 모두 자동 설정. 항상 프로젝트 최상단에 위치해야 함
public class Application { //프로젝트의 메인 클래스
    public static void main(String[] args){
        SpringApplication.run(Application.class, args); //내장 WAS 실행 (톰캣 설치 필요 없어짐)
    }
}

