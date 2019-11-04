package com.spaneos.dhi.tms.domain;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleInfo {
	
		@Id
		private String id;
		private String title;
		private String description;
		
		@CreatedDate
		private Instant createdDate;
		@LastModifiedBy
		private String lastModifiedUser;
		@LastModifiedDate
		private Instant lastModifiedDate;
		
}
