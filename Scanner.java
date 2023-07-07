package Analyse_Lexicale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class Scanner {
    public ArrayList<Character> fluxCaracteres;
    private int indiceCourant;
    private char caractereCourant;
    private boolean eof;

    public Scanner() {
        this("");
    }

    public Scanner(String nomFich) {
        BufferedReader f=null;
        int car=0;
        fluxCaracteres=new ArrayList<Character>();
        indiceCourant=0;
        eof=false;
        try {
            f=new BufferedReader(new FileReader(nomFich));
        }
        catch(IOException e) {
            System.out.println("taper votre texte ci-dessous (ctrl+z pour finir)");
            f=new BufferedReader(new InputStreamReader(System.in));
        }

        try {
            while((car=f.read())!=-1)
                fluxCaracteres.add((char)car);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void caractereSuivant() {
        if(indiceCourant<fluxCaracteres.size())
            caractereCourant=fluxCaracteres.get(indiceCourant++);
        else
            eof=true;
    }

    public void reculer() {
        if(indiceCourant>0)
            indiceCourant--;
    }

   public UniteLexicale lexemeSuivant() {
		caractereSuivant();
		
		while(eof || Character.isWhitespace(caractereCourant)) { 
			if (eof)
				return new UniteLexicale(Categorie.EOF, "");
			caractereSuivant();
		}
	    if(caractereCourant=='s')
			return getBoucleCondit();
		
		else if(caractereCourant=='a')
			return getA();
		
		else if(caractereCourant=='f')
			return getF();
		
		else if(caractereCourant=='t')
			return getT();
		
		else if(caractereCourant=='d')
			return getDe();
		
	    else if(caractereCourant=='e')
			return getEntier();
		
		else if(caractereCourant=='r')
			return getReel();
		
		else if(caractereCourant=='b')
			return getBool();
		
		else if(caractereCourant=='p')
			return getPour();
		
		else if(caractereCourant=='l')
			return getLire();
		
		else if(caractereCourant=='"')
			return getString();

		else if(caractereCourant=='#')
		return getCommentaire();
		
		else if(caractereCourant =='c')
			return getC();
		
 		
		else if(Character.isLetter(caractereCourant))
		{
			StringBuffer sb=new StringBuffer();
			return getID(sb);
		}
			
		else if(Character.isDigit(caractereCourant))
			return getNombre();
		else if(caractereCourant==';')
			return new UniteLexicale(Categorie.pv,";");
		else if(caractereCourant==':')
			return getOppAff();
		else if(caractereCourant=='<' || caractereCourant=='>' ||caractereCourant=='=')
			return getOPPRel();
		else if(caractereCourant=='+'|| caractereCourant=='-' || caractereCourant=='*' || caractereCourant=='/')
			return getOppArith();
		else if(caractereCourant=='&'|| caractereCourant=='|' || caractereCourant=='!'  )
			return getOppLog();
		else if(caractereCourant=='.')
			return getDp();
		
		
		else if(caractereCourant=='{')	
			return new UniteLexicale(Categorie.Debut,"{"); 
		else if(caractereCourant=='}')
			return new UniteLexicale(Categorie.Fin,"}"); 
		else if(caractereCourant=='(')
			return new UniteLexicale(Categorie.ParOuv,"("); 
		else if(caractereCourant==')')
			return new UniteLexicale(Categorie.ParFer,")");
		else if(caractereCourant=='[')
			return new UniteLexicale(Categorie.CroOuv,"[");
		else if(caractereCourant==']')
			return new UniteLexicale(Categorie.CroFer,"]");
		else if(caractereCourant==',')
			return new UniteLexicale(Categorie.v,","); 
		else 
		{
			return new UniteLexicale(Categorie.NUL,caractereCourant+" mal placé");
		}
			
		

	}
	
	
	
private UniteLexicale getDp() {
	 int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
			case 0 :	
				sb.append(caractereCourant);
				caractereSuivant();
				etat=1;
				break;
			case 1	 : 
				if(caractereCourant=='.')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=2;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
			
			case 2 :
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.Dp,sb.toString());}
				else 
					return getID(sb);
				
	
}}}

