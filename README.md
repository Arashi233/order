# Order System

## 结构
- backend/ - Spring Boot 后端
- frontend/ - Vue 3 + Vite 前端

## 运行（开发）

1. 后端
```bash
cd backend
mvn spring-boot:run
```
后端运行在 http://localhost:8080
H2 控制台: http://localhost:8080/h2-console

2. 前端
```bash
cd frontend
npm install
npm run dev
```
前端运行在 http://localhost:5173

## 接口
- POST /api/orders  { items: ["菜1","菜2"] }
- GET /api/orders?status=NEW|READY|COMPLETED
- PUT /api/orders/{id}/advance

## WebSocket
- STOMP endpoint: ws://localhost:8080/ws (SockJS)
- Topic: /topic/orders
- メッセージ: { type: "CREATED" | "UPDATED", order: { ... } }

## Maven Wrapper の使用（グローバルな Maven 不要） ✅

ローカルに Maven がインストールされていない場合は、プロジェクト付属の Maven Wrapper を使用できます（Windows の例）：

1. `backend` ディレクトリで一度ダウンロードスクリプトを実行します（PowerShell）：

   cd backend
   .\setup-maven-wrapper.ps1

   スクリプトは `.mvn\wrapper\maven-wrapper.jar` をダウンロードします。

2. Wrapper を使って起動またはテストを実行します：

   - バックエンド起動：
     .\mvnw.cmd spring-boot:run

   - テスト実行：
     .\mvnw.cmd test

(Linux / macOS では `./mvnw spring-boot:run` または `./mvnw test` を使用します)

## 前端快速启动（回顾）
- `cd frontend`
- `npm install`
- `npm run dev`

## Windows 快速启动（开发） ✅
下面是常用的一键命令（Windows 环境）：

- 后端（使用 Maven wrapper）
  - `cd backend`
  - `.\mvnw.cmd spring-boot:run`

- 后端（使用项目脚本，设置 `JAVA_HOME` 并通过内置 Maven 启动）
  - `cd backend`
  - `.\run-backend.ps1`

- 后端（运行已打包的可执行 JAR）
  - `cd backend`
  - `java -jar target\order-backend-0.0.1-SNAPSHOT.jar`

- 前端（开发模式）
  - `cd frontend`
  - `npm install`
  - `npm run dev`

访问服务：
- 后端: `http://localhost:8080`
- 前端: `http://localhost:5173/`

