package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.model.Employee;



@Repository
public interface UserDao extends JpaRepository<Employee, Long> {

	public Employee findByName(String name);
}
