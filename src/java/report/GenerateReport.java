/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Łukasz
 */
public class GenerateReport {
    
    private List<String> messagesErrorList = new ArrayList();
    
    String wybraneID;
    
     public boolean sprawdzID(String id) throws Exception {

	this.wybraneID = id;
	
        
	
	return true;

    }   
     
      public List<String> getMessagesErrorList() {
	return messagesErrorList;
    }
    
}
