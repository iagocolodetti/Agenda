package br.com.iagocolodetti.controle;

import br.com.iagocolodetti.modelo.ContatoNaoExisteException;
import br.com.iagocolodetti.modelo.Contato;
import br.com.iagocolodetti.modelo.ContatoDAOImpl;
import br.com.iagocolodetti.modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iagocolodetti
 */
public class ServletRemover extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (!request.getSession().isNew() && usuario != null) {
            try {
                Contato contato = new Contato(
                        Integer.parseInt(request.getParameter("id")),
                        Util.decodificar(request.getParameter("nome")),
                        Util.decodificar(request.getParameter("email")),
                        Util.decodificar(request.getParameter("telefone")));
                new ContatoDAOImpl().remover(usuario.getId(), contato);
                request.setAttribute("sucesso", "Contato removido com sucesso.");
            } catch (ContatoNaoExisteException e) {
                request.setAttribute("erro", "Erro: " + e.getMessage());
            }
        }

        request.getRequestDispatcher("agenda").forward(request, response);
    }
}
