/**
 * 					Universidade de Brasília
 * 					Instituto de Ciências Exatas
 * 					Departamento de Ciência da Computação
 * 
 * 					Engenharia de Software - turma B
 *  					
 * 					2º semestre de 2013
 * 					
 * 					Alunos:
 * 					- Felipe Camargos Costa - 10/0100341
 * 					- Gutemberg Guilherme de Araújo - 11/0120451
 *                  - Erasmo de Castro Leite Junior - 12/0139855
 * 
 * 					Descrição:
 * 					
 * 
 */

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Provê os serviços básicos de comunicação com o banco de dados.
 * 
 * @author felipe
 */
public class ServicoConexao {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost/UsuariosTeste";
	private static String username = "root";
	private static String password = "Juliana11@";

	// variável static que informa se uma conexão ao banco está ativa.
	private static boolean conectadoAoBancoDeDados;

	private static Connection conexao;
	private Statement statement;

	public static void setUsername(String nomeUsuario) {
		username = nomeUsuario;
	}

	private static String getUsername() {
		return username;
	}

	public static void setPassword(String senha) {
		password = senha;
	}

	private static String getPassword() {
		return password;
	}

	/**
	 * Altera o valor da variável que informa o estado da conexão ao banco de dados.
	 * 
	 * @param valor
	 */
	public static void setConectadoAoBancoDeDados(boolean valor) {
		conectadoAoBancoDeDados = valor;
	}

	/**
	 * Informa o valor da variável conectadoAoBancoDeDados.
	 * 
	 * @return true caso uma conexão esteja ativa, false caso contrário.
	 */
	public static boolean isConectadoAoBancoDeDados() {
		return conectadoAoBancoDeDados;
	}

	private static void setConexao(Connection conexaoParametro) {
		conexao = conexaoParametro;
	}

	private Connection getConexao() {
		return conexao;
	}

	private void setStatement(Statement statement) {
		this.statement = statement;
	}

	private Statement getStatement() {
		return statement;
	}

	/**
	 * Tenta se conectar ao banco de dados.
	 * 
	 * @return a conexão criada. Retorna null caso não tenha sido possível criá-la.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void conectarAoBancoDeDados() throws SQLException, ClassNotFoundException {

		if (isConectadoAoBancoDeDados()) {
			return;
		} else {
			Class.forName(JDBC_DRIVER);
			setConexao(DriverManager.getConnection(DATABASE_URL, getUsername(), getPassword()));
			setStatement(conexao.createStatement());
			setConectadoAoBancoDeDados(true);
		}
	}

	/**
	 * Tenta fechar uma conexão de banco de dados. Caso ocorram erros, lança uma exceção SQLException.
	 * 
	 * @param conexao
	 * @throws SQLException
	 */
	public void fecharConexaoBancoDeDados() throws SQLException {

		// Se não houver conexões ativas, não faz nada.
		if (!isConectadoAoBancoDeDados()) {
			return;
		}

		// Senão, tenta fechá-la.
		else {

			// Se o autoCommit estiver ativo, apenas fecha a conexão. Do contrário, executa o commit.
			if (!getConexao().getAutoCommit()) {
				getConexao().commit();
			}

			// Atualiza o valor do campo static, informando que a conexão não está mais ativa.
			setConectadoAoBancoDeDados(false);
		}
	}

	/**
	 * Executa uma query no banco de dados, e cuida para que a conexão esteja aberta. Não fecha a conexão, sendo de
	 * responsabilidade do método invocador chamar depois o método fecharConexaoBancoDeDados. Retorna o ResultSet da query
	 * executada, ou null caso a query seja nula ou vazia.
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ResultSet executarQuery(String query) throws SQLException, ClassNotFoundException {

		if (query == null || "".equals(query.trim())) {
			return null;
		}

		// Se não houver uma conexão ativa, abre uma nova conexão.
		if (!isConectadoAoBancoDeDados()) {
			conectarAoBancoDeDados();
		}

		setStatement(conexao.createStatement());
		return getStatement().executeQuery(query);
	}

	/**
	 * Executa uma sql de update no banco de dados. Retorna 0 caso nenhuma query seja executada, ou se a sql passada seja nula ou
	 * vazia.
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int executarUpdate(String sql) throws SQLException, ClassNotFoundException {

		if (sql == null || "".equals(sql.trim())) {
			return 0;
		}

		if (!isConectadoAoBancoDeDados()) {
			conectarAoBancoDeDados();
		}

		return getStatement().executeUpdate(sql);
	}
}
