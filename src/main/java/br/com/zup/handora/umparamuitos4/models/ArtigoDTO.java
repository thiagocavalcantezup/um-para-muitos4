package br.com.zup.handora.umparamuitos4.models;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ArtigoDTO {

    @NotBlank
    @Size(max = 200)
    private String titulo;

    @NotBlank
    @Size(max = 10000)
    private String corpo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoArtigo tipo;

    public ArtigoDTO(@NotBlank @Size(max = 200) String titulo,
                     @NotBlank @Size(max = 10000) String corpo, @NotNull TipoArtigo tipo) {
        this.titulo = titulo;
        this.corpo = corpo;
        this.tipo = tipo;
    }

    public Artigo toModel() {
        return new Artigo(titulo, corpo, tipo);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public TipoArtigo getTipo() {
        return tipo;
    }

}
