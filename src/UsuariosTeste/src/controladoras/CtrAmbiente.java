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
 *                  Classe controladora das telas relacionadas a cadastro, edição, exclusão e alteração de ambientes.
 */

package controladoras;

import java.sql.SQLException;
import java.util.Set;

import dao.DAOAmbiente;
import entidades.Ambiente;
import entidades.Plataforma;

/**
 * Responsável por controlar a validação dos campos e a interação das telas relativas a ambientes. Se comunica com o DAOAmbiente.
 * 
 * @see dao.DAOAmbiente
 */
public class CtrAmbiente {
    private boolean mostrarErroCadastroNome;

    /**
     * Construtor reinicia os indicadores de erro de validação.
     */
    public CtrAmbiente() {
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
     * Valida o cadastro de um novo ambiente no banco de dados. Caso ocorra erro de validação, retorna 1 e ajusta as flags
     * indicadoras de erro de validação para o campo correspondente do objeto ambiente.
     * 
     * @param ambiente
     * @return 0 caso seja corretamente cadastrado e 1 caso ocorra erro de validação.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int cadastrarAmbiente(Ambiente ambiente) throws ClassNotFoundException, SQLException {

        // Se o nome for vazio ou de tamanho maior que 50 caracteres, indica que há erro na validação do nome.
        if ((ambiente.getNome() == null) || (ambiente.getNome().length() == 0) || (ambiente.getNome().length() > 50)) {
            setMostrarErroCadastroNome(true);
            return 1;
        }

        // Senão, cadastra o ambiente e retorna 0.
        else {
            new DAOAmbiente().cadastrarNovoAmbiente(ambiente);
            return 0;
        }
    }

    /**
     * Valida a alteração de um ambiente no banco de dados.
     * 
     * @param ambiente
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int alterarAmbiente(Ambiente ambiente) throws ClassNotFoundException, SQLException {

        // Se o nome for vazio ou de tamanho maior que 50 caracteres, indica que há erro na validação do nome.
        if ((ambiente.getNome() == null) || (ambiente.getNome().length() == 0) || (ambiente.getNome().length() > 50)) {
            setMostrarErroCadastroNome(true);
            return 1;
        }

        // Senão, altera o ambiente e retorna 0.
        else {
            new DAOAmbiente().alterarAmbiente(ambiente);
            return 0;
        }
    }

    /**
     * Recupera um ambiente do banco de dados.
     * 
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Ambiente recuperarAmbiente(int id) throws ClassNotFoundException, SQLException {
        return new DAOAmbiente().recuperarAmbientePorId(id);
    }

    /**
     * Recupera todos os ambientes pertencentes à plataforma informada.
     * 
     * @param plataforma
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Set<Ambiente> recuperarTodosAmbientesDaPlataforma(Plataforma plataforma) throws ClassNotFoundException, SQLException {
        return new DAOAmbiente().recuperarAmbientesDaPlataforma(plataforma);
    }

    /**
     * Valida a exclusão de um ambiente no banco de dados.
     * 
     * @param ambiente
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void excluirAmbiente(Ambiente ambiente) throws ClassNotFoundException, SQLException {
        new DAOAmbiente().excluirAmbiente(ambiente);
    }
}
