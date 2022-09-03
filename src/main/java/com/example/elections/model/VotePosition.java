package com.example.elections.model;

import com.example.elections.model.common.JPA;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "vote_position")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotePosition extends JPA {

    @ManyToOne(fetch = FetchType.LAZY)
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vote vote;

}
