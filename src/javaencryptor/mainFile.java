package javaencryptor;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.*;
import java.util.Scanner;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author umang18oct
 */
public class mainFile {
   private Scanner fileScan; 
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel appPanel;
   private JPanel algoPanel;
   private JPanel textPanel;
   private JPanel responsePanel;
   private JPanel buttonPanel;
   private static final int PADDING = 50; 
   ArrayList[] values=new ArrayList[3];
   ArrayList[] names=new ArrayList[3];
   JComboBox[] appsName=new JComboBox[3];

   public mainFile(String fName){
      prepareGUI();
      /*ArrayList tmp;
      tmp = new ArrayList();
      try{
          InputStream ips=new FileInputStream(fName); 
          InputStreamReader ipsr=new InputStreamReader(ips);
          try (BufferedReader br = new BufferedReader(ipsr)) {
              String line;
              while ((line=br.readLine())!=null) {
                  String[] s = line.split(" ");
                  tmp.add(s[0]);
                  tmp.add(s[1]);                  
              }
          }
        }       
        catch (Exception e){
        }
        ArrayList[] apps = new ArrayList[6];
        int j=0; int k=0;
        for(int i=0;i<apps.length;i++){
        if(i%2==0)
            names[j++]=apps[i];            
        else 
            values[k++]=apps[i];
        }
        System.out.println(names);
        for(int i=0;i<appsName.length;i++)
        appsName[i]=new JComboBox(names);    */ 
   } 
 
   public static void main(String[] args){
      mainFile mainObject = new mainFile("C://Users/umang18oct/Documents/NetBeansProjects/JavaEncryptor/src/javaencryptor/applications.txt");      
      mainObject.mainApp();
   }

