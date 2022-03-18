package pessoaapi.repository;

import org.springframework.stereotype.Repository;
import pessoaapi.entity.Pessoa;
import pessoaapi.exceptions.RegraDeNegocioException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class PessoaRepository {
    private static final List<Pessoa> listaPessoas = new ArrayList<>();
    private final AtomicInteger COUNTER = new AtomicInteger();

    public PessoaRepository() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        listaPessoas.add(new Pessoa(COUNTER.incrementAndGet(), "Maicon", LocalDate.parse("10/10/1990", formatter), "12345678910", "gabrielpoersch@gmail.com"));
        listaPessoas.add(new Pessoa(COUNTER.incrementAndGet(), "Charles", LocalDate.parse("08/05/1985", formatter), "12345678911", "gabrielpoersch@gmail.com"));
        listaPessoas.add(new Pessoa(COUNTER.incrementAndGet(), "Marina", LocalDate.parse("30/03/1970", formatter), "12345678912", "gabrielpoersch@gmail.com"));
        listaPessoas.add(new Pessoa(COUNTER.incrementAndGet(), "Rafael", LocalDate.parse("01/07/1990", formatter), "12345678916", "gabrielpoersch@gmail.com"));
        listaPessoas.add(new Pessoa(COUNTER.incrementAndGet(), "Ana", LocalDate.parse("01/07/1990", formatter), "12345678917", "gabrielpoersch@gmail.com"));
        listaPessoas.add(new Pessoa(COUNTER.incrementAndGet(), "Tiago", LocalDate.parse("06/12/1994", formatter), "12345678918", "gabrielpoersch@gmail.com"));
    }

    public Pessoa create(Pessoa pessoa) {
        pessoa.setIdPessoa(COUNTER.incrementAndGet());
        listaPessoas.add(pessoa);
        return pessoa;
    }

    public List<Pessoa> list() {
        return listaPessoas;
    }

    public Pessoa update(Integer id,
                         Pessoa pessoaAtualizar) throws Exception {
        Pessoa pessoaRecuperada = listaPessoas.stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id)).findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada"));
        pessoaRecuperada.setCpf(pessoaAtualizar.getCpf());
        pessoaRecuperada.setNome(pessoaAtualizar.getNome());
        pessoaRecuperada.setDataNascimento(pessoaAtualizar.getDataNascimento());
        return pessoaRecuperada;
    }

    public void delete(Integer id) throws Exception {
        Pessoa pessoaRecuperada = listaPessoas.stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id)).findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada"));
        listaPessoas.remove(pessoaRecuperada);
    }

    public List<Pessoa> listByName(String nome) {
        return listaPessoas.stream()
                .filter(pessoa -> pessoa.getNome().toUpperCase().contains(nome.toUpperCase()))
                .collect(Collectors.toList());
    }

    public Pessoa getById(Integer id){
        return listaPessoas.stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id))
                .findFirst()
                .orElse(null);
    }
}
