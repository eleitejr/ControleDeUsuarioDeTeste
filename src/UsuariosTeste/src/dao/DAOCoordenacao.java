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
 *                  Se comunica com o banco de dados e faz a interface do objeto Coordenacao com a tabela COORDENACAO no banco de
 *                  dados.
 */

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import entidades.Coordenacao;
import entidades.Requerente;
import entidades.Requisicao;

/**
 * Se comunica com o banco de dados e faz a interface do objeto Coordenacao com a tabela COORDENACAO no banco de dados.
 * 
 * @author felipe
 */
public class DAOCoordenacao {

    /**
     * Dado um id, recupera o objeto Coordenacao correspondente. Se a id for igual a 0, retorna null.
     * 
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Coordenacao recuperarCoordenacaoPorId(int id) throws SQLException, ClassNotFoundException {

        if (id == 0) {
            return null;
        }

        Coordenacao coordenacao = null;

        ServicoConexao servicoConexao = new ServicoConexao();
        ResultSet resultado = servicoConexao.executarQuery("SELECT * FROM COORDENACAO WHERE id = " + id);

        if (resultado.next()) {
            String nome = resultado.getString("nome");
            Requerente requerente = new DAORequerente().recuperarRequerentePorId(resultado.getInt("idRequerente"));
            coordenacao = new Coordenacao(id, nome, requerente);
        }

        servicoConexao.fecharConexaoBancoDeDados();
        return coordenacao;
    }

    /**
     * Cadastra uma nova coordenação, cujo objeto é passado como parâmetro.
     * 
     * @param coordenacao
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void cadastrarNovaCoordenacao(Coordenacao coordenacao) throws SQLException, ClassNotFoundException {

        if (coordenacao == null) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("INSERT INTO COORDENACAO (nome, idRequerente) values('" + coordenacao.getNome() + "', "
                + coordenacao.getRequerente().getId() + ")");

        servicoConexao.fecharConexaoBancoDeDados();
    }

    /**
     * Altera a coordenação. Os novos dados são passados como parâmetro.
     * 
     * @param coordenacao
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void alterarCoordenacao(Coordenacao coordenacao) throws SQLException, ClassNotFoundException {

        if (coordenacao == null) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("UPDATE COORDENACAO SET nome = '" + coordenacao.getNome() + "', idRequerente = "
                + coordenacao.getRequerente().getId() + " WHERE id = " + coordenacao.getId());

        servicoConexao.fecharConexaoBancoDeDados();
    }

    /**
     * Exclui a coordenação informada.
     * 
     * @param coordenacao
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void excluirCoordenacao(Coordenacao coordenacao) throws SQLException, ClassNotFoundException {

        if (coordenacao == null) {
            return;
        }

        DAORequisicao daoRequisicao = new DAORequisicao();
        Set<Requisicao> requisicoesDaCoordenacao = daoRequisicao.recuperarRequisicoesDaCoordenacao(coordenacao);

        for (Requisicao requisicao : requisicoesDaCoordenacao) {
            daoRequisicao.excluirRequisicao(requisicao);
        }

        ServicoConexao servicoConexao = new ServicoConexao();

        servicoConexao.executarUpdate("DELETE FROM COORDENACAO WHERE id = " + coordenacao.getId());
        servicoConexao.fecharConexaoBancoDeDados();
    }

    /**
     * Recupera todas as coordenações do requerente informado.
     * 
     * @param requerente
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Set<Coordenacao> recuperarCoordenacoesDoRequerente(Requerente requerente) throws SQLException, ClassNotFoundException {

        if (requerente == null) {
            return null;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        Set<Coordenacao> coordenacoesDoRequerente = new HashSet<Coordenacao>();

        ResultSet resultado = servicoConexao
                .executarQuery("SELECT * FROM COORDENACAO WHERE idRequerente = " + requerente.getId());

        while (resultado.next()) {
            Coordenacao coordenacao = recuperarCoordenacaoPorId(resultado.getInt("id"));
            coordenacoesDoRequerente.add(coordenacao);
        }

        servicoConexao.fecharConexaoBancoDeDados();
        return coordenacoesDoRequerente;
    }
}
