package br.com.iagocolodetti.modelo;

import br.com.iagocolodetti.controle.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContatoDAO {
    
    // Create
    public void cadastrar(int usuario_id, Contato contato) throws ContatoExisteException {
        
        if (contatoExiste(usuario_id, contato)) throw new ContatoExisteException();
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("INSERT INTO contato(nome, email, telefone, usuario_id) VALUES(?, ?, ?, ?)");
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getEmail());
            ps.setString(3, contato.getTelefone());
            ps.setInt(4, usuario_id);

            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con, ps);
        }
    }
    
    // Read
    public ArrayList<Contato> carregar(Usuario usuario) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Contato> contatos = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM contato WHERE usuario_id = ?");
            ps.setInt(1, usuario.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                Contato contato = new Contato(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"));
                contatos.add(contato);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con, ps, rs);
        }

        return contatos;
    }
    
    // A ideia aqui é checar se o contato existe pelos seus parâmetros.
    public boolean contatoExiste(int usuario_id, Contato contato) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean existe = false;
        
        try {
            ps = con.prepareStatement("SELECT id FROM contato WHERE nome = ? and email = ? and telefone = ? and usuario_id = ?");
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getEmail());
            ps.setString(3, contato.getTelefone());
            ps.setInt(4, usuario_id);

            rs = ps.executeQuery();

            if (rs.next()) {
                existe = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con, ps, rs);
        }

        return existe;
    }
    
    // A ideia aqui é checar se o contato existe pelo ID.
    public boolean contatoExiste(int usuario_id, int contato_id) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean existe = false;
        
        try {
            ps = con.prepareStatement("SELECT id FROM contato WHERE id = ?");
            ps.setInt(1, contato_id);

            rs = ps.executeQuery();

            if (rs.next()) {
                existe = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con, ps, rs);
        }

        return existe;
    }
    
    // Update
    public void alterar(int usuario_id, Contato contato) throws ContatoExisteException, ContatoNaoExisteException {
        
        if (contatoExiste(usuario_id, contato)) throw new ContatoExisteException();
        if (!contatoExiste(usuario_id, contato.getId())) throw new ContatoNaoExisteException();
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("UPDATE contato SET nome = ?, email = ?, telefone = ? WHERE id = ?");
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getEmail());
            ps.setString(3, contato.getTelefone());
            ps.setInt(4, contato.getId());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con, ps);
        }
    }
    
    // Delete
    public void remover(int usuario_id, Contato contato) throws ContatoNaoExisteException {
        
        if (!contatoExiste(usuario_id, contato.getId())) throw new ContatoNaoExisteException();
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("DELETE FROM contato WHERE id = ?");
            ps.setInt(1, contato.getId());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con, ps);
        }
    }
}
