## Commands to start program

> mvn clean compile\
> mvn spring-boot:run

### H2 Url

> http://localhost:8080/h2 \
> JDBC URL: jdbc:h2:mem:mydb
> Username: root

### API 2

> GET http://localhost:8080/latest-prices

---

### API 3

> POST http://localhost:8080/trade

```json
{
  "symbol": "BTCUSDT",
  "action": "BUY",
  "amount": "25000"
}
```

##### Possible value:

> symbol - "BTCUSDT", "ETHUSDT"\
> action - "BUY", "SELL"\
> amount - The quantity of cryptocurrency you have and want to use, the unit of the amount is depends on the symbol and action

| Symbol  | Action | Unit | Outcome              |
| ------- | ------ | ---- | -------------------- |
| BTCUSDT | BUY    | USDT | Deduct USDT, Add BTC |
| BTCUSDT | SELL   | BTC  | Deduct BTC, Add USDT |
| ETHUSDT | BUY    | USDT | Deduct USDT, Add ETH |
| ETHUSDT | SELL   | ETH  | Deduct ETH, Add USDT |

---

### API 4

> GET http://localhost:8080/wallet-balances

---

### API 5

> GET http://localhost:8080/order-histories

---
