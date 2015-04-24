/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
 * @author dominik
 */
@Entity
@Table(name = "Users")
@XmlRootElement
@NamedNativeQueries({
    @NamedNativeQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = SHA2(:password, 256)"),
})
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByMailLogin", query = "SELECT u FROM Users u WHERE u.mailLogin = :mailLogin"),
    @NamedQuery(name = "Users.findByType", query = "SELECT u FROM Users u WHERE u.type = :type"),
    @NamedQuery(name = "Users.findByLastLogon", query = "SELECT u FROM Users u WHERE u.lastLogon = :lastLogon")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100, message = "Niepoprawna długość loginu")
    @Column(name = "mailLogin")
    private String mailLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256, message = "Niepoprawna długość hasła")
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "lastName")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type")
    private short type;
    @Column(name = "lastLogon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operator")
    private Collection<Order1> order1Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mailLogin")
    private Collection<UsersTaxi> usersTaxiCollection;

    public Users() {
    }

    public Users(String mailLogin) {
	this.mailLogin = mailLogin;
    }

    public Users(String mailLogin, String password, String firstName, String lastName, short type) {
	this.mailLogin = mailLogin;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.type = type;
    }

    public String getMailLogin() {
	return mailLogin;
    }

    public void setMailLogin(String mailLogin) {
	this.mailLogin = mailLogin;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public short getType() {
	return type;
    }

    public void setType(short type) {
	this.type = type;
    }

    public Date getLastLogon() {
	return lastLogon;
    }

    public void setLastLogon(Date lastLogon) {
	this.lastLogon = lastLogon;
    }

    @XmlTransient
    public Collection<Order1> getOrder1Collection() {
	return order1Collection;
    }

    public void setOrder1Collection(Collection<Order1> order1Collection) {
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
	hash += (mailLogin != null ? mailLogin.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Users)) {
	    return false;
	}
	Users other = (Users) object;
	if ((this.mailLogin == null && other.mailLogin != null) || (this.mailLogin != null && !this.mailLogin.equals(other.mailLogin))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "model.Users[ mailLogin=" + mailLogin + " ]";
    }
    
}
