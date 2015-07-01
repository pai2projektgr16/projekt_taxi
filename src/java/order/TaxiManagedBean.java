package order;

import auth.Logon;
import auth.TypeUserEnum;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Order;
import model.UsersTaxi;

/**
 *
 * @author Adrian Dorota && Dominik Zawadzki
 */
@Named
@RequestScoped
public class TaxiManagedBean {

    private String numberRegister;

    private EntityManager em;

    private Logon logon;

    private UsersTaxi userTaxi = new UsersTaxi();
    
    private List <Order> ordersList;

    public TaxiManagedBean() throws Exception {

	FacesContext context = FacesContext.getCurrentInstance();
	logon = context.getApplication().evaluateExpressionGet(context, "#{logon}", Logon.class);

	if (!logon.isLogged()) {
	    throw new Exception("Użytkownik nie zalogowany");
	}

	if (logon.getTypeUser() != TypeUserEnum.Taximan) {
	    throw new Exception("Użytkownik nie jest taksówkarzem!");
	}

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Taxi");
	em = factory.createEntityManager();
	
	loadNumberRegister();
	loadOrder();

    }

    private void loadNumberRegister() throws Exception {
	Query query = em.createNamedQuery("UsersTaxi.findByMailLogin2")
		.setParameter("mailLogin", logon.getUserName());
	List<UsersTaxi> usersTaxiCollection = query.getResultList();

	if (usersTaxiCollection.size() != 1) {
	    throw new Exception("Niepoprawna ilość taksówek przypisanych dla taksówkarza");
	}

	userTaxi = usersTaxiCollection.get(0);
	numberRegister = userTaxi.getNumberRegister().getNumberRegister();
    }
    
    private void loadOrder() {
	Query query = em.createNamedQuery("Orders.findByNumberRegister")
		.setParameter("numberRegister", numberRegister);
	ordersList = query.getResultList();
    }

    public String getNumberRegister() {
	return numberRegister;
    }

    public List<Order> getOrdersList() {
	return ordersList;
    }
    
    

}
