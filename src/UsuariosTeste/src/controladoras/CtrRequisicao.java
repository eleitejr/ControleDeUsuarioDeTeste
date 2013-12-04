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
 *                  Classe controladora das telas relacionadas a cadastro, edição, exclusão e alteração de requisições.
 */

package controladoras;

import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

import dao.DAORequisicao;
import entidades.Coordenacao;
import entidades.Requisicao;
import entidades.Usuario;

/**
 * Responsável por controlar a validação dos campos e a interação das telas relativas a requisições. Se comunica com o
 * DAORequisicao.
 * 
 * @see dao.DAORequisicao
 */
public class CtrRequisicao {

    /**
     * Cadastra uma nova requisição, associando-a com a coordenação e o usuário informados.
     * 
     * @param requisicao
     * @param coordenacao
     * @param usuario
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void cadastrarRequisicao(Requisicao requisicao, Coordenacao coordenacao, Usuario usuario)
            throws ClassNotFoundException, SQLException {

        requisicao.setCoordenacao(coordenacao);
        requisicao.setUsuario(usuario);
        requisicao.setData(new Date());
        requisicao.setFoiExecutada(true);
        new DAORequisicao().cadastrarNovaRequisicao(requisicao);
    }

    /**
     * Recupera uma requisição do banco de dados.
     * 
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Requisicao recuperarRequisicao(int id) throws ClassNotFoundException, SQLException {
        return new DAORequisicao().recuperarRequisicaoPorId(id);
    }

    /**
     * Recupera todas as requisições pertencentes à coordenação informada.
     * 
     * @param coordenacao
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Set<Requisicao> recuperarTodasRequisicoesDaCoordenacao(Coordenacao coordenacao) throws ClassNotFoundException,
            SQLException {
        return new DAORequisicao().recuperarRequisicoesDaCoordenacao(coordenacao);
    }

    /**
     * Recupera todas as requisições pertencentes ao usuário informado.
     * 
     * @param usuario
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Set<Requisicao> recuperarTodasRequisicoesDoUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        return new DAORequisicao().recuperarRequisicoesDoUsuario(usuario);
    }

    /**
     * Valida a exclusão de uma requisição no banco de dados.
     * 
     * @param requisicao
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void excluirRequisicao(Requisicao requisicao) throws ClassNotFoundException, SQLException {
        new DAORequisicao().excluirRequisicao(requisicao);
    }
}