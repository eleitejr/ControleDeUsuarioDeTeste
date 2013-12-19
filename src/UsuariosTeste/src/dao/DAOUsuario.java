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
 *                  Classe responsável por realizar a inteface entre objetos da entidade Usuário com seus dados salvos no banco de
 *                  dados.
 */

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import entidades.Grupo;
import entidades.Requisicao;
import entidades.Usuario;

/**
 * Classe responsável pela interface entre objetos Usuario e a tabela USUARIO no banco de dados.
 * 
 * @author felipe
 */
public class DAOUsuario {

    /**
     * Recupera do banco o usuário cujo id é informado.
     * 
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Usuario recuperarUsuarioPorId(int id) throws SQLException, ClassNotFoundException {

        // Se a id for igual a 0 (id inválida), encerra o método imediatamente, retornando null.
        if (id == 0) {
            return null;
        }

        Usuario usuario = null;

        // Tenta se conectar ao banco de dados, e executar a consulta ao banco de dados que retornará o usuário cuja id é passada
        // como parâmetro.
        ServicoConexao servicoConexao = new ServicoConexao();
        ResultSet resultado = servicoConexao.executarQuery("SELECT * FROM USUARIO WHERE id = " + id);

        // Se o resultado retornou uma consulta, joga esses dados no objeto usuario.
        if (resultado.next()) {
            String nome = resultado.getString("nome");
            String senha = resultado.getString("senha");
            String email = resultado.getString("email");
            boolean emUso = "S".equalsIgnoreCase(resultado.getString("em_uso"));
            boolean ativo = "S".equalsIgnoreCase(resultado.getString("ativo"));
            boolean bloqueado = "S".equalsIgnoreCase(resultado.getString("bloqueado"));
            boolean expirado = "S".equalsIgnoreCase(resultado.getString("expirado"));

            Set<Grupo> grupos = new HashSet<Grupo>();

            // Busca no banco os grupos do usuário selecionado.
            resultado = servicoConexao.executarQuery("SELECT * FROM USUARIO_GRUPO WHERE idUsuario = " + id);

            while (resultado.next()) {
                Grupo grupo = new DAOGrupo().recuperarGrupoPorId(resultado.getInt("id"));
                grupos.add(grupo);
            }

            usuario = new Usuario(id, nome, senha, email, emUso, ativo, bloqueado, expirado, grupos);
        }

        servicoConexao.fecharConexaoBancoDeDados();
        return usuario;
    }

    /**
     * Recupera todos os usuários associados ao grupo informado.
     * 
     * @param grupo
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Set<Usuario> recuperarUsuariosDoGrupo(Grupo grupo) throws ClassNotFoundException, SQLException {

        if (grupo == null) {
            return null;
        }

        // Executa a query de seleção com o grupo informado.
        ServicoConexao servicoConexao = new ServicoConexao();
        ResultSet resultado = servicoConexao
                .executarQuery("SELECT * FROM USUARIO where id = (SELECT idUsuario FROM USUARIO_GRUPO WHERE idGrupo = "
                        + grupo.getId() + ")");

        Set<Usuario> usuariosDoGrupo = new HashSet<Usuario>();

        // Adiciona os usuários obtidos no resultado da query ao conjunto usuariosDoGrupo.
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

        // Executa a query que cria o cadastr do usuário no banco de dados.
        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("INSERT INTO USUARIO(nome, senha, email, em_uso, ativo, bloqueado, expirado) values('"
                + usuario.getNome() + "', '" + usuario.getSenha() + "', '" + usuario.getEmail() + "', '"
                + (usuario.isEmUso() ? "S" : "N") + "', '" + (usuario.isAtivo() ? "S" : "N") + "', '"
                + (usuario.isBloqueado() ? "S" : "N") + "', '" + (usuario.isExpirado() ? "S" : "N") + "')");

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
        servicoConexao.executarUpdate("UPDATE USUARIO SET nome = '" + usuario.getNome() + "', senha = '" + usuario.getSenha()
                + "', email = '" + usuario.getEmail() + "', em_uso = '" + (usuario.isEmUso() ? "S" : "N") + "', ativo = '"
                + (usuario.isAtivo() ? "S" : "N") + "', bloqueado = '" + (usuario.isBloqueado() ? "S" : "N") + "', expirado = '"
                + (usuario.isExpirado() ? "S" : "N") + "' WHERE id = " + usuario.getId());

        servicoConexao.fecharConexaoBancoDeDados();
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

        if ((grupo == null) || (usuario == null)) {
            return;
        }

        // Executa a query de exclusão do usuário no banco de dados.
        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("DELETE FROM USUARIO_GRUPO WHERE idGrupo = " + grupo.getId() + " AND idUsuario = "
                + usuario.getId());

        servicoConexao.fecharConexaoBancoDeDados();
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

        if ((grupo == null) || (usuario == null)) {
            return;
        }

        // Executa a query de inserção do usuário no grupo, no banco de dados.
        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("INSERT INTO USUARIO_GRUPO (idGrupo, idUsuario) values (" + grupo.getId() + ", "
                + usuario.getId() + ")");

        servicoConexao.fecharConexaoBancoDeDados();
    }

    /**
     * Exclui o usuário informado.
     * 
     * @param usuario
     *            ServicoConexao
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void excluirUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {

        // Se o usuario for null, encerra o método imediatamente.
        if (usuario == null) {
            return;
        }

        // Retira o usuário de todos os grupos associados.
        for (Grupo grupo : usuario.getGrupos()) {
            retirarUsuarioDoGrupo(grupo, usuario);
        }

        // Exclui todas as requisições do usuário informado.
        DAORequisicao daoRequisicao = new DAORequisicao();
        Set<Requisicao> requisicoesDoUsuario = daoRequisicao.recuperarRequisicoesDoUsuario(usuario);
        for (Requisicao requisicao : requisicoesDoUsuario) {
            daoRequisicao.excluirRequisicao(requisicao);
        }

        // Exclui o usuário.
        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("DELETE FROM USUARIO_WHERE id = " + usuario.getId());

        servicoConexao.fecharConexaoBancoDeDados();
    }
}
