package com.fiap.mercadoexpress.repository;

import com.fiap.mercadoexpress.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
