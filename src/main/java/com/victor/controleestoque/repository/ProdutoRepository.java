package com.victor.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.victor.controleestoque.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
