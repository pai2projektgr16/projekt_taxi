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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dominik
 */
@Entity
@Table(name = "UsersTaxi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersTaxi.findAll", query = "SELECT u FROM UsersTaxi u"),
    @NamedQuery(name = "UsersTaxi.findByIdUsersTaxi", query = "SELECT u FROM UsersTaxi u WHERE u.idUsersTaxi = :idUsersTaxi"),
    @NamedQuery(name = "UsersTaxi.findByMailLogin", query = "SELECT u FROM UsersTaxi u WHERE u.mailLogin = :mailLogin")})
public class UsersTaxi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsersTaxi")
    private Integer idUsersTaxi;
    @JoinColumn(name = "mailLogin", referencedColumnName = "mailLogin")
    @ManyToOne(optional = false)
    private Users mailLogin;
    @JoinColumn(name = "numberRegister", referencedColumnName = "numberRegister")
    @ManyToOne(optional = false)
    private Taxi numberRegister;

    public UsersTaxi() {
    }

    public UsersTaxi(Integer idUsersTaxi) {
	this.idUsersTaxi = idUsersTaxi;
    }

    public Integer getIdUsersTaxi() {
	return idUsersTaxi;
    }

    public void setIdUsersTaxi(Integer idUsersTaxi) {
	this.idUsersTaxi = idUsersTaxi;
    }

    public Users getMailLogin() {
	return mailLogin;
    }

    public void setMailLogin(Users mailLogin) {
	this.mailLogin = mailLogin;
    }

    public Taxi getNumberRegister() {
	return numberRegister;
    }

    public void setNumberRegister(Taxi numberRegister) {
	this.numberRegister = numberRegister;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (idUsersTaxi != null ? idUsersTaxi.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof UsersTaxi)) {
	    return false;
	}
	UsersTaxi other = (UsersTaxi) object;
	if ((this.idUsersTaxi == null && other.idUsersTaxi != null) || (this.idUsersTaxi != null && !this.idUsersTaxi.equals(other.idUsersTaxi))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "model.UsersTaxi[ idUsersTaxi=" + idUsersTaxi + " ]";
    }
    
}
