<template>
  <div id="app">
    <div class="logo">
      <img class="image" src="./assets/logo.png" alt="Logo" />
    </div>
    <div class="navigation-container">
      <button @click="goBackToMain">Fridge</button>
      <button @click="goToFoodRecord">Record New Item</button>
      <button @click="goToScanPage">Scan Page</button>
      <button @click="goToRecipe">Recipe</button>
      <button @click="goToRecipeManager">Custom Recipes</button>
      <button @click="goToGenerateRecipe">Generate Recipes</button>
    </div>
    <!-- Dynamic component based on currentComponent -->
    <component :is="currentComponent"></component>
  </div>
</template>


<script>
import Fridge from './components/Fridge.vue'
import Recipe from './components/Recipe.vue'
import FoodRecord from './components/FoodRecord.vue'
import ScanPage from './components/ScanPage.vue'
import RecipeManager from './components/RecipeManager.vue' 
import GenerateRecipe from './components/GenerateRecipe.vue'
export default {
  name: 'App',
  components: {
    Fridge,
    Recipe,
    FoodRecord,
    ScanPage,
    RecipeManager,
    GenerateRecipe
  },
  data() {
    return {
      currentComponent: 'Fridge',
      pageTitle: 'FridgeTrack'
    }
  },
  mounted() {
    document.title = this.pageTitle;
  },
  watch: {
    pageTitle(newTitle) {
      document.title = newTitle;
    },
  },
  methods: {
    goToRecipe() {
      // Change the component to Recipe
      this.currentComponent = 'Recipe';
    },
    goToFoodRecord() {
      // Change the component to FoodRecord
      this.currentComponent = 'FoodRecord';
    },
    goBackToMain() {
      // Reset the component to Fridge (or your main component)
      this.currentComponent = 'Fridge';
    },
    goToScanPage() {
      // Change the component to ScanPage
      this.currentComponent = 'ScanPage';
    },
    goToRecipeManager() { // New method
      this.currentComponent = 'RecipeManager'; // Switch to RecipeManager component
    },
    goToGenerateRecipe() {
      this.currentComponent = 'GenerateRecipe';
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.image{
  display: block;
  margin-bottom: 20px;
  margin-left: auto;
  margin-right: auto;
  width: 10%;
  height: auto;
  border-radius: 10%;
}

.navigation-container {
  display: flex;
  justify-content: center;
  margin: 20px;
  flex-wrap: wrap;
}

/* Adjusted margins for more space */
button {
  padding: 10px 15px;
  margin: 0 10px;
  background-color: #11a7c1;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
}

button:hover {
  background: #ccc;
}

/* Fridge styles */
.fridge {
  display: grid;
  grid-template-columns: repeat(8, 1fr); 
  gap: 20px;
  padding: 20px;
}

@media (max-width: 1200px) {
  .fridge {
    grid-template-columns: repeat(4, 1fr);
  }
  .image{
    width: 20%;
  }
}

@media (max-width: 900px) {
  .fridge {
    grid-template-columns: repeat(2, 1fr);
  }
  .image{
    width: 30%;
  }
}

</style>
