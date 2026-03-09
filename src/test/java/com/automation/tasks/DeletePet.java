package com.automation.tasks;

import com.automation.utils.Constants;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/**
 * Task de Screenplay para eliminar una mascota por ID (DELETE /pet/{petId}).
 */
public class DeletePet implements Task {

    private final Long petId;

    public DeletePet(Long petId) {
        this.petId = petId;
    }

    public static DeletePet withId(Long petId) {
        return Tasks.instrumented(DeletePet.class, petId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.given()
                .baseUri(Constants.BASE_URL)
                .when()
                .delete(Constants.PET_ENDPOINT + "/" + petId);
    }
}

