CREATE TABLE cliente (
    id TEXT NOT NULL UNIQUE PRIMARY KEY,
    cpf TEXT NOT NULL UNIQUE,
    nome TEXT NOT NULL,
    telefone TEXT,
    email TEXT,
    nascimento TEXT,
    endereco_id TEXT,
    ativo BOOLEAN
);