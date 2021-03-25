package russianhackers.api.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import russianhackers.api.auth.ApplicationUser;
import russianhackers.api.security.ApplicationUserRole;
import russianhackers.api.utils.TestingUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@WithUserDetails("seth")
class UserControllerTest {

	private MockMvc mvc;
	private WebApplicationContext context;

	@Autowired
	public UserControllerTest(WebApplicationContext context) {
		this.mvc = MockMvcBuilders
						.webAppContextSetup(context)
						.apply(springSecurity())
						.build();
	}

	@Test
	@DisplayName("Should Add a New User")
	public void itShouldAddApplicationUser() throws Exception {
//		var testUser = new ApplicationUser(
//		null,
//		"test user",
//		"test",
//		"test_password",
//		"ADMIN",
//		"test@user.com",
//		true,
//		true,
//		true,
//		true
//		);
//
//		this.mvc.perform(post("/api/v1/user")
////			.content(TestingUtils.asJsonString(testUser))
//			.contentType(MediaType.APPLICATION_JSON)
//			.accept(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.user_id").exists())
//			.andExpect(jsonPath("$.name").value(testUser.getName())
//		);

		//COMMENT TO DELETE LATER
	}
}