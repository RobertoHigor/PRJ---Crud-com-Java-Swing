import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class TelaInicial extends JDialog implements ActionListener{
	
	private JPanel pnlBotoes;
	private JButton btnInserir;
	private JButton btnAlterar;
	private JButton btnRemover;
	private JButton btnLogar;
	private JButton btnSobre;
	
	private JButton btnFiltrar;
	private JTextField tbxFiltro;
	
	//Variaveis de classes
	private GridTV grid;	
	private TV t = new TV();

	public TelaInicial(){
		//Titulo da janela e tamanho
		setTitle("Gerenciador de TV");
		setSize(900,400);		
		
		//Mudar icone da janela para o do arquivo tv.png
		Toolkit kit = Toolkit.getDefaultToolkit();  
        Image img = kit.getImage("tv.png"); 
		setIconImage(img);	
		
	
		//Travar quando abrir outra janela
		setModal(true);
		//Abrir no meio da tela
		setLocationRelativeTo(null);
		
		//Criar Bot�es
		pnlBotoes = new JPanel();
		add(pnlBotoes, BorderLayout.NORTH);
		
		//Inserir, Remover e Alterar
		btnInserir = criarBotao("Inserir");
		btnRemover = criarBotao("Remover");
		btnAlterar = criarBotao("Alterar");
		
		//Logar e sobre
		btnLogar = criarBotao("Logar");
		btnSobre = criarBotao("Sobre");
		
		//Filtro
		btnFiltrar = criarBotao("Filtro");
		tbxFiltro = new JTextField(20);
		tbxFiltro.addCaretListener(new CaretListener(){
        @Override
        public void caretUpdate(CaretEvent e) {
           t.filtrar(tbxFiltro.getText());
           grid.atualizar();
        }
		});
		
		pnlBotoes.add(tbxFiltro);
			      
	    //Criar Grid de dados
	    grid = new GridTV();
	    grid.setTV(t);
	    add(grid, BorderLayout.CENTER);
		    
		      
	}

	//M�todo para criar bot�o e associar ao painel de bot�es
	private JButton criarBotao(String nome){
		JButton btn = new JButton(nome);	
		
		//Utilizar um icone no bot�o. A imagem � o nome do bot�o com .jpg no final.
		btn.setIcon(new ImageIcon(nome+".jpg"));	
	    
		btn.setPreferredSize(new Dimension(90,20));
	    btn.addActionListener(this);
	    pnlBotoes.add(btn);
	    return btn;
	}
	
	
	//O que fazer quando clicar em um bot�o
	public void actionPerformed(ActionEvent e){
		//O bot�o de logar n�o foi implementado
		if (e.getSource() == btnLogar){
		
		//Abrir a tela de sobre
		}else if (e.getSource() == btnSobre){
			TlSobre sb = new TlSobre();
			sb.executar();
		//Abrir a tela de cadastro e inserir os dados no banco ap�s clicar em OK
		}else if (e.getSource() == btnInserir){
			Canal ficha = new Canal();
			if (FrmCanal.executar(ficha))
				t.inserir(ficha);
			grid.atualizar();
		//Abrir a tela de alterar com os dados da linha selecionada
		}else if (e.getSource() == btnAlterar){
			Canal ficha = grid.obterCanalSelecionado();
			if (ficha == null)
				JOptionPane.showMessageDialog(null, "Um canal deve estar selecionado para alterar");
			else {
				String nome = ficha.getNome();
				if(FrmCanal.executar(ficha))
					t.alterar(nome, ficha);
				grid.atualizar();
			}
		//Deletar o registro selecionado do banco
		}else if (e.getSource() == btnRemover){
			Canal c = grid.obterCanalSelecionado();
			if (c == null)
				JOptionPane.showMessageDialog(null, "Um canal deve estar selecionado para remover");
			else{
				t.remover(c.getNome());
			grid.atualizar();
			}
		//Utilizar o m�todo de filtrar da TV	
		}else if (e.getSource() == btnFiltrar)
	      {
	        t.filtrar(tbxFiltro.getText());
	        grid.atualizar();
	      }
	}
	
	public static void main(String[] args) {
		//Mostrar a tela de splash antes de abrir o programa
		TelaSplash splash = new TelaSplash(3000);
		splash.mostrar();        

		TelaInicial tela = new TelaInicial();
		tela.setVisible(true);

	}

}
