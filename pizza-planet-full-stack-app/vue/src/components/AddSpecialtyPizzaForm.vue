<template>
  <v-container class="mx-auto fill-height ">
    <v-sheet align="center" justify="center" class="d-flex flex-row glow-border">
      <v-sheet>
        <v-form @submit.prevent>
          <div>
            <h1>New Specialty Pizza Options</h1>
            <v-text-field v-model="pizza.itemName" label="Pizza Name"></v-text-field>
            <v-text-field v-model="pizza.itemDesc" label="Pizza Description"></v-text-field>


            <v-list>
              <v-list-item-title>Toppings</v-list-item-title>
                <v-checkbox
                  v-for="topping in this.$store.state.toppings"
                  v-bind:key="topping.toppingId"
                  v-bind:label="topping.name"
                  v-bind:value="topping"
                  v-model="pizza.toppings"
                >
                </v-checkbox>
              </v-list>

            <v-list>
              <v-list-item-title>Premium Toppings</v-list-item-title>
              <v-checkbox
                v-for="premiumTopping in this.$store.state.premiumToppings"
                v-bind:key="premiumTopping.toppingId"
                v-bind:label="premiumTopping.name"
                v-bind:value="premiumTopping"
                v-model="pizza.premiumToppings"
              >
              </v-checkbox>
            </v-list>

            <v-list>
              <v-list-item-title>Sauce</v-list-item-title>
              <v-radio-group v-model="pizza.sauceName" row>
                <v-radio
                  v-for="sauce in this.$store.state.sauce"
                  v-bind:key="sauce.id"
                  v-bind:label="sauce.name"
                  v-bind:value="sauce.name"
                ></v-radio>
              </v-radio-group>
            </v-list>

            <v-list>
              <v-list-item-title>Crust</v-list-item-title>
              <v-radio-group v-model="pizza.crustName" row>
                <v-radio
                  v-for="crust in this.$store.state.crust"
                  v-bind:key="crust.id"
                  v-bind:label="crust.name"
                  v-bind:value="crust.name"
                ></v-radio>
              </v-radio-group>
            </v-list>

            <v-list-item-title>Make Pizza Active?</v-list-item-title>
            <v-radio-group v-model="pizza.isActive" row>
                <v-radio label="Yes" v-bind:value="true"></v-radio>
                <v-radio label="No" v-bind:value="false"></v-radio>
            </v-radio-group>

            <v-btn @click="saveSpecialtyPizza">Save New Specialty Pizza</v-btn>

          </div>
        </v-form>
      </v-sheet>
      

      <v-sheet align="center" justify="center">
        <h2>Pizza Summary</h2>
        <ul>
          <h4>Pizza Name: {{this.pizza.itemName}}</h4>
          <p>Pizza Description: {{this.pizza.itemDesc}}</p>
          <li v-if="pizza.toppings.length > 0">
            Toppings: {{ pizza.toppings.map(topping => topping.name).join(", ") }}
          </li>
          <li v-if="pizza.premiumToppings.length > 0">
            Premium Toppings: {{ pizza.premiumToppings.map(premiumTopping => premiumTopping.name).join(", ") }}
          </li>
          <li v-if="pizza.sauce">
            Sauce: {{ pizza.sauce}}
          </li>
          <li v-if="pizza.crust">
            Crust: {{ pizza.crust }}
          </li>
        </ul>
      </v-sheet>
    </v-sheet>
  </v-container>
</template>

<script>
import specialtyMenuServices from '../services/SpecialtyMenuServices';
import ToppingServices from "../services/ToppingServices";
import SauceServices from "../services/SauceServices";
import CrustServices from "../services/CrustServices";
import SizeServices from "../services/SizeServices";

export default {
  name: "add-specialty-pizza-form",
  props: {
    pizzaId: String,
  },
  components: {},
  data() {
    return {
      pizza: {
        itemName: "",
        itemDesc: "",
        toppings: [],
        premiumToppings: [],
        sauceName: "",
        crustName: "",
        imagePath: "plate1.png",
        itemPrice: 12,
        isActive: true,
        availability: "IN STOCK"
      },
    };
  },
  
  methods: {
    saveSpecialtyPizza() {
      console.log(this.pizza);
        specialtyMenuServices
        .post(this.pizza)
        .then(resp => {
            if (resp.status === 201 || resp.status === 200) {
        this.clearForm;
      this.$router.push('/menu')  
            }
        })
        
      }
    },
    clearForm() {
      this.pizza ={itemName: "",
        itemDesc: "",
        toppings: [],
        premiumToppings: [],
        sauce: "",
        crust: "",
        imagePath: "",
        itemPrice: 12,
        isActive: true,
        availability: "IN STOCK"}
    },
    created(){
    ToppingServices.getToppingByType("regular").then((resp) => {
      this.$store.commit("SET_TOPPINGS_MENU", resp.data);
      });

    ToppingServices.getToppingByType("premium").then((resp) => {
        this.$store.commit("SET_PREMIUMTOPPINGS_MENU", resp.data);
      });

      SauceServices.get().then((resp) => {
        this.$store.commit("SET_SAUCE_MENU", resp.data);
      });

      CrustServices.get().then((resp) => {
        this.$store.commit("SET_CRUST_MENU", resp.data);
      });

      SizeServices.get().then((resp) => {
        this.$store.commit("SET_SIZE_MENU", resp.data);
      });
    
  },
  
};
</script>

<style>

</style>