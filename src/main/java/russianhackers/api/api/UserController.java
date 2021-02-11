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

import russianhackers.api.model.User;
import russianhackers.api.service.UserService;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public void addUser(@Valid @NonNull @RequestBody User user) {
		userService.addUser(user);
	}

	@GetMapping
	public List<User> getAllPeople() {
		return userService.getAllPeople();
	}

	@GetMapping(path = "{user_id}" )
	public User getUserById(@PathVariable("user_id") UUID user_id) {
		return userService.getUserById(user_id)
						.orElse(null);
	}

	@DeleteMapping(path="{user_id}")
	public void deleteUserById(@PathVariable("user_id") UUID user_id) {
		userService.deleteUser(user_id);
	}

	@PutMapping(path = "{user_id}")
	public User updateUserById(@PathVariable("user_id") UUID user_id, @Valid @NonNull @RequestBody User userToUpdate) {
		userService.updateUser(user_id, userToUpdate);
		return userService.getUserById(user_id)
						.orElse(null);
	}


}
