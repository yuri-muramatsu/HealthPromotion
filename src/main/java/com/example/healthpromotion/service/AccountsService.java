package com.example.healthpromotion.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.healthpromotion.entity.Accounts;
import com.example.healthpromotion.entity.Role;
import com.example.healthpromotion.form.SignupForm;
import com.example.healthpromotion.repository.AccountsRepository;
import com.example.healthpromotion.repository.RoleRepository;

@Service
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final RoleRepository roleRepository;

    public AccountsService(AccountsRepository accountsRepository, RoleRepository roleRepository) {
        this.accountsRepository = accountsRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Accounts create(SignupForm signupForm) throws NoSuchAlgorithmException {
        Accounts accounts = new Accounts();
        Role role = roleRepository.findByName("ROLE_GENERAL");
        //パスワードのハッシュ化と16進数への変換
        String originalPassword = signupForm.getPassword();
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hashedPw = digest.digest(originalPassword.getBytes());
        String hashedPassword = String.format("%040x", new BigInteger(1, hashedPw));

        accounts.setName(signupForm.getName());
        accounts.setGender(signupForm.getGender());
        accounts.setBirthYear(signupForm.getBirthYear());
        accounts.setEmail(signupForm.getEmail());
        accounts.setPassword(hashedPassword);
        accounts.setRole(role);
        accounts.setEnabled(true);

        return accountsRepository.save(accounts);
    }

    // メールアドレスが登録済みかをチェックする。
    public boolean isEmailRegistered(String email) {
        Accounts accounts = accountsRepository.findByEmail(email);
        return accounts != null;
    }

    // パスワードと確認用パスワードの入力値が一致するかどうか
    public boolean isSamePassword(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }

    public String findPassword(String email) {
        Accounts accounts = accountsRepository.findByEmail(email);
        return accounts.getPassword();
    }
}
