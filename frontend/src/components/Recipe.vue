<template>
    <div class="recipe-page">
      <h1 class="recipe-title">{{ recipe.title }}</h1>
      <div class="recipe-details">
        <img :src="recipe.image" alt="Recipe Image" class="recipe-image"/>
        <div class="recipe-meta">
          <h2>Ingredients</h2>
          <ul>
            <li v-for="(ingredient, index) in recipe.ingredients" :key="index">
              {{ ingredient }}
            </li>
          </ul>
          <h2>Instructions</h2>
          <p>{{ recipe.instructions }}</p>
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
          title: 'Chocolate Chip Cookies',
          image: 'path-to-your-image.jpg', // Replace with your image path
          ingredients: [
            '1 cup granulated sugar',
            '2 cups all-purpose flour',
            // Add more ingredients
          ],
          instructions: 'Preheat your oven to 350°F (175°C). ...' // Add your instructions
        }
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
  