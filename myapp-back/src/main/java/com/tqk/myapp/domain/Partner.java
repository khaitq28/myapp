package com.tqk.myapp.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partner {

    private Long id;

    @NotBlank
    private String alias;

    @NotBlank
    private String type;

    private Direction direction;

    private String application;

    private ProcessedFlowType processedFlowType;

    @NotBlank
    private String description;
}

