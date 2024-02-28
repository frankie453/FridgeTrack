<template>
  <div class="search-container">
      <input type="text" class="search-box" placeholder="Search items...">
      <button class="search-icon-btn">
        <!-- You can use an SVG or a font icon for the search icon -->
        <svg class="search-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
      </button>
    </div>
  <Fridge />
</template>

<script>
import Fridge from './components/Fridge.vue'

export default {
  name: 'App',
  components: {
    Fridge
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
/* Add/modify in <style> of Fridge.vue */
/* Default to 8 columns for larger screens (PCs) */
.fridge {
  display: grid;
  grid-template-columns: repeat(8, 1fr); /* 8 columns */
  gap: 20px;
  padding: 20px;
}
@media (max-width: 1200px) {
  .fridge {
    grid-template-columns: repeat(4, 1fr); /* Adjust to 2 columns for smaller screens */
  }
}

/* Use a media query for smaller screens (phones) */
@media (max-width: 900px) {
  .fridge {
    grid-template-columns: repeat(2, 1fr); /* Adjust to 2 columns for smaller screens */
  }
}
.search-container {
  display: flex;
  justify-content: center;
  margin: 20px;
}

.search-box {
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 20px 0 0 20px; /* Rounded left corners */
  outline: none; /* Removes the outline */
  width: 300px; /* Adjust based on your design */
}

.search-icon-btn {
  padding: 10px;
  background: #ddd;
  border: none;
  border-radius: 0 20px 20px 0; /* Rounded right corners */
  cursor: pointer; /* Changes cursor to pointer on hover */
}

.search-icon {
  height: 20px; /* Adjust icon size */
  width: 20px; /* Adjust icon size */
  fill: #333; /* Icon color */
}

/* Optional: Add hover effect for the button */
.search-icon-btn:hover {
  background: #ccc;
}

</style>
