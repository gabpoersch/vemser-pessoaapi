package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.entity.PessoaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {
    Optional<PessoaEntity> findByNome(String nome);

    List<PessoaEntity> findByNomeContainingIgnoreCase(String nome);

    List<PessoaEntity> findByNomeOrDataNascimento(String nome, LocalDate dataNascimento);

    //    @Query("select .... from PESSOA p where p.nome = ?1")
    //    List<PessoaEntity> findByNomeJPQL(String nome, String sobrenome);

    @Query("select p " +
            " from PESSOA p " +
            "where p.nome = :nome")
    List<PessoaEntity> findByNomeJPQL(String nome);

    @Query(value="SELECT * FROM Pessoa WHERE nome = ?1", nativeQuery = true)
    List<PessoaEntity> findByNomeSQLNativo(String nome);

    @Query("       select p " +
            "        from PESSOA p " +
            "  join fetch p.contatos c " +
            "       where c.tipoContato = 1")
    List<PessoaEntity> findContatosIgual1Bolacha();

    @Query(value=" SELECT * " +
            "        FROM Pessoa p " +
            "  INNER JOIN CONTATO c on (c.id_pessoa = p.id_pessoa) " +
            "       WHERE c.tipo = 1", nativeQuery = true)
    List<PessoaEntity> findContatosIgual1NativeQuery();

    @Query(value = "select p from PESSOA p where upper(p.nome) like upper(:nome)")
    Page<PessoaEntity> findByNamePaged(String nome, Pageable pageable);
}
