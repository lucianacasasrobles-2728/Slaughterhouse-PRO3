
package dk.via.pro3.slaughterhouse.service;

import dk.via.pro3.slaughterhouse.model.Animal;
import dk.via.pro3.slaughterhouse.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

  private final AnimalRepository animalRepository;

  public AnimalService(AnimalRepository animalRepository) {
    this.animalRepository = animalRepository;
  }

  public void registerAnimal(Animal animal) {
    if (animal.getRegistrationNumber() <= 0) {
      throw new IllegalArgumentException(
          "Registration number must be positive"
      );
    }

    if (animal.getWeight() <= 0) {
      throw new IllegalArgumentException(
          "Weight must be positive"
      );
    }

    if (animal.getDate() == null) {
      throw new IllegalArgumentException(
          "Date is required"
      );
    }

    if (animal.getOrigin() == null ||
        animal.getOrigin().isBlank()) {
      throw new IllegalArgumentException(
          "Origin is required"
      );
    }

    animalRepository.saveAnimal(animal);
  }

  public Optional<Animal> getAnimal(int registrationNumber) {
    return animalRepository.findByRegistrationNumber(
        registrationNumber
    );
  }

  public List<Animal> getAnimalsByDate(LocalDate date) {
    return animalRepository.findByDate(date);
  }

  public List<Animal> getAnimalsByOrigin(String origin) {
    return animalRepository.findByOrigin(origin);
  }
}
