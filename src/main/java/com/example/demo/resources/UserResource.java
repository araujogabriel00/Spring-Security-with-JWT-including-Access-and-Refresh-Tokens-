package com.example.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok().body(userService.getUsers());
	}

	@GetMapping("/getUserbyUserName")
	public ResponseEntity<User> getUser(String username) {
		return ResponseEntity.ok().body(userService.getUser(username));
	}

	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/addUser").toString());
		return ResponseEntity.created(uri).body(userService.saveUser(user));
	}

	@PostMapping("/addRole")
	public ResponseEntity<Role> addRole(@RequestBody Role role) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/addRole").toString());
		return ResponseEntity.created(uri).body(userService.saveRole(role));
	}

	@PostMapping("/addRoletoUser")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm) {
		userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
		return ResponseEntity.ok().build();
	}

}

@Data
class RoleToUserForm {
	private String username;
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
