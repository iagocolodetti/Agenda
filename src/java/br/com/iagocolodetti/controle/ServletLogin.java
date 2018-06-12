package br.com.iagocolodetti.controle;

import br.com.iagocolodetti.modelo.ManipularCSV;
import br.com.iagocolodetti.modelo.util;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletLogin extends HttpServlet {

    private final String USUARIO = "admin";
    private final String SENHA = "12345";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        
        String usuarioSessao = request.getParameter("usuario"), senhaSessao = request.getParameter("senha");
        
        if (usuarioSessao.equals(USUARIO) && senhaSessao.equals(SENHA)) {
            HttpSession objSessao = request.getSession();
            if (objSessao == null) {
                objSessao = request.getSession(true);
            }
            objSessao.setAttribute("usuarioSessao", usuarioSessao);
            objSessao.setAttribute("senhaSessao", senhaSessao);
            
            rd = request.getRequestDispatcher("agenda");
        }
        else {
            request.setAttribute("usuario", util.decodificar(usuarioSessao));
            request.setAttribute("senha", util.decodificar(senhaSessao));
            request.setAttribute("erro", "Erro: Usuário e/ou senha inválido(s)");
            rd = request.getRequestDispatcher("index.jsp");
        }
        
        rd.forward(request, response);
    }
}
