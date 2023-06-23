package com.vti.repository;

import com.vti.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
    Account findById(int id);
    Account findByUserName(String name);

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);

    Account findByEmail(String email);

    Account findByToken(String token);
}
