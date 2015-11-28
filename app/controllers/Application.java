package controllers;

import play.*;
import play.mvc.*;
import views.html.main;


public class Application extends Controller {
    public Result index() {
        return ok(main.render());
    }

    public Result matchAll(String path) {
        return ok(main.render());
    }
}
