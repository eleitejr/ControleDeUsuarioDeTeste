/**
 *                  Universidade de Brasília
 *                  Instituto de Ciências Exatas
 *                  Departamento de Ciência da Computação
 * 
 *                  Engenharia de Software - turma B
 *                      
 *                  2º semestre de 2013
 *                  
 *                  Alunos:
 *                  - Felipe Camargos Costa - 10/0100341
 *                  - Gutemberg Guilherme de Araújo - 11/0120451
 *                  - Erasmo de Castro Leite Junior - 12/0139855
 * 
 *                  Descrição:
 *                  
 * 
 */

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import entidades.Ambiente;
import entidades.Plataforma;

/**
 * Se comunica com o banco de dados e faz a interface do objeto Plataforma com a tabela PLATAFORMA no banco de dados.
 * 
 */
public class DAOPlataforma {

    /**
     * Dado um id, recupera o objeto Plataforma correspondente. Se a id for igual a 0, retorna null.
     * 
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Plataforma recuperarPlataformaPorId(int id) throws SQLException, ClassNotFoundException {

        ResultSet resultado = null;
        Plataforma plataforma = null;

        if (id == 0) {
            return null;
        }

        ServicoConexao servicoConexao = new ServicoConexao();

        resultado = servicoConexao.executarQuery("SELECT * FROM PLATAFORMA WHERE id = " + id);

        if (resultado.next()) {
            String nome = resultado.getString("nome");

            plataforma = new Plataforma(id, nome);
        }

        servicoConexao.fecharConexaoBancoDeDados();

        return plataforma;
    }

    /**
     * Cadastra uma nova plataforma, cujo objeto é passado como parâmetro.
     * 
     * @param plataforma
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void cadastrarNovaPlataforma(Plataforma plataforma) throws SQLException, ClassNotFoundException {

        if (plataforma == null) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("INSERT INTO PLATAFORMA(nome) values('" + plataforma.getNome() + "')");

        servicoConexao.fecharConexaoBancoDeDados();
    }

    /**
     * Altera a plataforma. Os novos dados são passados como parâmetro.
     * 
     * @param plataforma
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void alterarPlataforma(Plataforma plataforma) throws SQLException, ClassNotFoundException {

        if (plataforma == null) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("UPDATE PLATAFORMA SET nome = '" + plataforma.getNome() + "' WHERE id = "
                + plataforma.getId());

        servicoConexao.fecharConexaoBancoDeDados();
    }

    public void excluirPlataforma(Plataforma plataforma) throws SQLException, ClassNotFoundException {

        ServicoConexao servicoConexao = new ServicoConexao();

        DAOAmbiente daoAmbiente = new DAOAmbiente();
        Set<Ambiente> ambientesDaPlataforma = daoAmbiente.recuperarAmbientesDaPlataforma(plataforma);

        for (Ambiente ambiente : ambientesDaPlataforma) {
            daoAmbiente.excluirAmbiente(ambiente);
        }

        servicoConexao.executarUpdate("DELETE FROM PLATAFORMA WHERE id = " + plataforma.getId());
        servicoConexao.fecharConexaoBancoDeDados();
    }

}
