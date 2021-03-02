package russianhackers.api.auth;

import russianhackers.api.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApplicationUserDAO {

    int insertApplicationUser (UUID user_id, ApplicationUser applicationUser);

    default int insertApplicationUser (ApplicationUser applicationUser) {
        UUID user_id = UUID.randomUUID();
        return insertApplicationUser (user_id, applicationUser);
    }

    List<ApplicationUser> selectAllPeople();

    Optional<ApplicationUser> selectApplicationUserById(UUID user_id);

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);

    int deleteApplicationUserById(UUID user_id);

    int updateApplicationUserById(UUID user_id, ApplicationUser user);

}
