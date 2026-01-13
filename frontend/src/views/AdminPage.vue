<template>
  <div style="padding:20px;display:flex;gap:20px">
    <div style="flex:1">
      <h3>未完了の注文</h3>
      <div v-for="o in newOrders" :key="o.id" style="padding:8px;border:1px solid #ddd;margin-bottom:8px">
        <div>ID: {{ o.id }} | {{ o.items.join(', ') }}</div>
        <div>時間: {{ format(o.createdAt) }}</div>
        <button @click="complete(o.id)">受取待ちにする</button>
      </div>
    </div>
    <div style="flex:1">
      <h3>受取待ちの注文</h3>
      <div v-for="o in readyOrders" :key="o.id" style="padding:8px;border:1px solid #ddd;margin-bottom:8px">
        <div>ID: {{ o.id }} | {{ o.items.join(', ') }}</div>
        <div>時間: {{ format(o.createdAt) }}</div>
        <button @click="picked(o.id)">受け取り済み</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { connect, disconnect } from '../ws'

export default {
  data() {
    return {
      newOrders: [],
      readyOrders: []
    }
  },
  methods: {
    async load() {
      const resNew = await axios.get('http://localhost:8080/api/orders?status=NEW')
      this.newOrders = resNew.data
      const resReady = await axios.get('http://localhost:8080/api/orders?status=READY')
      this.readyOrders = resReady.data
    },
    format(dt){
      return new Date(dt).toLocaleString()
    },
    async complete(id){
      await axios.put(`http://localhost:8080/api/orders/${id}/advance`)
      await this.load()
    },
    async picked(id){
      await axios.put(`http://localhost:8080/api/orders/${id}/advance`)
      await this.load()
    },
    onMessage(msg){
      // reload lists (simple approach)
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