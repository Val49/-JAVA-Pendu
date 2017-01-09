package model;
import java.util.*;

/**
 * 
 * @author quentin
 *
 */
public class PenduModel {

	private final int nbErreursMax = 10;
	private char lettre;
	private String motCache;
	private StringBuilder motCourant;
	private ArrayList< String > listeDeMots;
	private int nbErreurs;
	int compteur = 0;
	private ArrayList<Character> liste = new ArrayList();
	private boolean gagner;
	private boolean mauvais;
	private boolean deja;

	/**
	 * constructeur de la classe PenduModel
	 * @param filename le chemin vers le fichier contenant les mots 
	 */
	public PenduModel(String filename){
		this.nbErreurs = 0;
		initialize(filename);

	}
	/**
	 * permet d'initialiser le jeu en creant une liste de mot et en choississant un mot a deviner
	 * @param fileName c'est le chemin vers le fichier contenant les mots 
	 */
	private void initialize(String fileName){

		this.listeDeMots = RWFile.readFile(fileName);
		this.motCache = listeDeMots.get( (int)(Math.random()*listeDeMots.size()));


		this.motCourant = new StringBuilder( this.motCache );

		for(int i = 0; i < this.motCache.length(); i++){
			this.motCourant.setCharAt(i, '-');
		}
	}

	/**
	 * permet de tester si la lettre est contenue dans le mot 
	 * @param l c'est la lettre a testee
	 * @return retourne true si la lettre est dedans et false si elle ne l'ai pas 
	 */
	private boolean essaiLettre(char l){
		boolean isIn = false;

		for(int i = 0; i < this.motCache.length(); i++){
			//si la lettre jouee correspond a un ou plusieurs caractere du mot
			if ( l == this.motCache.charAt(i)){
				isIn = true;
				this.motCourant.setCharAt(i, l);
				compteur++;
			}
		}
		//si la lettre n'est pas dans le mot
		if(isIn == false){
			this.nbErreurs++;
		}
		return isIn;
	}

	/**
	 * permet de jouer un tour de jeu, et verifie si la partie est finie, si le joueur a perdu ou gagner
	 * @param l c'est la lettre qui est jouee
	 * @return retourne true si la partie est finie et false si elle n'est pas finie
	 */
	public boolean jouer(char l){
		boolean boo = false;
		mauvais = false;
		deja = false;
		int limite = this.motCache.length();
		
		//si la lettre n'a pas deja ete jouee
		if(!this.liste.contains(l)){
			//si la lettre jouee est dans le mot
			if (essaiLettre(l) == true ){
				//si toutes les lettres sont decouvertes, partie gagnee
				if ( compteur == limite ){
					boo = true;
					this.gagner = true;
				}
			}
			//si la lettre jouee n'est pas dans le mot
			else{
				mauvais = true;
				//si le nombre d'erreur max est atteint, partie perdue
				if (this.nbErreurs == this.nbErreursMax){
					boo = true;
					this.gagner = false;
				}
			}
		}
		else{
			//si la lettre est deja jouee
			deja = true;
		}
		//ajout de la lettre jouee dans la liste de lettre
		if(deja == false){
			this.liste.add(l);
		}
		return boo;
	}
	/**
	 * permet de retourner le mot avec les lettres devinee pour le moment
	 * @return le mot que l'on a devinee
	 */
	public StringBuilder getMotCourant(){
		return this.motCourant;
	}
	
	/**
	 * permet de retourner un booleen pour savoir si on a gagner
	 * @return retourne true si on a gagner ou false si on a perdu
	 */
	public boolean getGagner(){
		return this.gagner;
	}
	
	/**
	 * permet de recuperer la liste de lettre deja jouees sur forme de  String
	 * @return retourne le string de lettre deja jouees
	 */
	public String getListe(){
		String str = "";
		for(Character c : this.liste){
			str += c+" ";
		}
		return str;
	}
	
	/**
	 * permet de recuperer le mot que l'on doit deviner
	 * @return retourne le mot a deviner
	 */
	public String getMotCache(){
		return this.motCache;
	}
	
	/**
	 * permet de recuperer le nombre actuel d'erreur
	 * @return retourne le int correspondant au nombre d'erreur
	 */
	public int getNbErreurs(){
		return this.nbErreurs;
	}
	
	/**
	 * permet de savoir si une lettre etait mauvaise ou bonne
	 * @return retourne false si la lettre etait bonne, true sinon
	 */
	public boolean getMauvais(){
		return mauvais;
	}
	
	/**
	 * permet de recuperer le boolean pour savoir si la lettre a deja ete jouee
	 * @return
	 */
	public boolean getDeja(){
		return this.deja;
	}
}
