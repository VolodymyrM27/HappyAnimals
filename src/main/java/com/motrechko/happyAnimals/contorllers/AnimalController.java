package com.motrechko.happyAnimals.contorllers;

import com.motrechko.happyAnimals.dto.AnimalDto;
import com.motrechko.happyAnimals.exception.EmployeeNotFoundException;
import com.motrechko.happyAnimals.service.impl.AnimalServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AnimalController {

    private final AnimalServiceImpl animalService;

    public AnimalController(AnimalServiceImpl animalService) {
        this.animalService = animalService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/animals")
    public List<AnimalDto> all(){
        return  animalService.getAll()
                .stream()
                .map(AnimalDto::fromAnimal)
                .collect(Collectors.toList());
    }



    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/animals", consumes="application/json")
    public AnimalDto newAnimal(@RequestBody AnimalDto animalDto){
        return AnimalDto.fromAnimal(
          animalService.add(AnimalDto.fromDto(animalDto))
        );
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/animals/{id}")
    public AnimalDto one(@PathVariable Long id) {
        return animalService.findById(id)
                .map(AnimalDto::fromAnimal)
                .orElseThrow( () -> new EmployeeNotFoundException(id));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/animals/{id}")
    public AnimalDto replaceEmployee(@RequestBody AnimalDto animalDto, @PathVariable Long id) {
        return AnimalDto.fromAnimal(
                animalService.replaceOrAdd(AnimalDto.fromDto(animalDto),id)
        );
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/animals/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        animalService.delete(id);
    }


}
