package auth;

import javax.faces.bean.ManagedBean;
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

    private Boolean isLogged = false;
    private TypeUserEnum typeUser;

    private Users user = new Users();

    @PersistenceContext
    private EntityManager em;

    public String executeLogon() throws Exception {

	Query query = em.createNamedQuery("Users.findByPassword");
	query.setParameter(1, this.user.getPassword());
	query.setParameter(2, this.user.getMailLogin());
	try {

	    user = (Users) query.getSingleResult();

	    if (user == null) {
		throw new NoResultException();
	    }

	    isLogged = true;
	    typeUser = TypeUserEnum.fromValue(user.getType());

	    return typeUser.getRouteAdress();

	} catch (NoResultException ex) {
	    return null;
	}

    }

    public Boolean isLogged() {
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

    public Users getUser() {
	return user;
    }

    public String getUserName() {
	return user.getMailLogin();
    }

    public void logout() {
	isLogged = false;
	user = new Users();
    }
}
