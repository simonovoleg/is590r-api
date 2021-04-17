package russianhackers.api.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Journal {
	private final UUID journal_id;
	private final UUID user_id;
	private final String journal_name;
	private final Timestamp createdAt;
	private final Timestamp updatedAt;

	public Journal( @JsonProperty("journal_id") UUID journal_id,
					@JsonProperty("user_id") UUID user_id,
					@JsonProperty("journal_name") String journal_name,
					@JsonProperty("createdAt") Timestamp createdAt,
					@JsonProperty("updatedAt") Timestamp updatedAt ) {
		this.journal_id = journal_id;
		this.user_id = user_id;
		this.journal_name = journal_name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UUID getJournal_id() {
		return journal_id;
	}

	public UUID getUser_id() {
		return user_id;
	}

	public String getJournal_name() {
		return journal_name;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
}
