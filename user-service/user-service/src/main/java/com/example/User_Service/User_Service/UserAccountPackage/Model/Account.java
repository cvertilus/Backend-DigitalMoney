package com.example.User_Service.User_Service.UserAccountPackage.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        description = "Model representing a user's account in the system. " +
                "It includes details such as userId, balance, cvu, alias, and name."
)
public class Account {
       private Long accountId;
       private String userId;
       private Long balance;
       private String cvu;
       private String alias;
       private String name;
}
