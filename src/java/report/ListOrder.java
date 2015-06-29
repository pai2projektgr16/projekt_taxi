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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import model.*;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class ListOrder {

    private String[] selectedOrder;
    private List<String> checkedOrder;
    private List<String> order;
    private List<Order> o;
   
    private StreamedContent file;
    
    private List<Report> allReports;

    @PersistenceContext
    private EntityManager em;

    private Object entityManager;

    @PostConstruct
    public void init() {
        order = new ArrayList<String>();

        o = getAllOrders();
        
        Order or = new Order();
        
        for (int i = 0, n = o.size(); i < n; i++) {
            or = o.get(i);
            order.add(i + " --> Id zlecenia: " + or.getIdOrder().toString() + " Skąd: " + or.getFrom() + " Dokąd: " + or.getTo() + " Taxi: " + or.getNumberRegister().getNumberRegister() + " Data: " + or.getDateAdd().toString());
        }

        /*   order.add("San Francisco");
         order.add("London");
         order.add("Paris"); */
    }

    public List<Order> getAllOrders() {

        Query query = em.createNamedQuery("Orders.findAll");
        return (List<Order>) query.getResultList();

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

    public List<String> getCheckedOrder() {
        return checkedOrder;
    }

    public void setCheckedOrder(List<String> checkedOrder) {
        this.checkedOrder = checkedOrder;
    }

    public List<Report> getAllReports() {
        return allReports;
    }

    public Order getOrdersById(int id) {
        return o.get(id);
    }

    
    

    public void generateReportAction() {
        checkedOrder = new ArrayList<String>();
        //     info(selectedOrder[0]);
        for (int i = 0, n = selectedOrder.length; i < n; i++) {
            checkedOrder.add(i, selectedOrder[i].subSequence(0, 1).toString());
        }

        allReports = new ArrayList<Report>();

        for (int i = 0, n = checkedOrder.size(); i < n; i++) {
            allReports.add(new Report(o.get(Integer.parseInt(checkedOrder.get(i)))));

        }

     //   allReports.add(generatedReport.getOneRaport(0));
        //   allReports.add(generatedReport.getOneRaport(1));
        /*
         for (int i = 0, n = generatedReport.getListaRaportowSize(); i < n; i++) {
         allReports.add(generatedReport.getOneRaport(i));
    
         }  */
   //    allReports = ; 
        //    allReports.add((Report) generatedRaport.createReports(1));
        //      GenerateReport report = new GenerateReport(checkedOrder);
        //      info(report.getStatus());
    }

    public void fileDownloadView() {        
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/file.pdf");
        file = new DefaultStreamedContent(stream, "image/jpg", "file.pdf");
    }
 
    public StreamedContent getFile() {
        fileDownloadView();
        return file;
    }
    
    public void info(String a) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", a));
    }

}
