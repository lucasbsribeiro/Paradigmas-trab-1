package academia;

import DAO.AlunoDAO;

import java.sql.Date;
import java.util.Scanner;

public class Aluno {
	private String nome;
	private String cpf;
	private Date aniversario;

	public Aluno(String nome, String cpf, Date aniversario) {
		this.nome = nome;
		this.cpf=cpf;
		this.aniversario=aniversario;

	}

	public String getNome(){return nome;}
	public String getCpf(){return cpf;}
	public Date getAniversario(){return aniversario;}
	public void printaAluno(){
		System.out.println("Nome: "+this.nome);
		System.out.println("Cpf: "+this.cpf);
		System.out.println("Aniversario: "+this.aniversario);
	}
}