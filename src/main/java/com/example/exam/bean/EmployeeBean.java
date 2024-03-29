package com.example.exam.bean;

import com.example.exam.dao.EmployeeDaoImpl;
import com.example.exam.dao.ProjectDaoImpl;
import com.example.exam.model.Employee;
import com.example.exam.model.Project;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.model.DataModel;
import jakarta.faces.model.ListDataModel;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class EmployeeBean implements Serializable {
    private List<Employee> allEmployees;
    private List<Project> allProjects;

    private Employee selectedEmployee;
    private Project selectedProject;
    private EmployeeDaoImpl uDao = new EmployeeDaoImpl();
    private ProjectDaoImpl pDao = new ProjectDaoImpl();
    private Employee newemployee = new Employee();
    private DataModel employees;

    public EmployeeBean() {
    }

    public DataModel getEmployees() {
        if (employees == null) {
            employees = new ListDataModel();
            employees.setWrappedData(uDao.selectAll());
        }
        return employees;
    }

    public String createEmployee() {
        uDao.insert(newemployee);
        newemployee = new Employee();
        employees.setWrappedData(uDao.selectAll());
        return "tableau";
    }

    public String delete() {
        Employee p = (Employee) employees.getRowData();
        uDao.delete(p);
        employees.setWrappedData(uDao.selectAll());
        return null;
    }

    public List<Employee> getAllEmployees() {
        if (allEmployees == null) {
            allEmployees = uDao.selectAll(); // Implémentez cette méthode dans votre DAO
        }
        return allEmployees;
    }

    public void setAllEmployees(List<Employee> allEmployees) {
        this.allEmployees = allEmployees;
    }

    public List<Project> getAllProjects() {
        if (allProjects == null) {
            allProjects = pDao.selectAll(); // Implémentez cette méthode dans votre DAO
        }
        return allProjects;
    }

    public void setAllProjects(List<Project> allProjects) {
        this.allProjects = allProjects;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public EmployeeDaoImpl getuDao() {
        return uDao;
    }

    public void setuDao(EmployeeDaoImpl uDao) {
        this.uDao = uDao;
    }

    public Employee getNewemployee() {
        return newemployee;
    }

    public void setNewemployee(Employee newemployee) {
        this.newemployee = newemployee;
    }

    public void setEmployees(DataModel employees) {
        this.employees = employees;
    }

    public String assignProject() {
        if (selectedEmployee != null && selectedProject != null) {
            return "success";
        } else {
            return "error";
        }
    }
}
