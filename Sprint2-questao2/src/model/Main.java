package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Connection con = CreateConnection.reCon();
		Statement stm = con.createStatement();

		// tentei de várias maneiras não deixar esse código aqui, porém não obtive êxito por causa do tempo.
		// deleting table and creating for easily run the program
		stm.execute("DROP TABLE MOVIE");
		stm.execute("CREATE TABLE IF NOT EXISTS MOVIE (ID INT AUTO_INCREMENT PRIMARY KEY,"
				+ "	NAME VARCHAR(70) NOT NULL," + "	DESCRIPTION VARCHAR(20) NOT NULL," + "	YEAR INTEGER NOT NULL);");

		// Adding to database some information
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('O Poderoso Chefão', 'Drama', '1972');");
		stm.execute(
				"INSERT INTO MOVIE (name, description, year) VALUES ('Batman: O Cavaleiro das Trevas', 'Ação', '2008');");
		stm.execute(
				"INSERT INTO MOVIE (name, description, year) VALUES ('Pulp Fiction: Tempo de Violência', 'Drama', '1994');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('A Viagem de Chihiro', 'Drama', '2001');");
		stm.execute(
				"INSERT INTO MOVIE (name, description, year) VALUES ('O Resgate do Soldado Ryan', 'Drama', '1998');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Matrix', 'Ficção', '1999');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Rambo 3', 'Ação', '1988');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Velozes e Furiosos', 'Ação', '2001');");
		stm.execute(
				"INSERT INTO MOVIE (name, description, year) VALUES ('Harry Potter e a Pedra Filosofal', 'Fantasia', '2001');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('V de Vingança', 'Ação', '2005');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Os Vingadores', 'Aventura', '2012');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('A Procura da Felicidade', 'Drama', '2006');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('O Preço do Amanhã', 'Ficção', '2011');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Gente Grande', 'Comédia', '2010');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Um Espião e Meio', 'Comédia', '2016');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('O Máskara', 'Comédia', '1994');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('John Wick 2', 'Ação', '2017');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('O Quarto de Jack', 'Drama', '2015');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Atividade Paranormal', 'Terror', '2007');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Jogos Mortais', 'Terror', '2004');");

		try {

			System.out.println("Olá, qual o número de filmes que vc deseja filtrar? 10 filmes cada página.");
			int nMovie = sc.nextInt();
			System.out.println("Qual o número da páginas? 1, 2 ou 0 para filtrar independente da página.");
			int nPag = sc.nextInt();
			System.out.println();
			
			// Getting information independent of page
			if (nPag == 0) {
				PreparedStatement pstm = con.prepareStatement("SELECT * FROM MOVIE LIMIT ?;");
				pstm.setInt(1, nMovie);
				ResultSet rst = pstm.executeQuery();
				while (rst.next()) {
					String name = rst.getString("name");
					String description = rst.getString("description");
					int year = rst.getInt("year");
					System.out.println(name);
					System.out.println("Gênero: " + description);
					System.out.println("Ano: " + year);
					System.out.println();
				}
			}
			
			// Getting information on first page
			if (nPag == 1 && nMovie <= 10) {
				PreparedStatement pstm = con.prepareStatement("SELECT * FROM MOVIE LIMIT ?;");
				pstm.setInt(1, nMovie);
				ResultSet rst = pstm.executeQuery();
				while (rst.next()) {
					String name = rst.getString("name");
					String description = rst.getString("description");
					int year = rst.getInt("year");
					System.out.println(name);
					System.out.println("Gênero: " + description);
					System.out.println("Ano: " + year);
					System.out.println();
				}
			}
			
			// Getting information on second page
			if (nPag == 2 && nMovie <= 10) {
				PreparedStatement pstm = con.prepareStatement("SELECT * FROM MOVIE WHERE ID > 10 limit ?;");
				pstm.setInt(1, nMovie);
				ResultSet rst = pstm.executeQuery();
				while (rst.next()) {
					String name = rst.getString("name");
					String description = rst.getString("description");
					int year = rst.getInt("year");
					System.out.println(name);
					System.out.println("Gênero: " + description);
					System.out.println("Ano: " + year);
					System.out.println();
				}
			}
			// Checking errors
			if (nPag >= 3 || nPag < 0) {
				System.out.println("Número de página inválido.");
			}
			if (nMovie > 10 && nPag != 0) {
				System.out.println("Número de filmes inválidos por página.");
			}
		} catch (InputMismatchException | SQLSyntaxErrorException e) {
			System.out.println("Informe apenas números não negativos! Finalizando programa.");
			
		}

	}

}
