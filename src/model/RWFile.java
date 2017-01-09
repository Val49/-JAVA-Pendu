package model;
/*
 *  RWFile.java
 *
 * Created by Isabelle Borne on 27/01/11
 */

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cette classe contient des methodes utilitaires pour lire et ecrire des fichiers de textes
 * a partir de collections ArrayList et HashMap
 */
public class RWFile {
  
  /**
   * cette methode est utilise  pour lire un fichier de texte ligne par ligne
   * et restitue le contenu dans un ArrayLis
   * @param file le fichier a lister
   * @return une arrayList contenant les lignes du fichier
   */
  public static ArrayList<String> readFile (String fileName) {
    ArrayList<String> file = new ArrayList<String>(); 
    
    try {
      //  ouverture du fichier
      Scanner in = new Scanner (new FileReader (fileName)) ;
      
      //  lecture et affichage des lignes une par une
      while (in.hasNextLine() ) {
        file.add(in.nextLine ()) ;
      }
      
      // fermeture du fichier ouvert en lecture
      in.close() ;
    } catch (FileNotFoundException e) {
      System.out.println ("Fichier non trouve : '" + fileName + "'") ;
    } catch (IOException e) {
      e.printStackTrace() ;
    }
    return file;
  } // -------------------------------------------------- readFile()
  
  
  /**
   * cette methode est utilise  pour ecrire un ArrayList dans un fichier de texte ligne par ligne
   * *@param liste un ArrayList contenant les donnees
   * @param fileName le nom du fichier a ecrire
   */
  public static void writeFile(ArrayList<String> liste, String fileName) {
    
    try {
      // ouverture du fichier a ecrire
      PrintWriter out = new PrintWriter (fileName) ;
      for (String ligne : liste)
        out.println (ligne.toString()) ; 
      // fermeture des fichiers
      out.close() ;
      
    } catch (FileNotFoundException e) {
      System.out.println ("Fichier non trouve : '" + fileName + "'") ;
      
    } catch (IOException e) {
      e.printStackTrace() ;
    }
  }// -------------------------------------------------- writeFile()
  
  
  
  /**
   * cette methode est utilise  pour ecrire un HashMap dans un fichier de texte ligne par ligne
   * *@param map un HasMap contenant les donnees
   * @param fileName le nom du fichier a ecrire
   */
  public static void writeMap (HashMap<String, Double> map, String fileName) {
    
    try {
      // ouverture du fichier a ecrire
      PrintWriter out = new PrintWriter (fileName) ;
      for (String key : map.keySet())
        out.println (key + "     " + map.get(key).toString()) ;  // concatene une chaine et un Double.toString()
      // fermeture des fichiers
      out.close() ;
      
    } catch (FileNotFoundException e) {
      System.out.println ("Fichier non trouve : '" + fileName + "'") ;
      
    } catch (IOException e) {
      e.printStackTrace() ;
    }
  } // -------------------------------------------------- writeMap()
  
  
  
}