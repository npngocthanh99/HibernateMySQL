package com.thanhnpn99.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name = "Course")
@Table(name = "Course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@Column(name = "Name")
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "Created_Date")
	private Date createdDate;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<Syllabus> syllabus = new ArrayList<Syllabus>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<Syllabus> getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(List<Syllabus> syllabus) {
		this.syllabus = syllabus;
	}

	public Course(String name, Date createdDate, List<Syllabus> syllabus) {
		super();
		this.name = name;
		this.createdDate = createdDate;
		this.syllabus = syllabus;
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public Course() {
		super();
	}

}
