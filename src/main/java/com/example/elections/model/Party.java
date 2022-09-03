package com.example.elections.model;

import com.example.elections.model.common.JPA;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "party")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Party extends JPA {

    @Column
    private String name;

}
