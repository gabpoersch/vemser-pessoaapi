package com.dbc.pessoaapi.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PessoaTest {

    @Test
    public void testLombok() {
        PessoaEntity pessoaEntity = new PessoaEntity();
        pessoaEntity.setIdPessoa(1);
        pessoaEntity.setNome("OI");
        pessoaEntity.setDataNascimento(LocalDate.of(1991, 10, 10));
        pessoaEntity.setCpf("132456789");
        System.out.println(pessoaEntity.toString());

        PessoaEntity pessoaEntity1 = PessoaEntity.builder()
                .idPessoa(1)
                .cpf("teste")
                .dataNascimento(LocalDate.of(1991, 10, 10))
                .nome("Maicon")
                .build();

        var pessoaX = PessoaEntity.builder()
                .idPessoa(1)
                .cpf("teste")
                .dataNascimento(LocalDate.of(1991, 10, 10))
                .nome("Maicon")
                .build();
    }
}