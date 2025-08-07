package foro.hub.api.infrastructure.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorDeErrores {

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity gestionarError404() {
            return ResponseEntity.notFound().build();
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity gestionarError400(MethodArgumentNotValidException exception){
            var errores = exception.getFieldErrors();
            return ResponseEntity.badRequest().body(errores.stream().map(DatosErrorValidacion::new).toList());
        }

        @ExceptionHandler(ValidacionExcepcion.class)
        public ResponseEntity gestionarErrorDeValidacion(ValidacionExcepcion excepcion){
            return ResponseEntity.badRequest().body(excepcion.getMessage());
        }

        public record DatosErrorValidacion(String campo, String mensaje){
            public DatosErrorValidacion(FieldError fieldError){
                this(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                );
            }
        }
}