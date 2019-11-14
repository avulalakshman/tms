package com.heraizen.dhi.tms.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.heraizen.dhi.tms.service.UploadDocumentService;

@RestController
public class FileUploadController {

	@Autowired
	private UploadDocumentService uploadDocumentService;

	@PostMapping(value = { "issue/files/supload" })
	public String upload(@RequestParam("file") MultipartFile mulFile) {
		Optional<String> uploadedFile = uploadDocumentService.uploadDocument(mulFile);
		if (uploadedFile.isPresent()) {
			return uploadedFile.get();
		}
		throw new FileUploadException("While uploading file got error");

	}

	@PostMapping(value = { "issue/files/mupload" })
	public List<String> uploadMultiple(@RequestParam("files") MultipartFile[] mulFile) {
		return uploadDocumentService.uploadDocument(mulFile);

	}

}
