package com.kugiojotaro.placesshots.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item implements Serializable {

	private static final long serialVersionUID = 9155948458292891943L;
	
	private Short id;
	private String title;
	private Short multiple;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Short getId() {
		return id;
	}
	public void setId(Short id) {
		this.id = id;
	}
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "multiple")
	public Short getMultiple() {
		return multiple;
	}
	public void setMultiple(Short multiple) {
		this.multiple = multiple;
	}

}