/**
 *                  Universidade de Brasília
 *                  Instituto de Ciências Exatas
 *                  Departamento de Ciência da Computação
 * 
 *                  Engenharia de Software - turma B
 *                      
 *                  2º semestre de 2013
 *                  
 *                  Alunos:
 *                  - Felipe Camargos Costa - 10/0100341
 *                  - Gutemberg Guilherme de Araújo - 11/0120451
 *                  - Erasmo de Castro Leite Junior - 12/0139855
 * 
 *                  Descrição:
 *                  Classe que modela a tela de escolha de requerente.
 */

package telas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controladoras.CtrRequerente;
import controladoras.CtrUsuario;
import entidades.Requerente;

/**
 * Classe que modela uma tela usada para escolher um requerente responsável pelo login no sistema.
 * 
 * @author felipe
 * @see CtrUsuario
 * @see JFrame
 */
public class TelaEscolherRequerente extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel labelRequerente;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton buttonSair;
    private JButton buttonLogin;
    private CtrRequerente controladora;
    private GridBagLayout layout;
    private GridBagConstraints constraints;

    /**
     * Construtor utiliza o método <code>criarTela</code>.
     * 
     * @see #criarTela
     */
    public TelaEscolherRequerente() {
        criarTela();
        controladora = new CtrRequerente();
    }

    /**
     * Método responsável por instanciar, posicionar e adicionar componentes à tela.
     * 
     * @see GridBagLayout, GridBagConstraints
     */
    public void criarTela() {

        // Cria objetos para determinar o layout da tela e para manipular as constantes usadas por este layout.
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();

        // Padrão de constraints para todos os componentes da tela.
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;

        // Instancia, configura posicionamento e adiciona componentes ao frame.

        labelRequerente = new JLabel("Requerente:");
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        layout.setConstraints(labelRequerente, constraints);
        add(labelRequerente);

        textField = new JTextField(20);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 1;
        constraints.gridy = 0;
        layout.setConstraints(textField, constraints);
        add(textField);

        labelRequerente = new JLabel("Senha:");
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 0;
        constraints.gridy = 1;
        layout.setConstraints(labelRequerente, constraints);
        add(labelRequerente);

        passwordField = new JPasswordField(20);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 1;
        constraints.gridy = 1;
        layout.setConstraints(passwordField, constraints);
        add(passwordField);

        buttonSair = new JButton("Sair");
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 0;
        constraints.gridy = 4;
        layout.setConstraints(buttonSair, constraints);
        add(buttonSair);

        buttonLogin = new JButton("Login");
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 2;
        constraints.gridy = 4;
        layout.setConstraints(buttonLogin, constraints);
        add(buttonLogin);

        // Adiciona um manipulador de eventos para o pressionameto dos botões.
        buttonSair.addActionListener(new ButtonHandler());
        buttonLogin.addActionListener(new ButtonHandler());

        // Informa o título, dimensões, diz se é redimensionável e torna visível a tela.
        setTitle("Login do Requerente");
        setResizable(false);
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Classe interna usada para manipular eventos dos botões da tela.
     * 
     * @see ActionListener
     */
    private class ButtonHandler implements ActionListener {

        /**
         * Método executado quando um usuário pressiona um dos botões da tela.
         * 
         * @param event
         *            Evento de ação criado ao se pressionar um botão.
         */
        public void actionPerformed(ActionEvent event) {

            // Se a origem do evento for o botão de cadastrar, então:
            if (event.getSource() == buttonLogin) {

                String texto = textField.getText();

                Set<Requerente> requerentes = new HashSet<Requerente>();

                try {
                    requerentes = controladora.recuperarTodosRequerentes();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),
                            "Erro na recuperação da lista de requerentes no banco de dados", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                    dispose();
                }

                for (Requerente requerente : requerentes) {

                    if (requerente.getNome().equals(texto)) {
                        TelaCadastrarRequisicao telaCadastrarRequisicao = new TelaCadastrarRequisicao();
                        dispose();
                        return;
                    }
                }

                JOptionPane.showMessageDialog(null, "Requerente não encontrado", "Erro no login.", JOptionPane.ERROR_MESSAGE);
            }

            // Se não for um evento disparado pelo botão de cadastrar, simplesmente fecha a tela.
            else {
                dispose();
            }
        }
    }
}