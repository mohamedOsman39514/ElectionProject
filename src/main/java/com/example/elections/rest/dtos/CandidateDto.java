package com.example.elections.rest.dtos;

import com.example.elections.model.ElectionProcess;
import com.example.elections.model.Position;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto {

    @NotEmpty
    @Size(min = 2, message = "candidate name should have at least 2 characters")
    private String name;

    @NotEmpty(message = "must enter nickname")
    private String nickname;

    @NotEmpty(message = "must enter national id")
    @Size(max = 14, min = 14, message = "candidate national id should have at least 14 digit")
    private String nationalId;

    @NotNull(message = "must enter number")
    private Integer number;

    @NotNull(message = "enter position")
    private Position position;

    @NotNull(message = "enter election process")
    private ElectionProcess electionProcess;

}
