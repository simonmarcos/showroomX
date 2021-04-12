package com.SistemaGestion.ShowroomX.Controller;

import com.SistemaGestion.ShowroomX.Model.User;
import com.SistemaGestion.ShowroomX.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceImpl userImpl;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User userResponse = userImpl.save(user);

        ResponseEntity responseEntity = new ResponseEntity<>(userResponse, HttpStatus.OK);
        ResponseEntity responseEntityError = new ResponseEntity("No se pudo registrar por falta de datos o inconvenientes. Verifique.", HttpStatus.BAD_REQUEST);

        return userResponse != null ? responseEntity : responseEntityError;
    }
}
