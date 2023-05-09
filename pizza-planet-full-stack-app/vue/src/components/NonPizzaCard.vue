
<template>
<v-sheet class="rounded">
  <v-card color="yellow">
    <v-img
    :src="imgPath" width="250px"  class="mx-auto"/>
    <v-card-title class="purrple">{{ product.itemName }}</v-card-title>
    <v-card-subtitle class="purrple">${{ product.itemPrice.toFixed(2) }}</v-card-subtitle>
    <v-card-actions class="centered">
        <v-btn @click="handleClick()">Order</v-btn>
    </v-card-actions>
  </v-card>
  </v-sheet>
</template>

<script>
export default {
  name: "non-pizza-card",
  props: {
    product: Object,

  },
  data() {
    return {
    images: [
      'pizza1.png',
      'pizza2.png',
      'pizza3.png',
      'pizza4.png',
      'pizza5.png',
      'pizza6.png',
    ]
    }
  },
  computed: {
    imgPath() {
      // const randomIndex = Math.floor(Math.random() * 6);
      return require('../assets/' + this.product.imagePath);
      // return require('../assets/' + 'plate1.png');
    }
  },
  methods: {
    handleClick() {
      let itemType = this.product.itemType;
      if(itemType=="APPETIZER") {
        this.$store.state.order.appetizers.push(this.product);
        alert("Added Appetizer to Cart.");
      } else if(itemType=="SALAD") {
        this.$store.state.order.salads.push(this.product);
        alert("Added Salad to Cart.");
      } else if(itemType=="SIDE") {
        this.$store.state.order.sides.push(this.product);
        alert("Added Side to Cart.");
      } else if(itemType=="DRINK") {
        this.$store.state.order.drinks.push(this.product);
        alert("Added Drink to Cart.");
      }
    }
  }

};
</script>

<style scoped>
.mx-auto {
  height: 250px;
}
.purrple {
  background-color: #C67CF7;
}
.rounded {
  border: 3px solid #fff;
  border-radius: 32px;
  padding: 30px;
  box-shadow: 
  0 0 3px #fff,
  0 0 3px #fff,
  0 0 3px #bc13fe,
  0 0 12px #bc13fe,
  0 0 45px #bc13fe,
  inset 0 0 21px #bc13fe;
}
.centered {
  display: flex;
  justify-content: center;
}
</style>