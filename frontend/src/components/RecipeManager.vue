<template>
  <div id="recipe-manager">
    <h1>My Recipes</h1>
    <button @click="showForm(true)">Create New Recipe</button>

    <div v-if="showCreateForm || editingRecipe" class="recipe-form">
      <h2>{{ editingRecipe ? 'Edit Recipe' : 'Create Recipe' }}</h2>
      <form @submit.prevent="submitRecipe">
        <div class="form-group">
          <label for="recipeName">Recipe Name:</label>
          <input id="recipeName" v-model="currentRecipe.name" type="text" required>
        </div>
        <div class="form-group">
          <label for="ingredients">Ingredients:</label>
          <textarea id="ingredients" v-model="currentRecipe.ingredients" required></textarea>
        </div>
        <div class="form-group">
          <label for="instructions">Instructions:</label>
          <textarea id="instructions" v-model="currentRecipe.instructions" required></textarea>
        </div>
        <button type="submit">{{ editingRecipe ? 'Update Recipe' : 'Create Recipe' }}</button>
        <button type="button" @click="cancelEdit">Cancel</button>
      </form>
    </div>

    <div v-else class="recipes-list">
      <ul>
        <li v-for="(recipe, index) in recipes" :key="index">
          <h3>{{ recipe.name }}</h3>
          <p><strong>Ingredients:</strong> {{ recipe.ingredients }}</p>
          <p><strong>Instructions:</strong> {{ recipe.instructions }}</p>
          <button @click="editRecipe(index)">Edit</button>
          <button @click="deleteRecipe(index)">Delete</button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RecipeManager',
  data() {
    return {
      recipes: [],
      showCreateForm: false,
      editingRecipe: false,
      currentRecipe: { name: '', ingredients: '', instructions: '' },
      currentIndex: -1,
    };
  },
  methods: {
    submitRecipe() {
      if (this.editingRecipe) {
        this.recipes[this.currentIndex] = { ...this.currentRecipe };
      } else {
        this.recipes.push({ ...this.currentRecipe });
      }
      this.resetForm();
    },
    editRecipe(index) {
      this.currentIndex = index;
      this.currentRecipe = { ...this.recipes[index] };
      this.editingRecipe = true;
      this.showCreateForm = true;
    },
    cancelEdit() {
      this.resetForm();
    },
    resetForm() {
      this.currentRecipe = { name: '', ingredients: '', instructions: '' };
      this.showCreateForm = false;
      this.editingRecipe = false;
      this.currentIndex = -1;
    },
    showForm(createNew = false) {
      this.showCreateForm = createNew;
    },
    deleteRecipe(index) {
      if (window.confirm("Are you sure you want to delete this recipe?")) {
        this.recipes.splice(index, 1);
      }
    },
  },
}
</script>

<style>
/* Your existing styles */
</style>
