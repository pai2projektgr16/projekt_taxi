package auth;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Users;

/**
 * Ziarenko do zmiany i przypomnienia hasła użytkownikom
 *
 * @author Dominik Zawadzki && Adrian Dorota
 */
@Stateless
public class ChangePassword {

    String mail;

    @PersistenceContext
    private EntityManager em;

    @EJB
    private EmailSessionBean mailBean;

    private List<String> messagesErrorList = new ArrayList();

    /**
     * 1 etap przypomnienia hasła, tzn. wykonanie sprawdzenia czy taka osoba istnieje w systemie (mail) Wygenerowanie
     * linku i wysłanie wiadomości (maila) z dalszymi instrukcjami
     *
     * @param mail
     * @param siteUrl adres strony internetowej do ktorej ma byc link w mailu
     * @param siteTitle tytuł strony
     * @return
     */
    public boolean sendMailRemember(String mail, String siteUrl, String siteTitle) throws Exception {

	this.mail = mail;
	Users user = fetchUser();

	if (user == null) {
	    messagesErrorList.add("Nie znaleziono takiego maila w systemie");
	    return false;
	}

	String link = generateLink(user, siteUrl);

	sendMailForLink(user, link, siteTitle, siteUrl);
	return true;

    }

    /**
     * 2 etap (końcowy) przypomnienia hasła. Użytkownik przesyła link, dzięki temu jest pewność że należy zmienić hasło
     *
     * Sprawdzenie poprawności zgłoszenia (linku), zmiana hasła, wysłanie nowego hasła na maila
     *
     * @param link
     * @param siteUrl adres internetowy całej witryny
     * @param siteTitle tytuł całej witryny
     * @throws Exception gdy nie uda się zwerfikować linku potwierdzającego
     * @return
     */
    public boolean changePasswordForLink(String link, String siteUrl, String siteTitle) throws Exception {
	try {
	    mail = fetchMailFromLink(link);

	    Users user = fetchUser();

	    if (user == null) {
		throw new InvalidURLException();
	    }

	    checkLink(link, user);

	    String newPassword = (new SessionIdentifierGenerator()).nextSessionId();
	    changePasswordDatabase(user, newPassword);

	    sendMailForPassword(user, newPassword, siteTitle, siteUrl);

	    return true;
	} catch (InvalidURLException ex) {
	    messagesErrorList.add("Niepoprawny link potwierdzający");
	    return false;
	}
    }

    public List<String> getMessagesErrorList() {
	return messagesErrorList;
    }
    
    

    /**
     * Zwraca informacje o użytkowniku lub null gdy go nie znaleziono
     *
     * @return
     */
    private Users fetchUser() {
	try {

	    Query query = em.createNamedQuery("Users.findByMailLogin")
		    .setParameter("mailLogin", mail);
	    return (Users) query.getSingleResult();

	} catch (NoResultException ex) {
	    return null;
	}
    }

    private String generateLink(Users user, String siteUrl) throws NoSuchAlgorithmException, UnsupportedEncodingException {

	return siteUrl + "?mail=" + user.getMailLogin() + "&token=" + generateToken(user);

    }

    private String generateToken(Users user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	String tokenInput = user.getMailLogin() + user.getPassword();

	MessageDigest md = MessageDigest.getInstance("MD5");
	md.update(tokenInput.getBytes());
	byte[] digest = md.digest();
	StringBuffer sb = new StringBuffer();
	for (byte b : digest) {
	    sb.append(String.format("%02x", b & 0xff));
	}
	return sb.toString();
    }

    private void sendMailForLink(Users user, String link, String siteTitle, String siteUrl) {

	String topic = "Przypomnienie hasła - " + siteTitle;

	String body = "Wtiaj <b>" + user.getFirstName() + "</b>, otrzymujesz ten mail dlatego, że zażądałeś "
		+ "przypomnienia hasła.<br><br>Wejdź pod ten adres aby potwierdzić zmianę hasła <a href='"
		+ link + "'>" + link + "</a>";
	body += "<hr>E-mail wysłano automatycznie ze strony <a href='" + siteUrl + "'>" + siteUrl + "</a>";

	mailBean.sendEmail(mail, topic, body);

    }

    private void sendMailForPassword(Users user, String newPassword, String siteTitle, String siteUrl) {

	String topic = "Przypomnienie hasła cz.2 - " + siteTitle;

	String body = "Wtiaj <b>" + user.getFirstName() + "</b>, otrzymujesz ten mail dlatego, że zażądałeś "
		+ "przypomnienia hasła.<br><br>To jest Twoje nowe hasło :<b> " + newPassword + "</b>";
	body += "<hr>E-mail wysłano automatycznie ze strony <a href='" + siteUrl + "'>" + siteUrl + "</a>";

	mailBean.sendEmail(mail, topic, body);

    }

    private void checkLink(String link, Users user) throws InvalidURLException, NoSuchAlgorithmException, UnsupportedEncodingException {

	String token = getUrlParameters(link).get("token");

	if (token == null) {
	    throw new InvalidURLException();
	}

	if (!token.equals(generateToken(user))) {
	    throw new InvalidURLException();
	}

    }

    private String fetchMailFromLink(String link) throws InvalidURLException {

	try {

	    String mail = getUrlParameters(link).get("mail");

	    if (mail == null) {

		throw new InvalidURLException();

	    }

	    return mail;

	} catch (UnsupportedEncodingException ex) {
	    throw new InvalidURLException();
	}

    }

    private static Map<String, String> getUrlParameters(String url)
	    throws UnsupportedEncodingException {
	Map<String, String> params = new HashMap();

	String query = url.split("\\?")[1];

	for (String param : query.split("&")) {
	    String pair[] = param.split("=");
	    String key = URLDecoder.decode(pair[0], "UTF-8");
	    String value = "";
	    if (pair.length > 1) {
		value = URLDecoder.decode(pair[1], "UTF-8");
	    }
	    params.put(key, value);
	}

	return params;
    }

    private void changePasswordDatabase(Users user, String newPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	user.setPassword(newPassword);
	em.persist(user);
    }

}

final class SessionIdentifierGenerator {

    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
	return new BigInteger(130, random).toString(32);
    }
}

class InvalidURLException extends Exception {
}
