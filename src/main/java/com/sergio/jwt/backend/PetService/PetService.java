package com.sergio.jwt.backend.PetService;

import com.sergio.jwt.backend.Pet.Pet;
import com.sergio.jwt.backend.PetRepository.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetService {

    private PetRepository petRepository;
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public  List<Pet> findAllWithout(){
        return petRepository.findAllWithout();
    }

}
