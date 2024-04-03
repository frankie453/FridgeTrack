<template>
  <div class="scan-page">
    <div class="title-section">
      <h1 class="page-title">Scan Your Receipt Or Photo of Food</h1>
    </div>
  <div class="dropbox" @click="triggerFileInput" @dragover.prevent @dragenter.prevent @drop="handleDrop">
    <input type="file" ref="fileInput" @change="handleFiles" accept="image/*" hidden />
    <div v-if="!imageSrc" class="dropbox-message">
      Drag and drop your receipt here or click to select a file.
    </div>
    <div v-else class="image-preview">
      <img :src="imageSrc" alt="Receipt Preview" />
    </div>
  </div>
    <button class="submit-button" @click="submitImage">Submit</button>
  </div>
    
</template>
  
  <script>
  export default {
    data() {
      return {
        imageSrc: null,
      };
    },
    methods: {
      triggerFileInput() {
        this.$refs.fileInput.click();
      },
      handleFiles(event) {
        const files = event.target.files || event.dataTransfer.files;
        if (!files.length) return;
        this.createImage(files[0]);
      },
      handleDrop(event) {
        this.handleFiles(event);
      },
      createImage(file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.imageSrc = e.target.result;
        };
        reader.readAsDataURL(file);
      },
      submitImage() {
        alert('Apple detected!!!!');
        this.imageSrc = null;
        this.$refs.fileInput.value = '';
      },
    },
  };
  </script>
  
  <style scoped>
    .scan-page {
      max-width: 800px;
      margin: auto;
      padding: 20px;
    }
    .submit-button {
      margin-top: 20px;
      padding: 10px 20px;
      background-color: #11a7c1;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s ease-in-out;
    }
    .submit-button:hover {
      background-color: #7db9e8;
    }

    .dropbox {
      border: 3px dashed #ccc; /* Slightly thicker border for better visibility */
      border-radius: 10px; /* More rounded corners for a softer look */
      padding: 40px; /* Increased padding for a larger drop area */
      text-align: center;
      cursor: pointer;
      transition: all 0.3s ease-in-out; /* Smooth transition for hover effects */
      width: 300px; /* Fixed width */
      height: 200px; /* Fixed height */
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      background-color: #f9f9f9; /* Light background color */
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
      margin: auto; /* Center the dropbox if it's the only element */
    }

    .dropbox:hover {
      border-color: #007bff; /* Brighter border color on hover */
      background-color: #e3f2fd; /* Slight background color change on hover */
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* More pronounced shadow on hover */
    }

    .dropbox-message {
      font-size: 18px; /* Slightly larger text for clarity */
      color: #333; /* Darker text color for better readability */
      margin-bottom: 10px; /* Space between message and optional content below */
    }

    .image-preview img {
      max-width: 100%;
      height: auto;
      border-radius: 5px; /* Rounded corners for the image */
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Shadow for the image for depth */
    }

    /* Animation for the border */
    @keyframes dash {
      to {
        stroke-dashoffset: 0;
      }
    }

    .dropbox.dropping {
      animation: dash 0.7s linear infinite;
      border-image: linear-gradient(to bottom, #007bff 0%, #33ccff 100%);
      border-image-slice: 1;
    }
    
  </style>
  