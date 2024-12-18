package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

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
           
        if(accountRepository.findByUsername(account.getUsername()) != null){
            return null;
        }

          return accountRepository.save(account);
          
     }
     
    /*
     * The login will be successful if and only if: 
     * the username and password provided in the request body JSON match a real account existing on the database.
     */
    public Account accountLogin(String username){
      Account existingAccount = accountRepository.findByUsername(username);
      // if(existingAccount != null){
       Account account = existingAccount;
      accountRepository.save(account);
      //     return account;
      // }
      return account;
    
 
     }
    
}
