package com.example.elections.model;

import com.example.elections.model.common.JPA;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "voter")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voter extends JPA{

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Integer national_id;

    @Column
    private Boolean voter_vote = false;

    @Column
    private Boolean revocation = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id")
    private List<Role> roles = new ArrayList<>();

}
