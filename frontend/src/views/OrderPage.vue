<template>
  <div style="padding:20px">
    <h2>注文ページ</h2>
    <div>
      <label v-for="item in menu" :key="item" style="display:block;margin:6px 0">
        <input type="checkbox" :value="item" v-model="selected" /> {{ item }}
      </label>
    </div>
    <button @click="submit" :disabled="selected.length===0">注文する</button>

    <div v-if="lastOrder" style="margin-top:20px;color:green">
      注文成功：ID {{ lastOrder.id }}、メニュー：{{ lastOrder.items.join(', ') }}
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      menu: ['ライス', 'チンジャオロース', 'マーボー豆腐', 'マーラータン', 'スープ'],
      selected: [],
      lastOrder: null
    }
  },
  methods: {
    async submit() {
      try {
        const res = await axios.post('http://localhost:8080/api/orders', { items: this.selected })
        this.lastOrder = res.data
        this.selected = []
      } catch (e) {
        alert('注文に失敗しました')
      }
    }
  }
}
</script>