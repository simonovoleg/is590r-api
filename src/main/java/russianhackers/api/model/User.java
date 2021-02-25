package russianhackers.api.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username"),
				@UniqueConstraint(columnNames = "email")
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}


//ORIGINAL CODE

//import java.util.UUID;
//
//import javax.validation.constraints.NotBlank;
//
//import com.fasterxml.jackson.annotation.JsonProperty;

//public class User {
//	private final UUID user_id;
//
//	@NotBlank
//	private final String name;
//	private final String email;
//	private final String password;
//	private final String username;
//	private final Boolean isAccountNonExpired;
//	private final Boolean isAccountNonLocked;
//	private final Boolean isCredentialsNonExpired;
//	private final Boolean isEnabled;
//
//	public User(@JsonProperty("user_id") UUID user_id,
//				@JsonProperty("name") String name,
//				@JsonProperty("email") String email),
//				@JsonProperty("password") String password),
//				@JsonProperty("username") String username),
//				@JsonProperty("isAccountNonExpired") Boolean isAccountNonExpired),
//				@JsonProperty("isAccountNonLocked") Boolean isAccountNonLocked),
//				@JsonProperty("isCredentialsNonExpired") Boolean isCredentialsNonExpired),
//				@JsonProperty("isEnabled") Boolean isEnabled), {
//		this.user_id = user_id;
//		this.name = name;
//		this.email = email;
//		this.password = password;
//		this.username = username;
//		this.isAccountNonExpired = isAccountNonExpired;
//		this.isAccountNonLocked = isAccountNonLocked;
//		this.isCredentialsNonExpired = isCredentialsNonExpired;
//		this.isEnabled = isEnabled;
//	}
//
//	public UUID getId() {
//		return user_id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//	public String getUsername() {
//		return username;
//	}
//}
