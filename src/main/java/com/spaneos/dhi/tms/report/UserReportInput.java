package com.spaneos.dhi.tms.report;

import static com.spaneos.dtssp.mongodb.input.DtsspMongoFiltersAndNamedParamsProviderImpl.newDtsspMongoFiltersAndParamsProvider;

import com.spaneos.dhi.tms.domain.Role;
import com.spaneos.dhi.tms.domain.UserStatus;
import com.spaneos.dtssp.input.AbstractDtsspInput;
import com.spaneos.dtssp.input.DtsspFilterAndParameters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReportInput extends AbstractDtsspInput {

	

	private String username;
	private String shortName;
	private String fullName;
	private UserStatus status;
	private Role role;
	
	
	public static final String ROLE_FILTER = "ROLE_FILTER";
	public static final String ROLE_PARAM = "ROLE_PARAM";
	public static final String STATUS_PARAM = "STATUS_PARAM";
	public static final String STATUS_FILTER = "STATUS_FILTER";

	@Override
	public DtsspFilterAndParameters toFilterParams() {
		return newDtsspMongoFiltersAndParamsProvider()
				.addFilterParamValWhenNotNull(ROLE_FILTER, ROLE_PARAM, role)
				.addFilterParamValWhenNotNull(STATUS_FILTER, STATUS_PARAM, status)
				.addSearchFilterValue(getSearch().getValue())
				.build();
	}
	
	

	

}
