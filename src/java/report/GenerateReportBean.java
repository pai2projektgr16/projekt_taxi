/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Łukasz
 */
@Named(value = "generateReportBean")
@Dependent
public class GenerateReportBean {

    /**
     * Creates a new instance of GenerateReportBean
     */
    public GenerateReportBean() {
    }
    
}
