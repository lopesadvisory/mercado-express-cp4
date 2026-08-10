package com.fiap.mercadoexpress.service;

import com.fiap.mercadoexpress.exception.ProdutoNaoEncontradoException;
import com.fiap.mercadoexpress.model.Produto;
import com.fiap.mercadoexpress.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    // buffer com os dados recebidos antes de serem persistidos (commit) no Oracle
    private final List<Produto> bufferEntrada = new ArrayList<>();

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    public Produto criar(Produto produto) {
        bufferEntrada.add(produto);
        Produto novoProduto = bufferEntrada.remove(bufferEntrada.size() - 1);
        return produtoRepository.save(novoProduto);
    }

    public Produto atualizar(Long id, Produto dados) {
        Produto produtoExistente = buscarPorId(id);

        bufferEntrada.add(dados);
        Produto dadosRecebidos = bufferEntrada.remove(bufferEntrada.size() - 1);

        produtoExistente.setNome(dadosRecebidos.getNome());
        produtoExistente.setTipo(dadosRecebidos.getTipo());
        produtoExistente.setSetor(dadosRecebidos.getSetor());
        produtoExistente.setTamanho(dadosRecebidos.getTamanho());
        produtoExistente.setPreco(dadosRecebidos.getPreco());

        return produtoRepository.save(produtoExistente);
    }

    public Produto atualizarParcial(Long id, Produto dados) {
        Produto produtoExistente = buscarPorId(id);

        bufferEntrada.add(dados);
        Produto dadosRecebidos = bufferEntrada.remove(bufferEntrada.size() - 1);

        if (dadosRecebidos.getNome() != null) {
            produtoExistente.setNome(dadosRecebidos.getNome());
        }
        if (dadosRecebidos.getTipo() != null) {
            produtoExistente.setTipo(dadosRecebidos.getTipo());
        }
        if (dadosRecebidos.getSetor() != null) {
            produtoExistente.setSetor(dadosRecebidos.getSetor());
        }
        if (dadosRecebidos.getTamanho() != null) {
            produtoExistente.setTamanho(dadosRecebidos.getTamanho());
        }
        if (dadosRecebidos.getPreco() != null) {
            produtoExistente.setPreco(dadosRecebidos.getPreco());
        }

        return produtoRepository.save(produtoExistente);
    }

    public void deletar(Long id) {
        Produto produtoExistente = buscarPorId(id);
        produtoRepository.delete(produtoExistente);
    }

}
