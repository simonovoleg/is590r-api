package russianhackers.api.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
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

	@PostMapping
	@PreAuthorize("hasAuthority('record:write')")
	public void addRecord(@Valid @NonNull @RequestBody Record record) {
		final UUID journal_id = record.getJournal_id();
		recordService.addRecord(journal_id, record);
	}

	@GetMapping(path = "{record_id}" )
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
	public Record getRecordById(@PathVariable("record_id") UUID record_id) {
		return recordService.getRecordById(record_id)
						.orElse(null);
	}

	@GetMapping(path = "/journal/{journal_id}" )
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
	public List<Record> getRecordsByJournalId(@PathVariable("journal_id") UUID journal_id) {
		return recordService.getRecordsByJournalId(journal_id);
	}

	@DeleteMapping(path="{record_id}")
	@PreAuthorize("hasAuthority('record:write')")
	public void deleteRecordById(@PathVariable("record_id") UUID record_id) {
		recordService.deleteRecord(record_id);
	}

	@PutMapping(path = "{record_id}")
	@PreAuthorize("hasAuthority('record:write')")
	public Record updateRecordById(@PathVariable("record_id") UUID record_id, @Valid @NonNull @RequestBody Record recordToUpdate) {
		recordService.updateRecord(record_id, recordToUpdate);
		return recordService.getRecordById(record_id)
						.orElse(null);
	}
}
