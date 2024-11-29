package co.id.implementation.test.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.implementation.test.entity.User;
import co.id.implementation.test.repository.UserRepository;
import co.id.implementation.test.service.UserService;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		this.userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
	}

	@Override
	public List<User> getListUsers() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return this.userRepository.findById(id);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(id);
	}

}
