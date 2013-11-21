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

package entidades;

public class Ambiente {

    private int id;
    private String nome;
    private Plataforma plataforma;

    public Ambiente(int id, String nome, Plataforma plataforma) {
        setId(id);
        setNome(nome);
        setPlataforma(plataforma);
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

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }
}
