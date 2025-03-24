package com.example.User_Service.User_Service.UserAccountPackage.Model;

import lombok.Data;

@Data
public class Account {
       private Long id;
       private String userId;
       private Long balance;
       private String cvu;
       private String alias;
       private String name;
}
