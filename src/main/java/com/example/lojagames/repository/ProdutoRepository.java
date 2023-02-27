package com.example.lojagames.repository;

import com.example.lojagames.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findAllByDescricaoontainingIgnoreCase(@Param("descricao") String nome);
}
