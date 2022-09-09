package com.example.elections.rest.dtos;

import com.example.elections.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class VoterDto {

    @NotEmpty(message = "please enter your name")
    private String name;

    @Email( message = "not valid email")
    @NotEmpty(message = "please enter email")
    private String email;

    @Size(min = 8, message = "password at least 8 character")
    @NotEmpty(message = "please enter password")
    private String password;

    @Size(min = 8, message = "national id must 14 digit")
    @NotEmpty(message = "please enter your national id")
    private String nationalId;

    private Boolean voterVote = false;

    private List<Role> roles = new ArrayList<>();

}
