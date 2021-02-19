package russianhackers.api.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import russianhackers.api.model.Journal;
import russianhackers.api.model.Record;
import russianhackers.api.service.RecordService;

@RequestMapping("api/v1/record")
@RestController
public class RecordController {

	private final RecordService recordService;

	@Autowired
	public RecordController(RecordService recordService) {
		this.recordService = recordService;
	}

	@GetMapping(path="/all")
	public List<Record> getAllRecords() {
		return recordService.getAllRecords();
	}

	@PostMapping
//	public void addRecord(@Valid @NonNull @RequestBody UUID journal_id, @Valid @NonNull @RequestBody Record record) {
	public void addRecord(@Valid @NonNull @RequestBody Record record) {
		final UUID journal_id = record.getJournal_id();
		recordService.addRecord(journal_id, record);
	}

	@GetMapping(path = "/user/{user_id}" )
	public List<Record> getRecordsByUserId(@PathVariable("user_id") UUID user_id) {
		return recordService.getRecordsByUserId(user_id);
	}

	@GetMapping(path = "{record_id}" )
	public Record getRecordById(@PathVariable("record_id") UUID record_id) {
		return recordService.getRecordById(record_id)
						.orElse(null);
	}

	@GetMapping(path = "/journal/{journal_id}" )
	public List<Record> getRecordByJournalId(@PathVariable("journal_id") UUID journal_id) {
		return recordService.getRecordByJournalId(journal_id);
	}

	@DeleteMapping(path="{record_id}")
	public void deleteRecordById(@PathVariable("record_id") UUID record_id) {
		recordService.deleteRecord(record_id);
	}

	@PutMapping(path = "{record_id}")
	public Record updateRecordById(@PathVariable("record_id") UUID record_id, @Valid @NonNull @RequestBody Record recordToUpdate) {
		recordService.updateRecord(record_id, recordToUpdate);
		return recordService.getRecordById(record_id)
						.orElse(null);
	}


}
