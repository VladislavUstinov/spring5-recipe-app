package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeMappedServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IndexControllerTest {
    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeMappedServiceImpl recipeMappedService;

    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

   //     recipeMappedService = new RecipeMappedServiceImpl(recipeRepository);
        indexController = new IndexController();
        indexController.recipeMappedService = recipeMappedService;

    }

    @Test
    public void getIndexPage() {

        Recipe recipe=new Recipe();
        recipe.setDescription("aga");
        Set<Recipe> setData = new HashSet<>();
        setData.add(recipe);

        when (recipeMappedService.getRecipes()).thenReturn(setData);

        String viewName = indexController.getIndexPage(model);

        assertEquals(viewName, "index");
        //проверил бы, что в model записан нужный set в attribute, но пока не вижу методов get в ней

        verify(recipeMappedService, times(1)).getRecipes();
        //eq = EqualMatcher - уточняем, что будем считать именно количество вызовов addAtribute с данным значением параметра
        //anySet() - наоборот уточняем, что множество могло быть произвольным
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());





        /*
        //собственно тестовый запуск :)
        Set<Recipe> setResult = recipeMappedService.getRecipes();

        assertEquals(setResult.size(), 1);
        verify(recipeRepository,times(1)).findAll();

         */
    }
}