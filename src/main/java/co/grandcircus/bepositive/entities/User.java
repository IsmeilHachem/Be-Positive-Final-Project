package co.grandcircus.bepositive.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "user_name")
	private String name;

	// https://medium.com/skillhive/how-to-retrieve-a-parent-field-from-a-child-entity-in-a-one-to-many-bidirectional-jpa-relationship-4b3cd707bfb7
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Post> posts;

	public User() {

	}

	public Integer getUserId() {

		return userId;
	}

	public void setUserId(Integer userId) {

		this.userId = userId;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}
}
