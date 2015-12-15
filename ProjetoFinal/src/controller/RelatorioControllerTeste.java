package controller;
import comboBoxModel.VooComboBoxModel;
import view.JanelaRelatorioTeste;
import servico.VooServico;
import servico.PassagemServico;
import servico.ClienteServico;
import controller.PassagemController;
import tableModels.PassagemTableModel;
import viewTabelas.TabelaPassagem;
/**
 *
 * @author Thiago
 */
public class RelatorioControllerTeste {
    private final static int PAINELCLIENTE = 1;
    private final static int PAINELORIGEM = 2;
    private final static int PAINELDESTINO = 3;
    private final static int PAINELVOO = 4;
    private final static int PAINELTEMPO = 5;
    
    private int telaAtual = 0;
    private JanelaRelatorioTeste janela;
    
    private PassagemServico servicoP;
    private VooServico servicoV;
    private ClienteServico servicoC;
    
    public RelatorioControllerTeste(){
        telaAtual = PAINELCLIENTE;
        servicoV = new VooServico();
        servicoP = new PassagemServico();
        servicoC = new ClienteServico();
    }
    
    public RelatorioControllerTeste(JanelaRelatorioTeste janela){
        telaAtual = PAINELCLIENTE;
        servicoV = new VooServico();
        servicoP = new PassagemServico();
        servicoC = new ClienteServico();
        this.janela = janela;
    }
    
//    public void atualizaTabela() {
//        TabelaPassagem painelTabela = this.janela.getPainelTabela();
//        PassagemTableModel modelo = (PassagemTableModel) painelTabela.getTabela().getModel();
//
//        //PacienteDao dao = new PacienteDaoBd();
//        modelo.setPassagens(servicoP.listarPassagem());
//
//        painelTabela.getTabela().updateUI();
//    }
    
//    public void atualizaComboBox() {
//        FormularioPassagem painelFormulario = this.janela.getPainelFormulario();
//        VooComboBoxModel modeloVoo = (VooComboBoxModel) painelFormulario.getjComboBoxVoo().getModel();
//                
//        modeloVoo.setVoos(servicoV.listarVoo());        
//        painelFormulario.getjComboBoxVoo().updateUI();        
//    }
    
    public void setJanela(JanelaRelatorioTeste janela) {
        this.janela = janela;
    }
}
