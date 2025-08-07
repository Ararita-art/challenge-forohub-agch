package foro.hub.api.controller;

import foro.hub.api.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    private final TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDescripcionTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {

        Topico topico = topicoService.crearTopico(datosRegistroTopico);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDescripcionTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopico(@PageableDefault(size = 10, sort = {"fecha"}, direction = Sort.Direction.DESC) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.enlistarTopico(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDescripcionTopico> describirTopico(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.detallarTopico(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosDescripcionTopico> actualizarTopico(@RequestBody @Valid DatosActualizacionTopico datosActualizacionTopico) {
        return ResponseEntity.ok(topicoService.renovarTopico(datosActualizacionTopico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.suprimirTopico(id);
        return ResponseEntity.noContent().build();
    }
}