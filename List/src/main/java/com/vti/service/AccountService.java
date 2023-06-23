package com.vti.service;

import com.vti.entity.Account;
import com.vti.filter.AccountForm.AccountFilterForm;
import com.vti.filter.AccountForm.CreateAccountFilterForm;
import com.vti.filter.AccountForm.UpdateAccountFilterForm;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.specification.AccountSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountService implements IAccountService{
    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IAccountRepository accountRepository;
    @Override
    public Page<Account> getAllAccounts(AccountFilterForm form, Pageable pageable) {
        Specification<Account> specification = AccountSpecification.buildWhere(form);
        return accountRepository.findAll(specification, pageable);
    }

    @Override
    public Account getAccountById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account findByUserName(String userName) {
        return accountRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public void createAccount(CreateAccountFilterForm form) throws Exception {
        //convert từ CreateAccountFilterForm -> Account
        TypeMap<CreateAccountFilterForm, Account> typeMap = modelMapper.getTypeMap(CreateAccountFilterForm.class, Account.class);
        if (typeMap == null){
            modelMapper.addMappings(new PropertyMap<CreateAccountFilterForm, Account>() {
                @Override
                protected void configure() {
                    skip(destination.getId());// không convert trường getID -> tránh gây lỗi hiểu nhầm thành update id của account sang id của department
                }
            });
        }
        Account account = modelMapper.map(form, Account.class);
        account.setDepartment(departmentRepository.findById(form.getDepartmentId()));
        accountRepository.save(account);

    }

    @Override
    @Transactional
    public void updateAccount(UpdateAccountFilterForm form) {
        Account account = modelMapper.map(form, Account.class);
        account.setPassword( new BCryptPasswordEncoder().encode(form.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public boolean isAccountExistsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public boolean isAccountExistsByUserName(String userName) {
        return accountRepository.existsByUserName(userName);
    }

    @Override
    public boolean isAccountExistsById(Integer id) {
        return accountRepository.existsById(id);
    }

    @Override
    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserName(username);
        if (account == null){
            throw new UsernameNotFoundException(username);
        }
        return new User(username, account.getPassword(), AuthorityUtils.createAuthorityList(account.getRole().toString()));
    }
}
