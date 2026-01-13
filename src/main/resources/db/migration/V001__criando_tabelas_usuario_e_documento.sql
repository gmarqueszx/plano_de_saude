CREATE SEQUENCE seq_usario
    START WITH 1
    INCREMENT BY 50;

CREATE SEQUENCE seq_documento
    START WITH 1
    INCREMENT BY 50;

CREATE TABLE usuario (
    id BIGINT DEFAULT nextval('seq_usario') PRIMARY KEY,
    nome VARCHAR(250) NOT NULL,
    telefone VARCHAR(13),
    data_de_nascimento DATE,
    data_de_inclusao TIMESTAMPTZ,
    data_de_atualizacao TIMESTAMPTZ
);

ALTER SEQUENCE seq_usario OWNED BY usuario.id;

CREATE TABLE documento(
    id BIGINT DEFAULT nextval('seq_documento') PRIMARY KEY,
    tipo_do_documento VARCHAR(30) NOT NULL,
    descricao VARCHAR(200),
    data_de_inclusao TIMESTAMPTZ,
    data_de_atualizacao TIMESTAMPTZ,
    usuario_id BIGINT,
    CONSTRAINT fk_documento_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

ALTER SEQUENCE seq_documento OWNED BY documento.id;