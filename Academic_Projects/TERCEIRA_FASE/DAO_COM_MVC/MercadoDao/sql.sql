create database mercado;

use mercado;

DROP TABLE IF EXISTS `produto`;
CREATE TABLE `produto` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `valor` double(6,2) NOT NULL,
  `ativo` int(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT INTO `produto` (`id`, `nome`, `valor`, `ativo`) VALUES
(1, 'Feijão', 3.50, 1);


ALTER TABLE `produto`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `produto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;


select * from produto;

update produto set ativo = 1;