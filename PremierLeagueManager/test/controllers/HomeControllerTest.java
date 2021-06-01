package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void clubList() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/pointTable");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void matchesList() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/matches");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void generateRandomMatch() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/randomMatch");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void findMatch() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/findMatch/:date");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void clubListSortGoals() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/sortGoals");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void clubListSortWins() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/sortWins");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
}
