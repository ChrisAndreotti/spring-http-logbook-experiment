package com.candreotti.demoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HelloResponseDto {

    private String message;

    private List<Integer> luckyNumbers;
}
