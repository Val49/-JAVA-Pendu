package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.*;
import controller.*;

import java.awt.image.BufferedImage;
/**
 * 
 * @author quentin
 *
 */
public class Fenetre extends JFrame {
	private PenduModel model;
	
	private JButton boutJouer;
	private JLabel decouvert; 
	private JLabel lettreJouee = new JLabel();
	private JTextField lettreEntree = new JTextField();
	private JPanel panelDessus = new JPanel();
	private JPanel panelDessous = new JPanel();
	private JPanel panelBis = new JPanel();
	private ImageIcon img;
	private JLabel image;
	private JLabel labelText;
	private ReactionButton reactBout;
	private JButton quit;
	private ReactionClicDialog reactDialog;
	
	private JDialog monAide;
	
	private JMenuBar myBar;
	private JMenuItem itemAide;
	
	/**
	 * permet de recuperer le bouton jouer
	 * @return retourne l'instance du bouton jouer
	 */
	public JButton getBoutJouer(){
		return this.boutJouer;
	}
	
	/**
	 * permet de recuperer la lettre entree
	 * @return retourne la lettre entree dans le jtextField
	 */
	public JTextField getLettreEntree(){
		return this.lettreEntree;
	}
	
	/**
	 * permet de recuperer l'item aide
	 * @return retourne l'item aide
	 */
	public JMenuItem getAide(){
		return this.itemAide;
	}
	
	/**
	 * permet de recuperer le bouton quitter
	 * @return retourne le bouton quitter
	 */
	public JButton getQuit(){
		return this.quit;
	}
	/**
	 * permet de recuperer le jDialog monAide
	 * @return retourne l'instance monAide
	 */
	public JDialog getMonAide(){
		return this.monAide;
	}
	
	/**
	 * c'est le constructeur de la classe
	 * il permet d'afficher la fenetre et de mettre en place le decor initial
	 */
	public Fenetre(){
		super ( "Jeu Pendu" );

		this.setSize(1240,680);
		this.setVisible(true);
		// appel constructeur de JFrame
		this.miseEnPlaceDecor();
		// mise en place du décor
		this.attacherReactions();
		// écouteurs des événements utilisateurs
		this.setSize( 1240, 680 );
		// dimensionnement de la fenêtre
		this.setVisible( true );
		// rendre la fenêtre visible
		this.setDefaultCloseOperation ( EXIT_ON_CLOSE );
		// clic sur la croix
		redimensionnement();
	}
	
	/**
	 * permet de mettre le decor en place 
	 */
	private void miseEnPlaceDecor(){
		Font police50 = new Font("Arial",Font.BOLD,50);
		Font police20 = new Font("Arial",Font.BOLD,20);
		Font policeB15 = new Font("Arial",Font.BOLD,15);
				
		this.decouvert = new JLabel("Bienvenue sur le jeu du pendu");
		this.boutJouer = new JButton("commencer la partie ?");
		this.img = new ImageIcon("./image/0.jpg");
		this.image = new JLabel(img);
		this.labelText = new JLabel("Hey Jaaack ! Comment ça va ?");
		
		this.decouvert.setFont(police50);
		this.lettreJouee.setFont(policeB15);
		this.boutJouer.setFont(police20);
		this.labelText.setFont(policeB15);
		this.lettreEntree.setFont(police50);
		
		//mise en place du bouton aide
		this.myBar = new JMenuBar();
		this.itemAide = new JMenuItem("aide");
		myBar.add(this.itemAide);
		this.setJMenuBar(myBar);
		
		//mise en place de la premiere fenetre
		this.setLayout(new GridLayout(2,1));
		/*pendu = new ImageIcon("./pendu.jpg");
		labelCentre = new JLabel(pendu);
		this.add( labelCentre, BorderLayout.CENTER);
		*/
		//mise en place des panel
		this.add(this.panelDessus);
		this.add(this.panelDessous);
		
		//separation paneldessus
		panelDessus.setLayout(new GridLayout(2,1));
		//remplissage panelDessus
		this.decouvert.setHorizontalAlignment((this.panelDessus.getSize().width)/2);
		panelDessus.add(this.decouvert);
		panelDessus.add(this.panelBis);
		panelBis.setLayout(new GridLayout(1,2));
		this.lettreJouee.setHorizontalAlignment((this.lettreJouee.getSize().width)/2);
		panelBis.add(this.lettreJouee);

		panelBis.add(lettreEntree);
		
		//separation panelDessous
		panelDessous.setLayout(new GridLayout(1,3));
		//remplissage panelDessous
		
		panelDessous.add(this.image);
		
		panelDessous.add(this.boutJouer);
		
		this.labelText.setHorizontalAlignment((this.labelText.getSize().width)/2);
		panelDessous.add(this.labelText);
	}
	
	/**
	 * regroupe les differents ecouteur des bouton initiaux 
	 */
	private void attacherReactions(){
		this.addComponentListener ( new WinReaction() );
		this.reactBout = new ReactionButton(this);
		this.boutJouer.addActionListener(reactBout);
		
		ReactionClicItem reactItem = new ReactionClicItem(this);
		this.itemAide.addActionListener(reactItem);
	}

