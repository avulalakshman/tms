package com.spaneos.dhi.tms.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spaneos.dhi.tms.report.ReportFactory;
import com.spaneos.dhi.tms.report.UserReportInput;
import com.spaneos.dtssp.mongodb.DtsspMongoDBFetchService;
import com.spaneos.dtssp.output.DataTablesOutput;
import com.spaneos.export.mongo.DtsspMongoExportService;
import com.spaneos.export.mongo.shared.DtsspMongoExportDetails;
import static com.spaneos.export.mongo.shared.DtsspMongoExportDetails.newDtsspMongoExportDetailsBuilderFor;

import java.nio.file.Paths;

@RestController
public class UserReportController {


	@Autowired
	DtsspMongoDBFetchService fetchService;
	@Autowired
	ReportFactory reportFactory;
	@Autowired
	DtsspMongoExportService exportService;

	@PostMapping("api/user/reports/users")
	public DataTablesOutput getUsersReport(@RequestBody UserReportInput userReportInput) {
		return fetchService.fetchPagedData(reportFactory.userReport(), userReportInput);
	}

	@PostMapping("api/user/reports/excel")
	public void usersReportExcel(@RequestBody UserReportInput userReportInput) {
		DtsspMongoExportDetails<UserReportInput> exportDetails = newDtsspMongoExportDetailsBuilderFor(
				reportFactory.userReport()).withDtsspInput(userReportInput)
						.withDefaultPdfExporter(Paths.get("D:\\one.pdf")).build();
		exportService.exportDataAsync(exportDetails, s -> System.out.println("Success"),
				s -> System.out.println("Failed"));
	}
}
