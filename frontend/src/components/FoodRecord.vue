<template>
  <div id="app">
    <!-- Food Entry Form -->
    <div class="table">
      <h1>Add Food Item</h1>
      <form @submit.prevent="addFoodItem">
        <input class="select" type="text" v-model="newFoodItem.name" placeholder="Food Name" required>
        <input class="select" type="number" v-model="newFoodItem.quantity" placeholder="Quantity" required>
        <input class="select" type="date" v-model="newFoodItem.expiryDate" placeholder="Expiry Date" required>
        <button class="button" type="submit">Add to Fridge</button>
      </form>
    </div>
    <!-- Fridge Overview List -->
    <div>
      <h2>Items added</h2>
      <transition-group name="list" tag="ul">
        <li v-for="item in foodItems" :key="item.id">
          {{ item.name }} - Quantity: {{ item.quantity }}, Expiry: {{ item.expiryDate }}
        </li>
      </transition-group>
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
        id: Date.now()
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
.button{
  width: 35%;
  position: center;
  margin-left: auto;
  margin-right: auto;
}
form {
  
  display: flex;
  flex-direction: column;
  gap: 30px;
}
.table {
  max-width: 400px;
  margin-left: auto;
  margin-right: auto;
}

.select {
  width: 100%;
  height: 32px;
  border: 2px solid #007BFF;
  border-radius: 20px;
  padding: 5px 20px; 
  font-size: 16px;
  color: #333;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  outline: none;
  transition: border-color 0.3s ease, box-shadow 0.3s ease;
}
ul {
  list-style-type: none;
  padding: 0; 
  display: flex; 
  flex-direction: column; 
  align-items: center; 
}

li {
  max-width: 80%; 
  background-color: #f9f9f9;
  margin: 5px 0;
  padding: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s ease, transform 0.3s ease;
  cursor: pointer;
  text-align: center;
  border-radius: 5px;
}

li:hover {
  background-color: #e9e9e9;
  transform: scale(1.02);
}
</style>