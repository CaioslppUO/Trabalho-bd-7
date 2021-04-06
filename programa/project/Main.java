package project;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		DBConnection conn = new DBConnection();
		
		Scanner reader = new Scanner(System.in);		
		
		
		System.out.println("---> Liste o nome de todos os produtos cadastrados:");
		System.out.println("=> Algebra Relacional: \"π nomePro(produto)\"");
		System.out.println("=> SQL: \"SELECT nomePro FROM produto;\"");
		reader.nextLine();
		conn.queryAndPrint("SELECT nomePro FROM produto");
		System.out.println("");

		
		System.out.println("---> Liste o nome e codigo de todos os produtos cujo o precoVenda seja maior que 100:");
		System.out.println("=> Algebra Relacional: \"π nomePro, codPro (σ precoVenda > 100 (produto))\"");
		System.out.println("=> SQL: \"SELECT nomePro, codPro FROM produto WHERE precoVenda > 100;\"");
		reader.nextLine();
		conn.queryAndPrint("SELECT nomePro, codPro FROM produto WHERE precoVenda > 100");
		System.out.println("");

		
		System.out.println("---> Liste o codigo de todos os produtos que tenham registro de compra e venda:");
		System.out.println("=> Algebra Relacional: \"π codPro (ItemCompra) ⋂ π codPro (ItemVenda)\"");
		System.out.println("=> SQL: \"SELECT codPro FROM ItemCompra INTERSECT SELECT codPro FROM ItemVenda;\"");
		reader.nextLine();
		conn.queryAndPrint("SELECT codPro FROM ItemCompra INTERSECT SELECT codPro FROM ItemVenda");
		System.out.println("");

		
		System.out.println("---> Liste o codigo de todos os produtos que nao tem registro de venda ainda:");
		System.out.println("=> Algebra Relacional: \"π codPro (produto) - π codPro(ItemVenda)\"");
		System.out.println("=> SQL: \"SELECT codPro FROM produto Except SELECT codPro FROM ItemVenda;\"");
		reader.nextLine();
		conn.queryAndPrint("SELECT codPro FROM produto Except SELECT codPro FROM ItemVenda");
		System.out.println("");

		
		System.out.println("---> Liste o codigo de todos os produtos que nao tem nenhum registro de compra e venda:");
		System.out.println("=> Algebra Relacional: \"π codPro (produto) - (π codPro (ItemCompra) U π codPro (ItemVenda))\"");
		System.out.println("=> SQL: \"SELECT codPro FROM produto Except (SELECT codPro FROM ItemCompra UNION SELECT codPro FROM ItemVenda);\"");
		reader.nextLine();
		conn.queryAndPrint("SELECT codPro FROM produto Except (SELECT codPro FROM ItemCompra UNION SELECT codPro FROM ItemVenda)");
		System.out.println("");

		
		System.out.println("---> Liste o nome de todos os clientes cadastrados:");
		System.out.println("=> Algebra Relacional: \"π nome (Cliente)\"");
		System.out.println("=> SQL: \"SELECT nome FROM Cliente;\"");
		reader.nextLine();
		conn.queryAndPrint("SELECT nome FROM Cliente");
		System.out.println("");

		
		System.out.println("---> Liste o nome de todos os produtos que tem venda:");
		System.out.println("=> Algebra Relacional: \"π nomePro ( σ produto.codPro = ItemVenda.codPro (produto x ItemVenda))\"");
		System.out.println("=> SQL: \"SELECT DISTINCT nomePro FROM produto, ItemVenda WHERE produto.codPro = ItemVenda.codPro;\"");
		reader.nextLine();
		conn.queryAndPrint("SELECT DISTINCT nomePro FROM produto, ItemVenda WHERE produto.codPro = ItemVenda.codPro");
		System.out.println("");

		
		System.out.println("---> Liste o nome dos clientes que tem notaVenda, cuja a venda foi realiazada em 2021:");
		System.out.println("=> Algebra Relacional: \"π nome ( σ NotaVenda.codCliente = Cliente.codCliente ^ dataVenda = 2021  (NotaVenda x Cliente) )\"");
		System.out.println("=> SQL: \"SELECT nome FROM NotaVenda, Cliente WHERE NotaVenda.codCliente = Cliente.codCliente AND YEAR(NotaVenda.dataVenda) = 2021;\"");
		reader.nextLine();
		conn.queryAndPrint("SELECT nome FROM NotaVenda, Cliente WHERE NotaVenda.codCliente = Cliente.codCliente AND YEAR(NotaVenda.dataVenda) = 2021");
		System.out.println("");

		
		System.out.println("---> Liste o nome de todos os produtos que nao tem Venda registrada:");
		System.out.println("=> Algebra Relacional: \"π nomesPro (π codPro, nomePro (produto) - π codPro, nomePro (σ produto.codPro = ItemVenda.codPro (produto x ItemVenda)));\"");
		System.out.println("=> SQL: \"SELECT nomePro FROM produto Except (SELECT produto.nomePro FROM produto, ItemVenda WHERE produto.codPro = ItemVenda.codPro);\"");
		reader.nextLine();
		conn.queryAndPrint("SELECT nomePro FROM produto Except (SELECT produto.nomePro FROM produto, ItemVenda WHERE produto.codPro = ItemVenda.codPro)");
		System.out.println("");

		
		System.out.println("---> Liste o nome de todos os produtos que tem Compra, mas nao tem venda registrada:");
		System.out.println("=> Algebra Relacional: \"π nomePro (π codPro, nomePro (σ produto.codPro = ItemCompra.codPro (produto x ItemCompra)) - (π codPro, nomePro (σ produto.codPro = ItemVenda.codPro (produto x ItemVenda))))\"");
		System.out.println("=> SQL: \"SELECT nomePro FROM produto, ItemCompra WHERE produto.codPro = ItemCompra.codPro Except SELECT nomePro FROM produto, ItemVenda WHERE produto.codPro = ItemVenda.codPro;\"");
		reader.nextLine();
		conn.queryAndPrint("SELECT nomePro FROM produto, ItemCompra WHERE produto.codPro = ItemCompra.codPro Except SELECT nomePro FROM produto, ItemVenda WHERE produto.codPro = ItemVenda.codPro;");
		System.out.println("");

		
		System.out.println("---> Liste o nome de todos os clientes que ainda nao tem venda registrada em 2021:");
		System.out.println("=> Algebra Relacional: \"π codCliente (Cliente) - (π codCliente (σ dataVenda = 2021 (NotaVenda)))\"");
		System.out.println("=> SQL: \"SELECT codCliente FROM Cliente Except SELECT codCliente FROM NotaVenda WHERE YEAR(NotaVenda.dataVenda) = 2021;\"");
		reader.nextLine();
		conn.queryAndPrint("SELECT codCliente FROM Cliente Except SELECT codCliente FROM NotaVenda WHERE YEAR(NotaVenda.dataVenda) = 2021");
		System.out.println("");

		
		System.out.println("---> Liste o produto que tem o maior preco de venda cadastrado:");
		System.out.println("=> Algebra Relacional: \"π produto.precoVenda, produto.nomePro ((produto x produto) - (σ a1 < a2) ((pa1(produto)))(produto) x (pa2(produto))(produto))\"");
		System.out.println("=> SQL: \"select produto.precoVenda, produto.nomePro from produto Except select produto.precoVenda, produto.nomePro from produto join produto v2 on produto.precoVenda < v2.precoVenda\"");
		reader.nextLine();
		conn.queryAndPrint("select produto.precoVenda, produto.nomePro from produto Except select produto.precoVenda, produto.nomePro from produto join produto v2 on produto.precoVenda < v2.precoVenda");
		System.out.println("");

		
		System.out.println("---> Considere que o cliente possa ter profissao informada ou nao. Sendo assim liste o nome de todos os Clientes cadastrados com o nome da sua profissao, caso tenham:");
		System.out.println("=> Algebra Relacional: \"π nome, nomeProfissao (Cliente _[x] Profissao)\"");
		System.out.println("=> SQL: \"SELECT Cliente.nome, Profissao.nomeProfissao FROM Cliente LEFT JOIN Profissao ON Profissao.idProfissao = Cliente.idProfissao\"");
		reader.nextLine();
		conn.queryAndPrint("SELECT Cliente.nome, Profissao.nomeProfissao FROM Cliente LEFT JOIN Profissao ON Profissao.idProfissao = Cliente.idProfissao");
		System.out.println("");

		
		System.out.println("---> Considere que o cliente possa ter profissao informada ou nao. Sendo assim listeo nome dos Clientes cadastrados com o nome de sua profissao, apenas para os clientes com profissao informada:");
		System.out.println("=> Algebra Relacional: \"\"");
		System.out.println("=> SQL: \"SELECT Cliente.nome,Profissao.nomeProfissao FROM Cliente RIGHT JOIN Profissao ON Cliente.idProfissao = Profissao.idProfissao where Cliente.nome is not null\"");
		reader.nextLine();
		conn.queryAndPrint("SELECT Cliente.nome,Profissao.nomeProfissao FROM Cliente RIGHT JOIN Profissao ON Cliente.idProfissao = Profissao.idProfissao where Cliente.nome is not null");
		System.out.println("");

		
		System.out.println("---> Liste o nome dos produtos que tenham registros tanto em ItemVenda, quanto em itemCompra em Marco 2021:");
		System.out.println("=> Algebra Relacional: \"π nomeCliente, nomeProfissao (σ Cliente.idProfissao = Profissao.idProfissao (Cliente [x]_ Profissao))\"");
		System.out.println("=> SQL: \"SELECT produto.nomePro FROM NotaVenda INNER JOIN ItemVenda on ItemVenda.nroVenda = NotaVenda.nroVenda INNER JOIN produto on produto.codPro = ItemVenda.codPro WHERE YEAR(NotaVenda.dataVenda) = '2021' AND MONTH(NotaVenda.dataVenda) = '03' UNION SELECT produto.nomePro FROM NotaCompra INNER JOIN ItemCompra on ItemCompra.nroCompra = NotaCompra.nroCompra INNER JOIN produto on produto.codPro = ItemCompra.codPro WHERE YEAR(NotaCompra.dataCompra) = '2021' AND MONTH(NotaCompra.dataCompra) = '03'\"");
		reader.nextLine();
		conn.queryAndPrint("SELECT produto.nomePro FROM NotaVenda INNER JOIN ItemVenda on ItemVenda.nroVenda = NotaVenda.nroVenda INNER JOIN produto on produto.codPro = ItemVenda.codPro WHERE YEAR(NotaVenda.dataVenda) = '2021' AND MONTH(NotaVenda.dataVenda) = '03' UNION SELECT produto.nomePro FROM NotaCompra INNER JOIN ItemCompra on ItemCompra.nroCompra = NotaCompra.nroCompra INNER JOIN produto on produto.codPro = ItemCompra.codPro WHERE YEAR(NotaCompra.dataCompra) = '2021' AND MONTH(NotaCompra.dataCompra) = '03'");
		System.out.println("");
		
		
		conn.closeConnection();
	}	
}