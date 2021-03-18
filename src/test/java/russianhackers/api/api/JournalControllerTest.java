package russianhackers.api.api;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import russianhackers.api.jwt.JwtConfig;
import russianhackers.api.model.Journal;
import russianhackers.api.security.ApplicationSecurityConfig;
import russianhackers.api.utils.TestingUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@WithUserDetails("seth")
class JournalControllerTest {

	private MockMvc mvc;
//	@Autowired
	private WebApplicationContext context;

	@Autowired
	public JournalControllerTest(WebApplicationContext context) {
		this.mvc = MockMvcBuilders
						.webAppContextSetup(context)
						.apply(springSecurity())
						.build();
	}

	@Test
	@DisplayName("Should List All Journals when making GET request to /api/v1/journal")
	public void shouldListAllJournals() throws Exception {

		this.mvc.perform(get("/api/v1/journal")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("drew")
	@DisplayName("Should Fail List All Journals Because User Has No ADMIN Role")
	public void shouldNotListAllJournals() throws Exception {

		this.mvc.perform(get("/api/v1/journal")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isForbidden());
	}

	@Test
	@DisplayName("Should Add a New Journal")
	public void shouldCreateNewJournal() throws Exception {
		var testJournal = new Journal(
						null,
						UUID.fromString("8046aac5-1025-41ef-a7b8-a3ba3f266c8d"),
						"Test Journal Title",
						null,
						null
		);

		this.mvc.perform(post("/api/v1/journal")
			.content(TestingUtils.asJsonString(testJournal))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
//				.andExpect(jsonPath("$.journal_id").exists())
//				.andExpect(jsonPath("$.journal_name").value(testJournal.getJournal_name())
//		);
	}

	@Test
	@DisplayName("Should Get a Journal by ID")
	public void shouldGetJournalById() throws Exception {
		String id = "7a4b41bb-6824-4404-9beb-ab2ba10a978b";

		this.mvc.perform(get("/api/v1/journal/" + id)
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk()
		);
	}
}
