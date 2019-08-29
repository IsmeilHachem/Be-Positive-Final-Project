package co.grandcircus.bepositive.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.bepositive.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByNameAndPassword(String name, String password);

	public User findByName(String name);

	public User findByUserId(Integer userId);

}
