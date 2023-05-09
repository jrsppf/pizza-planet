<template>
  <v-container class="mx-auto fill-height ">
    <v-sheet align="center" justify="center" class="d-flex flex-row glow-border">
      <v-sheet>
        <v-form @submit.prevent>
            <v-text-field
                label="Topping Name"
                v-model="topping.name"
                autofocus
            >
            </v-text-field>

            <v-radio-group v-model="topping.availability">
                <v-radio label="Unavailable" v-bind:value="'UNAVAILABLE'"></v-radio>
                <v-radio label="Low Stock" v-bind:value="'LOW STOCK'"></v-radio>
                <v-radio label="In Stock" v-bind:value="'IN STOCK'"></v-radio>
            </v-radio-group>

            <v-btn @click="saveTopping">Save Topping Option</v-btn>

        </v-form>
      </v-sheet>
      
    </v-sheet>
  </v-container>
</template>

<script>
import toppingServices from '../services/ToppingServices';

export default {
  name: "add-pizza-premium-topping-form",
  props: {
    selectedItem: String,
  },
  components: {},
  data() {
    return {
        topping: {
            name: "",
            isPremium: true,
            price: 2.00,
            availability: "",

        }
    }
  },
  methods: {

      saveTopping() {
        console.log(this.topping);
          toppingServices
          .post(this.topping)
          .then(resp => {
              if (resp.status === 201 || resp.status === 200) {

          this.$router.push('/menu/modify-options')  
            }
        })
        
      }

 
  },    
  
};
</script>

<style>

</style>