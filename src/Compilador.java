
import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Carlos
 */
public class Compilador extends javax.swing.JFrame {

    private String title;
    private Directory Directorio;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;

    /**
     * Creates new form Compilador
     */
    public Compilador() {
        initComponents();
        init();
        this.setSize(1100, 550);
        this.setTitle("<KOVVID>");
        this.setLocationRelativeTo(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCompilar = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        btnGuardarC = new javax.swing.JButton();
        txaErrores = new java.awt.TextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLexemas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtpEscritorio = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(null);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo);
        btnNuevo.setBounds(19, 19, 90, 24);

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });
        jPanel1.add(btnAbrir);
        btnAbrir.setBounds(120, 20, 90, 24);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar);
        btnGuardar.setBounds(220, 20, 100, 24);

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCompilar);
        btnCompilar.setBounds(490, 20, 90, 24);

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEjecutar);
        btnEjecutar.setBounds(600, 20, 100, 24);

        btnGuardarC.setText("Guardar Como");
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardarC);
        btnGuardarC.setBounds(330, 20, 110, 24);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(6, 5, 720, 70);
        getContentPane().add(txaErrores);
        txaErrores.setBounds(10, 370, 720, 110);

        tblLexemas.setBackground(new java.awt.Color(204, 204, 204));
        tblLexemas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Componente Lexico", "Lexema", "{Linea, Columna}"
            }
        ));
        jScrollPane3.setViewportView(tblLexemas);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(740, 100, 310, 380);

        jScrollPane2.setViewportView(jtpEscritorio);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 110, 710, 250);

        jButton1.setText("Estatica");
        getContentPane().add(jButton1);
        jButton1.setBounds(950, 10, 57, 24);

        jButton2.setText("Dinamica");
        getContentPane().add(jButton2);
        jButton2.setBounds(950, 40, 65, 24);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Directorio.New();
        clearFields();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (Directorio.Open()) {
            colorAnalisis();
            clearFields();
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (Directorio.Save()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (Directorio.Save()) {
                compile();
            }
        } else {
            compile();
        }

    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        btnCompilar.doClick();
        if (codeHasBeenCompiled) {
            if (errors.size() > 0) {
                JOptionPane.showMessageDialog(null, "No se puede ejecutar el codigo ya que se encontro uno o mas errores");
            } else {

            }
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if (Directorio.SaveAs()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    /**
     * @param args the command line arguments
     */
    private void init() {
        title = "KOVVID";
        setLocationRelativeTo(null);
        setTitle(title);
        Directorio = new Directory(this, jtpEscritorio, title, ".kov");
        addWindowListener(new WindowAdapter() {
            public void windwClosing(WindowEvent e) {
                Directorio.Exit();
                System.exit(0);
            }
        });
        Functions.setLineNumberOnJTextComponent(jtpEscritorio);
        timerKeyReleased = new Timer(300, ((e) -> {
            timerKeyReleased.stop();
            colorAnalisis();
        }));
        Functions.insertAsteriskInName(this, jtpEscritorio, () -> {
            timerKeyReleased.restart();
        });
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();
        Functions.setAutocompleterJTextComponent(new String[]{"hola", "numero", "este"}, jtpEscritorio, () -> {
            timerKeyReleased.restart();
        });

    }

    private void colorAnalisis() {
        LexerColor lexer;
        textsColor.clear();

        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpEscritorio.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF-8"));
            lexer = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexer.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Compilador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Compilador.class.getName()).log(Level.SEVERE, null, ex);
        }
        Functions.colorTextPane(textsColor, jtpEscritorio, new Color(40, 40, 40));
    }

    private void clearFields() {
        Functions.clearDataInTable(tblLexemas);
        txaErrores.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
    }

    private void compile() {
        clearFields();
        LexicalAnalisis();
        fillTableTokens();
        SintacticAnalisis();
        SemanticAnalisis();
        ImprimirConsola();
        codeHasBeenCompiled = true;
    }

    private void LexicalAnalisis() {
        Lexer lexer;

        try {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpEscritorio.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF-8"));
            lexer = new Lexer(entrada);
            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                tokens.add(token);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Compilador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Compilador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void fillTableTokens() {
        tokens.forEach(token -> {
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(),
                "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(tblLexemas, data);
        });
    }

    private void SintacticAnalisis() {
        Grammar gramatica = new Grammar(tokens, errors);
        
        /* ELIMINACION DE ERRORES*/
        gramatica.delete(new String[]{"ERROR", "ERROR_1", "ERROR_2"}, 1);
                
         /*AGRUPACION DE VALORES*/
         gramatica.group("VALOR", "(N_NUMERO | COLOR)", true);
         
         /*DECLARACION DE VARIABLES*/
         gramatica.group("VARIABLE", "TIPO_DATO IDENTIFICADOR Op_Asig VALOR", true);
         gramatica.group("VARIABLE", "TIPO_DATO Op_Asig VALOR", true, 2, "ERROR SINTACTICO {}: FALTA EL IDENTIFICADOR EN LA VARIABLE[#,%]");
         
         gramatica.finalLineColumn();
         
         gramatica.group("VARIABLE", "TIPO_DATO IDENTIFICADOR Op_Asig", 3, "ERROR SINTACTICO {}: FALTA EL VALOR EN LA DECLARACION [#, %]");
         
         gramatica.initialLineColumn();
         
         //AQUI SE AGREGAN MAS CONBINACIONES DESAPARECIENDO UN TOKEN,
         //AGREGAR MAS VARIABLES O LO QUE SE NECESITE PARA LAS GRAMATICAS.
         
         /* ELIMINACION DE TIPOS DE DATO Y OPERADORES DE ASIGNACION*/
         gramatica.delete("TIPO_DATO", 4, "ERROR SINTACTICO {}: EL TIPO DE DATO NO ESTA EN UNA DECLARACION [#, %]");
         gramatica.delete("Op_Asig", 4, "ERROR SINTACTICO{}: EL OPERADOR DE ASIGNACION NO ESTA EN UNA DELCARACION [#, %]");
         
         /* AGRUPACION DE IDENTIFICADORES Y DEFINICION DE PARAMETROS */
         gramatica.group("VALOR", "IDENTIFICADOR", true);
         gramatica.group("PARAMETROS", "VALOR (COMA VALOR)+");
         /**/
         
         /*agrupacion de funciones*/
         gramatica.group("FUNCION", "(EVALUAR | MUTAR | FIJAR_ORIGEN | EXPANDIR | GENERAR_GRAF)", true);
         gramatica.group("FUNCION_COMP", "FUNCION PARENTESIS_A (VALOR | PARAMETROS)? PARENTESIS_C", true);
         gramatica.group("FUNCION_COMP", "FUNCION (VALOR | PARAMETROS)? PARENTESIS_C", true, 6,"ERROR SINTACTICO{}:FALTA EL PARENTESIS QUE ABRE EN LA FUNCION[#, %]");
         gramatica.finalLineColumn();
         gramatica.group("FUNCION_COMP", "FUNCION PARENTESIS_A (VALOR | PARAMETROS)", true, 7,"ERROR SINTACTICO{}:FALTA EL PARENTESIS QUE CIERRA EN LA FUNCION[#, %]");
         
         gramatica.initialLineColumn();
         
         /*FUNCIONES DE EVALUAR*/
         /////////////gramatica.group("FUNCION_EVALUAR", "FUNCION_COMP(FASES)", true);
         
         /*ELIMINACION DE FUNCIONES INCOMPLETAS*/
         gramatica.delete("FUNCION",8,"ERROR SINTACTICO{}: LA FUNCION NO ESTA DECLARADA CORRECTAMENTE[#, %]");
          
         
         
        gramatica.show();

    }

    private void SemanticAnalisis() {

    }

    private void ImprimirConsola() {
        int sizeErrors = errors.size();
        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErro = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(errors);
                strErro += strError + "\n";

            }
            txaErrores.setText("Compilacion terminada...\n" + strErro + "\nLa compilacion termino con errores");
        } else {
            txaErrores.setText("Compilacion terminada");
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());

                new Compilador().setVisible(true);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Compilador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jtpEscritorio;
    private javax.swing.JTable tblLexemas;
    private java.awt.TextArea txaErrores;
    // End of variables declaration//GEN-END:variables
}
