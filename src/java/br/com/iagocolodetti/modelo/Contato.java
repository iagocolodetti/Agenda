package br.com.iagocolodetti.modelo;

public class Contato implements java.io.Serializable {
    
    private String nome, email, telefone;

    public Contato(String nome, String email, String telefone) {
        setNome(nome);
        setEmail(email);
        setTelefone(telefone);
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
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public boolean equals(Contato contato) {
        return contato instanceof Contato 
                && getNome().equals((contato).getNome())
                && getEmail().equals((contato).getEmail())
                && getTelefone().equals((contato).getTelefone());
    }
}
