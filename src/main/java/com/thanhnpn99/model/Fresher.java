package com.thanhnpn99.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Fresher")
public class Fresher {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;

	
	@OneToOne
	private Address address;
	
	@OneToMany
	private List<Course> courses = new ArrayList<Course>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "Fresher_Group", 
        joinColumns = { @JoinColumn(name = "fresher_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "group_id") }
    )
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
