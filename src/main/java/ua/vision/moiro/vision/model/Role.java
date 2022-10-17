package ua.vision.moiro.vision.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
}
