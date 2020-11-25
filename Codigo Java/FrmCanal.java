import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class FrmCanal extends JDialog implements ActionListener{
	private JButton btnOk;
	private JButton btnCancelar;
	private JTextField tbxCaminho;
	private JTextField tbxGrupo;
	private JTextField tbxNome;
	private JTextField tbxUrl;
	private boolean confirmou;
	
	//Criar botões e adicionar no painel passado
	private JButton criarBotao(JPanel pnl, String nome){
		JButton btn = new JButton(nome);
		btn.setIcon(new ImageIcon(nome+".jpg"));
		btn.setPreferredSize(new Dimension(90, 20));
		btn.addActionListener(this);
		pnl.add(btn);
		return btn;
	}
	
	//Criar uma caixa de texto e adicionar no painel passado
	private JTextField criarCaixaTexto(JPanel pnl, String nome, int largura){
		JTextField tbx = new JTextField(largura);
		JLabel lbl = new JLabel(nome);
		pnl.add(lbl);
		pnl.add(tbx);
		return tbx;
	}
	
	//Converte o arquivo de foto para um array de bytes
	public byte[] fotoParaByte(String caminho){
		ByteArrayOutputStream bos = null;
		try{
			File f = new File(caminho);
			FileInputStream fis = new FileInputStream(f);
			byte[] buffer = new byte[1024];
			bos = new ByteArrayOutputStream();
			
			for (int len; (len = fis.read(buffer)) != -1;){
				bos.write(buffer, 0, len);
			}		
			fis.close();			
		} catch (FileNotFoundException e){
			System.err.println(e.getMessage());
		} catch (IOException e2) {
			System.err.println(e2.getMessage());
		}
		
		return bos != null ? bos.toByteArray() : null;
	}
	
	//Construtor
	private FrmCanal(){
		setTitle("Canal");
		setSize(800, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//Mudar icone da janela
		Toolkit kit = Toolkit.getDefaultToolkit();  
        Image img = kit.getImage("tv.png"); 
		setIconImage(img);
		
		setModal(true);
		
		JPanel pnlBotoes, pnlDados;
		pnlBotoes = new JPanel();
		pnlDados = new JPanel();
		
		btnOk = criarBotao(pnlBotoes, "Ok");
		btnCancelar = criarBotao(pnlBotoes, "Cancelar");
		
		tbxCaminho = criarCaixaTexto(pnlDados, "Logo", 5);		
		tbxNome = criarCaixaTexto(pnlDados, "Nome", 20);
		tbxGrupo = criarCaixaTexto(pnlDados, "Grupo", 10);
		tbxUrl = criarCaixaTexto(pnlDados, "URL", 20);
	
		
		add(pnlBotoes, BorderLayout.SOUTH);
		add(pnlDados, BorderLayout.CENTER);		
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == btnCancelar)
			confirmou = false;
		else
			confirmou = true;
		setVisible(false);
	}
	
	//Transferir os dados armazenados no objeto para a tela do formulário
	private void objetoParaTela(Canal c){
		tbxCaminho.setText(c.getCaminho());
		tbxGrupo.setText(c.getGrupo());
		tbxNome.setText(c.getNome());
		tbxUrl.setText(c.getUrl());
	}
	
	//Transferir os dados dos formulários para o objeto do canal
	private void telaParaObjeto(Canal c){
		c.setCaminho(tbxCaminho.getText());
		c.setLogo(fotoParaByte(c.getCaminho()));
		c.setGrupo(tbxGrupo.getText());
		c.setNome(tbxNome.getText());
		c.setUrl(tbxUrl.getText());		
	}
	
	private static FrmCanal _Instancia = null;
	
	public static boolean executar(Canal c){
		if (_Instancia==null){
			_Instancia = new FrmCanal();
			
		}
		_Instancia.confirmou = false;
		_Instancia.objetoParaTela(c);
		_Instancia.setVisible(true);
		if (_Instancia.confirmou){
			_Instancia.telaParaObjeto(c);
		}
		return _Instancia.confirmou;
	}
	
}
