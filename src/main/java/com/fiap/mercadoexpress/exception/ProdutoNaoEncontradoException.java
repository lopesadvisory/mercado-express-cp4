package com.fiap.mercadoexpress.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException(Long id) {
        super("Produto não encontrado para o id: " + id);
    }

}
