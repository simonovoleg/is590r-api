package russianhackers.api.auth;

import russianhackers.api.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.core.ApplicationContext;

public interface ApplicationUserDAO {

    ApplicationUser insertApplicationUser(UUID user_id, ApplicationUser applicationUser);

    default ApplicationUser insertApplicationUser (ApplicationUser applicationUser) {
        UUID user_id = UUID.randomUUID();
        return insertApplicationUser (user_id, applicationUser);
    }

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);

    ApplicationUser updateApplicationUser(UUID user_id, ApplicationUser updatedUser);

    ApplicationUser getUserById(UUID user_id);
}
