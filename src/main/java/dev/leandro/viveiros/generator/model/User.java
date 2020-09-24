package dev.leandro.viveiros.generator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "usuario")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "cpf", nullable = false, updatable = false)
    private String cpf;


}
