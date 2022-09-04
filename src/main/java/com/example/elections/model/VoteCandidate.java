package com.example.elections.model;

import com.example.elections.model.common.JPA;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "vote_candidate")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteCandidate  extends JPA{

    @ManyToOne(fetch = FetchType.EAGER)
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    private Vote vote;

    @ManyToOne(fetch = FetchType.EAGER)
    private Candidate candidate;

}
