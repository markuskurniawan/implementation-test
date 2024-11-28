package co.id.implementation.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.http.HttpClient;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import co.id.implementation.test.entity.User;
import co.id.implementation.test.repository.UserRepository;
import co.id.implementation.test.service.impl.UserServiceImpl;



@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private HttpClient httpClient;
	
	private User user;
	
	@BeforeEach
    public void setup(){
		user = new User();
		user.setId(1L);
		user.setPassword("password");
		user.setUsername("user");
	}
	
	 @Test
    public void testSave() {
        // Arrange
		 when(userRepository.save(user)).thenReturn(user);
        // Act
		 userService.saveUser(user);

        // Assert
		 verify(userRepository, times(1)).save(user);
    }
	 
	 @Test
    public void testFindByUsername() {
        // Arrange
		 when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(user);
        // Act
		 User result = userService.findByUsername(user.getUsername());

        // Assert
        assertNotNull(result);
        assertEquals(user, result);
		 verify(userRepository, times(1)).findByUsername(user.getUsername());
    }
	 
	 @Test
    public void testFindById() {
        // Arrange
		 when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(user));
        // Act
		 User result = userService.findById(user.getId());

        // Assert
        assertNotNull(result);
        assertEquals(user, result);
		 verify(userRepository, times(1)).findById(user.getId());
    }
}
