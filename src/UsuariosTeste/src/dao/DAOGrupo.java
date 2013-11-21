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

import entidades.Ambiente;
import entidades.Grupo;
import entidades.Usuario;

/**
 * Se comunica com o banco de dados e faz a interface do objeto Grupo com a tabela GRUPO no banco de dados.
 * 
 */
public class DAOGrupo {

    /**
     * Dado um id, recupera o objeto Curso correspondente. Se a id for igual a 0, retorna null.
     * 
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Grupo recuperarGrupoPorId(int id) throws SQLException, ClassNotFoundException {

        Grupo grupo = null;

        if (id == 0) {
            return null;
        }

        ServicoConexao servicoConexao = new ServicoConexao();

        ResultSet resultado = servicoConexao.executarQuery("SELECT * FROM GRUPO WHERE id = " + id);

        if (resultado.next()) {

            String nome = resultado.getString("nome");

            Ambiente ambiente = new DAOAmbiente().recuperarAmbientePorId(resultado.getInt("idAmbiente"));

            Set<Usuario> usuarios = new HashSet<Usuario>();

            resultado = servicoConexao
                    .executarQuery("SELECT * FROM USUARIO_GRUPO WHERE id = (SELECT idUsuario FROM USUARIO_GRUPO WHERE idGrupo = "
                            + id + ")");

            while (resultado.next()) {
                Usuario usuario = new DAOUsuario().recuperarUsuarioPorId(resultado.getInt("id"));
                usuarios.add(usuario);
            }

            grupo = new Grupo(id, nome, ambiente, usuarios);
        }

        servicoConexao.fecharConexaoBancoDeDados();
        return grupo;
    }

    /**
     * Cadastra um novo grupo, cujo objeto é passado como parâmetro.
     * 
     * @param grupo
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void cadastrarNovoGrupo(Grupo grupo) throws SQLException, ClassNotFoundException {

        if (grupo == null) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("INSERT INTO GRUPO(nome, idAmbiente) values('" + grupo.getNome() + "', "
                + grupo.getAmbiente().getId() + ")");

        servicoConexao.fecharConexaoBancoDeDados();
    }

    /**
     * Altera o grupo. Os novos dados são passados como parâmetro.
     * 
     * @param grupo
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void alterarGrupo(Grupo grupo) throws SQLException, ClassNotFoundException {

        if (grupo == null) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        servicoConexao.executarUpdate("UPDATE GRUPO SET nome = '" + grupo.getNome() + "' ambiente = "
                + grupo.getAmbiente().getId() + " WHERE id = " + grupo.getId());

        servicoConexao.fecharConexaoBancoDeDados();
    }

    public Set<Grupo> recuperarGruposDoUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {

        if (usuario == null) {
            return null;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        ResultSet resultado = servicoConexao
                .executarQuery("SELECT * FROM GRUPO WHERE id = (SELECT idGrupo FROM USUARIO_GRUPO WHERE idUsuario = "
                        + usuario.getId() + ")");

        Set<Grupo> gruposDoUsuario = new HashSet<Grupo>();

        while (resultado.next()) {
            Grupo grupo = recuperarGrupoPorId(resultado.getInt("id"));
            gruposDoUsuario.add(grupo);
        }

        servicoConexao.fecharConexaoBancoDeDados();
        return gruposDoUsuario;
    }

    public void excluirGrupo(Grupo grupo) throws SQLException, ClassNotFoundException {

        if (grupo == null) {
            return;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        DAOUsuario daoUsuario = new DAOUsuario();

        for (Usuario usuario : grupo.getUsuarios()) {
            daoUsuario.retirarUsuarioDoGrupo(grupo, usuario);
        }

        servicoConexao.executarUpdate("DELETE FROM GRUPO WHERE id = " + grupo.getId());
        servicoConexao.fecharConexaoBancoDeDados();
    }

    public Set<Grupo> recuperarGruposDoAmbiente(Ambiente ambiente) throws ClassNotFoundException, SQLException {

        Set<Grupo> gruposDoAmbiente = new HashSet<Grupo>();

        if (ambiente == null) {
            return null;
        }

        ServicoConexao servicoConexao = new ServicoConexao();
        ResultSet resultado = servicoConexao.executarQuery("SELECT * FROM GRUPO WHERE idAmbiente = " + ambiente.getId());

        while (resultado.next()) {
            Grupo grupo = recuperarGrupoPorId(resultado.getInt("id"));
            gruposDoAmbiente.add(grupo);
        }

        servicoConexao.fecharConexaoBancoDeDados();
        return gruposDoAmbiente;
    }

}