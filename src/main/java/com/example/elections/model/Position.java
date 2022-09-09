package com.example.elections.model;

import com.example.elections.model.common.JPA;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "positions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Position extends JPA {

    @Column
    private String name;

    @Column
    private Integer sets;

    @ManyToOne(fetch = FetchType.EAGER)
    private ElectionProcess electionProcess;

}
