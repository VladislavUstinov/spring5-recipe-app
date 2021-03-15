package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeMappedServiceImpl implements RecipeMappedService {

    //можно было бы хранить не репозиторий, а сам set. Привязать его через OneToMany? По идее это ложный путь в сервисе
    //но можно было бы просто хранить, один раз проинициализировав в Bootdtrap.run - ?
    private final RecipeRepository recipeRepository;

    @Override
    public Set<Recipe> getRecipes () {
        Set<Recipe> set = new HashSet<Recipe>();

        for (Recipe recipe : recipeRepository.findAll())
            set.add(recipe);
        return set;
    }

    //вроде по конструктуру autowired будет автоматом
    public RecipeMappedServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
}
