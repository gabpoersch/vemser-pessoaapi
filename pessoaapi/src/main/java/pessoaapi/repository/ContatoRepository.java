package pessoaapi.repository;

import org.springframework.stereotype.Repository;
import pessoaapi.entity.Contato;
import pessoaapi.exceptions.RegraDeNegocioException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ContatoRepository {

    private final List<Contato> listaContatos = new ArrayList<>();
    private final AtomicInteger COUNTER = new AtomicInteger();

    public ContatoRepository(){
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 1, "51 99991"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 2, "51 99992"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 3, "51 99993"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 4, "51 99994"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 5, "51 99995"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 6, "51 99996"));
    }

    public Contato create(Integer id, Contato contato){
        contato.setIdContato(COUNTER.incrementAndGet());
        contato.setIdPessoa(id);
        listaContatos.add(contato);
        return contato;
    }

    public List<Contato> list(){
        return listaContatos;
    }

    public Contato update(Integer id, Contato contatoUpdate) throws Exception {
        Contato contatoRecuperado = listaContatos.stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato inexistente."));
        contatoRecuperado.setIdPessoa(contatoUpdate.getIdPessoa());
        contatoRecuperado.setNumero(contatoUpdate.getNumero());
        return contatoRecuperado;
    }

    public void delete(Integer id) throws Exception {
        Contato contatoRecuperado = listaContatos.stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato inexistente."));
        listaContatos.remove(contatoRecuperado);
    }

    public List<Contato> findContatoByIdPessoa(Integer id){
        return listaContatos.stream()
                .filter(contato -> contato.getIdPessoa().equals(id))
                .collect(Collectors.toList());
    }

    public Contato findContatoById(Integer id) throws Exception {
        return listaContatos.stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato inexistente."));
    }
}