package com.cadastro.pessoas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_infor")
public class PersonModel extends RepresentationModel<PersonModel> implements Serializable {

    @Serial
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 3)
    private int age;

    @Column(nullable = false)
    @NotNull(message = "Saldo must not be null")
    private BigDecimal saldo;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false)
    private LocalDateTime dateCreated;
}
