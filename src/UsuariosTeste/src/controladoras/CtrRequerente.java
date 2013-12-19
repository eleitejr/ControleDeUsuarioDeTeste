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
 *                  Classe controladora das telas relacionadas a cadastro, edição, exclusão e alteração de requerentes.
 */

package controladoras;

import java.sql.SQLException;
import java.util.Set;

import dao.DAORequerente;
import entidades.Requerente;

/**
 * Responsável por controlar a validação dos campos e a interação das telas relativas a Requerentes. Se comunica com o
 * DAORequerente.
 * 
 * @see dao.DAORequerente
 */
public class CtrRequerente {

    private boolean mostrarErroCadastroNome;
    private boolean mostrarErroCadastroMatricula;
    private boolean mostrarErroCadastroFuncao;

    /**
     * Construtor reinicia os indicadores de erro de validação.
     */
    public CtrRequerente() {
        reiniciarFlagsErrosValidacao();
    }

    public boolean isMostrarErroCadastroNome() {
        return mostrarErroCadastroNome;
    }

    public void setMostrarErroCadastroNome(boolean mostrarErroCadastroNome) {
        this.mostrarErroCadastroNome = mostrarErroCadastroNome;
    }

    public boolean isMostrarErroCadastroMatricula() {
        return mostrarErroCadastroMatricula;
    }

    public void setMostrarErroCadastroMatricula(boolean mostrarErroCadastroMatricula) {
        this.mostrarErroCadastroMatricula = mostrarErroCadastroMatricula;
    }

    public boolean isMostrarErroCadastroFuncao() {
        return mostrarErroCadastroFuncao;
    }

    public void setMostrarErroCadastroFuncao(boolean mostrarErroCadastroFuncao) {
        this.mostrarErroCadastroFuncao = mostrarErroCadastroFuncao;
    }

    public void reiniciarFlagsErrosValidacao() {
        setMostrarErroCadastroNome(false);
        setMostrarErroCadastroMatricula(false);
        setMostrarErroCadastroFuncao(false);
    }

    /**
     * Valida o cadastro de um novo requerente no banco de dados. Caso ocorra erro de validação, retorna 1 e ajusta as flags
     * indicadoras de erro de validação para o campo correspondente do objeto requerente.
     * 
     * @param requerente
     * @return 0 caso seja corretamente cadastrado e 1 caso ocorra erro de validação.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int cadastrarRequerente(Requerente requerente) throws ClassNotFoundException, SQLException {

        // O código de retorno irá indicar se houve erros de validação.
        int codigoRetorno = 0;

        // Se o nome for vazio ou de tamanho maior que 200 caracteres, indica que há erro na validação do nome.
        if ((requerente.getNome() == null) || (requerente.getNome().length() == 0) || (requerente.getNome().length() > 200)) {
            setMostrarErroCadastroNome(true);
            codigoRetorno = 1;
        }

        // Se a matrícula for vazia ou de tamanho maior que 30 caracteres, indica que há erro na validação do nome.
        if ((requerente.getMatricula() == null) || (requerente.getMatricula().length() == 0)
                || (requerente.getMatricula().length() > 30)) {
            setMostrarErroCadastroMatricula(true);
            codigoRetorno = 1;
        }

        // Se a função for vazia ou de tamanho maior que 30 caracteres, indica que ha erro na validação da função.
        if ((requerente.getFuncao() == null) || (requerente.getFuncao().length() == 0) || (requerente.getFuncao().length() > 30)) {
            setMostrarErroCadastroFuncao(true);
            codigoRetorno = 1;
        }

        // Se o código de retorno continua sendo 0, não houve erros de validação.
        if (codigoRetorno == 0) {
            new DAORequerente().cadastrarNovoRequerente(requerente);
        }

        return codigoRetorno;
    }

    /**
     * Valida a alteração de um requerente no banco de dados.
     * 
     * @param requerente
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int alterarRequerente(Requerente requerente) throws ClassNotFoundException, SQLException {

        // O código de retorno irá indicar se houve erros de validação.
        int codigoRetorno = 0;

        // Se o nome for vazio ou de tamanho maior que 200 caracteres, indica que há erro na validação do nome.
        if ((requerente.getNome() == null) || (requerente.getNome().length() == 0) || (requerente.getNome().length() > 200)) {
            setMostrarErroCadastroNome(true);
            codigoRetorno = 1;
        }

        // Se a matrícula for vazia ou de tamanho maior que 30 caracteres, indica que há erro na validação do nome.
        if ((requerente.getMatricula() == null) || (requerente.getMatricula().length() == 0)
                || (requerente.getMatricula().length() > 30)) {
            setMostrarErroCadastroMatricula(true);
            codigoRetorno = 1;
        }

        // Se a função for vazia ou de tamanho maior que 30 caracteres, indica que ha erro na validação da função.
        if ((requerente.getFuncao() == null) || (requerente.getFuncao().length() == 0) || (requerente.getFuncao().length() > 30)) {
            setMostrarErroCadastroFuncao(true);
            codigoRetorno = 1;
        }

        // Se o código de retorno continua sendo 0, não houve erros de validação.
        if (codigoRetorno == 0) {
            new DAORequerente().alterarRequerente(requerente);
        }

        return codigoRetorno;
    }

    /**
     * Recupera um requerente do banco de dados.
     * 
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Requerente recuperarRequerente(int id) throws ClassNotFoundException, SQLException {
        return new DAORequerente().recuperarRequerentePorId(id);
    }

    /**
     * Recupera todos os requerentes do banco de dados.
     * 
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Set<Requerente> recuperarTodosRequerentes() throws ClassNotFoundException, SQLException {
        return new DAORequerente().recuperarTodosRequerentes();
    }

    /**
     * Valida a exclusão de um requerente no banco de dados.
     * 
     * @param requerente
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void excluirRequerente(Requerente requerente) throws ClassNotFoundException, SQLException {
        new DAORequerente().excluirRequerente(requerente);
    }
}