/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dominik
 */
public class CheckAuthFilter implements Filter {
    
    public CheckAuthFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain)
	    throws IOException, ServletException {
	
	HttpSession session = ((HttpServletRequest) request).getSession(false);
	Logon logon = (session != null) ? (Logon) session.getAttribute("logon") : null;

	String path = ((HttpServletRequest) request).getPathInfo().substring(1);
	// 1. Gdy zalogowany a chce wejsc do formularza przekieruj w zaleznosci od typu (operator..
	// 2. Gdy nie zalogowany a chce wejsc do ukrytej tresci przekieruj do formularza
	if (logon != null && logon.isLogged() && path.equals("index.xhtml")) {
	    ((HttpServletResponse) response).sendRedirect(logon.getTypeUser().getRouteAdress());
	} else if (logon != null && !logon.isLogged() && TypeUserEnum.isRouteAdress(path)) {
	    ((HttpServletResponse) response).sendRedirect("/index.html");
	}
	
	Throwable problem = null;
	try {
	    chain.doFilter(request, response);
	} catch (Throwable t) {
	    // If an exception is thrown somewhere down the filter chain,
	    // we still want to execute our after processing, and then
	    // rethrow the problem after that.
	    problem = t;
	    t.printStackTrace();
	}

	// If there was a problem, we want to rethrow it if it is
	// a known type, otherwise log it.
	if (problem != null) {
	    if (problem instanceof ServletException) {
		throw (ServletException) problem;
	    }
	    if (problem instanceof IOException) {
		throw (IOException) problem;
	    }
	    
	}
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	
    }
    
    @Override
    public void destroy() {
	
    }
    
}
