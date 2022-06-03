
import compilerTools.TextColor;
import java.awt.Color;

%%
%class LexerColor
%type TextColor
%char
%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int)start, size, color);
    }
%}
/*Variables basicas de comentarios y espacios */
    TerminadorDeLinea = \r|\n|\r\n
    EntradaDeCaracter = [^\r\n]
    EspacioEnBlanco     = {TerminadorDeLinea} | [ \t\f]
    ComentarioTradicional   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
    FinDeLineaComentario     = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?
    ContenidoComentario       = ( [^*] | \*+ [^/*] )*
    ComentarioDeDocumentacion = "/**" {ContenidoComentario} "*"+ "/"

    /* Comentario */
    Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}

   
    /* Identificador */
    Letra = [A-Za-zÑñ_áéíóú]
    Digito = [0-9]
    Identificador = {Letra}({Letra}|{Digito})*
    
    /* Numero */
    Numero = 0 | [1-9][0-9]*

    /* Numero_deci */
    Numero_deci = 0 "." 0 | [1-9][0-9]* "." [0-9]*

    /*Temperatura*/
    Temperatura =  "."  | [1-9][0-9]* "." [0-9]*
    
    /*cad*/
    cad= {Letra} {Letra}*
    
    /*Bool*/
    Bool= "VERDADERO" | "FALSO"
    

%%
{Comentario} { return textColor(yychar, yylength(), new Color(146, 146, 146));}
{EspacioEnBlanco} {/* Ignorar */ }

/* Identificador */
"&"{Identificador} { return textColor(yychar, yylength(), new Color(255, 200, 0)): }

/* Tipo de dato */
ent |
deci |
cad |
Bool { return textColor(yychar, yylength(), new Color(0, 255, 0)); }

/* Numero */
{Numero} { return textColor(yychar, yylength(), new Color(35, 120, 147));}

/* Numero_deci */
{Numero_deci} { return textColor(yychar, yylength(), new Color(35, 120, 147));}

/*cad*/
"K_"{cad} {yychar, yylength(), new Color(255, 0, 255));}
    
/*Bool*/
{Bool} {yychar, yylength(), new Color(0, 255, 255));}

/* Operadores de agrupacion */
"(" | ")" | "{" | "}" | "[" | "]" { return textColor(yychar, yylength(), new Color(100, 149, 237));}

/* Signos de apuntuacion */
"," | "." | ";" | ":" { return textColor(yychar, yylength(), new Color(0, 0, 0));}

/*OPERADORES DE MATEMATICAS*/
"+" { return textColor(yychar, yylength(), new Color(124, 252, 0));}
"-" { return textColor(yychar, yylength(), new Color(124, 252, 0));}
"/" { return textColor(yychar, yylength(), new Color(124, 252, 0));}
"*" { return textColor(yychar, yylength(), new Color(124, 252, 0));}

/* Operdador de asignacion */
"-." { return textColor(yychar, yylength(), new Color(102, 205, 170));}

/* Operador Aritmetico */
"<" | ">" | "<=" | ">=" | "==" | "!=" { return textColor(yychar, yylength(), new Color(102, 205, 170));}

/* OPERADOR LOGICO */
"&" | "|" | "!" { return textColor(yychar, yylength(), new Color(102, 205, 170));}

/* ciclos */
repetir |
repetirMientras { /* Ignorar */}

/* detener */ 
interrumpir { /* Ignorar */ }

/* Estructura Si */
si |
sino { /* Ignorar */ }

/* Final */
final { return textColor(yychar, yylength(), new Color(255, 0, 0));}

/*FUNCIONES PARA EL SISTEMA*/

//FUNCION PARA MUTAR
Mutar { return textColor(yychar, yylength(), new Color(0, 144, 255));}

//PALABRAS RESERVADAS PARA EVALUAR
fase1 |
fase2 |
fase3 |
fase4 |
fase5 |
fase6 { return textColor(yychar, yylength(), new Color(0, 0, 255)); }

//TEMPERATURA
"T"Temperatura { /* Ignorar */ }

//FUNCION PARA FIJAR ORIGEN
fijar_Origen { return textColor(yychar, yylength(), new Color(0, 144, 255));}

//FUNCION PARA EXPANDIR
expandir {return textColor(yychar, yylength(), new Color(0, 144, 255));}

//FUNCION PARA GENERAR GRAFICA
generar_graf { return textColor(yychar, yylength(), new Color(0, 144, 255));}

//NUMERO ERRONEO
0{Numero} { /* Ignorar */ }
//IDENTIFICADOR ERRONEO
{Identificador} { /* Ignorar */ }

. { /* Ignorar */ }