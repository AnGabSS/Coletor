package com.fiap.microsservice.coletor.resources;

import com.fiap.microsservice.coletor.entities.Coleta;
import com.fiap.microsservice.coletor.entities.DTO.ColetaDTO;
import com.fiap.microsservice.coletor.service.ColetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/coleta")
public class ColetaResource {

    @Autowired
    private ColetaService service;

    @PostMapping
    public ResponseEntity<Coleta> createColeta(@RequestBody ColetaDTO coletaDTO) {
        Coleta coleta = service.fromDTO(coletaDTO);
        service.insert(coleta);
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(coleta).toUri();
        //return ResponseEntity.created(uri).body(coleta);
        return ResponseEntity.ok(coleta);
    }

    @GetMapping
    public ResponseEntity<List<Coleta>> getColetaList() {
        return ResponseEntity.ok(service.getAllColeta());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coleta> getColetayId(@PathVariable long id) {
        return ResponseEntity.ok(service.getColeta(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coleta> update(@PathVariable long id, @RequestBody Coleta coleta) {
        return ResponseEntity.ok(service.update(id, coleta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
