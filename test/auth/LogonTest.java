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
public class LogonTest {

    public LogonTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of executeLogon method, of class Logon.
     */
    @Test
    public void testExecuteLogon() throws Exception {
	System.out.println("executeLogon");

	EJBContainer ejbContainer = EJBContainer.createEJBContainer();

        Logon instance = (Logon) ejbContainer.getContext().lookup("java:global/simple-stateless/CalculatorBean");

	String expResult = "";
	String result = instance.executeLogon();
	assertTrue((result == null || result.length() > 0));

    }

//    /**
//     * Test of isLogged method, of class Logon.
//     */
//    @Test
//    public void testIsLogged() {
//	System.out.println("isLogged");
//	Logon instance = new Logon();
//	Boolean expResult = null;
//	Boolean result = instance.isLogged();
//	assertEquals(expResult, result);
//	// TODO review the generated test code and remove the default call to fail.
//	fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTypeUser method, of class Logon.
//     */
//    @Test
//    public void testGetTypeUser() {
//	System.out.println("getTypeUser");
//	Logon instance = new Logon();
//	TypeUserEnum expResult = null;
//	TypeUserEnum result = instance.getTypeUser();
//	assertEquals(expResult, result);
//	// TODO review the generated test code and remove the default call to fail.
//	fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUser method, of class Logon.
//     */
//    @Test
//    public void testGetUser() {
//	System.out.println("getUser");
//	Logon instance = new Logon();
//	Users expResult = null;
//	Users result = instance.getUser();
//	assertEquals(expResult, result);
//	// TODO review the generated test code and remove the default call to fail.
//	fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUserName method, of class Logon.
//     */
//    @Test
//    public void testGetUserName() {
//	System.out.println("getUserName");
//	Logon instance = new Logon();
//	String expResult = "";
//	String result = instance.getUserName();
//	assertEquals(expResult, result);
//	// TODO review the generated test code and remove the default call to fail.
//	fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of logout method, of class Logon.
//     */
//    @Test
//    public void testLogout() {
//	System.out.println("logout");
//	Logon instance = new Logon();
//	instance.logout();
//	// TODO review the generated test code and remove the default call to fail.
//	fail("The test case is a prototype.");
//    }
}
