package com.example.elections.rest.dtos;

import com.example.elections.model.Position;
import com.example.elections.model.Vote;
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
public class VotePositionDto {

    @NotNull
    private Position position;

    @NotNull
    private Vote vote;

}
