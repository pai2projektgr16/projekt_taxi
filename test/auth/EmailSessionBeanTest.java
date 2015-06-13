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
public class EmailSessionBeanTest {
    
    public EmailSessionBeanTest() {
    }
    
  
    @Test
    public void testSendEmail() {
	System.out.println("sendEmail");
	String to = "dominik137@vp.pl";
	String subject = "Test wysyłania wiadomości dla projektu Taxi";
	String body = "<html><head><meta charset='utf-8'></head><body>Oto testowa wiadomość."
		+ " Test polskich znaków: ąźćłóęńęż. ĄŚŻŁ <br>"
		+ "TEST HTML <b>pogrubienie</b>, <p>akapit</p>"
		+ "</body></html>";
	EmailSessionBean instance = new EmailSessionBean();
	assertTrue(instance.sendEmail(to, subject, body));
	
    }
    
}
