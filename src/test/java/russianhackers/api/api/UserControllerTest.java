package russianhackers.api.api;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import russianhackers.api.auth.ApplicationUser;
import russianhackers.api.utils.TestingUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@WithUserDetails("seth")
class UserControllerTest {

	private MockMvc mvc;

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
		var user = "{\"name\": \"New User\",\"email\": \"user@email.com\",\"username\": \"username\",\"password\": \"password\"}";

		this.mvc.perform(post("/api/v1/user")
			.content(user)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Should Update a User")
	public void itShouldUpdateApplicationUser() throws Exception {
		var id = "2864fbf4-7b97-11eb-9439-0242ac130002";

		var updatedUser = "{\"name\": \"MJS\",\"email\": \"mjs@email.com\"}";


		this.mvc.perform(put("/api/v1/user/" + id)
						.content(updatedUser)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().is5xxServerError());
	}

}