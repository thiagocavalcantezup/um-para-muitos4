package br.com.zup.handora.umparamuitos4.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.handora.umparamuitos4.models.Blog;
import br.com.zup.handora.umparamuitos4.models.BlogDTO;
import br.com.zup.handora.umparamuitos4.repositories.BlogRepository;

@RestController
@RequestMapping(BlogController.BASE_URI)
public class BlogController {

    public final static String BASE_URI = "/blogs";

    private final BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid BlogDTO blogDTO,
                                       UriComponentsBuilder uriComponentsBuilder) {
        Blog blog = blogRepository.save(blogDTO.toModel());

        URI location = uriComponentsBuilder.path(BASE_URI + "/{id}")
                                           .buildAndExpand(blog.getId())
                                           .toUri();

        return ResponseEntity.created(location).build();
    }

}
