package com.vti.controller;


import com.vti.DTO.LogInfoDTO;
import com.vti.entity.Account;
import com.vti.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/login")
    public LogInfoDTO login(Principal principal){

        String userName = principal.getName();
        Account account = accountService.findByUserName(userName);
        return modelMapper.map(account, LogInfoDTO.class);

    }
}
