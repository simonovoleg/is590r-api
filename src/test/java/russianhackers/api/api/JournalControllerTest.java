package russianhackers.api.api;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import java.util.UUID;

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

import russianhackers.api.model.Journal;
import russianhackers.api.utils.TestingUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@WithUserDetails("seth")
class JournalControllerTest {

	private MockMvc mvc;
	private WebApplicationContext context;

	@Autowired
	public JournalControllerTest(WebApplicationContext context) {
		this.mvc = MockMvcBuilders
						.webAppContextSetup(context)
						.apply(springSecurity())
						.build();
	}

	@Test
	@DisplayName("Should List All User's Journals when making GET request to /api/v1/journal/user")
	public void shouldListAllJournals() throws Exception {

		this.mvc.perform(get("/api/v1/journal/user")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
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
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.journal_id").exists())
				.andExpect(jsonPath("$.journal_name").value(testJournal.getJournal_name())
		);
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

	@Test
	@DisplayName("Should Add a New Journal and Then Delete It")
	public void shouldCreateAndDeleteNewJournal() throws Exception {
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
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.journal_id").exists())
			.andExpect(jsonPath("$.journal_name").value(testJournal.getJournal_name())
		);

		this.mvc.perform(delete("/api/v1/journal/" + "8046aac5-1025-41ef-a7b8-a3ba3f266c8d"))
			.andExpect(status().isOk()
		);
	}

	@Test
	@DisplayName("Should Update Journal")
	public void shouldUpdateJournal() throws Exception {
		String id = "7a4b41bb-6824-4404-9beb-ab2ba10a978b";

		var updatedJournal = new Journal(
			null,
			null,
			"Updated Test Journal Title",
			null,
			null
		);

		this.mvc.perform(put("/api/v1/journal/" + id)
			.content(TestingUtils.asJsonString(updatedJournal))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.journal_id").exists())
			.andExpect(jsonPath("$.journal_name").value(updatedJournal.getJournal_name())
		);
	}

	@Test
	@DisplayName("Should Return 200 on Health Check")
	public void shouldHealthCheck() throws Exception {
		this.mvc.perform(get("/api/v1/journal/hc"))
						.andExpect(status().isOk());
	}
}
