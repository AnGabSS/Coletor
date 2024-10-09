package com.fiap.microsservice.coletor.resources;

import com.fiap.microsservice.coletor.entities.Caminhao;
import com.fiap.microsservice.coletor.service.CaminhaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/caminhao")
public class CaminhaoResource {

    @Autowired
    private CaminhaoService service;

    @PostMapping
    public ResponseEntity<Caminhao> createCaminhao(@RequestBody Caminhao caminhao) {
        service.insert(caminhao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(caminhao.getId()).toUri();
        return ResponseEntity.created(uri).body(caminhao);
    }

    @GetMapping
    public ResponseEntity<List<Caminhao>> getCaminhaoList() {
        return ResponseEntity.ok(service.getAllCaminhao());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caminhao> getCaminhaoById(@PathVariable long id) {
        return ResponseEntity.ok(service.getCaminhao(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caminhao> updateCaminhado(@PathVariable long id, @RequestBody Caminhao caminhao) {
        return ResponseEntity.ok(service.update(id, caminhao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaminhao(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
