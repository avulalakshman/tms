package com.spaneos.dhi.tms.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spaneos.dhi.tms.service.UploadDocumentService;

@RestController
public class FileUploadController {

	@Autowired
	private UploadDocumentService uploadDocumentService;

	@RequestMapping(value = {"/upload"},method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile mulFile) {
		File file = convertMultiPartToFile(mulFile);
		return uploadDocumentService.uploadDocument(file);

	}

	private File convertMultiPartToFile(MultipartFile file) {

		String path = System.getProperty("java.io.tmpdir");
	
		Path paths = Paths.get(path, "\\upload\\",System.currentTimeMillis()+"_"+file.getOriginalFilename());
		File f = new File(paths.toString());

		try (BufferedOutputStream outputStream = new BufferedOutputStream(
				new FileOutputStream(f))) {
			outputStream.write(file.getBytes());
			outputStream.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return f;

	}
}
