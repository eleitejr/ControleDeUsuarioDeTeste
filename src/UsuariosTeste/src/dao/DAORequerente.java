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

import entidades.Coordenacao;
import entidades.Requerente;

/**
 * Se comunica com o banco de dados e faz a interface do objeto Requerente com a tabela REQUERENTE no banco de dados.
 * 
 */
public class DAORequerente {

    /**
     * Dado um id, recupera o objeto Requerente correspondente. Se a id for igual a 0, retorna null.
     * 
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Requerente recuperarRequerentePorId(int id) throws SQLException, ClassNotFoundException {

        if (id == 0) {
            return null;
        }

        Requerente requerente = null;

        ServicoConexao servicoConexao = new ServicoConexao();

        ResultSet resultado = servicoConexao.executarQuery("SELECT * FROM REQUERENTE WHERE id = " + id);

        if (resultado.next()) {
            String matricula = resultado.getString("matricula");
            String nome = resultado.getString("nome");
            String funcao = resultado.getString("funcao");

            requerente = new Requerente(id, matricula, nome, funcao);
        }

        servicoConexao.fecharConexaoBancoDeDados();
        return requerente;
    }

    /**
     * Cadastra um novo requerente, cujo objeto é passado como parâmetro.
     * 
     * @param requerente
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void cadastrarNovoRequerente(Requerente requerente) throws SQLException, ClassNotFoundException {

        if (requerente == null) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("INSERT INTO REQUERENTE (nome, idRequerente) values('" + requerente.getNome() + "', "
                + requerente.getId() + ")");

        servicoConexao.fecharConexaoBancoDeDados();
    }

    /**
     * Altera o requerente. Os novos dados são passados como parâmetro.
     * 
     * @param requerente
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void alterarRequerente(Requerente requerente) throws SQLException, ClassNotFoundException {

        if (requerente == null) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("UPDATE REQUERENTE SET matricula = '" + requerente.getMatricula() + "', nome = '"
                + requerente.getNome() + "', funcao = '" + requerente.getFuncao() + "' WHERE id = " + requerente.getId());

        servicoConexao.fecharConexaoBancoDeDados();
    }

    public void excluirRequerente(Requerente requerente) throws SQLException, ClassNotFoundException {

        if (requerente == null) {
            return;
        }

        DAOCoordenacao daoCoordenacao = new DAOCoordenacao();
        Set<Coordenacao> coordenacoesDoRequerente = daoCoordenacao.recuperarCoordenacoesDoRequerente(requerente);
        for (Coordenacao coordenacao : coordenacoesDoRequerente) {
            daoCoordenacao.excluirCoordenacao(coordenacao);
        }

        ServicoConexao servicoConexao = new ServicoConexao();

        servicoConexao.executarUpdate("DELETE FROM REQUERENTE WHERE id = " + requerente.getId());
        servicoConexao.fecharConexaoBancoDeDados();
    }

}
