package com.auth.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {
	@CrossOrigin(origins = "*")
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/user")
	@PreAuthorize("hasRole('user') or hasRole('trainer') or hasRole('admin')")
	public String userAccess() {
		return "User Content.";
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/trainer")
	@PreAuthorize("hasRole('trainer')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/admin")
	@PreAuthorize("hasRole('admin')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
