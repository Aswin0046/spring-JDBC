package com.jdbc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdbc.demo.dao.SignUpDao;
import com.jdbc.demo.model.Response;
import com.jdbc.demo.model.SignUpModel;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class SignUpController {

	@Autowired
	SignUpDao dao;
	
	
	@PostMapping("/create")
	public ResponseEntity<Response> createUser(@RequestBody SignUpModel values){
		return ResponseEntity.ok(dao.createUser(values));
	}
	
	@GetMapping("/get")
	public ResponseEntity<Response> selectUser(){
		return ResponseEntity.ok(dao.selectUser( ));
	}
	
	@GetMapping("/getone")
	public ResponseEntity<Response> getOneUser(@RequestParam String sNo){
		return ResponseEntity.ok(dao.getOneUser(sNo));
	}
	@PutMapping("/update")
	public ResponseEntity<Response>getUpdate(@RequestParam String email,@RequestParam String sNo){
		return ResponseEntity.ok(dao.getUpdate(email,sNo));
		
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteUser(@RequestParam String sNo){
		return ResponseEntity.ok(dao.deleteUser(sNo));
	}
	
	
}
