package academia;

import java.util.Scanner;

public class Plano {
    private int id;
    private String nome;
    private String valorMensal;

    public Plano(String nome, String valorMensal) {
        this.id = 0;
        this.nome = nome;
        this.valorMensal = valorMensal;
    }
    public int getId() {
        return id;
    }
    public String getNomePlano() {
        return nome;
    }
    public String getValorMensal() {
        return valorMensal;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNomePlano(String nomePlano) {
        this.nome = nomePlano;
    }
    public void setValorMensal(String valorMensal) {
        this.valorMensal = valorMensal;
    }
}
