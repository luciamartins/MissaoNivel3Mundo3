package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import java.sql.SQLException;

public class CadastroBDTeste {
    private final PessoaFisicaDAO pessoafisicaDAO;
    private final PessoaJuridicaDAO pessoajuridicaDAO;
    
    public CadastroBDTeste() {
        pessoafisicaDAO = new PessoaFisicaDAO();
        pessoajuridicaDAO = new PessoaJuridicaDAO();
    }
    
    private void run(){
        PessoaFisica pf = new PessoaFisica(null, "Lucia", "There", "Loob", "RJ", "333355556", "Lucia@gmail.com", "35353535961");
        try{
            pessoafisicaDAO.incluir(pf);
            System.out.println("============================");
            System.out.println("Pessoa Fisica id: " + pf.getId() + " adicionada.");
            System.out.println("============================");
            pf.exibir();
            System.out.println("============================");
            pf.setCidade("Uberlandia");
            pf.setLogradouro("Rua Veneza");
            pessoafisicaDAO.alterar(pf);
            System.out.println("Pessoa Fisica id: " + pf.getId() + " dados alterados.");
            System.out.println("============================");
            pf.exibir();
            System.out.println("============================");
            System.out.println("Exibindo todas Pessoas Fisicas.");
            System.out.println("============================");
            pessoafisicaDAO.getPessoas().forEach(pessoaF -> pessoaF.exibir());
            System.out.println("============================");
            pessoafisicaDAO.excluir(pf.getId());
            System.out.println("Pessoa Fisica id: " + pf.getId() + " excluida.");
        }catch(Exception e){
            System.err.println("F: " + e);
        }
        PessoaJuridica pj = new PessoaJuridica(null, "Jose", "Priet", "Itabira", "RJ", "38365542", "Jose@gmail.com", "36235965821256");
        try{
            pessoajuridicaDAO.incluir(pj);
            System.out.println("============================");
            System.out.println("Pessoa Juridica id: " + pj.getId() + " adicionada.");
            System.out.println("============================");
            pj.exibir();
            System.out.println("============================");
            pj.setCidade("Ouro Preto");
            pj.setLogradouro("Rua Direita");
            pessoajuridicaDAO.alterar(pj);
            System.out.println("Pessoa Juridica id: " + pj.getId() + " Dados alterados.");
            System.out.println("============================");
            pj.exibir();
            System.out.println("============================");
            pessoajuridicaDAO.getPessoas().forEach(pessoaJ -> pessoaJ.exibir());
            System.out.println("============================");
            pessoajuridicaDAO.excluir(pj.getId());
            System.out.println("Pessoa Juridica id: " + pj.getId() + " excluida.");
        }catch(Exception e){
            System.err.println("J: " + e);
        }
    }
    
    public static void main(String[] args) throws SQLException, Exception{
        new CadastroBDTeste().run();
    }

}
