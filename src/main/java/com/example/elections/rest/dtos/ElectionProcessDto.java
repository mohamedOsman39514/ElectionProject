package com.example.elections.rest.dtos;

import com.example.elections.model.Party;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ElectionProcessDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private LocalDate startAt;

    @NotEmpty
    private LocalDate endAt;

    @NotEmpty
    private Party party;

}
