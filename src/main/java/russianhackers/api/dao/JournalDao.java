package russianhackers.api.dao;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import russianhackers.api.model.Journal;

public interface JournalDao {

	Journal insertJournal (UUID journal_id, Journal journal);

	default Journal insertJournal (Journal journal) {
		UUID journal_id = UUID.randomUUID();
		return insertJournal (journal_id, journal);
	}

	List<Journal> selectAllJournals();

	Optional<Journal> selectJournalById(UUID journal_id);

	List<Journal> selectJournalByUserId(UUID user_id, Principal principal);

	int deleteJournalById(UUID journal_id);

	Journal updateJournalById(UUID journal_id, Journal journal);
}
