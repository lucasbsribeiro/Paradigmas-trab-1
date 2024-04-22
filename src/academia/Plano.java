package academia;

import java.util.Scanner;

public class Plano {
    public int id;
    public String nome;
    public String valorMensal;

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
