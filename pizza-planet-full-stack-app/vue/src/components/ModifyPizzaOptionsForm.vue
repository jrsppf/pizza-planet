<template>
  <v-container class="mx-auto fill-height ">
    <v-sheet id="foo" align="center" justify="center" class="d-flex flex-row glow-border">
      <v-sheet id="hello">
        <v-form @submit.prevent>
          <div>
            <h1>Available Pizza Options</h1>
            <v-select
                label="Select Pizza Option"
                :items="[
                {text:'Toppings'}, 
                {text:'Premium Toppings'},
                {text:'Sauce'}, 
                {text:'Crust'},
                {text: 'Size'},
                ]"
            v-model="selectedItem"
            variant="solo"
            @change="updateLocalToppings()"
            ></v-select>

            <v-list v-show="selectedItem === 'Toppings'">
              
              <v-list-item-title>Toppings</v-list-item-title>
                <v-btn :to="{ name: 'add-pizza-topping-form'}" text  class="text-none">Add Topping Option<v-icon small right>fa-plus</v-icon></v-btn>
                <v-list-item 
                    v-for="topping in this.toppings"
                    v-bind:key="topping.name"
                >
                    <v-list-item-title>{{ topping.name }}</v-list-item-title>
                    <v-list-item-action>
                        <v-radio-group v-model="topping.availability">
                            <v-radio label="Unavailable" v-bind:value="'UNAVAILABLE'"></v-radio>
                            <v-radio label="Low Stock" v-bind:value="'LOW STOCK'"></v-radio>
                            <v-radio label="In Stock" v-bind:value="'IN STOCK'"></v-radio>
                        </v-radio-group>
                    </v-list-item-action>
                </v-list-item>
              </v-list>

            <v-list v-show="selectedItem === 'Premium Toppings'">
                <v-list-item-title>Premium Toppings</v-list-item-title>
                <v-btn :to="{ name: 'add-pizza-topping-form' }" text class="text-none">Add Premium Topping Option<v-icon small right>fa-plus</v-icon></v-btn>
                    <v-list-item 
                        v-for="premiumTopping in this.premiumToppings"
                        v-bind:key="premiumTopping.name"
                    >
                        <v-list-item-title>{{ premiumTopping.name }}</v-list-item-title>
                        <v-list-item-action>
                            <v-radio-group v-model="premiumTopping.availability">
                                <v-radio label="Unavailable" v-bind:value="'UNAVAILABLE'"></v-radio>
                                <v-radio label="Low Stock" v-bind:value="'LOW STOCK'"></v-radio>
                                <v-radio label="In Stock" v-bind:value="'IN STOCK'"></v-radio>
                            </v-radio-group>
                        </v-list-item-action>
                    </v-list-item>
                </v-list>

            <v-list v-show="selectedItem === 'Sauce'">
                <v-list-item-title>Sauce</v-list-item-title>
                <v-btn :to="{ name: 'add-pizza-sauce-form' }" text class="text-none">Add Sauce Option<v-icon small right>fa-plus</v-icon></v-btn>
                    <v-list-item 
                        v-for="sauce in this.sauces"
                        v-bind:key="sauce.name"
                    >
                        <v-list-item-title>{{ sauce.name }}</v-list-item-title>
                        <v-list-item-action>
                            <v-radio-group v-model="sauce.availability" >
                                <v-radio label="Unavailable" v-bind:value="'UNAVAILABLE'"></v-radio>
                                <v-radio label="Low Stock" v-bind:value="'LOW STOCK'"></v-radio>
                                <v-radio label="In Stock" v-bind:value="'IN STOCK'"></v-radio>
                            </v-radio-group>
                        </v-list-item-action>
                    </v-list-item>
                </v-list>

            <v-list v-show="selectedItem === 'Crust'">
                <v-list-item-title>Crust</v-list-item-title>
                <v-btn :to="{ name: 'add-pizza-crust-form'}" text  class="text-none">Add Crust Option<v-icon small right>fa-plus</v-icon></v-btn>
                    <v-list-item 
                        v-for="crust in this.crusts"
                        v-bind:key="crust.name"
                    >
                    <v-list-item-title>{{ crust.name }}</v-list-item-title>
                    <v-list-item-action>
                        <v-radio-group v-model="crust.availability">
                                <v-radio label="Unavailable" v-bind:value="'UNAVAILABLE'"></v-radio>
                                <v-radio label="Low Stock" v-bind:value="'LOW STOCK'"></v-radio>
                                <v-radio label="In Stock" v-bind:value="'IN STOCK'"></v-radio>
                        </v-radio-group>
                    </v-list-item-action>
                </v-list-item>
            </v-list>

            <v-list v-show="selectedItem === 'Size'">
                <v-list-item-title>Size</v-list-item-title>
                <v-btn :to="{ name: 'add-pizza-size-form'}" text  class="text-none">Add Size Option<v-icon small right>fa-plus</v-icon></v-btn>
                    <v-list-item 
                        v-for="size in this.sizes"
                        v-bind:key="size.name"
                    >
                    <v-list-item-title>{{ size.name }}</v-list-item-title>
                </v-list-item>
            </v-list>

            <v-btn @click="handleClick">Save Pizza Options</v-btn>

          </div>
        </v-form>
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
  name: "modify-pizza-options-form",
  props: {
  },
  components: {},
  data() {
    return {
        selectedItem: null,
        toppings: null,
        premiumToppings: null,
        sauces: null,
        crusts: null,
        sizes: null,
    }
  },
  methods: {
      handleClick(){
          this.updateToppingAvailability;
          this.updateSauceAvailability;
          this.updateCrustAvailability;
          this.$router.push('/employee-main-page');
      },
      updateToppingAvailability() {
          this.toppings.forEach(topping => {
              ToppingServices.put(topping.id, topping);
          });
          this.$router.push('/employee-main-=page');
            
      },
      updateSauceAvailability() {
          this.sauces.forEach(sauce => {
              SauceServices.put(sauce.id, sauce);
          });
          this.$router.push('/employee-main-page');
      },

      updateCrustAvailability() {
          this.crusts.forEach(crust => {
              CrustServices.put(crust.id, crust);
          });
          this.$router.push('/employee-main-page');
      },

      updateLocalToppings() {
          ToppingServices.getToppingByType("regular").then(response => {
              this.toppings = response.data;
          });
          ToppingServices.getToppingByType("premium").then(response => {
              this.premiumToppings = response.data;
          });
          SauceServices.get().then(response => {
              this.sauces = response.data;
          });
          CrustServices.get().then(response => {
              this.crusts = response.data;
          });
          SizeServices.get().then(response => {
              this.sizes = response.data;
          })
      }
    },


    // clearForm() {
    //   this.pizza.toppings = [];
    //   this.pizza.premiumToppings = [];
    //   this.pizza.sauce = null;
    //   this.pizza.crust = null;
    // },
  
};
</script>

<style scoped>
#foo {
    width: 375px;
}

</style>