/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

/**
 *
 * @author Łukasz
 */

 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import model.Order1;
 
@ManagedBean
public class ListOrder {
         
    private String[] selectedOrder;   
    private List<String> order;
    private Integer liczba;
    
   private Order1 o = new Order1();
   
   @PersistenceContext
    private EntityManager em;
    private Object entityManager;
     
    @PostConstruct
    public void init() {
      order = new ArrayList<String>();

       
      
        order.add("San Francisco");
        order.add("London");
        order.add("Paris");
        order.add("Istanbul"); 
    }
    
 
 
    public String[] getSelectedOrder() {
        return selectedOrder;
    }
 
    public void setSelectedOrder(String[] selectedOrder) {
        this.selectedOrder = selectedOrder;
    }
 
    public List<String> getOrder() {
        return order;
    }
    
    public void generateReportAction(){
        info();
        liczba++;
    }
    
     public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
    }
    
    
}

class Zlecenie {
 
    private int id;
    private String rejestracja;
    private String operator;
    private String from;
    private String to;
    private String attention;
    private Date date;
 
public void setId(int id) {
    this.id = id;
}

public int getID() {
    return id;
}
 
}