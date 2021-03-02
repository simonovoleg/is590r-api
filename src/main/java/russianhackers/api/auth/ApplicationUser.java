package russianhackers.api.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import russianhackers.api.security.ApplicationUserRole;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ApplicationUser implements UserDetails {

//    Build users from this class

    private final String role;
    private final UUID user_id;
    private final String username;
    private final String password;
    private final String name;
    private final String email;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    public ApplicationUser(UUID user_id,
                           String name,
                           String email,
                           String username,
                           String password,
                           String role,
                           boolean isAccountNonExpired,
                           boolean isAccountNonLocked,
                           boolean isCredentialsNonExpired,
                           boolean isEnabled) {
        this.role = role;
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.name = name;
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
        return null;
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
        return user_id;
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
