<template>
  <v-carousel class="carousel">
  <v-carousel-item 
    src="https://m.foolcdn.com/media/dubs/original_images/Slide_6_Pizza_GettyImages-621463552.jpg" 
    cover
  ></v-carousel-item>

  <v-carousel-item
    src="https://img.cdn4dd.com/cdn-cgi/image/fit=contain,width=1200,height=672,format=auto/https://doordash-static.s3.amazonaws.com/media/store/header/1f379d03-fd0a-48e6-a65c-41564573aa87.jpg" 
    cover
  ></v-carousel-item>

  <v-carousel-item
    src="https://images.unsplash.com/photo-1595708684082-a173bb3a06c5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1728&q=80"
    cover
  ></v-carousel-item>
</v-carousel>
</template>

<script>
export default {
  name: "slide-show"

}
</script>

<style>
.carousel {
  border: 3px solid #fff;
  padding: 1px;
  border-radius: 20px;
  box-shadow: 
  0 0 3px #fff,
  0 0 3px #fff,
  0 0 3px #bc13fe,
  0 0 12px #bc13fe,
  0 0 45px #bc13fe,
  inset 0 0 21px #bc13fe;
}

</style>