package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.models.Dummy;
import ar.edu.utn.frc.tup.lciii.services.DummyServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
Esta anotación es similar a @Controller, pero se utiliza específicamente para
controladores RESTful. Combina la anotación @Controller y @ResponseBody, lo que
significa que los métodos en un controlador anotado con @RestController devuelven
directamente los datos en lugar de ser resueltos a través de una vista.
 */
@RestController
/*
Se utiliza para mapear las solicitudes HTTP a métodos específicos en los controladores.
Permite definir las URL y los métodos HTTP (GET, POST, PUT, DELETE, etc.) que un controlador debe manejar.
 */
@RequestMapping("/dummies")
public class DummyController {

    /*
    Utilizada para realizar la inyección de dependencias en Spring.
    Se aplica a campos, constructores o métodos "setter" y permite
    que Spring resuelva e inyecte las dependencias necesarias automáticamente.
     */
    @Autowired
    private DummyServiceInterface dummyService;

    /*
    Esta anotación combina las anotaciones @RequestMapping y @RequestMethod.GET.
    Se utiliza para mapear una solicitud HTTP GET a un método específico en un controlador.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Dummy> getDummy(
            /*
            Utilizada para vincular variables de una URL con los parámetros de un método en un controlador.
            Permite acceder a valores dinámicos en la URL de una solicitud.
             */
            @PathVariable Long id) {
        return ResponseEntity.ok(dummyService.getDummy(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Dummy>> searchDummy(
            /*
            Utilizada para vincular atributos de la solicitud HTTP a los parámetros de
            un método en un controlador. Permite acceder a los atributos establecidos en la solicitud.
             */
            @RequestParam(value = "info", required = false)
            String info) {
        if(info != null) {
            return ResponseEntity.ok(dummyService.searchByInfo(info));
        } else {
            return ResponseEntity.ok(dummyService.getAllDummy());
        }

    }

    /*
    Esta anotación combina las anotaciones @RequestMapping y @RequestMethod.POST.
    Se utiliza para mapear una solicitud HTTP POST a un método específico en un controlador.
     */
    @PostMapping("")
    public ResponseEntity<Dummy> postDummy(

            /*
            Se utiliza en parámetros de métodos o argumentos de constructores para activar
            la validación de datos según las anotaciones de validación de Spring, como @NotNull, @Size, etc.
             */
            @Valid
            /*
            Esta anotación se utiliza para vincular el cuerpo de una solicitud HTTP
            con un objeto en un método de un controlador. Permite recibir y procesar
            datos enviados en el cuerpo de la solicitud, como en una solicitud POST o PUT.
             */
            @RequestBody Dummy dummy) {
        return ResponseEntity.ok(dummyService.createDummy(dummy));
    }

    /*
    Esta anotación combina las anotaciones @RequestMapping y @RequestMethod.PUT.
    Se utiliza para mapear una solicitud HTTP PUT a un método específico en un controlador.
     */
    @PutMapping("")
    public ResponseEntity<Dummy> putDummy(@RequestBody Dummy dummy) {
        return ResponseEntity.ok(dummyService.updateDummy(dummy));
    }

    /*
    Esta anotación combina las anotaciones @RequestMapping y @RequestMethod.DELETE.
    Se utiliza para mapear una solicitud HTTP DELETE a un método específico en un controlador.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Dummy> deleteDummy(
            /*
            Se utiliza para vincular los encabezados de una solicitud HTTP a los
            parámetros de un método en un controlador. Permite acceder a los
            valores de los encabezados de la solicitud.
             */
            @RequestHeader Map<String, String> headers,
            @PathVariable Long id) {
        return ResponseEntity.ok(dummyService.deleteDummy(id));
    }

    @PatchMapping()
    /*
    Esta anotación se utiliza para establecer el código de estado de
    la respuesta HTTP devuelta por un método en un controlador.
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Example of Response Status")
    public ResponseEntity<String> patchExample() {
        return ResponseEntity.ok("Prueba PATCH");
    }
}