private UniteLexicale getC() {
	 int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
			case 0 :	
				sb.append(caractereCourant);
				caractereSuivant();
				etat=1;
				break;
			
			case 1 : 
				if(caractereCourant=='h')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=2;
					}
				else if(caractereCourant=='a')
				{
				sb.append(caractereCourant);
				caractereSuivant(); 
				etat=7;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 2	 : 
				if(caractereCourant=='a')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=3;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 3 : 
				if(caractereCourant=='i')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=4;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 4 :
				if(caractereCourant=='n')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=5;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 5 :
				if(caractereCourant=='e')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=6;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 6 : 
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.Chaine,sb.toString());}
				else 
					return getID(sb);
			
				
			case 7 :
				if(caractereCourant=='r')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=8;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
				
			case 8 :
				if(caractereCourant=='a')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=9;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 9 :
				if(caractereCourant=='c')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=10;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
				
			case 10 :
				if(caractereCourant=='t')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=11;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 11 :
				if(caractereCourant=='e')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=12;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
				
			case 12 :
				if(caractereCourant=='r')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=13;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 13 :
				if(caractereCourant=='e')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=14;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 14 : 
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.Caractere,sb.toString());}
				else 
					return getID(sb);

			}
		}
}

private UniteLexicale getOppLog() {
	int etat=0;
	StringBuffer sb=new StringBuffer();
	while(true) {
		switch(etat) 
		{
		case 0 :	
			sb.append(caractereCourant);
			caractereSuivant();
			etat=1;
			
		case 1 :
			if(eof || Character.isWhitespace(caractereCourant)){
				{	reculer();
		        	if (sb.charAt(0)=='&')
		        		return new UniteLexicale(Categorie.OppLog, "And");
		        	else if (sb.charAt(0)=='|')
		        		return new UniteLexicale(Categorie.OppLog, "Or");
		        	else if (sb.charAt(0)=='!')
		        		return new UniteLexicale(Categorie.OppLog, "Not");
				}
	}}}}

private UniteLexicale getOppArith() {
	int etat=0;
	StringBuffer sb=new StringBuffer();
	while(true) {
		switch(etat) 
		{
		case 0 :	
			sb.append(caractereCourant);
			caractereSuivant();
			etat=1;
			
		case 1 :
			if(eof || Character.isWhitespace(caractereCourant)){
				{	reculer();
		        	if (sb.charAt(0)=='+')
		        		return new UniteLexicale(Categorie.OppArith, "Add");
		        	else if (sb.charAt(0)=='-')
		        		return new UniteLexicale(Categorie.OppArith, "Sus");
		        	else if (sb.charAt(0)=='*')
		        		return new UniteLexicale(Categorie.OppArith, "Multip");
		        	else if (sb.charAt(0)=='/')
		        		return new UniteLexicale(Categorie.OppArith, "Div");
				}
	}}}}

	private UniteLexicale getCommentaire() 
	{
		int etat=0;
		 StringBuffer sb=new StringBuffer();
		 while(true)
		  {
			 switch(etat) {
			 case 0 :	
				 caractereSuivant();
				 etat=1;
				 break;
			 case 1 :
				 if(eof){
					return new UniteLexicale(Categorie.NUL,"# mal placé");	
					
				}

				 else if(caractereCourant=='#')
						 {
						 caractereSuivant(); 
						 etat=2;
						 }
				else 
						 {	sb.append(caractereCourant);
							 caractereSuivant(); 
							 }

				 break;
				 
			 case 2 :
					 return new UniteLexicale(Categorie.Commentaire,sb.toString());	
					
					 		
 		
 
			 }
			}
		}
			


