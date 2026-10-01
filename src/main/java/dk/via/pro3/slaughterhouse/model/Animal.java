package dk.via.pro3.slaughterhouse.model;

public class Animal {

  private int registrationNumber;
  private double weight;

  public Animal(int registrationNumber, double weight) {
    this.registrationNumber = registrationNumber;
    this.weight = weight;
  }

  public int getRegistrationNumber() {
    return registrationNumber;
  }

  public double getWeight() {
    return weight;
  }

  public void setRegistrationNumber(int registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }
}