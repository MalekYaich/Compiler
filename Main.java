package Analyse_syntaxique;

import java.util.ArrayList;
import java.util.Scanner;

import Analyse_Lexicale.anallex;


public class Main {
    public static void main(String[] args) {
    	try (Scanner sc = new Scanner(System.in)) {
			anallex anaLex=new anallex();
			boolean b=true;
  

			System.out.println("\n\n\n------------------------------------------");

			  //analyse  lexical	
			ArrayList<String> t=anaLex.lecture();
			

			// analyse Syntaxique
			if(!t.isEmpty())
			{
  //System.out.println("***********************Analyse Syntaxique*************************");
			String tab []= new String[t.size()];
			for(int i=0; i<t.size();i++)
			{
				tab[i]=t.get(i);
			}

			Parser test = new Parser();
			test.analyzeSLnewSansAff(tab);

			//generatiooon code
			System.out.println("code a 3 adresses de fichier a22.txt :");

			while(b)
			{
   System.out.println("\n-----------------------------------------");
   System.out.println("0-> afficher les lexemes");
   System.out.println("1-> afficher la trace d'execution");
   System.out.println("2-> afficher la table slr ");
   System.out.println("3-> afficher le code source  ");
   System.out.println("4-> quitter");

      int nb = sc.nextInt();
   switch(nb) {
			   case 0: anaLex.afficheLexeme();
			           break;

			   case 1:System.out.println("merci :D ");
			          test.analyzeSLnew(tab);
			          break;

			   case 2: test.ouput();
			   System.out.println("merci :D ");
			          break;

			   case 3:break;


			   case 4:	   
				   System.out.println("\n-----------------------------------------");
				   System.out.println("merci :D \nTravail realise par : \nYaich Malek  Lahyani Mohamed Amine");
			               b=false; 
						   break;
   }
			}
   }
		}

	}
}

