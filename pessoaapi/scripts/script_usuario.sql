CREATE TABLE USUARIO(
    ID_USUARIO NUMBER NOT NULL,
    LOGIN varchar2(50) UNIQUE NOT NULL,
    SENHA varchar2(50) NOT NULL,
    PRIMARY KEY(ID_USUARIO)
);

CREATE SEQUENCE seq_usuario
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

insert into usuario (id_usuario, login, senha)
     values (seq_usuario.nextval, 'maicon', '123');
