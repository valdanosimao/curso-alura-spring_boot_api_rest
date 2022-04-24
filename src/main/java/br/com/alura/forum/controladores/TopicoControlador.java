package br.com.alura.forum.controladores;

import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.modelos.Topico;
import br.com.alura.forum.repositorios.TopicoRepositorio;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicoControlador {

    private final TopicoRepositorio topicoRepositorio;

    public TopicoControlador(TopicoRepositorio topicoRepositorio) {
        this.topicoRepositorio = topicoRepositorio;
    }

    @RequestMapping("/topicos")
    public List<TopicoDTO> lista(){
        List<Topico> topicos = topicoRepositorio.findAll();
        return TopicoDTO.converter(topicos);
    }

    //outra forma de buscar topicos, em duas situações, caso nulo, é a mesma coisa de cima, caso com filtro ele faz o filtro por nome
    @RequestMapping("/topicosPorFiltro")
    public List<TopicoDTO> lista(String nomeCurso){
        if (nomeCurso == null){
            List<Topico> topicos = topicoRepositorio.findAll();
            return TopicoDTO.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepositorio.findByCursoNome(nomeCurso);
            return TopicoDTO.converter(topicos);
        }

    }

}
