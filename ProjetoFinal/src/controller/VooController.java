package controller;

//import javax.swing.JOptionPane;
//import model.Aviao;
//import model.Voo;
import servico.AviaoServico;
import comboBoxModel.AviaoComboBoxModel;
import tableModels.VooTableModel;
import viewFomularios.FormularioVoo;
import viewTabelas.TabelaVoo;
import servico.VooServico;
import view.JanelaVoo;
import comboBoxModel.PonteComboBoxModel;

/**
 *
 * @author Thiago
 */
public class VooController {

    private final static int TABELA = 0;
    private final static int FORMCADASTRO = 1;
    private final static int FORMEDICAO = 2;
    private final static int FORMVISUALIZACAO = 3;
    private final static int TABELA_AVIAO = 4;
    private final static int TABELA_ROTA = 5;

    private int telaAtual = 0;
    private int linhaSelecionada = -1;

    private JanelaVoo janela;

    private VooServico servicoV;
    private AviaoServico servicoA;

    public VooController() {
        telaAtual = TABELA;
        servicoV = new VooServico();
        servicoA = new AviaoServico();
    }

    public void setJanela(JanelaVoo janela) {
        this.janela = janela;
    }

    public void inserirVoo() {
        FormularioVoo painelForm = this.janela.getPainelFormulario();

        painelForm.limparCampos();

        //painelForm.getLabelPainelFormulario().setText("Cadastrar Paciente");
        //painelForm.setTitle("Cadastrar Avião");        
//        painelForm.getjTID().setEditable(false);
//        painelForm.getjTCampoAviao().setEditable(false);
//        painelForm.getjTPonte().setEditable(false);
//        painelForm.getjComboBoxAviao().setModel(new AviaoComboBoxModel(servicoA.listarAviao()));
        painelForm.getjTID().setVisible(false);
        painelForm.getjBCadastrar().setVisible(true);
        painelForm.getjBCadastrar().setText("Cadastrar");
        painelForm.habilitaEdicaoFormVoo(true);

        telaAtual = FORMCADASTRO;
        this.janela.mostrarPainel(JanelaVoo.PAINELFORM);
    }

    public void voltarPrincipal() {
        telaAtual = TABELA;
        this.atualizaTabela();
        this.atualizaComboBox();
        this.janela.mostrarPainel(JanelaVoo.PAINELTABELA);
    }

    public void atualizaTabela() {
        TabelaVoo painelTabela = this.janela.getPainelTabela();
        VooTableModel modelo = (VooTableModel) painelTabela.getTabela().getModel();

        //PacienteDao dao = new PacienteDaoBd();
        modelo.setVoos(servicoV.listarVoo());

        painelTabela.getTabela().updateUI();
    }
    
    public void atualizaComboBox() {
        FormularioVoo painelFormulario = this.janela.getPainelFormulario();
        AviaoComboBoxModel modeloAviao = (AviaoComboBoxModel) painelFormulario.getjComboBoxAviao().getModel();
        PonteComboBoxModel modeloPonte = (PonteComboBoxModel) painelFormulario.getjComboBoxPonte().getModel();
        
        modeloAviao.setAvioes(servicoA.listarAviao());
        modeloPonte.setPonte(servicoV.listarPontes());
        
        painelFormulario.getjComboBoxAviao().updateUI();
        painelFormulario.getjComboBoxPonte().updateUI();
    }    
}
