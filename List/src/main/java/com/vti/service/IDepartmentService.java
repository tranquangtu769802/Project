package com.vti.service;

import com.vti.entity.Department;
import com.vti.filter.DepartmentForm.CreateDepartmentFilterForm;
import com.vti.filter.DepartmentForm.DepartmentFilterForm;
import com.vti.filter.DepartmentForm.UpdateDepartmentFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDepartmentService {
//    Page<Department> getAllDepartments(DepartmentFilterForm form, Pageable pageable);
    List<Department> getAllDepartments(DepartmentFilterForm form);
    Department getDepartmentById(int id);
    void createDepartment(CreateDepartmentFilterForm form);
    void updateDepartment(UpdateDepartmentFilterForm form);
    void deleteDepartment(int id);





}
