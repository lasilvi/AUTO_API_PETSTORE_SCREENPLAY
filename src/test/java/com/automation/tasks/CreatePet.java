package com.automation.tasks;

import com.automation.models.Pet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/**
 * Task de Screenplay para crear una mascota (POST /pet).
 */
public class CreatePet implements Task {

    private final Pet pet;

    public CreatePet(Pet pet) {
        this.pet = pet;
    }

    public static CreatePet with(Pet pet) {
        return Tasks.instrumented(CreatePet.class, pet);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // TODO: Implementar la llamada HTTP POST /pet usando CallAnApi
        // Ejemplo:
        // actor.attemptsTo(
        //     Post.to("/pet").with(request -> request.body(pet))
        // );
    }
}

