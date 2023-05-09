<template>
  <div class="home">
  
  </div>
</template>

<script>


export default {
  name: "home",
created(){
  if(this.$store.state.token === ''){
    this.$router.push("/customer-main-page");
  }else{
    this.$router.push("/employee-main-page");
  }
}
};
</script>

