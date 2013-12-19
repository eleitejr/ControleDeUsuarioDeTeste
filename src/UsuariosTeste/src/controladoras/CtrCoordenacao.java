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
 *                  Classe controladora das telas relacionadas a cadastro, edição, exclusão e alteração de coordenações.
 */

package controladoras;

import java.sql.SQLException;
import java.util.Set;

import dao.DAOCoordenacao;
import entidades.Coordenacao;
import entidades.Requerente;

/**
 * Responsável por controlar a validação dos campos e a interação das telas relativas a coordenações. Se comunica com o
 * DAOCoordenacao.
 * 
 * @author felipe
 * @see dao.DAOCoordenacao
 */
public class CtrCoordenacao {
    private boolean mostrarErroCadastroNome;

    /**
     * Construtor reinicia os indicadores de erro de validação.
     */
    public CtrCoordenacao() {
        reiniciarFlagsErrosValidacao();
    }

    public boolean isMostrarErroCadastroNome() {
        return mostrarErroCadastroNome;
    }

    public void setMostrarErroCadastroNome(boolean mostrarErroCadastroNome) {
        this.mostrarErroCadastroNome = mostrarErroCadastroNome;
    }

    public void reiniciarFlagsErrosValidacao() {
        setMostrarErroCadastroNome(false);
    }

    /**
     * Valida o cadastro de uma nova coordeção no banco de dados. Caso ocorra erro de validação, retorna 1 e ajusta as flags
     * indicadoras de erro de validação para o campo correspondente do objeto coordenacao.
     * 
     * @param coordenacao
     * @return 0 caso seja corretamente cadastrado e 1 caso ocorra erro de validação.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int cadastrarCoordenacao(Coordenacao coordenacao) throws ClassNotFoundException, SQLException {

        // Se o nome for vazio ou de tamanho maior que 50 caracteres, indica que há erro na validação do nome.
        if ((coordenacao.getNome() == null) || (coordenacao.getNome().length() == 0) || (coordenacao.getNome().length() > 50)) {
            setMostrarErroCadastroNome(true);
            return 1;
        }

        // Senão, cadastra a coordenação e retorna 0.
        else {
            new DAOCoordenacao().cadastrarNovaCoordenacao(coordenacao);
            return 0;
        }
    }

    /**
     * Valida a alteração de uma coordenação no banco de dados.
     * 
     * @param coordenacao
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int alterarCoordenacao(Coordenacao coordenacao) throws ClassNotFoundException, SQLException {

        // Se o nome for vazio ou de tamanho maior que 50 caracteres, indica que há erro na validação do nome.
        if ((coordenacao.getNome() == null) || (coordenacao.getNome().length() == 0) || (coordenacao.getNome().length() > 50)) {
            setMostrarErroCadastroNome(true);
            return 1;
        }

        // Senão, altera a coordenação e retorna 0.
        else {
            new DAOCoordenacao().alterarCoordenacao(coordenacao);
            return 0;
        }
    }

    /**
     * Recupera uma coordenacao do banco de dados.
     * 
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Coordenacao recuperarCoordenacao(int id) throws ClassNotFoundException, SQLException {
        return new DAOCoordenacao().recuperarCoordenacaoPorId(id);
    }

    /**
     * Recupera todas as coordenações pertencentes ao requerente informado.
     * 
     * @param requerente
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Set<Coordenacao> recuperarTodasCoordenacoesDoRequerente(Requerente requerente) throws ClassNotFoundException,
            SQLException {
        return new DAOCoordenacao().recuperarCoordenacoesDoRequerente(requerente);
    }

    /**
     * Valida a exclusão de uma coordenação no banco de dados.
     * 
     * @param coordenacao
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void excluirCoordenacao(Coordenacao coordenacao) throws ClassNotFoundException, SQLException {
        new DAOCoordenacao().excluirCoordenacao(coordenacao);
    }
}
