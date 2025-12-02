package com.victor.controleestoque.controller;

import com.victor.controleestoque.model.Produto;
import com.victor.controleestoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;


    // ===============================
    // 1) GET - Listar todos os produtos
    // ===============================
    @GetMapping
    public List<Produto> listarTodos() {
        return repository.findAll();
    }


    // ===============================
    // 2) GET - Buscar por ID
    // ===============================
    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }


    // ===============================
    // 3) POST - Criar novo produto
    // ===============================
    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return repository.save(produto);
    }


    // ===============================
    // 4) PUT - Atualizar tudo (substitui tudo)
    // ===============================
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto novo) {

        Optional<Produto> existente = repository.findById(id);

        if (existente.isPresent()) {
            Produto p = existente.get();

            // PUT = substitui TODOS os campos
            p.setCodigo(novo.getCodigo());
            p.setDescricao(novo.getDescricao());
            p.setQuantidade(novo.getQuantidade());
            p.setMedida(novo.getMedida());
            p.setObservacao(novo.getObservacao());

            return repository.save(p);
        }

        return null; // ou lançar exception
    }


    // ===============================
    // 5) PATCH - Atualizar parcialmente
    // ===============================
    @PatchMapping("/{id}")
    public Produto atualizarParcial(@PathVariable Long id, @RequestBody Produto novo) {

        Optional<Produto> existente = repository.findById(id);

        if (existente.isPresent()) {
            Produto p = existente.get();

            // Atualiza apenas o que foi enviado
            if (novo.getCodigo() != null) p.setCodigo(novo.getCodigo());
            if (novo.getDescricao() != null) p.setDescricao(novo.getDescricao());
            if (novo.getQuantidade() != null) p.setQuantidade(novo.getQuantidade());
            if (novo.getMedida() != null) p.setMedida(novo.getMedida());
            if (novo.getObservacao() != null) p.setObservacao(novo.getObservacao());

            return repository.save(p);
        }

        return null;
    }


    // ===============================
    // 6) DELETE - Apagar produto
    // ===============================
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Produto removido com sucesso!";
        }

        return "Produto não encontrado!";
    }
}
