package br.com.iagocolodetti.controle;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class FiltroAutenticacao implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) {
        
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest rq = (HttpServletRequest)request;
        if (rq.getSession().isNew() || rq.getSession().getAttribute("usuario") == null) {
            rq.setAttribute("erro", "Sessão expirada, faça o login novamente.");
            rq.getSession().invalidate();
            rq.getRequestDispatcher("index.jsp").forward(request, response);
        }

        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {   
        
    }
}
