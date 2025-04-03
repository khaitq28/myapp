package com.tqk.myapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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

