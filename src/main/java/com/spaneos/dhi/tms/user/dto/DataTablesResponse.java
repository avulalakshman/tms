package com.spaneos.dhi.tms.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataTablesResponse<T extends Object>{

	private  List<? extends Object> data;
	private int draw;
	private int recordsFiltered;
	private int recordsTotal;
}
