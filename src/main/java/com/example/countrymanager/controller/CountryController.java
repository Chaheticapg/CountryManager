package com.example.countrymanager.controller;

import com.example.countrymanager.entity.Country;
import com.example.countrymanager.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    private static final String AUTH_TOKEN = "myauthcode";

    private boolean isAuthorized(String token) {
        return AUTH_TOKEN.equals(token);
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries(@RequestHeader("Authorization") String token) {
        if (!isAuthorized(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (!isAuthorized(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<Country> country = countryService.findById(id);
        return country.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Country> createCountry(@RequestHeader("Authorization") String token, @RequestBody Country country) {
        if (!isAuthorized(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(countryService.save(country), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody Country country) {
        if (!isAuthorized(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!countryService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        country.setCountryId(id);
        return new ResponseEntity<>(countryService.save(country), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (!isAuthorized(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        countryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
