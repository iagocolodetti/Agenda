package br.com.iagocolodetti.controle;

import br.com.iagocolodetti.modelo.Contato;
import br.com.iagocolodetti.modelo.ContatoExisteException;
import br.com.iagocolodetti.modelo.ContatoNaoExisteException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletAlterar extends HttpServlet {

    private void erro(HttpServletRequest request, String erro) {
        request.setAttribute("erro", "Erro: " + erro);
        request.setAttribute("nomeatual", util.decodificar(request.getParameter("nomeatual")));
        request.setAttribute("emailatual", util.decodificar(request.getParameter("emailatual")));
        request.setAttribute("telefoneatual", util.decodificar(request.getParameter("telefoneatual")));
        request.setAttribute("novonome", util.decodificar(request.getParameter("novonome")));
        request.setAttribute("novoemail", util.decodificar(request.getParameter("novoemail")));
        request.setAttribute("novotelefone", util.decodificar(request.getParameter("novotelefone")));
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        request.setAttribute("nomeatual", util.decodificar(request.getParameter("nome")));
        request.setAttribute("emailatual", util.decodificar(request.getParameter("email")));
        request.setAttribute("telefoneatual", util.decodificar(request.getParameter("telefone")));
        
        request.getRequestDispatcher("alterar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd = null;
        
        if (!request.getSession().isNew() && request.getSession().getAttribute("usuarioSessao") != null) {
            try {
                CSV.alterarContato(
                        new Contato(util.decodificar(request.getParameter("nomeatual")), util.decodificar(request.getParameter("emailatual")), util.decodificar(request.getParameter("telefoneatual"))),
                        new Contato(util.decodificar(request.getParameter("novonome")), util.decodificar(request.getParameter("novoemail")), util.decodificar(request.getParameter("novotelefone"))));
                request.setAttribute("sucesso", "Contato alterado com sucesso.");
                rd = request.getRequestDispatcher("agenda");
            }
            catch (IllegalArgumentException e) {
                erro(request, e.getMessage());
                rd = request.getRequestDispatcher("alterar.jsp");
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
