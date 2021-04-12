package com.SistemaGestion.ShowroomX.Controller;

import com.SistemaGestion.ShowroomX.Model.Provider;
import com.SistemaGestion.ShowroomX.Service.ProviderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/provider")
public class ProviderController {

    private final ProviderServiceImpl dao;

    @Autowired
    public ProviderController(ProviderServiceImpl providerService) {
        this.dao = providerService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Provider> save(@RequestBody Provider provider) {
        Provider providerResponse = dao.save(provider);

        ResponseEntity responseEntity = new ResponseEntity<>(providerResponse, HttpStatus.OK);
        ResponseEntity responseEntityError = new ResponseEntity("No se pudo registrar por falta de datos. Verifique.", HttpStatus.BAD_REQUEST);

        return providerResponse != null ? responseEntity : responseEntityError;
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value = "/update")
    public ResponseEntity<Provider> update(@RequestBody Provider provider) {
        Provider providerResponse = dao.update(provider);

        ResponseEntity responseEntity = new ResponseEntity<>(providerResponse, HttpStatus.OK);
        ResponseEntity responseEntityError = new ResponseEntity("No se pudo registrar por falta de datos. Verifique.", HttpStatus.BAD_REQUEST);

        return providerResponse != null ? new ResponseEntity<>(providerResponse, HttpStatus.OK) : responseEntityError;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Provider>> findAll() {
        return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findAllPageable")
    public ResponseEntity<List<Provider>> findAllPageable(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(dao.findAllPageable(pageable).getContent(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/deleteByIdProvider/{idProvider}")
    public ResponseEntity<Long> deleteByIdProvider(@PathVariable Long idProvider) {
        Long response = dao.deleteByIdProvider(idProvider);

        ResponseEntity responseEntityOK = new ResponseEntity<>(response, HttpStatus.OK);
        ResponseEntity responseEntityError = new ResponseEntity("Se eliminó correctamente el Proveedor", HttpStatus.BAD_REQUEST);

        return response == 1 ? responseEntityOK : responseEntityError;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/deleteByNameProvider/{nameProvider}")
    public ResponseEntity<Long> deleteByNameProvider(@PathVariable String nameProvider) {
        Long response = dao.deleteByName(nameProvider);

        ResponseEntity responseEntityOK = new ResponseEntity<>(response, HttpStatus.OK);
        ResponseEntity responseEntityError = new ResponseEntity(response, HttpStatus.BAD_REQUEST);

        return response == 1 ? responseEntityOK : responseEntityError;
    }
}
