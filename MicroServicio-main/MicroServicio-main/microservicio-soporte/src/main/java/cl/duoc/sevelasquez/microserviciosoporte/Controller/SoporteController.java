package cl.duoc.sevelasquez.microserviciosoporte.Controller;

import cl.duoc.sevelasquez.microserviciosoporte.DTO.EstadoDTO;
import cl.duoc.sevelasquez.microserviciosoporte.DTO.SoporteRequestDTO;
import cl.duoc.sevelasquez.microserviciosoporte.Model.Soporte;
import cl.duoc.sevelasquez.microserviciosoporte.Service.SoporteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @PostMapping
    public Soporte crear(@RequestBody @Valid SoporteRequestDTO dto) {
        Soporte soporte = new Soporte();
        soporte.setDescripcion(dto.getDescripcion());
        soporte.setUsuarioId(dto.getUsuarioId());
        soporte.setPrioridad(dto.getPrioridad());
        soporte.setFechaCreacion(LocalDateTime.now());
        soporte.setEstado("PENDIENTE");

        // Se pasa el objeto Soporte al servicio
        return soporteService.crear(soporte);
    }

    @GetMapping
    public List<Soporte> obtenerTodas() {
        return soporteService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Optional<Soporte> obtenerPorId(@PathVariable Long id) {
        return soporteService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        soporteService.eliminar(id);
        return ResponseEntity.ok("Soporte eliminado");
    }

    // SOLO EL ENCARGADO puede modificar el estado
    @PutMapping("/{id}/estado")
    public Soporte actualizarEstado(@PathVariable Long id, @RequestBody EstadoDTO dto) {
        return soporteService.actualizarEstado(id, dto.getEstado());
    }

}
