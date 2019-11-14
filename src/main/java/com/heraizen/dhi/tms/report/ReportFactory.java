package com.heraizen.dhi.tms.report;

import static com.spaneos.dtssp.mongodb.DtsspMongoQueryGenerator.newDtsspMongoQueryGenerator;
import static com.spaneos.dtssp.mongodb.MongoFilterExpression.filterName;
import static com.spaneos.dtssp.mongodb.dsparam.wrappers.MongoParamCriteriaClauses.where;
import static com.spaneos.dtssp.view.DataTableView.newView;

import org.springframework.stereotype.Component;

import com.spaneos.dtssp.DataTableToDbColMapper;
import com.spaneos.dtssp.mongodb.DtsspMongoReport;
import com.spaneos.dtssp.view.DataTableColumn;

@Component
public class ReportFactory {

	public DtsspMongoReport<UserReportInput> userReport() {
		
		return new DtsspMongoReport.Builder<UserReportInput>()
				
				.withName("Users Report").withView(
						newView()
				.addColumn(DataTableColumn.newDtsspViewCol().withName("username").withTitle("Username").build())
				.addColumn(DataTableColumn.newDtsspViewCol().withName("fullName").withTitle("Fullname").build())
				.addColumn(DataTableColumn.newDtsspViewCol().withName("shortName").withTitle("Shortname").build())
				.addColumn(DataTableColumn.newDtsspViewCol().withName("role").withTitle("Role").build())
				.addColumn(DataTableColumn.newDtsspViewCol().withName("status").withTitle("Status").build())
				.withTitle("Users Report").build())
				.withReportInputType(UserReportInput.class)
				.withQueryGenerator(newDtsspMongoQueryGenerator(UserReportInput.class)
						.fieldNames("username", "fullName", "shortName", "role", "status").fromCollection("user")
						.whenValueGivenFor(filterName(UserReportInput.ROLE_FILTER).useClause(where("role").isGivenParam(UserReportInput.ROLE_PARAM)))
						.whenValueGivenFor(
								filterName(UserReportInput.STATUS_FILTER).useClause(where("status").isGivenParam(UserReportInput.STATUS_PARAM)))
						.whenGivenSearchValUseTheseFields("username", "fullName","shortName","status","role").build())
				.withColumnNameResolver(
						new DataTableToDbColMapper().put("fullName", "fullName").put("shortName", "shortName")
								.put("username", "username").put("role", "role").put("status", "status").get())
				.build();
	}
}
