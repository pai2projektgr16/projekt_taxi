package auth;

import java.util.HashMap;

/**
 * Typ wyliczeniowy w zależności od użytkownika
 * @author Dominik Zawadzki
 */
public enum TypeUserEnum {
    Guess(""), Operator("operator.xhtml"), Taximan("taximan.xhtml");
    
    String adress;
    String nameType;
    
    TypeUserEnum(String value) {
	adress = value;
	
	switch(adress) {
	    case "taximan.xhtml" : nameType = "taksówkarz"; break;
	    case "operator.xhtml" : nameType = "operator"; break;
	}
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
    
    public String getNameType() {
	return nameType;
    }
    
}
