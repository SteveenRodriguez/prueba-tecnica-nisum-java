package com.nisum.apiusers.entities.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneDto {
    private String number;
    private int cityCode;
    private int countryCode;
}
