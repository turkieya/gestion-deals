package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Fournisseur;

public class FournisseurController implements Initializable {

    @FXML
    private Button btnajout;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnsupp;

    @FXML
    private TableColumn<Fournisseur,String> coladresse;

    @FXML
    private TableColumn<Fournisseur,Integer> colcontact;

    @FXML
    private TableColumn<Fournisseur,String> colemail;

    @FXML
    private TableColumn<Fournisseur,String> colhorraire;

    @FXML
    private TableColumn<Fournisseur,Integer> colid;

    @FXML
    private TableColumn<Fournisseur,String> colnomcom;

    @FXML
    private TextField rechid;

    @FXML
    private TableView<Fournisseur> tvFournisseurs;

    @FXML
    private TextField txtadresse;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txthorraire;

    @FXML
    private TextField txtid;

    @FXML
    private PasswordField txtmdp;

    @FXML
    private TextField txtnomcom;

    @FXML
    private TextField txttel;
    
    ObservableList<Fournisseur> fournisseursList = FXCollections.observableArrayList();
    
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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		afficher_listeFournisseurs();		
	}
    public void afficher_listeFournisseurs() {
  	  try 
  	  {
  		  Connection conn = getConnection();
	      ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM fournisseurs");
	      while(rs.next())
	      { 
	    	  fournisseursList.add(new Fournisseur(rs.getInt("id"),rs.getString("nom_commercial"),rs.getString("horraire"),rs.getString("email"),rs.getString("mdp"),rs.getInt("tel"),rs.getString("adresse")));
	      }
	           
	  } catch (SQLException ex) {
		  Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
	  }
	        
  	  colid.setCellValueFactory(new PropertyValueFactory<>("id")); 
  	  colnomcom.setCellValueFactory(new PropertyValueFactory<>("nom_commercial"));
  	  colhorraire.setCellValueFactory(new PropertyValueFactory<>("horraire"));
  	  colemail.setCellValueFactory(new PropertyValueFactory<>("email")); 
  	  colcontact.setCellValueFactory(new PropertyValueFactory<>("tel"));
  	  coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

  	  tvFournisseurs.setItems(fournisseursList);
  }

    @FXML
    void ajouterfr(ActionEvent event) {
    	
    	PreparedStatement pst; 
    	
        String requete="INSERT INTO fournisseurs VALUES ('" +txtid.getText()+"','"+txtnomcom.getText()+"','"+txthorraire.getText()+"','"+txtemail.getText()+"','"+txtmdp.getText()+"','"+txttel.getText()+"','"+txtadresse.getText()+"');";  
        if (!txtid.getText().equals("") && !txtnomcom.getText().equals("") && !txthorraire.getText().equals("") && !txtemail.getText().equals("") && !txtmdp.getText().equals("") && !txttel.getText().equals("") && !txtadresse.getText().equals("")) 
       {
    	   Connection conn = getConnection();
    	   try {
    		   ResultSet rs = conn.createStatement().executeQuery("SELECT id FROM fournisseurs where id='"+txtid.getText()+"'");
    		   if (rs.next()) 
    		   {
    			   Alert alert = new Alert(AlertType.ERROR,"Fournisseur avec ID : "+txtid.getText()+" existe déjà !",javafx.scene.control.ButtonType.OK);
    			   alert.showAndWait();
    			   txtid.clear();  
    		   }
    		   else {
    	   
    			   try
    			   {	
    				   pst = conn.prepareStatement(requete);
    				   pst.executeUpdate(requete);
    				   System.out.println("Fournisseur ajouté avec succès !");
    				   txtid.clear();  
    				   txtnomcom.clear();
    				   txthorraire.clear();
    				   txtemail.clear();
    				   txtmdp.clear();
    				   txttel.clear();
    				   txtadresse.clear();
            
    			   } catch (SQLException ex) {
    				   System.out.println("Erreur d'ajout !");
    			   }
    			   
    		    tvFournisseurs.getItems().clear();
        
    		    afficher_listeFournisseurs();
        	    Alert alert = new Alert(AlertType.CONFIRMATION,"Fournisseur ajouté avec succès",javafx.scene.control.ButtonType.OK);
        	    alert.showAndWait();}
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
    void chercherfr(ActionEvent event) {
      	int m=0;
   	 try {
        	Connection conn = getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM fournisseurs where id='"+rechid.getText()+"'");
            if (rs.next()) {
           	 txtid.setText(Integer.toString(rs.getInt("id")));
           	 txtnomcom.setText(rs.getString("nom_commercial"));
           	 txthorraire.setText(rs.getString("horraire"));
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
   		 Alert alert = new Alert(AlertType.ERROR,"Aucun fournisseur trouvé avec l'ID : "+rechid.getText()+"",javafx.scene.control.ButtonType.OK);
   		 alert.showAndWait();
   	 }

    }

    @FXML
    void modifierfr(ActionEvent event) {
     	PreparedStatement pst;
    	String requete="UPDATE fournisseurs set id='"+txtid.getText()+"',nom_commercial='"+txtnomcom.getText()+"',horraire='"+txthorraire.getText()+"',email='"+txtemail.getText()+"',mdp='"+txtmdp.getText()+"',tel='"+txttel.getText()+"',adresse='"+txtadresse.getText()+"' where id='"+rechid.getText()+"'";
        if (!txtid.getText().equals("") && !txtnomcom.getText().equals("") && !txthorraire.getText().equals("") && !txtemail.getText().equals("") && !txtmdp.getText().equals("") && !txttel.getText().equals("") && !txtadresse.getText().equals("")) 
        {
         try 
         {	
        	 Connection conn = getConnection();
        	 pst = conn.prepareStatement(requete);
             pst.executeUpdate(requete);
             System.out.println("Fournisseur modifié avec succès !");
             txtid.clear();  
			 txtnomcom.clear();
			 txthorraire.clear();
             txtemail.clear();
             txtmdp.clear();
             txttel.clear();
             txtadresse.clear();
           	 rechid.clear();
             
         } catch (SQLException ex) {
             System.out.println("Erreur de modification !");
         }
         tvFournisseurs.getItems().clear();

         afficher_listeFournisseurs();
 		
 		 Alert alert = new Alert(AlertType.INFORMATION,"Fournisseur modifié avec succès",javafx.scene.control.ButtonType.OK);
		 alert.showAndWait();
       }
       else {
    	   Alert alert = new Alert(AlertType.WARNING,"Veuillez remplir tous les champs !",javafx.scene.control.ButtonType.OK);
   		   alert.showAndWait();
        }

    }

    @FXML
    void supprimerfr(ActionEvent event) {
    	PreparedStatement pst;
    	String requete="DELETE FROM fournisseurs where id='"+rechid.getText()+"'";
    	 try
         {	
    		 Connection conn = getConnection();
    		 pst = conn.prepareStatement(requete);
             pst.executeUpdate(requete);
             System.out.println("Client supprimé avec succès !");
             txtid.clear();  
			 txtnomcom.clear();
			 txthorraire.clear();
             txtemail.clear();
             txtmdp.clear();
             txttel.clear();
             txtadresse.clear();
             rechid.clear();
             tvFournisseurs.getItems().clear();

             afficher_listeFournisseurs();
             Alert alert = new Alert(AlertType.INFORMATION,"Fournisseur supprimé avec succès",javafx.scene.control.ButtonType.OK);
    		 alert.showAndWait();
             
         } catch (SQLException ex) {
             System.out.println("Erreur de modification !");
         }
 
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
    	System.out.println("aaaa");
    	Fournisseur fr =tvFournisseurs.getSelectionModel().getSelectedItem();
    	System.out.println("vvvvv");
    	System.out.println(fr);
    	System.out.println("nom "+fr.getNom_commercial());
    	System.out.println("dccc");
    	txtnomcom.setText(fr.getNom_commercial());
    	/*	txtid.setText(Integer.toString(fr.getId()));
    	txtnomcom.setText(fr.getNom_commercial());
    	txthorraire.setText(fr.getHorraire());
    	txtemail.setText(fr.getEmail());
    	txttel.setText(Integer.toString(fr.getTel()));
    	txtadresse.setText(fr.getAdresse());*/
    	/*try {
        	Connection conn = getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM fournisseurs where id='"+fr.getId()+"'");
            if (rs.next()) {         	 
            	txtmdp.setText(rs.getString("mdp"));
            }
   	 }catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

}