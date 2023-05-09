<template>
  <v-container class="mx-auto fill-height ">
    <v-sheet align="center" justify="center" class="d-flex flex-row glow-border">
      <v-sheet>
        <v-form @submit.prevent>
            <v-text-field
                label="Crust Name"
                v-model="crust.name"
                autofocus
            >
            </v-text-field>
            <v-text-field
                label="Additional Price"
                v-model="crust.price"
                autofocus
            >
            </v-text-field>
            <v-radio-group v-model="crust.availability">
                <v-radio label="Unavailable" v-bind:value="'UNAVAILABLE'"></v-radio>
                <v-radio label="Low Stock" v-bind:value="'LOW STOCK'"></v-radio>
                <v-radio label="In Stock" v-bind:value="'IN STOCK'"></v-radio>
            </v-radio-group>


            <v-btn @click="saveCrust">Save Crust Option</v-btn>

        </v-form>
      </v-sheet>
      
    </v-sheet>
  </v-container>
</template>

<script>
import crustServices from '../services/CrustServices';

export default {
  name: "add-pizza-crust-form",
  props: {
  },
  components: {},
  data() {
    return {
        crust: {
            name: "",
            price: 0,
            availability: ""
        }
    }
  },
  methods: {

      saveCrust() {
        console.log(this.crust);
          crustServices
          .post(this.crust)
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