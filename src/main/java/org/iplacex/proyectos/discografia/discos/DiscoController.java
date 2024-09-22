package org.iplacex.proyectos.discografia.discos;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepo;

    
    
    

    @PostMapping(
        value= "/disco",
        consumes= MediaType.APPLICATION_JSON_VALUE,
        produces=  MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> HandlePostDiscoRequest(@RequestBody Disco disco){
    Disco temp= discoRepo.insert(disco);
    return new ResponseEntity<>(temp, HttpStatus.CREATED);
    }

    @GetMapping(
        value = "/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> handleGetdiscosRequest() {
        List<Disco> discos = (List<Disco>) discoRepo.findAll();
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

    
    @SuppressWarnings("null")
    @GetMapping(
        value = "/disco/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> handleGetArtistaRequest(@PathVariable ("id") String id) {
        Optional<Disco> temp = discoRepo.findById(id);

        if(!temp.isPresent()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(temp.get(),null,HttpStatus.OK);
    }

    
    @GetMapping(
        value = "/artista/{id}/discos",
        produces = "application/json")

    public ResponseEntity<List<Disco>> handleGetDiscosByArtistaRequest(@PathVariable String id) {
        List<Disco> discos = (List<Disco>) discoRepo.findDiscosByIdArtista(id);
        return ResponseEntity.ok(discos);
    }
}

