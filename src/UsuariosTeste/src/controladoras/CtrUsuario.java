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
 *                  Classe controladora das telas relacionadas a cadastro, edição, exclusão e alteração de usuários.
 */

package controladoras;

import java.sql.SQLException;
import java.util.Set;

import dao.DAOUsuario;
import entidades.Grupo;
import entidades.Usuario;

/**
 * Responsável por controlar a validação dos campos e a interação das telas relativas a Usuários. Se comunica com o DAOUsuario.
 * 
 * @see dao.DAOUsuario
 */
public class CtrUsuario {

    private boolean mostrarErroCadastroNome;
    private boolean mostrarErroCadastroEmail;
    private boolean mostrarErroCadastroSenha;

    /**
     * Construtor reinicia os indicadores de erro de validação.
     */
    public CtrUsuario() {
        reiniciarFlagsErrosValidacao();
    }

    public boolean isMostrarErroCadastroNome() {
        return mostrarErroCadastroNome;
    }

    public void setMostrarErroCadastroNome(boolean mostrarErroCadastroNome) {
        this.mostrarErroCadastroNome = mostrarErroCadastroNome;
    }

    public boolean isMostrarErroCadastroEmail() {
        return mostrarErroCadastroEmail;
    }

    public void setMostrarErroCadastroEmail(boolean mostrarErroCadastroEmail) {
        this.mostrarErroCadastroEmail = mostrarErroCadastroEmail;
    }

    public boolean isMostrarErroCadastroSenha() {
        return mostrarErroCadastroSenha;
    }

    public void setMostrarErroCadastroSenha(boolean mostrarErroCadastroSenha) {
        this.mostrarErroCadastroSenha = mostrarErroCadastroSenha;
    }

    /**
     * Altera todas as flags de indicação de erros de validação para o valor false.
     */
    public void reiniciarFlagsErrosValidacao() {
        setMostrarErroCadastroNome(false);
        setMostrarErroCadastroEmail(false);
        setMostrarErroCadastroSenha(false);
    }

    /**
     * Valida o cadastro de um novo usuário no banco de dados. Caso ocorra erro de validação, retorna 1 e ajusta as flags
     * indicadoras de erro de validação para o campo correspondente do objeto usuario.
     * 
     * @param usuario
     * @return 0 caso seja corretamente cadastrado e 1 caso ocorra erro de validação.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int cadastrarUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {

        // O código de retorno irá indicar se houve erros de validação.
        int codigoRetorno = 0;

        // Se o nome for vazio ou de tamanho maior que 200 caracteres, indica que há erro na validação do nome.
        if ((usuario.getNome() == null) || (usuario.getNome().length() == 0) || (usuario.getNome().length() > 200)) {
            setMostrarErroCadastroNome(true);
            codigoRetorno = 1;
        }

        // Se o email for de tamanho maior que 50 caracteres, indica que há erro na validação do email.
        if (usuario.getEmail().length() > 50) {
            setMostrarErroCadastroEmail(true);
            codigoRetorno = 1;
        }

        // Se a senha for vazia ou de tamanho maior que 30 caracteres, indica que há erro na validação da senha.
        if ((usuario.getSenha() == null) || (usuario.getSenha().length() == 0) || (usuario.getSenha().length() > 30)) {
            setMostrarErroCadastroSenha(true);
            codigoRetorno = 1;
        }

        // Se o código de retorno continua sendo 0, não houve erros de validação.
        if (codigoRetorno == 0) {
            new DAOUsuario().cadastrarNovoUsuario(usuario);
        }

        return codigoRetorno;
    }

    /**
     * Valida a alteração de um usuário no banco de dados.
     * 
     * @param usuario
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int alterarUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {

        // O código de retorno irá indicar se houve erros de validação.
        int codigoRetorno = 0;

        // Se o nome for vazio ou de tamanho maior que 200 caracteres, indica que há erro na validação do nome.
        if ((usuario.getNome() == null) || (usuario.getNome().length() == 0) || (usuario.getNome().length() > 200)) {
            setMostrarErroCadastroNome(true);
            codigoRetorno = 1;
        }

        // Se o email for de tamanho maior que 50 caracteres, indica que há erro na validação do email.
        if (usuario.getEmail().length() > 50) {
            setMostrarErroCadastroEmail(true);
            codigoRetorno = 1;
        }

        // Se a senha for vazia ou de tamanho maior que 30 caracteres, indica que há erro na validação da senha.
        if ((usuario.getSenha() == null) || (usuario.getSenha().length() == 0) || (usuario.getSenha().length() > 30)) {
            setMostrarErroCadastroSenha(true);
            codigoRetorno = 1;
        }

        // Se o código de retorno continua sendo 0, não houve erros de validação.
        if (codigoRetorno == 0) {
            new DAOUsuario().alterarUsuario(usuario);
        }

        return codigoRetorno;
    }

    /**
     * Recupera um usuário do banco de dados.
     * 
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Usuario recuperarUsuario(int id) throws ClassNotFoundException, SQLException {
        return new DAOUsuario().recuperarUsuarioPorId(id);
    }

    /**
     * Recupera todos os usuários pertencentes ao grupo informado.
     * 
     * @param grupo
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Set<Usuario> recuperarTodosUsuariosDoGrupo(Grupo grupo) throws ClassNotFoundException, SQLException {
        return new DAOUsuario().recuperarUsuariosDoGrupo(grupo);
    }

    /**
     * Retira o usuário do grupo informado.
     * 
     * @param grupo
     * @param usuario
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void retirarUsuarioDoGrupo(Grupo grupo, Usuario usuario) throws ClassNotFoundException, SQLException {
        new DAOUsuario().retirarUsuarioDoGrupo(grupo, usuario);
    }

    /**
     * Insere o usuário no grupo informado.
     * 
     * @param grupo
     * @param usuario
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void inserirUsuarioNoGrupo(Grupo grupo, Usuario usuario) throws ClassNotFoundException, SQLException {
        new DAOUsuario().inserirUsuarioNoGrupo(grupo, usuario);
    }

    /**
     * Valida a exclusão de um usuário no banco de dados.
     * 
     * @param usuario
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void excluirUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        new DAOUsuario().excluirUsuario(usuario);
    }
}