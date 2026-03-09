package com.automation.tasks;

import com.automation.utils.Constants;
import net.serenitybdd.rest.SerenityRest;
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
        return new GetPet(petId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.given()
                .baseUri(Constants.BASE_URL)
                .when()
                .get(Constants.PET_ENDPOINT + "/" + petId);
    }
}

