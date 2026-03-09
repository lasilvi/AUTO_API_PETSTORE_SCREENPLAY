package com.automation.tasks;

import com.automation.models.Pet;
import com.automation.utils.Constants;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static io.restassured.http.ContentType.JSON;

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
        SerenityRest.given()
                .baseUri(Constants.BASE_URL)
                .contentType(JSON)
                .body(pet)
                .when()
                .put(Constants.PET_ENDPOINT);
    }
}

