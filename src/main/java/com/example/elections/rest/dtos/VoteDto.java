package com.example.elections.rest.dtos;

import com.example.elections.model.ElectionProcess;
import com.example.elections.model.Station;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto {

    private Boolean revocation = false;

    @NotNull
    private Station station;

    @NotNull
    private ElectionProcess electionProcess;

}
