package dk.via.pro3.slaughterhouse.service;

import dk.via.pro3.slaughterhouse.model.AnimalPart;
import dk.via.pro3.slaughterhouse.model.Tray;
import dk.via.pro3.slaughterhouse.repository.AnimalPartRepository;
import dk.via.pro3.slaughterhouse.repository.TrayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuttingService {

  private final AnimalPartRepository animalPartRepository;
  private final TrayRepository trayRepository;

  public CuttingService(
      AnimalPartRepository animalPartRepository,
      TrayRepository trayRepository) {
    this.animalPartRepository = animalPartRepository;
    this.trayRepository = trayRepository;
  }

  public void registerAnimalPart(AnimalPart animalPart) {
    if (animalPart.getType() == null || animalPart.getType().isBlank()) {
      throw new IllegalArgumentException("Part type is required");
    }

    if (animalPart.getWeight() <= 0) {
      throw new IllegalArgumentException("Part weight must be positive");
    }

    if (animalPart.getAnimalRegistrationNumber() <= 0) {
      throw new IllegalArgumentException(
          "Animal registration number must be positive"
      );
    }

    animalPartRepository.saveAnimalPart(animalPart);
  }

  public void createTray(Tray tray) {
    if (tray.getPartType() == null || tray.getPartType().isBlank()) {
      throw new IllegalArgumentException("Tray part type is required");
    }

    if (tray.getMaxWeight() <= 0) {
      throw new IllegalArgumentException("Tray max weight must be positive");
    }

    trayRepository.saveTray(tray);
  }

  public void addPartToTray(int trayId, int partId) {
    if (trayId <= 0) {
      throw new IllegalArgumentException("Tray id must be positive");
    }

    if (partId <= 0) {
      throw new IllegalArgumentException("Part id must be positive");
    }

    trayRepository.addPartToTray(trayId, partId);
  }

  public List<AnimalPart> getPartsByAnimal(int registrationNumber) {
    return animalPartRepository.findPartsByAnimal(registrationNumber);
  }

  public List<AnimalPart> getAllParts() {
    return animalPartRepository.findAllParts();
  }

  public List<Tray> getAllTrays() {
    return trayRepository.findAllTrays();
  }
}