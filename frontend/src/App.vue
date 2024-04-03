<template>
  <div id="app">
    <div class="logo">
      <img class="image" src="./assets/logo.png" alt="Logo" />
    </div>
    <div class="navigation-container">
      <!-- Button to switch to Recipe component -->
      <button @click="goToRecipe">Recipe</button>
      <!-- Button to switch to FoodRecord component -->
      <button @click="goToFoodRecord">Record New Item</button>
      <!-- Button to go back to the main component (Fridge) -->
      <button @click="goBackToMain">Back to Main</button>
      <!-- Button to go to the ScanPage component -->
      <button @click="goToScanPage">Scan Page</button>
      <button @click="goToRecipeManager">Manage Recipes</button>
    </div>
    <!-- Dynamic component based on currentComponent -->
    <component :is="currentComponent"></component>
  </div>
</template>


<script>
import Fridge from './components/Fridge.vue'
import Recipe from './components/Recipe.vue'
import FoodRecord from './components/FoodRecord.vue' // Import FoodRecord component
import ScanPage from './components/ScanPage.vue'
import RecipeManager from './components/RecipeManager.vue' 
export default {
  name: 'App',
  components: {
    Fridge,
    Recipe,
    FoodRecord,
    ScanPage,
    RecipeManager
  },
  data() {
    return {
      // Initially show the Fridge component
      currentComponent: 'Fridge'
    }
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
  flex-wrap: wrap; /* Allows buttons to wrap on small screens */
}

/* Adjusted margins for more space */
button {
  padding: 10px 15px; /* Adjusted padding for better button size */
  margin: 0 10px; /* Increased space between buttons */
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
  grid-template-columns: repeat(8, 1fr); /* 8 columns */
  gap: 20px;
  padding: 20px;
}

@media (max-width: 1200px) {
  .fridge {
    grid-template-columns: repeat(4, 1fr); /* Adjust to 4 columns for smaller screens */
  }
  .image{
    width: 20%;
  }
}

@media (max-width: 900px) {
  .fridge {
    grid-template-columns: repeat(2, 1fr); /* Adjust to 2 columns for smaller screens */
  }
  .image{
    width: 30%;
  }
}

</style>
