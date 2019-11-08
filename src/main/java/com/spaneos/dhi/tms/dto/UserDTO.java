package com.spaneos.dhi.tms.dto;

import java.time.Instant;

import javax.xml.bind.annotation.XmlRootElement;

import com.spaneos.dhi.tms.domain.Role;
import com.spaneos.dhi.tms.domain.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class UserDTO {
	private String id;
	private String fullName;
	private String shortName;
	private String username;
	private String password;
	private Instant createdDate;
	private String lastModifiedUser;
	private Instant lastModifiedDate;
	private UserStatus status;
	private Role role;
}
