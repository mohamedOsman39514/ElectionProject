package com.example.elections.model;

import com.example.elections.model.common.JPA;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "photo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photo extends JPA {

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private byte[] image;

    @OneToOne
    private Candidate candidate;

}
