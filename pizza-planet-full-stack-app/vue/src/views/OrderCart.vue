<template>
    <v-container>
        <h1 class="banner">Your Cart</h1>
            <div class="bigDivv border">
            <v-list>
                <v-list-item-group>
                    <v-subheader>Pizzas:</v-subheader>
                            <order-item v-for="(pizza, index) in pizzas" :key="index" :pizza="pizza"/>
                            <!-- <order-non-pizza-item v-for="(nonPizza, index) in nonPizzas" :key="index + nonPizza.itemType" :nonPizza="nonPizza"/> -->
                            <order-non-pizza-item v-for="(nonPizza, index) in appetizers" :key="index + nonPizza.itemType" :nonPizza="nonPizza"/>
                            <order-non-pizza-item v-for="(nonPizza, index) in salads" :key="index + nonPizza.itemType" :nonPizza="nonPizza"/>
                            <order-non-pizza-item v-for="(nonPizza, index) in sides" :key="index + nonPizza.itemType" :nonPizza="nonPizza"/>
                            <order-non-pizza-item v-for="(nonPizza, index) in drinks" :key="index + nonPizza.itemType" :nonPizza="nonPizza"/>



                </v-list-item-group>
            </v-list>
            <v-divider></v-divider>
            <v-list>
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title>
                            Total: ${{ totalPrice.toFixed(2) }}
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title id="uhuh">
                            <v-btn to="/menu">Keep Shopping</v-btn>
                        </v-list-item-title>
                        <v-list-item-title>
                            <v-btn @click="handleClick()">Checkout</v-btn>
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
            </div>
        </v-container>
</template>

<script>
import OrderItem from '../components/OrderItem.vue'
import OrderNonPizzaItem from '../components/OrderNonPizzaItem.vue'
export default {
    name: "order-cart",
    components: {
        OrderItem, OrderNonPizzaItem
    },
    data() {
        return {
            // orderCart: [],
        }
    },
    methods: {
        updateOrderPrice() {
            this.$store.commit("SET_ORDER_TOTAL", this.totalPrice)
        },
        handleClick() {
            this.updateOrderPrice();
            this.$router.push('/checkout');
        }
    },
    computed: {
        pizzas() {
            return this.$store.state.order.pizzas
        },

        // nonPizzas() {
        //     let nonPizzaArray = [];
        //     this.$store.state.order.nonPizzaItems.appetizers.forEach(item => nonPizzaArray.push(item));
        //     this.$store.state.order.nonPizzaItems.salads.forEach(item => nonPizzaArray.push(item));
        //     this.$store.state.order.nonPizzaItems.sides.forEach(item => nonPizzaArray.push(item));
        //     this.$store.state.order.nonPizzaItems.drinks.forEach(item => nonPizzaArray.push(item));
        //     return nonPizzaArray
        // },

        //REPLACE nonPizzas()
        appetizers(){
            let appetizers = [];
            this.$store.state.order.appetizers.forEach(item => appetizers.push(item));
            return appetizers;
        },
        salads(){
            let salads = [];
            this.$store.state.order.salads.forEach(item => salads.push(item));
            return salads;
        },
         sides(){
            let sides = [];
            this.$store.state.order.sides.forEach(item => sides.push(item));
            return sides;
        },
         drinks(){
            let drinks = [];
            this.$store.state.order.drinks.forEach(item => drinks.push(item));
            return drinks;
        },

        totalPizza() {
            return this.$store.state.order.pizzas.reduce((total, pizza) => total + pizza.price, 0);
        },

        totalPrice() {
            let pizzas = this.$store.state.order.pizzas;
            // let appetizers = this.$store.state.order.nonPizzaItems.appetizers;
            // let salads = this.$store.state.order.nonPizzaItems.salads;
            // let sides = this.$store.state.order.nonPizzaItems.sides;
            // let drinks = this.$store.state.order.nonPizzaItems.drinks;

            let appetizers = this.$store.state.order.appetizers;
            let salads = this.$store.state.order.salads;
            let sides = this.$store.state.order.sides;
            let drinks = this.$store.state.order.drinks;

            let pizzaPrice = 0;
            if(pizzas) {
                pizzaPrice = pizzas.reduce((total, pizza) => total + pizza.price, 0);
            }

            let appetizerPrice = 0;
            if(appetizers){
                appetizerPrice = appetizers.reduce((total, appetizer) => total + appetizer.itemPrice, 0);
            }

            let saladPrice = 0;
            if(salads) {
                saladPrice = salads.reduce((total, salad) => total + salad.itemPrice, 0);
            }

            let sidePrice = 0;
            if(sides) {
                sidePrice = sides.reduce((total, side) => total + side.itemPrice, 0);
            }

            let drinkPrice = 0;
            if(drinks) {
                drinkPrice = drinks.reduce((total, drink) => total + drink.itemPrice, 0);
            }

            return pizzaPrice + appetizerPrice + saladPrice + sidePrice + drinkPrice;
        }
    },
    
 }

</script>

<style>

.banner {
    background-color: #C67CF7;
    border-style: solid;
    padding: 5px;
    margin-bottom: 5px;
    /* color: black;
  -webkit-text-fill-color: white; 
  -webkit-text-stroke-width: 1px;
  -webkit-text-stroke-color: black;
  font-size: 60px; */
}

.border {
    border-style: solid;
    padding: 5px;
    /* border-radius: 32px; */
}
.bigDivv {
  border: 3px solid #fff;
  padding: 0px;
  box-shadow: 
  0 0 3px #fff,
  0 0 3px #fff,
  0 0 3px #bc13fe,
  0 0 12px #bc13fe,
  0 0 45px #bc13fe,
  inset 0 0 21px #bc13fe;
}
#uhuh {
    margin-bottom: 8px;
}

</style>