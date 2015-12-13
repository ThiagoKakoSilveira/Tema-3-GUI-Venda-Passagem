package controller;

import comboBoxModel.VooComboBoxModel;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import servico.ClienteServico;
import tableModels.PassagemTableModel;
import viewFomularios.FormularioPassagem;
import viewTabelas.TabelaPassagem;
import servico.PassagemServico;
import servico.VooServico;
import view.JanelaPassagem;
/**
 *
 * @author Thiago
 */
public class PassagemController {
    private final static int TABELA = 0;
    private final static int FORMCADASTRO = 1;
    private final static int FORMEDICAO = 2;
    private final static int FORMVISUALIZACAO = 3;
    
    private int telaAtual = 0;
    private int linhaSelecionada = -1;
    
    private JanelaPassagem janela;
    
    private PassagemServico servicoP;
    private VooServico servicoV;
    private ClienteServico servicoC;

    public void voltarPrincipal() {
        telaAtual = TABELA;
        this.atualizaTabela();
        this.atualizaComboBox();
        this.janela.mostrarPainel(JanelaPassagem.PAINELTABELA);
    }
    
    public void atualizaTabela() {
        TabelaPassagem painelTabela = this.janela.getPainelTabela();
        PassagemTableModel modelo = (PassagemTableModel) painelTabela.getTabela().getModel();

        //PacienteDao dao = new PacienteDaoBd();
        modelo.setPassagens(servicoP.listarPassagem());

        painelTabela.getTabela().updateUI();
    }
    
    public void atualizaComboBox() {
        FormularioPassagem painelFormulario = this.janela.getPainelFormulario();
        VooComboBoxModel modeloVoo = (VooComboBoxModel) painelFormulario.getjComboBoxVoo().getModel();
                
        modeloVoo.setVoos(servicoV.listarVoo());        
        painelFormulario.getjComboBoxVoo().updateUI();        
    }

    public void setJanela(JanelaPassagem janela) {
        this.janela = janela;
    }
    
    private void makeComboReadonly(JComboBox box) {
        FormularioPassagem painelFormulario = this.janela.getPainelFormulario();
        JComboBox boxMaquiado = box;        
        boxMaquiado.getEditor().getEditorComponent();
        Component editorComponent = boxMaquiado.getEditor().getEditorComponent();
        if (editorComponent instanceof JComboBox) {
            ((JComboBox) editorComponent).setEditable(false);
        }

        for (Component childComponent : boxMaquiado.getComponents()) {
            if (childComponent instanceof AbstractButton) {
                childComponent.setEnabled(false);
                final MouseListener[] listeners = childComponent.getListeners(MouseListener.class);
                for (MouseListener listener : listeners) {
                    childComponent.removeMouseListener(listener);
                }
            }
        }

        final MouseListener[] mouseListeners = boxMaquiado.getListeners(MouseListener.class);
        for (MouseListener listener : mouseListeners) {
            boxMaquiado.removeMouseListener(listener);
        }

        final KeyListener[] keyListeners = boxMaquiado.getListeners(KeyListener.class);
        for (KeyListener keyListener : keyListeners) {
            boxMaquiado.removeKeyListener(keyListener);
        }

        boxMaquiado.setFocusable(false);
        //box.getActionMap () clear ().; // nenhum efeito 
        //box.getInputMap () clear         
    }
    
}
