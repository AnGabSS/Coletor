package com.fiap.microsservice.coletor.resources;

import com.fiap.microsservice.coletor.entities.Rota;
import com.fiap.microsservice.coletor.service.CaminhaoService;
import com.fiap.microsservice.coletor.service.RotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rota")
public class RotaResource {

    @Autowired
    private RotaService service;

    @PostMapping
    public ResponseEntity<Rota> create(@RequestBody Rota rota) {
        service.insert(rota);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rota.getId()).toUri();
        return ResponseEntity.created(uri).body(rota);
    }

    @GetMapping
    public ResponseEntity<List<Rota>> getRotaList() {
        return ResponseEntity.ok(service.getAllRota());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rota> getRotaById(@PathVariable long id) {
        return ResponseEntity.ok(service.getRota(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rota> update(@PathVariable long id, @RequestBody Rota rota) {
        return ResponseEntity.ok(service.update(id, rota));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
