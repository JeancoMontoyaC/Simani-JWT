package com.sergio.jwt.backend.PetController;

import com.sergio.jwt.backend.Pet.Pet;
import com.sergio.jwt.backend.PetService.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
@AllArgsConstructor

public class PetController {


    private PetService petService;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Pet pet) {
        return ResponseEntity.ok(petService.save(pet));
    }
    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(petService.findAll());
    }
    @GetMapping("/without")
    public ResponseEntity<?> listAllWithout() {
        return ResponseEntity.ok(petService.findAllWithout());
    }
}