private UniteLexicale getString() {
	   int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
			case 0 :	
				caractereSuivant();
				etat=1;
				break;
			case 1 :
				if(eof){
					return new UniteLexicale(Categorie.NUL," \" mal placé ");}
				else if(caractereCourant != '"')
				{	sb.append(caractereCourant);
					caractereSuivant(); 
					etat=2;
					}
					else return new UniteLexicale(Categorie.String,"");
				break;
				
			case 2	: 
				if(caractereCourant=='"')
						{
					return new UniteLexicale(Categorie.Char,sb.toString());
						}
				else if(eof){
					return new UniteLexicale(Categorie.NUL," \" mal placé ");}
				
				else
				{
						sb.append(caractereCourant);
						caractereSuivant(); 
						etat=3;
					}
				break;
				
			case 3 :
			if(eof){
				return new UniteLexicale(Categorie.NUL," \" mal placé ");}
				else if(caractereCourant=='"')
				{
			return new UniteLexicale(Categorie.String,sb.toString());
				}
				else{
					sb.append(caractereCourant);
					caractereSuivant(); 
				break;		
}
			}
		}}

private UniteLexicale getLire() {
	   int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
			case 0 :	
				sb.append(caractereCourant);
				caractereSuivant();
				etat=1;
				break;
			
			case 1 : 
				if(caractereCourant=='i')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=2;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 2	 : 
				if(caractereCourant=='r')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=3;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 3 : 
				if(caractereCourant=='e')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=4;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 4 : 
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.Lire,sb.toString());}
				else 
					return getID(sb);

			}
		}
}

private UniteLexicale getPour() {
	int etat=0;
	StringBuffer sb=new StringBuffer();
	while(true) {
		switch(etat) {
		case 0 :	
			sb.append(caractereCourant);
			caractereSuivant();
			etat=1;
			break;
		
		case 1 : 
			if(caractereCourant=='o')
				{
				sb.append(caractereCourant);
				caractereSuivant(); 
				etat=2;
				}
			else if(eof || Character.isWhitespace(caractereCourant)){
				return new UniteLexicale(Categorie.ID,sb.toString());}
			else 
				return getID(sb);
			break;
			
		case 2	 : 
			if(caractereCourant=='u')
				{
				sb.append(caractereCourant);
				caractereSuivant(); 
				etat=3;
				}
			else if(eof || Character.isWhitespace(caractereCourant)){
				return new UniteLexicale(Categorie.ID,sb.toString());}
			else 
				return getID(sb);
			break;
			
		case 3 : 
			if(caractereCourant=='r')
				{
				sb.append(caractereCourant);
				caractereSuivant(); 
				etat=4;
				}
			else if(eof || Character.isWhitespace(caractereCourant)){
				return new UniteLexicale(Categorie.ID,sb.toString());}
			else 
				return getID(sb);
			break;
			
		case 4 : 
			if(eof || Character.isWhitespace(caractereCourant)){
				reculer();
				return new UniteLexicale(Categorie.Pour,sb.toString());}
			else 
				return getID(sb);
		}}
}

private UniteLexicale getBool() {
	int etat=0;
	StringBuffer sb=new StringBuffer();
	while(true) {
		switch(etat) {
		case 0 :	
			sb.append(caractereCourant);
			caractereSuivant();
			etat=1;
			break;
		
		case 1 : 
			if(caractereCourant=='o')
				{
				sb.append(caractereCourant);
				caractereSuivant(); 
				etat=2;
				}
			else if(eof || Character.isWhitespace(caractereCourant)){
				return new UniteLexicale(Categorie.ID,sb.toString());}
			else 
				return getID(sb);
			break;
			
		case 2	 : 
			if(caractereCourant=='o')
				{
				sb.append(caractereCourant);
				caractereSuivant(); 
				etat=3;
				}
			else if(eof || Character.isWhitespace(caractereCourant)){
				return new UniteLexicale(Categorie.ID,sb.toString());}
			else 
				return getID(sb);
			break;
			
		case 3 : 
			if(caractereCourant=='l')
				{
				sb.append(caractereCourant);
				caractereSuivant(); 
				etat=4;
				}
			else if(eof || Character.isWhitespace(caractereCourant)){
				return new UniteLexicale(Categorie.ID,sb.toString());}
			else 
				return getID(sb);
			break;
			
		case 4 : 
			if(eof || Character.isWhitespace(caractereCourant)){
				reculer();
				return new UniteLexicale(Categorie.Bool,sb.toString());}
			else 
				return getID(sb);
		}}
}
	

