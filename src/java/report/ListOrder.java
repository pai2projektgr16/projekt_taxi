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
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.*;

 
@ManagedBean
public class ListOrder {
         
    private String[] selectedOrder;
    private String[] checkedOrder;

    
    private List<String> order;
     private List<Orders> o;
   
   @PersistenceContext
    private EntityManager em;

    private Object entityManager;
     
  
    
    @PostConstruct
    public void init() {
      order = new ArrayList<String>();
        
      o = getAllOrders();
      Orders or = new Orders();
      
       for (int i = 0, n = o.size(); i < n; i++) {
            or = o.get(i);
           order.add(i + " --> Id zlecenia: " + or.getIdOrder().toString() + " Skąd: " + or.getFrom() + " Dokąd: " + or.getTo() + " Taxi: " + or.getNumberRegister().getNumberRegister() + " Data: " + or.getDateAdd().toString());
        }
      
      
     /*   order.add("San Francisco");
        order.add("London");
        order.add("Paris"); */
       
    }
   
  
   public List<Orders> getAllOrders(){

       Query query = em.createNamedQuery("Orders.findAll");
        return (List<Orders>) query.getResultList();
        
   }
 
   public void wczytaj() {
     String a = "brak";
      

     a = getAllOrders().get(1).getFrom();
     
     
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Odebrane dane:", a));
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

     public String[] getCheckedOrder() {
        return checkedOrder;
    }

    public void setCheckedOrder(String[] checkedOrder) {
        this.checkedOrder = checkedOrder;
    }
    
    public void generateReportAction(){
       
   //     info(selectedOrder[0]);
        for (int i = 0, n = selectedOrder.length; i < n; i++) {
    //         checkedOrder[i] = selectedOrder[i].subSequence(0, 1).toString();
            info(selectedOrder[i]); 
         } 
         
   //      GenerateReport report = new GenerateReport(selectedOrder);
   //      info(report.getStatus());
        
    }
    
     public void info(String a) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", a));
    }
    
    
}

