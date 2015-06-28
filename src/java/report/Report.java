/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Orders;
import model.Users;

/**
 *
 * @author Łukasz
 */
public class Report {

    private String nrRejestracji;
    private String kierowca;
    private String operator;
    private String from;
    private String to;
    private String date;
    private String attencion;
    private String distanse;
    private String price;

    @PersistenceContext
    private EntityManager em;

    public Report() {
    }

    public Report(Orders o) {
        generuj(o);
    }

    private void generuj(Orders o) {

      nrRejestracji = o.getNumberRegister().getNumberRegister();
      operator = o.getOperator().getFirstName() + " " + o.getOperator().getLastName();
      from = o.getFrom();
      to = o.getTo();
      date = "null";
      attencion = "null";
      distanse = "null";
      price = "null";
       
    }

    public String getNrRejestracji() {
        return nrRejestracji;
    }

    public String getKierowca() {
        return kierowca;
    }

    public String getOperator() {
        return operator;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public String getAttencion() {
        return attencion;
    }

    public String getDistanse() {
        return distanse;
    }

    public String getPrice() {
        return price;
    }


   

}
