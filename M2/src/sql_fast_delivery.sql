CREATE DATABASE fast_delivery DEFAULT CHARSET=UTF8;
USE fast_delivery;

CREATE TABLE cardapio (
id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50),
descricao VARCHAR(200),
preco DECIMAL(10,2),
gluten BOOLEAN,
calorias INT,
image VARCHAR(500)
);

SELECT * FROM cardapio;

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'admin';

FLUSH PRIVILEGES;

INSERT INTO cardapio (nome, descricao, preco, gluten, calorias, image) VALUES
('Sopa', 'Sopa de legumes', 10, false, 55, 'https://www.collinsdictionary.com/images/full/soup_103076762.jpg'),
('Churrasco', 'Verdadeiro sabor gaúcho', 153, false, 267, 'https://cozinhaadois.com.br/wp-content/uploads/2014/03/Costelinha-4.jpg'),
('Fritas', 'Batatas fritas com tempero verde', 25, false, 168, 'https://www.delonghi.com/PageFiles/789038/french-fries.jpg'),
('Sushi', 'Sushi fresco', 187, false, 102, 'https://media-cdn.tripadvisor.com/media/photo-s/1c/46/88/f7/combinado-30-pcs.jpg'),
('Pizza', 'Sabor Calabresa', 121, true, 136, 'https://www.glutenfreepalate.com/wp-content/uploads/2018/08/Gluten-Free-Pizza-3.2-480x360.jpg'),
('Sorvete', 'Sabor creme saboroso', 34, true, 85, 'https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/34F77344-5BD2-47B8-9213-2932E58C75FA/Derivates/D64A6A85-3934-4C3C-8DD4-7D54A4CA5A4F.jpg'),
('Salada', 'Variedade de legumes', 18, false, 45, 'https://media-cdn.tripadvisor.com/media/photo-p/11/71/26/39/salad-lab.jpg'),
('Peixe', 'Salmão grelhado', 108, false, 168, 'https://food.fnr.sndimg.com/content/dam/images/food/fullset/2019/12/20/0/FNK_Baked-Salmon_H_s4x3.jpg.rend.hgtvcom.616.462.suffix/1576855635102.jpeg');

DELETE FROM cardapio WHERE id = 4;

DROP TABLE cardapio;

