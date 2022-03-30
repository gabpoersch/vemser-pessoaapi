package com.dbc.pessoaapi.entity;

import com.dbc.pessoaapi.entity.pk.ProfessorPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name="PESSOA")
public class PessoaEntity {

    @Id
    @Column(name="id_pessoa")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_SEQ")
    @SequenceGenerator(name = "PESSOA_SEQ", sequenceName = "SEQ_PESSOA2", allocationSize = 1)
    private Integer idPessoa;

    @Column(name="nome")
    private String nome;

    @Column(name="data_nascimento")
    private LocalDate dataNascimento;

    @Column(name="cpF")
    private String cpf;

    @Column(name="email")
    private String email;

    @JsonIgnore
    // uma pessoa, muitos contatos
    @OneToMany(mappedBy="pessoaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContatoEntity> contatos;

    // muitas pessoas para muitos endereços
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Pessoa_X_Pessoa_Endereco",
            joinColumns = @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa"),
            inverseJoinColumns = @JoinColumn(name= "id_endereco", referencedColumnName = "id_endereco")
    )
    private Set<EnderecoEntity> enderecos;
}