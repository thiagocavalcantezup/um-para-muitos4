package br.com.zup.handora.umparamuitos4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.handora.umparamuitos4.models.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {

}
