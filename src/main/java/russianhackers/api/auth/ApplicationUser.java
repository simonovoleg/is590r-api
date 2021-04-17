package russianhackers.api.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import russianhackers.api.security.ApplicationUserRole;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationUser implements UserDetails {

//    Build users from this class


    private final UUID id;
    private final String name;
    private final String role;
    private final String password;
    private final String email;
    private final String username;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;


    public ApplicationUser(
                    @JsonProperty("user_id") UUID id,
                    @JsonProperty("name") String name,
                    @JsonProperty("username") String username,
                    @JsonProperty("password") String password,
                    @JsonProperty("role") String role,
                    @JsonProperty("email") String email,
                    @JsonProperty("isAccountNonExpired") boolean isAccountNonExpired,
                    @JsonProperty("isAccountNonLocked") boolean isAccountNonLocked,
                    @JsonProperty("isCredentialsNonExpired") boolean isCredentialsNonExpired,
                    @JsonProperty("isEnabled") boolean isEnabled) {

        this.id = id;
        this.name = name;
        this.role = role;
        this.password = password;
        this.username = username;
        this.email = email;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    private static Set<SimpleGrantedAuthority> getAuthoritiesForRole(String role) {
        if (role.equalsIgnoreCase(ApplicationUserRole.ADMIN.toString())) {
            return ApplicationUserRole.ADMIN.getGrantedAuthorities();
        } else if (role.equalsIgnoreCase(ApplicationUserRole.READER.toString())) {
            return ApplicationUserRole.READER.getGrantedAuthorities();
        }
        return new HashSet<SimpleGrantedAuthority>();
    }

    public String getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthoritiesForRole(this.getRole());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UUID getUserid() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
