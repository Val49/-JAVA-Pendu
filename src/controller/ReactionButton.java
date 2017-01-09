package controller;

import model.*;
import view.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

/**
 * 
 * @author quentin
 *
 */
public class ReactionButton implements ActionListener {
	private Fenetre laFen;
	
	/**
	 * c'est le constructeur de la classe
	 * @param f c'est l'instance de la fenetre
	 */
	public ReactionButton( Fenetre f ){
		this.laFen = f;
	}
	
	
	/**
	 * permet de verifier les action sur les boutons de l'interface
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		//si on clic sur le bouton "commencer la partie"
		if(source == this.laFen.getBoutJouer()){
			laFen.initialisation();
		}
		
		//si on entre une lettre et que l'on appuye sur la touche "entree
		if(source == this.laFen.getLettreEntree()){
			String str = "";
			str = laFen.getLettreEntree().getText();
			char c = str.charAt(0);
			laFen.tour(c);
		}
		
		
		
	}


}
