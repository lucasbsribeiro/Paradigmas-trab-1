package academia;

public class Exercicio {
    private int id;
    private String nome;
    private int series;
    private int minReps;
    private int maxReps;
    private float carga;
    private int tempoDescanso;

    public Exercicio(String nome, int series, int minReps, int maxReps, float carga, int  tempoDescanso) {
        this.nome = nome;
        this.series = series;
        this.minReps = minReps;
        this.maxReps = maxReps;
        this.carga = carga;
        this.tempoDescanso = tempoDescanso;
    }
    public String getNomeExercicio() {
        return nome;
    }
    public int getIdExercicio() {
        return id;
    }
    public int getSeries() {
        return series;
    }
    public int getMinReps() {
        return minReps;
    }
    public int getMaxReps() {
        return maxReps;
    }
    public float getCarga() {
        return carga;
    }
    public int getTempoDescanso() {
        return tempoDescanso;
    }

    public void setIdExercicio(int id) {
        this.id = id;
    }
    public void setNomeExercicio(String nomeExercicio) {
        this.nome = nomeExercicio;
    }
}
