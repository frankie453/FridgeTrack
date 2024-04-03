<template>
    <div class="recipe-page">
      <h1 class="recipe-title">{{ recipe.title }}</h1>
      <div class="recipe-details">
        <img src="../assets/recipe-example.jpeg" alt="Recipe Image" class="recipe-image"/>
        <div class="recipe-meta">
          <h2>Ingredients</h2>
          <ul>
            <li v-for="(ingredient, index) in recipe.ingredients" :key="index">
              {{ ingredient }}
            </li>
          </ul>
          <h2>Instructions</h2>
          <pre>{{ recipe.instructions }}</pre>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'RecipeDisplay',
    data() {
      return {
        recipe: {
          title: 'Crème Brûlée',
          image: '../assets/logo.png',
          ingredients: [
            '2 cups heavy cream',
            '1 vanilla bean, split and scraped (or 1 teaspoon vanilla extract)',
            '5 egg yolks',
            '1/2 cup granulated sugar, plus more for the caramelized topping',
            'Hot water (for the water bath)',
          ],
          instructions: `Preheat: Oven to 325°F.
Heat Cream: Warm cream and vanilla in a pot until hot, not boiling.
Mix: Whisk yolks and 1/2 cup sugar until thick.
Combine: Gradually mix hot cream into yolks.
Strain: Pour through a sieve to remove lumps.
Bake: Pour into ramekins in a water bath. Bake 40-45 min until set but wobbly.
Chill: Cool, then refrigerate for 2 hours.
Caramelize: Sprinkle sugar on top and torch until caramelized.`}
      };
    }
  }
  </script>
  
  <style scoped>
  .recipe-page {
    max-width: 800px;
    margin: auto;
    padding: 20px;
  }
  
  .recipe-title {
    text-align: center;
    margin-bottom: 20px;
  }
  
  .recipe-details {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  
  .recipe-image {
    max-width: 100%;
    border-radius: 10px;
    width: 40%;
  }
  
  .recipe-meta {
    margin-top: 20px;
  }
  
  h2 {
    margin-bottom: 10px;
  }
  
  ul {
    list-style-type: none;
    padding: 0;
  }
  
  li {
    margin-bottom: 5px;
  }
  </style>
  