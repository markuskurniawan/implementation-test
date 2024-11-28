package co.id.implementation.test.contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.id.implementation.test.entity.User;
import co.id.implementation.test.model.MetaData;
import co.id.implementation.test.service.UserService;

@RestController
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/ping")
	public ResponseEntity<String> ping(){

		String result = "Welcome Java Technical Tes API";

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> register(@RequestBody User body){
		ObjectMapper mapper = new ObjectMapper();
		MetaData response = new MetaData();
		String result = "";
		try {
			
			User checkUser = this.userService.findByUsername(body.getUsername());
			
			if (checkUser != null) {
				response.setStatusCode(409);
				response.setMessage("Username sudah terpakai");
			} else {
				this.userService.saveUser(body);
				response.setStatusCode(201);
				response.setMessage("data berhasil disimpan.");				
			}
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
			
		} catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@RequestBody User body){
		ObjectMapper mapper = new ObjectMapper();
		MetaData response = new MetaData();
		String result = "";
		try {
			
			User checkUser = this.userService.findByUsername(body.getUsername());
			
			if (checkUser != null) {
				if(checkUser.getPassword().equalsIgnoreCase(body.getPassword())) {					
					response.setStatusCode(200);
					response.setMessage("sukses login");
				} else {
					response.setStatusCode(401);
					response.setMessage("username / password salah");
				}
			} else {
				response.setStatusCode(401);
				response.setMessage("user tidak ditemukan.");				
			}
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
			
		} catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getListUsers(){
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			
			List<User> dataUsers = this.userService.getListUsers();
			
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataUsers);
			
		} catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUser(@RequestBody User body){
		ObjectMapper mapper = new ObjectMapper();
		MetaData response = new MetaData();
		String result = "";
		try {
			
			User checkUser = this.userService.findById(body.getId());
			
			if (checkUser != null) {
				User checkUserByUsername = this.userService.findByUsername(body.getUsername());
				if(checkUserByUsername != null && checkUserByUsername.getId() != body.getId()) {
					response.setStatusCode(409);
					response.setMessage("Username sudah terpakai");
				} else if(checkUser.getPassword().equalsIgnoreCase(body.getPassword())) {
					response.setStatusCode(400);
					response.setMessage("Password tidak boleh sama dengan password sebelumnya");	
				} else {
					this.userService.saveUser(body);
					response.setStatusCode(201);
					response.setMessage("data berhasil diupdate");
				}
			}
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
			
		} catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
