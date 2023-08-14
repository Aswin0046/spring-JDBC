package com.jdbc.demo.service;

import org.springframework.stereotype.Service;

import com.jdbc.demo.model.Response;
import com.jdbc.demo.model.SignUpModel;
@Service
public interface SignUpService {
		public Response createUser(SignUpModel values);
		public Response selectUser();
		public Response getOneUser(String sNo);
		public Response getUpdate(String email,String sNo);
}
