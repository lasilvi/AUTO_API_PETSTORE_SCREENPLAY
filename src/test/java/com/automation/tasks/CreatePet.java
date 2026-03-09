package com.automation.tasks;

import com.automation.models.Pet;
import com.automation.utils.Constants;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;

/**
 * Task de Screenplay para crear una mascota (POST /pet).
 */
public class CreatePet implements Task {

    private final Pet pet;

    public CreatePet(Pet pet) {
        this.pet = pet;
    }

    public static CreatePet with(Pet pet) {
        return new CreatePet(pet);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<String, Object> body = new HashMap<>();
        if (pet.getId() != null) body.put("id", pet.getId());
        body.put("name", pet.getName());
        body.put("status", pet.getStatus());

        SerenityRest.given()
                .baseUri(Constants.BASE_URL)
                .contentType(JSON)
                .body(body)
                .when()
                .post(Constants.PET_ENDPOINT);
    }
}

