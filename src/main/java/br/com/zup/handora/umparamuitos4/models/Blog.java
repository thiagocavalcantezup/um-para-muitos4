package br.com.zup.handora.umparamuitos4.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "blog")
    private Set<Artigo> artigos = new HashSet<>();

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Blog() {}

    public Blog(String nome) {
        this.nome = nome;
    }

    public void adicionar(Artigo artigo) {
        artigo.setBlog(this);
        this.artigos.add(artigo);
    }

    public Long getId() {
        return id;
    }

    public Set<Artigo> getArtigos() {
        return artigos;
    }

}
