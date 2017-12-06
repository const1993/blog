package by.owm.rest.controller;

import by.owm.domain.entity.Customer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@RestController
@RequestMapping(value = "/api")
public class WebController {

    private Map<Integer, Customer> customers = Stream.of(
            new Customer(1, "Mary", "Taylor", 24),
            new Customer(2, "Peter", "Smith", 18),
            new Customer(3, "Lauren", "Taylor", 31)
    ).collect(toMap(Customer::getId, identity()));


    @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAll() {
        return customers.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(toList());
    }

    @GetMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomer(@PathVariable int id) {
        return this.customers.get(id);
    }
}