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
 *                  Classe que modela a entidade Plataforma.                  
 */

package entidades;

/**
 * Classe que modela a entidade Plataforma.
 */
public class Plataforma {

    private int id;
    private String nome;

    /**
     * Construtor recebe 2 parâmetros e os usa para ajustar os campos da plataforma.
     * 
     * @param id
     * @param nome
     */
    public Plataforma(int id, String nome) {
        setId(id);
        setNome(nome);
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
}
