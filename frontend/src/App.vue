<template>
  <div id="app">
    <div class="navigation-container">
      <!-- Button to switch to Recipe component -->
      <button @click="goToRecipe">Go to Recipe</button>
      <!-- Button to switch to FoodRecord component -->
      <button @click="goToFoodRecord">Record New Item</button>
      <!-- Button to go back to the main component (Fridge) -->
      <button @click="goBackToMain">Back to Main</button>
    </div>
    <!-- Dynamic component based on currentComponent -->
    <component :is="currentComponent"></component>
  </div>
</template>


<script>
import Fridge from './components/Fridge.vue'
import Recipe from './components/Recipe.vue'
import FoodRecord from './components/FoodRecord.vue' // Import FoodRecord component

export default {
  name: 'App',
  components: {
    Fridge,
    Recipe,
    FoodRecord // Register FoodRecord component
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
  background: #ddd;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background 0.3s ease; /* Smooth background color transition on hover */
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
}

@media (max-width: 900px) {
  .fridge {
    grid-template-columns: repeat(2, 1fr); /* Adjust to 2 columns for smaller screens */
  }
}

</style>
