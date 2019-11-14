package com.heraizen.dhi.tms.domain;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueAttachment {
		
		@Id
		private String id;
		private byte[] attchement;
		
		
	  
}
