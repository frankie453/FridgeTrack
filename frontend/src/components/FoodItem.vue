<template>
    <div class="food-item">
      <h3>{{ name }}</h3>
      <p>Expires on: {{ expiryDate }}</p>
      <button @click="emitDelete">Delete</button>
    </div>
  </template>
  
  <script>
  export default {
    name: 'FoodItem',
    props: {
      name: String,
      expiryDate: String,
      itemId: Number,
    },
    methods: {
      emitDelete() {
        console.log(this.itemId);
        this.$emit('deleteItem', this.itemId);
      }
    }
  };
  </script>
  
  <style>
.food-item {
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 10px;
  margin: 10px;
  transition: all 0.3s ease-in-out; 
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); 
}

.food-item:hover {
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2); 
  transform: translateY(-5px);
  border-color: #aaa; 
}
  </style>
  