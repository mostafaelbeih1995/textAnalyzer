package com.Dedalus.TextAnalyzer.model.payload;

import com.Dedalus.TextAnalyzer.model.enums.AnalysisMode;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnalyzeTextRequest {

    @NotNull
    private String text;
    @NotNull
    private AnalysisMode type;
}
