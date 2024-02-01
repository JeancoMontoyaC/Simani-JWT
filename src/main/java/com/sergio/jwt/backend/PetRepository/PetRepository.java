package com.sergio.jwt.backend.PetRepository;


import com.sergio.jwt.backend.Pet.Pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query("SELECT new Pet(p.id, p.name, p.birthday) FROM Pet p")
    List<Pet> findAllWithout();


}
