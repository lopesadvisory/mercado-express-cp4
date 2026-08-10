package com.fiap.mercadoexpress.controller;

import com.fiap.mercadoexpress.assembler.ProdutoModelAssembler;
import com.fiap.mercadoexpress.model.Produto;
import com.fiap.mercadoexpress.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/mercado")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoModelAssembler assembler;

    public ProdutoController(ProdutoService produtoService, ProdutoModelAssembler assembler) {
        this.produtoService = produtoService;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Produto>>> listarTodos() {
        List<EntityModel<Produto>> produtos = produtoService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Produto>> collectionModel = CollectionModel.of(produtos,
                linkTo(methodOn(ProdutoController.class).listarTodos()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Produto>> buscarPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(assembler.toModel(produto));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Produto>> criar(@RequestBody @Valid Produto produto) {
        Produto produtoCriado = produtoService.criar(produto);
        EntityModel<Produto> model = assembler.toModel(produtoCriado);

        return ResponseEntity
                .created(model.getRequiredLink("self").toUri())
                .body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Produto>> atualizar(@PathVariable Long id, @RequestBody @Valid Produto produto) {
        Produto produtoAtualizado = produtoService.atualizar(id, produto);
        return ResponseEntity.ok(assembler.toModel(produtoAtualizado));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<Produto>> atualizarParcial(@PathVariable Long id, @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.atualizarParcial(id, produto);
        return ResponseEntity.ok(assembler.toModel(produtoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
