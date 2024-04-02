<template>
  <div id="app">
    <h1>FridgeTrack</h1>
    <!-- Food Entry Form -->
    <div>
      <h2>Add Food Item</h2>
      <form @submit.prevent="addFoodItem">
        <input type="text" v-model="newFoodItem.name" placeholder="Food Name" required>
        <input type="number" v-model="newFoodItem.quantity" placeholder="Quantity" required>
        <input type="date" v-model="newFoodItem.expiryDate" placeholder="Expiry Date" required>
        <button type="submit">Add to Fridge</button>
      </form>
    </div>
    <!-- Fridge Overview List -->
    <div>
      <h2>Items added</h2>
      <ul>
        <li v-for="item in foodItems" :key="item.id">
          {{ item.name }} - Quantity: {{ item.quantity }}, Expiry: {{ item.expiryDate }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      newFoodItem: {
        name: '',
        quantity: 1,
        expiryDate: ''
      },
      foodItems: []
    }
  },
  methods: {
    addFoodItem() {
      const newItem = {
        ...this.newFoodItem,
        id: Date.now() // Simple way to generate unique IDs
      };
      this.foodItems.push(newItem);
      this.resetForm();
    },
    resetForm() {
      this.newFoodItem = { name: '', quantity: 1, expiryDate: '' };
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

form input, form button {
  margin: 0.5rem;
}
</style>