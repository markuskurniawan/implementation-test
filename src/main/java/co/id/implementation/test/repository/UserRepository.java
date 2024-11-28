package co.id.implementation.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.implementation.test.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
