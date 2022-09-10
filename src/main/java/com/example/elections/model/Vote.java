package com.example.elections.model;

import com.example.elections.model.common.JPA;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vote")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends JPA {

    @Column
    private Boolean revocation = false;

    @ManyToOne(fetch =  FetchType.EAGER)
    private Station station;

    @ManyToOne(fetch = FetchType.EAGER)
    private ElectionProcess electionProcess;

}
