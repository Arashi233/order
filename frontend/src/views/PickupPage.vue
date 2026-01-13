<template>
  <div style="padding:20px">
    <h2>受取ページ（受取待ち）</h2>
    <div v-for="o in readyOrders" :key="o.id" style="padding:8px;border:1px solid #ddd;margin-bottom:8px">
      <div>ID: {{ o.id }} | {{ o.items.join(', ') }}</div>
      <div>時間: {{ format(o.createdAt) }}</div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { connect, disconnect } from '../ws'

export default {
  data() {
    return { readyOrders: [] }
  },
  methods: {
    async load() {
      const res = await axios.get('http://localhost:8080/api/orders?status=READY')
      this.readyOrders = res.data
    },
    format(dt){
      return new Date(dt).toLocaleString()
    },
    onMessage(msg){
      this.load()
    }
  },
  mounted(){
    this.load()
    connect(this.onMessage)
  },
  beforeUnmount(){
    disconnect()
  }
}
</script>