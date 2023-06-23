package com.vti.controller;

import com.vti.DTO.AccountDTO;
import com.vti.Validate.IdExists;
import com.vti.entity.Account;
import com.vti.filter.AccountForm.AccountFilterForm;
import com.vti.filter.AccountForm.CreateAccountFilterForm;
import com.vti.filter.AccountForm.UpdateAccountFilterForm;
import com.vti.service.EmailForgotPassword;
import com.vti.service.IAccountService;
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
@RequestMapping(value = "/api/v1/accounts")
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IAccountService accountService;

    @Autowired
    private EmailForgotPassword emailForgotPassword;
    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<Page<AccountDTO>> getAllAccounts(AccountFilterForm form, Pageable pageable) {
        Page<Account> pages = accountService.getAllAccounts(form, pageable);
        List<AccountDTO> accountDTOS = modelMapper.map(pages.getContent(), new TypeToken<List<AccountDTO>>() {}.getType());
        for (AccountDTO accountDTO: accountDTOS){
            accountDTO.add(linkTo(methodOn(AccountController.class).getAccountById(accountDTO.getId())).withSelfRel());
        };
        return new ResponseEntity<>(new PageImpl<>(accountDTOS, pageable, pages.getTotalElements()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable(name = "id") @IdExists int id) {
        return new ResponseEntity<>(modelMapper.map(accountService.getAccountById(id), AccountDTO.class),HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> createAccount(@RequestBody @Valid CreateAccountFilterForm form) throws Exception {
        accountService.createAccount(form);
        return new ResponseEntity<>("Tạo mới thành công", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateAccount(@PathVariable(name = "id") @IdExists int id, @RequestBody @Valid UpdateAccountFilterForm form) {
        form.setId(id);
        accountService.updateAccount(form);
        return new ResponseEntity<>("Update thành công", HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> deleteAccount(@PathVariable(name = "id") @IdExists int id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);

    }

    // ham gui mail: gui mail, dong thoi se tao token, và thoi gian token
    @PostMapping(value = "/sendMail")
    public ResponseEntity<?> sendMail(@RequestParam String email){
        emailForgotPassword.linktoChangePassword(email);
        return new ResponseEntity<>("gui mail thanh cong", HttpStatus.OK);
    }

    @PutMapping(value = "/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestParam String newPassword, @RequestParam String token) throws Exception {
        emailForgotPassword.changePassword(newPassword, token);
        return new ResponseEntity<>("thay doi password thanh cong", HttpStatus.OK);
    }



}
