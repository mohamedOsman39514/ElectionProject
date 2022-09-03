package com.example.elections.model;

import com.example.elections.model.common.JPA;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vote")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends JPA {

    @Column
    private LocalDate date;

    @ManyToOne(fetch =  FetchType.EAGER)
    private Station station;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private ElectionProcess electionProcess;


}
