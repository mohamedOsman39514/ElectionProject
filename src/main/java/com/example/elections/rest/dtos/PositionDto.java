package com.example.elections.rest.dtos;

import com.example.elections.model.ElectionProcess;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private Integer sets;

    @NotEmpty
    private ElectionProcess electionProcess;


}
