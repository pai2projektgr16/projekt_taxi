package auth;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Users;

/**
 * Obsługuje formularz logowania do systemu w index.xhtml
 * @author Dominik Zawadzki
 */
@ManagedBean
@RequestScoped
public class Logon {

    
    protected String login;
    protected String password;
    
    /**
     * Creates a new instance of Logon
     */
    public Logon() {
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }
    
    
    
    public String executeLogon() throws Exception {
	
	
	
	return null;
    }
    
}
