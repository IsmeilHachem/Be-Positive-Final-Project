package co.grandcircus.bepositive.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Integer commentId;

	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	private Date created;

	public Integer getCommentId() {

		return commentId;
	}

	public void setCommentId(Integer id) {

		this.commentId = id;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	@JsonIgnore
	public Post getPost() {

		return post;
	}

	@JsonIgnore
	public void setPost(Post post) {

		this.post = post;
	}

	public Date getCreated() {

		return created;
	}

	public void setCreated(Date created) {

		this.created = created;
	}
}
