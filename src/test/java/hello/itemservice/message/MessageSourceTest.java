package hello.itemservice.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    //자동 의존 주입, 기본으로 있는 messageproperties불러드림
    @Autowired
    MessageSource ms;

    @Test
    void helloMessage() {
        //코드이름, 파라미터, 로케일정보
        //locale 정보가 없으면 basename 에서 설정한 기본 이름 메시지 파일을 조회,
        //basename으로 messages를 지정 했으므로 messages.properties 파일에서 데이터 조회, locale.Korea로 해도됨
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    //메시지코드를 못찾으면
    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    //기본 메시지 주는
    @Test
    void notFoundMessageCodeDefaultMessage() {
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    // 매개변수 사용, 값을 넘겨서 치환해야 하는
    @Test
    void argumentMessage() {
        String result = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(result).isEqualTo("안녕 Spring");
    }

    //국제화 파일 선택1
    //Locale이 en_US 의 경우 messages_en_US messages_en messages 순서로 찾는다.
    //Locale 에 맞추어 구체적인 것이 있으면 구체적인 것을 찾고, 없으면 디폴트
    @Test
    void defaultLang() {
        //locale 정보가 없으면 basename 에서 설정한 기본 이름 메시지 파일을 조회,
        //basename으로 messages를 지정 했으므로 messages.properties 파일에서 데이터 조회, locale.Korea로 해도됨
        /**
         * 예시, th:text="#{page.addItem}" 이렇게 하면
         * ms.getMessage("hello", null, Locale.KOREA) 이렇게 호출해서
         * 어딘가에 보관되어 있는 로케일 정보를 사용해서 해당하는 값을 반환하는
         * */
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        //, message_ko 안만들었으니 기본으로
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    //국제화 파일 선택2
    @Test
    void enLang() {
        //기본이 아닌 영어로
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }
}