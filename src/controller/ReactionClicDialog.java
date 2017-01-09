package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import model.*;
import view.*;

/**
 * 
 * @author quentin
 *
 */
public class ReactionClicDialog implements ActionListener {
	
	private JDialog log;
	private Fenetre laFen;
	/**
	 * c'est le constructeur de la classe
	 * @param log c'est l'instance du JDialog
	 * @param f c'est l'instance de la fenetre principale
	 */
	public ReactionClicDialog( JDialog log, Fenetre f ){
		this.log = log;
		this.laFen = f;
	}

	/**
	 * permet de recuperer l'action sur le JDialog
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		//si l'action est sur le bouton fermer la fenetre
		if(source == this.laFen.getQuit()){
			this.laFen.getMonAide().dispose();
		}
	}
}
