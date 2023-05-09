<template>
    <v-sheet outlined color="transparent" class="wrapper">
        
        <div class="title">
            <div>
            <router-link v-bind:to="{ name: 'add-specialty-pizza-form' }" v-if="$store.state.token != ''" class="background">Add a New Pizza</router-link>
        </div>
        <div>
            <router-link v-bind:to="{ name: 'modify-pizza-options-form' }" v-if="$store.state.token != ''" class="background">Edit Pizza Options</router-link>
        </div>
        
            <v-row align="center my-5" justify="center">
                <h1 class="banner">Pizza Planet Menu!</h1>
            </v-row>
        </div>
        <div class="content">
            <v-row>
                <v-col sm="10" offset-sm="1" lg="8" offset-lg="2">
                    <h2 class="banner">Pizzas!</h2>
                </v-col>
            </v-row>
            <v-row>
                <v-col sm="10" offset-sm="1" lg="8" offset-lg="2">
                    <specialty-pizza-list />
                </v-col>
            </v-row>
            <v-row>
                <v-col sm="10" offset-sm="1" lg="8" offset-lg="2">
                    <h2 class="banner">Appetizers!</h2>
                </v-col>
            </v-row>
            <v-row>
                <v-col sm="10" offset-sm="1" lg="8" offset-lg="2">
                    <appetizers-list/>
                </v-col>
            </v-row>
            <v-row>
                <v-col sm="10" offset-sm="1" lg="8" offset-lg="2">
                    <h2 class="banner">Salads!</h2>
                </v-col>
            </v-row>
            <v-row>
                <v-col sm="10" offset-sm="1" lg="8" offset-lg="2">
                    <salad-list/>
                </v-col>
            </v-row>
            <v-row>
                <v-col sm="10" offset-sm="1" lg="8" offset-lg="2">
                    <h2 class="banner">Sides!</h2>
                </v-col>
            </v-row>
            <v-row>
                <v-col sm="10" offset-sm="1" lg="8" offset-lg="2">
                    <side-list/>
                </v-col>
            </v-row>
            <v-row>
                <v-col sm="10" offset-sm="1" lg="8" offset-lg="2">
                    <h2 class="banner">Drinks!</h2>
                </v-col>
            </v-row>
            <v-row class="mb-5">
                <v-col sm="10" offset-sm="1" lg="8" offset-lg="2">
                    <drink-list/>
                </v-col>
            </v-row>
        </div>      
            
    </v-sheet>
    
</template>

<script>
import AppetizersList from '../components/AppetizersList.vue'
import SpecialtyPizzaList from '../components/SpecialtyPizzaList.vue'
import SaladList from '../components/SaladList.vue'
import SideList from '../components/SideList.vue'
import DrinkList from '../components/DrinkList.vue'

export default {
    components: {
        SpecialtyPizzaList,
        AppetizersList,
        SaladList,
        SideList,
        DrinkList
    },
    created() {
        console.log(this.$store.state.appetizers)
    }

}
</script>

<style>
    .background {
        background-color: white;
        border-style: solid 1px;
        padding: 3px;
    }
    .title {
        grid-area: title;
    }

    .content {
        grid-area: content;
    }
    
    .wrapper {
        display: grid;
        grid-template-columns: repeat(3);
        grid-template-areas:
        "title"
        "content"
    }
</style>