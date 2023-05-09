<template>
<v-sheet class="rounded">
  <v-card color="yellow">
    <v-img :src="imgPath" width="250px" class="mx-auto" />
    <v-card-title class="purrple">{{ product.itemName }}</v-card-title>
    <v-card-subtitle class="purrple">Starting from ${{ product.itemPrice }}</v-card-subtitle>
    <v-card-actions class="centered">
      <router-link
        v-bind:to="{
          name: 'pizza-order-form',
          params: { pizzaName: product.itemName },
        }"
        v-show="$store.state.token === '' && product.itemId == 1"
      >
        <v-btn>Order</v-btn>
      </router-link>

      <router-link
        v-bind:to="{
          name: 'specialty-pizza-order-form',
          params: { pizzaName: product.itemName, pizzaSauce: product.sauceName, pizzaCrust: product.crustName, 
          pizzaId: product.itemId, 
          pizzaToppings: product.toppings.map((topping) => topping.name),
          pizzaPremiumToppings: product.premiumToppings.map((premiumTopping) => premiumTopping.name), product: product,
          pizzaDescription: product.itemDesc },
        }"
        v-show="$store.state.token === '' && product.itemId != 1"
      >
        <v-btn>Order</v-btn>
      </router-link>
      <v-btn 
        @click="$emit('update-active', product)"
        v-show="$store.state.token != ''"
        >{{ product.isActive ? "Deactivate" : "Activate" }}</v-btn
      >
    </v-card-actions>
  </v-card>
</v-sheet>
</template>
<script>
export default {
  name: "specialty-pizza-card",
  props: {
    product: Object,
  },
  data() {
    return {
      images: [
        "pizza1.png",
        "pizza2.png",
        "pizza3.png",
        "pizza4.png",
        "pizza5.png",
        "pizza6.png",
      ],
    };
  },
  computed: {
    imgPath() {
      return require(`../assets/${this.product.imagePath}`);
      // return '';
    },
  },

    created() {
      console.log(this.product)
      console.log(this.product.itemDesc)

  },
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







