package com.example.healthpromotion.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.healthpromotion.entity.Accounts;
import com.example.healthpromotion.form.LoginForm;
import com.example.healthpromotion.form.SignupForm;
import com.example.healthpromotion.repository.AccountsRepository;
import com.example.healthpromotion.service.AccountsService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
	private final HttpSession session;
	private final AccountsService accountsService;
	private final AccountsRepository accountsRepository;

	public AuthController(final AccountsService accountsService, final AccountsRepository accountsRepository,
			final HttpSession session) {
		this.session = session;
		this.accountsService = accountsService;
		this.accountsRepository = accountsRepository;
	}

	@GetMapping("/login")
	public String login(final Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "auth/login";
	}

	@GetMapping("/signup")
	public String signup(final Model model) {
		model.addAttribute("signupForm", new SignupForm());
		return "auth/signup";
	}

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute @Validated final SignupForm signupForm, final BindingResult bindingResult,
			final RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException {
		// メールアドレスが登録済みでないかの確認
		if (accountsService.isEmailRegistered(signupForm.getEmail())) {
			final FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email",
					"ユーザー名、またはパスワードに誤りがあります。");
			bindingResult.addError(fieldError);
		}
		// パスワードと確認用パスワードの入力値の確認
		/*if (!accountsService.isSamePassword(signupForm.getPassword(), signupForm.getPasswordConfirmation())) {
		    final FieldError fieldError = new FieldError(bindingResult.getObjectName(), "password", "入力に誤りがあります。");
		    bindingResult.addError(fieldError);
		}*/
		if (bindingResult.hasErrors()) {
			return "auth/signup";
		}
		accountsService.create(signupForm);
		redirectAttributes.addFlashAttribute("successMassage", "会員登録が完了しました。");
		redirectAttributes.addFlashAttribute("memberName", signupForm.getName());
		final int birthYear = signupForm.getBirthYear();
		final String gender = signupForm.getGender();
		// sessionに取得情報を入れる
		session.setAttribute("gender", gender);
		session.setAttribute("birthYear", birthYear);
		session.setAttribute("generalId", "true");

		return "redirect:/bodymeasurement";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute @Validated final LoginForm loginForm, final BindingResult bindingResult,
			final RedirectAttributes redirectAttributes,
			@RequestParam(name = "email", required = false) final String email,
			@RequestParam(name = "password", required = false) final String password) throws NoSuchAlgorithmException {
		final Accounts accounts = accountsRepository.findByEmail(email);
		// 入力されたメールアドレスが登録されていない場合
		if (accounts == null) {
			final FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "入力に誤りがあります。");
			bindingResult.addError(fieldError);
		}
		//パスワードのハッシュ化と16進数への変換
		final MessageDigest digest = MessageDigest.getInstance("MD5");
		final byte[] hashedPw = digest.digest(password.getBytes());
		final String hashedPassword = String.format("%040x", new BigInteger(1, hashedPw));

		// データベースのハッシュ済みパスワードと照合
		if (accounts != null) {
			if (hashedPassword.equals(accounts.getPassword())) {
				// データベースから生年と性別を取得
				final int birthYear = accounts.getBirthYear();
				final String gender = accounts.getGender();
				// sessionに取得情報を入れる
				session.setAttribute("gender", gender);
				session.setAttribute("birthYear", birthYear);
				session.setAttribute("generalId", "true");
				redirectAttributes.addFlashAttribute("memberName", accounts.getName());
				return "redirect:/bodymeasurement";
			} else {
				final FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "ログインに失敗しました。");
				bindingResult.addError(fieldError);
			}
		} else {
			final FieldError fieldError = new FieldError(bindingResult.getObjectName(), "password", "入力に誤りがあります。");
			bindingResult.addError(fieldError);
		}
		return "auth/login";
	}
}
