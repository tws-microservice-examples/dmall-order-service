#Simle API Test After Lunch

1.Start API Server
> ./gradlew bootRun

2.Call Post API
> http://localhost:8084/api/orders/

With example Data

```json
{
	"data":{
		"attributes": {
			"name" : "Buy a Iphone",
			"province" : "Sichuang",
    		"city" : "Chengdu",
    		"area" : "JinNiu",
    		"street" : "Jiulidi",
    		"moreDetails" : "no",
    		 "price" : 8777,
    		 "amount" : 1,
    		"description" : "no more",
    		"brand" : "IPONE X",
    		"petId" : "001a"
		}	
	}
	
}
```

3.Get Created Resource with API
> http://localhost:8084/api/orders/1

Run API Server with DB H2 Included:
>gradle clean build

>java -jar build/libs/order-service-0.0.1.jar  --spring.profiles.active=local