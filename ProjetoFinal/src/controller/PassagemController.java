package controller;

import tableModels.PassagemTableModel;
//import viewFomularios.FormularioPassagem;
import viewTabelas.TabelaPassagem;
import servico.PassagemServico;
//import view.JanelaPassagem;
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
    
    //private JanelaPassagem janela;
    
    private PassagemServico servicoP;
    
}
