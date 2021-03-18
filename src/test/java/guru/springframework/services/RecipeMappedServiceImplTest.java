package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeMappedServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    RecipeMappedServiceImpl recipeMappedService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeMappedService = new RecipeMappedServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        Recipe recipe=new Recipe();
        Set<Recipe> setData = new HashSet<>();
        setData.add(recipe);

        when (recipeRepository.findAll()).thenReturn(setData);

        //собственно тестовый запуск :)
        Set<Recipe> setResult = recipeMappedService.getRecipes();

        assertEquals(setResult.size(), 1);
        verify(recipeRepository,times(1)).findAll();
    }
}