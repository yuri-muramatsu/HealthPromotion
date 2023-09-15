package com.example.healthpromotion.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BodyMeasurementForm {
	@NotNull(message = "身長を入力してください。")
	@Min(value = 50, message = "身長はcmで入力してください。")
	private Float heightCM;

	@NotNull(message = "体重を入力してください。")
	@Min(value = 1, message = "体重は実際の数字を入力してください。")
	private Float weightKG;

	@NotNull(message = "身体活動レベルを選択してください。")
	private Float pla;
}
