package Analyse_Lexicale;

public class UniteLexicale {
    private Categorie categorie;
    private Object lexeme;

 
        public UniteLexicale(Categorie categorie, Object lexeme) {
            this.categorie=categorie;
            this.lexeme=lexeme;
        }

        public Categorie getCategorie() {
            return categorie;
        }
        public String getLexeme() {
            return lexeme.toString();}

        public String getCat()
        {
        	return categorie.toString();
        }
        

        public String toString() {
            return "<"+categorie.toString()+","+lexeme+">";
        }
    }

