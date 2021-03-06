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
 *                  Classe que modela a entidade Coordenação.
 */

package entidades;

/**
 * Classe que modela a entidade Coordenação.
 * 
 * @author felipe
 */
public class Coordenacao {

    private int id;
    private String nome;
    private Requerente requerente;

    /**
     * Construtor recebe 3 parâmetros, que serão usados para ajustar os campos correspondentes da coordenação.
     * 
     * @param id
     * @param nome
     * @param requerente
     */
    public Coordenacao(int id, String nome, Requerente requerente) {
        setId(id);
        setNome(nome);
        setRequerente(requerente);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Requerente getRequerente() {
        return requerente;
    }

    public void setRequerente(Requerente requerente) {
        this.requerente = requerente;
    }
}
