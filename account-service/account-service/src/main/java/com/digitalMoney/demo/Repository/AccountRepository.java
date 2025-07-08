package com.digitalMoney.demo.Repository;

import com.digitalMoney.demo.model.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AccountRepository extends JpaRepository  <Account,Long>{
    @Schema(
            description = "Check if an account exists by userId",
            example = "true or false")
    boolean  existsByUserId(String userId);

    @Schema(
            description = "Find an account by userId",
            example = "Optional<Account>")
    Optional <Account> findByUserId(String userId);

    @Schema(
            description = "Find an account by cvu or alias",
            example = "Optional<Account>")
    Optional<Account> findByCvuOrAlias(String cvu , String alias);
}
