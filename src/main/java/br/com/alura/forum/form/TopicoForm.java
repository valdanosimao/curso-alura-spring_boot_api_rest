package br.com.alura.forum.form;

import br.com.alura.forum.modelos.Curso;
import br.com.alura.forum.modelos.Topico;
import br.com.alura.forum.repositorios.CursoRepositorio;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TopicoForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String titulo;
    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;
    @NotNull @NotEmpty
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepositorio cursoRepositorio) {
        Curso curso = cursoRepositorio.findByNome(nomeCurso);
        return new Topico(titulo,mensagem,curso);
    }
}
