package br.com.alura.forum.repositorios;

import br.com.alura.forum.modelos.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso,Long> {
    Curso findByNome(String nome);
}
