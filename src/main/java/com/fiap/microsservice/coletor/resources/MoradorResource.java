package com.fiap.microsservice.coletor.resources;

import com.fiap.microsservice.coletor.entities.Morador;
import com.fiap.microsservice.coletor.service.MoradorService;
import com.fiap.microsservice.coletor.service.RotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/morador")
public class MoradorResource {

    @Autowired
    private MoradorService service;

    @PostMapping
    public ResponseEntity<Morador> create(@RequestBody Morador morador) {
        service.insert(morador);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(morador.getId()).toUri();
        return ResponseEntity.created(uri).body(morador);
    }

    @GetMapping
    public ResponseEntity<List<Morador>> getMoradorList() {
        return ResponseEntity.ok(service.getAllMorador());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Morador> getMoradorById(@PathVariable long id) {
        return ResponseEntity.ok(service.getMorador(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Morador> update(@PathVariable long id, @RequestBody Morador morador) {
        return ResponseEntity.ok(service.update(id, morador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
