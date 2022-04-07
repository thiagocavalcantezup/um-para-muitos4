package br.com.zup.handora.umparamuitos4.controllers;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.handora.umparamuitos4.models.Artigo;
import br.com.zup.handora.umparamuitos4.models.ArtigoDTO;
import br.com.zup.handora.umparamuitos4.models.Blog;
import br.com.zup.handora.umparamuitos4.repositories.ArtigoRepository;
import br.com.zup.handora.umparamuitos4.repositories.BlogRepository;

@RestController
@RequestMapping(BlogController.BASE_URI + "/{blogId}" + ArtigoController.BASE_URI)
public class ArtigoController {

    public final static String BASE_URI = "/artigos";

    private final BlogRepository blogRepository;
    private final ArtigoRepository artigoRepository;

    public ArtigoController(BlogRepository blogRepository, ArtigoRepository artigoRepository) {
        this.blogRepository = blogRepository;
        this.artigoRepository = artigoRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> postMethodName(@PathVariable Long blogId,
                                               @RequestBody @Valid ArtigoDTO artigoDTO,
                                               UriComponentsBuilder uriComponentsBuilder) {
        Blog blog = blogRepository.findById(blogId)
                                  .orElseThrow(
                                      () -> new ResponseStatusException(
                                          HttpStatus.NOT_FOUND,
                                          "Não existe um blog com o ID informado."
                                      )
                                  );

        Artigo artigo = artigoDTO.toModel();
        blog.adicionar(artigo);
        artigo = artigoRepository.save(artigo);
        blogRepository.save(blog);

        URI location = uriComponentsBuilder.path(
            BlogController.BASE_URI + "/{blogId}" + BASE_URI + "{/id}"
        ).buildAndExpand(blogId, artigo.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
