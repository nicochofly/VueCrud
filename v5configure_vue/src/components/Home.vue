<template>
<div>
  <nav-menu class="nav"></nav-menu>
  <router-view/>
</div>
</template>

<script>
import NavMenu from "@/components/common/NavMenu";
export default {
  name: "Home",
  components: {NavMenu}
}
</script>

<style scoped>
.nav{
  margin-left:auto; margin-right:auto;
}
</style>
