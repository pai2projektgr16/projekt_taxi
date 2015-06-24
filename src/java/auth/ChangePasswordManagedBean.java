package auth;

/**
 * Pośredniczy pomiędzy JSF (użytkownikiem wypełniającym formularz) a modelem w celu przypomnienia hasła
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIForm;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Adrian Dorota && Dominik Zawadzki
 */
@Named
@RequestScoped
public class ChangePasswordManagedBean {

    @EJB
    private ChangePassword changePasswordModel;

    private UIForm form = null;
    private UIOutput outputMessage = null;

    private String mailLogin;

    final private String siteTitle = "TaxiSite";
    final private String siteUrl = "http://localhost:8080/Taxi/faces/rememberPasswordConfirm.xhtml";

    public ChangePasswordManagedBean() {

    }

    public UIForm getForm() {
	return form;
    }

    public void setForm(UIForm form) {
	this.form = form;
    }

    public String sendMailAction() throws Exception {

	if (changePasswordModel.sendMailRemember(this.mailLogin, siteUrl, siteTitle)) {
	    form.setRendered(false);
	    outputMessage.setRendered(true);
	} else {
	    for (String message : changePasswordModel.getMessagesErrorList()) {
		FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(message));
	    }

	}
	return "";
    }

    /**
     * Drugi etap przypomnienia hasła
     * @param event 
     */
    public void checkLinkListener(ComponentSystemEvent event) {
	FacesContext ctx = FacesContext.getCurrentInstance();
	try {
	    String link = getFullUrl((HttpServletRequest) ctx.getExternalContext().getRequest());

	    if (changePasswordModel.changePasswordForLink(link, siteUrl, siteTitle)) {
		outputMessage.setRendered(true);
	    } else {
		for (String message : changePasswordModel.getMessagesErrorList()) {
		    ctx.addMessage("messages", new FacesMessage(message));
		}
	    }

	} catch (MalformedURLException ex) {
	    Logger.getLogger(ChangePasswordManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	} catch (Exception ex) {
	    Logger.getLogger(ChangePasswordManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public String getMailLogin() {
	return mailLogin;
    }

    public void setMailLogin(String mailLogin) {
	this.mailLogin = mailLogin;
    }

    public UIOutput getOutputMessage() {
	return outputMessage;
    }

    public void setOutputMessage(UIOutput outputMessage) {
	this.outputMessage = outputMessage;
    }

    private String getFullUrl(HttpServletRequest request) throws MalformedURLException {
	String file = request.getRequestURI();
	if (request.getQueryString() != null) {
	    file += '?' + request.getQueryString();
	}
	URL reconstructedURL = new URL(request.getScheme(),
		request.getServerName(),
		request.getServerPort(),
		file);
	return reconstructedURL.toString();
    }

}
