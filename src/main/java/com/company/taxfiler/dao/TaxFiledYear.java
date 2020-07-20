package com.company.taxfiler.dao;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tax_filed_year")
public class TaxFiledYear {

	private long id;
	private long year;
	private long user_id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

}
