DROP TABLE IF EXISTS itemVenda;
DROP TABLE IF EXISTS venda;
DROP TABLE IF EXISTS estoque;
DROP TABLE IF EXISTS produto;
DROP TABLE IF EXISTS formaPagamento;
DROP TABLE IF EXISTS vendedor;
DROP TABLE IF EXISTS fabricante;
DROP TABLE IF EXISTS categoria;
DROP TABLE IF EXISTS cliente;

-- SELECT * FROM cliente;

CREATE TABLE cliente (
idCliente INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100),
endereco VARCHAR(50),
telefone INT ,
isTorcedorFlamengo BOOLEAN,
isFanOnePiece BOOLEAN,
isSouza BOOLEAN
);

CREATE TABLE categoria (
idCategoria INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100)
);

CREATE TABLE fabricante (
idFabricante INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100),
endereco VARCHAR(100)
);

CREATE TABLE vendedor (
idVendedor INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100)
);

CREATE TABLE formaPagamento (
idFormaPagamento INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100)
);

CREATE TABLE produto (
idProduto INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100),
preco DECIMAL(10,2),
idCategoria INT,
idFabricante INT,
FOREIGN KEY (idCategoria) REFERENCES categoria(idCategoria),
FOREIGN KEY (idFabricante) REFERENCES fabricante(idFabricante)
);

CREATE TABLE estoque (
idProduto INT,
quantidade INT,
FOREIGN KEY (idProduto) REFERENCES produto(idProduto)
);

CREATE TABLE venda (
idVenda INT PRIMARY KEY,
idCliente INT,
idVendedor INT,
idFormaPagamento INT,
dataVenda DATE,
FOREIGN KEY (idCliente) REFERENCES cliente(idCliente),
FOREIGN KEY (idVendedor) REFERENCES vendedor(idVendedor),
FOREIGN KEY (idFormaPagamento) REFERENCES formaPagamento(idFormaPagamento)
);

CREATE TABLE itemVenda (
idItemVenda INT AUTO_INCREMENT PRIMARY KEY,
idVenda INT,
idProduto INT,
quantidade INT,
FOREIGN KEY (idVenda) REFERENCES venda(idVenda),
FOREIGN KEY (idProduto) REFERENCES produto(idProduto)
);