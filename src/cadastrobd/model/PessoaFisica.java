package cadastrobd.model;

public class PessoaFisica extends Pessoa {
    
    private String cpf;
    
    public PessoaFisica(Integer id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }
    
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    public String getCpf(){
        return cpf;
    }
    
    @Override
    public void exibir(){
        System.out.println("Id: " + this.getId() +
                "\nNome: " + this.getNome() +
                "\nCpf: " + this.getCpf() +
                "\nLogradouro: " + this.getLogradouro() + 
                "\nCidade: " + this.getCidade() + 
                "\nEstado: " + this.getEstado() +
                "\nTelefone: " + this.getTelefone() +
                "\nEmail: " + this.getEmail() +
                "\n===========================");
    }
}
