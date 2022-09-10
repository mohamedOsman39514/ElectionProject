package com.example.elections.model;

import com.example.elections.model.common.JPA;
import lombok.*;

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

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column(unique = true,length = 14)
    private String nationalId;

    @Column
    private Boolean voterVote = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id")
    private List<Role> roles = new ArrayList<>();
//    private List<Role> roles = Arrays.asList(new Role(null,"user"));

}
