package com.jdbc.demo.dao;

import java.sql.Statement;
import java.util.Calendar;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jdbc.demo.model.Response;
import com.jdbc.demo.model.SignUpModel;
import com.jdbc.demo.service.SignUpService;

@SuppressWarnings("unused")
@Component
public class SignUpDao implements SignUpService {

	Response rps = new Response();
	String url = "jdbc:mysql://127.0.0.1:3306/kgm";
	String username = "root";
	String password = "Aswin@20";

	public Response createUser(SignUpModel values) {

		String uuid = UUID.randomUUID().toString();
		values.setsNo(uuid);
		values.setCreatedBy(uuid);
		values.setUpdatedBy(uuid);

		Date date = new Date(Calendar.getInstance().getTime().getTime());
		values.setCreatedDate(date);
		values.setUpdatedDate(date);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();) {
				String insertQuery = "INSERT INTO kgm.user_details(s_no,first_name,last_name,email,dob,gender,phone_number,password,created_by,"
						+ "updated_by,created_date,updated_date)VALUES('" + values.getsNo() + "','"
						+ values.getFirstName() + "','" + values.getLastName() + "','" + values.getEmail() + "','"
						+ values.getDob() + "','" + values.getGender() + "'," + "" + values.getPhoneNumber() + ",'"
						+ values.getPassword() + "','" + values.getCreatedBy() + "','" + values.getUpdatedBy() + "','"
						+ values.getUpdatedDate() + "','" + values.getCreatedDate() + "')";
				st.executeUpdate(insertQuery);
				rps.setData("User Created");
				rps.setResponseCode(200);
				rps.setResponseMessage("sucess");

			} catch (Exception e) {
				e.printStackTrace();

				rps.setData("Cannot create User");
				rps.setResponseCode(500);
				rps.setResponseMessage("error");
			}

		} catch (Exception e) {
			e.printStackTrace();
			rps.setData("Driver error");
			rps.setResponseCode(500);
			rps.setResponseMessage("error");
		}

		return rps;
	}

	@SuppressWarnings("unchecked")
	public Response selectUser() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String selectQuery = "select * from user_details";

			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(selectQuery);) {

				JSONArray jsonArray = new JSONArray();

				while (rs.next()) {

					JSONObject jsonObject = new JSONObject();
					jsonObject.put("sNo", rs.getString("s_no"));
					jsonObject.put("firstName", rs.getString("first_name"));
					jsonObject.put("lastName", rs.getString("last_name"));
					jsonObject.put("email", rs.getString("email"));
					jsonObject.put("dob", rs.getDate("dob"));
					jsonObject.put("gender", rs.getString("gender"));
					jsonObject.put("phoneNumber", rs.getString("phone_number"));
					jsonObject.put("password", rs.getString("password"));
					jsonObject.put("createdBy", rs.getString("created_by"));
					jsonObject.put("createdDate", rs.getDate("created_date"));

					jsonArray.add(jsonObject);

				}
				rps.setData("Fetch Created");
				rps.setjData(jsonArray);
				rps.setResponseCode(200);
				rps.setResponseMessage("Fetch sucess");

			}

			catch (Exception e) {
				e.printStackTrace();

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return rps;
	}

	@SuppressWarnings("unchecked")
	public Response getOneUser(String sNo) {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			String selectQuary = "select * from user_details where s_no='" + sNo + "'";
			JSONObject jsonObject = new JSONObject();
			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(selectQuary)){
				while (rs.next()) {

					jsonObject.put("sNo", rs.getString("s_no"));
					jsonObject.put("firstName", rs.getString("first_name"));
					jsonObject.put("lastName", rs.getString("last_name"));
					jsonObject.put("email", rs.getString("email"));
					jsonObject.put("dob", rs.getDate("dob"));
					jsonObject.put("gender", rs.getString("gender"));
					jsonObject.put("phoneNumber", rs.getString("phone_number"));
					jsonObject.put("password", rs.getString("password"));
					jsonObject.put("createdBy", rs.getString("created_by"));
					jsonObject.put("createdDate", rs.getDate("created_date"));
				}
				rps.setData("Fetch Created");
				rps.setjData(jsonObject);
				rps.setResponseCode(200);
				rps.setResponseMessage("Fetch sucess");

			} catch (Exception e) {
				e.printStackTrace();
				rps.setData("Fetch error");
				rps.setjData(jsonObject);
				rps.setResponseCode(500);
				rps.setResponseMessage("Fetch failer");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			rps.setData("Driver error");
			rps.setResponseCode(500);
			rps.setResponseMessage("Driver failer");
		}
		return rps;
	}

	public Response getUpdate(String email,String sNo) {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();){
				
				String updateQueary = "update user_details set email= '" + email + "' where s_no ='"+sNo+"'";
				
				st.execute(updateQueary); 
				
				rps.setData("Fetch Created");
				rps.setResponseCode(200);
				rps.setResponseMessage("Email Fetch sucess");

			} catch (Exception e) {
				e.printStackTrace();
				rps.setData("Fetch error");
				rps.setResponseCode(500);
				rps.setResponseMessage("Email Fetch failer");

			}

		} catch (Exception e) {
			e.printStackTrace();
			rps.setData("Email Driver error");
			rps.setResponseCode(500);
			rps.setResponseMessage("Driver failer");
		}
		return rps;

	}

	public Response deleteUser(String sNo) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			try(Connection conn =DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();
					) {
				
				String deleteQuery ="delete from user_details where s_no ='"+sNo+"'";
				
				st.executeUpdate(deleteQuery);
				
				
				rps.setData("User deleted successfullu!");
				rps.setResponseCode(200);
				rps.setResponseMessage("Email Fetch sucess");
				
				
				
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
				rps.setData("Cannot delete User");
				rps.setResponseCode(500);
				rps.setResponseMessage("Email Fetch failer");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			rps.setData("Email Driver error");
			rps.setResponseCode(500);
			rps.setResponseMessage("Driver failer");
		}
		
		
		return rps;
	}
	
	

}
