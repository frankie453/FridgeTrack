<template>
    <div class="fridge">
      <FoodItem
        v-for="item in foodItems"
        :key="item.id"
        :itemId="item.id"
        :name="item.name"
        :expiryDate="item.expiryDate"
        @deleteItem="(itemId) => deleteFoodItem(itemId)" 
      />
    </div>
  </template>
  
  <script>
  import FoodItem from './FoodItem.vue';
  
  export default {
    name: 'FridgeDisplay',
    components: {
      FoodItem
    },
    data() {
      return {
        foodItems: [
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
        searchTerm: '',
      };
    },
    methods: {
      searchItems() {
        
      },
      deleteFoodItem(itemId) {
        this.foodItems = this.foodItems.filter(item => item.id !== itemId);
      },
    },

  };
  </script>
  
  <style>
  
.fridge {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
  padding: 20px;
}
  </style>
  