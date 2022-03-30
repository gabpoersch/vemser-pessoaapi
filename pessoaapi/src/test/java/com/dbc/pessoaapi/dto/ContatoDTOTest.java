package com.dbc.pessoaapi.dto;

import com.dbc.pessoaapi.entity.ContatoEntity;
import com.dbc.pessoaapi.entity.TipoContato;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ContatoDTOTest {
    @Test
    void test(){

        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato(TipoContato.COMERCIAL);

        ContatoEntity contatoEntity1 = new ObjectMapper().convertValue(contatoDTO, ContatoEntity.class);



        ContatoEntity contatoEntity = new ContatoEntity();
        contatoEntity.setTipoContato(TipoContato.COMERCIAL);
    }
}