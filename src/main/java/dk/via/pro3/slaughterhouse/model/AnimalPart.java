package dk.via.pro3.slaughterhouse.model;

public class AnimalPart {

  private int partId;
  private String type;
  private double weight;
  private int animalRegistrationNumber;

  public AnimalPart() {
  }

  public AnimalPart(
      int partId,
      String type,
      double weight,
      int animalRegistrationNumber) {
    this.partId = partId;
    this.type = type;
    this.weight = weight;
    this.animalRegistrationNumber = animalRegistrationNumber;
  }

  public int getPartId() {
    return partId;
  }

  public void setPartId(int partId) {
    this.partId = partId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public int getAnimalRegistrationNumber() {
    return animalRegistrationNumber;
  }

  public void setAnimalRegistrationNumber(int animalRegistrationNumber) {
    this.animalRegistrationNumber = animalRegistrationNumber;
  }
}