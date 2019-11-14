package com.heraizen.dhi.tms.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.heraizen.dhi.tms.domain.IssueAttachment;
import com.heraizen.dhi.tms.user.repo.IssueAttachmentRepo;

@Component
public class UploadDocumentService {

	private static final Logger LOG = LoggerFactory.getLogger(UploadDocumentService.class);
	@Autowired
	private IssueAttachmentRepo issueAttachmentRepo;

	public Optional<String> uploadDocument(MultipartFile file) {
		IssueAttachment obj = null;
		try {

			IssueAttachment attachment = new IssueAttachment();
			attachment.setAttchement(file.getBytes());
			obj = issueAttachmentRepo.save(attachment);
			LOG.info("Attachment saved with id :{}", obj.getId() != null ? toString() : " not saved");
		} catch (IOException e) {
			LOG.error("While uploading file:{}", e);
			LOG.debug("Stack Trace: {}", e);

		}
		return Optional.of(obj!=null?obj.getId():null);

	}

	public List<String> uploadDocument(MultipartFile[] files) {
		List<String> ids = null;
		LOG.info("No of files requested to upload:{}", files.length);
		ids = Arrays.asList(files).stream()
				.map(this::uploadDocument)
				.filter(e -> e.isPresent())
				.map(e -> e.get())
				.collect(Collectors.toList());
		LOG.info("Total files uploaded successfully is :{}", ids.size());
		return ids;
	}
}
