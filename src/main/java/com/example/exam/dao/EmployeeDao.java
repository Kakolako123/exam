package com.example.exam.dao;


import com.example.exam.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> selectAll();
    Employee insert(Employee employee);
    void delete(Employee employee);
    Employee update(Employee employee);
    Employee findById(int id);
}
