/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_application_dj_kstra;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Me
 */
public class Log {
    
    public static List<String> log_list=new ArrayList();
    
    public static void add_to_list(String string){
        log_list.add(string);
    }
    public static String show(){
        String s="";
        for (String ss:log_list){
            s=s+"\n"+ss;
        }
        return s;
    }
    
    
}
