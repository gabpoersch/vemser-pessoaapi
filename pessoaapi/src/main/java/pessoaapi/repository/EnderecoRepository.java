package pessoaapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pessoaapi.entity.Endereco;
import pessoaapi.exceptions.RegraDeNegocioException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class EnderecoRepository {

    @Autowired
    PessoaRepository pessoaRepository;

    List<Endereco> listaEnderecos = new ArrayList<>();
    AtomicInteger COUNTER = new AtomicInteger();

    public EnderecoRepository(){
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 1, "99001", "11"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 2, "99002", "22"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 3, "99003", "33"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 4, "99004", "44"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 5, "99005", "55"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 6, "99006", "66"));
    }

    public Endereco create(Endereco endereco, Integer id) throws RegraDeNegocioException {
        endereco.setIdEndereco(COUNTER.incrementAndGet());
        endereco.setIdPessoa(id);
        if(pessoaRepository.getById(endereco.getIdPessoa()) == null){
            throw new RegraDeNegocioException("Pessoa não encontrada!");
        }
        listaEnderecos.add(endereco);
        return endereco;
    }

    public void delete(Integer id) throws Exception {
        Endereco enderecoRecuperado = listaEnderecos.stream()
                .filter(endereco -> endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        listaEnderecos.remove(enderecoRecuperado);
    }

    public List<Endereco> list(){
        return listaEnderecos;
    }

    public Endereco getByIdEndereco(Integer id) throws Exception {
        return listaEnderecos.stream()
                .filter(endereco -> endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(Exception::new);
    }

    public List<Endereco> getByIdPessoa(Integer id) throws Exception {
        return listaEnderecos.stream()
                .filter(endereco -> endereco.getIdPessoa().equals(id))
                .collect(Collectors.toList());
    }

    public Endereco update(Endereco novoEndereco, Integer id) throws Exception {
        Endereco enderecoRecuperado = listaEnderecos.stream()
                .filter(endereco -> endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        enderecoRecuperado.setIdPessoa(novoEndereco.getIdPessoa());
        enderecoRecuperado.setNumero(novoEndereco.getNumero());
        enderecoRecuperado.setCep(novoEndereco.getCep());
        return enderecoRecuperado;
    }
}