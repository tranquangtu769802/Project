package com.vti.service;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.filter.DepartmentForm.CreateDepartmentFilterForm;
import com.vti.filter.DepartmentForm.DepartmentFilterForm;
import com.vti.filter.DepartmentForm.UpdateDepartmentFilterForm;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.specification.DepartmentSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
public class DepartmentService implements IDepartmentService{
    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;


//    @Override
//    public Page<Department> getAllDepartments(DepartmentFilterForm form, Pageable pageable) {
//        Specification<Department> specification = DepartmentSpecification.buildWhere(form);
//        return departmentRepository.findAll(specification, pageable);
//    }

    @Override
    public List<Department> getAllDepartments(DepartmentFilterForm form) {
        Specification<Department> specification = DepartmentSpecification.buildWhere(form);
        return departmentRepository.findAll(specification);
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    @Transactional
    public void createDepartment(CreateDepartmentFilterForm form) {
        //createDepartment
        Department department = departmentRepository.save(modelMapper.map(form, Department.class));
        //createAccount
        List<Account> accounts = new ArrayList<>();
        List<CreateDepartmentFilterForm.AccountForm> accountsF = form.getAccounts();
        // convert từ accountForm >> Account
        accountsF.forEach(accountF -> {
            Account account = modelMapper.map(accountF, Account.class);
            account.setDepartment(department);
            account.setDepartment(departmentRepository.findById(accountF.getDepartmentId()));
            accounts.add(account);
        });
        accountRepository.saveAll(accounts);
    }

    @Override
    @Transactional
    public void updateDepartment(UpdateDepartmentFilterForm form) {
        departmentRepository.save(modelMapper.map(form, Department.class));
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }
}
