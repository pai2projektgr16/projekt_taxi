/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dominik
 */
@Entity
@Table(name = "Taxi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taxi.findAll", query = "SELECT t FROM Taxi t"),
    @NamedQuery(name = "Taxi.findByNumberRegister", query = "SELECT t FROM Taxi t WHERE t.numberRegister = :numberRegister")})
public class Taxi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numberRegister")
    private String numberRegister;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "make")
    private String make;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numberRegister")
    private Collection<Order> order1Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numberRegister")
    private Collection<UsersTaxi> usersTaxiCollection;

    public Taxi() {
    }

    public Taxi(String numberRegister) {
	this.numberRegister = numberRegister;
    }

    public Taxi(String numberRegister, String make) {
	this.numberRegister = numberRegister;
	this.make = make;
    }

    public String getNumberRegister() {
	return numberRegister;
    }

    public void setNumberRegister(String numberRegister) {
	this.numberRegister = numberRegister;
    }

    public String getMake() {
	return make;
    }

    public void setMake(String make) {
	this.make = make;
    }

    @XmlTransient
    public Collection<Order> getOrder1Collection() {
	return order1Collection;
    }

    public void setOrder1Collection(Collection<Order> order1Collection) {
	this.order1Collection = order1Collection;
    }

    @XmlTransient
    public Collection<UsersTaxi> getUsersTaxiCollection() {
	return usersTaxiCollection;
    }

    public void setUsersTaxiCollection(Collection<UsersTaxi> usersTaxiCollection) {
	this.usersTaxiCollection = usersTaxiCollection;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (numberRegister != null ? numberRegister.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Taxi)) {
	    return false;
	}
	Taxi other = (Taxi) object;
	if ((this.numberRegister == null && other.numberRegister != null) || (this.numberRegister != null && !this.numberRegister.equals(other.numberRegister))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "model.Taxi[ numberRegister=" + numberRegister + " ]";
    }
    
}
