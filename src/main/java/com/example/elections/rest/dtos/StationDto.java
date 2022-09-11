package com.example.elections.rest.dtos;

import com.example.elections.model.ElectionProcess;
import com.example.elections.model.Voter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class StationDto {

    @NotNull
    private Integer number;

    @NotNull
    private ElectionProcess electionProcess;

    @NotNull
    private List<Voter> voters = new ArrayList<>();
}
