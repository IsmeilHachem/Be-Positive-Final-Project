package co.grandcircus.bepositive.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Integer postId;

	private String description;

	private Date created;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Comment> comments;

	@Column(name = "max_score")
	private Double maxScore;

	@Column(name = "max_tone")
	private String maxTone;

	public Integer getPostId() {

		return postId;
	}

	public void setPostId(Integer id) {

		this.postId = id;
	}

	public Double getMaxScore() {

		return maxScore;
	}

	public void setMaxScore(Double maxScore) {

		this.maxScore = maxScore;
	}

	public String getMaxTone() {

		return maxTone;
	}

	public void setMaxTone(String maxTone) {

		this.maxTone = maxTone;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public Date getCreated() {

		return created;
	}

	public void setCreated(Date created) {

		this.created = created;
	}

	@JsonIgnore
	public User getUser() {

		return user;
	}

	@JsonIgnore
	public void setUser(User user) {

		this.user = user;
	}

	public List<Comment> getComments() {

		return comments;
	}

	public void setComments(List<Comment> comments) {

		this.comments = comments;
	}

	/*
	 * if less than a minute display in seconds else if less than a hour display in
	 * minutes else if less than a day display in hours else display in days
	 *
	 */
	private static final int ONE_MIN = 60;

	private static final int ONE_HOUR = ONE_MIN * 60;

	private static final int ONE_DAY = ONE_HOUR * 24;

	public String getElapsed() {

		String returnValue = "";
		long diffInSeconds = (System.currentTimeMillis() - getCreated().getTime()) / 1000;
		if (diffInSeconds < ONE_MIN) {
			returnValue = diffInSeconds + " sec";
		} else if (diffInSeconds < ONE_HOUR) {
			returnValue = diffInSeconds / ONE_MIN + " min";
		} else if (diffInSeconds < ONE_DAY) {
			returnValue = diffInSeconds / ONE_HOUR + " hour";
		} else {
			returnValue = diffInSeconds / ONE_DAY + " day";
		}
		return returnValue + "(s) ago";
	}
}
