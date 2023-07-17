package com.nisum.apiusers.entities.dto;

import com.nisum.apiusers.util.ErrorMessages;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
public class RequestDto {

    @NotBlank(message = ErrorMessages.BLANK_NAME)
    private String name;

    @Email(
            regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = ErrorMessages.REGEX_MAIL)
    @NotBlank(message = ErrorMessages.BLANK_MAIL)
    private String email;

    @NotBlank(message = ErrorMessages.BLANK_PASSWORD)
    private String password;

    @NotEmpty(message = ErrorMessages.EMPTY_PHONES)
    private List<PhoneDto> phones;
}