private UniteLexicale getReel() {
	int etat=0;
	StringBuffer sb=new StringBuffer();
	while(true) {
		switch(etat) {
		case 0 :	
			sb.append(caractereCourant);
			caractereSuivant();
			etat=1;
			break;
		
		case 1 : 
			if(caractereCourant=='e')
				{
				sb.append(caractereCourant);
				caractereSuivant(); 
				etat=2;
				}
			else if(eof || Character.isWhitespace(caractereCourant)){
				return new UniteLexicale(Categorie.ID,sb.toString());}
			else 
				return getID(sb);
			break;
			
		case 2	 : 
			if(caractereCourant=='e')
				{
				sb.append(caractereCourant);
				caractereSuivant(); 
				etat=3;
				}
			else if(eof || Character.isWhitespace(caractereCourant)){
				return new UniteLexicale(Categorie.ID,sb.toString());}
			else 
				return getID(sb);
			break;
			
		case 3 : 
			if(caractereCourant=='l')
				{
				sb.append(caractereCourant);
				caractereSuivant(); 
				etat=4;
				}
			else if(eof || Character.isWhitespace(caractereCourant)){
				return new UniteLexicale(Categorie.ID,sb.toString());}
			else 
				return getID(sb);
			break;
			
		case 4 : 
			if(eof || Character.isWhitespace(caractereCourant)){
				reculer();
				return new UniteLexicale(Categorie.Reel,sb.toString());}
			else 
				return getID(sb);
		}}
}
private UniteLexicale getEntier() {
	   int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
			case 0 :	
				sb.append(caractereCourant);
				caractereSuivant();
				etat=1;
				break;
			
			case 1 : 
				if(caractereCourant=='n')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=2;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;

			case 2 : 
				if(caractereCourant=='t')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=3;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 3 : 
				if(caractereCourant=='i')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=4;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 4 : 
				if(caractereCourant=='e')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=5;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 5 :
				if(caractereCourant=='r')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=6;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 6 : 
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.Entier,sb.toString());}
				else 
					return getID(sb);


	}}

}

public UniteLexicale getDe() {
	   int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
			case 0 :	
				sb.append(caractereCourant);
				caractereSuivant();
				etat=1;
				break;
			
			case 1 : 
				if(caractereCourant=='e')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=2;
					}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
	
			case 2 : 
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.de,sb.toString());}
				else 
					return getID(sb);
				
			}}}

public UniteLexicale getT() {
	   int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
			case 0 :	
				sb.append(caractereCourant);
				caractereSuivant();
				etat=1;
				break;
			
			case 1 : 
				if(caractereCourant=='r')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=2;
					}
				else if (caractereCourant=='a')
				{
				sb.append(caractereCourant);
				caractereSuivant(); 
				etat=5;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 2 : 
				if (caractereCourant=='u')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=3;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 3 :
				if (caractereCourant=='e')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=4;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
			
			case 4 :
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.True,sb.toString());}
				else 
					return getID(sb);
			
			case 5 :
				if (caractereCourant=='b')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=6;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 6 :
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.Tab,sb.toString());}
				else 
					return getID(sb);
			
				
}}}

