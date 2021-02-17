package russianhackers.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import russianhackers.api.dao.JournalDao;
import russianhackers.api.model.Journal;

@Service
public class JournalService {

	private final JournalDao journalDao;

	@Autowired
	public JournalService(@Qualifier("postgres-journal") JournalDao journalDao) {
		this.journalDao = journalDao;
	}

	public int addJournal(Journal journal) {
		return journalDao.insertJournal(journal);
	}

	public List<Journal> getAllJournals() {
		return journalDao.selectAllJournals();
	}

	public Optional<Journal> getJournalById(UUID journal_id) {
		return journalDao.selectJournalById(journal_id);
	}

	public int deleteJournal(UUID journal_id) {
		return journalDao.deleteJournalById(journal_id);
	}

	public int updateJournal(UUID journal_id, Journal newJournal) {
		return journalDao.updateJournalById(journal_id, newJournal);
	}

}
