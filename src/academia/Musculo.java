package academia;

public class Musculo {
    private int id;
    private String nome;

    public Musculo(String nome) {
        this.nome = nome;
    }
    public String getNomeMusculo() {
        return nome;
    }
    public int getIdMusculo() {
        return id;
    }
    public void setIdMusculo(int id) {
        this.id = id;
    }
    public void setNomeMusculo(String nomeMusculo) {
        this.nome = nomeMusculo;
    }
}
