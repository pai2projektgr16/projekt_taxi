/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

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
public class SessionIdentifierGeneratorTest {
    
    public SessionIdentifierGeneratorTest() {
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

    @Test
    public void testNextSessionId() {
	System.out.println("nextSessionId");
	SessionIdentifierGenerator instance = new SessionIdentifierGenerator();
	String expResult = "";
	String result = instance.nextSessionId();
	assertEquals(expResult, result);
	fail("The test case is a prototype.");
    }
    
}
