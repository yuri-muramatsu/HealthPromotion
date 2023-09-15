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
import com.example.healthpromotion.form.GeinWeightForm;
import com.example.healthpromotion.form.ReduceWeightForm;
import com.example.healthpromotion.service.ColculateCalorie;

import jakarta.servlet.http.HttpSession;

@Controller
public class GoalController {
	private final ColculateCalorie colculateCalorie;
	private final HttpSession session;

	public GoalController(final ColculateCalorie colculateCalorie, final HttpSession session) {
		this.colculateCalorie = colculateCalorie;
		this.session = session;
	}

	@GetMapping("/geinweight")
	public String geinWeight(final Model model) {
		model.addAttribute("geinWeightForm", new GeinWeightForm());
		return "goal/geinweight";
	}

	@GetMapping("/keepweight")
	public String keepWeight() {
		return "goal/keepweight";
	}

	@GetMapping("/reduceweight")
	public String reduceWeight(final Model model) {
		model.addAttribute("reduceWeightForm", new ReduceWeightForm());
		return "goal/reduceweight";
	}

	@PostMapping("/reduceweight")
	public String calculateReduceCalorie(@ModelAttribute @Validated final ReduceWeightForm reduceWeightForm,
			final BodyMeasurementForm bodyMeasurementForm,
			final BindingResult bindingResult,
			@RequestParam(name = "period", required = true) final int period,
			@RequestParam(name = "reduceweightKG", required = true) final int reduceweightKG,
			final RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException {
		if (bindingResult.hasErrors()) {
			return "goal/reduceweight";
		}
		//１日あたり、減らすカロリーの計算
		final int periodday = reduceWeightForm.getPeriod();
		final int reduceweightkg = reduceWeightForm.getReduceweightKG();

		redirectAttributes.addFlashAttribute("calculateReduceCalorieResult",
				"１日あたり、減らすカロリーは" + colculateCalorie.calculateReduceCalorie(periodday, reduceweightkg) + "kcalです。");

		// 体重の取得
		final float weightkg = (float) session.getAttribute("weightKG");
		//final float weightkg = bodyMeasurementForm.getWeightKG();
		// 消費カロリーの取得
		final int reduceCalorie = colculateCalorie.calculateReduceCalorie(periodday, reduceweightkg);

		redirectAttributes.addFlashAttribute("calculateWalkingCalorieResult",
				"ウォーキングでは" + colculateCalorie.calculateWalkingCalorie(weightkg, reduceCalorie) + "分間です。" + weightkg
						+ reduceCalorie);

		return "redirect:/reducecalorieresult";
	}

	@GetMapping("/reducecalorieresult")
	public String reducecalorieresult() {
		return "goal/reducecalorieresult";
	}

	@PostMapping("/geinweight")
	public String calculateGeinCalorie(@ModelAttribute @Validated final GeinWeightForm geinWeightForm,
			final BindingResult bindingResult,
			@RequestParam(name = "period", required = true) final int period,
			@RequestParam(name = "geinweightKG", required = true) final int geinweightKG,
			final RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException {
		if (bindingResult.hasErrors()) {
			return "goal/geinweight";
		}
		//１日あたり、増やすカロリーの計算
		final int periodday = geinWeightForm.getPeriod();
		final int geinweightkg = geinWeightForm.getGeinweightKG();

		redirectAttributes.addFlashAttribute("calculateGeinCalorieResult",
				"１日あたり、増やすカロリーは" + colculateCalorie.calculateGeinCalorie(periodday, geinweightkg) + "kcalです。");

		return "redirect:/geincalorieresult";
	}

	@GetMapping("/geincalorieresult")
	public String geincalorieresult() {
		return "goal/geincalorieresult";
	}
}
