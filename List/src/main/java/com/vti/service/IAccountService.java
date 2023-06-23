package com.vti.service;

import com.vti.entity.Account;
import com.vti.filter.AccountForm.AccountFilterForm;
import com.vti.filter.AccountForm.CreateAccountFilterForm;
import com.vti.filter.AccountForm.UpdateAccountFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAccountService extends UserDetailsService {
    Page<Account> getAllAccounts(AccountFilterForm form, Pageable pageable);
    Account getAccountById(int id);
    Account findByUserName(String userName);
    void createAccount(CreateAccountFilterForm form) throws Exception;
    void updateAccount(UpdateAccountFilterForm form);
    boolean isAccountExistsByEmail(String value);

    boolean isAccountExistsByUserName(String userName);

    boolean isAccountExistsById(Integer id);
    void deleteAccount(int id);

}
