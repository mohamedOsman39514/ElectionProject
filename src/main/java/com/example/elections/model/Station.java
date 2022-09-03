package com.example.elections.model;

import com.example.elections.model.common.JPA;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "stations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station extends JPA {

    @Column(name = "station_number")
    private Integer number;

    @ManyToOne(fetch = FetchType.EAGER)
    private ElectionProcess electionProcess;

    @ManyToOne(fetch = FetchType.EAGER)
    private Voter voter;

}
