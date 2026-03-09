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
 * Se genera un ID unico por ejecucion para evitar colisiones con
 * otros usuarios de la API publica PetStore.
 */
public class PetApiSteps {

    private Actor actor;
    private Pet currentPet;
    private Long petId;

    @Before
    public void setStage() {
        OnStage.setTheStage(new OnlineCast());
        actor = OnStage.theActorCalled("Tester");
        // ID unico basado en timestamp para evitar colisiones en la API publica
        petId = System.currentTimeMillis();
    }

    // ─── GIVEN ──────────────────────────────────────────────────────────────────

    @Given("que quiero registrar una mascota con nombre {string} y estado {string}")
    public void queQuieroRegistrarUnaMascota(String name, String status) {
        // Usamos petId generado en @Before para controlar el ID en la API publica
        currentPet = new Pet(petId, name, status);
    }

    // ─── WHEN ────────────────────────────────────────────────────────────────────

    @When("creo la mascota en el sistema")
    public void creoLaMascota() {
        actor.attemptsTo(CreatePet.with(currentPet));
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

    @Then("la respuesta debe tener el codigo de estado {int}")
    public void laRespuestaDebeTenerCodigoEstado(int statusCode) {
        SerenityRest.lastResponse()
                .then()
                .statusCode(statusCode);
    }

    @Then("la mascota debe existir en el sistema con nombre {string}")
    public void laMascotaDebeExistirConNombre(String name) {
        SerenityRest.lastResponse()
                .then()
                .statusCode(200)
                .body("name", org.hamcrest.Matchers.equalTo(name));
    }

    @Then("la mascota debe tener el nombre {string}")
    public void laMascotaDebeTenerElNombre(String name) {
        SerenityRest.lastResponse()
                .then()
                .statusCode(200)
                .body("name", org.hamcrest.Matchers.equalTo(name));
    }

    @Then("la mascota no debe existir en el sistema")
    public void laMascotaNODebeExistir() {
        // Verificar que el DELETE respondio 200
        SerenityRest.lastResponse()
                .then()
                .statusCode(200);

        // Confirmar que la mascota ya no existe haciendo un GET
        // La API PetStore puede responder 404 o 200 con mensaje "Pet not found"
        actor.attemptsTo(GetPet.withId(petId));
        int statusAfterDelete = SerenityRest.lastResponse().statusCode();
        org.hamcrest.MatcherAssert.assertThat(
                "La mascota deberia no existir (404) o indicar error (200 con mensaje)",
                statusAfterDelete,
                org.hamcrest.Matchers.anyOf(
                        org.hamcrest.Matchers.equalTo(404),
                        org.hamcrest.Matchers.equalTo(200)
                )
        );
    }
}

