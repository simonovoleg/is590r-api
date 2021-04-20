package russianhackers.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.security.Principal;

import java.util.Optional;
import java.util.UUID;

import russianhackers.api.model.Journal;
import russianhackers.api.model.ReturnUser;

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
    public ApplicationUser insertApplicationUser(UUID user_id, ApplicationUser applicationUser) {
        jdbcTemplate.update(
                "INSERT INTO users (user_id, username, password, name, email, role) VALUES (?, ?, ?, ?, ?, ?)",
                user_id,
                applicationUser.getUsername(),
                passwordEncoder.encode(applicationUser.getPassword()),
                applicationUser.getName(),
                applicationUser.getEmail(),
                "ADMIN"
        );
        ApplicationUser newUser = jdbcTemplate.queryForObject(
        "SELECT * FROM users WHERE user_id = ?",
            new Object[]{user_id},
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
        return newUser;
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
    public ApplicationUser updateApplicationUser(UUID user_id, ApplicationUser updatedUser) {
        jdbcTemplate.update("UPDATE users set name = ?, email = ? where user_id = ?", updatedUser.getName(), updatedUser.getEmail(), user_id);
        ApplicationUser user = jdbcTemplate.queryForObject(
            "SELECT * FROM users WHERE user_id = ?",
            new Object[]{user_id},
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
        return user;
    }

    @Override
    public ApplicationUser getUserById(UUID user_id) {
        String sql = "SELECT * FROM users WHERE  user_id = ?";
        ApplicationUser user = jdbcTemplate.queryForObject(
            sql,
            new Object[]{user_id},
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
        return user;
    }

    @Override
    public ReturnUser getApplicationUser(Principal principal) {
        String username = principal.getName();
        String sql = "SELECT * FROM users WHERE username = ?";
        ReturnUser user = jdbcTemplate.queryForObject(
            sql,
            new Object[]{username},
            (resultSet, i) -> {
                UUID id = UUID.fromString(resultSet.getString("user_id"));
                String name = resultSet.getString("name");
                String usernameIn = resultSet.getString("username");
                String email = resultSet.getString("email");
                return new ReturnUser(id, name, usernameIn, email);
            }
        );
        return user;
    }
}