public UniteLexicale getF() {
	   int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
			case 0 :	
				sb.append(caractereCourant);
				caractereSuivant();
				etat=1;
				break;
			
			case 1 : 
				if(caractereCourant=='i')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=2;
					}
				else if (caractereCourant=='a')
				{
				sb.append(caractereCourant);
				caractereSuivant(); 
				etat=10;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
				
			case 2 :
				if (caractereCourant=='n')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=3;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 3:
               if (caractereCourant=='p')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=4;
				}
				else if (caractereCourant=='s')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=8;
				}
				else 
					return getID(sb);
				break;
				
			case 4:
				if (caractereCourant=='o')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=5;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 5 : 
				if (caractereCourant=='u')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=6;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
			case 6 :
				if (caractereCourant=='r')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=7;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 7 :
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.Finpour,sb.toString());}
				else 
					return getID(sb);
			
					
			case 8 :
				if (caractereCourant=='i')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=9;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 9 :
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.FinSi,sb.toString());}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				
			case 10: 
				if (caractereCourant=='l')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=11;
					
				}
				else if (caractereCourant=='i')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=14;
					
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 11 :
				if (caractereCourant=='s')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=12;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 12 :
				if (caractereCourant=='e')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=13;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 13 :
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.False,sb.toString());}
				else 
					return getID(sb);
				
			case 14 :
				if (caractereCourant=='r')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=15;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 15 :
				if (caractereCourant=='e')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=16;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 16 :
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.faire,sb.toString());}
				else 
					return getID(sb);
				

}}}

	

private UniteLexicale getA() {
	   int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
			case 0 :	
				sb.append(caractereCourant);
				caractereSuivant();
				etat=1;
				break;
			
			case 1 : 
				if(caractereCourant=='l')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=2;
					}
				else if(caractereCourant=='f')
				{
				sb.append(caractereCourant);
				caractereSuivant(); 
				etat=6;}
				
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 	
					return getID(sb);
				break;
				
				
			case 2 :
				if (caractereCourant=='o')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=3;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				
			case 3:
				if (caractereCourant=='r')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=4;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 4:
				if (caractereCourant=='s')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=5;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 5 :
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.Alors,sb.toString());}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
			case 6 :
				if (caractereCourant=='f')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=7;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
			
			case 7 :
				if (caractereCourant=='i')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=8;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
			
			case 8 :
				if (caractereCourant=='c')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=9;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
			
			case 9 :
				if (caractereCourant=='h')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=10;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
			
			case 10: 
				if (caractereCourant=='e')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=11;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
			
			case 11 :
				if (caractereCourant=='r')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=12;
				}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				break;
				
			case 12 :
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.Afficher,sb.toString());}
				else if(eof || Character.isWhitespace(caractereCourant)){
					return new UniteLexicale(Categorie.ID,sb.toString());}
				else 
					return getID(sb);
				
	
}}}



