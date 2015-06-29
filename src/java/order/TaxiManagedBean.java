package order;

import auth.Logon;
import auth.TypeUserEnum;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.UsersTaxi;

/**
 *
 * @author Adrian Dorota && Dominik Zawadzki
 */
@Named
@RequestScoped
public class TaxiManagedBean {

    private String numberRegister;

    @PersistenceContext
    private EntityManager em;

    private Logon logon;

    private UsersTaxi userTaxi = new UsersTaxi();

    public TaxiManagedBean() throws Exception {
	
	//logon = (Logon) request.getSession().getAttribute("beanName");

	FacesContext context = FacesContext.getCurrentInstance();
logon = context.getApplication().evaluateExpressionGet(context, "#{logon}", Logon.class);
	
	if (!logon.isLogged()) {
	    throw new Exception("Użytkownik nie zalogowany");
	}

	if (logon.getTypeUser() != TypeUserEnum.Taximan) {
	    throw new Exception("Użytkownik nie jest taksówkarzem!");
	}

	loadNumberRegister();

    }

    private void loadNumberRegister() throws Exception {
	Query query = em.createNamedQuery("Users.findByMailLogin")
		.setParameter("mailLogin", logon.getUserName());
	List<UsersTaxi> usersTaxiCollection = query.getResultList();

	if (usersTaxiCollection.size() != 1) {
	    throw new Exception("Niepoprawna ilość taksówek przypisanych dla taksówkarza");
	}

	userTaxi = usersTaxiCollection.get(1);
	numberRegister = userTaxi.getNumberRegister().getNumberRegister();
    }

    public String getNumberRegister() {
	return numberRegister;
    }

}
