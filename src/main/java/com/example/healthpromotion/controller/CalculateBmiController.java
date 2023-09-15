package com.example.healthpromotion.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.healthpromotion.form.BodyMeasurementForm;
import com.example.healthpromotion.service.CalculateBmi;

import jakarta.servlet.http.HttpSession;

@Controller
public class CalculateBmiController {
	private final CalculateBmi calculateBmi;
	private final HttpSession session;

	public CalculateBmiController(final CalculateBmi calculateBmi, final HttpSession session) {
		this.calculateBmi = calculateBmi;
		this.session = session;
	}

	@GetMapping("/bodymeasurement")
	public String bodymeasurement(final Model model) {
		model.addAttribute("bodyMeasurementForm", new BodyMeasurementForm());
		return "auth/bodymeasurement";
	}

	@PostMapping("/bodymeasurement")
	public String colculateBmi(@ModelAttribute @Validated final BodyMeasurementForm bodyMeasurementForm,
			final BindingResult bindingResult,
			@RequestParam(name = "weightKG", required = true) final float weightKG,
			@RequestParam(name = "heightCM", required = true) final float heightCM,
			final RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException {
		if (bindingResult.hasErrors()) {
			return "auth/bodymeasurement";
		}
		// BMI計算
		final float weightkg = bodyMeasurementForm.getWeightKG();
		final float heightcm = bodyMeasurementForm.getHeightCM();
		final float activeLevel = bodyMeasurementForm.getPla();
		// sessionで性別、生年の取得
		final String genderStr = (String) session.getAttribute("gender");
		final int birthYearInt = (int) session.getAttribute("birthYear");

		redirectAttributes.addFlashAttribute("calculateBmiResult",
				"BMI：" + calculateBmi.calculateBmi(weightkg, heightcm));
		redirectAttributes.addFlashAttribute("calculateStandardWeightResult",
				"適正体重：" + calculateBmi.calculateStandardWeight(heightcm) + "kg");
		redirectAttributes.addFlashAttribute("differenceStandardWeightResult",
				"適正体重との差：" + calculateBmi.differenceStandardWeight(weightkg, heightcm) + "kg");
		redirectAttributes.addFlashAttribute("eerResult",
				"推定エネルギー必要量：" + calculateBmi.calculateEER(birthYearInt, activeLevel, genderStr) + "kcal");

		session.setAttribute("weightKG", weightkg);
		return "redirect:/bmiresult";
	}

	@GetMapping("/bmiresult")
	public String bmiresult() {
		return "/bmiresult";
	}
}