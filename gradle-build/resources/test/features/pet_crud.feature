@pet
Feature: Ciclo CRUD del recurso Pet en la API PetStore
  Como consumidor de la API PetStore
  Quiero poder gestionar mascotas a traves del servicio /pet
  Para validar las operaciones de creacion, consulta, actualizacion y eliminacion

  @crud_completo
  Scenario Outline: Ciclo CRUD completo de una mascota
    Given que quiero registrar una mascota con nombre "<nombre>" y estado "<estado>"
    When creo la mascota en el sistema
    Then la respuesta debe tener el codigo de estado 200
    And la mascota debe existir en el sistema con nombre "<nombre>"

    When consulto la mascota creada
    Then la respuesta debe tener el codigo de estado 200
    And la mascota debe tener el nombre "<nombre>"

    When actualizo la mascota con nombre "<nombre_actualizado>" y estado "<estado_actualizado>"
    Then la respuesta debe tener el codigo de estado 200
    And la mascota debe tener el nombre "<nombre_actualizado>"

    When elimino la mascota creada
    Then la respuesta debe tener el codigo de estado 200
    And la mascota no debe existir en el sistema

    Examples:
      | nombre   | estado    | nombre_actualizado | estado_actualizado |
      | Firulais | available | Max                | sold               |

