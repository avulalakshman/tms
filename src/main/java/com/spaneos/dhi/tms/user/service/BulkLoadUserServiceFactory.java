package com.spaneos.dhi.tms.user.service;

import java.nio.file.Path;
import java.util.function.Function;

public class BulkLoadUserServiceFactory {

	public BulkUserLoaderService getLoadUserService(Function<Path,FileType> fileTypeProvider, Path path) {
		switch (fileTypeProvider.apply(path)) {
		case CSV:
			return  BulkLoadCSVUserService.of(path);
		case EXCEL:
			return BulkLoadExcelUserService.of(path);
		case YAML:
			return BulkLoadYAMUserService.of(path);
		default:
			throw new IllegalArgumentException("File type is not a valid type");
		}
	}

}
