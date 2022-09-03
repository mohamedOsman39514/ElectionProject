package com.example.elections.rest.dtos;

import com.example.elections.model.ElectionProcess;
import com.example.elections.model.Position;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto {

    @NotEmpty
    @Size(min = 2, message = "candidate name should have at least 2 characters")
    private String name;

    @NotEmpty
    private String nickname;

    @NotEmpty
    @Size(max = 14, message = "candidate national id should have at least 14 digit")
    private Integer national_id;

    @NotEmpty
    private Integer number;

    @NotEmpty
    private LocalDate date;

    @NotEmpty
    private Position position;

    @NotEmpty
    private ElectionProcess electionProcess;

}
