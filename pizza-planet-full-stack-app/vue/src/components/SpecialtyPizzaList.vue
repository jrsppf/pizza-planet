<template>

    <div class="order-item-container">
        <v-row>
            <v-col sm="4" v-for="(product) in availableSpecialtyPizzas" :key="product.itemName">
                <SpecialtyPizzaCard :product="product" @update-active="toggleActive"/>
            </v-col>
        </v-row>
    </div>
</template>

<script>

import SpecialtyPizzaCard from './SpecialtyPizzaCard.vue';
import SpecialtyMenuServices from '../services/SpecialtyMenuServices';

export default {
  name: "specialty-pizza-list",
  components: {
      SpecialtyPizzaCard
    },
    data() {
        return{}
    },
    methods: {
        toggleActive(product) {
            product.isActive = !product.isActive;
            SpecialtyMenuServices
                .updateStatus(product)
                .then(() => {
              })
                .catch(error => {
                    console.log(error, "updating");
                    product.isActive = !product.isActive;
                });
        }
        
    },
    computed: {  
    availableSpecialtyPizzas() {
        if (this.$store.state.token != '') {
            return this.$store.state.pizzaMenu;
        } else {
            return this.$store.state.pizzaMenu.filter(pizza => pizza.isActive)
        }
    }
    },

    created(){
        //Make Your Own pizza item is part of the list
        SpecialtyMenuServices.get().then(response =>{
            // this.$store.commit('SET_PIZZA_MENU', response.data.pizzas);
             this.$store.commit('SET_PIZZA_MENU', response.data);
        })
    },


};



</script>

<style>
.order-item-container {
    /* display:flex;
    justify-content: space-evenly;
    align-items: flex-start;
    flex-wrap: wrap; */

  display: grid;
  grid-template-columns: repeat(3);
  grid-gap: 10px;
}
</style>