package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Account;

@Service
public class AccountService {

    /*
     * The registration will be successful if and only if: 
     * the username is not blank, 
     * the password is at least 4 characters long, 
     * and an Account with that username does not already exist.
     */
    public Account registerAccount(Account account){
        if (account.getPassword().length() < 4 || account.getUsername().isBlank()){
            return null;
         }
     
        Account a  = registerAccount(account);
         if(a.getAccountId() == account.getAccountId()){
             return null;
         }

         Account registerAccount = registerAccount(account);
          return registerAccount;
     }
     
    /*
     * The login will be successful if and only if: 
     * the username and password provided in the request body JSON match a real account existing on the database.
     */
    public Account accountLogin(Account account){
        Account login = accountLogin(account);
            if (login != null){
                return login;
            }
        return null;
    
 
     }
    
}
