import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class TlSobre extends JDialog implements ActionListener{

	private JPanel pnlBotoes;
	private JPanel pnlFoto;
	private JPanel pnlAutores;
	private JPanel pnlAplicacao;
	private JPanel pnlDireita;
	
	private JTextArea txtAutor;
	private JTextArea txtAplicacao;
	
	private JButton btnOk;
	
	private boolean sair = false;
	
	public TlSobre(){
		//Atributos da tela
		setTitle("Sobre");
		setSize(800, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setModal(true);
		
		//Mudar icone da janela
			Toolkit kit = Toolkit.getDefaultToolkit();  
	        Image img = kit.getImage("tv.png"); 
			setIconImage(img);
				
		//Painel de Autores
		pnlAutores = new JPanel();
		pnlAplicacao = new JPanel();
		//pnlAutores.setPreferredSize(new Dimension(100, 10));
		//pnlAplicacao.setPreferredSize(new Dimension(100, 10));
		
		txtAutor = criarCaixaTexto(pnlAutores, "Autores", 50);		
		txtAutor.setText("Roberto Higor Matos dos Anjos\nEmail: roberto.higor@outlook.com  "
				+ "\n\nGabriel Oliveira de Carvalho\nEmail: gabriel_gal22@hotmail.com");
		
		txtAplicacao = criarCaixaTexto(pnlAplicacao, "O que é a aplicação", 50);
		txtAplicacao.setText("Um sistema que armazena canais\nde TV via internet e permite"
				+ "com\nque seus usuários consigam\nassistir-los.\nO projeto foi criado no IST"
				+ "servindo \ncomo trabalho final da disciplina PRJ");
		
		//Criando um painel na direita da tela com os autores e a descrição do sistema
		pnlDireita = new JPanel();
		pnlDireita.setPreferredSize(new Dimension(300, 40));
		pnlDireita.add(pnlAutores);
		pnlDireita.add(pnlAplicacao);
		
		//Painel de foto
		pnlFoto = new JPanel();
		BufferedImage myPicture = null;
		
		//Tentar a foto
		try {
			myPicture = ImageIO.read(new File("faeterj.jpg"));
		} catch (IOException e) {			
			e.printStackTrace();
		}	
		//Adicionar a foto lida na label
		JLabel lblFoto = new JLabel(new ImageIcon(myPicture));
		
		pnlFoto.setBorder(BorderFactory.createTitledBorder("Foto"));
		pnlFoto.add(lblFoto);
		
		//painel de botões e criar botão ok
		pnlBotoes = new JPanel();
		btnOk = criarBotao("Ok");		
		
		add(pnlBotoes, BorderLayout.SOUTH);
		add(pnlFoto, BorderLayout.WEST);
		add(pnlDireita, BorderLayout.EAST);
	}
	
	//Método para decidir o que fazer quando clicar no botão
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == btnOk)		
			setVisible(false);
	}
	
	//Método para criar caixa de texto
	private JTextArea criarCaixaTexto(JPanel pnl, String nome, int largura){
		int foo = largura;
		JTextArea tbx = new JTextArea();
		pnl.setBorder(BorderFactory.createTitledBorder(nome)); 
		pnl.add(tbx);
		return tbx;
	}
	
	//Método para a criação de botões
	private JButton criarBotao(String nome){
		JButton btn = new JButton(nome);
		btn.setIcon(new ImageIcon(nome+".jpg"));
		btn.setPreferredSize(new Dimension(90,20));
	    btn.addActionListener(this);
	    pnlBotoes.add(btn);
	    return btn;
	}
	
	public boolean executar(){
		setVisible(true);
		sair = true;
		return sair;
	}

}
