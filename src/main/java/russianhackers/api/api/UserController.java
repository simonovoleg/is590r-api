package russianhackers.api.api;

import java.security.Principal;
import java.util.UUID;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import russianhackers.api.auth.ApplicationUser;
import russianhackers.api.auth.ApplicationUserService;
import russianhackers.api.model.ReturnUser;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

	private final ApplicationUserService applicationUserService;

	@Autowired
	public UserController(ApplicationUserService applicationUserService) {
		this.applicationUserService = applicationUserService;
	}

	@PostMapping
	public ApplicationUser addApplicationUser(@Valid @NonNull @RequestBody ApplicationUser applicationUser) {
		return applicationUserService.addApplicationUser(applicationUser);
	}

	@PutMapping(path = "{user_id}")
	public ApplicationUser updateUserById(@PathVariable("user_id") UUID user_id, @Valid @NonNull @RequestBody ApplicationUser userToUpdate) {
		applicationUserService.updateApplicationUser(user_id, userToUpdate);
		return applicationUserService.getUserById(user_id);
	}

	@GetMapping
	public ReturnUser getApplicationUser(Principal principal) {
		return applicationUserService.getApplicationUser(principal);
	}
}
