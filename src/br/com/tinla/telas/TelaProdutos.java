/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tinla.telas;

import java.sql.*;
import br.com.tinla.dal.ModuloConexao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author vinic
 */
public class TelaProdutos extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaProdutos
     */
    public TelaProdutos() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //Metódo para adicionar novos produtos
    private void adicionar() {
        String sql = "insert into produtos(nome, categoria, valor, qntd_disponivel, fornecedor_id) values (?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtProNome.getText());
            pst.setString(2, txtProCategoria.getText());
            pst.setString(3, txtProValor.getText());
            pst.setString(4, txtProQntd.getText());
            pst.setString(5, txtProFornecedor.getText());

            //Validação dos campos obrigatórios
            if (((((txtProNome.getText().isEmpty())
                    || txtProCategoria.getText().isEmpty()) || txtProValor.getText().isEmpty())
                    || txtProQntd.getText().isEmpty()) || txtProFornecedor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

                //A estrutura abaixo é usada para confirma a inserção dos dados na tabela
                //A linha abaixo atualiza a tabela produto com os dados dos campos do formulario
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
                    limpar();

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    //Criando o metódo para alterar dados do cliente
    private void alterar() {

        String sql = "update produtos set nome=?, categoria=?, valor=?, qntd_disponivel=?, fornecedor_id=? where idProduto=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtProNome.getText());
            pst.setString(2, txtProCategoria.getText());
            pst.setString(3, txtProValor.getText());
            pst.setString(4, txtProQntd.getText());
            pst.setString(5, txtProFornecedor.getText());
            pst.setString(6, txtProId.getText());

            if (((((txtProNome.getText().isEmpty())
                    || txtProCategoria.getText().isEmpty()) || txtProValor.getText().isEmpty())
                    || txtProQntd.getText().isEmpty()) || txtProFornecedor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

                //A estrutura abaixo é usada para confirma a alteração dos dados na tabela
                //A linha abaixo atualiza a tabela cliente com os dados dos campos do formulario
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do produto alterados com sucesso");
                    limpar();
                    btnAdicionar.setEnabled(true);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metódo responsável pela remoção de usuários 
    private void remover() {
        //A estrutura abaixo confirma a remoção do usuário
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este produto?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from produtos where idProduto=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtProId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Produto removido com sucesso");
                    limpar();
                    btnAdicionar.setEnabled(true);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    //Metódo para pesquisar produto pelo nome com filtro 
    private void pesquisar_produto() {
        String sql = "select idProduto as Id, nome as Nome, categoria as Categoria, valor as Valor, qntd_disponivel as Quantidade, fornecedor_id as Fornecedor from produtos where nome like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //Passando o conteúdo da caixa de pesquisa para o interroga
            //Atenção ao porcentagem que é a continuação da String SQL
            pst.setString(1, txtProPesquisar.getText() + "%");
            rs = pst.executeQuery();

            //A linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela 
            tblProdutos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //Metódo para setar os campos do formulário com o conteúdo da tabela
    public void setar_campos() {
        int setar = tblProdutos.getSelectedRow();

        txtProId.setText(tblProdutos.getModel().getValueAt(setar, 0).toString());
        txtProNome.setText(tblProdutos.getModel().getValueAt(setar, 1).toString());
        txtProCategoria.setText(tblProdutos.getModel().getValueAt(setar, 2).toString());
        txtProValor.setText(tblProdutos.getModel().getValueAt(setar, 3).toString());
        txtProQntd.setText(tblProdutos.getModel().getValueAt(setar, 4).toString());
        txtProFornecedor.setText(tblProdutos.getModel().getValueAt(setar, 5).toString());

        //A linha abaixo desabilita o bom adiciona
        btnAdicionar.setEnabled(false);
    }

    //Metódo para limpar os campos
    private void limpar(){
        txtProPesquisar.setText(null);
        txtProId.setText(null);
        txtProNome.setText(null);
        txtProCategoria.setText(null);
        txtProValor.setText(null);
        txtProQntd.setText(null);
        txtProFornecedor.setText(null);
        ((DefaultTableModel) tblProdutos.getModel()).setRowCount(0);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtProPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtProId = new javax.swing.JTextField();
        txtProNome = new javax.swing.JTextField();
        txtProValor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtProQntd = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtProFornecedor = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtProCategoria = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Tinla - Produtos");

        txtProPesquisar.setToolTipText("Pesquisar");
        txtProPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProPesquisarActionPerformed(evt);
            }
        });
        txtProPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProPesquisarKeyReleased(evt);
            }
        });

        jLabel1.setText("*Campos obrigatórios");

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Categoria", "Valor", "Qntd disponivel", "Fornecedor_id"
            }
        ));
        tblProdutos.getTableHeader().setReorderingAllowed(false);
        tblProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProdutos);

        jLabel2.setText("ID:");

        txtProId.setToolTipText("Id");
        txtProId.setEnabled(false);

        txtProNome.setToolTipText("Nome");
        txtProNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProNomeActionPerformed(evt);
            }
        });

        txtProValor.setToolTipText("Valor");

        jLabel3.setText("*Nome:");

        jLabel4.setText("*Categoria:");

        jLabel5.setText("*Valor:");

        jLabel6.setText("*Qntd disp:");

        txtProQntd.setToolTipText("Quantidade disponivel");
        txtProQntd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProQntdActionPerformed(evt);
            }
        });

        jLabel7.setText("*Fornecedor_id:");

        txtProFornecedor.setToolTipText("Fornecedor");
        txtProFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProFornecedorActionPerformed(evt);
            }
        });

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tinla/icones/create.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tinla/icones/update.png"))); // NOI18N
        jButton2.setToolTipText("Alterar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setPreferredSize(new java.awt.Dimension(80, 80));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tinla/icones/delete.png"))); // NOI18N
        jButton3.setToolTipText("Remover");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setPreferredSize(new java.awt.Dimension(80, 80));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tinla/icones/pesquisar.png"))); // NOI18N

        txtProCategoria.setToolTipText("Categoria");
        txtProCategoria.setPreferredSize(new java.awt.Dimension(233, 42));
        txtProCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtProPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProNome)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProId, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(99, 99, 99)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(107, 107, 107)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtProCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                    .addComponent(txtProQntd))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtProFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtProValor, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProId, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProNome, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtProValor, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProQntd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(txtProFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(332, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(704, 869));
    }// </editor-fold>//GEN-END:initComponents

    private void txtProPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProPesquisarActionPerformed

    private void txtProNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProNomeActionPerformed

    private void txtProFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProFornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProFornecedorActionPerformed

    private void txtProQntdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProQntdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProQntdActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Chamando metódo alterar
        alterar();


        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtProCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProCategoriaActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        //Chamando o metódo adicionar
        adicionar();


       
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void txtProPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProPesquisarKeyReleased
        //Chamando metódo de busco com filtro
        pesquisar_produto();


        
    }//GEN-LAST:event_txtProPesquisarKeyReleased

    private void tblProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutosMouseClicked
        //Chamando metódo setar_campos
        setar_campos();

        // TODO add your handling code here:
    }//GEN-LAST:event_tblProdutosMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Chamando o metódo remover
        remover();


       
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtProCategoria;
    private javax.swing.JTextField txtProFornecedor;
    private javax.swing.JTextField txtProId;
    private javax.swing.JTextField txtProNome;
    private javax.swing.JTextField txtProPesquisar;
    private javax.swing.JTextField txtProQntd;
    private javax.swing.JTextField txtProValor;
    // End of variables declaration//GEN-END:variables
}
