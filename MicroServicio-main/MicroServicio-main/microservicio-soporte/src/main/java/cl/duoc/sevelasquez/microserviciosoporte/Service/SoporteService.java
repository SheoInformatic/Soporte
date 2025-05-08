package cl.duoc.sevelasquez.microserviciosoporte.Service;

import cl.duoc.sevelasquez.microserviciosoporte.DTO.UsuarioDTO;
import cl.duoc.sevelasquez.microserviciosoporte.Model.Soporte;
import cl.duoc.sevelasquez.microserviciosoporte.Repository.SoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    public List<Soporte> obtenerTodas() {
        return soporteRepository.findAll();
    }

    public Optional<Soporte> obtenerPorId(Long id) {
        return soporteRepository.findById(id);
    }

    public Soporte crear(Soporte soporte) {
        return soporteRepository.save(soporte);
    }

    public void eliminar(Long id) {
        soporteRepository.deleteById(id);
    }

    public void actualizarEstado(Long id, String nuevoEstado) {
        Soporte soporte = soporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soporte no encontrado"));
        soporte.setEstado(nuevoEstado);
        soporteRepository.save(soporte);
    }
    @Autowired
    private RestTemplate restTemplate;

    public UsuarioDTO obtenerDatosUsuario(Long usuarioId) {
        String url = "http://localhost:8081/api/usuarios/" + usuarioId;
        return restTemplate.getForObject(url, UsuarioDTO.class);
    }




}
