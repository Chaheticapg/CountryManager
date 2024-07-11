package com.example.countrymanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;

    private String countryName;
    private String nationalLanguage;
    private Long population;
    private LocalDate dbUpdateDate;
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getNationalLanguage() {
		return nationalLanguage;
	}
	public void setNationalLanguage(String nationalLanguage) {
		this.nationalLanguage = nationalLanguage;
	}
	public Long getPopulation() {
		return population;
	}
	public void setPopulation(Long population) {
		this.population = population;
	}
	public LocalDate getDbUpdateDate() {
		return dbUpdateDate;
	}
	public void setDbUpdateDate(LocalDate dbUpdateDate) {
		this.dbUpdateDate = dbUpdateDate;
	}

    // Getters and Setters
}
