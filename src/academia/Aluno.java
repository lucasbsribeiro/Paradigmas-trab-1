package academia;

import DAO.AlunoDAO;

import java.sql.Date;
import java.util.Scanner;

public class Aluno {
	public String nome;
	public String cpf;
	public Date aniversario;
	public Date comecoPlano;
	public String cartao;

	public Aluno(String nome, String cpf, Date aniversario) {
		this.nome = nome;
		this.cpf=cpf;
		this.aniversario=aniversario;
		this.comecoPlano = null;
		this.cartao = null;
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
		String aniversario;
		
		System.out.println("Nome do aluno: ");
		nome = entrada.next();
		System.out.println("Cpf de "+nome+": ");
		cpf = entrada.next();
		System.out.println("Data de nascimento (YYYY-MM-DD) de "+nome+": ");
		aniversario  =entrada.next();
		
		Date aniverDate = Date.valueOf(aniversario);
		
		Aluno obj = new Aluno(nome, cpf, aniverDate);
		
		return obj;
	}
}