   private void prepareGUI(){
      mainFrame = new JFrame("Java Encryptor");
      mainFrame.setSize(1050,500);
      mainFrame.setLayout(new GridLayout(7, 1));
      mainFrame.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      
      headerLabel = new JLabel("", JLabel.CENTER);   
      statusLabel = new JLabel("",JLabel.CENTER);    

      appPanel = new JPanel();
      appPanel.setLayout(new FlowLayout(FlowLayout.LEADING,PADDING,0));
      
      algoPanel = new JPanel();
      algoPanel.setLayout(new FlowLayout(FlowLayout.LEADING, PADDING, 0));
      
      textPanel = new JPanel();
      textPanel.setLayout(new FlowLayout(FlowLayout.LEADING, PADDING, 0));
      
      responsePanel = new JPanel();
      responsePanel.setLayout(new FlowLayout(FlowLayout.LEADING, PADDING, 0));
      
      buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(appPanel);
      mainFrame.add(algoPanel);
      mainFrame.add(textPanel);
      mainFrame.add(responsePanel);
      mainFrame.add(buttonPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }

   private void mainApp(){                                    
      headerLabel.setText("Welcome!");
      JLabel appLabel = new JLabel("",JLabel.CENTER);
      appLabel.setText("Choose Application : "); 
      final JComboBox appCombo = new JComboBox(appsName);   
      
      appCombo.setSelectedIndex(0);
      JScrollPane appListScrollPane = new JScrollPane(appCombo);    
      
      JLabel algoLabel = new JLabel("",JLabel.LEFT);
      algoLabel.setText("Choose Algorithm :    "); 
      final DefaultComboBoxModel algoName = new DefaultComboBoxModel();
      algoName.addElement("MDS");
      algoName.addElement("AES");
      algoName.addElement("3DASH");
      algoName.addElement("RC4");
      algoName.addElement("MD5");
      final JComboBox algoCombo = new JComboBox(algoName);    
      algoCombo.setSelectedIndex(0);
      JScrollPane algoListScrollPane = new JScrollPane(algoCombo);
      
      JLabel textLabel = new JLabel("",JLabel.CENTER);
      textLabel.setText("Enter the String :        ");  
      JTextField textField = new JTextField(16);
      
      JLabel responseLabel = new JLabel("",JLabel.CENTER);
      responseLabel.setText("Response :                  ");  
      JTextField responseField = new JTextField(16);
      responseField.setEditable(false);
      JButton copyButton = new JButton("COPY");
      copyButton.addActionListener(new ActionListener (){
          public void actionPerformed(ActionEvent e){
              String responseData = responseField.getText();
              StringSelection responseString = new StringSelection(responseData);
              Clipboard copy = Toolkit.getDefaultToolkit().getSystemClipboard();
              copy.setContents(responseString, null);
          }
      });
      
      JButton encryptButton = new JButton("ENCRYPT");
      encryptButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              String textData = textField.getText();
              String keyData="";
              if (appCombo.getSelectedIndex() != -1) {
                  keyData +=appCombo.getItemAt(appCombo.getSelectedIndex());
              }
              String responseData="";
              String algoData="";
              if (algoCombo.getSelectedIndex() != -1) {
                  algoData +=algoCombo.getItemAt(algoCombo.getSelectedIndex());
              }
              switch (algoData) {
                  case "RC4":
                  {
                      try {
                      responseData+=rc4.encrypt(textData,keyData);
                      } catch (Exception ex) {
                        Logger.getLogger(mainFile.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      break;
                  }
                  case "MD5":
                  {
                      try {
                      responseData+=md5.MD5(textData);
                      } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                      Logger.getLogger(mainFile.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      break;
                  }
                  case "MDS":
                  {
                      //mds obj1= new mds(textData,keyData);
                      break;
                  }
                  case "AES":
                  {           
                      try {
                          responseData+=aes.encrypt(textData,keyData);
                      } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException ex) {
                          Logger.getLogger(mainFile.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      break;
                  }
                  case "3DASH":
                  {
                      //dash3 obj1= new dash3(textData,keyData);
                      break;
                  }
              }
              //String responseData = ""+obj1.encrypt(textData,keyData);
              responseField.setText(responseData);
          }
      });      
      JButton decryptButton = new JButton("DECRYPT");
      decryptButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              String textData = textField.getText();
              String keyData="";
              if (appCombo.getSelectedIndex() != -1) {
                  keyData +=appCombo.getItemAt(appCombo.getSelectedIndex());
              }
              String responseData="";
              String algoData="";
              if (algoCombo.getSelectedIndex() != -1) {
                  algoData +=algoCombo.getItemAt(algoCombo.getSelectedIndex());
              }
              switch (algoData) {
                  case "RC4":
                  {
                    try {
                      responseData+=rc4.decrypt(textData,keyData);
                    } catch (Exception ex) {
                        Logger.getLogger(mainFile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     break;
                  }
                  case "MD5":
                  {
                  
                  }
                  case "MDS":
                  {
                      //mds obj1= new mds(textData,keyData);
                      break;
                  }
                  case "AES":
                  {
                      try {
                          responseData+=aes.decrypt(textData,keyData);
                      } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException ex) {
                          Logger.getLogger(mainFile.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      break;
                  }
                  case "3DASH":
                  {
                      //dash3 obj1= new dash3(textData,keyData);
                      break;
                  }
              }
              //String responseData = ""+obj1.decrypt(textData,keyData);
              responseField.setText(responseData);
          }
      });
      
      statusLabel.setText("Hope you liked it!"); 
      
      appPanel.add(Box.createRigidArea(new Dimension(250,0)));
      appPanel.add(appLabel);
      appPanel.add(appListScrollPane);          
      
      
      algoPanel.add(Box.createRigidArea(new Dimension(250,0)));
      algoPanel.add(algoLabel);
      algoPanel.add(algoListScrollPane);
      
      textPanel.add(Box.createRigidArea(new Dimension(250,0)));
      textPanel.add(textLabel);
      textPanel.add(textField);
      
      responsePanel.add(Box.createRigidArea(new Dimension(250,0)));
      responsePanel.add(responseLabel);
      responsePanel.add(responseField);
      responsePanel.add(copyButton);
      
      buttonPanel.add(encryptButton);
      buttonPanel.add(Box.createRigidArea(new Dimension(50,0)));
      buttonPanel.add(decryptButton);
      
      mainFrame.setVisible(true);             
   }
}
