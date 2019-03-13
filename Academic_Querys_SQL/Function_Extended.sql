/*
Fonte: https://forum.imasters.com.br/topic/511517-valor-por-extenso-diretamente-do-mysql/
*/

drop function if exists `grupoExtenso`;
DELIMITER $$
CREATE FUNCTION `grupoExtenso`
-- Parâmetro da função
(valor decimal(15, 2))
-- Retorno da função
RETURNS varchar(255)
BEGIN
-- Variáveis auxiliares
DECLARE textoRetorno varchar(255);
DECLARE valorArredondado int;
DECLARE auxiliar int;
-- Inicia variáveis
SET textoRetorno = '';
SET auxiliar = 0;
SET valorArredondado = 0;
-- Monta o valor por extenso
SET valorArredondado = round(valor, 0);
SET auxiliar = valorArredondado - (valorArredondado % 100);
IF (valorArredondado = 100) THEN
SET textoRetorno = ' cem';
ELSEIF (auxiliar = 100) THEN
SET textoRetorno = ' cento';
ELSEIF (auxiliar = 200) THEN
SET textoRetorno = ' duzentos';
ELSEIF (auxiliar = 300) THEN
SET textoRetorno = ' trezentos';
ELSEIF (auxiliar = 400) THEN
SET textoRetorno = ' quatrocentos';
ELSEIF (auxiliar = 500) THEN
SET textoRetorno = ' quinhentos';
ELSEIF (auxiliar = 600) THEN
SET textoRetorno = ' seiscentos';
ELSEIF (auxiliar = 700) THEN
SET textoRetorno = ' setecentos';
ELSEIF (auxiliar = 800) THEN
SET textoRetorno = ' oitocentos';
ELSEIF (auxiliar = 900) THEN
SET textoRetorno = ' novecentos';
END IF;
IF (((valorArredondado - auxiliar) <> 0) AND
(auxiliar <> 0)) THEN
SET textoRetorno = concat(textoRetorno, ' e');
END IF;
SET auxiliar = (valorArredondado % 100) - (valorArredondado % 10);
IF (auxiliar = 0) THEN
SET auxiliar = (valorArredondado % 10);
IF (auxiliar = 1) THEN
SET textoRetorno = concat(textoRetorno, ' um');
ELSEIF (auxiliar = 2) THEN
SET textoRetorno = concat(textoRetorno, ' dois');
ELSEIF (auxiliar = 3) THEN
SET textoRetorno = concat(textoRetorno, ' três');
ELSEIF (auxiliar = 4) THEN
SET textoRetorno = concat(textoRetorno, ' quatro');
ELSEIF (auxiliar = 5) THEN
SET textoRetorno = concat(textoRetorno, ' cinco');
ELSEIF (auxiliar = 6) THEN
SET textoRetorno = concat(textoRetorno, ' seis');
ELSEIF (auxiliar = 7) THEN
SET textoRetorno = concat(textoRetorno, ' sete');
ELSEIF (auxiliar = 8) THEN
SET textoRetorno = concat(textoRetorno, ' oito');
ELSEIF (auxiliar = 9) THEN
SET textoRetorno = concat(textoRetorno, ' nove');
END IF;
END IF;
SET auxiliar = (valorArredondado % 100) - (valorArredondado % 10);
IF (auxiliar = 10) THEN
SET auxiliar = (valorArredondado % 10);
IF (auxiliar = 0) THEN
SET textoRetorno = concat(textoRetorno, ' dez');
ELSEIF (auxiliar = 1) THEN
SET textoRetorno = concat(textoRetorno, ' onze');
ELSEIF (auxiliar = 2) THEN
SET textoRetorno = concat(textoRetorno, ' doze');
ELSEIF (auxiliar = 3) THEN
SET textoRetorno = concat(textoRetorno, ' treze');
ELSEIF (auxiliar = 4) THEN
SET textoRetorno = concat(textoRetorno, ' quatorze');
ELSEIF (auxiliar = 5) THEN
SET textoRetorno = concat(textoRetorno, ' quinze');
ELSEIF (auxiliar = 6) THEN
SET textoRetorno = concat(textoRetorno, ' dezesseis');
ELSEIF (auxiliar = 7) THEN
SET textoRetorno = concat(textoRetorno, ' dezessete');
ELSEIF (auxiliar = 8) THEN
SET textoRetorno = concat(textoRetorno, ' dezoito');
ELSEIF (auxiliar = 9) THEN
SET textoRetorno = concat(textoRetorno, ' dezenove');
END IF;
ELSE
IF (auxiliar = 20) THEN
SET textoRetorno = concat(textoRetorno, ' vinte');
ELSEIF (auxiliar = 30) THEN
SET textoRetorno = concat(textoRetorno, ' trinta');
ELSEIF (auxiliar = 40) THEN
SET textoRetorno = concat(textoRetorno, ' quarenta');
ELSEIF (auxiliar = 50) THEN
SET textoRetorno = concat(textoRetorno, ' cinquenta');
ELSEIF (auxiliar = 60) THEN
SET textoRetorno = concat(textoRetorno, ' sessenta');
ELSEIF (auxiliar = 70) THEN
SET textoRetorno = concat(textoRetorno, ' setenta');
ELSEIF (auxiliar = 80) THEN
SET textoRetorno = concat(textoRetorno, ' oitenta');
ELSEIF (auxiliar = 90) THEN
SET textoRetorno = concat(textoRetorno, ' noventa');
END IF;
IF ((auxiliar <> 0) AND
((valorArredondado % 10) <> 0)) THEN
SET textoRetorno = concat(textoRetorno, ' e');
SET auxiliar = (valorArredondado % 10);
IF (auxiliar = 1) THEN
SET textoRetorno = concat(textoRetorno, ' um');
ELSEIF (auxiliar = 2) THEN
SET textoRetorno = concat(textoRetorno, ' dois');
ELSEIF (auxiliar = 3) THEN
SET textoRetorno = concat(textoRetorno, ' três');
ELSEIF (auxiliar = 4) THEN
SET textoRetorno = concat(textoRetorno, ' quatro');
ELSEIF (auxiliar = 5) THEN
SET textoRetorno = concat(textoRetorno, ' cinco');
ELSEIF (auxiliar = 6) THEN
SET textoRetorno = concat(textoRetorno, ' seis');
ELSEIF (auxiliar = 7) THEN
SET textoRetorno = concat(textoRetorno, ' sete');
ELSEIF (auxiliar = 8) THEN
SET textoRetorno = concat(textoRetorno, ' oito');
ELSEIF (auxiliar = 9) THEN
SET textoRetorno = concat(textoRetorno, ' nove');
END IF;
END IF;
END IF;
-- Retorna o valor por extenso
RETURN(textoRetorno);
END
$$
-- -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
-- =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
-- -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
drop function if exists `trataGrupoExtenso`;
DELIMITER $$
CREATE FUNCTION `trataGrupoExtenso`
-- Parâmetro da função
(parametro varchar(255))
-- Estrutura do parâmetro
-- "G>>:" = Grupo
-- "S>>:" = Singular da moeda ou unidade (mil, milhão, bilhão...)
-- "P>>:" = Plural da moeda ou unidade (mil, milhões, bilhões...)
-- "E>>:" = Define se haverá a junção "e" (0 = Não / 1 = Sim)
-- "T>>:" = Texto tratado na função "grupoExtenso"
-- Retorno da função
RETURNS varchar(255)
-- Estrutura do retorno
-- "V>>:" = Valor por extenso até este ponto
-- "F>>:" = Define se haverá a junção "e" (0 = Não / 1 = Sim)
BEGIN
-- Variáveis para localização dos valores no campo de entrada "parametro"
DECLARE posParamGrupo int;
DECLARE posParamSingular int;
DECLARE posParamPlural int;
DECLARE posParamFlagE int;
DECLARE posParamTextoEntrada int;
DECLARE tamanhoParametro int;
-- Variáveis para os valores destrinchados do campo de entrada "parametro"
DECLARE grupo decimal(15, 2);
DECLARE singular varchar(50);
DECLARE plural varchar(50);
DECLARE flagE int;
DECLARE textoEntrada varchar(255);
-- Texto de retorno da função
DECLARE textoRetorno varchar(255);
SET textoRetorno = '';
-- Localiza a posição de início de cada informação no campo "parametro"
SET posParamGrupo = instr(trim(parametro), 'G>>:');
SET posParamSingular = instr(trim(parametro), 'S>>:');
SET posParamPlural = instr(trim(parametro), 'P>>:');
SET posParamFlagE = instr(trim(parametro), 'E>>:');
SET posParamTextoEntrada = instr(trim(parametro), 'T>>:');
SET tamanhoParametro = length(trim(parametro));
-- Obtem do campo "parametro" as informações que serão utilizadas
SET grupo = cast((substring(parametro, (posParamGrupo + 4), (posParamSingular - (posParamGrupo + 4)))) as decimal(15, 2));
SET singular = substring(parametro, (posParamSingular + 4), (posParamPlural - (posParamSingular + 4)));
SET plural = substring(parametro, (posParamPlural + 4), (posParamFlagE - (posParamPlural + 4)));
SET flagE = cast((substring(parametro, (posParamFlagE + 4), (posParamTextoEntrada - (posParamFlagE + 4)))) as unsigned);
SET textoEntrada = substring(parametro, (posParamTextoEntrada + 4), (tamanhoParametro - (posParamTextoEntrada + 3)));
-- Monta o valor por extenso
SET textoRetorno = textoEntrada;
-- SET @FLAG = flagE
IF (grupo <> 0) THEN
IF (flagE = 1) THEN
SET textoRetorno = concat(textoRetorno, ' e');
END IF;
-- SET flagE = 1;
SET textoRetorno = concat(textoRetorno, grupoExtenso(grupo));
IF (grupo = 1) THEN
SET textoRetorno = concat(textoRetorno, singular);
ELSE
SET textoRetorno = concat(textoRetorno, plural);
END IF;
END IF;
SET textoRetorno = concat('V>>:', textoRetorno, 'F>>:', cast(flagE as char(1)));
-- Retorna o valor por extenso e a flag que determina o uso do "E"
RETURN textoRetorno;
END
$$
-- -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
-- =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
-- -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
drop function if exists `valorExtenso`;
DELIMITER $$
CREATE FUNCTION `valorExtenso`
-- Parâmetro da função
(valor decimal(15, 2))
-- Retorno da função
RETURNS varchar(255)
BEGIN
-- Variável de retorno da função
DECLARE textoRetorno varchar(255);
-- Variáveis auxiliares
DECLARE grupo decimal(15, 2);
DECLARE flagE int;
DECLARE temValorFracionado int;
DECLARE textoEntrada varchar(255);
DECLARE valorAuxiliar bigint;
DECLARE valorSemDecimal bigint;
-- Retorno da função trataGrupoExtenso
-- "V>>:" = Valor por extenso até este ponto
-- "F>>:" = Define se haverá a junção "e" (0 = Não / 1 = Sim)
DECLARE textoTrataGrupo varchar(255);
DECLARE posTextoValorExtenso varchar(250);
DECLARE posTextoE int;
DECLARE tamanhoTexto int;
-- Variáveis para o nome da moeda
DECLARE moedaSingular varchar(50);
DECLARE moedaPlural varchar(50);
DECLARE fracaoMoedaSingular varchar(50);
DECLARE fracaomoedaPlural varchar(50);
-- Não retorna nada caso o valor seja negativo ou maior que bilhões
IF (ifnull(valor, 0) > 999999999999.99) OR
(ifnull(valor, 0) < 0) THEN
SET textoRetorno = '';
RETURN textoRetorno;
END IF;
-- Determina o nome da moeda e sua fração
SET moedaSingular = ' real';
SET moedaPlural = ' reais';
SET fracaoMoedaSingular = ' centavo';
SET fracaomoedaPlural = ' centavos';
-- Valor sem decimal
SET valorSemDecimal = truncate(valor, 0);
-- Determina se será utilizado valor fracionado (0 = Não / 1 = Sim)
SET temValorFracionado = 1;
-- Monta o número por extenso
SET valorAuxiliar = 0;
IF ((valor - (valor % 1)) = 0) THEN
SET textoRetorno = ' zero';
ELSE
SET textoRetorno = '';
SET flagE = 0;
-- Bilhão
SET valorAuxiliar = (((valorSemDecimal % 1000000000000) - (valorSemDecimal % 1000000000)) * 0.000000001);
SET textoTrataGrupo =
trataGrupoExtenso(concat('G>>:', cast(valorAuxiliar as char),
'S>>: bilhão',
'P>>: bilhões',
'E>>:', cast(flagE as char),
'T>>:', textoRetorno));
SET posTextoValorExtenso = instr(trim(textoTrataGrupo), 'V>>:');
SET posTextoE = instr(trim(textoTrataGrupo), 'F>>:');
SET tamanhoTexto = length(trim(textoTrataGrupo));
SET textoRetorno = substring(textoTrataGrupo, (posTextoValorExtenso + 4), (posTextoE - (posTextoValorExtenso + 4)));
SET flagE = cast((substring(textoTrataGrupo, (posTextoE + 4), (tamanhoTexto - (posTextoE + 3)))) as unsigned);
-- Milhão
SET valorAuxiliar = (((valorSemDecimal % 1000000000) - (valorSemDecimal % 1000000)) * 0.000001);
SET textoTrataGrupo =
trataGrupoExtenso(concat('G>>:', cast(valorAuxiliar as char),
'S>>: milhão',
'P>>: milhões',
'E>>:', cast(flagE as char),
'T>>:', textoRetorno));
SET posTextoValorExtenso = instr(trim(textoTrataGrupo), 'V>>:');
SET posTextoE = instr(trim(textoTrataGrupo), 'F>>:');
SET tamanhoTexto = length(trim(textoTrataGrupo));
SET textoRetorno = substring(textoTrataGrupo, (posTextoValorExtenso + 4), (posTextoE - (posTextoValorExtenso + 4)));
SET flagE = cast((substring(textoTrataGrupo, (posTextoE + 4), (tamanhoTexto - (posTextoE + 3)))) as unsigned);
-- Mil
SET valorAuxiliar = (((valorSemDecimal % 1000000) - (valorSemDecimal % 1000)) * 0.001);
SET textoTrataGrupo =
trataGrupoExtenso(concat('G>>:', cast(valorAuxiliar as char),
'S>>: mil',
'P>>: mil',
'E>>:', cast(flagE as char),
'T>>:', textoRetorno));
SET posTextoValorExtenso = instr(trim(textoTrataGrupo), 'V>>:');
SET posTextoE = instr(trim(textoTrataGrupo), 'F>>:');
SET tamanhoTexto = length(trim(textoTrataGrupo));
SET textoRetorno = substring(textoTrataGrupo, (posTextoValorExtenso + 4), (posTextoE - (posTextoValorExtenso + 4)));
SET flagE = cast((substring(textoTrataGrupo, (posTextoE + 4), (tamanhoTexto - (posTextoE + 3)))) as unsigned);
-- Cem
SET valorAuxiliar = (valorSemDecimal % 1000);
IF ((valorSemDecimal >= 100) AND
(((valorAuxiliar < 100) AND
((valorAuxiliar % 100) between 1 and 99)) OR
((valorAuxiliar >= 100) AND
((valorAuxiliar % 100) = 0)))) THEN
SET flagE = 1;
END IF;
SET textoTrataGrupo =
trataGrupoExtenso(concat('G>>:', cast(valorAuxiliar as char),
'S>>: ',
'P>>: ',
'E>>:', cast(flagE as char),
'T>>:', textoRetorno));
SET posTextoValorExtenso = instr(trim(textoTrataGrupo), 'V>>:');
SET posTextoE = instr(trim(textoTrataGrupo), 'F>>:');
SET tamanhoTexto = length(trim(textoTrataGrupo));
SET textoRetorno = substring(textoTrataGrupo, (posTextoValorExtenso + 4), (posTextoE - (posTextoValorExtenso + 4)));
SET flagE = cast((substring(textoTrataGrupo, (posTextoE + 4), (tamanhoTexto - (posTextoE + 3)))) as unsigned);
END IF;
-- Moeda
IF (round(valor, 0) = 1) THEN
SET textoRetorno = concat(trim(textoRetorno), moedaSingular);
ELSE
IF (round(valor, -6) <> 0) AND
((round(valor, 0) - round(valor, -6)) = 0) THEN
SET textoRetorno = concat(trim(textoRetorno), ' de', moedaPlural);
ELSE
SET textoRetorno = concat(trim(textoRetorno), moedaPlural);
END IF;
END IF;
-- Fração da moeda
IF (temValorFracionado = 1) THEN
SET flagE = 1;
SET valorAuxiliar = 0;
SET valorAuxiliar = valorAuxiliar + right(valor, 2);
SET textoTrataGrupo =
trataGrupoExtenso(concat('G>>:', cast(valorAuxiliar as char),
'S>>:', fracaoMoedaSingular,
'P>>:', fracaoMoedaPlural,
'E>>:', cast(flagE as char),
'T>>:', textoRetorno));
SET posTextoValorExtenso = instr(trim(textoTrataGrupo), 'V>>:');
SET posTextoE = instr(trim(textoTrataGrupo), 'F>>:');
SET tamanhoTexto = length(trim(textoTrataGrupo));
SET textoRetorno = substring(textoTrataGrupo, (posTextoValorExtenso + 4), (posTextoE - (posTextoValorExtenso + 4)));
SET flagE = cast((substring(textoTrataGrupo, (posTextoE + 4), (tamanhoTexto - (posTextoE + 3)))) as unsigned);
END IF;
RETURN textoRetorno;
END
$$
