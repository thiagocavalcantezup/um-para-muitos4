package br.com.zup.handora.umparamuitos4.models;

import javax.validation.constraints.NotBlank;

public class BlogDTO {

    @NotBlank
    private String nome;

    public BlogDTO(@NotBlank String nome) {
        this.nome = nome;
    }

    public Blog toModel() {
        return new Blog(nome);
    }

    public String getNome() {
        return nome;
    }

}
