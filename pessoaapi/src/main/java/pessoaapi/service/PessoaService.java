package pessoaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pessoaapi.dto.PessoaCreateDTO;
import pessoaapi.dto.PessoaDTO;
import pessoaapi.entity.Pessoa;
import pessoaapi.repository.PessoaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmailService emailService;

    public PessoaDTO create(PessoaCreateDTO pessoaCreate) throws Exception {
        log.info("Chamou criar pessoa");
        Pessoa pessoa = objectMapper.convertValue(pessoaCreate, Pessoa.class);
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaRepository.create(pessoa), PessoaDTO.class);
        emailService.sendEmailPessoa(pessoaDTO);
        return pessoaDTO;
    }

    public List<PessoaDTO> list(){
        log.info("Chamou listar pessoa");
        return pessoaRepository.list().stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .collect(Collectors.toList());
    }

    public PessoaDTO update(Integer id, PessoaCreateDTO pessoaAtualizar) throws Exception {
        log.info("Chamou atualizar pessoa");
        Pessoa pessoa = objectMapper.convertValue(pessoaAtualizar, Pessoa.class);
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaRepository.update(id, pessoa), PessoaDTO.class);
        emailService.sendEmailUpdatePessoa(pessoaDTO);
        return pessoaDTO;
    }

    public void delete(Integer id) throws Exception {
        log.info("Chamou deletar pessoa");
        pessoaRepository.list().stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id))
                .findFirst()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class)).ifPresent(pessoaDTO -> {
                            emailService.sendEmailDeletePessoa(pessoaDTO);
                }
                );
        pessoaRepository.delete(id);
    }

    public List<PessoaDTO> listByName(String nome) {
        log.info("Chamou listar pessoa por nome");
        return pessoaRepository.listByName(nome).stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .collect(Collectors.toList());
    }
}