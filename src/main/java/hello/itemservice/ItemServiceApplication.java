package hello.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	//스프링부트를 쓰면 이게 없어도됨 MessageSource 자동등록
	//MessageSource는 인터페이스, 코드를 포함한 일부 파라미터로 메시지를 읽어오는 기능
//	MessageSource 를 스프링 빈으로 등록하지 않고, 스프링 부트와 관련된 별도의 설정을 하지 않으면
//	messages 라는 이름으로 기본 등록된다. 따라서 messages_en.properties ,
//	messages_ko.properties , messages.properties 파일만 등록하면 자동으로 인식
	//개발자가 등록한 messages, errors파일 읽어서 가지고있음
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		//basenames : 설정 파일의 이름을 지정, messages 로 지정하면 messages.properties 파일을 읽어서 사용
		//errors.properties도 읽어드림
		//추가로 국제화 기능을 적용하려면 messages_en.properties , messages_ko.properties 와 같이파일명 마지막에 언어 정보를 주면된다.
		//만약 찾을 수 있는 국제화 파일이 없으면 messages.properties (언어정보가 없는 파일명)를 기본으로 사용한
		//여러 파일을 한번에 지정할 수 있다. 여기서는 messages , errors 둘을 지정
		messageSource.setBasenames("messages", "errors");
		//defaultEncoding : 인코딩 정보를 지정
		messageSource.setDefaultEncoding("utf-8");

		return messageSource;
	}
}