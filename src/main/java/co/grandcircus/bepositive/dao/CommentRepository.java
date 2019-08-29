package co.grandcircus.bepositive.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.bepositive.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	public Comment findByCommentId (Integer commentId);
}
