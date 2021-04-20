package russianhackers.api.auth;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

import russianhackers.api.model.ReturnUser;

public interface ApplicationUserDAO {

    ApplicationUser insertApplicationUser(UUID user_id, ApplicationUser applicationUser);

    default ApplicationUser insertApplicationUser (ApplicationUser applicationUser) {
        UUID user_id = UUID.randomUUID();
        return insertApplicationUser (user_id, applicationUser);
    }

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);

    ApplicationUser updateApplicationUser(UUID user_id, ApplicationUser updatedUser);

    ApplicationUser getUserById(UUID user_id);

    ReturnUser getApplicationUser(Principal principal);
}
