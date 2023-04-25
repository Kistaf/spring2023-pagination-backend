package com.example.paginationbackend.config;

import com.example.paginationbackend.entity.Person;
import com.example.paginationbackend.repository.PersonRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.IntStream;


@Controller
public class DeveloperData implements ApplicationRunner {

    private PersonRepository personRepository;

    public DeveloperData(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Person> people = IntStream
                .rangeClosed(1, 100)
                .mapToObj(i ->
                        Person.builder()
                        .name("Test" + new Random().nextInt(1000))
                        .age(new Random().nextInt(200))
                        .build())
                .toList();

        personRepository.saveAll(people);
    }

}
