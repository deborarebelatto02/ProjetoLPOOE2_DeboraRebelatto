/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.ifsul.cc.projetolpooe2_deborarebelatto.view;

import br.edu.ifsul.cc.projetolpooe2_deborarebelatto.dao.PersistenciaJPA;
import br.edu.ifsul.cc.projetolpooe2_deborarebelatto.model.Livro;
import java.awt.BorderLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author debor
 */
public class TelaCadastroLivro extends javax.swing.JFrame {

    private Livro livro;
    private Livro livroOriginal;
    private boolean livroEditado = false;
    private JTable table;
    PersistenciaJPA jpa;

    /**
     * Creates new form TelaCadastroLivro
     */
    public TelaCadastroLivro() {
        setTitle("Gerenciamento de Livros");
        initComponents();
        //adicionarLivro();
        jpa = new PersistenciaJPA();
        setVisible(true);
    }

    private void adicionarLivro() {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String isbn = txtIsbn.getText();

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setIsbn(isbn);

        jpa.persist(livro);
        visualizarLivros();
        JOptionPane.showMessageDialog(this, "Livro adicionado com sucesso!");
        dispose();
    }
    
    /*private void adicionarLivro(){
        jpa = new PersistenciaJPA();
            jpa.conexaoAberta();
            jpa.persist(livro);
            jpa.fecharConexao();
            dispose();
    }*/

    private void salvarAlteracoes() {
        if (!txtTitulo.getText().equals(livroOriginal.getTitulo()) ||
            !txtAutor.getText().equals(livroOriginal.getAutor()) ||
            !txtIsbn.getText().equals(livroOriginal.getIsbn())) {

            livroOriginal.setTitulo(txtTitulo.getText());
            livroOriginal.setAutor(txtAutor.getText());
            livroOriginal.setIsbn(txtIsbn.getText());

            livroEditado = true;
        }
    }

    public boolean isLivroEditado() {
        return livroEditado;
    }
    
    private void editarLivro() throws Exception {
        // Código para editar um livro existente
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Long id = (Long) table.getValueAt(selectedRow, 0);
            Livro livro = (Livro) jpa.find(Livro.class, id);
            livro.setTitulo(txtTitulo.getText());
            livro.setAutor(txtAutor.getText());
            livro.setIsbn(txtIsbn.getText());

            jpa.persist(livro);
            visualizarLivros();
            JOptionPane.showMessageDialog(this, "Livro atualizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um livro para editar.");
        }
    }

    private void excluirLivro() throws Exception {
        // Código para excluir um livro selecionado
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Long id = (Long) table.getValueAt(selectedRow, 0);
            Livro livro = (Livro) jpa.find(Livro.class, id);
            jpa.remover(livro);
            visualizarLivros();
            JOptionPane.showMessageDialog(this, "Livro excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um livro para excluir.");
        }
    }

    private void visualizarLivros() {
        // Código para atualizar a JTable com os livros
        List<Livro> livros = jpa.listLivro();
        String[] colunas = {"ID", "Título", "Autor", "ISBN"};
        Object[][] dados = new Object[livros.size()][4];

        for (int i = 0; i < livros.size(); i++) {
            dados[i][0] = livros.get(i).getId();
            dados[i][1] = livros.get(i).getTitulo();
            dados[i][2] = livros.get(i).getAutor();
            dados[i][3] = livros.get(i).getIsbn();
        }

        if (table != null) {
            remove(table.getParent());
        }

        table = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAutor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtIsbn = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnVizualizar = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Título:");

        jLabel2.setText("Autor:");

        jLabel3.setText("ISBN:");

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnVizualizar.setText("Vizualizar");
        btnVizualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVizualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir)
                        .addGap(12, 12, 12)
                        .addComponent(btnVizualizar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTitulo)
                            .addComponent(txtAutor)
                            .addComponent(txtIsbn, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(btnVizualizar))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            excluirLivro();
        } catch (Exception ex) {
            Logger.getLogger(TelaCadastroLivro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        adicionarLivro();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            editarLivro();
        } catch (Exception ex) {
            Logger.getLogger(TelaCadastroLivro.class.getName()).log(Level.SEVERE, null, ex);
        }
        salvarAlteracoes();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnVizualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVizualizarActionPerformed
        visualizarLivros();

        visualizarLivros();
        setVisible(true);
    }//GEN-LAST:event_btnVizualizarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroLivro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnVizualizar;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
