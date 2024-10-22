package cadastrobd.model;

public class PessoaJuridica extends Pessoa {
    
    private String cnpj; 
    
    public PessoaJuridica(Integer id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj){
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }
    
    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }
    
    public String getCnpj(){
        return cnpj;
    }
    
    @Override
    public void exibir(){
        System.out.println("\nId: " + this.getId() +
                "\nNome: " + this.getNome() +
                "\nCpf: " + this.getCnpj() +
                "\nLogradouro: " + this.getLogradouro() + 
                "\nCidade: " + this.getCidade() + 
                "\nEstado: " + this.getEstado() +
                "\nTelefone: " + this.getTelefone() +
                "\nEmail: " + this.getEmail() +
                "\n===========================");
    }
}
