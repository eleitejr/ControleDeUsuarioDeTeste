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
import java.util.HashSet;
import java.util.Set;

import entidades.Grupo;
import entidades.Usuario;

/**
 * 
 *
 */
public class DAOUsuario {

    public Usuario recuperarUsuarioPorId(int id) throws SQLException, ClassNotFoundException {
        ResultSet resultado = null;
        Usuario usuario = null;

        String nome = null, senha = null, email = null;
        boolean emUso = false, ativo = false, bloqueado = false, expirado = false;
        Set<Grupo> grupos = new HashSet<Grupo>();

        // Se a id for igual a 0 (id inválida), encerra o método imediatamente, retornando null.
        if (id == 0) {
            return null;
        }

        // Tenta se conectar ao banco de dados, e executar a consulta ao banco de dados que retornará o usuário cuja id é passada
        // como parâmetro.
        ServicoConexao servicoConexao = new ServicoConexao();
        resultado = servicoConexao.executarQuery("SELECT * FROM USUARIO WHERE id = " + id);

        // Se o resultado retornou uma consulta, joga esses dados no objeto usuario.
        if (resultado.next()) {
            nome = resultado.getString("nome");
            senha = resultado.getString("senha");
            email = resultado.getString("email");
            emUso = "S".equalsIgnoreCase(resultado.getString("em_uso"));
            ativo = "S".equalsIgnoreCase(resultado.getString("ativo"));
            bloqueado = "S".equalsIgnoreCase(resultado.getString("bloqueado"));
            expirado = "S".equalsIgnoreCase(resultado.getString("expirado"));
        }

        resultado = servicoConexao.executarQuery("SELECT * FROM USUARIO_GRUPO WHERE idUsuario = " + id);

        if (resultado.next()) {

        }

        usuario = new Usuario(id, nome, senha, email, emUso, ativo, bloqueado, expirado, grupos);

        servicoConexao.fecharConexaoBancoDeDados();
        return usuario;
    }

    public Set<Usuario> recuperarUsuariosDoGrupo(Grupo grupo) throws ClassNotFoundException, SQLException {

        if (grupo == null) {
            return null;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        ResultSet resultado = servicoConexao
                .executarQuery("SELECT * FROM USUARIO where id = (SELECT idUsuario FROM USUARIO_GRUPO WHERE idGrupo = "
                        + grupo.getId() + ")");

        Set<Usuario> usuariosDoGrupo = new HashSet<Usuario>();

        while (resultado.next()) {
            Usuario usuario = recuperarUsuarioPorId(resultado.getInt("id"));
            usuariosDoGrupo.add(usuario);
        }

        servicoConexao.fecharConexaoBancoDeDados();
        return usuariosDoGrupo;
    }

    /**
     * Cadastra um novo usuário, cujo objeto é passado como parâmetro.
     * 
     * @param usuario
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void cadastrarNovoUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {

        // Se o objeto usuario passado como parâmetro for null, encerra imediatamente o método, retornando null.
        if (usuario == null) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("INSERT INTO USUARIO(nome, senha, email, emUso, ativo, bloqueado, expirado) values('"
                + usuario.getNome() + "', '" + usuario.getSenha() + "', '" + (usuario.isEmUso() ? "S" : "N") + "', '"
                + (usuario.isAtivo() ? "S" : "N") + "', '" + (usuario.isBloqueado() ? "S" : "N") + "', '"
                + (usuario.isExpirado() ? "S" : "N") + ")");

        servicoConexao.fecharConexaoBancoDeDados();
    }

    /**
     * Altera o usuário. Os novos dados são passados como parâmetro.
     * 
     * @param campus
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void alterarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {

        if (usuario == null) {
            return;
        }

        // Tenta abrir uma conexao com o banco de dados e executar uma seleção no banco de dados que deve alterar os dados do
        // usuário.
        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("UPDATE USUARIO SET nome = '" + usuario.getNome() + "' + senha = '" + usuario.getSenha()
                + "' email = '" + usuario.getEmail() + "'  emUso = '" + (usuario.isEmUso() ? "S" : "N") + "' ativo = '"
                + (usuario.isAtivo() ? "S" : "N") + "' bloqueado = '" + (usuario.isBloqueado() ? "S" : "N") + "' expirado = '"
                + (usuario.isExpirado() ? "S" : "N") + "' WHERE id = " + usuario.getId());

        servicoConexao.fecharConexaoBancoDeDados();
    }

    public void retirarUsuarioDoGrupo(Grupo grupo, Usuario usuario) throws ClassNotFoundException, SQLException {

        if ((grupo == null) || (usuario == null)) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("DELETE FROM USUARIO_GRUPO WHERE idGrupo = " + grupo.getId() + " AND idUsuario = "
                + usuario.getId());

        servicoConexao.fecharConexaoBancoDeDados();
    }

    public void inserirUsuarioNoGrupo(Grupo grupo, Usuario usuario) throws ClassNotFoundException, SQLException {

        if ((grupo == null) || (usuario == null)) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("INSERT INTO USUARIO_GRUPO (idGrupo, idUsuario) values (" + grupo.getId() + ", "
                + usuario.getId() + ")");

        servicoConexao.fecharConexaoBancoDeDados();
    }

    public void excluirUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {

        // Se o usuario for null, encerra o método imediatamente.
        if (usuario == null) {
            return;
        }

        for (Grupo grupo : usuario.getGrupos()) {
            retirarUsuarioDoGrupo(grupo, usuario);
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("DELETE FROM USUARIO_WHERE id = " + usuario.getId());

        servicoConexao.fecharConexaoBancoDeDados();
    }
}
