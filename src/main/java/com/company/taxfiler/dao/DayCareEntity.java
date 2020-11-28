package com.company.taxfiler.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "day_care")
public class DayCareEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "inst_name")
	private String instName;

	@Column(name = "inst_tax_id")
	private String instTaxId;

	private String address;

	@Column(name = "door_no")
	private String doorNo;

	private String city;

	private String state;

	private String zip;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dependent_info_id", referencedColumnName = "id")
	@JsonBackReference
	private DependentInformationEntity dependentInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getInstTaxId() {
		return instTaxId;
	}

	public void setInstTaxId(String instTaxId) {
		this.instTaxId = instTaxId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public DependentInformationEntity getDependentInfo() {
		return dependentInfo;
	}

	public void setDependentInfo(DependentInformationEntity dependentInfo) {
		this.dependentInfo = dependentInfo;
	}

}
