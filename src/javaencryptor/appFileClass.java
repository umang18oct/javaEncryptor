/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaencryptor;

/**
 *
 * @author umang18oct
 */
public class appFileClass {
    private String appName; 
    private String appKey;
    public appFileClass(String name, String key) { 
        appName = name; 
        appKey = key; 
    } 
    public String getName() {
        return appName; 
    } 
    public String getKey(){ 
        return appKey; 
    } 
}
    
