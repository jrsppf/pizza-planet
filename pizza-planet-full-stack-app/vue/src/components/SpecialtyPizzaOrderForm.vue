<template>
  <v-container class="mx-auto fill-height ">
    <v-sheet id="goo" align="center" justify="center" class="d-flex flex-row glow-border">
      <v-sheet>
        <v-form @submit.prevent>
          <div>
            <h1>Pizza Order Options</h1>
            
            <div id="bigDiv">
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
          </div>
        </v-form>
      </v-sheet>
      

      <v-sheet id="ga">
        <h2>Order Summary</h2>

          <h4>{{this.pizza.name}}</h4>

          <p>{{this.pizza.pizzaDescription}}</p>
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
  name: "specialty-pizza-order-form",
  props: {
    pizzaName: String,
    pizzaSauce: String,
    pizzaCrust: String,
    pizzaId: String,
    pizzaToppings: Array,
    pizzaPremiumToppings: Array,
    product: Object,
    pizzaDescription: String
  },
  components: {},
  data() {
    return {
      pizza: {
        name: "",
        toppings: [],
        premiumToppings: [],
        sauce:     {
          sauceId: "",
          availability: null,
          name: "",
          price: 0.00
        },
        crust: {
          crustId: "",
          availability: null,
          name: "",
          price: 0
        },
        size: null,
        imgPath: "plate1.png",
        pizzaId: "",
        pizzaDescription: ""
      },

    };
  },
  methods: {
    addToCart() {
      const pizzaCrust = this.$route.params.pizzaCrust;
      this.pizza.crust.name = pizzaCrust;

      const crust = this.$store.state.crust.find(crust => crust.name === pizzaCrust);
      const crustId = crust ? crust.crustId : null;
      this.pizza.crust.crustId = crustId





      const pizzaId = this.$route.params.pizzaId;
      this.pizza.pizzaId = pizzaId

      const premiumPizzaToppings = JSON.parse(JSON.stringify(this.$route.params.product.premiumToppings));
      this.pizza.premiumToppings = premiumPizzaToppings;
      console.log(this.premiumToppings)

      const pizzaToppings = JSON.parse(JSON.stringify(this.$route.params.product.toppings));
      this.pizza.toppings = pizzaToppings;
      console.log(this.toppings)

      

      const pizzaSauce = this.$route.params.pizzaSauce;
      this.pizza.sauce.name = pizzaSauce;

      const sauce = this.$store.state.sauce.find(sauce => sauce.name === pizzaSauce);
      const sauceId = sauce ? sauce.sauceId : null;
      this.pizza.sauce.sauceId = sauceId



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
      let defaultPrice = 12.0;
    
      let sizePrice = this.pizza.size != null ? this.pizza.size.price : 0;

      return (
        defaultPrice +
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

  const pizzaDescription = this.$route.params.pizzaDescription;
  this.pizza.pizzaDescription = pizzaDescription;

  const pizzaName = this.$route.params.pizzaName;
    this.pizza.name = pizzaName;
    console.log(this.pizza.pizzaDescription)




  },

  

  

  
  
};
</script>

<style scoped>
#goo {
  padding: 10px;
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
#ga {
  width: 400px;
}
#bigDiv {
   margin-top: 30px;
}
</style>