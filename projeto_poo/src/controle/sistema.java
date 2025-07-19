package controle;

import java.util.Scanner;

public class sistema {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int opcao;

		do {

			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");

			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				System.out.println("Você escolheu a opção 1.");
				break;
			case 0:
				System.out.println("");
				break;
			}
		} while (opcao != 0);
	}
}
