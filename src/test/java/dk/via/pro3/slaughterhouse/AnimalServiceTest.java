package dk.via.pro3.slaughterhouse;

import dk.via.pro3.slaughterhouse.model.Animal;
import dk.via.pro3.slaughterhouse.repository.AnimalRepository;
import dk.via.pro3.slaughterhouse.service.AnimalService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimalServiceTest {

  @Mock
  private AnimalRepository animalRepository;

  private AnimalService animalService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    animalService = new AnimalService(animalRepository);
  }

  @Test
  void registerValidAnimal() {
    Animal animal = new Animal(
        1004, 520.5,
        LocalDate.of(2026, 11, 1),
        "Farm Hansen"
    );

    animalService.registerAnimal(animal);

    verify(animalRepository).saveAnimal(animal);
  }

  @Test
  void rejectNegativeWeight() {
    Animal animal = new Animal(
        1005, -200,
        LocalDate.of(2026, 11, 1),
        "Farm Hansen"
    );

    assertThrows(
        IllegalArgumentException.class,
        () -> animalService.registerAnimal(animal)
    );

    verify(animalRepository, never()).saveAnimal(any());
  }

  @Test
  void rejectMissingOrigin() {
    Animal animal = new Animal(
        1006, 400,
        LocalDate.of(2026, 11, 1),
        ""
    );

    assertThrows(
        IllegalArgumentException.class,
        () -> animalService.registerAnimal(animal)
    );

    verify(animalRepository, never()).saveAnimal(any());
  }

  @Test
  void findAnimalByRegistrationNumber() {
    Animal animal = new Animal(
        1004, 520.5,
        LocalDate.of(2026, 11, 1),
        "Farm Hansen"
    );

    when(animalRepository.findByRegistrationNumber(1004))
        .thenReturn(Optional.of(animal));

    Optional<Animal> result = animalService.getAnimal(1004);

    assertTrue(result.isPresent());
    assertEquals(1004, result.get().getRegistrationNumber());
  }

  @Test
  void findAnimalsByDate() {
    LocalDate date = LocalDate.of(2026, 11, 1);

    Animal animal = new Animal(
        1004, 520.5, date, "Farm Hansen"
    );

    when(animalRepository.findByDate(date))
        .thenReturn(List.of(animal));

    List<Animal> result = animalService.getAnimalsByDate(date);

    assertEquals(1, result.size());
    assertEquals(1004, result.get(0).getRegistrationNumber());
  }

  @Test
  void findAnimalsByOrigin() {
    Animal animal = new Animal(
        1004, 520.5,
        LocalDate.of(2026, 11, 1),
        "Farm Hansen"
    );

    when(animalRepository.findByOrigin("Farm Hansen"))
        .thenReturn(List.of(animal));

    List<Animal> result =
        animalService.getAnimalsByOrigin("Farm Hansen");

    assertEquals(1, result.size());
    assertEquals("Farm Hansen", result.get(0).getOrigin());
  }

  @Test
  void animalNotFound() {
    when(animalRepository.findByRegistrationNumber(9999))
        .thenReturn(Optional.empty());

    Optional<Animal> result = animalService.getAnimal(9999);

    assertTrue(result.isEmpty());
  }
}
