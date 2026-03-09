package com.automation.stepdefinitions;

import com.automation.models.Pet;
import com.automation.tasks.CreatePet;
import com.automation.tasks.DeletePet;
import com.automation.tasks.GetPet;
import com.automation.tasks.UpdatePet;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

/**
 * Step definitions de Cucumber para el ciclo CRUD del recurso Pet.
 * El id generado por el POST se guarda en 'petId' y se reutiliza
 * en los pasos de GET, PUT y DELETE.
 */
public class PetApiSteps {

    private Actor actor;
    private Pet currentPet;
    private Long petId;  // id devuelto por la API al crear la mascota

    @Before
    public void setStage() {
        OnStage.setTheStage(new OnlineCast());
        actor = OnStage.theActorCalled("Tester");
    }

    // ─── GIVEN ──────────────────────────────────────────────────────────────────

    @Given("que quiero registrar una mascota con nombre {string} y estado {string}")
    public void queQuieroRegistrarUnaMascota(String name, String status) {
        // Sin id: la API lo genera al hacer POST
        currentPet = new Pet(null, name, status);
    }

    // ─── WHEN ────────────────────────────────────────────────────────────────────

    @When("creo la mascota en el sistema")
    public void creoLaMascota() {
        actor.attemptsTo(CreatePet.with(currentPet));
        petId = SerenityRest.lastResponse().jsonPath().getLong("id");
    }

    @When("consulto la mascota creada")
    public void consultoLaMascotaCreada() {
        actor.attemptsTo(GetPet.withId(petId));
    }

    @When("actualizo la mascota con nombre {string} y estado {string}")
    public void actualizoLaMascota(String name, String status) {
        currentPet = new Pet(petId, name, status);
        actor.attemptsTo(UpdatePet.with(currentPet));
    }

    @When("elimino la mascota creada")
    public void eliminoLaMascotaCreada() {
        actor.attemptsTo(DeletePet.withId(petId));
    }

    // ─── THEN ────────────────────────────────────────────────────────────────────

    @Then("la respuesta debe tener el código de estado {int}")
    public void laRespuestaDebeTenerCodigoEstado(int statusCode) {
        SerenityRest.lastResponse()
                .then()
                .statusCode(statusCode);
    }

    @Then("la mascota debe existir en el sistema con nombre {string}")
    public void laMascotaDebeExistirConNombre(String name) {
        SerenityRest.lastResponse()
                .then()
                .statusCode(200);
        String nombreEnRespuesta = SerenityRest.lastResponse().jsonPath().getString("name");
        assert nombreEnRespuesta.equals(name) :
                "Se esperaba el nombre '" + name + "' pero se obtuvo '" + nombreEnRespuesta + "'";
    }

    @Then("la mascota debe tener el nombre {string}")
    public void laMascotaDebeTenerElNombre(String name) {
        String nombreEnRespuesta = SerenityRest.lastResponse().jsonPath().getString("name");
        assert nombreEnRespuesta.equals(name) :
                "Se esperaba el nombre '" + name + "' pero se obtuvo '" + nombreEnRespuesta + "'";
    }

    @Then("la mascota no debe existir en el sistema")
    public void laMascotaNODebeExistir() {
        SerenityRest.lastResponse()
                .then()
                .statusCode(200);
    }
}

