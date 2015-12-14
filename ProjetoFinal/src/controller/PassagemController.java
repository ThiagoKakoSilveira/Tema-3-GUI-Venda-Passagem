package controller;

import comboBoxModel.VooComboBoxModel;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.Passagem;
import servico.ClienteServico;
import tableModels.PassagemTableModel;
import viewFomularios.FormularioPassagem;
import viewTabelas.TabelaPassagem;
import servico.VooServico;
import servico.PassagemServico;
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
    
    public PassagemController() {
        telaAtual = TABELA;
        servicoV = new VooServico();
        servicoP = new PassagemServico();
        servicoC = new ClienteServico();
    }
    
    public PassagemController(JanelaPassagem janela) {
        this.telaAtual = TABELA;
        this.servicoV = new VooServico();
        this.servicoP = new PassagemServico();
        this.servicoC = new ClienteServico();
        this.janela = janela;
    }
    
    public void inserirPassagem() {
        FormularioPassagem painelForm = this.janela.getPainelFormulario();

        painelForm.limparCampos();

        painelForm.getjLabelID().setVisible(false);
        painelForm.getjTextFieldID().setVisible(false);
        painelForm.getjBCadastrar().setVisible(true);
        painelForm.getjBCadastrar().setText("Cadastrar");
        painelForm.getjTextHoraAtual().setVisible(false);
        painelForm.getjLabelHoraAtual().setVisible(false);
        painelForm.getjLabelObservacao().setVisible(false);
        
        painelForm.habilitaEdicaoFormPassagem(true);

        telaAtual = FORMCADASTRO;
        this.janela.mostrarPainel(JanelaPassagem.PAINELFORM);
    }
    
    public void editarPassagem() {
        TabelaPassagem painelTabela = this.janela.getPainelTabela();
        FormularioPassagem painelForm = this.janela.getPainelFormulario();
        PassagemTableModel tableModel = (PassagemTableModel) painelTabela.getTabela().getModel();
        
        linhaSelecionada = painelTabela.getTabela().getSelectedRow();
        if(linhaSelecionada < 0)
        {
            JOptionPane.showMessageDialog(janela, "Não há nenhum elemento selecionado na tabela");                    
            return;
        }
        Passagem passagem = tableModel.getPassagem(linhaSelecionada);
        painelForm.carregaDados(passagem.getCodigo(), passagem.getCliente().getRG(), passagem.getVoo(), passagem.getHoraVenda());

        painelForm.getjLabelMensagem().setText("Atualize os Dados do Voo");
        painelForm.getjLabelID().setVisible(true);
        painelForm.getjTextFieldID().setVisible(true);
        painelForm.getjBCadastrar().setVisible(true);
        painelForm.getjBCadastrar().setText("Editar");
        painelForm.getjTextHoraAtual().setVisible(true);
        painelForm.getjLabelHoraAtual().setVisible(true);
        painelForm.getjLabelObservacao().setVisible(true);
        painelForm.habilitaEdicaoFormPassagem(true);

        telaAtual = FORMEDICAO;
        this.janela.getPainelFormulario().carregaText();
        this.janela.mostrarPainel(JanelaPassagem.PAINELFORM);
    }

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
