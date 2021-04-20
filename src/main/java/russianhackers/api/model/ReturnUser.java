package russianhackers.api.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnUser {
	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	private final UUID id;
	private final String name;
	private final String email;
	private final String username;

	public ReturnUser(
					@JsonProperty("user_id") UUID id,
					@JsonProperty("name") String name,
					@JsonProperty("email") String email,
					@JsonProperty("username") String username) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
	}
}
