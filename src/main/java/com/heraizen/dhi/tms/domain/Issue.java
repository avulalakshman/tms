package com.heraizen.dhi.tms.domain;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Issue {
		
		@Id
		private String id;
		private String title;
		private String description;
		private String username;
		private String moduleName;
		private List<String> tags;
		private IssueStatus issueStatus;
		private List<String> attachementIds;
		private List<IssueAction> issueActions;
		
		
		@CreatedDate
		private Instant createdDate;
		@LastModifiedBy
		private String lastModifiedUser;
		@LastModifiedDate
		private Instant lastModifiedDate;

}
