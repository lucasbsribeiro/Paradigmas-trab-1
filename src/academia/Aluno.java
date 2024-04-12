package academia;

import java.util.Date;
import java.util.Scanner;

public class Aluno {
	String nome;
	String cpf;
	Date aniversario;
	Date comecoPlano;
	String cartao;

	public Aluno(String nome, String cpf, Date aniversario) {
		this.nome = nome;
		this.cpf=cpf;
		this.aniversario=aniversario;
	}
	public static void main() {
		//TESTES
		Aluno obj = criaObj();
		System.out.printf("FIM");
	}
	public static Aluno criaObj() {
		Scanner entrada = new Scanner (System.in);
		String cpf;
		String nome;
		int ano;
		int mes;
		int dia;
		
		System.out.println("Nome do aluno: ");
		nome = entrada.next();
		System.out.println("Cpf de "+nome+": ");
		cpf = entrada.next();
		System.out.println("Ano de nascimento de "+nome+": ");
		ano = entrada.nextInt();
		System.out.println("Mes de nascimento de "+nome+": ");
		mes = entrada.nextInt();
		System.out.println("Dia de nascimento de "+nome+": ");
		dia = entrada.nextInt();
		
		Date aniversario = new Date(ano, mes, dia);
		
		Aluno obj = new Aluno(nome, cpf, aniversario);
		
		return obj;
	}
}