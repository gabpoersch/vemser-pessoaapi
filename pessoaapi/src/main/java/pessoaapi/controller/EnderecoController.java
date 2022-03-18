package pessoaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pessoaapi.dto.EnderecoCreateDTO;
import pessoaapi.dto.EnderecoDTO;
import pessoaapi.service.EnderecoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/endereco")
@Validated
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> list(){
        return new ResponseEntity<>(enderecoService.listarEnderecos(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> getEndereco(@PathVariable("idEndereco") Integer id) throws Exception {
        return new ResponseEntity<>(enderecoService.getByIdEndereco(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{idPessoa}/pessoa")
    public ResponseEntity<List<EnderecoDTO>> getEnderecoByPessoa(@PathVariable("idPessoa") Integer id) throws Exception {
        return new ResponseEntity<>(enderecoService.getByIdPessoa(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/{idPessoa}")
    public ResponseEntity<EnderecoDTO> postEndereco(@PathVariable("idPessoa") Integer id,
                                 @Valid @RequestBody EnderecoCreateDTO endereco) throws Exception {
        return new ResponseEntity<>(enderecoService.criarEndereco(id, endereco), HttpStatus.CREATED);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> updateEndereco(@PathVariable("idEndereco") Integer id,
                                   @Valid @RequestBody EnderecoCreateDTO endereco) throws Exception {
        return new ResponseEntity<>(enderecoService.editarEndereco(id, endereco), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{idEndereco}")
    public void delete(@PathVariable("idEndereco") Integer id) throws Exception {
        enderecoService.deletarEndereco(id);
    }
}
