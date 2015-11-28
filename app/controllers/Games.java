package controllers;

import play.*;
import play.libs.Json;
import play.mvc.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static play.data.Form.*;

public class Games extends Controller {
    public Result index(String type) {
        String [] array = { "Pierwsza gra", "Druga gra" };
        List<String> list = Arrays.asList(array);
        return ok(Json.toJson(list));
    }

    public Result create() {
        return ok();
    }

}