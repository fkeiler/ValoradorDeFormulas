package UI;

import Logica.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class JanelaPrincipal extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField formulaTextField;
    private Map<String, Atomo> atomos;

    public JanelaPrincipal() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    // Análise sintática da string de entrada
    private void onOK() {
        String proposicaoRaw = formulaTextField.getText();

        if (proposicaoRaw == "") {
            JOptionPane.showMessageDialog(this, "A sua proposição está vazia");
        }

        // Validação do número de parenteses
        int parentesesEsquerdos = 0;
        int parentesesDireitos = 0;
        for (char c : proposicaoRaw.toCharArray()) {
            if (c == '(') parentesesEsquerdos++;
            if (c == ')') parentesesDireitos++;
        }
        if (parentesesEsquerdos > parentesesDireitos) {
            JOptionPane.showMessageDialog(this, "Um ou mais parenteses direitos ausentes, cheque sua entrada");
            return;
        } else if (parentesesDireitos > parentesesEsquerdos) {
            JOptionPane.showMessageDialog(this, "Um ou mais parenteses esquerdos ausentes, cheque sua entrada");
            return;
        }

        //Validação de um parenteses para cada operador
        int operadores = 0;
        for (String s : proposicaoRaw.split(" ")) {
            if (s.matches("(AND)|(OR)|(NAND)|(NOR)|(XOR)|(IMPLIES)|(IFF)|(NOT)")) operadores++;
        }
        if (operadores > parentesesDireitos) {
            JOptionPane.showMessageDialog(this, "Faltam parenteses para um ou mais operadores");
            return;
        }

        // Geração da tabela verdade
        this.atomos = new HashMap<>();
        new TabelaVerdade(interpretar(proposicaoRaw), new Vector(this.atomos.values()));
    }

    // Conversão da String em um objeto proposição
    private Proposicao interpretar(String s) {
        //Remove espaços na entrada
        s = s.trim();

        //Se iniciar por NOT retorna a negação da proposição entre parenteses
        if (s.startsWith("NOT")) {
            return new Negacao(interpretar(s.substring(3)));
        }

        // Se não for negação tampouco estiver envolvido por parenteses, é atômica
        if (!s.startsWith("(") || !s.endsWith(")")) {
            if (atomos.containsKey(s)) {
                return atomos.get(s);
            }
            atomos.put(s, new Atomo(s));
            return atomos.get(s);
        }

        // Intepreta qualquer conectivo entre os parenteses principais
        int parenteses = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                parenteses++;
            } else if (s.charAt(i) == ')') {
                parenteses--;
            } else if (parenteses == 1) {
                if (s.length() > i + 3 && s.substring(i, i + 4).equals(" OR "))
                    return new Molecula(interpretar(s.substring(1, i)), "OR", interpretar(s.substring(i + 4, s.length() - 1)));

                if (s.length() > i + 4) {
                    if (s.substring(i, i + 5).equals(" AND "))
                        return new Molecula(interpretar(s.substring(1, i)), "AND", interpretar(s.substring(i + 5, s.length() - 1)));

                    if (s.substring(i, i + 5).equals(" XOR "))
                        return new Molecula(interpretar(s.substring(1, i)), "XOR", interpretar(s.substring(i + 5, s.length() - 1)));

                    if (s.substring(i, i + 5).equals(" NOR "))
                        return new Molecula(interpretar(s.substring(1, i)), "NOR", interpretar(s.substring(i + 5, s.length() - 1)));

                    if (s.substring(i, i + 5).equals(" IFF "))
                        return new Molecula(interpretar(s.substring(1, i)), "IFF", interpretar(s.substring(i + 5, s.length() - 1)));
                }

                if (s.length() > i + 5 && s.substring(i, i + 6).equals(" NAND "))
                    return new Molecula(interpretar(s.substring(1, i)), "NAND", interpretar(s.substring(i + 6, s.length() - 1)));

                if (s.length() > i + 8 && s.substring(i, i + 9).equals(" IMPLIES "))
                    return new Molecula(interpretar(s.substring(1, i)), "IMPLIES", interpretar(s.substring(i + 9, s.length() - 1)));
            }
        }

        //Se tiver parenteses mas nenhum conectivo dentro intepreta o interior
        return interpretar(s.substring(1, s.length() - 1));
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Erro ao definir aparência de Janela: " + e);
        }

        JanelaPrincipal dialog = new JanelaPrincipal();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(panel1, gbc);
        formulaTextField = new JTextField();
        formulaTextField.setText("Fórmula");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(formulaTextField, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("<html>\n\t<h1>Dicionário</h1>\n\n\t<h2>Símbolos</h2>\n \n\t<b>NOT</b>: não _, ¬<br>\n\t<b>AND</b>: _ e _, ∧<br>\n\t<b>OR</b>: _ ou _, ∨<br>\n\n\t<b>IMPLIES</b>: Se _ então _, →<br>\n\t<b>IFF</b>: _ se e somente se _, ↔<br>\n\n\t<b>NAND</b>: Não _ e _, ⊼<br>\n\t<b>NOR</b>: Nem _ nem _, ⊽<br>\n\t\n\t<b>XOR</b>: Ou _ ou _, ⊕<br>\n\n\t<h2>Sintaxe</h2>\n\n\tNOT(Exp)<br>\n\t(Exp1 OP Exp2)<br><br>\n\n\te.g.: (p IFF NOT((p OR q)))\n\n</html>");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.VERTICAL;
        contentPane.add(panel2, gbc);
        buttonOK = new JButton();
        buttonOK.setText("OK");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(buttonOK, gbc);
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(buttonCancel, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
