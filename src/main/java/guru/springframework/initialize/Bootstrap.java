package guru.springframework.initialize;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.NotesRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

//import src/main/resources/data.sql;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final NotesRepository notesRepository;

    private final CategoryRepository categoryRepository;

    public Bootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, NotesRepository notesRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.notesRepository = notesRepository;
        this.categoryRepository = categoryRepository;
    }

/*    @Override
    public void run(String... args) throws Exception {

    }*/

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

                    //load UnitOfMeasures from data.sql

        Optional<UnitOfMeasure>  uomPiecesOpt = unitOfMeasureRepository.findByDescription("Pieces");

        if (!uomPiecesOpt.isPresent())
            throw new RuntimeException ("Could find unit of measure Pieces in data.sql");

        UnitOfMeasure uomPieces = uomPiecesOpt.get();

                    //load Categories from data.sql

        Optional<Category> categoryAmericanOpt = categoryRepository.findByDescription ("American");

        if (!categoryAmericanOpt.isPresent())
            throw new RuntimeException ("Could find category American in data.sql ");

        Category categoryAmerican = categoryAmericanOpt.get();

        Optional<Category> categoryMexicanOpt = categoryRepository.findByDescription ("Mexican");

        if (!categoryMexicanOpt.isPresent())
            throw new RuntimeException ("Could find category Mexican in data.sql ");

        Category categoryMexican = categoryMexicanOpt.get();

                    //Guacomole Recipe:

        Ingredient ingredientAvocado = new Ingredient("avocado", new BigDecimal(2.0), uomPieces);
        Ingredient ingredientLemon = new Ingredient("lemon", new BigDecimal(0.5), uomPieces);

        Recipe recipeGuacomole = new Recipe();
        Set<Ingredient> ingredientsGuacomole = new HashSet<>();

        ingredientsGuacomole.add(ingredientAvocado);
        ingredientsGuacomole.add(ingredientLemon);

        recipeGuacomole.setIngredients(ingredientsGuacomole);
        recipeGuacomole.setDescription("Guacomole");

        Notes notesGuacomole = new Notes();
        notesGuacomole.setRecipeNotes("Some notes");
        notesGuacomole.setRecipe(recipeGuacomole);
        recipeGuacomole.setNotes(notesGuacomole);

        //Chicken Recipe:
        Ingredient ingredientChicken = new Ingredient("chicken", new BigDecimal(1.0), uomPieces);
        Ingredient ingredientLemon2 = new Ingredient("lemon", new BigDecimal(1.5), uomPieces);

        Recipe recipeChicken = new Recipe();
        Set<Ingredient> ingredientsChicken = new HashSet<>();

        ingredientsChicken.add(ingredientChicken);
        ingredientsChicken.add(ingredientLemon2);

        recipeChicken.setIngredients(ingredientsChicken);
        recipeChicken.setDescription("Chicken");

        Notes notesChicken = new Notes();
        notesChicken.setRecipeNotes("Some notes 2");
        notesChicken.setRecipe(recipeChicken);
        recipeChicken.setNotes(notesChicken);

        // adding categories


        //categoryAmerican.getRecipes().add (recipeGuacomole);
        //categoryAmerican.getRecipes().add (recipeChicken);

        //categoryMexican.getRecipes().add (recipeGuacomole);

        recipeGuacomole.getCategories().add(categoryAmerican);
        recipeGuacomole.getCategories().add(categoryMexican);

        recipeChicken.getCategories().add(categoryAmerican);

        recipeRepository.save(recipeGuacomole);
        recipeRepository.save(recipeChicken);
    }
}
