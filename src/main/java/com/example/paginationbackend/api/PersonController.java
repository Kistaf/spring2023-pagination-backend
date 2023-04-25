package com.example.paginationbackend.api;

import com.example.paginationbackend.Entity.Person;
import com.example.paginationbackend.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/people")
@CrossOrigin
public class PersonController {

    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @GetMapping
    public Page<Person> paginated(@RequestParam int page, @RequestParam int take, @RequestParam Optional<String> sortBy, @RequestParam Optional<String> dir) {
        PageRequest pageRequest;
        if (sortBy.isPresent() && dir.isPresent()) {
            String sortByStr = sortBy.get();
            String dirStr = dir.get();
            Sort sort = dirStr.equalsIgnoreCase("asc") ? Sort.by(sortByStr).ascending() : Sort.by(sortByStr).descending();
            pageRequest = PageRequest.of(page, take, sort);
        } else if (sortBy.isPresent()) {
            String sortByStr = sortBy.get();
            pageRequest = PageRequest.of(page, take, Sort.by(sortByStr));
        } else {
            pageRequest = PageRequest.of(page, take);
        }

        return personRepository.findAll(pageRequest);
    }

}
