package com.example.healthpromotion.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class CalculateBmi {

	// BMI計算
	public float calculateBmi(final float weightkg, final float heightcm) {
		final float bmiInt = (weightkg * 10000) / (heightcm * heightcm);

		return bmiInt;
	}

	// 適正体重の計算
	public float calculateStandardWeight(final float heightcm) {
		final float standardWeight = ((heightcm * heightcm) * 22) / 10000;

		return standardWeight;
	}

	// 適正体重との差
	public float differenceStandardWeight(final float weightkg, final float heightcm) {
		final float difference = weightkg - (((heightcm * heightcm) * 22) / 10000);

		return difference;
	}

	// 推定エネルギー必要量(EER)の計算
	public float calculateEER(final int birthYearInt, final float activeLevel, final String genderStr) {
		// 年齢計算
		final Calendar c = Calendar.getInstance();
		final Date now = new Date();
		c.setTime(now);
		final int currentYear = c.get(Calendar.YEAR);
		final int age = currentYear - birthYearInt;
		// 年齢と性別から推定エネルギー必要量(EER)を計算
		// 男性
		if (age <= 2 && genderStr.equals("male")) {
			final int bmr = 700;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 2 && age <= 5 && genderStr.equals("male")) {
			final int bmr = 900;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 5 && age <= 7 && genderStr.equals("male")) {
			final int bmr = 980;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 7 && age <= 9 && genderStr.equals("male")) {
			final int bmr = 1140;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 9 && age <= 11 && genderStr.equals("male")) {
			final int bmr = 1330;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 11 && age <= 14 && genderStr.equals("male")) {
			final int bmr = 1520;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 14 && age <= 17 && genderStr.equals("male")) {
			final int bmr = 1610;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 17 && age <= 29 && genderStr.equals("male")) {
			final int bmr = 1530;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 29 && age <= 49 && genderStr.equals("male")) {
			final int bmr = 1530;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 49 && age <= 64 && genderStr.equals("male")) {
			final int bmr = 1480;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 64 && age <= 74 && genderStr.equals("male")) {
			final int bmr = 1400;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 74 && genderStr.equals("male")) {
			final int bmr = 1280;
			final float eer = bmr * activeLevel;
			return eer;
			// 女性
		} else if (age <= 2 && genderStr.equals("female")) {
			final int bmr = 660;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 2 && age <= 5 && genderStr.equals("female")) {
			final int bmr = 840;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 5 && age <= 7 && genderStr.equals("female")) {
			final int bmr = 920;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 7 && age <= 9 && genderStr.equals("female")) {
			final int bmr = 1050;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 9 && age <= 11 && genderStr.equals("female")) {
			final int bmr = 1260;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 11 && age <= 14 && genderStr.equals("female")) {
			final int bmr = 1410;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 14 && age <= 17 && genderStr.equals("female")) {
			final int bmr = 1310;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 17 && age <= 29 && genderStr.equals("female")) {
			final int bmr = 1110;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 29 && age <= 49 && genderStr.equals("female")) {
			final int bmr = 1160;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 49 && age <= 64 && genderStr.equals("female")) {
			final int bmr = 1110;
			final float eer = bmr * activeLevel;
			return eer;
		} else if (age > 64 && age <= 74 && genderStr.equals("female")) {
			final int bmr = 1080;
			final float eer = bmr * activeLevel;
			return eer;
		} else {
			final int bmr = 1010;
			final float eer = bmr * activeLevel;
			return eer;
		}
	}

}
