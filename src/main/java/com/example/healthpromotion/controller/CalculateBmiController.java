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
public String colculateBmi(@ModelAttribute @Validated BodyMeasurementForm bodyMeasurementForm, BindingResult bindingResult,
        @RequestParam(name = "weightKG", required = false) final Integer weightKG, @RequestParam(name = "heightCM", required = false) final Integer heightCM, RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException {
    if (bindingResult.hasErrors()) {
        return "auth/bodymeasurement";
    }
    // BMI計算
    int weightkg = bodyMeasurementForm.getWeightKG();
    int heightcm = bodyMeasurementForm.getHeightCM();
    int bmi1 = weightkg / ((heightcm * heightcm)/10000);
    Integer bmi2 = Integer.valueOf(bmi1);
    String bmi3 = bmi2.toString();
    redirectAttributes.addFlashAttribute("caluculateBmiResult", "あなたのBMIは" + bmi3 +"です。");

    return "redirect:/./bmiresult";
    }

@GetMapping("/bmiresult")
public String bmiresult() {
    return "/bmiresult";
    }
}