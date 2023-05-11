create database mercado;

create table cliente(
    id int not null auto_increment primary key,
    nome varchar(40),
    celular int,
    desconto boolean
)

SELECT * FROM cliente;

INSERT INTO cliente (nome, celular, desconto)
VALUES ('João', 99999999, true);

INSERT INTO cliente (nome, celular, desconto)
VALUES ('Maria', 88888888, false);

INSERT INTO cliente (nome, celular, desconto)
VALUES ('Pedro', 77777777, true);

INSERT INTO cliente (nome, celular, desconto)
VALUES ('Ana', 66666666, false);

INSERT INTO cliente (nome, celular, desconto)
VALUES ('Lucas', 55555555, true);
