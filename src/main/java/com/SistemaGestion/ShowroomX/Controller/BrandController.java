package com.SistemaGestion.ShowroomX.Controller;

import com.SistemaGestion.ShowroomX.Model.Brand;
import com.SistemaGestion.ShowroomX.Service.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    private final BrandServiceImpl dao;

    @Autowired
    public BrandController(BrandServiceImpl brandService) {
        this.dao = brandService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/save", consumes = {"application/json"})
    public ResponseEntity<Brand> save(@RequestBody Brand brand) {

        Brand brandResponse = dao.save(brand);

        ResponseEntity responseEntityError = new ResponseEntity("No se pudo registrar por falta de datos", HttpStatus.BAD_REQUEST);
        ResponseEntity responseEntity = new ResponseEntity<>(brandResponse, HttpStatus.OK);

        return brandResponse == null ? responseEntityError : responseEntity;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/update", consumes = {"application/json"})
    public ResponseEntity<Brand> update(@RequestBody Brand brand) {
        Brand brandResponse = dao.update(brand);

        ResponseEntity responseEntityError = new ResponseEntity("No se pudo actualizar por falta de datos", HttpStatus.BAD_REQUEST);
        ResponseEntity responseEntity = new ResponseEntity<>(brandResponse, HttpStatus.OK);

        return brandResponse == null ? responseEntityError : responseEntity;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/deleteById/{idBrand}")
    public ResponseEntity<Long> deleteById(@PathVariable("idBrand") long idBrand) {
        return new ResponseEntity<>(dao.deleteById(idBrand), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/deleteByName/{name}")
    public ResponseEntity<Long> deleteByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(dao.deleteByName(name), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Brand>> findAll() {
        ResponseEntity responseEntity = new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
        System.out.println(responseEntity.getStatusCode());
        return responseEntity;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findAllPageable")
    public ResponseEntity<List<Brand>> findAllPageable(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {

        List<Brand> listBrandResponse = dao.findAllPageable(PageRequest.of(page, size, Sort.by("name").ascending())).getContent();

        ResponseEntity responseEntity_OK = new ResponseEntity(listBrandResponse, HttpStatus.OK);
        ResponseEntity responseEntity_NOK = new ResponseEntity("No se encontraron resultados.", HttpStatus.BAD_REQUEST);

        return listBrandResponse != null ? responseEntity_OK : responseEntity_NOK;
    }


    //Find by Brand with stock equals to Zero
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findStockZero")
    public ResponseEntity<List<Brand>> findByStockZero() {
        return new ResponseEntity<>(dao.findByStockZero(), HttpStatus.OK);
    }
}
