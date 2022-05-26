
import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column
%{
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
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
{Comentario}|{EspacioEnBlanco} { /* Ignorar */ }

/* Identificador */
"&"{Identificador} { return token(yytext(), "IDENTIFICADOR", yyline, yycolumn); }

/* Tipo de dato */
entero |
decimal |
cad |
bool { return token(yytext(), "TIPO_DATO", yyline, yycolumn); }

/* Numero */
{Numero} { return token(yytext(), "N_NUMERO", yyline, yycolumn);}

/* Operadores de agrupacion */
"(" { return token(yytext(), "PARENTESIS_A", yyline, yycolumn);}
")" { return token(yytext(), "PARENTESIS_C", yyline, yycolumn);}
"{" { return token(yytext(), "LLAVE_A", yyline, yycolumn);}
"}" { return token(yytext(), "LLAVE_C", yyline, yycolumn);}
"[" { return token(yytext(), "CORCHETE_A", yyline, yycolumn);}
"]" { return token(yytext(), "CORCHETE_C", yyline, yycolumn);}

/* Signos de apuntuacion */
"," { return token(yytext(), "COMA", yyline, yycolumn);}
"." { return token(yytext(), "PUNTO", yyline, yycolumn);}
";" { return token(yytext(), "PUNTOyCOMA", yyline, yycolumn);}
":" { return token(yytext(), "DOBLE_PUNTO", yyline, yycolumn);}

/* Operdador de asignacion */
"-." { return token(yytext(), "Op_Asig", yyline, yycolumn);}


/* Operador Aritmetico */
"<" { return token(yytext(), "MENOR_QUE", yyline, yycolumn);}
">" { return token(yytext(), "MAYOR_QUE", yyline, yycolumn);}
"<=" { return token(yytext(), "MENOR_IGUAL_QUE", yyline, yycolumn);}
">=" { return token(yytext(), "MAYOR_IGUAL_QUE", yyline, yycolumn);}
"==" { return token(yytext(), "IGUAL_QUE", yyline, yycolumn);}
"!=" { return token(yytext(), "DIFERENTE_DE", yyline, yycolumn);}

/*OPERADORES DE MATEMATICAS */
"+" { return token(yytext(), "SUMA", yyline, yycolumn);}
"-" { return token(yytext(), "RESTA", yyline, yycolumn);}
"/" { return token(yytext(), "DIVISION", yyline, yycolumn);}
"*" { return token(yytext(), "MULTIPLICACION", yyline, yycolumn);}

/* OPERADOR LOGICO */
"&" { return token(yytext(), "VERDADERO_SI_AMBOS", yyline, yycolumn);}
"|" { return token(yytext(), "VERDADERO_SI_UNO_O_AMBOS", yyline, yycolumn);}
"!" { return token(yytext(), "INVIERTE_VALOR_FINAL", yyline, yycolumn);}

/* ciclos */
repetir |
repetirMientras { return token(yytext(), "REPETIR", yyline, yycolumn);}

/* detener */ 
interrumpir { return token(yytext(), "DETENER_REPETIR", yyline, yycolumn);}

/* Estructura Si */
si |
sino { return token(yytext(), "ESTRUCTURA_SI", yyline, yycolumn);}

/* Final */
final { return token(yytext(), "FINAL", yyline, yycolumn);}

//NUMERO ERRONEO
0{Numero} { return token(yytext(), "ERROR_1", yyline, yycolumn);}

//IDENTIFICADOR ERRONEO
{Identificador} { return token(yytext(), "ERROR_2", yyline, yycolumn);}

. { return token(yytext(), "ERROR", yyline, yycolumn);}
