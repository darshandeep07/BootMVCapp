package com.cg.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.model.Employee;
import com.cg.repository.UserDao;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index() {
		return "Welcome";
	}

	// Create
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestParam(value = "name") String name,
			@RequestParam(value = "phonenumber") Integer phonenumber) {
		Employee employee = null;
		try {
			employee = new Employee(phonenumber, name);
			userDao.save(employee);
		} catch (Exception ex) {
			return "Error creating the user: " + ex.toString();
		}
		return "User succesfully created! (id = " + employee.getId() + ")";
	}

	// Read
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public List<Employee> getUsers() {
		List<Employee> employee = new ArrayList<>();
		try {
			Iterator<Employee> itr = userDao.findAll().iterator();
			while (itr.hasNext()) {
				employee.add(itr.next());
			}

		} catch (Exception ex) {
			return employee;
		}
		return employee;
	}

	@RequestMapping(value="/get-by-name",method=RequestMethod.GET)
	public String getByName(@RequestParam(value = "name") String name) {
		String userId, phoneNumber;
		try {
			Employee employee = userDao.findByName(name);
			userId = String.valueOf(employee.getId());
			phoneNumber = String.valueOf(employee.getPhonenumber());
		} catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + userId + " and the phone number is " + phoneNumber;
	}

	@RequestMapping(value="getAll",method=RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAll() {
		List<Employee> userList = new ArrayList<>();
		try {
			Iterator<Employee> itr = userDao.findAll().iterator();
			while (itr.hasNext()) {
				userList.add(itr.next());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Employee>>(userList, HttpStatus.OK);
	}

	// Update
	@RequestMapping(value="update/{id}",method=RequestMethod.PUT)
	public String updateUser(@PathVariable("id") long id, @RequestParam(value = "name") String name,
			@RequestParam(value = "phonenumber") Integer phonenumber) {
		try {
			Employee employee = userDao.findOne(id);
			employee.setName(name);
			employee.setPhonenumber(phonenumber);
			userDao.save(employee);
		} catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}

	// Delete
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") long id) {
		try {
			Employee employee = new Employee(id);
			userDao.delete(employee);
		} catch (Exception ex) {
			return "Error deleting the user: " + ex.toString();
		}
		return "User succesfully deleted!";
	}
}
