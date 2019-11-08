package com.spaneos.dhi.tms.user.service;

import java.util.function.Supplier;

public class BulkLoadUserServiceFactory {

	public BulkUserLoaderService getLoadUserService(Supplier<FileType> fileType, String filePath) {
		switch (fileType.get()) {
		case CSV:
			return new BulkLoadCSVUserService(filePath);
		case EXCEL:
			return new BulkLoadExcelUserService(filePath);
		case YAML:
			return new BulkLoadYAMUserService(filePath);
		default:
			throw new IllegalArgumentException("File type is not a valid type");
		}
	}

	

}
