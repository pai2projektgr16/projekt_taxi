package auth;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Users;

/**
 * Obsługuje formularz logowania do systemu w index.xhtml
 *
 * @author Dominik Zawadzki
 */
@ManagedBean
@SessionScoped
public class Logon {

    protected String login;
    protected String password;
    protected Boolean isLogged = false;
    protected TypeUserEnum typeUser;

    @PersistenceContext
    protected EntityManager em;

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

	Query query = em.createNamedQuery("Users.findByMailLogin")
		.setParameter("mailLogin", login);
	try {

	    Users user = (Users) query.getSingleResult();

	    if (!user.getPassword().equals(password)) {
		throw new NoResultException();
	    }

	    this.login = user.getMailLogin();
	    this.password = user.getPassword();
	    this.isLogged = true;
	    this.typeUser = TypeUserEnum.fromValue(user.getType());

	    switch (this.typeUser) {
		case Operator:
		    return "operator";
		case Taximan:
		    return "taximan";
		default:
		    throw new Exception("Nieobgłuwiany typ użytkownika");
	    }

	} catch (NoResultException ex) {
	    return null;
	}

    }

    public Boolean getIsLogged() {
	return isLogged;
    }

    /**
     * Czy operator czy taksówkarz
     *
     * @return
     */
    public TypeUserEnum getTypeUser() {
	return typeUser;
    }

}
