package cadastrobd.model;

import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PessoaJuridicaDAO {
    private final ConectorBD connector;
    
    public PessoaJuridicaDAO(){
        connector = new ConectorBD();
    }
    
    public PessoaJuridica getPessoa(int id) throws SQLException, Exception{
        String sql = "SELECT pj.Pessoa_idPessoa, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pj.cnpj "
            + "FROM PessoaJuridica pj "
            + "INNER JOIN Pessoa p ON pj.Pessoa_idPessoa = p.idPessoa "
            + "WHERE pj.Pessoa_idPessoa = ?";
        PessoaJuridica pessoa = null;
        Connection con = null; 
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = connector.getConnection(); 
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                pessoa = new PessoaJuridica(
                rs.getInt("Pessoa_idPessoa"), 
                rs.getString("nome"),
                rs.getString("logradouro"),
                rs.getString("cidade"),
                rs.getString("estado"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("cnpj")                    
                );
                return pessoa;
            }else{
                return null;
            }
        }finally{
            connector.close(rs);
            connector.close(stmt);
            connector.close(con);
        } 
    }
    
    public List<PessoaJuridica> getPessoas() throws SQLException, Exception{
        String sql = "SELECT pj.Pessoa_idPessoa, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pj.cnpj "
            + "FROM Pessoa p "
            + "INNER JOIN PessoaJuridica pj ON p.idPessoa = pj.Pessoa_idPessoa;";
        List<PessoaJuridica> lista = new ArrayList<>();
        Connection con = null; 
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = connector.getConnection(); 
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                lista.add(new PessoaJuridica(
                rs.getInt("Pessoa_idPessoa"), 
                rs.getString("nome"),
                rs.getString("logradouro"),
                rs.getString("cidade"),
                rs.getString("estado"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("cnpj")                    
                ));
            }
            return lista;
        }finally{
            connector.close(rs);
            connector.close(stmt);
            connector.close(con);
        }
    }
    
    public void incluir(PessoaJuridica pessoa) throws SQLException, Exception{
        String sqlPessoa = "INSERT INTO Pessoa(idPessoa,nome,logradouro,cidade,estado,telefone,email) VALUES(?,?,?,?,?,?,?);";
        String sqlPessoaJuridica = "INSERT INTO PessoaJuridica(Pessoa_idPessoa,cnpj) VALUES(?,?);";
        Connection con = null; 
        PreparedStatement stmt = null;
        PreparedStatement stmtPj = null;
        try{
            con = connector.getConnection(); 
            con.setAutoCommit(false);
            
            SequenceManager sequenceManager = new SequenceManager();
            int idNovaPessoa = sequenceManager.getValue("orderPessoa");
            
            stmt = con.prepareStatement(sqlPessoa);
            stmt.setInt(1, idNovaPessoa);
            stmt.setString(2, pessoa.getNome());
            stmt.setString(3, pessoa.getLogradouro());
            stmt.setString(4, pessoa.getCidade());
            stmt.setString(5, pessoa.getEstado());
            stmt.setString(6, pessoa.getTelefone());
            stmt.setString(7, pessoa.getEmail());
            stmt.executeUpdate();
            
            pessoa.setId(idNovaPessoa);

            stmtPj = con.prepareStatement(sqlPessoaJuridica);
            stmtPj.setInt(1, idNovaPessoa);
            stmtPj.setString(2, pessoa.getCnpj());
            stmtPj.executeUpdate();
            
            con.commit();
        }catch(Exception e){
            System.err.println("Adj: " + e);
        }
            finally{
            connector.close(stmtPj);
            connector.close(stmt);
            connector.close(con);
        }
    }
    
    public void alterar(PessoaJuridica pessoa) throws SQLException, Exception{
        String sqlPessoa = "UPDATE PESSOA SET nome=?,logradouro =?,cidade=?,estado=?,telefone=?,email=? WHERE idPessoa=?;";
        String sqlPessoaJuridica = "UPDATE PESSOAJURIDICA SET cnpj=? WHERE Pessoa_idPessoa=?;";
        Connection con = null; 
        PreparedStatement stmt = null;
        PreparedStatement stmtPj = null;
        try{
            con = connector.getConnection();
            stmt = con.prepareStatement(sqlPessoa);
            stmtPj = con.prepareStatement(sqlPessoaJuridica);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getLogradouro());
            stmt.setString(3, pessoa.getCidade());
            stmt.setString(4, pessoa.getEstado());
            stmt.setString(5, pessoa.getTelefone());
            stmt.setString(6, pessoa.getEmail());
            stmt.setInt(7, pessoa.getId());
            stmt.executeUpdate();
            stmtPj.setString(1, pessoa.getCnpj());
            stmtPj.setInt(2, pessoa.getId());
            stmtPj.executeUpdate();
        }catch(Exception e){
            System.err.println("Alj: " + e);
        }finally{
            connector.close(stmtPj);
            connector.close(stmt);
            connector.close(con);
        }
    }
    
    public void excluir(int chave) throws SQLException, Exception{
        String sqlPessoa = "DELETE FROM PESSOA WHERE idPessoa=?";
        String sqlPessoaJuridica = "DELETE FROM PESSOAJURIDICA WHERE Pessoa_idPessoa=?";
        Connection con = null; 
        PreparedStatement stmt = null;
        PreparedStatement stmtPj = null;
        try{
            con = connector.getConnection();
            stmtPj = con.prepareStatement(sqlPessoaJuridica);
            stmt = con.prepareStatement(sqlPessoa);
            stmtPj.setInt(1, chave);
            stmtPj.executeUpdate();
            stmt.setInt(1, chave);
            stmt.executeUpdate();
        }catch(Exception e){
            System.err.println("Exj: " + e);
        }finally{
            connector.close(stmt);
            connector.close(stmtPj);
            connector.close(con);
        }
    }
}
