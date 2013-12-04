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
 *                  Classe que modela a tela de cadastro de usuário.
 */

package telas;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controladoras.CtrUsuario;
import entidades.Usuario;

/**
 * Classe que modela uma tela usada para cadastrar um novo usuário.
 * 
 * @see CtrUsuario
 * @see JFrame
 */
public class TelaCadastrarUsuario extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel labelNome;
    private JLabel labelErroValidacaoNome;
    private JLabel labelEmail;
    private JLabel labelErroValidacaoEmail;
    private JLabel labelSenha;
    private JLabel labelErroValidacaoSenha;
    private JTextField textFieldNome;
    private JTextField textFieldEmail;
    private JPasswordField passwordFieldSenha;
    private JButton buttonVoltar;
    private JButton buttonCadastrar;
    private CtrUsuario controladora;
    private GridBagLayout layout;
    private GridBagConstraints constraints;

    /**
     * Construtor utiliza o método <code>criarTela</code>.
     * 
     * @see #criarTela
     */
    public TelaCadastrarUsuario() {
        criarTela();
        controladora = new CtrUsuario();
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

        labelNome = new JLabel("Nome:");
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        layout.setConstraints(labelNome, constraints);
        add(labelNome);

        textFieldNome = new JTextField(20);
        textFieldNome.setToolTipText("máximo: 200 letras");
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.gridy = 0;
        layout.setConstraints(textFieldNome, constraints);
        add(textFieldNome);

        labelErroValidacaoNome = new JLabel("Nome não pode ser vazio e deve ter no máximo 200 caracteres.");
        labelErroValidacaoNome.setVisible(false);
        labelErroValidacaoNome.setForeground(new Color(255, 0, 0));
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 2;
        constraints.gridy = 0;
        layout.setConstraints(labelErroValidacaoNome, constraints);
        add(labelErroValidacaoNome);

        labelEmail = new JLabel("E-mail:");
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 0;
        constraints.gridy = 1;
        layout.setConstraints(labelEmail, constraints);
        add(labelEmail);

        textFieldEmail = new JTextField(20);
        textFieldEmail.setToolTipText("máximo: 50 letras");
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.gridy = 1;
        layout.setConstraints(textFieldEmail, constraints);
        add(textFieldEmail);

        labelErroValidacaoEmail = new JLabel("E-mail pode ter no máximo 50 caracteres.");
        labelErroValidacaoEmail.setVisible(false);
        labelErroValidacaoEmail.setForeground(new Color(255, 0, 0));
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 2;
        constraints.gridy = 1;
        layout.setConstraints(labelErroValidacaoEmail, constraints);
        add(labelErroValidacaoEmail);

        labelSenha = new JLabel("Senha:");
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 0;
        constraints.gridy = 2;
        layout.setConstraints(labelSenha, constraints);
        add(labelSenha);

        passwordFieldSenha = new JPasswordField(20);
        passwordFieldSenha.setToolTipText("5 caracteres");
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.gridy = 2;
        layout.setConstraints(passwordFieldSenha, constraints);
        add(passwordFieldSenha);

        labelErroValidacaoSenha = new JLabel("Senha não pode ser vazia e deve ter no máximo 30 caracteres.");
        labelErroValidacaoSenha.setVisible(false);
        labelErroValidacaoSenha.setForeground(new Color(255, 0, 0));
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 2;
        constraints.gridy = 2;
        layout.setConstraints(labelErroValidacaoSenha, constraints);
        add(labelErroValidacaoSenha);

        buttonVoltar = new JButton("Voltar");
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 0;
        constraints.gridy = 4;
        layout.setConstraints(buttonVoltar, constraints);
        add(buttonVoltar);

        buttonCadastrar = new JButton("Cadastrar");
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 2;
        constraints.gridy = 4;
        layout.setConstraints(buttonCadastrar, constraints);
        add(buttonCadastrar);

        // Adiciona um manipulador de eventos para o pressionameto dos botões.
        buttonVoltar.addActionListener(new ButtonHandler());
        buttonCadastrar.addActionListener(new ButtonHandler());

        // Informa o título, dimensões, diz se é redimensionável e torna visível a tela.
        setTitle("Cadastrar Usuário");
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
            if (event.getSource() == buttonCadastrar) {

                // Indica à controladora para zerar os indicadores de erros de validação.
                controladora.reiniciarFlagsErrosValidacao();

                // Cria um novo objeto usuario com os dados obtidos na tela.
                Usuario usuario = new Usuario(0, textFieldNome.getText(), new String(passwordFieldSenha.getPassword()),
                        textFieldEmail.getText(), true, true, false, false, null);

                // Tenta cadastrar o usuário e informa se houve erros.
                try {

                    // Se não houve erros de validação (a operação da controladora retornou 0, informa que o usuário foi
                    // cadastrado corretamente e fecha a tela.
                    if (controladora.cadastrarUsuario(usuario) == 0) {
                        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Cadastro de usuário",
                                JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }

                    // Senão, descobre as fontes do(s) erro(s) e informa na tela.
                    else {
                        if (controladora.isMostrarErroCadastroNome()) {
                            labelErroValidacaoNome.setVisible(true);
                        }

                        if (controladora.isMostrarErroCadastroEmail()) {
                            labelErroValidacaoEmail.setVisible(true);
                        }

                        if (controladora.isMostrarErroCadastroSenha()) {
                            labelErroValidacaoSenha.setVisible(true);
                        }
                    }
                }

                // Se uma exceção foi detectada na operação, informa o usuário do sistema e encerra a tela.
                catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro no cadastro de usuário", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                    dispose();
                }
            }

            // Se não for um evento disparado pelo botão de cadastrar, simplesmente fecha a tela.
            else {
                dispose();
            }
        }
    }
}