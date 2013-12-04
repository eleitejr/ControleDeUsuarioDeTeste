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
 *                  Classe controladora das telas relacionadas a cadastro, edição, exclusão e alteração de grupos.
 */

package controladoras;

import java.sql.SQLException;
import java.util.Set;

import dao.DAOGrupo;
import entidades.Ambiente;
import entidades.Grupo;
import entidades.Usuario;

/**
 * Responsável por controlar a validação dos campos e a interação das telas relativas a grupos. Se comunica com o DAOGrupo.
 * 
 * @see dao.DAOGrupo
 */
public class CtrGrupo {
    private boolean mostrarErroCadastroNome;

    /**
     * Construtor reinicia os indicadores de erro de validação.
     */
    public CtrGrupo() {
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
     * Valida o cadastro de um novo grupo no banco de dados. Caso ocorra erro de validação, retorna 1 e ajusta as flags
     * indicadoras de erro de validação para o campo correspondente do objeto grupo.
     * 
     * @param grupo
     * @return 0 caso seja corretamente cadastrado e 1 caso ocorra erro de validação.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int cadastrarGrupo(Grupo grupo) throws ClassNotFoundException, SQLException {

        // Se o nome for vazio ou de tamanho maior que 50 caracteres, indica que há erro na validação do nome.
        if ((grupo.getNome() == null) || (grupo.getNome().length() == 0) || (grupo.getNome().length() > 50)) {
            setMostrarErroCadastroNome(true);
            return 1;
        }

        // Senão, cadastra o grupo e retorna 0.
        else {
            new DAOGrupo().cadastrarNovoGrupo(grupo);
            return 0;
        }
    }

    /**
     * Valida a alteração de um grupo no banco de dados.
     * 
     * @param grupo
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int alterarGrupo(Grupo grupo) throws ClassNotFoundException, SQLException {

        // Se o nome for vazio ou de tamanho maior que 50 caracteres, indica que há erro na validação do nome.
        if ((grupo.getNome() == null) || (grupo.getNome().length() == 0) || (grupo.getNome().length() > 50)) {
            setMostrarErroCadastroNome(true);
            return 1;
        }

        // Senão, altera o grupo e retorna 0.
        else {
            new DAOGrupo().alterarGrupo(grupo);
            return 0;
        }
    }

    /**
     * Recupera um grupo do banco de dados.
     * 
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Grupo recuperarGrupo(int id) throws ClassNotFoundException, SQLException {
        return new DAOGrupo().recuperarGrupoPorId(id);
    }

    /**
     * Recupera todos os grupos pertencentes ao usuário informado.
     * 
     * @param usuario
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Set<Grupo> recuperarTodosGruposDoUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        return new DAOGrupo().recuperarGruposDoUsuario(usuario);
    }

    /**
     * Recupera todos os grupos do ambiente informado.
     * 
     * @param ambiente
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Set<Grupo> recuperarTodosGruposDoAmgiente(Ambiente ambiente) throws ClassNotFoundException, SQLException {
        return new DAOGrupo().recuperarGruposDoAmbiente(ambiente);
    }

    /**
     * Valida a exclusão de um grupo no banco de dados.
     * 
     * @param grupo
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void excluirGrupo(Grupo grupo) throws ClassNotFoundException, SQLException {
        new DAOGrupo().excluirGrupo(grupo);
    }
}
