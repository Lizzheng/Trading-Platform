package com.fdmgroup.User;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fdmgroup.Crud.Storable;

@Entity
@Table(name = "trading_user")
public class User implements Storable {

	@Column(name = "user_name", unique = true)
	private String username;

	@Column(name = "user_password", nullable = false)
	private String password;

	@Column(name = "first_name")
	private String firstname;

	@Column(name = "last_name")
	private String lastname;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
	@SequenceGenerator(name = "user_id", sequenceName = "trading_user_id_seq", allocationSize = 1)
	@Column(name = "User_id")
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "User")
	private int idNumber;

	@OneToMany(mappedBy = "forUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "User_id", nullable = false)
	private Set<UserRoles> role = new HashSet<UserRoles>();

	public User() {

	}

	public User(String username, String password, String firstname,
			String lastname, UserRoles type) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		type.setUser(this);
		role.add(type);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User && ((User) obj).idNumber == this.idNumber)
			return true;
		if (obj instanceof User && ((User) obj).username == this.username)
			return true;
		if (obj instanceof Integer && ((Integer) obj) == this.idNumber)
			return true;
		if (obj instanceof String && ((String) obj) == this.username)
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idNumber;
		return result;
	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int getID() {
		return idNumber;
	}

	public void setID(int idNumber) {
		this.idNumber = idNumber;
	}

	public Set<UserRoles> getRole() {
		return role;
	}

	public void setRole(UserRoles type) {
		if (!role.contains(type)) {
			type.setUser(this);
			role.add(type);
		}
	}

	public void removeRole(UserRoles type) {
		while (role.contains(type)) {
			role.remove(type);
		}
	}

	public void removeRole(UserType type) {
		this.role.remove(type);
	}

	public void addUserRoles(UserRoles User1) {
		this.role.add(User1);
		if (User1.getUser() != this) {
			User1.setUser(this);
		}
	}

	public Set<UserType> getRoleType() {
		Set<UserType> list = new HashSet<UserType>();
		for (UserRoles type : role) {
			list.add(type.getRoles());
		}
		return list;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

}
