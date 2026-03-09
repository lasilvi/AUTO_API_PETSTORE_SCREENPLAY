@epic:PetStore
@feature:CicloCRUDdelrecursoPet
@story:CicloCRUDcompletodeunamascota
@pet
Feature: Ciclo CRUD del recurso Pet en la API PetStore
  Como consumidor de la API PetStore
  Quiero poder gestionar mascotas a traves del servicio /pet
  Para validar las operaciones de creacion, consulta, actualizacion y eliminacion

  # ─── CREAR MASCOTA ─────────────────────────
  @crud_completo
  Scenario Outline: Crear una mascota
    Given que quiero registrar una mascota con nombre "<nombre>" y estado "<estado>"
    When creo la mascota en el sistema
    Then la respuesta debe tener el codigo de estado 200
    And la mascota debe existir en el sistema con nombre "<nombre>"

    Examples:
      | nombre   | estado    |
      | Firulais | available |

  # ─── CONSULTAR MASCOTA ─────────────────────
  @crud_completo
  Scenario Outline: Consultar una mascota
    Given que existe la mascota "<nombre>"
    When consulto la mascota creada
    Then la respuesta debe tener el codigo de estado 200
    And la mascota debe tener el nombre "<nombre>"

    Examples:
      | nombre   |
      | Firulais |

  # ─── ACTUALIZAR MASCOTA ────────────────────
  @crud_completo
  Scenario Outline: Actualizar una mascota
    Given que existe la mascota "<nombre>"
    When actualizo la mascota con nombre "<nombre_actualizado>" y estado "<estado_actualizado>"
    Then la respuesta debe tener el codigo de estado 200
    And la mascota debe tener el nombre "<nombre_actualizado>"

    Examples:
      | nombre   | nombre_actualizado | estado_actualizado |
      | Firulais | Max               | sold              |

  # ─── ELIMINAR MASCOTA ─────────────────────
  @crud_completo
  Scenario Outline: Eliminar una mascota
    Given que existe la mascota "<nombre>"
    When elimino la mascota creada
    Then la respuesta debe tener el codigo de estado 200
    And la mascota no debe existir en el sistema

    Examples:
      | nombre   |
      | Max      |