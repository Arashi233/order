import { Client } from '@stomp/stompjs'
import SockJS from 'sockjs-client/dist/sockjs'

let client = null

export function connect(onMessage) {
  client = new Client({
    webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
    reconnectDelay: 5000,
    debug: function(str) { /* console.log(str) */ }
  })

  client.onConnect = () => {
    client.subscribe('/topic/orders', message => {
      const body = JSON.parse(message.body)
      onMessage && onMessage(body)
    })
  }

  client.activate()
}

export function disconnect() {
  if (client) client.deactivate()
}