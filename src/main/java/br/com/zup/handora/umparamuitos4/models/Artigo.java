package br.com.zup.handora.umparamuitos4.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "artigos")
public class Artigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false)
    @Lob
    private String corpo;

    @Column(nullable = false)
    private TipoArtigo tipo;

    @ManyToOne(optional = false)
    private Blog blog;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Artigo() {}

    public Artigo(String titulo, String corpo, TipoArtigo tipo) {
        this.titulo = titulo;
        this.corpo = corpo;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

}
