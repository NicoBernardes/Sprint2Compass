import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String feliz = ":-)";
		String chateado = ":-(";
		int count = 0;
		int countC = 0;
		int countF = 0;

		System.out.println("Olá, bem vindo ao programa de teste de emoticons!");

		System.out.print("Frase: ");
		String frase = sc.nextLine();

		for (int i = 0; i <= frase.length(); i++) {
			if (frase.substring(i).startsWith(feliz)) {
				countF++;
			}
			if (frase.substring(i).startsWith(chateado)) {
				countC++;
			}
		}

		if (countF > countC) {
			System.out.println("Divertido!");
		}
		if (countC > countF) {
			System.out.println("Chateado.");
		}
		if (countC == countF) {
			System.out.println("Neutro.");
		}
	}
}
