
/* ==============================================================
   tabelas
   ============================================================== */

/*
DROP TABLE pedido_item;
DROP TABLE pedido;
DROP TABLE produto;
DROP TABLE cliente;
*/

CREATE TABLE produto (
    id        NUMBER PRIMARY KEY,
    descricao VARCHAR2(40) NOT NULL,
    preco     NUMBER(7,2) NOT NULL
);

CREATE TABLE cliente (
    id    NUMBER PRIMARY KEY,
    nome  VARCHAR2(40) NOT NULL,
    email VARCHAR2(80)
);

CREATE TABLE pedido (
    id          NUMBER PRIMARY KEY,
    cliente_id  NUMBER NOT NULL,
    dt_criacao  DATE   NOT NULL,
    CONSTRAINT fk_pedido_cliente
        FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE pedido_item (
    id          NUMBER PRIMARY KEY,
    pedido_id   NUMBER NOT NULL,
    produto_id  NUMBER NOT NULL,
    quantidade NUMBER NOT NULL,
    CONSTRAINT fk_item_pedido
        FOREIGN KEY (pedido_id)   REFERENCES pedido(id),
    CONSTRAINT fk_item_produto
        FOREIGN KEY (produto_id)  REFERENCES produto(id),
    CONSTRAINT ck_quantidade_pos
        CHECK (quantidade > 0)
);


/* ==============================================================
   sequences
   ============================================================== */

/*
DROP SEQUENCE seq_produto;
DROP SEQUENCE seq_cliente;
DROP SEQUENCE seq_pedido;
DROP SEQUENCE seq_pedido_item;
*/

CREATE SEQUENCE seq_produto
    START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE seq_cliente
    START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE seq_pedido
    START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE seq_pedido_item
    START WITH 1 INCREMENT BY 1;


/* ==============================================================
   dados de teste
   ============================================================== */

/* produtos */
INSERT INTO produto (id, descricao, preco)
VALUES (seq_produto.NEXTVAL, 'Teclado Oracle 23ai',      142.50);

INSERT INTO produto (id, descricao, preco)
VALUES (seq_produto.NEXTVAL, 'Fone Bluetooth',   175.00);

INSERT INTO produto (id, descricao, preco)
VALUES (seq_produto.NEXTVAL, 'Pen drive',         62.10);

INSERT INTO produto (id, descricao, preco)
VALUES (seq_produto.NEXTVAL, 'Monitor HDMI',     780.00);

/* clientes */
INSERT INTO cliente (id, nome, email)
VALUES (seq_cliente.NEXTVAL, 'Juvenal Junqueira', 'juvenal@freemail.com');

INSERT INTO cliente (id, nome, email)
VALUES (seq_cliente.NEXTVAL, 'Mário Martins',    'mario@freemail.com');

INSERT INTO cliente (id, nome, email)
VALUES (seq_cliente.NEXTVAL, 'Norberto Nogueira','norberto@freemail.com');

/* pedidos */
INSERT INTO pedido (id, cliente_id, dt_criacao)
VALUES (seq_pedido.NEXTVAL, 1, SYSDATE);

/* itens do pedido */
INSERT INTO pedido_item (id, pedido_id, produto_id, quantidade)
VALUES (seq_pedido_item.NEXTVAL, 1, 1, 2);

INSERT INTO pedido_item (id, pedido_id, produto_id, quantidade)
VALUES (seq_pedido_item.NEXTVAL, 1, 2, 1);

INSERT INTO pedido_item (id, pedido_id, produto_id, quantidade)
VALUES (seq_pedido_item.NEXTVAL, 1, 3, 3);


/* ==============================================================
   consultas de teste
   ============================================================== */

SELECT id, descricao FROM produto;

SELECT * FROM pedido_item;

SELECT * FROM cliente;

SELECT p.descricao,
       i.quantidade,
       p.preco,
       (i.quantidade * p.preco) AS subtotal
FROM   produto p
JOIN   pedido_item i ON i.produto_id = p.id
WHERE  i.pedido_id = 1;
