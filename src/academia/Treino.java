package academia;

public class Treino {
    private int id;
    private String nome;

    public Treino(String nome) {
        this.nome = nome;
    }
    public String getNomeTreino() {
        return nome;
    }
    public int getIdTreino() {
        return id;
    }
    public void setIdTreino(int id) {
        this.id = id;
    }
    public void setNomeTreino(String nome) {
        this.nome = nome;
    }
}
