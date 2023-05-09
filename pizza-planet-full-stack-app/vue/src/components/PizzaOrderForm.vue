<template>
  <v-container class="mx-auto fill-height ">
    <v-sheet align="center" justify="center" class="d-flex flex-row glow-border">
      <v-sheet>
        <v-form @submit.prevent>
          <div>
            <h1>Pizza Order Options</h1>
            <v-list>
              <v-list-item-title>Toppings</v-list-item-title>
                <v-checkbox
                  v-for="(topping, index) in availableToppings"
                  v-bind:key="index"
                  v-bind:label="itemCheckboxString(topping)"
                  v-bind:value="topping"
                  v-model="pizza.toppings"
                >
                </v-checkbox>
              </v-list>

            <v-list >
              <v-list-item-title>Premium Toppings</v-list-item-title>
              <v-checkbox
                v-for="(premiumTopping, index) in availablePremiumToppings"
                v-bind:key="index"
                v-bind:label="itemCheckboxString(premiumTopping)"
                v-bind:value="premiumTopping"
                v-model="pizza.premiumToppings"
              >
              </v-checkbox>
            </v-list>

            <v-list >
              <v-list-item-title>Sauce</v-list-item-title>
              <v-radio-group v-model="pizza.sauce" row>
                <v-radio
                  v-for="(sauce, index) in availableSauces"
                  v-bind:key="index"
                  v-bind:label="itemCheckboxString(sauce)"
                  v-bind:value="sauce"
                ></v-radio>
              </v-radio-group>
            </v-list>

            <v-list >
              <v-list-item-title>Crust</v-list-item-title>
              <v-radio-group v-model="pizza.crust" row>
                <v-radio
                  v-for="(crust, index) in availableCrusts"
                  v-bind:key="index"
                  v-bind:label="itemCheckboxString(crust)"
                  v-bind:value="crust"
                ></v-radio>
              </v-radio-group>
            </v-list>

            <div>Size</div>
            <v-radio-group v-model="pizza.size" row>
              <v-radio
                v-for="(size, index) in this.$store.state.size"
                v-bind:key="index"
                v-bind:label="itemCheckboxString(size)"
                v-bind:value="size"
                required
              ></v-radio>
            </v-radio-group>

            <v-btn @click="addToCart">Add to Cart</v-btn>

          </div>
        </v-form>
      </v-sheet>
      

      <v-sheet>
        <h2>Order Summary</h2>
        <ul class="ul">
          <h4>{{this.pizza.name}}</h4>
          <li v-if="pizza.toppings.length > 0">
            Toppings: {{ pizza.toppings.map(topping => topping.name).join(", ") }}
          </li>
          <li v-if="pizza.premiumToppings.length > 0">
            Premium Toppings: {{ pizza.premiumToppings.map(premiumTopping => premiumTopping.name).join(", ") }}
          </li>
          <li v-if="pizza.sauce">
            Sauce: {{ pizza.sauce.name }}
          </li>
          <li v-if="pizza.crust">
            Crust: {{ pizza.crust.name }}
          </li>
        </ul>
        <div padding>
          Price: ${{ price.toFixed(2) }}
        </div>
      </v-sheet>
    </v-sheet>
  </v-container>
</template>

<script>
import ToppingServices from '../services/ToppingServices'
import SauceServices from '../services/SauceServices'
import CrustServices from '../services/CrustServices'
import SizeServices from '../services/SizeServices'

export default {
  name: "pizza-order-form",
  props: {
    pizzaId: String
  },
  components: {},
  data() {
    return {
      pizza: {
        name: "",
        toppings: [],
        premiumToppings: [],
        sauce: null,
        crust: null,
        size: null,
        imgPath: "plate1.png",
        isCustomizable: false,
        pizzaId: ""

      },
    };
  },
  methods: {
    addToCart() {

      
      const orderItem = {
        toppings: this.pizza.toppings,
        premiumToppings: this.pizza.premiumToppings,
        sauce: this.pizza.sauce != null ? this.pizza.sauce : {},
        crust: this.pizza.crust != null ? this.pizza.crust : {},
        size: this.pizza.size,
        price: this.price,
        name: this.pizza.name,
        imgPath: this.pizza.imgPath,
        pizzaId: this.pizza.pizzaId

      };
      this.$store.commit("ADD_PIZZA_TO_ORDER", orderItem);
      console.log("Testing the pizza added to the order:")
      console.log(orderItem);


      this.clearForm();
      this.$router.push('/order-cart')
    },
    clearForm() {
      this.pizza.toppings = [];
      this.pizza.premiumToppings = [];
      this.pizza.sauce = null;
      this.pizza.crust = null;
      this.pizza.size = null;
    },
    itemCheckboxString(item) {
      if (item.price != 0) {
        return item.name + " (+$" + item.price.toFixed(2) + ")";
      } else {
        return item.name;
      }
    },
  },
  computed: {
    price() {
      let defaultPrice = 10.0;
      let toppingPrice = this.pizza.toppings.reduce((price, topping) => {
        return price + topping.price;
      }, 0);
      let premiumToppingPrice = this.pizza.premiumToppings.reduce(
        (price, premiumTopping) => {
          return price + premiumTopping.price;
        },
        0
      );
      let saucePrice = this.pizza.sauce != null ? this.pizza.sauce.price : 0;
      let crustPrice = this.pizza.crust != null ? this.pizza.crust.price : 0;
      let sizePrice = this.pizza.size != null ? this.pizza.size.price : 0;

      return (
        defaultPrice +
        toppingPrice +
        premiumToppingPrice +
        saucePrice +
        crustPrice +
        sizePrice
      );
    },
   

    availableToppings() {
      return this.$store.state.toppings.filter(topping => topping.availability !== "UNAVAILABLE")
    },
    availablePremiumToppings() {
      return this.$store.state.premiumToppings.filter(premiumTopping => premiumTopping.availability !== "UNAVAILABLE")
    },
    availableSauces() {
      return this.$store.state.sauce.filter(sauce => sauce.availability !== "UNAVAILABLE")
    },
    availableCrusts() {
      return this.$store.state.crust.filter(crust => crust.availability !== "UNAVAILABLE")
    },

    
  },
  created() {


    ToppingServices.getToppingByType('regular').then(resp => {
    this.$store.commit('SET_TOPPINGS_MENU', resp.data);
  })

  ToppingServices.getToppingByType('premium').then(resp => {
    this.$store.commit('SET_PREMIUMTOPPINGS_MENU', resp.data);
  })

  SauceServices.get().then(resp => {
    this.$store.commit('SET_SAUCE_MENU', resp.data);
  })

  CrustServices.get().then(resp => {
    this.$store.commit('SET_CRUST_MENU', resp.data);
  })

  SizeServices.get().then(resp => {
    this.$store.commit('SET_SIZE_MENU', resp.data);
  })
  
  },
  
};
</script>

<style scoped>
ul {
  list-style-type: none;
}
.glow-border {
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
</style>