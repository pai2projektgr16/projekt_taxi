package auth;

import java.util.HashMap;

/**
 * Typ wyliczeniowy w zależności od użytkownika
 * @author Dominik Zawadzki
 */
public enum TypeUserEnum {
    Guess(""), Operator("operator.xhtml"), Taximan("taximan.xhtml");
    
    String adress;
    
    TypeUserEnum(String value) {
	adress = value;
    }
    
    public static TypeUserEnum fromValue(int value) {  
        for (TypeUserEnum my: TypeUserEnum.values()) {  
            if (my.ordinal() == value) {  
                return my;  
            }  
        }  
  
        return null;  
    }  
    
    public String getRouteAdress() {
	return adress;
    }
    
    public static Boolean isRouteAdress(String adress) {
	 for (TypeUserEnum my: TypeUserEnum.values()) {  
            if (my.adress.equals(adress)) {  
                return true;
            }  
        }  
	 
	 return false;
    }
    
}
