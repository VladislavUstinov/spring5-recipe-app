package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeMappedServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

/**
 * Created by jt on 6/1/17.
 */
@Controller
public class IndexController {

    @Autowired
    RecipeMappedServiceImpl recipeMappedService;

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){

        Set<Recipe> recipes = recipeMappedService.getRecipes();
        model.addAttribute("recipes", recipes);

        return "index";
    }
}
