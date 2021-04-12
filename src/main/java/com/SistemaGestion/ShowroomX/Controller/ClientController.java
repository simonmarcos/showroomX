package com.SistemaGestion.ShowroomX.Controller;

import com.SistemaGestion.ShowroomX.Model.Client;
import com.SistemaGestion.ShowroomX.Service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    private final ClientServiceImpl clientService;

    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/save")
    public ResponseEntity<Client> save(@RequestBody Client client) {

        ResponseEntity responseEntity_OK = new ResponseEntity(client, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity(client, HttpStatus.BAD_REQUEST);

        return clientService.save(client) != null ? responseEntity_OK : responseEntity_NOK;
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value = "/update")
    public ResponseEntity<Client> update(@RequestBody Client client) {

        ResponseEntity responseEntity_OK = new ResponseEntity(client, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity(client, HttpStatus.BAD_REQUEST);

        return clientService.update(client) != null ? responseEntity_OK : responseEntity_NOK;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Client>> findAll() {

        List<Client> listClientResponse = clientService.findAll();

        ResponseEntity responseEntity_OK = new ResponseEntity(listClientResponse, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity(listClientResponse, HttpStatus.BAD_REQUEST);

        return listClientResponse != null ? responseEntity_OK : responseEntity_NOK;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findAllPagebale", params = {"page", "size"})
    public ResponseEntity<List<Client>> findAllPageable(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size) {

        List<Client> listClientResponse = clientService.findAllPageable(PageRequest.of(page, size, Sort.by("date").ascending())).getContent();

        ResponseEntity responseEntity_OK = new ResponseEntity(listClientResponse, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity("No se encontraron resultados.", HttpStatus.BAD_REQUEST);


        return listClientResponse != null ? responseEntity_OK : responseEntity_NOK;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/deleteByIdClient/{idClient}")
    public ResponseEntity<Long> deleteByIdClient(@PathVariable(value = "idSales") Long idClient) {

        Long salesResponse = clientService.deleteByIdClient(idClient);

        ResponseEntity responseEntity_OK = new ResponseEntity(salesResponse, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity("No se encontraron resultados con el Id indicado.", HttpStatus.BAD_REQUEST);

        return salesResponse == 1 ? responseEntity_OK : responseEntity_NOK;
    }

}
