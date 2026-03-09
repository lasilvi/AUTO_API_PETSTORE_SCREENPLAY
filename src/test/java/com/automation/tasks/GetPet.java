package com.automation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/**
 * Task de Screenplay para consultar una mascota por ID (GET /pet/{petId}).
 */
public class GetPet implements Task {

    private final Long petId;

    public GetPet(Long petId) {
        this.petId = petId;
    }

    public static GetPet withId(Long petId) {
        return Tasks.instrumented(GetPet.class, petId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // TODO: Implementar la llamada HTTP GET /pet/{petId} usando CallAnApi
        // Ejemplo:
        // actor.attemptsTo(
        //     Get.resource("/pet/" + petId)
        // );
    }
}

