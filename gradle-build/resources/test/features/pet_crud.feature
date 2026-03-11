@epic:PetStore
@feature:CicloCRUDdelrecursoPet
@story:CicloCRUDcompletodeunamascota
@pet
Feature: Ciclo CRUD del recurso Pet en la API PetStore
  Como consumidor de la API PetStore
  Quiero poder gestionar mascotas a traves del servicio /pet
  Para validar las operaciones de creacion, consulta, actualizacion y eliminacion

  # ═══════════════════════════════════════════
  # FLUJO CRUD COMPLETO - DE PRINCIPIO A FIN
  # ═══════════════════════════════════════════
  @crud_completo @flujo_completo
  Scenario Outline: Ejecutar ciclo CRUD completo de una mascota
    # ─── PASO 1: CREAR MASCOTA ─────────────────────────
    Given que quiero registrar una mascota con nombre "<nombre_inicial>" y estado "<estado_inicial>"
    When creo la mascota en el sistema
    Then la respuesta debe tener el codigo de estado 200
    And la mascota debe existir en el sistema con nombre "<nombre_inicial>"

    # ─── PASO 2: CONSULTAR MASCOTA CREADA ─────────────
    When consulto la mascota creada
    Then la respuesta debe tener el codigo de estado 200
    And la mascota debe tener el nombre "<nombre_inicial>"

    # ─── PASO 3: ACTUALIZAR MASCOTA ────────────────────
    When actualizo la mascota con nombre "<nombre_actualizado>" y estado "<estado_actualizado>"
    Then la respuesta debe tener el codigo de estado 200
    And la mascota debe tener el nombre "<nombre_actualizado>"

    # ─── PASO 4: CONSULTAR MASCOTA ACTUALIZADA ────────
    When consulto la mascota creada
    Then la respuesta debe tener el codigo de estado 200
    And la mascota debe tener el nombre "<nombre_actualizado>"

    # ─── PASO 5: ELIMINAR MASCOTA ─────────────────────
    When elimino la mascota creada
    Then la respuesta debe tener el codigo de estado 200
    And la mascota no debe existir en el sistema

    Examples:
      | nombre_inicial | estado_inicial | nombre_actualizado | estado_actualizado |
      | Firulais       | available      | Max                | sold               |
      | Luna           | available      | Luna Updated       | pending            |