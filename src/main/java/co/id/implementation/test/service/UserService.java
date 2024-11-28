package co.id.implementation.test.service;

import java.util.List;

import org.springframework.stereotype.Component;

import co.id.implementation.test.entity.User;


@Component
public interface UserService {
	
	public void saveUser(User user);
	public User findByUsername(String username);
	public User findById(Long id);
	public List<User> getListUsers();

}
