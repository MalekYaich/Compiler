package Analyse_Lexicale;
import java.util.ArrayList;




public class anallex {
	
static ArrayList<String> tab= new ArrayList<String>();
static ArrayList<String> tab1= new ArrayList<String>();

public Scanner anaLex1=new Scanner("C:\\Users\\lahya\\eclipse-workspace\\Projet\\src\\Analyse_Lexicale\\a22.txt");
public Scanner anaLex2=new Scanner("C:\\Users\\lahya\\eclipse-workspace\\Projet\\src\\Analyse_Lexicale\\a22.txt");

    public void afficheLexeme() {
        UniteLexicale u2=null;
        do {
            u2=anaLex2.lexemeSuivant();
            System.out.println(u2);    
        } while(u2.getCategorie()!=Categorie.EOF);

    }


    public ArrayList<String> lecture() {
        int i =0 ;
    	boolean b = false ;
        UniteLexicale ul=null;
        do {
            ul=anaLex1.lexemeSuivant();
            tab.add(ul.toString());
            tab1.add(ul.getCat());
            //System.out.println(ul);
          if (ul.getCat()=="NUL")
            {
            i++;
            System.out.println("erreur lexical "+i +" : "+ul.getLexeme());
            b= true ; }
           
        } while(ul.getCategorie()!=Categorie.EOF);
        
       if (b)
       {
        System.out.println("total erreurs : "+i );
        return (new ArrayList<String>());
       } 
       else {
           System.out.println("analyse lexical valide");
           return(tab1);}

    }
}








