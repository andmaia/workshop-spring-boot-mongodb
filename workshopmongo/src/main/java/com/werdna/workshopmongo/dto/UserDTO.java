package com.werdna.workshopmongo.dto;

import com.werdna.workshopmongo.domain.User;

public record UserDTO(
		String id,
		String name,
		String email) {
	public UserDTO(User user) {
		this(user.getId(),user.getName(),user.getEmail());
	}

}
