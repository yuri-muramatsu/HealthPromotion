package com.example.healthpromotion.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {
    @NotBlank(message = "メールアドレスを入力してください。")
    @Email(message = "メールアドレスは正しい形式で入力してください。")
    private String email;

    @NotBlank(message = "パスワードを入力してください。")
    @Length(min = 8, message = "パスワードは８文字以上で入力してください。")
    private String password;
}
