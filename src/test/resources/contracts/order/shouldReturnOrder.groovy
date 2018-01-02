package order

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            name('should get a exist order')
            request {
                method GET()
                url ('/api/v1/orders/1') {
                }
            }
            response {
                status(200)
                body """
                    {
                        "data": {
                            "id": 1,
                            "attributes": {
                                "customerContact": {
                                    "name": "张三",
                                    "province": "北京市",
                                    "city": "北京",
                                    "area": "东城区",
                                    "street": "东直门内大街",
                                    "more_details": "国华投资大厦1105室",
                                    "adress": "北京市北京东城区东直门内大街国华投资大厦1105室"
                                },
                                "status": "NOT_COMPLETED",
                                "orderItems": "/api/v1/orders/1/orderItems"
                            }
                        }
                    }
                """
            }
        },


        Contract.make {
            name('should post a new orders success')
            request {
                method POST()
                url ('/api/v1/orders') {
                }
                headers {
                    header 'Accept': 'application/json'
                    header 'Content-Type': 'application/json'
                }
                body """
                    {
                      "orderItems": [{
                        "skuSnapShot":{
                          "skuId": 6009907
                        },
                        "amount":1
                      }],
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
                """
            }
            response {
                status(200)
                body """
                    {
                        "data": {
                            "id": 2,
                            "attributes": {
                                "uri": "/orders/2"
                            }
                        }
                    }
                """
            }
        },

        Contract.make {
            name('should  get order Items by order id')
            request {
                method GET()
                url ('http://localhost:8084/api/v1/orders/1/orderItems?page=0&size=2') {
                }
                headers {
                    header 'Accept': 'application/json'
                    header 'Content-Type': 'application/json'
                }
            }
            response {
                status(200)
                body """
                    {
                        "data": {
                            "id": 1,
                            "attributes": [
                                {
                                    "amount": 1,
                                    "skuSnapShot": {
                                        "skuId": 3,
                                        "price": 6
                                    }
                                },
                                {
                                    "amount": 4,
                                    "skuSnapShot": {
                                        "skuId": 2,
                                        "price": 4
                                    }
                                }
                            ]
                        }
                    }
                """
            }
        }
]