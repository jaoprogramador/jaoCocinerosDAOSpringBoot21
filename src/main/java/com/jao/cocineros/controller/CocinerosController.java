package com.jao.cocineros.controller;

import com.jao.cocineros.dto.CocinerosRequest;
import com.jao.cocineros.dto.CocinerosResponse;
import com.jao.cocineros.service.ICocinerosService;
import com.jao.cocineros.utils.ManejoErrores;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cocineros")
@RequiredArgsConstructor
public class CocinerosController {

    private final ICocinerosService cocinerosService;

    // GET: Obtener todos
    @GetMapping
    public ResponseEntity<List<CocinerosResponse>> findAll() {
        List<CocinerosResponse> cocineros = cocinerosService.findAll();
        return ResponseEntity.ok(cocineros);
    }

    // GET: Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<CocinerosResponse> findById(@PathVariable Long id) {
        return cocinerosService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Crear un nuevo cocinero
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid CocinerosRequest request, BindingResult result) {
        //cocinerosService.save(request);
        //return ResponseEntity.status(HttpStatus.CREATED).build();
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ManejoErrores.procesar(result));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cocinerosService.save(request));

    }

    // PUT: Actualizar un cocinero existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CocinerosRequest request) {
        boolean updated = cocinerosService.update(id, request);
        if (updated) {
            return ResponseEntity.noContent().build(); // 204 No Content si se actualizó con éxito
        }
        return ResponseEntity.notFound().build(); // 404 Not Found si no existe el ID
    }

    // DELETE: Eliminar un cocinero
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = cocinerosService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content si se eliminó
        }
        return ResponseEntity.notFound().build(); // 404 Not Found si no existe el ID
    }
}