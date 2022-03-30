package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.entity.PessoaEntity;
import com.dbc.pessoaapi.repository.PessoaRepository;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/paginacao")
@RequiredArgsConstructor
public class PaginacaoController {
    private final PessoaRepository pessoaRepository;

    @GetMapping("/find-all")
    public Page<PessoaEntity> findAllPaginado(Integer paginaSolicitada, Integer tamanhoDaPagina){
        Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoDaPagina);
        Page<PessoaEntity> all = pessoaRepository.findAll(pageable);
        return all;
    }

    @GetMapping("/find-all-ordenado-cpf")
    public Page<PessoaEntity> findAllPaginadoEOrdenadoCPF(Integer paginaSolicitada, Integer tamanhoDaPagina){
        Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoDaPagina, Sort.by("cpf").descending().and(Sort.by("nome")));
        Page<PessoaEntity> all = pessoaRepository.findAll(pageable);
        return all;
    }

    @GetMapping("/find-by-name")
    public Page<PessoaEntity> findByNamePaginado(Integer paginaSolicitada, Integer tamanhoDaPagina, String nome){
        Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoDaPagina);
        Page<PessoaEntity> all = pessoaRepository.findByNamePaged(nome.toUpperCase(Locale.ROOT), pageable);
        return all;
    }


}
