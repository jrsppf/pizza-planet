<template>
  <v-container class="mx-auto fill-height ">
    <v-sheet align="center" justify="center" class="d-flex flex-row glow-border">
      <v-sheet>
        <v-form @submit.prevent>
            <v-text-field
                label="Size Name"
                v-model="size.name"
                autofocus
            >
            </v-text-field>
            <v-text-field
                label="Additional Price"
                v-model="size.price"
                autofocus
            >
            </v-text-field>



            <v-btn @click="saveSize">Save Size Option</v-btn>

        </v-form>
      </v-sheet>
      
    </v-sheet>
  </v-container>
</template>

<script>
import sizeServices from '../services/SizeServices';

export default {
  name: "add-pizza-size-form",
  props: {
  },
  components: {},
  data() {
    return {
        size: {
            name: "",
            price: 0,
        }
    }
  },
  methods: {

      saveSize() {
          sizeServices
          .post(this.size)
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