package com.studycase.springboot.rms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studycase.springboot.rms.entity.Users;
import com.studycase.springboot.rms.services.RoleServices;
import com.studycase.springboot.rms.services.UsersServices;

@Controller
public class UsersController {

	private UsersServices usersSerivces;
	private RoleServices roleServices;
	private PasswordEncoder passwordEncoder;

	public UsersController(UsersServices usersSerivces, RoleServices roleServices, PasswordEncoder passwordEncoder) {
		this.usersSerivces = usersSerivces;
		this.roleServices = roleServices;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		model.addAttribute("logout", "You have been logged out");
		return "redirect:/login";
	}

	@GetMapping("/users")
	public String findAll(Model model) {
		model.addAttribute("userList", usersSerivces.findAll());
		return "user";
	}

	@GetMapping("/admin/find/{id}")
	public Users getUsers(@PathVariable Long id) {
		return usersSerivces.findbyId(id).orElseThrow(() -> new RuntimeException("User id not found - " + id));

	}

	@GetMapping("/admin/create")
	public String addUser(Model model) {
		model.addAttribute("user", new Users());
		model.addAttribute("roles", roleServices.findAll());
		return "create";
	}

	@PostMapping("/admin/create")
	public String create(Users users, Model model) {
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		usersSerivces.addUsers(users);
		model.addAttribute("userList", usersSerivces.findAll());
		return "user-edit";

	}

	@GetMapping("/admin/edit")
	public String editUser(Model model) {
		model.addAttribute("userList", usersSerivces.findAll());
		return "user-edit";
	}

	@GetMapping("/admin/delete/{id}")
	public String deleteUsers(@PathVariable Long id, Model model) {

		if (usersSerivces.findbyId(id) == null) {
			throw new RuntimeException("User id not found - " + id);
		}

		usersSerivces.deleteUsers(id);
		model.addAttribute("deleteMessage", "User has been deleted");
		model.addAttribute("userList", usersSerivces.findAll());
		return "user-edit";
	}

	@GetMapping("/admin/update/{id}")
	public String updateUserPage(@PathVariable Long id, Model model) {
		usersSerivces.findbyId(id).ifPresent(u -> model.addAttribute("user", u));
		model.addAttribute("roles", roleServices.findAll());
		return "update";
	}

	@PostMapping("/admin/update/{id}")
	public String updateUser(Users users, Model model) {
		List<Users> list = usersSerivces.findAll();
		for (Users u : list) {
			if (u.getUserName().equalsIgnoreCase(users.getUserName())) {
				throw new RuntimeException("User Name already exist");
			}
		}
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		usersSerivces.addUsers(users);
		model.addAttribute("userList", usersSerivces.findAll());
		return "user-edit";
	}

	@GetMapping("/403")
	public String acceessDenied() {
		return "403";
	}

}
