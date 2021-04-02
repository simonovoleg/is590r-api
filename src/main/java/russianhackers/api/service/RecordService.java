package russianhackers.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import russianhackers.api.dao.RecordDao;
import russianhackers.api.model.Journal;
import russianhackers.api.model.Record;

@Service
public class RecordService {

	private final RecordDao recordDao;

	@Autowired
	public RecordService(@Qualifier("postgres-record") RecordDao recordDao) {
		this.recordDao = recordDao;
	}

	public int addRecord(UUID journal_id, Record record) {
		return recordDao.insertRecord(journal_id, record);
	}

	public Optional<Record> getRecordById(UUID record_id) {
		return recordDao.selectRecordById(record_id);
	}

	public List<Record> getRecordsByJournalId(UUID journal_id) {
		return recordDao.selectRecordsByJournalId(journal_id);
	}

	public int deleteRecord(UUID record_id) {
		return recordDao.deleteRecordById(record_id);
	}

	public int updateRecord(UUID record_id, Record newRecord) {
		return recordDao.updateRecordById(record_id, newRecord);
	}

}
