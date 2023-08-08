package com.example.healthpromotion.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignupForm {
    @NotBlank(message = "ニックネームを入力してください。")
    private String name;

    @NotBlank(message = "性別を選択してください。")
    private String gender;

    @NotNull(message = "生まれ年を4桁で入力してください。")
    @Min(value = 1900, message = "生まれ年は4桁の半角数字で入力してください。")
    private Integer birthYear;

    @NotBlank(message = "メールアドレスを入力してください。")
    @Email(message = "メールアドレスは正しい形式で入力してください。")
    private String email;

    @NotBlank(message = "パスワードを入力してください。")
    @Length(min = 8, message = "パスワードは８文字以上で入力してください。")
    private String password;

    @NotBlank(message = "パスワード（確認用）を入力してください。")
    private String passwordConfirmation;
}
