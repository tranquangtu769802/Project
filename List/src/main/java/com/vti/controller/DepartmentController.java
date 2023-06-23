package com.vti.controller;

import com.vti.DTO.DepartmentDTO;
import com.vti.Validate.IdExists;
import com.vti.entity.Department;
import com.vti.filter.DepartmentForm.CreateDepartmentFilterForm;
import com.vti.filter.DepartmentForm.DepartmentFilterForm;
import com.vti.filter.DepartmentForm.UpdateDepartmentFilterForm;
import com.vti.service.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/departments")
@CrossOrigin("*")
public class DepartmentController{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IDepartmentService departmentService;

//    @GetMapping
//    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
//    public Page<DepartmentDTO> getAllDepartments(DepartmentFilterForm form, Pageable pageable) {
//        Page<Department> page = departmentService.getAllDepartments(form, pageable);
//        List<DepartmentDTO> departmentDTOS = modelMapper.map(page.getContent(), new TypeToken<List<DepartmentDTO>>() {
//        }.getType());
//
//        //add HATEOAS
//        departmentDTOS.forEach(departmentDTO -> {
//            departmentDTO.getAccounts().forEach(accountDTO -> {
//                accountDTO.add(linkTo(methodOn(AccountController.class).getAccountById(accountDTO.getId())).withSelfRel());
//            });
//            departmentDTO.add(linkTo(methodOn(DepartmentController.class).getDepartmentById(departmentDTO.getId())).withSelfRel());
//        });
//        return new PageImpl<>(departmentDTOS, pageable , page.getTotalElements());
//    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> getAllDepartments(DepartmentFilterForm form) {
        List<DepartmentDTO> ls = modelMapper.map(departmentService.getAllDepartments(form), new TypeToken<List<DepartmentDTO>>() {
        }.getType());
        return new ResponseEntity<>(ls, HttpStatus.OK);
}
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(modelMapper.map(departmentService.getDepartmentById(id), DepartmentDTO.class), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> createDepartment(@RequestBody @Valid CreateDepartmentFilterForm form) {
        departmentService.createDepartment(form);
        return new ResponseEntity<>("Tạo mới thành công", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateDepartment(@PathVariable(name = "id") @IdExists int id, @RequestBody @Valid UpdateDepartmentFilterForm form) {
        form.setId(id);
        departmentService.updateDepartment(form);
        return new ResponseEntity<>("Update thành công", HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> deleteDepartment(@PathVariable(name = "id") @IdExists int id){
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);

    }
}
