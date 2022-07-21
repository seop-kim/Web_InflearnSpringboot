package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    public void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean("memberService2", MemberService.class);  // 빈 이름을 직접 지정하여 이렇게 사용가능
        assertThat(memberService).isInstanceOf(MemberService.class);

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinition : beanDefinitionNames) {
            System.out.println("beanDefinition = " + beanDefinition);
        }
    }
}
