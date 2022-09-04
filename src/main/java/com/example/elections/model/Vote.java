package com.example.elections.model;

import com.example.elections.model.common.JPA;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne(fetch =  FetchType.EAGER)
    private Station station;

    @ManyToOne(fetch = FetchType.EAGER)
    private ElectionProcess electionProcess;

}
