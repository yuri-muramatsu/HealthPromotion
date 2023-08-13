package com.example.healthpromotion.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.healthpromotion.form.BodyMeasurementForm;

@Controller
public class CalculateBmiController {

	@PostMapping("/bodymeasurement")
	public String colculateBmi(@ModelAttribute @Validated final BodyMeasurementForm bodyMeasurementForm,
			final BindingResult bindingResult,
			@RequestParam(name = "weightKG", required = true) final int weightKG,
			@RequestParam(name = "heightCM", required = true) final int heightCM,
			final RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException {
		if (bindingResult.hasErrors()) {
			return "auth/bodymeasurement";
		}
		// BMI計算
		final int weightkg = bodyMeasurementForm.getWeightKG();
		final int heightcm = bodyMeasurementForm.getHeightCM();
		final int bmi1 = weightkg / ((heightcm * heightcm) / 10000);
		final String bmi3 = String.valueOf(bmi1);
		redirectAttributes.addFlashAttribute("calculateBmiResult", "あなたのBMIは" + bmi3 + "です。");

		return "redirect:/bmiresult";
	}

	@GetMapping("/bmiresult")
	public String bmiresult() {
		return "/bmiresult";
	}

}