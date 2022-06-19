import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		int option = 1;

		Connection con = CreateConnection.reCon();
		System.out.println("Olá, bem vindo ao programa de teste de banco de dados.");

		while (option != 0) {
			System.out.println("Selecione a opção de teste. \n" + "1- cadastro de produtos \n"
					+ "2- atualizar o primeiro produto cadastrado \n" + "3- excluir o terceiro produto \n" + "0- sair");
			option = sc.nextInt();

			if (option == 1) {
				try {
					Statement stm = con.createStatement();
					stm.execute(
							"INSERT INTO PRODUCT (id, name, description, quantity, price) VALUES ('1', 'Ventilador', 'Ventilador de teto', '1', '220.90');");
					stm.execute(
							"INSERT INTO PRODUCT (id, name, description, quantity, price) VALUES ('2', 'Fogão', 'Fogão 4 bocas', '2', '599.00');");
					stm.execute(
							"INSERT INTO PRODUCT (id, name, description, quantity, price) VALUES ('3', 'Televisão', 'Televisão de 50 polegadas 4K UHD', '3', '2763.10');");
					System.out.println("Produtos criado com sucesso!!!\n ");
				} catch (SQLIntegrityConstraintViolationException ex) {
					System.out.println("Valor de entrada de dados duplicado! \n");
				}
			}
			if (option == 2) {
				Statement stm = con.createStatement();
				stm.execute("UPDATE PRODUCT SET NAME = 'Máquina de lavar roupas', "
						+ "						DESCRIPTION = 'Máquina de lavar roupas 18KG automática',"
						+ "						QUANTITY = '2'," + "						PRICE = '1780.30'"
						+ "						WHERE ID = '1'; ");
				System.out.println("Produto atualizado com sucesso! \n ");
			}
			if (option == 3) {
				Statement stm = con.createStatement();
				stm.execute("DELETE FROM PRODUCT WHERE ID = 2;");
				System.out.println("Produto deletado com sucesso! \n ");
			}
			if (option >= 4 || option < 0) {
				System.out.println("Opção inválida!");
			}
			
			con.close();
			System.out.println("Programa finalizado.");
			sc.close();
		}

	}

}
