<template>

    <div class="order-item-container">
        <v-row>
            <!-- <v-col sm="4" v-for="(product) in $store.state.order.nonPizzaItems.appetizers.appetizers" :key="product.itemName"> -->
                <v-col sm="4" v-for="(product) in $store.state.appetizers.salads" :key="product.itemName">
                <NonPizzaCard :product="product"/>
            </v-col>
        </v-row>
    </div>
</template>

<script>

import NonPizzaCard from './NonPizzaCard.vue';
import MenuServices from '../services/MenuServices';

export default {
  name: "salad-list",
  components: {
      NonPizzaCard
    },
    data() {
        return{}
    },
    methods: {

    },
    created(){
        
        MenuServices.get().then(response =>{
            
             this.$store.commit('SET_APPETIZER_MENU', response.data);
        })
    }

};



</script>

<style>

</style>