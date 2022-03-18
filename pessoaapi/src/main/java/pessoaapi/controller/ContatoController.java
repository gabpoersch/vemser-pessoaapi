package pessoaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pessoaapi.dto.ContatoCreateDTO;
import pessoaapi.dto.ContatoDTO;
import pessoaapi.exceptions.RegraDeNegocioException;
import pessoaapi.service.ContatoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contato")
@Validated
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public ResponseEntity<List<ContatoDTO>> list(){
        return new ResponseEntity<>(contatoService.listarContatos(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{idContato}")
    public ResponseEntity<ContatoDTO> getById(@PathVariable("idContato") Integer id) throws Exception {
        return new ResponseEntity<>(contatoService.findById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/byIdPessoa/{idPessoa}")
    public ResponseEntity<List<ContatoDTO>> getByIdPessoa(@PathVariable("idPessoa") Integer id) {
        return new ResponseEntity<>(contatoService.getByIdPessoa(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/{idPessoa}")
    public ResponseEntity<ContatoDTO> create(@PathVariable("idPessoa") Integer id,
                                             @Valid @RequestBody ContatoCreateDTO contato) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.criarContato(id, contato), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idContato}")
    public void delete(@PathVariable("idContato") Integer id) throws Exception {
        contatoService.deletarContatos(id);
    }

    @PutMapping("/{idContato}")
    public ResponseEntity<ContatoDTO> update(@PathVariable("idContato") Integer id,
                          @Valid @RequestBody ContatoCreateDTO contatoAtualizar) throws Exception {
        return new ResponseEntity<>(contatoService.atualizarContato(id, contatoAtualizar), HttpStatus.ACCEPTED);
    }
}