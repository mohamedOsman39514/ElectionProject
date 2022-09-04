package com.example.elections.rest.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class VoterDto {

    @NotEmpty
    @Size(min = 2, message = "voter name should have at least 2 characters")
    private String name;

    @NotEmpty
    @Email(message = "email required")
    @UniqueElements
    private String email;

    @NotEmpty
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;

    @NotEmpty
    private Integer national_id;

    @NotEmpty
    private Boolean voter_vote = false;

}
