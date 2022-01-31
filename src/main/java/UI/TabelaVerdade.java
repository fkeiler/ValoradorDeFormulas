package UI;

import Logica.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import java.io.*;
import java.util.Vector;

public class TabelaVerdade extends JDialog {
    private JPanel contentPane;
    private JButton salvarComoButton;
    private JTable table1;

    public TabelaVerdade(Proposicao p, Vector<Atomo> a) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(salvarComoButton);

        //Definir função para salvar a tabela gerada
        salvarComoButton.addActionListener(e -> salvar(table1.getModel()));

        //Insere todas as combinações de valores para os atomos e seu resultado final
        double combinacoes = Math.pow(2, a.size());
        Vector<Vector<Boolean>> valores = new Vector<>();
        for(int i=0; i<combinacoes; i++){
            Vector<Boolean> valorProposicao = new Vector<>();
            for(int j=0; j<a.size(); j++){
                boolean b = ((i >> j) & 1) != 0;
                a.get(j).atribuirValor(b);
                valorProposicao.add(b);
            }
            valorProposicao.add(p.valorar());
            valores.add(valorProposicao);
        }

        //Define no TableModel como cabeçalho os átomos e a proposição final e como valores aqueles definidos na parte anterior
        Vector<Proposicao> cabecalho = new Vector<>(a);
        cabecalho.add(p);
        DefaultTableModel tabela = new DefaultTableModel();
        tabela.setDataVector(valores, cabecalho);
        table1.setModel(tabela);

        pack();
        setVisible(true);
    }

    public static void salvar(TableModel t){
        // Abre uma janela de FileChooser
        JFileChooser fchooser = new JFileChooser();
        fchooser.setDialogTitle("Salvar Tabela Verdade");
        fchooser.setFileFilter(new FileNameExtensionFilter("Comma Separated Values", "csv"));
        int saveResult = fchooser.showSaveDialog(null);
        // Ao confirmar, salva o arquivo
        if(saveResult == JFileChooser.APPROVE_OPTION){
            String conteudoCSV = "";

            // Insere os nomes dos atomos e da proposição final
            for(int i=0; i<t.getColumnCount(); i++){
                conteudoCSV += t.getColumnName(i);
                if(i != t.getColumnCount() - 1)
                    conteudoCSV += ",";
            }
            conteudoCSV += "\n";

            // Insere os valores
            for(int i=0; i<t.getRowCount(); i++){
                for(int j=0; j<t.getColumnCount(); j++){
                    conteudoCSV += t.getValueAt(i, j).toString();
                    if(j != t.getColumnCount()-1)
                        conteudoCSV += ",";
                }
                conteudoCSV += "\n";
            }

            //Escreve no arquivo e fecha
            try {
                File f = fchooser.getSelectedFile();
                FileWriter fw = new FileWriter(f);
                fw.write(conteudoCSV);
                fw.flush();
                fw.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("Salvamento cancelado");
        }
    }

    public static void main(String[] args){

    }
}
