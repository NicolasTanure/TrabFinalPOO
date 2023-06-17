package NicolasE3;


public class Porto {
    private int id;
    private String nome;
    private String pais;
    
    public Porto(int id, String nome, String pais) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getPais() {
        return pais;
    }
    
    // Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }
}
