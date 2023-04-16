package com.thanhnpn99.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Fresher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Name")
	private String name;

	
	@OneToOne
	private Address address;
	
	@OneToMany
	private List<Course> courses = new ArrayList<Course>();
	
	@ManyToMany
	private Set<Group> groups = new HashSet<Group>();

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Fresher(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
	}

	public Fresher(String name, List<Course> courses) {
		super();
		this.name = name;
		this.courses = courses;
	}

	public Fresher(String name, Set<Group> groups) {
		super();
		this.name = name;
		this.groups = groups;
	}

	public Fresher() {
		super();
		// TODO Auto-generated constructor stub
	}

}
