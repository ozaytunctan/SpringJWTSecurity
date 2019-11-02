package tr.ozaytunctan.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
@SuppressWarnings("serial")
@SequenceGenerator(name="SQ_USER",initialValue=1,allocationSize=1,sequenceName="idGenerator")
public class User extends BaseEntity<Integer> {

	private String username;

	private String password;

	private String permissions;

	private String roles;

	private boolean active = false;

	public User() {
		super(0);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getPermissions() {
		if (permissions == null || permissions.isEmpty())
			return Arrays.asList();

		return Arrays.asList(permissions.split(","));
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public List<String> getRoles() {
		if (roles == null || roles.isEmpty())
			return Arrays.asList();

		return Arrays.asList(roles.split(","));
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
