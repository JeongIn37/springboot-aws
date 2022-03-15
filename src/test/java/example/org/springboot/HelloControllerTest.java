package example.org.springboot;
import example.org.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class) //테스트 진행시 JUnit에 내장된 실행자 외 다른 실행자(SpringRunner) 실행. 스프링 부트 테스트와 JUnit 사이에 연결자 역할
@WebMvcTest(controllers = HelloController.class) //여러 스프링 테스트 어노테이션 중 Web에 집중할 수 있는 어노테이션. 컨트롤러만 사용하기 때문에 선언
public class HelloControllerTest {
    @Autowired //스프링이 관리하는 빈(Bean)을 주입 받음
    private MockMvc mvc; //웹API를 테스트할 때 사용. 스프링MVC 테스트의 시작점. HTTP GET, POST 등 API 테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk())  //mvc.perform의 결과 검증. HTTP Header의 Status 검증. 200, 404, 500 등 상태 검증
                .andExpect(content().string(hello)); //mvc.perform의 결과 검증. 응답 본문의 내용 검증. 컨트롤러에서 리턴하는 "hello" 값이 맞는지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
