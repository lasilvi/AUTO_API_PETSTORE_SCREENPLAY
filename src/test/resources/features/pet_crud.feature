# language: es
@pet
Feature: Ciclo CRUD del recurso Pet en la API PetStore
  Como consumidor de la API PetStore
  Quiero poder gestionar mascotas a través del servicio /pet
  Para validar las operaciones de creación, consulta, actualización y eliminación

  Background:
    Given que tengo una mascota con id 123456789, nombre "Firulais" y estado "available"

  @crear_mascota
  Scenario: Crear una nueva mascota
    When creo la mascota en el sistema
    Then la respuesta debe tener el código de estado 200
    And la mascota debe existir en el sistema

  @consultar_mascota
  Scenario: Consultar una mascota por ID
    When consulto la mascota con id 123456789
    Then la respuesta debe tener el código de estado 200
    And la mascota debe tener el nombre "Firulais"

  @actualizar_mascota
  Scenario: Actualizar los datos de una mascota
    When actualizo la mascota con id 123456789, nombre "Max" y estado "sold"
    Then la respuesta debe tener el código de estado 200
    And la mascota debe tener el nombre "Max"

  @eliminar_mascota
  Scenario: Eliminar una mascota por ID
    When elimino la mascota con id 123456789
    Then la respuesta debe tener el código de estado 200
    And la mascota no debe existir en el sistema

