<template>
    <!-- <v-list-item>
        <v-list-item-content>
            <v-list-item-title>
                {{ pizza.name }}
            </v-list-item-title>
            <v-list-item-subtitle>
                Toppings: {{ pizza.toppings.map(topping => topping.name).join(', ') }}
            </v-list-item-subtitle>
            <v-list-item-subtitle>
                Premium Toppings: {{ pizza.premiumToppings.map(premiumTopping => premiumTopping.name).join(', ') }}
            </v-list-item-subtitle>
            <v-list-item-subtitle>  
                Sauce: {{ pizza.sauce.name }}
            </v-list-item-subtitle>
            <v-list-item-subtitle>  
                Crust: {{ pizza.crust.name }}
            </v-list-item-subtitle>
            <v-list-item-subtitle>
                Size: {{ pizza.size.name }}
            </v-list-item-subtitle>
        </v-list-item-content>
        <v-list-item-content>
            <v-list-item-title>
                Subtotal: ${{ pizza.price.toFixed(2) }}
            </v-list-item-title>
        </v-list-item-content>
    </v-list-item> -->

    <v-card outlined min-width="100%" class="pa-4">
        <div class="d-flex">
            <div>
                <v-img 
                    :src="pizza.imgPath"
                    width="120px"
                    height="120px"
                />
             </div>
            <div class="d-flex flex-column justify-center">
                <v-card-title class="pt-0 text-4">
                    {{ pizza.name }}
                </v-card-title>
                <v-card-subtitle>
                    Subtotal: ${{ pizza.price.toFixed(2) }}
                </v-card-subtitle>
                <div class="ml-4">
                    <v-list-item-subtitle>
                        Size: {{ pizza.size.name }}
                    </v-list-item-subtitle>
                    <v-list-item-subtitle>
                    Toppings: {{ pizza.toppings.map(topping => topping.name).join(', ') }}
                    </v-list-item-subtitle>
                    <v-list-item-subtitle>
                        Premium Toppings: {{ pizza.premiumToppings.map(premiumTopping => premiumTopping.name).join(', ') }}
                    </v-list-item-subtitle>
                    <v-list-item-subtitle>  
                        Sauce: {{ pizza.sauce.name }}
                    </v-list-item-subtitle>
                    <v-list-item-subtitle>  
                        Crust: {{ pizza.crust.name }}
                    </v-list-item-subtitle>
                </div>
                <v-btn @click="removePizza" color="red" outlined small class="ml-4 mt-4">
                    <v-icon small left>fa-minus</v-icon>
                    Remove from Cart</v-btn>
            </div>
        </div>

    </v-card>
</template>

<script>
export default {
    name: "order-item",
    props: {
        pizza: Object,
    },
    methods: {
        removePizza() {
            this.$store.commit("DELETE_PIZZA", this.pizza)
        }
    },
      created() {
  },
    
}
</script>

<style>

</style>