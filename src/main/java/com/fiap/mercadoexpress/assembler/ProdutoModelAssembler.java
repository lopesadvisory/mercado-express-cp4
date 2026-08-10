package com.fiap.mercadoexpress.assembler;

import com.fiap.mercadoexpress.controller.ProdutoController;
import com.fiap.mercadoexpress.model.Produto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProdutoModelAssembler implements RepresentationModelAssembler<Produto, EntityModel<Produto>> {

    @Override
    public EntityModel<Produto> toModel(Produto produto) {
        return EntityModel.of(produto,
                linkTo(methodOn(ProdutoController.class).buscarPorId(produto.getId())).withSelfRel(),
                linkTo(methodOn(ProdutoController.class).listarTodos()).withRel("mercado"),
                linkTo(methodOn(ProdutoController.class).atualizar(produto.getId(), null)).withRel("atualizar"),
                linkTo(methodOn(ProdutoController.class).atualizarParcial(produto.getId(), null)).withRel("atualizar-parcial"),
                linkTo(methodOn(ProdutoController.class).deletar(produto.getId())).withRel("deletar")
        );
    }

}
