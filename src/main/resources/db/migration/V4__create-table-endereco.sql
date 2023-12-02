CREATE TABLE endereco(
    id TEXT NOT NULL UNIQUE PRIMARY KEY,
    cep TEXT NOT NULL,
    estado TEXT,
    cidade TEXT,
    bairro TEXT,
    rua TEXT,
    numero INTEGER,
    complemento TEXT
);