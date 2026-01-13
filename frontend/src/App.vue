<template>
  <div>
    <nav style="padding:10px;border-bottom:1px solid #ddd">
      <a href="/" style="margin-right:10px">注文</a>
      <a href="/admin" style="margin-right:10px">管理</a>
      <a href="/pickup">受取</a>
    </nav>
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
