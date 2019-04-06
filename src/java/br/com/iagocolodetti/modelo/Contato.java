package br.com.iagocolodetti.modelo;

/**
 *
 * @author iagocolodetti
 */
public class Contato implements java.io.Serializable {

    private static final long serialVersionUID = -2319598745423332344L;
    
    private int id;
    private String nome, email, telefone;

    public Contato() {

    }

    public Contato(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Contato(int id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
