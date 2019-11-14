package com.heraizen.dhi.tms;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Function;

import org.springframework.core.io.ClassPathResource;

import com.heraizen.dhi.tms.user.service.FileType;

public class AppTestUtil {
	
	public static Path getExcelFilePath() throws IOException {
		return new ClassPathResource("users.xlsx").getFile().toPath();
	}

	public static Path getCsvFilePath() throws IOException {
		return new ClassPathResource("users.csv").getFile().toPath();
	}
	public static Path getYAMLFilePath() throws IOException {
		return new ClassPathResource("users.yml").getFile().toPath();
	}


	
	public static final Function<Path, FileType> fileTypeDecider = (p) -> {
		String fileName = p.toString();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase().trim();
		System.out.println(fileName + " and " + fileExt);
		switch (fileExt) {
		case "xlsx":
		case "xls":
			return FileType.EXCEL;
		case "yaml":
		case "yml":
			return FileType.YAML;
		case "json":
			return FileType.JSON;
		case "csv":
			return FileType.CSV;
		default:
			throw new IllegalArgumentException(String.format("File extenction type %s is not supported", fileExt));

		}
	};

}
