delete
from membros;

delete
from projeto;

delete
from pessoa;

ALTER SEQUENCE pessoa_id_seq RESTART WITH 1;

ALTER SEQUENCE projeto_id_seq RESTART WITH 1;

INSERT INTO pessoa (nome, datanascimento, cpf, funcionario)
VALUES ('João da Silva', '1996-03-27', '02305841872', true);

INSERT INTO pessoa (nome, datanascimento, cpf, funcionario)
VALUES ('Maria da Silva', '1996-03-27', '02405841872', false);

INSERT INTO projeto (nome, data_inicio, data_previsao_fim, descricao, status, orcamento, risco, idgerente)
VALUES ('Hotelaria', '2021-10-05', '2022-06-08', 'Projeto de automação da rede hoteleira', 'EM_ANALISE', '50000.00', 'MEDIO_RISCO', 1);

INSERT INTO membros (idprojeto, idpessoa)
VALUES (1, 1);

