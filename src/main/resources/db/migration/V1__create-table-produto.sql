CREATE TABLE produto (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    nome TEXT NOT NULL,
    descricao TEXT,
    preco_em_centavos INTEGER NOT NULL,
    categoria TEXT
);