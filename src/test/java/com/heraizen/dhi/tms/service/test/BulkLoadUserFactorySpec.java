package com.heraizen.dhi.tms.service.test;

import static com.heraizen.dhi.tms.AppTestUtil.getCsvFilePath;
import static com.heraizen.dhi.tms.AppTestUtil.getExcelFilePath;
import static com.heraizen.dhi.tms.AppTestUtil.getYAMLFilePath;
import static com.heraizen.dhi.tms.SeedDataUtil.fileTypeDecider;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import com.heraizen.dhi.tms.user.service.BulkLoadCSVUserService;
import com.heraizen.dhi.tms.user.service.BulkLoadExcelUserService;
import com.heraizen.dhi.tms.user.service.BulkLoadUserServiceFactory;
import com.heraizen.dhi.tms.user.service.BulkLoadYAMUserService;

@SpringBootTest
public class BulkLoadUserFactorySpec {

	private BulkLoadUserServiceFactory loadUserFactory;

	@Autowired
	public BulkLoadUserFactorySpec(BulkLoadUserServiceFactory loadUserFactory) {
		this.loadUserFactory = loadUserFactory;
	}

	@Test
	public void factoryLoaderByType() throws IOException {

		assertAll("This factory method should return loader service object by file type:",
				() -> assertThat(loadUserFactory.getLoadUserService(fileTypeDecider, getExcelFilePath()))
						.isInstanceOf(BulkLoadExcelUserService.class),
				() -> assertThat(loadUserFactory.getLoadUserService(fileTypeDecider, getCsvFilePath()))
						.isInstanceOf(BulkLoadCSVUserService.class),
				() -> assertThat(loadUserFactory.getLoadUserService(fileTypeDecider, getYAMLFilePath()))
						.isInstanceOf(BulkLoadYAMUserService.class),
				() -> assertThrows(IllegalArgumentException.class, () -> loadUserFactory
						.getLoadUserService(fileTypeDecider, new ClassPathResource("error.abc").getFile().toPath()))

		);

	}

}
