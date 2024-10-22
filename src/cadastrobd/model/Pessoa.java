package cadastrobd.model;

public class Pessoa {
    private Integer id;
    private String nome;
    private String logradouro;
    private String cidade;
    private String estado;
    private String telefone;
    private String email;
    
    public Pessoa(){}
    
    public Pessoa(Integer id, String nome, String logradouro, String cidade, String estado, String telefone, String email){
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public Integer getId(){
        return id;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setLogradouro(String logradouro){
        this.logradouro = logradouro;
    }
    
    public String getLogradouro(){
        return logradouro;
    }
    
    public void setCidade(String cidade){
        this.cidade = cidade;
    }
    
    public String getCidade(){
        return cidade;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
    public String getTelefone(){
        return telefone;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void exibir(){
        System.out.println("\nId: " + this.getId() +
                "\nNome: "+ this.getNome() + 
                "\nLogradouro: " + this.getLogradouro() + 
                "\nCidade: " + this.getCidade() + 
                "\nEstado: " + this.getEstado() +
                "\nTelefone: " + this.getTelefone() +
                "\nEmail: " + this.getEmail());
    }
    
}
