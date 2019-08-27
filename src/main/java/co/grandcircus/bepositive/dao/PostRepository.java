package co.grandcircus.bepositive.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.grandcircus.bepositive.entities.Post;
import co.grandcircus.bepositive.entities.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	public List<Post> findAllByOrderByCreatedDesc();

	public List<Post> findByUser(User user);

	public Post findByPostId(Integer postId);

}
