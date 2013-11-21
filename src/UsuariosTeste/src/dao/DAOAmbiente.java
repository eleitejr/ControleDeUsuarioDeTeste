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
import java.util.HashSet;
import java.util.Set;

import entidades.Ambiente;
import entidades.Grupo;
import entidades.Plataforma;

/**
 * Se comunica com o banco de dados e faz a interface do objeto Ambiente com a tabela AMBIENTE no banco de dados.
 * 
 */
public class DAOAmbiente {

    /**
     * Dado um id, recupera o objeto Ambiente correspondente. Se a id for igual a 0, retorna null.
     * 
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Ambiente recuperarAmbientePorId(int id) throws SQLException, ClassNotFoundException {

        if (id == 0) {
            return null;
        }

        Ambiente ambiente = null;

        ServicoConexao servicoConexao = new ServicoConexao();
        ResultSet resultado = servicoConexao.executarQuery("SELECT * FROM AMBIENTE WHERE id = " + id);

        if (resultado.next()) {
            String nome = resultado.getString("nome");

            DAOPlataforma daoPlataforma = new DAOPlataforma();
            Plataforma plataforma = daoPlataforma.recuperarPlataformaPorId(resultado.getInt("idPlataforma"));

            ambiente = new Ambiente(id, nome, plataforma);
        }

        servicoConexao.fecharConexaoBancoDeDados();
        return ambiente;
    }

    /**
     * Cadastra um novo ambiente, cujo objeto é passado como parâmetro.
     * 
     * @param ambiente
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void cadastrarNovoAmbiente(Ambiente ambiente) throws SQLException, ClassNotFoundException {

        if (ambiente == null) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("INSERT INTO AMBIENTE(nome, idPlataforma) values('" + ambiente.getNome() + "', "
                + ambiente.getPlataforma().getId() + ")");

        servicoConexao.fecharConexaoBancoDeDados();
    }

    /**
     * Altera o ambiente. Os novos dados são passados como parâmetro.
     * 
     * @param ambiente
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void alterarAmbiente(Ambiente ambiente) throws SQLException, ClassNotFoundException {

        if (ambiente == null) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("UPDATE AMBIENTE SET nome = '" + ambiente.getNome() + "', idAmbiente = "
                + ambiente.getPlataforma().getId() + " WHERE id = " + ambiente.getId());

        servicoConexao.fecharConexaoBancoDeDados();
    }

    public void excluirAmbiente(Ambiente ambiente) throws SQLException, ClassNotFoundException {

        ServicoConexao servicoConexao = new ServicoConexao();

        DAOGrupo daoGrupo = new DAOGrupo();
        Set<Grupo> gruposDoAmbiente = daoGrupo.recuperarGruposDoAmbiente(ambiente);
        for (Grupo grupo : gruposDoAmbiente) {
            daoGrupo.excluirGrupo(grupo);
        }

        servicoConexao.executarUpdate("DELETE FROM AMBIENTE WHERE id = " + ambiente.getId());
        servicoConexao.fecharConexaoBancoDeDados();
    }

    public Set<Ambiente> recuperarAmbientesDaPlataforma(Plataforma plataforma) throws ClassNotFoundException, SQLException {

        Set<Ambiente> ambientesDaPlataforma = new HashSet<Ambiente>();

        if (plataforma == null) {
            return null;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        ResultSet resultado = servicoConexao.executarQuery("SELECT * FROM AMBIENTE WHERE idPlataforma = " + plataforma.getId());

        while (resultado.next()) {
            Ambiente ambiente = new Ambiente(resultado.getInt("id"), resultado.getString("nome"), plataforma);
            ambientesDaPlataforma.add(ambiente);
        }

        servicoConexao.fecharConexaoBancoDeDados();
        return ambientesDaPlataforma;
    }

}
