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
 *                  Classe que modela a entidade Requisição.
 */

package entidades;

import java.util.Date;

/**
 * Classe que modela a entidade Requisição.
 * 
 * @author felipe
 */
public class Requisicao {

    /** Constante para indicar um evento de criação de usuário. */
    public final String EVENTO_CRIACAO = "CRIACAO";

    /** Constante para indicar um evento de bloqueio de usuário. */
    public final String EVENTO_BLOQUEIO = "EVENTO_BLOQUEIO";

    /** Constante para indicar um evento de troca de senha de usuário. */
    public final String EVENTO_TROCA_DE_SENHA = "TROCA_DE_SENHA";

    /** Constante para indicar um evento de desbloqueio de usuário. */
    public final String EVENTO_DESBLOQUEIO = "DESBLOQUEIO";

    /** Constante para indicar um evento de exclusão de usuário. */
    public final String EVENTO_DELECAO = "DELECAO";

    /** Constante para indicar um evento de envio de e-mail para o usuário. */
    public final String EVENTO_ENVIO_DE_EMAIL = "ENVIO_DE_EMAIL";

    private int id;
    private Date data;
    private boolean foiExecutada;
    private Usuario usuario;
    private Coordenacao coordenacao;
    private String evento;

    /**
     * Construtor recebe 6 parâmetros e os usa para ajustar os campos correspondentes da requisição.
     * 
     * @param id
     * @param data
     * @param foiExecutada
     * @param usuario
     * @param coordenacao
     * @param evento
     */
    public Requisicao(int id, Date data, boolean foiExecutada, Usuario usuario, Coordenacao coordenacao, String evento) {
        setId(id);
        setData(data);
        setFoiExecutada(foiExecutada);
        setUsuario(usuario);
        setCoordenacao(coordenacao);
        setEvento(evento);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isFoiExecutada() {
        return foiExecutada;
    }

    public void setFoiExecutada(boolean foiExecutada) {
        this.foiExecutada = foiExecutada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Coordenacao getCoordenacao() {
        return coordenacao;
    }

    public void setCoordenacao(Coordenacao coordenacao) {
        this.coordenacao = coordenacao;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }
}
