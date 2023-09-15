package com.example.healthpromotion.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GeinWeightForm {
	@NotNull(message = "期間を選択してください。")
	private Integer period;

	@NotNull(message = "減量値を入力してください。")
	@Min(value = 1, message = "数値は正の整数で入力してください。")
	private Integer geinweightKG;
}
