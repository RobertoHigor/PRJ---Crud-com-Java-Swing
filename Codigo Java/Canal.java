public class Canal {
	private byte[] logo;
	private String nome;
	private String grupo;
	private String url;
	private int parentCode;
	private String caminho;
	
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] bs) {
		this.logo = bs;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	public int getParentCode() {
		return parentCode;
	}
	public void setParentCode(int parentCode) {
		this.parentCode = parentCode;
	}
	
	
}
