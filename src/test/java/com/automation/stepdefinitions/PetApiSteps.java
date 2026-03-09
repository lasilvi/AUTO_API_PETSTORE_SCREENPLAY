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
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

/**
 * Step definitions de Cucumber para el ciclo CRUD del recurso Pet.
 */
public class PetApiSteps {

    private Actor actor;
    private Pet currentPet;

    @Before
    public void setStage() {
        OnStage.setTheStage(new OnlineCast());
        actor = OnStage.theActorCalled("Tester");
    }

    // ─── GIVEN ──────────────────────────────────────────────────────────────────

    @Given("que tengo una mascota con id {long}, nombre {string} y estado {string}")
    public void quetengoUnaMascotaConDatos(Long id, String name, String status) {
        currentPet = new Pet(id, name, status);
    }

    // ─── WHEN ────────────────────────────────────────────────────────────────────

    @When("creo la mascota en el sistema")
    public void creoLaMascota() {
        actor.attemptsTo(CreatePet.with(currentPet));
    }

    @When("consulto la mascota con id {long}")
    public void consultoLaMascota(Long id) {
        actor.attemptsTo(GetPet.withId(id));
    }

    @When("actualizo la mascota con id {long}, nombre {string} y estado {string}")
    public void actualizoLaMascota(Long id, String name, String status) {
        currentPet = new Pet(id, name, status);
        actor.attemptsTo(UpdatePet.with(currentPet));
    }

    @When("elimino la mascota con id {long}")
    public void eliminoLaMascota(Long id) {
        actor.attemptsTo(DeletePet.withId(id));
    }

    // ─── THEN ────────────────────────────────────────────────────────────────────

    @Then("la respuesta debe tener el código de estado {int}")
    public void laRespuestaDebeTenerCodigoEstado(int statusCode) {
        // TODO: Implementar la validación del código de respuesta HTTP
        // Ejemplo:
        // actor.should(seeThatResponse(response -> response.statusCode(statusCode)));
    }

    @Then("la mascota debe existir en el sistema")
    public void laMascotaDebeExistir() {
        // TODO: Implementar la validación de existencia de la mascota
    }

    @Then("la mascota debe tener el nombre {string}")
    public void laMascotaDebeTenerElNombre(String name) {
        // TODO: Implementar la validación del nombre de la mascota
    }

    @Then("la mascota no debe existir en el sistema")
    public void laMascotaNODebeExistir() {
        // TODO: Implementar la validación de no existencia de la mascota
    }
}

