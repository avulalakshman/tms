package com.heraizen.dhi.tms.domain;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class IssueAction {
	
		
		private String issueId;
		private String description;
		private String username;
		private String assignedTo;
		private Instant createdDate;
		private IssueStatus issueStatus;
		
	
}
