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

		// tentei de v�rias maneiras n�o deixar esse c�digo aqui, por�m n�o obtive �xito por causa do tempo.
		// deleting table and creating for easily run the program
		stm.execute("DROP TABLE MOVIE");
		stm.execute("CREATE TABLE IF NOT EXISTS MOVIE (ID INT AUTO_INCREMENT PRIMARY KEY,"
				+ "	NAME VARCHAR(70) NOT NULL," + "	DESCRIPTION VARCHAR(20) NOT NULL," + "	YEAR INTEGER NOT NULL);");

		// Adding to database some information
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('O Poderoso Chef�o', 'Drama', '1972');");
		stm.execute(
				"INSERT INTO MOVIE (name, description, year) VALUES ('Batman: O Cavaleiro das Trevas', 'A��o', '2008');");
		stm.execute(
				"INSERT INTO MOVIE (name, description, year) VALUES ('Pulp Fiction: Tempo de Viol�ncia', 'Drama', '1994');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('A Viagem de Chihiro', 'Drama', '2001');");
		stm.execute(
				"INSERT INTO MOVIE (name, description, year) VALUES ('O Resgate do Soldado Ryan', 'Drama', '1998');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Matrix', 'Fic��o', '1999');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Rambo 3', 'A��o', '1988');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Velozes e Furiosos', 'A��o', '2001');");
		stm.execute(
				"INSERT INTO MOVIE (name, description, year) VALUES ('Harry Potter e a Pedra Filosofal', 'Fantasia', '2001');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('V de Vingan�a', 'A��o', '2005');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Os Vingadores', 'Aventura', '2012');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('A Procura da Felicidade', 'Drama', '2006');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('O Pre�o do Amanh�', 'Fic��o', '2011');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Gente Grande', 'Com�dia', '2010');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Um Espi�o e Meio', 'Com�dia', '2016');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('O M�skara', 'Com�dia', '1994');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('John Wick 2', 'A��o', '2017');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('O Quarto de Jack', 'Drama', '2015');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Atividade Paranormal', 'Terror', '2007');");
		stm.execute("INSERT INTO MOVIE (name, description, year) VALUES ('Jogos Mortais', 'Terror', '2004');");

		try {

			System.out.println("Ol�, qual o n�mero de filmes que vc deseja filtrar? 10 filmes cada p�gina.");
			int nMovie = sc.nextInt();
			System.out.println("Qual o n�mero da p�ginas? 1, 2 ou 0 para filtrar independente da p�gina.");
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
					System.out.println("G�nero: " + description);
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
					System.out.println("G�nero: " + description);
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
					System.out.println("G�nero: " + description);
					System.out.println("Ano: " + year);
					System.out.println();
				}
			}
			// Checking errors
			if (nPag >= 3 || nPag < 0) {
				System.out.println("N�mero de p�gina inv�lido.");
			}
			if (nMovie > 10 && nPag != 0) {
				System.out.println("N�mero de filmes inv�lidos por p�gina.");
			}
		} catch (InputMismatchException | SQLSyntaxErrorException e) {
			System.out.println("Informe apenas n�meros n�o negativos! Finalizando programa.");
			
		}

	}

}
