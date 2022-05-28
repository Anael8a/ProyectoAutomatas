
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
%%
{Comentario} { return textColor(yychar, yylength(), new Color(146, 146, 146));}
{EspacioEnBlanco} {/* Ignorar */ }

/* Identificador */
"&"{Identificador} { return textColor(yychar, yylength(), new Color(255, 200, 0)): }

/* Tipo de dato */
ent |
deci |
cad |
bool { return textColor(yychar, yylength(), new Color(0, 255, 0)); }

/* Numero */
{Numero} { return textColor(yychar, yylength(), new Color(35, 120, 147));}

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
"mutar" | "evaluar" | "fijar_Origen" | "expandir" | "generar_graf" { return textColor(yychar, yylength(), new Color(106, 90, 250));}

//NUMERO ERRONEO
0{Numero} { /* Ignorar */ }
//IDENTIFICADOR ERRONEO
{Identificador} { /* Ignorar */ }

. { /* Ignorar */ }