package pessoaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pessoaapi.dto.EnderecoCreateDTO;
import pessoaapi.dto.EnderecoDTO;
import pessoaapi.entity.Endereco;
import pessoaapi.exceptions.RegraDeNegocioException;
import pessoaapi.repository.EnderecoRepository;
import pessoaapi.repository.PessoaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    ObjectMapper objectMapper;

    public EnderecoDTO criarEndereco(Integer id, EnderecoCreateDTO enderecoCriar) throws Exception {
        if(pessoaRepository.getById(id) == null){
            throw new RegraDeNegocioException("Pessoa não encontrada!");
        }
        log.info("Chamou criar endereço");
        Endereco endereco = objectMapper.convertValue(enderecoCriar, Endereco.class);
        return objectMapper.convertValue(enderecoRepository.create(endereco, id), EnderecoDTO.class);
    }

    public void deletarEndereco(Integer id) throws Exception {
        log.info("Chamou deletar endereço");
        enderecoRepository.delete(id);
    }

    public EnderecoDTO getByIdEndereco(Integer id) throws Exception {
        log.info("Chamou achar endereço por ID");
        return objectMapper.convertValue(enderecoRepository.getByIdEndereco(id), EnderecoDTO.class);
    }

    public List<EnderecoDTO> getByIdPessoa(Integer id) throws Exception {
        if(pessoaRepository.getById(id) == null){
            throw new RegraDeNegocioException("Pessoa não encontrada!");
        }
        log.info("Chamou achar endereço por ID PESSOA");
        return enderecoRepository.getByIdPessoa(id).stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public List<EnderecoDTO> listarEnderecos(){
        log.info("Chamou listar endereços");
        return enderecoRepository.list().stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO editarEndereco(Integer id, EnderecoCreateDTO novoEndereco) throws Exception {
        if(pessoaRepository.getById(novoEndereco.getIdPessoa()) == null){
            throw new RegraDeNegocioException("Pessoa não encontrada!");
        }
        log.info("Chamou editar endereço");
        Endereco endereco = objectMapper.convertValue(novoEndereco, Endereco.class);
        return objectMapper.convertValue(enderecoRepository.update(endereco, id), EnderecoDTO.class);
    }
}