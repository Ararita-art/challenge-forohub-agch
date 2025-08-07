package foro.hub.api.controller;

import foro.hub.api.domain.curso.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(
            @RequestBody @Valid DatosRegistroCurso datos,
            UriComponentsBuilder uriBuilder) {

        Curso curso = cursoService.registrarCurso(datos);
        URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosRespuestaCurso(curso));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaCurso>> listarCurso(
            @PageableDefault(size = 10, sort = "nombre") Pageable paginacion) {

        return ResponseEntity.ok(cursoService.listarCurso(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> obtenerCurso(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.obtenerCurso(id));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<Page<DatosListaCurso>> listarCursoCategoria(
            @PathVariable String categoria,
            @PageableDefault(size = 10, sort = "nombre") Pageable paginacion) {

        return ResponseEntity.ok(cursoService.listarCursoCategoria(categoria, paginacion));
    }
}