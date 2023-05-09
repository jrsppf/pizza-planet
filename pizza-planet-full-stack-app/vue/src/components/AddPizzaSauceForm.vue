<template>
  <v-container class="mx-auto fill-height ">
    <v-sheet align="center" justify="center" class="d-flex flex-row glow-border">
      <v-sheet>
        <v-form @submit.prevent>
            <v-text-field
                label="Sauce Name"
                v-model="sauce.name"
                autofocus
            >
            </v-text-field>
            <v-text-field
                label="Additional Price"
                v-model="sauce.price"
                autofocus
            >
            </v-text-field>
            <v-radio-group v-model="sauce.availability">
                <v-radio label="Unavailable" v-bind:value="'UNAVAILABLE'"></v-radio>
                <v-radio label="Low Stock" v-bind:value="'LOW STOCK'"></v-radio>
                <v-radio label="In Stock" v-bind:value="'IN STOCK'"></v-radio>
            </v-radio-group>


            <v-btn @click="saveSauce">Save Sauce Option</v-btn>

        </v-form>
      </v-sheet>
      
    </v-sheet>
  </v-container>
</template>

<script>
import sauceServices from '../services/SauceServices';

export default {
  name: "add-pizza-sauce-form",
  props: {
  },
  components: {},
  data() {
    return {
        sauce: {
            name: "",
            price: 0,
            availability: ""
        }
    }
  },
  methods: {

      saveSauce() {
        console.log(this.sauce);
          sauceServices
          .post(this.sauce)
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