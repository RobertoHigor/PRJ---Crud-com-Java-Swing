import java.util.*;

public class TV implements Iterable<Canal>{
	
	private SQLiteBD bd;
	private boolean filtrado;
	private List<Canal> filtrados;
	
	//Construtor
	public TV(){
		bd = new SQLiteBD();
		filtrado = false;
		filtrados = new LinkedList<Canal>();
	}
	
	//Inserir na coleção
	public void inserir(Canal c){
		bd.inserir(c);
	}
	
	//Remover da coleção pelo nome
	public void remover(String nome){		
			bd.remover(nome);
	}
	
	public void alterar(String nome, Canal c){
		bd.alterar(nome, c);
	}
	
	//Retornar o tamanho da coleção
	public int getQtd(){
		return filtrado?filtrados.size():bd.obterQtd();
	}
	
	public Canal getCanal(String nome){
		return bd.obterCanal(nome);
	}
	
	//Retornar o iterator de canal
	public Iterator<Canal> iterator(){
		return filtrado?filtrados.iterator():bd.obterCanais().iterator();
	}
	
	//Filtro
	private boolean filtrado(Canal c, String filtro){
		return c.getCaminho().toLowerCase().contains(filtro) ||
				(""+c.getGrupo()).toLowerCase().contains(filtro) ||
				(""+c.getNome()).toLowerCase().contains(filtro) ||
				(""+c.getUrl()).contains(filtro);
	}
	
	//Filtrar
	public void filtrar(String filtro){
		if (filtro == null || filtro.trim().length()==0)
			filtrado = false;
		else{
			filtrado = true;
			filtrados.clear();
			filtro = filtro.trim().toLowerCase();
			
			for (Canal c : bd.obterCanais()){
				if (filtrado(c, filtro))
						filtrados.add(c);
			}
		}
	}
}
