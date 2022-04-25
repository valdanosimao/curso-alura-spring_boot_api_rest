package br.com.alura.forum.controladores;

import br.com.alura.forum.dto.DetalhesDoTopicoDTO;
import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.form.TopicoForm;
import br.com.alura.forum.form.AtualizacaoTopicoForm;
import br.com.alura.forum.modelos.Topico;
import br.com.alura.forum.repositorios.CursoRepositorio;
import br.com.alura.forum.repositorios.TopicoRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoControlador {

    private final TopicoRepositorio topicoRepositorio;
    private final CursoRepositorio cursoRepositorio;

    public TopicoControlador(TopicoRepositorio topicoRepositorio, CursoRepositorio cursoRepositorio) {
        this.topicoRepositorio = topicoRepositorio;
        this.cursoRepositorio = cursoRepositorio;
    }

//    @GetMapping
//    public List<TopicoDTO> lista(){
//        List<Topico> topicos = topicoRepositorio.findAll();
//        return TopicoDTO.converter(topicos);
//    }

    //outra forma de buscar topicos, em duas situações, caso nulo, é a mesma coisa de cima, caso com filtro ele faz o filtro por nome
    @GetMapping
    public List<TopicoDTO> lista(String nomeCurso){
        if (nomeCurso == null){
            List<Topico> topicos = topicoRepositorio.findAll();
            return TopicoDTO.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepositorio.findByCursoNome(nomeCurso);
            return TopicoDTO.converter(topicos);
        }

    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder){
        Topico topico = form.converter(cursoRepositorio);
        topicoRepositorio.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDTO> detalhar(@PathVariable Long id){
       Optional<Topico> topico = topicoRepositorio.findById(id);
       if (topico.isPresent()){
           return ResponseEntity.ok(new DetalhesDoTopicoDTO(topico.get()));
       }

       return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){

        Optional<Topico> optional = topicoRepositorio.findById(id);
        if (optional.isPresent()){
            Topico topico = form.atualizar(id,topicoRepositorio);
            return ResponseEntity.ok(new TopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover (@PathVariable Long id){
        Optional<Topico> optional = topicoRepositorio.findById(id);
        if (optional.isPresent()){
            topicoRepositorio.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
