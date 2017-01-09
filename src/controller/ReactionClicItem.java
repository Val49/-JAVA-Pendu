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
	public class ReactionClicItem implements ActionListener {
	private Fenetre laFen;
	
	/**
	 * c'est le constructeur de la classe
	 * @param f c'est l'instance de la fenetre principale
	 */
	public ReactionClicItem( Fenetre f){
		this.laFen = f;
	}
	
	/**
	 * permet de verifier les action sur les items de la fenetre
	 */
	public void actionPerformed( ActionEvent e ){
		Object source = e.getSource();
		
		//si on clic sur le bouton aide de la fenetre on ouvre le JDialog
		if( source == this.laFen.getAide()){
			this.laFen.dialog();
		}
		
	}

}
