package com.example.elections.model;

import com.example.elections.model.common.JPA;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vote_candidate")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteCandidate  extends JPA{

    @Column
    private Boolean revocation = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    private Vote vote;

    @ManyToOne(fetch = FetchType.EAGER)
    private Candidate candidate;

}
