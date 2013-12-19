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
 *                  Classe que provê a interface entre objetos da entidade Requisição e seus dados no banco de dados.
 */

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import entidades.Coordenacao;
import entidades.Requisicao;
import entidades.Usuario;

/**
 * Se comunica com o banco de dados e faz a interface do objeto Requisicao com a tabela REQUISICAO no banco de dados.
 * 
 * @author felipe
 */
public class DAORequisicao {

    /**
     * Dado um id, recupera o objeto Requisicao correspondente. Se a id for igual a 0, retorna null.
     * 
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Requisicao recuperarRequisicaoPorId(int id) throws SQLException, ClassNotFoundException {

        if (id == 0) {
            return null;
        }

        Requisicao requisicao = null;

        // Executa a query de seleção no banco de dados.
        ServicoConexao servicoConexao = new ServicoConexao();
        ResultSet resultado = servicoConexao.executarQuery("SELECT * FROM REQUISICAO WHERE id = " + id);

        // Constrói o objeto requisicao com os dados obtidos do resultado da query.
        if (resultado.next()) {
            Date data = resultado.getDate("data");
            boolean foiExecutada = "S".equalsIgnoreCase(resultado.getString("foi_executada"));
            Usuario usuario = new DAOUsuario().recuperarUsuarioPorId(resultado.getInt("idUsuario"));
            Coordenacao coordenacao = new DAOCoordenacao().recuperarCoordenacaoPorId(resultado.getInt("idCoordenacao"));
            String evento = resultado.getString("evento");

            requisicao = new Requisicao(id, data, foiExecutada, usuario, coordenacao, evento);
        }

        servicoConexao.fecharConexaoBancoDeDados();
        return requisicao;
    }

    /**
     * Cadastra uma nova requisição, cujo objeto é passado como parâmetro.
     * 
     * @param requisicao
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void cadastrarNovaRequisicao(Requisicao requisicao) throws SQLException, ClassNotFoundException {

        if (requisicao == null) {
            return;
        }

        // Executa a query responsável pelo cadastro da nova requisição no banco de dados.
        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("INSERT INTO REQUISICAO (data, foi_executada, idUsuario, idCoordenacao, evento) values("
                + requisicao.getData() + ", '" + (requisicao.isFoiExecutada() ? "S" : "N") + "', "
                + requisicao.getUsuario().getId() + ", " + requisicao.getCoordenacao().getId() + ", '" + requisicao.getEvento()
                + "')");

        servicoConexao.fecharConexaoBancoDeDados();
    }

    /**
     * Altera a requisição. Os novos dados são passados como parâmetro.
     * 
     * @param requisicao
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void alterarRequisicao(Requisicao requisicao) throws SQLException, ClassNotFoundException {

        if (requisicao == null) {
            return;
        }

        // Executa a query responsável pela alteração dos dados da requisição no banco de dados.
        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("UPDATE REQUISICAO SET data = " + requisicao.getData() + ", foi_executada = '"
                + (requisicao.isFoiExecutada() ? "S" : "N") + "', idUsuario = " + requisicao.getUsuario().getId()
                + ", idCoordenacao = " + ", evento = '" + requisicao.getEvento() + "' WHERE id = " + requisicao.getId());

        servicoConexao.fecharConexaoBancoDeDados();
    }

    /**
     * Exclui a requisição do banco de dados.
     * 
     * @param requisicao
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void excluirRequisicao(Requisicao requisicao) throws SQLException, ClassNotFoundException {

        if (requisicao == null) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();

        servicoConexao.executarUpdate("DELETE FROM REQUISICAO WHERE id = " + requisicao.getId());
        servicoConexao.fecharConexaoBancoDeDados();
    }

    /**
     * Recupera todas as requisições associadas ao usuário informado.
     * 
     * @param usuario
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Set<Requisicao> recuperarRequisicoesDoUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {

        if (usuario == null) {
            return null;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        Set<Requisicao> requisicoesDoUsuario = new HashSet<Requisicao>();

        // Executa a consulta no banco de dados.
        ResultSet resultado = servicoConexao.executarQuery("SELECT * FROM REQUISICAO WHERE idUsuario = " + usuario.getId());

        while (resultado.next()) {
            Requisicao requisicao = recuperarRequisicaoPorId(resultado.getInt("id"));
            requisicoesDoUsuario.add(requisicao);
        }

        servicoConexao.fecharConexaoBancoDeDados();
        return requisicoesDoUsuario;
    }

    /**
     * Recupera todas as requisições associadas à coordenação informada.
     * 
     * @param coordenacao
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Set<Requisicao> recuperarRequisicoesDaCoordenacao(Coordenacao coordenacao) throws ClassNotFoundException, SQLException {

        if (coordenacao == null) {
            return null;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        Set<Requisicao> requisicoesDaCoordenacao = new HashSet<Requisicao>();

        // Executa a consulta no banco de dados.
        ResultSet resultado = servicoConexao.executarQuery("SELECT * FROM REQUISICAO WHERE idCoordenacao = "
                + coordenacao.getId());

        while (resultado.next()) {
            Requisicao requisicao = recuperarRequisicaoPorId(resultado.getInt("id"));
            requisicoesDaCoordenacao.add(requisicao);
        }

        servicoConexao.fecharConexaoBancoDeDados();
        return requisicoesDaCoordenacao;
    }

}
