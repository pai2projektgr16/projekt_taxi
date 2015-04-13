package auth;

/**
 * Typ wyliczeniowy w zależności od użytkownika
 * @author dominik
 */
public enum TypeUserEnum {
    Operator(1), Taximan(2);
    
    int typeUser;
    
    TypeUserEnum(int value) {
	typeUser = value;
    }
    
    public static TypeUserEnum fromValue(int value) {  
        for (TypeUserEnum my: TypeUserEnum.values()) {  
            if (my.typeUser == value) {  
                return my;  
            }  
        }  
  
        return null;  
    }  
    
}
