CREATE TABLE membros
(
    idprojeto bigint    NOT NULL,
    idpessoa  bigint    NOT NULL,
    CONSTRAINT pk_membros_projeto PRIMARY KEY (idprojeto, idpessoa),
    CONSTRAINT fk_projeto FOREIGN KEY (idprojeto)
        REFERENCES projeto (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_pessoa FOREIGN KEY (idpessoa)
        REFERENCES pessoa (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);