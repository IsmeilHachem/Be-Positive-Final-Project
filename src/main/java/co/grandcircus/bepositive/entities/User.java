package co.grandcircus.bepositive.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "user_name") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "user_name", unique = true, nullable = false)
	private String name;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String password;

	// https://medium.com/skillhive/how-to-retrieve-a-parent-field-from-a-child-entity-in-a-one-to-many-bidirectional-jpa-relationship-4b3cd707bfb7
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Post> posts;

	@ManyToMany
	@JoinTable(name = "follows", joinColumns = { @JoinColumn(name = "user_id") })
	private Set<User> follows;

	@ManyToMany(mappedBy = "follows")
	private Set<User> followers;

	public User() {

	}

	public Integer getUserId() {

		return userId;
	}

	public void setUserId(Integer userId) {

		this.userId = userId;
	}

	public String getFirstName() {

		return firstName;
	}

	public void setFirstName(String firstName) {

		this.firstName = firstName;
	}

	public String getLastName() {

		return lastName;
	}

	public void setLastName(String lastName) {

		this.lastName = lastName;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Post> getPosts() {

		return posts;
	}

	public Set<User> getFollows() {

		return follows;
	}

	public void setFollows(Set<User> follows) {

		this.follows = follows;
	}

	public Set<User> getFollowers() {

		return followers;
	}

	public void setFollowers(Set<User> followers) {

		this.followers = followers;
	}

	@Override
	public String toString() {

		return "User [userId=" + userId + ", name=" + name + ", firstName=" + firstName + ", lastName=" + lastName
				+ "post count=" + getPosts().size() + "]";
	}
}
