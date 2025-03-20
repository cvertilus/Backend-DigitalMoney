package com.digitalMoney.demo.Repository;

import com.digitalMoney.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AccountRepository extends JpaRepository  <Account,Long>{

    boolean  existsByUserId(String userId);
    Optional <Account> findByUserId(String userId);
}
