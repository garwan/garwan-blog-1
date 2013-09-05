package sk.garwan.hibernate2pc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import javax.persistence.Id;

@Entity
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", length = 10, nullable = false)
	private String name;

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
}
