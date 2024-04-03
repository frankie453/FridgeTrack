<template>
  <div id="generate-recipe">
    <h1>Generate Recipe</h1>
    <div class="ingredients-selection">
      <h2>Available Ingredients</h2>
      <div v-for="item in fridgeItems" :key="item.id" class="ingredient-item">
        <input type="checkbox" :id="item.id" :value="item.name" v-model="selectedIngredients">
        <label :for="item.id">{{ item.name }}</label>
      </div>
    </div>
    <button @click="generateRecipe">Generate Recipe</button>

    <div v-if="suggestedRecipe">
      <h2>Suggested Recipe</h2>
      <p><strong>Name:</strong> {{ suggestedRecipe.name }}</p>
      <p><strong>Ingredients:</strong> {{ suggestedRecipe.ingredients.join(', ') }}</p>
      <p><strong>Instructions:</strong> {{ suggestedRecipe.instructions }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'GenerateRecipe',
  data() {
    return {
      fridgeItems: [
                  { id: 1, name: 'Milk', expiryDate: '2024-03-01' },
          { id: 2, name: 'Eggs', expiryDate: '2024-03-05' },
          { id: 3, name: 'Bread', expiryDate: '2024-03-10' },
          { id: 4, name: 'Carrots', expiryDate: '2024-03-15' },
          { id: 5, name: 'Apples', expiryDate: '2024-03-20' },
          { id: 6, name: 'Bananas', expiryDate: '2024-03-25'},
          { id: 7, name: 'Cheese', expiryDate: '2024-03-30' },
          { id: 8, name: 'Yogurt', expiryDate: '2024-04-01'},
          { id: 9, name: 'Lettuce', expiryDate: '2024-04-05' },
          { id: 10, name: 'Tomatoes', expiryDate: '2024-04-10'},
          { id: 11, name: 'Potatoes', expiryDate: '2024-04-15' },
          { id: 12, name: 'Onions', expiryDate: '2024-04-20' },
          { id: 13, name: 'Garlic', expiryDate: '2024-04-25' },
          { id: 14, name: 'Pasta', expiryDate: '2024-04-30' },
          { id: 15, name: 'Rice', expiryDate: '2024-05-01' },
          { id: 16, name: 'Cereal', expiryDate: '2024-05-05' },
          { id: 17, name: 'Peanut Butter', expiryDate: '2024-05-10' },
          { id: 18, name: 'Jam', expiryDate: '2024-05-15' },
          { id: 19, name: 'Soy Sauce', expiryDate: '2024-05-20' },
          { id: 20, name: 'Ketchup', expiryDate: '2024-05-25' },
      ],
      selectedIngredients: [],
      suggestedRecipe: null,
    };
  },
  methods: {
    generateRecipe() {
      // This method should ideally call a backend service to get recipes based on selectedIngredients
      // For demonstration, we'll mock this logic
      
      if (this.selectedIngredients.includes('Eggs') && this.selectedIngredients.includes('Milk') ) {
        this.suggestedRecipe = {
          name: 'Egg Pudding',
          ingredients: ['Eggs', 'Milk'],
          instructions: 'Mix all ingredients and freeze it in the freezer.'
        };
      } else {
        this.suggestedRecipe = null;
        alert('No matching recipe found for selected ingredients.');
      }
    },
  },
}
</script>

<style>
#generate-recipe {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  max-width: 600px;
  margin: auto;
}

.ingredient-item {
  margin: 10px 0;
}
</style>
