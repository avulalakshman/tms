package com.spaneos.dhi.tms.domain;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueAttachment {
		
		@Autowired
		private String id;
		private byte[] attchement;
	  
}
