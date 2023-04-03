package com.werdna.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.werdna.workshopmongo.domain.User;
import com.werdna.workshopmongo.dto.UserDTO;
import com.werdna.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDTO=list.stream().map(x->new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User user=service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> inser(@RequestBody UserDTO dados,UriComponentsBuilder builder){
		User user = new User(dados);
		service.insert(user);
		var url=builder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(url).body(dados);
		
		
	}
	
}
