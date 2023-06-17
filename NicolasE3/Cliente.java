package NicolasE3;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cliente {
    private static Set<Integer> codigosUtilizados = new HashSet<>();
    private static Set<String> emailsUtilizados = new HashSet<>();
    
    private int cod;
    private String nome;
    private String email;
    
    public Cliente(int cod, String nome, String email) {
        if (codigosUtilizados.contains(cod)) {
            throw new IllegalArgumentException("Código já cadastrado.");
        }
        if (emailsUtilizados.contains(email)) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }
        
        this.cod = cod;
        this.nome = nome;
        this.email = email;
        
        codigosUtilizados.add(cod);
        emailsUtilizados.add(email);
    }
    
     public int getCod() {
        return cod;
    }

	public void setCod(int cod) {
        if (codigosUtilizados.contains(cod)) {
            throw new IllegalArgumentException("Código já utilizado por outro cliente.");
        }
        
        codigosUtilizados.remove(this.cod);
        this.cod = cod;
        codigosUtilizados.add(cod);
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        if (emailsUtilizados.contains(email)) {
            throw new IllegalArgumentException("Email já utilizado por outro cliente.");
        }
        
        emailsUtilizados.remove(this.email);
        this.email = email;
        emailsUtilizados.add(email);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cliente cliente = (Cliente) obj;
        return cod == cliente.cod &&
                Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod, email);
    }
}
