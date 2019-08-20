package co.grandcircus.bepositive.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.bepositive.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	public List<Post> findAllByOrderByCreatedDesc();
}
