package foro.hub.api.controller;

import foro.hub.api.domain.respuesta.DatosListaRespuesta;
import foro.hub.api.domain.respuesta.DatosRegistroRespuesta;
import foro.hub.api.domain.respuesta.DatosRespuestaConstruida;
import foro.hub.api.domain.respuesta.RespuestaService;
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
@RequestMapping("/respuestas")
@RequiredArgsConstructor
public class RespuestaController {

    private final RespuestaService respuestaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaConstruida> registrarRespuesta(
            @RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,
            UriComponentsBuilder uriBuilder) {

        DatosRespuestaConstruida respuesta = respuestaService.crearRespuesta(datosRegistroRespuesta);
        URI uri = uriBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.id()).toUri();

        return ResponseEntity.created(uri).body(respuesta);
    }

    @GetMapping("/topico/{topicoId}")
    public ResponseEntity<Page<DatosListaRespuesta>> listarPorTopico(
            @PathVariable Long topicoId,
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {

        return ResponseEntity.ok(respuestaService.listarPorTopico(topicoId, paginacion));
    }

    @PatchMapping("/{respuestaId}/topico/{topicoId}/solucion")
    @Transactional
    public ResponseEntity<DatosRespuestaConstruida> marcarSolucionado(
            @PathVariable Long respuestaId,
            @PathVariable Long topicoId) {

        return ResponseEntity.ok(respuestaService.marcarSolucionado(respuestaId, topicoId));
    }
}