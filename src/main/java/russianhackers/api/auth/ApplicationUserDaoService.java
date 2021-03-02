package russianhackers.api.auth;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import russianhackers.api.model.User;

import java.sql.Array;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import russianhackers.api.auth.ApplicationUser;

@Repository("postgres")
public class ApplicationUserDaoService implements ApplicationUserDAO{

    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    public ApplicationUserDaoService(PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertApplicationUser(UUID user_id, ApplicationUser applicationUser) {
        jdbcTemplate.update(
                "INSERT INTO users (role, user_id, username, password, name, email) VALUES (?, ?, ?, ?, ?, ?)",
                applicationUser.getRole(),
                user_id,
                applicationUser.getUsername(),
                applicationUser.getPassword(),
                applicationUser.getName(),
                applicationUser.getEmail()
        );
        return 0;
    }

    @Override
    public List<ApplicationUser> selectAllPeople() {
        final String sql = "SELECT user_id, name, password, username, email, role, isAccountNonExpired,isAccountNonLocked, isCredentialsNonExpired, isEnabled FROM users";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID user_id = UUID.fromString(resultSet.getString("user_id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            //Set grantedAuthorities = (Set) resultSet.getArray("grantedAuthorities");
            String role = resultSet.getString("role");
            Boolean isAccountNonExpired = resultSet.getBoolean("isAccountNonExpired");
            Boolean isAccountNonLocked = resultSet.getBoolean("isAccountNonLocked");
            Boolean isCredentialsNonExpired = resultSet.getBoolean("isCredentialsNonExpired");
            Boolean isEnabled = resultSet.getBoolean("isEnabled");
            return new ApplicationUser(user_id, name, email, username, password, role, isAccountNonExpired, isCredentialsNonExpired, isAccountNonLocked, isEnabled);
        });
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserById(UUID user_id) {
        return Optional.empty();
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        final String sql = "SELECT user_id, name, email FROM users";
        return selectAllPeople()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    @Override
    public int deleteApplicationUserById(UUID user_id) {
        return 0;
    }

    @Override
    public int updateApplicationUserById(UUID user_id, ApplicationUser user) {
        return 0;
    }
}
