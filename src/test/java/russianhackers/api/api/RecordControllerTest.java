package russianhackers.api.api;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
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
import russianhackers.api.model.Record;
import russianhackers.api.utils.TestingUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@WithUserDetails("seth")
class RecordControllerTest {

	private MockMvc mvc;
	private WebApplicationContext context;

	@Autowired
	public RecordControllerTest(WebApplicationContext context) {
		this.mvc = MockMvcBuilders
						.webAppContextSetup(context)
						.apply(springSecurity())
						.build();
	}

//	@Test
//	@DisplayName("Should Add a New Record")
//	public void itShouldAddNewRecord() throws Exception {
//		var record = new Record(
//						UUID.randomUUID(),
//						UUID.fromString("7a4b41bb-6824-4404-9beb-ab2ba10a978b"),
//						"Test Record",
//						"Blah blah blah",
//						null,
//						null
//		);
//
//		this.mvc.perform(post("/api/v1/record")
//						.content(TestingUtils.asJsonString(record))
//						.contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON))
//						.andExpect(status().isOk());
//	}
//
//	//eeac4832-86b7-4436-aeb5-15baa9c3572e

	@Test
	@DisplayName("Should Add a New Record and Then Delete It")
	public void shouldCreateThenDeleteRecord() throws Exception {
		var record = new Record(
						UUID.fromString("8046aac5-1025-41ef-a7b8-a3ba3f266c8d"),
						UUID.fromString("7a4b41bb-6824-4404-9beb-ab2ba10a978b"),
						"Test Record",
						"Blah blah blah",
						null,
						null
		);

		this.mvc.perform(post("/api/v1/record")
						.content(TestingUtils.asJsonString(record))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());



		this.mvc.perform(delete("/api/v1/record/" + "8046aac5-1025-41ef-a7b8-a3ba3f266c8d"))
						.andExpect(status().isOk()
						);
	}

//	@Test
//	@DisplayName("Should Update a Record by ID")
//	public void shouldGetRecordById() throws Exception {
//		var id = "0c5d1a97-f77a-4206-87f6-f268038a992a";
//		var updatedRecord = new Record(
//						null,
//						null,
//						"Updated Test Record Title",
//						"updated content",
//						null,
//						null
//		);
//
//		this.mvc.perform(put("/api/v1/record/" + id)
//						.content(TestingUtils.asJsonString(updatedRecord))
//						.contentType(MediaType.APPLICATION_JSON))
//						.andExpect(status().isOk());
//	}


}