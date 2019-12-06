package com.example.pswbackend.domain;

import com.example.pswbackend.enums.UserStatus;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="CC_ADMIN")
public class CCAdmin extends Account{

	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;

	public CCAdmin() {
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
}
