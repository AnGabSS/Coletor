CREATE TABLE tb_caminhao (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             placa VARCHAR(7) UNIQUE NOT NULL,
                             capacidade INT NOT NULL,
                             motorista VARCHAR(100)
);

CREATE TABLE tb_rota (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         descricao VARCHAR(100),
                         distancia_km INT
);

CREATE TABLE tb_coleta (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           hora TIMESTAMP NOT NULL,
                           caminhao_id BIGINT,
                           rota_id BIGINT,
                           FOREIGN KEY (caminhao_id) REFERENCES tb_caminhao(id),
                           FOREIGN KEY (rota_id) REFERENCES tb_rota(id)
);

CREATE TABLE tb_morador (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nome VARCHAR(50) NOT NULL,
                            endereco VARCHAR(100) NOT NULL,
                            telefone VARCHAR(11),
                            email VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE tb_users (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(50) NOT NULL,
                          password VARCHAR(50) NOT NULL,
                          role VARCHAR(50) DEFAULT 'USER'
);
