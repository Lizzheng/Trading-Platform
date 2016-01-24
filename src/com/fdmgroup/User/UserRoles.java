package com.fdmgroup.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fdmgroup.Crud.Storable;

@Entity
@Table(name="trading_user_roles")
public class UserRoles implements Storable
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_role_id")
	@SequenceGenerator(name = "user_role_id", sequenceName="trading_user_role_id_seq", allocationSize=1)
	@Column(name="role_Id")
	private int roleId;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="User_id", nullable=false, referencedColumnName="User_id")
	User forUser;

	@Enumerated(EnumType.STRING)
	@Column(name="role")
	private UserType User_role;
	
	public int getRoleId()
	{
		return roleId;
	}
	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}
	public UserRoles()
	{
		
	}
	public UserRoles(UserType type)
	{
		User_role = type;
	}
	
	public User getUser()
	{
		return forUser;
	}

	public void setUser(User User)
	{
		this.forUser = User;
		if (!User.getRole().contains(this)) {
            User.getRole().add(this);
        }
	}


	public UserType getRoles()
	{
		return User_role;
	}

	public void setRoles(UserType roles)
	{
		this.User_role = roles;
	}
	@Override
	public int getID()
	{
		return roleId;
	}

	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + roleId;
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof UserRoles))
		{
			return false;
		}
		UserRoles other = (UserRoles) obj;
		if (User_role != other.User_role)
		{
			return false;
		}
		if (this.roleId == ((UserRoles)obj).getID())
		{
			return true;
		}
		if (roleId != other.roleId)
		{
			return false;
		}
		return true;
	}
}
