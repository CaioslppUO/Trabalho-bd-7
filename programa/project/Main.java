package project;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		DBConnection conn = new DBConnection();
		
		Scanner reader = new Scanner(System.in);		
		
		reader.nextLine();
		System.out.println("Todos os produtos cadastrados");
		conn.queryAndPrint("SELECT nomePro FROM produto");
		System.out.println("");

		reader.nextLine();
		System.out.println("Nome e codigo de todos os produtos cujo o precoVenda seja maior que 100 ");
		conn.queryAndPrint("SELECT nomePro,codPro FROM produto WHERE precoVenda > 100");
		System.out.println("");

		reader.nextLine();
		System.out.println("Liste o codigo de todos os produtos que tenham registro de compra e venda");
		conn.queryAndPrint("SELECT codPro FROM ItemCompra INTERSECT SELECT codPro FROM ItemVenda");
		System.out.println("");

		reader.nextLine();
		System.out.println("Liste o codigo de todos os produtos que nao tem registro de venda ainda");
		conn.queryAndPrint("SELECT codPro FROM produto Except SELECT codPro FROM ItemVenda");
		System.out.println("");

		reader.nextLine();
		System.out.println("Liste o codigo de todos os produtos que nao tem nenhum registro de compra e venda ");
		conn.queryAndPrint("SELECT codPro FROM produto Except (SELECT codPro FROM produto Except SELECT codPro FROM ItemVenda)");
		System.out.println("");

		reader.nextLine();
		System.out.println("Liste o nome de todos os clientes cadastrados");
		conn.queryAndPrint("SELECT nome FROM Cliente");
		System.out.println("");

		reader.nextLine();
		System.out.println("Liste o nome de todos os produtos que tem venda");
		conn.queryAndPrint("SELECT nomePro FROM produto, ItemVenda WHERE produto.codPro = ItemVenda.codPro");
		System.out.println("");

		reader.nextLine();
		System.out.println("Liste o nome dos clientes que tem notaVenda, cuja a venda foi realiazada em 2021");
		conn.queryAndPrint("SELECT nome FROM NotaVenda,Cliente WHERE NotaVenda.codCliente = Cliente.codCliente AND YEAR(NotaVenda.dataVenda) = 2021");
		System.out.println("");

		reader.nextLine();
		System.out.println("Liste o nome de todos os produtos que nao tem Venda registrada");
		conn.queryAndPrint("SELECT nomePro FROM produto Except (SELECT produto.nomePro FROM produto,ItemVenda WHERE produto.codPro = ItemVenda.codPro)");
		System.out.println("");

		reader.nextLine();
		System.out.println("Liste o nome de todos os produtos que tem Compra, mas nao tem venda registrada");
		conn.queryAndPrint("SELECT nomePro FROM produto, ItemCompra WHERE produto.codPro = ItemCompra.codPro Except SELECT nomePro FROM produto, ItemVenda WHERE produto.codPro = ItemVenda.codPro;");
		System.out.println("");

		reader.nextLine();
		System.out.println("Liste o nome de todos os clientes que ainda nao tem venda registrada em 2021");
		conn.queryAndPrint("SELECT codCliente FROM Cliente Except SELECT codCliente FROM NotaVenda WHERE YEAR(NotaVenda.dataVenda) = 2021");
		System.out.println("");

		reader.nextLine();
		System.out.println("Liste o produto que tem o maior preco de venda cadastrado");
		conn.queryAndPrint("select produto.precoVenda, produto.nomePro from produto Except select produto.precoVenda, produto.nomePro from produto join produto v2 on produto.precoVenda < v2.precoVenda");
		System.out.println("");

		reader.nextLine();
		System.out.println("Considere que o cliente possa ter profissao informada ou nao. Sendo assim liste o nome de todos os Clientes cadastrados com o nome da sua profissao, caso tenham");
		conn.queryAndPrint("SELECT Cliente.nome, Profissao.nomeProfissao FROM Cliente LEFT JOIN Profissao ON Profissao.idProfissao = Cliente.idProfissao");
		System.out.println("");

		reader.nextLine();
		System.out.println("Considere que o cliente possa ter profissao informada ou nao. Sendo assim listeo nome dos Clientes cadastrados com o nome de sua profissao, apenas para os clientes com profissao informada");
		conn.queryAndPrint("SELECT Cliente.nome,Profissao.nomeProfissao FROM Cliente RIGHT JOIN Profissao ON Cliente.idProfissao = Profissao.idProfissao where Cliente.nome is not null");
		System.out.println("");

		reader.nextLine();
		System.out.println("Liste o nome dos produtos que tenham registros tanto em ItemVenda, quanto em itemCompra em Marco 2021");
		conn.queryAndPrint("SELECT produto.nomePro FROM NotaVenda INNER JOIN ItemVenda on ItemVenda.nroVenda = NotaVenda.nroVenda INNER JOIN produto on produto.codPro = ItemVenda.codPro WHERE YEAR(NotaVenda.dataVenda) = '2021' AND MONTH(NotaVenda.dataVenda) = '03' UNION SELECT produto.nomePro FROM NotaCompra INNER JOIN ItemCompra on ItemCompra.nroCompra = NotaCompra.nroCompra INNER JOIN produto on produto.codPro = ItemCompra.codPro WHERE YEAR(NotaCompra.dataCompra) = '2021' AND MONTH(NotaCompra.dataCompra) = '03'");
		System.out.println("");
		
		
		conn.closeConnection();
	}	
}