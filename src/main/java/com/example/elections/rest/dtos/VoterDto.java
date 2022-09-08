package com.example.elections.rest.dtos;

import com.example.elections.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class VoterDto {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;

    @NotNull
    private Integer national_id;

    @NotNull
    private Boolean voter_vote = false;



    private List<Role> roles = new ArrayList<>();

}
