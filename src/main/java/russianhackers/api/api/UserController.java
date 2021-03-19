package russianhackers.api.api;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
	public ApplicationUser addApplicationUser(@Valid @NonNull @RequestBody ApplicationUser applicationUser) {
		return applicationUserService.addApplicationUser(applicationUser);
	}

	@PutMapping(path = "{user_id}")
	public ApplicationUser updateUserById(@PathVariable("user_id") UUID user_id, @Valid @NonNull @RequestBody ApplicationUser userToUpdate) {
		applicationUserService.updateApplicationUser(user_id, userToUpdate);
		return applicationUserService.getUserById(user_id);
	}


}
