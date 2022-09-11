package com.example.elections.model;

import com.example.elections.model.common.JPA;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station extends JPA {

    @Column
    private Integer number;

    @ManyToOne(fetch = FetchType.EAGER)
    private ElectionProcess electionProcess;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Voter> voters = new ArrayList<>();

}
