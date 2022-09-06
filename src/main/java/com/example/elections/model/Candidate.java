package com.example.elections.model;

import com.example.elections.model.common.JPA;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "candidates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidate extends JPA {

    @Column
    private String name;

    @Column
    private String nickname;

    @Column
    private Integer national_id;

    @Column
    private Integer number;

    @Column
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    private ElectionProcess electionProcess;

}
