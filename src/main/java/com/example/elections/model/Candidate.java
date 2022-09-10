package com.example.elections.model;

import com.example.elections.model.common.JPA;
import lombok.*;

import javax.persistence.*;

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

    @Column(unique = true, length = 14)
    private String nationalId;

    @Column(unique = true)
    private Integer number;

    @ManyToOne(fetch = FetchType.EAGER)
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    private ElectionProcess electionProcess;

}
