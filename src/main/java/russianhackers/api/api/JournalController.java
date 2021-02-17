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
import russianhackers.api.service.JournalService;

@RequestMapping("api/v1/journal")
@RestController
public class JournalController {

	private final JournalService journalService;

	@Autowired
	public JournalController(JournalService journalService) {
		this.journalService = journalService;
	}

	@PostMapping
	public void addJournal(@Valid @NonNull @RequestBody Journal journal) {
		journalService.addJournal(journal);
	}

	@GetMapping
	public List<Journal> getAllJournals() {
		return journalService.getAllJournals();
	}

	@GetMapping(path = "{journal_id}" )
	public Journal getJournalById(@PathVariable("journal_id") UUID journal_id) {
		return journalService.getJournalById(journal_id)
						.orElse(null);
	}

	@DeleteMapping(path="{journal_id}")
	public void deleteJournalById(@PathVariable("journal_id") UUID journal_id) {
		journalService.deleteJournal(journal_id);
	}

	@PutMapping(path = "{journal_id}")
	public Journal updateJournalById(@PathVariable("journal_id") UUID journal_id, @Valid @NonNull @RequestBody Journal journalToUpdate) {
		journalService.updateJournal(journal_id, journalToUpdate);
		return journalService.getJournalById(journal_id)
						.orElse(null);
	}


}
