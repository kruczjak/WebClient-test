package controllers;

import forms.NewGameForm;
import play.*;
import play.api.data.Form;
import play.mvc.*;
import views.html.games.build;
import views.html.games.index;

import static play.data.Form.*;

public class Games extends Controller {
    static Form<NewGameForm> newGameForm = form(NewGameForm.class);

    public Result index(String type) {
        return ok(index.render("eee new application is ready."));
    }

    public Result build() {


        return ok(build.render(newGameForm));
    }

    public Result create() {
        return ok(build.render("ok"));
    }

}