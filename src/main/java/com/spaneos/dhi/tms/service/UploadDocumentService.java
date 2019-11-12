package com.spaneos.dhi.tms.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.spaneos.dhi.tms.domain.IssueAttachment;
import com.spaneos.dhi.tms.repo.IssueAttachmentRepo;

@Component
public class UploadDocumentService {

	private static final Logger LOG = LoggerFactory.getLogger(UploadDocumentService.class);
	@Autowired
	private IssueAttachmentRepo issueAttachmentRepo;

	public String uploadDocument(File file) {
		byte[] data = null;
		IssueAttachment obj = null;
		try {
			LOG.info("File details:{} and size :{}", file.getName(), file.length());
			data = Files.readAllBytes(file.toPath());
			IssueAttachment attachment = new IssueAttachment();
			attachment.setAttchement(data);
			obj = issueAttachmentRepo.save(attachment);
			LOG.info("Attachment saved with id :{}", obj != null ? obj.getId() : " not saved");
		} catch (IOException e) {
			LOG.error("While uploading file:{}", e);

		}
		return obj != null ? obj.getId() : null;

	}

	public List<String> uploadMultiple(File directory) {
		List<String> ids = null;
		if (directory.isDirectory()) {
			File[] list = directory.listFiles();
			ids = Stream.of(list).map(e->uploadDocument(e)).collect(Collectors.toList());
		} else {
			throw new IllegalArgumentException("Given file is not a directory");
		}
		return ids;
	}
}
