package com.example.elections.model;


import com.example.elections.model.common.JPA;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "election_process")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectionProcess extends JPA {

    @Column
    private String name;

    @Column
    private LocalDate startAt;

    @Column
    private LocalDate endAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private Party party;

}
