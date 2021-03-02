package russianhackers.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class ApplicationUser {
    private final UUID user_id;

    @NotBlank
    private final String name;
    private final String email;

    public ApplicationUser(UUID user_id, @NotBlank String name, String email) {
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
