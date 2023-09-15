package com.example.healthpromotion.service;

import org.springframework.stereotype.Service;

@Service
public class ColculateCalorie {
	//1日に減らすべきカロリーの計算
	public int calculateReduceCalorie(final int periodday, final int reduceweightkg) {
		final int reduceCalorieInt = (reduceweightkg * 7200) / periodday;
		return reduceCalorieInt;
	}

	//1日に増やすべきカロリーの計算
	public int calculateGeinCalorie(final int periodday, final int geinweightkg) {
		final int geinCalorieInt = (geinweightkg * 7200) / periodday;
		return geinCalorieInt;
	}

	// ごはんの適切量

	// ウォーキングの消費カロリーの計算(消費カロリー(kcal) ＝ メッツ * 体重kg * 運動時間 * 1.05)
	public float calculateWalkingCalorie(final float weightkg, final int reduceCalorie) {
		final float walkingtime = (float) (((3 * weightkg * 1.05) / reduceCalorie) * 60);
		return walkingtime;
	}
	// ランニングの消費カロリーの計算
	// ごはんの減らす量
}
