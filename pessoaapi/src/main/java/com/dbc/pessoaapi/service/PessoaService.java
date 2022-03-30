package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.dto.ContatoDTO;
import com.dbc.pessoaapi.dto.PessoaCreateDTO;
import com.dbc.pessoaapi.dto.PessoaDTO;
import com.dbc.pessoaapi.dto.PessoaDTOComContatos;
import com.dbc.pessoaapi.entity.PessoaEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;

    public PessoaDTO create(PessoaCreateDTO pessoa) throws Exception {
        PessoaEntity pessoaEntity = objectMapper.convertValue(pessoa, PessoaEntity.class);

        PessoaEntity pessoaEntity1 = pessoaRepository.save(pessoaEntity);
        PessoaDTO pessoaDTO = new PessoaDTO();

        pessoaDTO.setIdPessoa(pessoaEntity1.getIdPessoa());
        pessoaDTO.setCpf(pessoaEntity1.getCpf());
        pessoaDTO.setNome(pessoaEntity1.getNome());
        pessoaDTO.setDataNascimento(pessoaEntity1.getDataNascimento());
        pessoaDTO.setEmail(pessoaEntity1.getEmail());

        return pessoaDTO;
    }

    public List<PessoaDTO> list() {
        return pessoaRepository.findAll()
                .stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .collect(Collectors.toList());
    }

    public PessoaEntity findById(Integer idPessoa) throws RegraDeNegocioException {
        return this.pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada!"));
    }

    public List<PessoaDTOComContatos> listComContatos(Integer idPessoa) throws RegraDeNegocioException {
        List<PessoaDTOComContatos> pessoaDTOList = new ArrayList<>();
        if(idPessoa == null){
            pessoaDTOList.addAll(pessoaRepository.findAll().stream()
                    .map(pessoa -> {
                        PessoaDTOComContatos pessoaDTO = objectMapper.convertValue(pessoa, PessoaDTOComContatos.class);
                        pessoaDTO.setContatos(pessoa.getContatos().stream()
                                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                                .collect(Collectors.toList())
                        );

                        return pessoaDTO;
                    }).collect(Collectors.toList())
            );
        } else {
            PessoaEntity pessoaEntity = findById(idPessoa);
            PessoaDTOComContatos pessoaDTO = objectMapper.convertValue(pessoaEntity, PessoaDTOComContatos.class);
            pessoaDTO.setContatos(pessoaEntity.getContatos().stream()
                    .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                    .collect(Collectors.toList())
            );
            pessoaDTOList.add(pessoaDTO);
        }
        return pessoaDTOList;
    }

    public PessoaDTO update(Integer id,
                            PessoaCreateDTO pessoaAtualizar) throws Exception {
//        Optional<PessoaEntity> pessoa = pessoaRepository.findById(id);
//        if(pessoa.isPresent()){
//
//        }
        PessoaEntity pessoaEncontada = findById(id);
        pessoaEncontada.setCpf(pessoaAtualizar.getCpf());
        pessoaEncontada.setDataNascimento(pessoaAtualizar.getDataNascimento());
        pessoaEncontada.setEmail(pessoaAtualizar.getEmail());
        pessoaEncontada.setNome(pessoaAtualizar.getNome());
        PessoaEntity update = pessoaRepository.save(pessoaEncontada);
        PessoaDTO pessoaDTO = objectMapper.convertValue(update, PessoaDTO.class);
        return pessoaDTO;
    }

    public void delete(Integer id) throws Exception {
        pessoaRepository.deleteById(id);
    }

//    public List<PessoaDTO> listByName(String nome) {
//        return pessoaRepository.listByName(nome).stream()
//                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
//                .collect(Collectors.toList());
//    }
}
