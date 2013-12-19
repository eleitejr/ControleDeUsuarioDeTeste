package telas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controladoras.CtrRequisicao;

/**
 * 
 * Tela usada para o cadastro de uma nova requisição.
 * 
 * @author felipe
 * 
 */
public class TelaCadastrarRequisicao extends JFrame {

    private JLabel labelCoordenacao;
    private JTextField textFieldCoordenacao;
    private String Evento;
    private JLabel labelEvento;
    private JComboBox<String> eventoComboBox;
    private JButton buttonCadastrar;
    private CtrRequisicao controladora;
    private GridBagLayout layout;
    private GridBagConstraints constraints;

    /**
     * Construtor utiliza o método <code>criarTela</code>.
     * 
     * @see #criarTela
     */
    public TelaCadastrarRequisicao() {
        criarTela();
        controladora = new CtrRequisicao();
    }

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

        labelCoordenacao = new JLabel("Coordenacao:");
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        layout.setConstraints(labelCoordenacao, constraints);
        add(labelCoordenacao);

        textFieldCoordenacao = new JTextField(20);
        textFieldCoordenacao.setToolTipText("máximo: 30 letras");
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.gridy = 0;
        layout.setConstraints(textFieldCoordenacao, constraints);
        add(textFieldCoordenacao);

        labelEvento = new JLabel("Evento:");
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 0;
        constraints.gridy = 1;
        layout.setConstraints(labelEvento, constraints);
        add(labelEvento);

        eventoComboBox = new JComboBox<String>();
        /*
         * eventoComboBox.add("Exclusão"); eventoComboBox.add("Criação"); eventoComboBox.add("Troca de senha");
         * eventoComboBox.add("Criação"); eventoComboBox.add("Bloqueio"); eventoComboBox.add("Desbloqueio");
         * eventoComboBox.add("Envio de e-mail"); constraints.anchor = GridBagConstraints.EAST; constraints.gridx = 1;
         * constraints.gridy = 1; layout.setConstraints(eventoComboBox, constraints); add(eventoComboBox); }
         */
    }
}
