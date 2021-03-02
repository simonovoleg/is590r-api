package russianhackers.api.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import russianhackers.api.auth.ApplicationUser;
import russianhackers.api.auth.ApplicationUserService;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

	private final ApplicationUserService applicationUserService;

	@Autowired
	public UserController(ApplicationUserService applicationUserService) {
		this.applicationUserService = applicationUserService;
	}

	@PostMapping
	public void addUser(@Valid @NonNull @RequestBody ApplicationUser applicationUser) {
		applicationUserService.addApplicationUser(applicationUser);
	}

	@GetMapping
	public List<ApplicationUser> getAllPeople() {
		return applicationUserService.getAllPeople();
	}

	@GetMapping(path = "{user_id}" )
	public ApplicationUser getUserById(@PathVariable("user_id") UUID user_id) {
		return applicationUserService.getUserById(user_id)
						.orElse(null);
	}

	@DeleteMapping(path="{user_id}")
	public void deleteUserById(@PathVariable("user_id") UUID user_id) {
		applicationUserService.deleteApplicationUser(user_id);
	}

	@PutMapping(path = "{user_id}")
	public ApplicationUser updateUserById(@PathVariable("user_id") UUID user_id, @Valid @NonNull @RequestBody ApplicationUser userToUpdate) {
		applicationUserService.updateApplicationUser(user_id, userToUpdate);
		return applicationUserService.getUserById(user_id)
						.orElse(null);
	}


}
