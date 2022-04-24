package br.com.alura.forum.repositorios;

import br.com.alura.forum.modelos.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepositorio extends JpaRepository<Topico, Long> {

        List<Topico> findByCursoNome(String nomeCurso); //filtrando pelo atribura da classe Curso


        /**
         quando não seguimos o padrão do jpa (findBy) ele não consegue fazer a query, para isso temos que fazer na mão (JPQL)

         @Query("SELECT t FROM Topico  t WHERE t.curso.nome = :nomeCurso")
         List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso); /outro problema o spring não assume que o parametro de cima, é o de baixo
         /com isso temos que colocar o @Param("nomeCurso")
         **/

}
