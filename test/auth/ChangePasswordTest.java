/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dominik
 */
public class ChangePasswordTest {
    
    
//    @Test
//    public void testSendMailRemember() throws Exception {
//	System.out.println("sendMailRemember");
//	String mail = "dominik137@vp.pl";
//	String siteUrl = "http://localhost:8080/taxi";
//	String siteTitle = "Testowy Tytuł Strony";
//	EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//	ChangePassword instance = (ChangePassword)container.getContext().lookup("java:global/classes/ChangePassword");
//	boolean expResult = true;
//	boolean result = instance.sendMailRemember(mail, siteUrl, siteTitle);
//	assertEquals(expResult, result);
//	container.close();
//
//    }

    @Test
    public void testChangePasswordForLink() throws Exception {
	System.out.println("changePasswordForLink");
	String link = "http://localhost:8080/taxi?mail=dominik137@vp.pl&token=6c9f0ad1c55c7e8ed23e6a5c6633723f";
	String siteUrl = "http://localhost:8080/taxi";
	String siteTitle = "Testowy Tytuł Strony";
	EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
	ChangePassword instance = (ChangePassword)container.getContext().lookup("java:global/classes/ChangePassword");
	boolean expResult = true;
	boolean result = instance.changePasswordForLink(link, siteUrl, siteTitle);
	assertEquals(expResult, result);
	container.close();

    }
    
}
