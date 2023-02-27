package com.generation.lojagames.controller;

import com.generation.lojagames.model.Produto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.generation.lojagames.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;


    @GetMapping
    public ResponseEntity<List<Produto>> getAll(){
        return  ResponseEntity.ok(produtoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto){
        return  ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id){
        return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping("/produto/{produto}")
    public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String descricao){
        return  ResponseEntity.ok(produtoRepository.findAllByDescricaoontainingIgnoreCase(descricao));
    }
    @PutMapping
    public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
        return  produtoRepository.findById(produto.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(produtoRepository.save(produto))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .build());
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        produtoRepository.deleteById(id);
    }
}
