package russianhackers.api.auth;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static russianhackers.api.security.ApplicationUserRole.ADMIN;
import static russianhackers.api.security.ApplicationUserRole.READER;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDAO {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
            new ApplicationUser(
                    "drew",
                    passwordEncoder.encode("password"),
                    ADMIN.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true
            ),
            new ApplicationUser(
                    "oleg",
                    passwordEncoder.encode("password123"),
                    READER.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true
            )
        );

        return applicationUsers;
    }
}
