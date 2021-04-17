package russianhackers.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class ApplicationUser {
    private final UUID user_id;

    @NotBlank
    private final String name;
    private final String email;

    public ApplicationUser(
            @JsonProperty("user_id") UUID user_id,
            @JsonProperty("name") @NotBlank String name,
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
