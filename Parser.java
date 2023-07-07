package Analyse_syntaxique;
import java.util.Stack;


public class Parser {


public String[] LRGS = {
		"P->D Debut I Fin P",
		"P-> ",
		"D->T K pv D",
		"D-> ",
		"K->ID",
		"K->ID v K",
		"T->Entier",
		"T->Reel",
		"T->Bool",
		"T->Tab [ Int ] de T",
		"T->Chaine",
		"T->Caractere",
		"I->ID OppAff E pv I",
		"I->Si E Alors I FinSi pv I",
		"I->Si E Alors I Sinon I FinSi pv I",
		"I->Pour ID de Int Dp Int faire I Finpour pv I",
		"I->Lire ID pv I",
		"I->Afficher E pv I",
		"I-> ",
		"O->OppRel E",
		"O->OppLog E",
		"O->OppArith E",
		"O-> ",
		"E->( E ) O",               
		"E->ID O",
		"E->Int O",
		"E->Float O",
		"E->String O",
		"E->Char O",
		"E->True O",
		"E->False O"}
   		;

public String[][] tableSLR = {
    	
		    {"etat/VT","Debut","Fin", "pv", "ID", "v" , "Entier" ,"Reel" , "Bool", "Tab","CroOuv","Int","CroFer", "de", "Chaine", "Caractere" , "OppAff" , "Si"  , "Alors", "FinSi" ,"epsilon","Sinon" , "Pour",  "Dp"  , "faire"   ,"Finpour", "Lire"    ,"Afficher",  "OppRel","OppLog" ,"OppArith", "(" ,  ")"    ,"Float"   ,"String","Char","True","False","EOF", "P"  , "D"  , "K"  , "T"  , "I"  , "O"  ,"E"  },                                 
            {"0"      ,"r4"   ,"err","err","err","err",   "s4"   ,  "s5" , "s6"  , "s7" ,"err", "err", "err" , "err" , "s8"     ,      "s9"   , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err" ,"r2" ,  "1" , "2"  , "err", "3"   , "err","err","err"},
            {"1"      ,"err"  ,"err","err","err","err",  "err"   , "err" , "err" , "err","err", "err", "err" , "err" , "err"    ,     "err"   , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err" ,"acc", "err", "err", "err","err" , "err", "err","err"},
            {"2"      ,"s10"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"3"      ,"err"  ,"err","err","s12","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "11" , "err", "err", "err","err"},
            {"4"      ,"err"  ,"err","err","r7" ,"err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"5"      ,"err"  ,"err","err","r8" ,"err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"6"      ,"err"  ,"err","err","r9" ,"err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"7"      ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","s13", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"8"      ,"err"  ,"err","err","r11","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"9"      ,"err"  ,"err","err","r12","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"10"     ,"err"  ,"r19","err","s15","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "s16" , "err"  , "r19"   , "r19"   , "err"  , "s17", "err"   , "err"     , "r19"  , "s18"      , "s19"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "14" , "err","err"},
            {"11"     ,"err"  ,"err","s20","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"12"     ,"err"  ,"err","r5" ,"err","s21",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"13"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "s22", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"14"     ,"err"  ,"s23","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"15"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "s24"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"16"     ,"err"  ,"err","err","s27","err",   "err"  ,  "err", "err" , "err","err", "s28", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "s26", "err"   , "s29"    , "s30"  , "s31", "s32", "s33", "err", "err", "err", "err", "err", "err", "err","25" },
            {"17"     ,"err"  ,"err","err","s34","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"18"     ,"err"  ,"err","err","s35","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"19"     ,"err"  ,"err","err","s27","err",   "err"  ,  "err", "err" , "err","err", "s28", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "s26", "err"   , "s29"    , "s30"  , "s31", "s32", "s33", "err", "err", "err", "err", "err", "err", "err","36" },
            {"20"     ,"r4"   ,"err","err","err","err",   "s4"   ,  "s5" , "s6"  , "s7" ,"err", "err", "err" , "err" , "s8"     ,      "s9"   , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "37" , "err", "3"  , "err", "err","err"},
            {"21"     ,"err"  ,"err","err","s12","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "38" , "err", "err", "err","err"},
            {"22"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "s39" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"23"     ,"r4"   ,"err","err","err","err",   "s4"   ,  "s5" , "s6"  , "s7" ,"err", "err", "err" , "err" , "s8"     ,      "s9"   , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "r2" , "40" , "2"  , "err", "3"  , "err", "err","err"},
            {"24"     ,"err"  ,"err","err","s27","err",   "err"  ,  "err", "err" ,"err" ,"err", "s28", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "s26", "err"   , "s29"    , "s30"  , "s31", "s32", "s33", "err", "err", "err", "err", "err", "err", "err","41" },
            {"25"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "s42"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"26"     ,"err"  ,"err","err","s27","err",   "err"  ,  "err", "err" , "err","err", "s28", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "s26", "err"   , "s29"    , "s30"  , "s31", "s32", "s33", "err", "err", "err", "err", "err", "err", "err","43" },
            {"27"     ,"err"  ,"err","r23","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r23"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "s45"    , "s46"      , "s47", "err", "r23"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "44" ,"err"},
            {"28"     ,"err"  ,"err","r23","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r23"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "s45"    , "s46"      , "s47", "err", "r23"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "48" ,"err"},
            {"29"     ,"err"  ,"err","r23","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r23"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "s45"    , "s46"      , "s47", "err", "r23"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "49" ,"err"},
            {"30"     ,"err"  ,"err","r23","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r23"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "s45"    , "s46"      , "s47", "err", "r23"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "50" ,"err"},
            {"31"     ,"err"  ,"err","r23","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r23"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "s45"    , "s46"      , "s47", "err", "r23"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "51" ,"err"},
            {"32"     ,"err"  ,"err","r23","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r23"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "s45"    , "s46"      , "s47", "err", "r23"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "52" ,"err"},
            {"33"     ,"err"  ,"err","r23","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r23"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "s45"    , "s46"      , "s47", "err", "r23"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "53" ,"err"},
            {"34"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "s54" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"35"     ,"err"  ,"err","s55","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"36"     ,"err"  ,"err","s56","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"37"     ,"r3"   ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"38"     ,"err"  ,"err","r6" ,"err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"39"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "s57" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"40"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "r1" , "err", "err", "err", "err", "err", "err","err"},
            {"41"     ,"err"  ,"err","s58","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"42"     ,"err"  ,"r19","err","s15","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "s16" , "err"  , "r19"   , "r19"   , "err"  , "s17", "err"   , "err"     , "r19"  , "s18"      , "s19"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "59" , "err","err"},
            {"43"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "s60"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"44"     ,"err"  ,"err","r25","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r25"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "r25"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"45"     ,"err"  ,"err","err","s27","err",   "err"  ,  "err", "err" , "err","err", "s28", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "s26", "err"   , "s29"    , "s30"  , "s31", "s32", "s33", "err", "err", "err", "err", "err", "err", "err","61" },
            {"46"     ,"err"  ,"err","err","s27","err",   "err"  ,  "err", "err" , "err","err", "s28", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "s26", "err"   , "s29"    , "s30"  , "s31", "s32", "s33", "err", "err", "err", "err", "err", "err", "err","62" },
            {"47"     ,"err"  ,"err","err","s27","err",   "err"  ,  "err", "err" , "err","err", "s28", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "s26", "err"   , "s29"    , "s30"  , "s31", "s32", "s33", "err", "err", "err", "err", "err", "err", "err","63" },
            {"48"     ,"err"  ,"err","r26","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r26"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "r26"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"49"     ,"err"  ,"err","r27","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r27"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "r27"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"50"     ,"err"  ,"err","r28","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r28"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "r28"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"51"     ,"err"  ,"err","r29","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r29"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "r29"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"52"     ,"err"  ,"err","r30","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r30"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "r30"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"53"     ,"err"  ,"err","r31","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r31"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "r31"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"54"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "s64", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"55"     ,"err"  ,"r19","err","s15","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "s16" , "err"  , "r19"   , "r19"   , "err"  , "s17", "err"   , "err"     , "r19"  , "s18"      , "s19"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "65" , "err","err"},
            {"56"     ,"err"  ,"r19","err","s15","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "s16" , "err"  , "r19"   , "r19"   , "err"  , "s17", "err"   , "err"     , "r19"  , "s18"      , "s19"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "65" , "err","err"},
            {"57"     ,"err"  ,"err","err","err","err",   "s4"   ,  "s5" , "s6"  , "s7" ,"err", "err", "err" , "err" , "s8"     ,      "s9"   , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "67" , "err", "err","err"},
            {"58"     ,"err"  ,"r19","err","s15","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "s16" , "err"  , "r19"   , "r19"   , "err"  , "s17", "err"   , "err"     , "r19"  , "s18"      , "s19"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "68" , "err","err"},
            {"59"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "s69"   , "s70"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"60"     ,"err"  ,"err","r23","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r21"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "s45"    , "s46"      , "s47", "err", "r23"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "71" ,"err"},
            {"61"     ,"err"  ,"err","r20","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r20"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "r20"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"62"     ,"err"  ,"err","r21","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r21"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "r21"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"63"     ,"err"  ,"err","r22","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r22"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "r22"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"64"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "s72"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"65"     ,"err"  ,"r17","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "r17"   , "r17"   , "err"  , "err", "err"   , "err"     , "r17"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"66"     ,"err"  ,"r18","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "r18"   , "r18"   , "err"  , "err", "err"   , "err"     , "r18"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"67"     ,"err"  ,"err","err","r10","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"68"     ,"err"  ,"r13","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "r13"   , "r13"   , "err"  , "err", "err"   , "err"     , "r13"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"69"     ,"err"  ,"err","s73","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"70"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "s74"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"71"     ,"err"  ,"err","r24","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "r24"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "r24"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"72"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "s75", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"73"     ,"err"  ,"r19","err","s15","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "s16" , "err"  , "r19"   , "r19"   , "err"  , "s17", "err"   , "err"     , "r19"  , "s18"      , "s19"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "76" , "err","err"},
            {"74"     ,"err"  ,"r19","err","s15","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "s16" , "err"  , "r19"   , "r19"   , "err"  , "s17", "err"   , "err"     , "r19"  , "s18"      , "s19"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "77" , "err","err"},
            {"75"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "s78"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"76"     ,"err"  ,"r14","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "r14"   , "r14"   , "err"  , "err", "err"   , "err"     , "r14"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"77"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "s79"   , "err"   , "err"  , "err", "err"   , "err"     , "eer"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"78"     ,"err"  ,"r19","err","s15","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "s16" , "err"  , "r19"   , "r19"   , "err"  , "s17", "err"   , "err"     , "r19"  , "s18"      , "s19"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "80" , "err","err"},
            {"79"     ,"err"  ,"err","s81","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"80"     ,"err"  ,"err","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "s82"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"81"     ,"err"  ,"r19","err","s15","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "s16" , "err"  , "r19"   , "r19"   , "err"  , "s17", "err"   , "err"     , "r19"  , "s18"      , "s19"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "83" , "err","err"},
            {"82"     ,"err"  ,"err","s84","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "err"   , "err"   , "err"  , "err", "err"   , "err"     , "err"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"83"     ,"err"  ,"r15","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "r15"   , "r15"   , "err"  , "err", "err"   , "err"     , "r15"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
            {"84"     ,"err"  ,"r19","err","s15","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "s16" , "err"  , "r19"   , "r19"   , "err"  , "s17", "err"   , "err"     , "r19"  , "s18"      , "s19"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "85" , "err","err"},
            {"85"     ,"err"  ,"r16","err","err","err",   "err"  ,  "err", "err" , "err","err", "err", "err" , "err" , "err"    ,      "err"  , "err"    , "err" , "err"  , "r16"   , "r16"   , "err"  , "err", "err"   , "err"     , "r16"  , "err"      , "err"    , "err"    , "err"      , "err", "err", "err"   , "err"    , "err"  , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err","err"},
                     };
      
           
     
// :'(

   
    
    public Stack<String> stackState = new Stack<>();
    public Stack<String> analyse = new Stack<>();
    public Stack<String> stackSymbol = new Stack<>();
    public String strInput ;
    public Stack<String> pileSemantique = new Stack<String>();
    public String action = "";
    int index = 0;


    
    public void analyzeSLnew(String []tt) {
    	
        action = "";
        index = 0;
      
        analyse.push("0");
        
        
       
        System.out.println("********pile     	    Entrï¿½e            Action***********");
        this.AfficherSLRnew(tt);
    
       while(index<tt.length) 
        
        {         
            String s = analyse.peek();

            String st = Action(s,tt[index]);
         if (tt[index] == "Commentaire")
            { index++ ;
            AfficherSLRnew(tt);
            
             }
         else{
               
            
             if (st== "epsilon")
             {
            	 String t[]= new String[tt.length-index+1];
            	 t[0]="epsilon";
            	 for (int i=1;i<t.length;i++)
            	 {
            		 t[i]=tt[index+i-1];
	 
            	 }
            tt=t;
            index=0;
            
            	 
             }
             else if (st.charAt(0)== 's') {
            
            	   if (tt[index]!="epsilon") 
                   analyse.push(tt[index]); 
                   else{analyse.pop();}
                   
                	analyse.push(Action(s, tt[index]).substring(1));
                	index++;
                	action = "shift ";
                    AfficherSLRnew(tt);
            }
            // Réduction 
            else if (st.charAt(0) == 'r' ) { 
            	semantchique(Integer.parseInt(Action(s, tt[index]).substring(1))-1);
                String str = LRGS[Integer.parseInt(Action(s, tt[index]).substring(1))-1];
               // System.out.println(str);
               
                String tabparties[]= str.split("->");
                String Partiegauche=tabparties[0];
                String Partiedroite=tabparties[1];

                
                String tabtoken[]= Partiedroite.split(" ");
                int taillepile= tabtoken.length +tabtoken.length;               
               
                for (int i = 0; i < taillepile; i++) 
                	
                	analyse.pop();
               
                
                String sommetpile = analyse.peek();
                analyse.push(Partiegauche);
                //String tetesucc = analyse.peek();
        
                analyse.push(Action(sommetpile, Partiegauche));
               
                action = "reduce:" + str;
                AfficherSLRnew(tt);
            } 
            //acceptation
            else if (st == "acc")
            	{
            	System.out.println("analyze SLR successfully"); 
            	break;}
            	
            else
            	//erreur
            	{
            	//System.out.println("texterreur"+Action(s,ch[index])+s+ch[index]+index); 
            	System.out.println("analyze SLR failled"); 
        	break;
            	}          }  
        }  
        
    }

	public void analyzeSLnewSansAff(String []tt) {

        action = "";
        index = 0; 
        analyse.push("0");    
       while(index<tt.length) 
        
        {         
            String s = analyse.peek();

            String st = Action(s,tt[index]);
         if (tt[index] == "Commentaire")
            { index++ ;            
             }
         else{
               
            
             if (st== "epsilon")
             {
            	 String t[]= new String[tt.length-index+1];
            	 t[0]="epsilon";
            	 for (int i=1;i<t.length;i++)
            	 {
            		 t[i]=tt[index+i-1];
	 
            	 }
            tt=t;
            index=0;
            
            	 
             }
             else if (st.charAt(0)== 's') {
            
            	   if (tt[index]!="epsilon") 
                   analyse.push(tt[index]); 
                   else{analyse.pop();}
                	analyse.push(Action(s, tt[index]).substring(1));
                	index++;
                	action = "shift ";
            }
            else if (st.charAt(0) == 'r' ) {
                
                String str = LRGS[Integer.parseInt(Action(s, tt[index]).substring(1))-1];
               // System.out.println(str);
               
                String tabparties[]= str.split("->");
                String Partiegauche=tabparties[0];
                String Partiedroite=tabparties[1];

                
                String tabtoken[]= Partiedroite.split(" ");
                int taillepile= tabtoken.length +tabtoken.length;               
               
                for (int i = 0; i < taillepile; i++) 
                	
                	analyse.pop();
               
                
                String sommetpile = analyse.peek();
                analyse.push(Partiegauche);
                //String tetesucc = analyse.peek();
        
                analyse.push(Action(sommetpile, Partiegauche));
               
                action = "reduce:" + str;
            } 
            //acceptation
            else if (st == "acc")
            	{
            	System.out.println("analyse synaxique valide"); 
            	break;}
            	
            else
            	{
            	System.out.println("erreur syntaxique "); 
        	break;
            	}          }  
        }  
        
    }
    





	public String Action(String s, String a) {
       for (int i = 1; i <87 ; i++)
    	   if (tableSLR[i][0].equals(s))
    		   for (int j = 1; j <46; j++)
    			   if (tableSLR[0][j].equals(a))
    			   {
    				   if (tableSLR[i][j].equals("err") && !(tableSLR[i][20].equals("err")) )
    				   {
                           return "epsilon"; 
                           
    				   }
    				   else     						   
    				   return tableSLR[i][j];
    			   }
    	return "err";		   
    	   
	}


    public void AfficherSLRnew(String []tt) {
      
    	
    	String ss= "-";
    	String ss1= "-";
    	 int taillepile=analyse.size();
    	int taillepilediv2= taillepile /2;
         for(int i=0;i<taillepilediv2;i++)
     		ss=ss + "-" ;
         int tailleinput=tt.length;
         for(int i=0;i<tailleinput;i++)
     		ss1=ss1 + "-" ;
    
        strInput="";
        for(int i=index; i<tt.length;i++)
        	strInput= strInput+ tt[i];
       
        System.out.printf("%s", analyse + ss1);
        System.out.printf("%s", strInput+ ss);
        System.out.printf("%s", action);
        System.out.print("         "+pileSemantique.toString());
        System.out.println();
    }
    
    private String Rechercher(String s)
    {
		return("Entier");
    	
    }
    
    private void semantchique(int i) {
    	
		switch (i) {
    	case 0 :
    		
    		String temp1= pileSemantique.pop();
    		String temp2= pileSemantique.pop();
    		String temp3= pileSemantique.pop();
    		if (temp1.equals(temp2) && temp2.equals(temp3) && temp3.equals("vide"))
    			pileSemantique.add("vide");
    		else
    			pileSemantique.add("erreur");

    		break;
    	
    	case 1 :
    		pileSemantique.add("vide");
    		break;
    		
    	case 2 :
            temp1= pileSemantique.pop();
    		temp2= pileSemantique.pop();
    		temp3= pileSemantique.pop();
    		if (temp3.equals(temp2)  && temp1.equals("vide"))
    			pileSemantique.add("vide");
    		else
    			pileSemantique.add("erreur");
    		break;
    		
    	case 3 :
    		pileSemantique.add("vide");
    		break;
    		
    	case 4 :
    		pileSemantique.add(Rechercher(""));
    		break;
    		
    	case 5 :
    		temp1= pileSemantique.pop();
    		if (Rechercher("").equals(temp1))
    			pileSemantique.add(Rechercher(""));
    		else
    			pileSemantique.add("erreur");
    		break;
  
    		
    	case 6 :
    		pileSemantique.add("Entier");
    		break;
    		
    	case 7 :
    		pileSemantique.add("Reel");
    		break;
    		
    	case 8 :
    		pileSemantique.add("Bool");
    		break;
    		
    	case 9 :
    		pileSemantique.add("Tab");
    		break;
    		
    	case 10 :
    		pileSemantique.add("Chaine");
    		break;
    		
    	case 11 :
    		pileSemantique.add("Caractere");
    		break;
    		
    	case 12 :
    		temp1= pileSemantique.pop();
    		temp2= pileSemantique.pop();
    		if (temp2.equals(Rechercher("")) && temp1.equals("vide"))
    			pileSemantique.add("vide");
    		else
    			pileSemantique.add("erreur");
    		break;

    		
    	case 13 :
    		temp1= pileSemantique.pop();
    		temp2= pileSemantique.pop();
    		temp3= pileSemantique.pop();
    		if (temp3.equals("Bool")  && temp2.equals(temp1) && temp1.equals("vide"))
    			pileSemantique.add("vide");
    		else
    			pileSemantique.add("erreur");
    		break;
    		
    	case 14 :
    		temp1= pileSemantique.pop();
    		temp2= pileSemantique.pop();
    		temp3= pileSemantique.pop();
    		String temp4= pileSemantique.pop();
    		if (temp4.equals("Bool")  && temp3.equals(temp2) && temp2.equals(temp1) && temp1.equals("vide"))
    			pileSemantique.add("vide");
    		else
    			pileSemantique.add("erreur");
    		break;

    		
    	case 15 :
    		
    		temp1= pileSemantique.pop();
    		temp2= pileSemantique.pop();
    		if (Rechercher("").equals("Entier")  && temp1.equals(temp2) && temp1.equals("vide"))
    			pileSemantique.add("vide");
    		else
    			pileSemantique.add("erreur");
    		break;

    	
    	case 16 :
    		
    		
    		break;
    		
    	case 17 :
    		
    		break;
    		
    	case 18 :
    		pileSemantique.add("vide");
    		break;
    		
    	case 19 :
    
    		temp1= pileSemantique.pop();
    		if (temp1.equals("Entier")  || temp1.equals("Reel"))
    			pileSemantique.add(temp1);
    		else
    			pileSemantique.add("erreur");
    		break;
    		
    	case 20 :
    		temp1= pileSemantique.pop();
    		if (temp1.equals("Bool"))
    			pileSemantique.add(temp1);
    		else
    			pileSemantique.add("erreur");
    		break;
    		
    	case 21 :

    		temp1= pileSemantique.pop();
    		if (temp1.equals("Entier")  || temp1.equals("Reel"))
    			pileSemantique.add(temp1);
    		else
    			pileSemantique.add("erreur");
    		break;
    		
    	case 22 :
    		pileSemantique.add("vide");
    		break;
    		
    	case 23 :
    		
    		temp1= pileSemantique.pop();
    		temp2= pileSemantique.pop();
    		if (temp1.equals(temp2))
    			pileSemantique.add(temp2);
    		else if (temp1.equals("Reel") && temp2.equals("Entier"))
    			pileSemantique.add(temp1);
    		else if (temp2.equals("Reel") && temp1.equals("Entier"))
    			pileSemantique.add(temp2);
    		else 
    			pileSemantique.add("erreur");
    		break;
    		
    	case 24 :
    		temp1= pileSemantique.pop();
    		if (temp1.equals(Rechercher("")) || temp1.equals("vide"))
    			pileSemantique.add(Rechercher(""));
    		else 
    			pileSemantique.add("erreur");    		
    		break;
    		
    	case 25 :

    		temp1= pileSemantique.pop();
    		if (temp1.equals("vide") || temp1.equals("Entier"))
    			pileSemantique.add("Entier");
    		else
        		pileSemantique.add("erreur");
    		break;
    		
    	case 26 :
    		temp1= pileSemantique.pop();
    		if (temp1.equals("vide") || temp1.equals("Reel")|| temp1.equals("Entier"))
    			pileSemantique.add("Reel");
    		else
        		pileSemantique.add("erreur");
    		break;
    		
    	case 27 :
    		temp1= pileSemantique.pop();
    		if (temp1.equals("vide") || temp1.equals("Chaine"))
    			pileSemantique.add("Chaine");
    		else
        		pileSemantique.add("erreur");
    		break;
    		
    	case 28 :

    		
    		temp1= pileSemantique.pop();
    		if (temp1.equals("vide") || temp1.equals("Caractere"))
    			pileSemantique.add("Caractere");
    		else
        		pileSemantique.add("erreur");
    		break;


    		
    	case 29 :
    		temp1= pileSemantique.pop();
    		if (temp1.equals("vide") || temp1.equals("Bool"))
    			pileSemantique.add("Bool");
    		else
        		pileSemantique.add("erreur");
    		break;
    		
    	case 30 :
    		temp1= pileSemantique.pop();
    		if (temp1.equals("vide") || temp1.equals("Bool"))
    			pileSemantique.add("Bool");
    		else
        		pileSemantique.add("erreur");
    		break;

    	
    	}
    	
    		
		
	}
    public void ouput() {
        
        
        System.out.println("**********Tableau SLRÂ¨********");

        for (int i = 0; i < 87 ; i++) {
            for (int j = 0; j <46; j++) {
                System.out.printf("%6s", tableSLR[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("**********Fin tableau SLR********");

    }
}
