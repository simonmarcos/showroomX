package com.SistemaGestion.ShowroomX.Controller;

import com.SistemaGestion.ShowroomX.Model.Purchases;
import com.SistemaGestion.ShowroomX.Service.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/purchases")
public class PurchasesController {

    private final PurchaseServiceImpl purchaseService;

    @Autowired
    public PurchasesController(PurchaseServiceImpl purchaseService) {
        this.purchaseService = purchaseService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/save")
    public ResponseEntity<Purchases> save(@RequestBody Purchases purchases) {

        ResponseEntity responseEntity_OK = new ResponseEntity(purchases, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity(purchases, HttpStatus.BAD_REQUEST);

        return purchaseService.save(purchases) != null ? responseEntity_OK : responseEntity_NOK;
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value = "/update")
    public ResponseEntity<Purchases> update(@RequestBody Purchases purchases) {

        ResponseEntity responseEntity_OK = new ResponseEntity(purchases, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity(purchases, HttpStatus.BAD_REQUEST);

        return purchaseService.update(purchases) != null ? responseEntity_OK : responseEntity_NOK;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Purchases>> findAll() {

        List<Purchases> listPurchasesResponse = purchaseService.findAll();

        ResponseEntity responseEntity_OK = new ResponseEntity(listPurchasesResponse, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity(listPurchasesResponse, HttpStatus.BAD_REQUEST);

        return listPurchasesResponse != null ? responseEntity_OK : responseEntity_NOK;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findAllPagebale", params = {"page", "size"})
    public ResponseEntity<List<Purchases>> findAllPageable(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size) {

        List<Purchases> listPurchasesResponse = purchaseService.findAllPageable(PageRequest.of(page, size, Sort.by("date").ascending())).getContent();

        ResponseEntity responseEntity_OK = new ResponseEntity(listPurchasesResponse, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity("No se encontraron resultados.", HttpStatus.BAD_REQUEST);


        return listPurchasesResponse != null ? responseEntity_OK : responseEntity_NOK;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/deleteByIdPurchases/{idPurchases}")
    public ResponseEntity<Long> deleteByIdPurchases(@PathVariable(value = "idSales") Long idPurchases) {

        Long salesResponse = purchaseService.deleteById(idPurchases);

        ResponseEntity responseEntity_OK = new ResponseEntity(salesResponse, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity("No se encontraron resultados con el Id indicado.", HttpStatus.BAD_REQUEST);

        return salesResponse == 1 ? responseEntity_OK : responseEntity_NOK;
    }
}
