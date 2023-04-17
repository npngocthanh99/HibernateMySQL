package com.thanhnpn99.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "[Group]")
public class Group {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	@ManyToMany(mappedBy = "groups")
	private Set<Fresher> freshers = new HashSet<>();
	
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
	
	public Set<Fresher> getFreshers() {
		return freshers;
	}
	public void setFreshers(Set<Fresher> freshers) {
		this.freshers = freshers;
	}
	public Group(String name) {
		super();
		this.name = name;
	}
	
	public Group(String name, Set<Fresher> freshers) {
		super();
		this.name = name;
		this.freshers = freshers;
	}
	public Group() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return id + " " + name;
	}
	
}
