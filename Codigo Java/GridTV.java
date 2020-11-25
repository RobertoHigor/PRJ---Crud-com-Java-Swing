import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;

public class GridTV extends JPanel {
	private JTable tbl;
	private TV tv;
	private TableModelTV tmtv;

//Construtor criando a tabela
	public GridTV(){	
		tmtv = new TableModelTV();
		setLayout(new BorderLayout());
		
		tbl = new JTable();
		tbl.setModel(tmtv);
		
		//Criando as colunas	
		tbl.setRowHeight(45);
		
		tbl.getColumnModel().getColumn(0).setPreferredWidth(100);
	    tbl.getColumnModel().getColumn(1).setPreferredWidth(25);	    
	    tbl.getColumnModel().getColumn(2).setPreferredWidth(50);
	    tbl.getColumnModel().getColumn(3).setPreferredWidth(200);
	    
	    //Ordenar	 
	    tbl.convertRowIndexToModel(0); 
	    tbl.setAutoCreateRowSorter(true);
	    
	   
	   
	  //Quando clicar em um canal, executar a função de abrir no navegador
	    tbl.addMouseListener(new MouseAdapter(){
	    	@Override
	    	public void mouseClicked(MouseEvent x){		
	    		//Executar caso tenha clicado 2 vezes
	    		if (x.getClickCount() == 2){
	    			abrirNavegador(obterURL());	    			
	    		}
	    	}
	    });

	    //ADicionar um scroll para a tabela
	    add(new JScrollPane(tbl), BorderLayout.CENTER);
	}
	
	public void atualizar(){
		if (tv!=null)
			tmtv.atualizar(tv);
		//Avisa que atualizou um valor
		tmtv.fireTableDataChanged();
	}
	
	//Abrir link no navegador
	public static void abrirNavegador(String url) {
	    try {
	        if (url != null){
	        	//Pegar o browser do usuário e abrir a url digitada
	        	Desktop.getDesktop().browse(new URL(url).toURI());
	        }else{
	        	JOptionPane.showMessageDialog(null, "Você deve selecionar um canal");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void setTV(TV t){
		tv = t;
		atualizar();
	}
	
	//Obter a Url do Canal selecionado
	public String obterURL(){
		if (tv == null) 
			return null;
		else if (tbl.getSelectedRows().length!=1)
			return null;
		else {
			Canal c = new Canal();
			c = tmtv.getCanal(tbl.getSelectedRows()[0]);
			return c.getUrl();
		}
	}
	
	//Obter o canal selecionado atualmente
	public Canal obterCanalSelecionado(){
		if (tv == null) 
			return null;
		else if (tbl.getSelectedRows().length!=1)
			return null;
		else 				
			return tmtv.getCanal(tbl.getSelectedRows()[0]);				
	}
}
