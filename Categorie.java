package Analyse_Lexicale;

public enum Categorie {
    EOF,
    $,
    NUL,
    Commentaire,
//les terminaux
    ID,
    
    Entier,Reel, Bool, Chaine, Caractere,//Déclaration des types
    Tab,Int, Float,String,Char, False,True, //valeur des types
   
      
    OppAff,
    OppRel,
    OppArith,
    OppLog,

     
    
    CroFer, CroOuv, ParFer, ParOuv, v, pv ,de ,Dp , // ..
    
    Pour, faire,
    Finpour,
    
    Si,Alors,
    Sinon,
    FinSi, 
        
    Afficher, Lire, 
    
    Debut,
    Fin, 
     ;
    
//les non terminaux
	
    


    public static final int MIN=3, MAX=13, MAX1=24;


/*La méthode java string toLowerCase () renvoie la chaine en minuscules. En d'autres termes,
il convertit tous les caract�res de la cha�ne en minuscules. */


    public String toString() {
        return this.name();
    }
    /*
La méthode equalsIgnoreCase() compare deux cha�nes en ignorant les diff�rences entre
minuscules et majuscules et renvoie � true � si les cha�nes sont �gales sinon renvoie � false �.
*/
    public static Categorie toCategorie(String s) {
        for(Categorie c:Categorie.values())
            if(c.toString().equalsIgnoreCase(s))
                return c;
        return null;
    }


    /*La m�thode ordinal() permet de retrouver le num�ro d'ordre d'un �l�ment �num�r�,
     dans la liste de tous les �l�ments d'une �num�ration. Le premier num�ro d'ordre est 0.
    */
    public boolean estTerminal() {
        return ordinal()>=MIN && ordinal()<=MAX;
    }

    public boolean estNonTerminal() {
        return ordinal()>MAX;
    }
}

