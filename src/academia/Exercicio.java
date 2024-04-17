package academia;

public class Exercicio {
    private int id;
    private String nome;

    public Exercicio(String nome) {
        this.nome = nome;
    }
    public String getNomeExercicio() {
        return nome;
    }
    public int getIdExercicio() {
        return id;
    }
    public void setIdExercicio(int id) {
        this.id = id;
    }
    public void setNomeExercicio(String nomeExercicio) {
        this.nome = nomeExercicio;
    }
}
