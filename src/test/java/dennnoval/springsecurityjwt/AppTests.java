package dennnoval.springsecurityjwt;

import static org.assertj.core.api.Assertions.assertThat;

import dennnoval.springsecurityjwt.controller.AuthenticationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("/application.properties")
class AppTests {

	@Autowired
	private AuthenticationController authenticationController;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@Autowired
	private Environment env;

	@Test
	void controllerLoaded() {
		assertThat(authenticationController).isNotNull();
	}

	@Test
	void getPropertyValue() {
		assertThat(contextPath).isEqualTo("/api/v1");
	}

	@Test
	void getPropertyValueFromEnvironment() {
		assertThat(env.getProperty("server.servlet.context-path")).isEqualTo("/api/v1");
	}

}
