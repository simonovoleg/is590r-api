package russianhackers.api.model;

import java.sql.Timestamp;
import java.util.UUID;

public class Record {

	private final UUID record_id;
	private final UUID journal_id;
	//	private final UUID user_id; //should this be included?
	private final String record_title;
	private final String content;
	private final Timestamp createdAt;
	private final Timestamp updatedAt;

	public Record(UUID record_id, UUID journal_id, String record_title, String content, Timestamp createdAt, Timestamp updatedAt) {
		this.record_id = record_id;
		this.journal_id = journal_id;
		this.record_title = record_title;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UUID getRecord_id() {
		return record_id;
	}

	public UUID getJournal_id() {
		return journal_id;
	}

	public String getRecord_title() {
		return record_title;
	}

	public String getContent() {
		return content;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

}
