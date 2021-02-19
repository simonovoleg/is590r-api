package russianhackers.api.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import russianhackers.api.model.Journal;

public interface JournalDao {

	int insertJournal (UUID journal_id, Journal journal);

	default int insertJournal (Journal journal) {
		UUID journal_id = UUID.randomUUID();
		return insertJournal (journal_id, journal);
	}

	List<Journal> selectAllJournals();

	Optional<Journal> selectJournalById(UUID journal_id);

	List<Journal> selectJournalByUserId(UUID user_id);

	int deleteJournalById(UUID journal_id);

	int updateJournalById(UUID journal_id, Journal journal);
}
