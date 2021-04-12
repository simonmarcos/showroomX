package com.SistemaGestion.ShowroomX.Controller;

import com.SistemaGestion.ShowroomX.Model.Sales;
import com.SistemaGestion.ShowroomX.Service.SalesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SalesController {

    private final SalesServiceImpl salesService;

    @Autowired
    public SalesController(SalesServiceImpl salesService) {
        this.salesService = salesService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/save")
    public ResponseEntity<Sales> save(@RequestBody Sales sales) {

        ResponseEntity responseEntity_OK = new ResponseEntity(sales, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity(sales, HttpStatus.BAD_REQUEST);

        return salesService.save(sales) != null ? responseEntity_OK : responseEntity_NOK;
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value = "/update")
    public ResponseEntity<Sales> update(@RequestBody Sales sale) {

        ResponseEntity responseEntity_OK = new ResponseEntity(sale, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity(sale, HttpStatus.BAD_REQUEST);

        return salesService.update(sale) != null ? responseEntity_OK : responseEntity_NOK;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Sales>> findAll() {

        List<Sales> listSalesResponse = salesService.findAll();

        ResponseEntity responseEntity_OK = new ResponseEntity(listSalesResponse, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity("Error de la solicitud.", HttpStatus.BAD_REQUEST);

        return listSalesResponse != null ? responseEntity_OK : responseEntity_NOK;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findByClient/{idClient}")
    public ResponseEntity<List<Sales>> findByClient(@PathVariable Long idClient) {

        List<Sales> salesResponse = salesService.findByClient(idClient);

        ResponseEntity responseEntity_OK = new ResponseEntity(salesResponse, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity("No se encontraron resultados.", HttpStatus.BAD_REQUEST);
        return salesResponse != null ? responseEntity_OK : responseEntity_NOK;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findAllPagebale", params = {"page", "size"})
    public ResponseEntity<List<Sales>> findAllPageable(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size) {

        List<Sales> listSalesResponse = salesService.findAllPageable(PageRequest.of(page, size, Sort.by("date").ascending())).getContent();

        ResponseEntity responseEntity_OK = new ResponseEntity(listSalesResponse, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity("No se encontraron resultados.", HttpStatus.BAD_REQUEST);


        return listSalesResponse != null ? responseEntity_OK : responseEntity_NOK;
    }


    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findByDate/{date}")
    public ResponseEntity<List<Sales>> findByClient(@PathVariable String date) {

        List<Sales> salesResponse = salesService.findByDate(date);

        ResponseEntity responseEntity_OK = new ResponseEntity(salesResponse, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity("No se encontraron resultados con la fecha indicada.", HttpStatus.BAD_REQUEST);

        return salesResponse != null ? responseEntity_OK : responseEntity_NOK;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findByPaymentTpye/{paymentType}")
    public ResponseEntity<List<Sales>> findByPaymentTpye(@PathVariable String paymentType) {

        List<Sales> salesResponse = salesService.findByPaymentTpye(paymentType);

        ResponseEntity responseEntity_OK = new ResponseEntity(salesResponse, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity("No se encontraron resultados con el tipo de pago indicado.", HttpStatus.BAD_REQUEST);

        return salesResponse != null ? responseEntity_OK : responseEntity_NOK;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/deleteByIdClient/{idSales}")
    public ResponseEntity<Long> deleteByIdSales(@PathVariable(value = "idSales") Long idSales) {

        Long salesResponse = salesService.deleteByIdSales(idSales);

        ResponseEntity responseEntity_OK = new ResponseEntity(salesResponse, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity("No se encontraron resultados con el Id indicado.", HttpStatus.BAD_REQUEST);

        return salesResponse == 1 ? responseEntity_OK : responseEntity_NOK;
    }

}
