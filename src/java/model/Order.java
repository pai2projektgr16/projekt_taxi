/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Łukasz
 */
@Entity
@Table(name = "\"Order\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Order o"),
    @NamedQuery(name = "Orders.findByIdOrder", query = "SELECT o FROM Order o WHERE o.idOrder = :idOrder"),
    @NamedQuery(name = "Orders.findByNumberRegister", query = "SELECT o FROM Order o WHERE o.numberRegister.numberRegister"
	    + " = :numberRegister"),
    @NamedQuery(name = "Orders.findByDateAdd", query = "SELECT o FROM Order o WHERE o.dateAdd = :dateAdd")})
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrder")
    private Integer idOrder;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "\"from\"")
    private String from;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "\"to\"")
    private String to;
    @Column(name = "dateAdd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdd;
    @Lob
    @Size(max = 65535)
    @Column(name = "attention")
    private String attention;
    @JoinColumn(name = "operator", referencedColumnName = "mailLogin")
    @ManyToOne(optional = false)
    private Users operator;
    @JoinColumn(name = "numberRegister", referencedColumnName = "numberRegister")
    @ManyToOne(optional = false)
    private Taxi numberRegister;
    @OneToMany(mappedBy = "idOrder")
    private List<Report> reportsList;

    public Order() {
    }

    public Order(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Order(Integer idOrder, String from, String to) {
        this.idOrder = idOrder;
        this.from = from;
        this.to = to;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public Users getOperator() {
        return operator;
    }

    public void setOperator(Users operator) {
        this.operator = operator;
    }

    public Taxi getNumberRegister() {
        return numberRegister;
    }

    public void setNumberRegister(Taxi numberRegister) {
        this.numberRegister = numberRegister;
    }

    @XmlTransient
    public List<Report> getReportsList() {
        return reportsList;
    }

    public void setReportsList(List<Report> reportsList) {
        this.reportsList = reportsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Orders[ idOrder=" + idOrder + " ]";
    }
    
}
