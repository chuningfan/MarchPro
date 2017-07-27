package org.march.report.test.dto;

import java.util.Date;

import org.march.report.annotation.ReportDataMarker;
import org.march.report.annotation.ReportEntityMarker;
import org.march.report.test.format.DateFormat;
import org.march.report.test.format.TestFormat;

@ReportEntityMarker(hasDataMapper = true)
public class TestDto {
	
	@ReportDataMarker(queryIndex = 2, displayName="number")
	private Long id;
	@ReportDataMarker(queryIndex = 1, displayName="user name", format=TestFormat.class)
	private String name;
	@ReportDataMarker(queryIndex = 0, displayName="address")
	private String addr;
	@ReportDataMarker(queryIndex = 3, displayName="date", format=DateFormat.class)
	private Date date;
	@ReportDataMarker(queryIndex = 4, displayName="sex")
	private String gender;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
