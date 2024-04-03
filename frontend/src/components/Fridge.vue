<template>
  <div>
    <!-- Search Input -->
    <div class="search-wrapper">
      <label for="search-box" class="search-label" :class="{ 'active': isFocused || hasText }">Search your food... </label>
      <input
        type="text"
        id="search-box"
        class="search-box"
        v-model="searchText"
        @focus="isFocused = true"
        @blur="isFocused = false"
      />
    </div>
    <div class="fridge">
      <!-- Loop over filteredFoodItems instead of foodItems -->
      <FoodItem
        v-for="item in filteredFoodItems"
        :key="item.id"
        :itemId="item.id"
        :name="item.name"
        :expiryDate="item.expiryDate"
        @deleteItem="(itemId) => deleteFoodItem(itemId)" 
      />
    </div>
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
        searchText: '',
        isFocused: false,
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
    computed: {
    filteredFoodItems() {
      if (!this.searchTerm) return this.foodItems;
      return this.foodItems.filter((item) => 
        item.name.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    },
    hasText() {
      return this.searchText.length > 0;
    },
  },
  methods: {
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
.search-wrapper {
  margin-left: auto;
  margin-right: auto;
  margin-top: 4vh;
  position: relative;
  width: 300px;
}

.search-box {
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

.search-box:focus {
  border-color: #0056b3;
  box-shadow: 0 0 8px rgba(0, 123, 255, 0.5);
}

.search-label {
  position: absolute;
  left: 20px;
  top: 10px;
  transition: top 0.3s ease, font-size 0.3s ease, color 0.3s ease;
  color: #aaa;
  pointer-events: none;
}

.search-label.active {
  top: -20px; 
  font-size: 12px;
  color: #007BFF;
}
  </style>
  