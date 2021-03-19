package russianhackers.api.auth;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserDAO applicationUserDAO;

    @Autowired
    public ApplicationUserService(@Qualifier("postgres") ApplicationUserDAO applicationUserDAO) {
        this.applicationUserDAO = applicationUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDAO
                .selectApplicationUserByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    public ApplicationUser addApplicationUser(ApplicationUser applicationUser) {
        return applicationUserDAO.insertApplicationUser(applicationUser);
    }

    public ApplicationUser updateApplicationUser(UUID user_id, ApplicationUser updatedUser) { return applicationUserDAO.updateApplicationUser(user_id, updatedUser); }

    public ApplicationUser getUserById(UUID user_id) { return applicationUserDAO.getUserById(user_id);}
}
