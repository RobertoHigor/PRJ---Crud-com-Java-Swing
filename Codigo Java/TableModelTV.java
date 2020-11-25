import javax.swing.table.DefaultTableModel;


public class TableModelTV extends DefaultTableModel {

	private Canal[] vet=null;
	
	public void atualizar(TV t){
		vet = new Canal[t.getQtd()];
		
		int i = 0;
		for (Canal can : t){
			vet[i] = can;
			i++;
		}
	}
	
	//Pegar a quantidade de linhas
	public int getRowCount(){
		return vet!=null?vet.length:0;
	}
	
	//Retornar a quantidade de colunas 4
	public int getColumnCount(){
		return 4;
	}
	
	//Pegar o nome das colunas pelo numero
	public String getColumnName(int col){
		switch(col){
		case 0: return "Caminho";
		case 1: return "Nome";
		case 2: return "Grupo";
		case 3: return "URL";		
		default: return "Erro no case de coluna";
		}
	}
	
	public Object getValueAt(int row, int col){
		if (vet == null)
			return "";
		else{
			Canal c = vet[row];
			switch(col){
			case 0: return c.getCaminho();					
			case 1: return c.getNome();
			case 2: return c.getGrupo();
			case 3: return c.getUrl();
			default: return "Erro no getValueAt";
			}
		}
	}
	
	//Retornar o canal na posição do vetor especificada
	public Canal getCanal(int row){
		return vet[row];
	}
	
	//Não permitir que o usuário consiga editar o texto das colunas
	@Override
    public boolean isCellEditable(int row, int column) {      
       return false;
    }
}
