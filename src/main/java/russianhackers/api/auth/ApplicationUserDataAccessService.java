package russianhackers.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class ApplicationUserDataAccessService implements ApplicationUserDAO {

    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ApplicationUserDataAccessService(PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        final String sql = "" +
                "SELECT user_id, name, username, password, email, role, isEnabled, isAccountNonExpired, isCredentialsNonExpired, isAccountNonLocked " +
                " FROM users " +
                " WHERE username = ?";

        try {
            ApplicationUser applicationUser = this.jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{username},
                    (resultSet, i) -> {
                        UUID id = UUID.fromString(resultSet.getString("user_id"));
                        String name = resultSet.getString("name");
                        String usernameIn = resultSet.getString("username");
                        String password = resultSet.getString("password");
                        String email = resultSet.getString("email");
                        String role = resultSet.getString("role");
                        Boolean isAccountNonExpired = resultSet.getBoolean("isAccountNonExpired");
                        Boolean isAccountNonLocked = resultSet.getBoolean("isAccountNonLocked");
                        Boolean isCredentialsNonExpired = resultSet.getBoolean("isCredentialsNonExpired");
                        Boolean isEnabled = resultSet.getBoolean("isEnabled");
                        return new ApplicationUser(id, name, usernameIn, password, role, email, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
                    }
            );
            return Optional.ofNullable(applicationUser);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }
    }

    @Override
    public ApplicationUser insertUser(ApplicationUser applicationUser) {
        return null;
    }

}
