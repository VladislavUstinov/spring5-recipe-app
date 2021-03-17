package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeMappedServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

/**
 * Created by jt on 6/1/17.
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    RecipeMappedServiceImpl recipeMappedService;

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){

        log.debug("MY DEBUG EXAMPLE - index page in controller is being created!");

        Set<Recipe> recipes = recipeMappedService.getRecipes();
        model.addAttribute("recipes", recipes);

        return "index";
    }
}
