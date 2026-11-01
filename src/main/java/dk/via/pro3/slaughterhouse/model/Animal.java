
package dk.via.pro3.slaughterhouse.model;

import java.time.LocalDate;

public class Animal {

  private int registrationNumber;
  private double weight;
  private LocalDate date;
  private String origin;

  public Animal() {
  }

  public Animal(int registrationNumber, double weight,
      LocalDate date, String origin) {
    this.registrationNumber = registrationNumber;
    this.weight = weight;
    this.date = date;
    this.origin = origin;
  }

  // Keeps compatibility with Part 2
  public Animal(int registrationNumber, double weight) {
    this.registrationNumber = registrationNumber;
    this.weight = weight;
  }

  public int getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(int registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }
}