	/**
	 * permet de redimensionner l'image du pendu a la bonne taille 
	 */
	public void redimensionnement(){
		int w = this.image.getSize().width;
		int h = this.image.getSize().height;
		ImageIcon tmp = new ImageIcon(getScaledImage ( this.img.getImage(), w, h ));
		this.image.setIcon(tmp);
	}
	
	/**
	 * permet de changer la taille de l'image 
	 * @param srcImg c'est l'image a etre modifier
	 * @param w c'est la largeur de l'image
	 * @param h c'est la hauteur de l'image
	 * @return retourne l'image avec ses dimensions modifiee
	 */
	private Image getScaledImage ( Image srcImg, int w, int h ){
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return resizedImg;
	}
	
	/**
	 * permet d'initialiser les composant de la fenetre d'accueil
	 */
	public void initialisation(){
		this.lettreEntree.removeActionListener(reactBout);
		this.model = new PenduModel("./dico.txt");
		String str = "./image/"+this.model.getNbErreurs()+".jpg";
		this.img = new ImageIcon(str);
		this.image.setIcon(this.img);
		this.decouvert.setText(this.model.getMotCourant().toString());
		this.labelText.setText("Entrer une lettre dans la case au dessus");
		this.boutJouer.setText("Recommencer une partie ?");
		this.lettreJouee.setText("Les lettres que vous avez joué : ");
		this.lettreEntree.setText("");
		this.lettreEntree.addActionListener(this.reactBout);
		this.redimensionnement();
	}
	
	/**
	 * permet de mettre ajour la fenetre apres un tour du jeu 
	 * @param c c'est la lettre qui a ete jouee pour le tour
	 */
	public void tour(char c){
		
		//si la partie est finie
		if ( this.model.jouer(c) == true ){
			//si la partie est gagner
			if(this.model.getGagner() == true){
				this.decouvert.setText(this.model.getMotCourant().toString());
				this.lettreJouee.setText("Les lettres que vous avez joués : "+this.model.getListe());
				this.labelText.setText("partie gagnée youhouuuuuuu * c'est la fête *");
				//permet d'empecher de jouer une lettre une fois la partie finie
				this.lettreEntree.removeActionListener(reactBout);
			}
			//si la partie est perdue
			else{
				String str = "./image/"+this.model.getNbErreurs()+".jpg";
				this.img = new ImageIcon(str);
				this.image.setIcon(this.img);
				redimensionnement();
				this.decouvert.setText(this.model.getMotCourant().toString());
				this.lettreJouee.setText("Les lettres que vous avez joués : "+this.model.getListe());
				this.decouvert.setText("Le mot à deviner était : "+this.model.getMotCache());
				this.labelText.setText("partie perdu muhahahaha * c'est la piquette Jack ! *");
				//permet d'empecher de jouer une lettre une fois la partie finie
				this.lettreEntree.removeActionListener(reactBout);
			}
		}
		//si la partie n'est pas finie
		else{
			this.labelText.setText("Tu es meilleur qu'au jokari Jack !");
			//si la lettre est mauvaise
			if( this.model.getMauvais() == true){
				String str = "./image/"+this.model.getNbErreurs()+".jpg";
				this.img = new ImageIcon(str);
				this.image.setIcon(this.img);
				this.labelText.setText("Mauvaise lettre hahaha * t'es mauuuuvais Jack *");
			}
			//si la lettre a deja ete jouee
			if(this.model.getDeja() == true){
				this.labelText.setText("Vous avez déjà joué cette lettre.");
			}
			this.lettreEntree.setText("");
			this.lettreJouee.setText("Les lettres que vous avez joués : "+this.model.getListe());
			this.decouvert.setText(this.model.getMotCourant().toString());
			
		}
	}
	
	/**
	 * permet d'afficher la fenetre d'aide
	 */
	public void dialog(){

		this.monAide = new JDialog ( this, "Aide" );
		monAide.setSize ( 600, 550 );
		monAide.setVisible ( true );
		monAide.setLayout(new BorderLayout());
		
		JTextArea text = new JTextArea("MENU D'AIDE\n\n\n- Pour jouer, appuyer sur le bouton commencer la partie.\n- Ecrivez une lettre dans la zone de texte et validez avec la touche \"entr�e\"\n- Une fois la partie finie vous pouvez recommencer avec le bouton \"recommencer une partie\"");
		this.quit = new JButton("fermer la fenetre");
		
		BorderLayout border = new BorderLayout();
		monAide.setLayout( border);
		
		
		monAide.add(text, BorderLayout.CENTER);
		monAide.add(quit, BorderLayout.SOUTH);
		
		this.reactDialog = new ReactionClicDialog(this.monAide, this);
		this.quit.addActionListener(this.reactDialog);
		
	}
		
	
	/**
	 * classe interne qui permet le redimensionnement de la fenetre et de l'image 
	 * @author quentin
	 *
	 */
	private class WinReaction extends ComponentAdapter {

		/**
		 * methode de redimensionnement de la fenetre et de l'image
		 */
		public void componentResized ( ComponentEvent e ) {
			redimensionnement();
		}
	}
}
