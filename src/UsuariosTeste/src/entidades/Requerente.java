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
 *                  Classe que modela a entidade Requerente.
 */

package entidades;

/**
 * Classe que modela a entidade Requerente.
 */
public class Requerente {

    private int id;
    private String matricula;
    private String nome;
    private String funcao;

    /**
     * Construtor recebe 4 parâmetros, que serão usados para ajustar os valores dos campos correspondentes do requerente.
     * 
     * @param id
     * @param matricula
     * @param nome
     * @param funcao
     */
    public Requerente(int id, String matricula, String nome, String funcao) {
        setId(id);
        setMatricula(matricula);
        setNome(nome);
        setFuncao(funcao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
