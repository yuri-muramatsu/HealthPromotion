package com.example.healthpromotion.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BodyMeasurementForm {
    @NotNull(message = "身長を入力してください。")
    @Min(value = 100, message = "身長はcmで入力してください。")
    private Integer heightCM;

    @NotNull(message = "体重を入力してください。")
    @Min(value = 1, message = "体重は実際の数字を入力してください。")
    private Integer weightKG;
}
