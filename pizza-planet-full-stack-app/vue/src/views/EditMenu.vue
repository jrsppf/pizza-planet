<template>
  <div>
      This sucks
      I hate front end
      Does this even indent?
      Ugh
  </div>
</template>

<script>
import CrustServices from '../services/CrustServices'
import ToppingServices from '../services/ToppingServices'
import SizeServices from '../services/SizeServices'
import SauceServices from '../services/SauceServices'

export default {
    methods: {
        getCrusts() {
            let crustList = [];
            CrustServices.get().then(response => {crustList = response.data});
            return crustList;
        },

        getToppings() {
            let toppingList = [];
            ToppingServices.getToppingByType('regular').then(response => {toppingList = response.data.filter(resp => !resp.isPremium)});
            return toppingList;
        },

        getPremiumToppings() {
            let toppingList = [];
            ToppingServices.getToppingByType('premium').then(response => {toppingList = response.data.filter(resp => resp.isPremium)});
            return toppingList;
        },

        getSizes() {
            let sizeList = [];
            SizeServices.get().then(response => sizeList = response.data);
            return sizeList;
        },

        getSauces() {
            let sauceList = [];
            SauceServices.get().then(response => sauceList = response.data);
            return sauceList;
        },
    }
}
</script>

<style>

</style>