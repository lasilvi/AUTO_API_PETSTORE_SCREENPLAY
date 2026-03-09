package com.automation.tasks;

import com.automation.models.Pet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/**
 * Task de Screenplay para actualizar una mascota (PUT /pet).
 */
public class UpdatePet implements Task {

    private final Pet pet;

    public UpdatePet(Pet pet) {
        this.pet = pet;
    }

    public static UpdatePet with(Pet pet) {
        return Tasks.instrumented(UpdatePet.class, pet);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // TODO: Implementar la llamada HTTP PUT /pet usando CallAnApi
        // Ejemplo:
        // actor.attemptsTo(
        //     Put.to("/pet").with(request -> request.body(pet))
        // );
    }
}

