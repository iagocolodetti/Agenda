package br.com.iagocolodetti.controle;

import br.com.iagocolodetti.modelo.ContatoNaoExisteException;
import br.com.iagocolodetti.modelo.Contato;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletRemover extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!request.getSession().isNew() && request.getSession().getAttribute("usuarioSessao") != null) {
            try {
                CSV.removerContato(new Contato(util.decodificar(request.getParameter("nome")), util.decodificar(request.getParameter("email")), util.decodificar(request.getParameter("telefone"))));
                request.setAttribute("sucesso", "Contato removido com sucesso.");
            }
            catch (ContatoNaoExisteException e) {
                request.setAttribute("erro", "Erro: " + e.getMessage());
            }
        }
        
        request.getRequestDispatcher("agenda").forward(request, response);
    }
}
