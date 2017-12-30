#Simle API Test After Lunch

1.Start API Server
> ./gradlew bootRun

2.Call Post API
> http://localhost:8084/api/v1/orders/

With example Data

```json
{
  "orderItems": [
    {
      "skuSnapShot": {
        "skuId": 1,
        "price": 2.00
      },
      "amount": 1
    }
  ],
  "customerContact": {
    "name": "李四",
    "province": "北京市",
    "city": "北京",
    "area": "东城区",
    "street": "东直门内大街",
    "more_details": "国华投资大厦1105室",
    "adress": "北京市北京东城区东直门内大街国华投资大厦1105室"
  }
}
```

3.Get Created Resource with API
> http://localhost:8084/api/v1/orders/1

4. Get orderItems with pagination
>http://localhost:8084/api/v1/orders/1/orderItems?page=0&size=2

```


```