public UniteLexicale getBoucleCondit() {
	   int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
			case 0 :	
				sb.append(caractereCourant);
				caractereSuivant();
				etat=1;
				break;
			
			case 1 : 
				if(caractereCourant=='i')
					{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=2;
					}
				else 
					{
					return getID(sb);
					}
				break;
				
				
			case 2 :
				if(eof || Character.isWhitespace(caractereCourant))
				{	reculer();
					return new UniteLexicale(Categorie.Si,sb.toString());
				}
				else if (caractereCourant=='n')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=3;
				}
				else 
					return getID(sb);

				break;
				
			case 3:
				if (caractereCourant=='o')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=4;
				}
				else 
					return getID(sb);
				break;
				
			case 4:
				if (caractereCourant=='n')
				{
					sb.append(caractereCourant);
					caractereSuivant(); 
					etat=5;
				}
				else 
					return getID(sb);
				break;
				
			case 5 :
				if(eof || Character.isWhitespace(caractereCourant)){
					reculer();
					return new UniteLexicale(Categorie.Sinon,sb.toString());}
				else 
					return getID(sb);
			
}}}




	public UniteLexicale getNombre() {
		int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
			case 0 : etat=1; 
					 sb.append(caractereCourant); 
					 break;
			case 1 : caractereSuivant();                   
					 if(eof)
						 etat=3;
					 else
						 if(Character.isDigit(caractereCourant)) 
							 sb.append(caractereCourant);
						 else
							 if (caractereCourant=='.')
							 {
								 sb.append(caractereCourant);
								 etat=4;
							 }
							 else				
								 etat=2;
					 break;
			case 2 : 
				reculer();
				return new UniteLexicale(Categorie.Int, sb.toString());
					 
			case 3 : 
				return new UniteLexicale(Categorie.Int, sb.toString());
			
			case 4 :
				caractereSuivant();
				if(eof)
					etat=5;
				else
					if(Character.isDigit(caractereCourant)) 
						sb.append(caractereCourant);
					else
						etat=6;
				break;
			
			case 5 : 
				return new UniteLexicale(Categorie.Float, sb.toString());
				
			case 6 : 
				{
					reculer();
					return new UniteLexicale(Categorie.Float,sb.toString());
				}
			}
		}
			
			
		}
	public UniteLexicale getID(StringBuffer sb) {
		int etat=0;
		
		while(true) {
			switch(etat) {
				case 0 : etat=1; 
						 sb.append(caractereCourant); 
						 break;
				case 1 : caractereSuivant();
						 if(eof)
							 etat=3;
						 else
							 if(Character.isLetterOrDigit(caractereCourant)) 
								 sb.append(caractereCourant);
							 else
								 etat=2;
						 break;
				case 2 : reculer();
						 return new UniteLexicale(Categorie.ID, sb.toString());
				case 3 : return new UniteLexicale(Categorie.ID, sb.toString());
			}
		}
	}
	
	public UniteLexicale getOPPRel() {
		 int etat = 0;
	        StringBuffer sb = new StringBuffer();
	        while (true) {
	            switch (etat) {
	                case 0 :
	                   if (caractereCourant == '=') {
	                         sb.append(caractereCourant);
	                         etat=1;}
	                   
	                   else if (caractereCourant == '>') {
	                                sb.append(caractereCourant);
	                                caractereSuivant();
	                                etat=2;}
	                	
	                   else {
	                                sb.append(caractereCourant);
	                                caractereSuivant();
	                                etat = 5;}
	                   break;
	                       
	                case 1 : 
                        return new UniteLexicale(Categorie.OppRel, "EGA"); 
	              
	                case 2: 
	                	 if(caractereCourant == '=')
	                		 etat=3;
	                	 else
	                		 etat=4;
	                case 3:
	                	 return new UniteLexicale(Categorie.OppRel, "PGE");
	                
	                case 4:
	                	reculer();
	                	return new UniteLexicale(Categorie.OppRel, "PGQ");
	                	
	                case 5 :
	                	 if(caractereCourant == '=')
	                		 
	                		 {sb.append(caractereCourant);
	                		 etat=6;}
	                	 
	                	 else if (caractereCourant == '>')
	                	 {
	                		 sb.append(caractereCourant);
	                		 etat=7;
	                	 }
	                	 else 
	                	     etat=8;
	                	 break;
	                case 6 :
	                	return new UniteLexicale(Categorie.OppRel, "PPE");
	                case 7 : 
	                	return new UniteLexicale(Categorie.OppRel, "DIF");
	                case 8 : 
	                	reculer();
	                	return new UniteLexicale(Categorie.OppRel, "PPQ");              	
	                	
	            }}}
	           

	
	public UniteLexicale getOppAff() {
		int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
			case 0 : 
				 etat=1;
					sb.append(caractereCourant);
			 break;
			case 1 :
				caractereSuivant();
				if(caractereCourant=='=')
				{
					etat=2;
					sb.append(caractereCourant);
					
				}
			case 2 :
				caractereSuivant();
				 if(eof)
					 etat=3;
					 
				 else 
						 etat=4;
		
			break;
			
			case 3 :
				return new UniteLexicale(Categorie.OppAff, sb.toString());	
					
			case 4 :
				reculer();
				return new UniteLexicale(Categorie.OppAff, sb.toString());
			

	}
		}}







	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return fluxCaracteres.toString();
	}
	
	
}