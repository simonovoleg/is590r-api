package russianhackers.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import russianhackers.api.auth.ApplicationUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import russianhackers.api.auth.ApplicationUserDAO;


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

    public int addApplicationUser(ApplicationUser applicationUser) {
        return applicationUserDAO.insertApplicationUser(applicationUser);
    }

    public List<ApplicationUser> getAllPeople() {
        return applicationUserDAO.selectAllPeople();
    }

    public Optional<ApplicationUser> getUserById(UUID user_id) {
        return applicationUserDAO.selectApplicationUserById(user_id);
    }

    public int deleteApplicationUser(UUID user_id) {
        return applicationUserDAO.deleteApplicationUserById(user_id);
    }

    public int updateApplicationUser(UUID user_id, ApplicationUser newUser) {
        return applicationUserDAO.updateApplicationUserById(user_id, newUser);
    }
}
