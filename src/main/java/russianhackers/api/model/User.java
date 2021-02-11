package russianhackers.api.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private final UUID user_id;

	@NotBlank
	private final String name;
	private final String email;

	public User(@JsonProperty("user_id") UUID user_id,
				@JsonProperty("name") String name,
				@JsonProperty("email") String email) {
		this.user_id = user_id;
		this.name = name;
		this.email = email;
	}

	public UUID getId() {
		return user_id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
}
