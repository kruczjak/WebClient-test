package controllers;

import play.*;
import play.mvc.*;
import views.html.application.index;


public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

}
