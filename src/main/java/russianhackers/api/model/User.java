package russianhackers.api.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private final UUID id;

	@NotBlank
	private final String name;
	private final String email;

	public User(@JsonProperty("id") UUID id,
				@JsonProperty("name") String name,
				@JsonProperty("email") String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
}
