package co.id.implementation.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import co.id.implementation.test.entity.User;


@Component
public interface UserService {
	
	public void saveUser(User user);
	public User findByUsername(String username);
	public Optional<User> findById(Long id);
	public List<User> getListUsers();
	public void deleteUser(Long id);

}
