package br.com.iagocolodetti.controle;

import br.com.iagocolodetti.modelo.Contato;
import br.com.iagocolodetti.modelo.ContatoDAO;
import br.com.iagocolodetti.modelo.ContatoExisteException;
import br.com.iagocolodetti.modelo.ContatoNaoExisteException;
import br.com.iagocolodetti.modelo.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletAlterar extends HttpServlet {

    private void erro(HttpServletRequest request, String erro) {
        request.setAttribute("erro", "Erro: " + erro);
        request.setAttribute("nomeatual", Util.decodificar(request.getParameter("nomeatual")));
        request.setAttribute("emailatual", Util.decodificar(request.getParameter("emailatual")));
        request.setAttribute("telefoneatual", Util.decodificar(request.getParameter("telefoneatual")));
        request.setAttribute("novonome", Util.decodificar(request.getParameter("novonome")));
        request.setAttribute("novoemail", Util.decodificar(request.getParameter("novoemail")));
        request.setAttribute("novotelefone", Util.decodificar(request.getParameter("novotelefone")));
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("id", Util.decodificar(request.getParameter("id")));
        request.setAttribute("nomeatual", Util.decodificar(request.getParameter("nome")));
        request.setAttribute("emailatual", Util.decodificar(request.getParameter("email")));
        request.setAttribute("telefoneatual", Util.decodificar(request.getParameter("telefone")));
        
        request.getRequestDispatcher("alterar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd = null;
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        if (!request.getSession().isNew() && usuario != null) {
            try {
                Contato contato = new Contato(
                        Integer.parseInt(request.getParameter("id")),
                        Util.decodificar(request.getParameter("novonome")),
                        Util.decodificar(request.getParameter("novoemail")),
                        Util.decodificar(request.getParameter("novotelefone")));
                
                new ContatoDAO().alterar(usuario.getId(), contato);
                
                request.setAttribute("sucesso", "Contato alterado com sucesso.");
                rd = request.getRequestDispatcher("agenda");
            }
            catch (ContatoExisteException e) {
                erro(request, e.getMessage());
                rd = request.getRequestDispatcher("alterar.jsp");
            }
            catch (ContatoNaoExisteException e) {
                erro(request, e.getMessage());
                rd = request.getRequestDispatcher("alterar.jsp");
            }
        }
        
        rd.forward(request, response);
    }
}
