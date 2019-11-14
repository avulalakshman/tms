package com.heraizen.dhi.tms.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.heraizen.dhi.tms.service.UploadDocumentService;
import com.heraizen.dhi.tms.user.repo.IssueAttachmentRepo;

@SpringBootTest
public class IssueAttachmentTest {
		@Autowired
		private UploadDocumentService uploadDocumentService;
		@Autowired
		private IssueAttachmentRepo issueAttachmentRepo;
	
		@BeforeEach
		public void init() {
			issueAttachmentRepo.deleteAll();
		}
		@Test
		public void addAttachment() {
			File f = new File(this.getClass().getResource("/codedemo.png").getFile());
			String id = null;//uploadDocumentService.uploadDocument(f);
			System.out.println(id);
			assertThat(id).isNotEmpty();
		}
		@Test
		public void addAttachmentDirectory() {
			File f = new File(this.getClass().getResource("/images").getFile());
			 List<String> ids = null;//uploadDocumentService.uploadMultiple(f);
			System.out.println(ids);
			assertThat(ids).size().isEqualTo(3);
		}
}
