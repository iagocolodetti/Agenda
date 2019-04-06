package br.com.iagocolodetti.controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iagocolodetti
 */
public class ServletIndex extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!request.getSession().isNew() && request.getSession().getAttribute("usuario") != null) {
            response.sendRedirect("agenda");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
