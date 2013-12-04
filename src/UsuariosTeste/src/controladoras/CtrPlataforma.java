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
 *                  Classe controladora das telas relacionadas a cadastro, edição, exclusão e alteração de plataformas.
 */

package controladoras;

import java.sql.SQLException;

import dao.DAOPlataforma;
import entidades.Plataforma;

/**
 * Responsável por controlar a validação dos campos e a interação das telas relativas a plataformas. Se comunica com o
 * DAOPlataforma.
 * 
 * @see dao.DAOPlataforma
 */
public class CtrPlataforma {
    private boolean mostrarErroCadastroNome;

    /**
     * Construtor reinicia os indicadores de erro de validação.
     */
    public CtrPlataforma() {
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
     * Valida o cadastro de uma nova plataforma no banco de dados. Caso ocorra erro de validação, retorna 1 e ajusta as flags
     * indicadoras de erro de validação para o campo correspondente do objeto plataforma.
     * 
     * @param plataforma
     * @return 0 caso seja corretamente cadastrado e 1 caso ocorra erro de validação.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int cadastrarPlataforma(Plataforma plataforma) throws ClassNotFoundException, SQLException {

        // Se o nome for vazio ou de tamanho maior que 50 caracteres, indica que há erro na validação do nome.
        if ((plataforma.getNome() == null) || (plataforma.getNome().length() == 0) || (plataforma.getNome().length() > 50)) {
            setMostrarErroCadastroNome(true);
            return 1;
        }

        // Senão, cadastra a plataforma e retorna 0.
        else {
            new DAOPlataforma().cadastrarNovaPlataforma(plataforma);
            return 0;
        }
    }

    /**
     * Valida a alteração de uma plataforma no banco de dados.
     * 
     * @param plataforma
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int alterarAmbiente(Plataforma plataforma) throws ClassNotFoundException, SQLException {

        // Se o nome for vazio ou de tamanho maior que 50 caracteres, indica que há erro na validação do nome.
        if ((plataforma.getNome() == null) || (plataforma.getNome().length() == 0) || (plataforma.getNome().length() > 50)) {
            setMostrarErroCadastroNome(true);
            return 1;
        }

        // Senão, altera a plataforma e retorna 0.
        else {
            new DAOPlataforma().alterarPlataforma(plataforma);
            return 0;
        }
    }

    /**
     * Recupera uma plataforma do banco de dados.
     * 
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Plataforma recuperarPlataforma(int id) throws ClassNotFoundException, SQLException {
        return new DAOPlataforma().recuperarPlataformaPorId(id);
    }

    /**
     * Valida a exclusão de uma plataforma no banco de dados.
     * 
     * @param plataforma
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void excluirPlataforma(Plataforma plataforma) throws ClassNotFoundException, SQLException {
        new DAOPlataforma().excluirPlataforma(plataforma);
    }
}
