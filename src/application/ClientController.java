package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Client;

public class ClientController implements Initializable {

    @FXML
    private Button btnajout;
    
    @FXML
    private Button btnmodifier;
    
    @FXML
    private Button btnsupp;

    @FXML
    private TableColumn<Client,String> coladresse;

    @FXML
    private TableColumn<Client,String> colemail;

    @FXML
    private TableColumn<Client,Integer> colcin;

    @FXML
    private TableColumn<Client,Integer> colcontact;

    @FXML
    private TableColumn<Client,String> colnom;

    @FXML
    private TableColumn<Client,String> colprenom;

    @FXML
    private TableView<Client> tvClients;
    
    ObservableList<Client> clientsList = FXCollections.observableArrayList();
    
	@FXML
    private TextField txtcin;
	@FXML
    private TextField txtnom;
	@FXML
    private TextField txtprenom; 
    @FXML
    private TextField txtemail;
    @FXML
    private PasswordField txtmdp;
	@FXML
    private TextField txttel;
	@FXML 
    private TextField txtadresse;
	@FXML
    private TextField rechcin;
	
	public Connection getConnection() {
		Connection  conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_deals","root","");
			return conn;
		} catch(Exception ex) {
			System.out.print("Error: "+ex.getMessage());
			return null; 
		}
	}
	 @Override
		public void initialize(URL url, ResourceBundle rb) {

		 afficher_listeClients();
		/* if(txtcin.getText().length()>0)
			 btnmodifier.setDisable(false);
		 else
			 btnmodifier.setDisable(true);*/
	      
	    }
    @FXML
    void ajouterclt(ActionEvent event) {
    	PreparedStatement pst; 
    	
        String requete="INSERT INTO clients VALUES ('" +txtcin.getText()+"','"+txtnom.getText()+"','"+txtprenom.getText()+"','"+txtemail.getText()+"','"+txtmdp.getText()+"','"+txttel.getText()+"','"+txtadresse.getText()+"');";   
       if (!txtcin.getText().equals("") && !txtnom.getText().equals("") && !txtprenom.getText().equals("") && !txtemail.getText().equals("") && !txtmdp.getText().equals("") && !txttel.getText().equals("") && !txtadresse.getText().equals("")) 
       {
    	   Connection conn = getConnection();
    	   try {
           ResultSet rs = conn.createStatement().executeQuery("SELECT CIN FROM clients where CIN='"+txtcin.getText()+"'");
           if (rs.next()) {
        	   Alert alert = new Alert(AlertType.ERROR,"Client avec le Cin : "+txtcin.getText()+" existe déjà !",javafx.scene.control.ButtonType.OK);
      		 alert.showAndWait();
      		 txtcin.clear();  
 
    	   }
           else {
    	   
        try 
        {	
           pst = conn.prepareStatement(requete);
            pst.executeUpdate(requete);
            System.out.println("Client ajouté avec succès !");
            //sendmail();
            txtcin.clear();  
            txtnom.clear();
            txtprenom.clear();
            txtemail.clear();
            txtmdp.clear();
            txttel.clear();
            txtadresse.clear();
             
        } catch (SQLException ex) {
            System.out.println("Erreur d'ajout !");
        }
        tvClients.getItems().clear();
        
        afficher_listeClients();
        Alert alert = new Alert(AlertType.CONFIRMATION,"Client ajouté avec succès",javafx.scene.control.ButtonType.OK);
  		alert.showAndWait();
  		}
    	   }catch (SQLException ex) {
               Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       else {
    	   Alert alert = new Alert(AlertType.WARNING,"Veuillez remplir tous les champs !",javafx.scene.control.ButtonType.OK);
  		 alert.showAndWait();
       } 
    }
    @FXML
    void chercherclt(ActionEvent event) {
    	int m=0;
    	 try {
         	Connection conn = getConnection();
             ResultSet rs = conn.createStatement().executeQuery("SELECT CIN,nom,prenom,email,mdp,tel,adresse FROM clients where CIN='"+rechcin.getText()+"'");
             if (rs.next()) {
            	 txtcin.setText(Integer.toString(rs.getInt("CIN")));
            	 txtnom.setText(rs.getString("nom"));
            	 txtprenom.setText(rs.getString("prenom"));
            	 txtemail.setText(rs.getString("email"));
            	 txtmdp.setText(rs.getString("mdp"));
            	 txttel.setText(Integer.toString(rs.getInt("tel")));
            	 txtadresse.setText(rs.getString("adresse"));
            	 m=1;
             }
    	 }catch (SQLException ex) {
             Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
         }
    	 if (m==0) {
    		 Alert alert = new Alert(AlertType.ERROR,"Aucun client trouvé avec le Cin : "+rechcin.getText()+"",javafx.scene.control.ButtonType.OK);
    		 alert.showAndWait();
    	 }
    	
    }
    @FXML
    void modifierclt(ActionEvent event) {
    	PreparedStatement pst;
    	String requete="UPDATE clients set CIN='"+txtcin.getText()+"',nom='"+txtnom.getText()+"',prenom='"+txtprenom.getText()+"',email='"+txtemail.getText()+"',mdp='"+txtmdp.getText()+"',tel='"+txttel.getText()+"',adresse='"+txtadresse.getText()+"' where CIN='"+rechcin.getText()+"'";
        if (!txtcin.getText().equals("") && !txtnom.getText().equals("") && !txtprenom.getText().equals("") && !txtemail.getText().equals("") && !txtmdp.getText().equals("") && !txttel.getText().equals("") && !txtadresse.getText().equals("")) 
        { 
         try
         {	Connection conn = getConnection();
            pst = conn.prepareStatement(requete);
             pst.executeUpdate(requete);
             System.out.println("Client modifié avec succès !");
             txtcin.clear(); 
             txtnom.clear();
             txtprenom.clear();
             txtemail.clear();
             txtmdp.clear(); 
             txttel.clear();
             txtadresse.clear();
             rechcin.clear();
             
             
         } catch (SQLException ex) {
             System.out.println("Erreur de modification !");
         }
         tvClients.getItems().clear();

         afficher_listeClients();
 		
 		Alert alert = new Alert(AlertType.INFORMATION,"Client modifié avec succès",javafx.scene.control.ButtonType.OK);
		 alert.showAndWait();
        }
        else {
     	   Alert alert = new Alert(AlertType.WARNING,"Veuillez remplir tous les champs !",javafx.scene.control.ButtonType.OK);
   		 alert.showAndWait();
        } 

    }

    @FXML
    void supprimerclt(ActionEvent event) {

    	PreparedStatement pst;
    	String requete="DELETE FROM Clients where CIN='"+txtcin.getText()+"'";
    	 try
         {	Connection conn = getConnection();
            pst = conn.prepareStatement(requete);
             pst.executeUpdate(requete);
             System.out.println("Client supprimé avec succès !");
             txtcin.clear(); 
             txtnom.clear();
             txtprenom.clear();
             txtemail.clear();
             txtmdp.clear();
             txttel.clear();
             txtadresse.clear();
             rechcin.clear();
             tvClients.getItems().clear();

             afficher_listeClients();
             Alert alert = new Alert(AlertType.INFORMATION,"Client supprimé avec succès",javafx.scene.control.ButtonType.OK);
    		 alert.showAndWait();
             
         } catch (SQLException ex) {
             System.out.println("Erreur de suppression !");
         }
       
    }
    public void afficher_listeClients() {
    	  try {
	        	Connection conn = getConnection();
	            ResultSet rs = conn.createStatement().executeQuery
	            ("SELECT CIN,nom,prenom,email,mdp,tel,adresse FROM clients");
	            while(rs.next())
	            { 
	            	clientsList.add(new Client(rs.getInt("CIN"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("mdp"),rs.getInt("tel"),rs.getString("adresse")));
	            }
	           
	        } catch (SQLException ex) {
	            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
	        } 
	         
			colcin.setCellValueFactory(new PropertyValueFactory<>("CIN")); 
			colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
			colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			colemail.setCellValueFactory(new PropertyValueFactory<>("email")); 
			colcontact.setCellValueFactory(new PropertyValueFactory<>("tel"));
			coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

			tvClients.setItems(clientsList);
    }
    
    public void sendmail()
    {
        Connection c = getConnection() ;
    	String host="smtp.gmail.com";   
    	String from="turkieya99@gmail.com";  
    	String login="turkieya99@gmail.com";
    	String pwd="Eyaturki2infoE";
    	String to ="selmiahmed098@gmail.com "; 


    			Transport t ;    
    			Properties props = System.getProperties(); 
    			props.put("mail.smtp.host", host); 
    			Session session = Session.getDefaultInstance(props); 
    			MimeMessage message = new MimeMessage(session);   
    			try {   message.setFrom(new InternetAddress(from));  
    			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));   
    			message.setSubject("gg"); 
    	                message.setText("1111");
    	                /*message.setText(li);
    	                message.setText(adr);
    	                message.setText(cb);
    			message.setText(des);
    	                message.setText(pg);*/
    			System.out.println("Tentative de connexion...");   t = session.getTransport("smtps");
    			t.connect(host, login, pwd);      
    			System.out.println("Envoi de mail...");  
    			t.sendMessage(message, message.getAllRecipients());   
    			System.out.println("Mail envoyé avec succès");      } 
    			catch (Exception ex){ex.printStackTrace();}  


    }

} 
