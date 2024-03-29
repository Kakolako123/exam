package com.example.exam.dao;

import com.example.exam.model.Employee;
import com.example.exam.model.Project;

import java.util.List;

public interface ProjectDao {
    List<Project> selectAll();
    Project insert(Project project);
    void delete(Project project);
    Project update(Project project);
    Project findById(int id);
}
