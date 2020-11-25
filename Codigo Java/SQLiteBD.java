import java.io.File;
import java.sql.*;
import java.util.*;


public class SQLiteBD {

	private static String _STR_CONEXAO_ = "jdbc:sqlite:Canais.db";
	
	public SQLiteBD(){
		try {		
		Class.forName("org.sqlite.JDBC");
		
		File f = new File("Canais.db");
		if (!f.exists())
			criarBanco();		
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	//Inserir dados no banco de dados
	public void inserir(Canal c){
		try{
			Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
			
			PreparedStatement stmt = conn.prepareStatement("insert into Canal(logo, grupo, nome, url, Caminho) values (?, ?, ?, ?, ?)");
			//O logo será passado em bytes
			stmt.setBytes(1, c.getLogo());
			stmt.setString(2, c.getGrupo());
			stmt.setString(3, c.getNome());
			stmt.setString(4, c.getUrl());
			stmt.setString(5,  c.getCaminho());
			
			stmt.executeUpdate();
			
			stmt.close();
			conn.close();
		} catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	//Alterar
	public void alterar(String nome, Canal c){
		try{
			Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
			PreparedStatement stmt = conn.prepareStatement("update Canal set "
					+ "Logo = ?, Grupo = ?, nome = ?, url = ?, caminho = ? where nome = ?");
			stmt.setBytes(1,  c.getLogo());
			stmt.setString(2,  c.getGrupo());
			stmt.setString(3,  c.getNome());
			stmt.setString(4,  c.getUrl());
			stmt.setString(5, c.getCaminho());		
			
			
			stmt.setString(6, nome);
			
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();;
			System.exit(0);
		}
	}
	//Remover
	public void remover(String nome){
		try{
			Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
			PreparedStatement stmt = conn.prepareStatement("delete from Canal where nome = ?");
			
			stmt.setString(1,  nome);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	//Obter um canal utilizando a chave primária nome
	public Canal obterCanal(String nome){
		Canal c = null;
		try {
			Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
			if (conn != null){
				PreparedStatement stmt = conn.prepareStatement("select logo, grupo, nome, url, Caminho FROM Canal where nome = ?");
				stmt.setString(1, nome);
				
				ResultSet r = stmt.executeQuery();
				if (r.next()){
					c = new Canal();
					c.setLogo(r.getBytes(1));
					c.setGrupo(r.getString(2));
					c.setNome(r.getString(3));
					c.setUrl(r.getString(4));
					c.setCaminho(r.getString(5));
				}
				stmt.close();
				conn.close();
			}
			return c;
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
			return null;
		}
	}	
	
	//Obter o COUNT de registros
	public int obterQtd(){
		int count = 0;
		try{
			Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
			if (conn != null){
			PreparedStatement stmt = conn.prepareStatement("select count(*) from Canal");
			ResultSet r = stmt.executeQuery();
			if (r.next())
					count = r.getInt(1);
			stmt.close();
			conn.close();
		}
			return count;
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
			return 0;
		}
		
	}
	
	//Obter todos os canais e retornar a LinkedList
	public Collection<Canal> obterCanais(){
		LinkedList<Canal> L = new LinkedList<Canal>();
		try{
			Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
			PreparedStatement stmt = conn.prepareStatement("select Logo, Grupo, Nome, URL, Caminho "
					+ "from Canal order by Nome");
			ResultSet r = stmt.executeQuery();
			
			while(r.next()){
				Canal c = new Canal();
				c.setLogo(r.getBytes(1));
				c.setGrupo(r.getString(2));
				c.setNome(r.getString(3));
				c.setUrl(r.getString(4));
				c.setCaminho(r.getString(5));
				L.add(c);
			}
			r.close();
			stmt.close();
			conn.close();
			return L;
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
			return L;
		}
	}
	
	//Criar o banco caso não exista
	public void criarBanco(){
		try{
			Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("create table Canal("
					+ "Logo BLOB NOT NULL,"
					+ "Grupo VARCHAR(40) NOT NULL,"
					+ "Nome VARCAHR(100) PRIMARY KEY,"
					+ "URL VARCHAR(200) NOT NULL,"
					+ "Caminho VARCHAR(100) NOT NULL)");
			stmt.close();
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
}
