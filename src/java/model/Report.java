/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dominik
 */
@Entity
@Table(name = "Report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByIdReport", query = "SELECT r FROM Report r WHERE r.idReport = :idReport"),
    @NamedQuery(name = "Report.findByPrice", query = "SELECT r FROM Report r WHERE r.price = :price"),
    @NamedQuery(name = "Report.findByDistance", query = "SELECT r FROM Report r WHERE r.distance = :distance")})
public class Report implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReport")
    private Integer idReport;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "distance")
    private float distance;
    @Lob
    @Size(max = 65535)
    @Column(name = "attention")
    private String attention;
    @JoinColumn(name = "idOrder", referencedColumnName = "idOrder")
    @ManyToOne
    private Order1 idOrder;

    public Report() {
    }

    public Report(Integer idReport) {
	this.idReport = idReport;
    }

    public Report(Integer idReport, float price, float distance) {
	this.idReport = idReport;
	this.price = price;
	this.distance = distance;
    }

    public Integer getIdReport() {
	return idReport;
    }

    public void setIdReport(Integer idReport) {
	this.idReport = idReport;
    }

    public float getPrice() {
	return price;
    }

    public void setPrice(float price) {
	this.price = price;
    }

    public float getDistance() {
	return distance;
    }

    public void setDistance(float distance) {
	this.distance = distance;
    }

    public String getAttention() {
	return attention;
    }

    public void setAttention(String attention) {
	this.attention = attention;
    }

    public Order1 getIdOrder() {
	return idOrder;
    }

    public void setIdOrder(Order1 idOrder) {
	this.idOrder = idOrder;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (idReport != null ? idReport.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Report)) {
	    return false;
	}
	Report other = (Report) object;
	if ((this.idReport == null && other.idReport != null) || (this.idReport != null && !this.idReport.equals(other.idReport))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "model.Report[ idReport=" + idReport + " ]";
    }
    
}
