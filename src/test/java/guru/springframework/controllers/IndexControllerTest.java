package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeMappedServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {
    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeMappedServiceImpl recipeMappedService;

    @Mock
    Model model;

   @Captor
    private ArgumentCaptor<Set<Recipe>> captor;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

   //     recipeMappedService = new RecipeMappedServiceImpl(recipeRepository);
        indexController = new IndexController();
        indexController.recipeMappedService = recipeMappedService;

    }

    @Test
    public void testMvcMock () throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {

        //given
        Recipe recipe=new Recipe();
        recipe.setDescription("aga");
        Set<Recipe> setData = new HashSet<>();
        setData.add(recipe);

        when (recipeMappedService.getRecipes()).thenReturn(setData);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        //when
        String viewName = indexController.getIndexPage(model);



        //then
        assertEquals(viewName, "index123");
        //проверил бы, что в model записан нужный set в attribute, но пока не вижу методов get в ней

        verify(recipeMappedService, times(1)).getRecipes();
        //eq = EqualMatcher - уточняем, что будем считать именно количество вызовов addAtribute с данным значением параметра
        //anySet() - наоборот уточняем, что множество могло быть произвольным
        //verify(model, times(1)).addAttribute(eq("recipes"), anySet());
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> actualSet = argumentCaptor.getValue();
        assertEquals(actualSet.size(),1);




        /*
        //собственно тестовый запуск :)
        Set<Recipe> setResult = recipeMappedService.getRecipes();

        assertEquals(setResult.size(), 1);
        verify(recipeRepository,times(1)).findAll();

         */
    }
}