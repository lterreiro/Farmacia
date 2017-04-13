SELECT p.codigo, p.descricao, p.quantidade, p.preco, f.codigo, f.descricao
FROM produto p
INNER JOIN fabricante f ON f.codigo = p.fabricante_codigo;

select * from produto;