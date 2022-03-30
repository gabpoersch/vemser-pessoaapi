package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.entity.PessoaEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultasController {
    private final PessoaRepository pessoaRepository;

    @GetMapping("/listar-pessoas-contatos-comercial")
    public List<PessoaEntity> listarPessoasComContato() throws RegraDeNegocioException {
        return pessoaRepository.findContatosIgual1NativeQuery();
    }

    @GetMapping("/listar-pessoas-por-nome-query-nativa")
    public List<PessoaEntity> listarPessoasComContato(String nome) throws RegraDeNegocioException {
        return pessoaRepository.findByNomeSQLNativo(nome);
    }
}
