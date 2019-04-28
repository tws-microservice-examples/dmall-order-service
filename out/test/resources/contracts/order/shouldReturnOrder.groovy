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
                headers {
                    header 'Content-Type': 'application/json;charset=UTF-8'
                }
                body """
                    {
                        "data": {
                            "id": 1,
                            "attributes": {
                                "customerContact": null,
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
                url ('http://localhost:8084/api/v1/orders' ) {
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
                        "id": 1
                      } 
                    }
                """
            }
            response {
                status(200)
                headers {
                    header 'Content-Type': 'application/json;charset=UTF-8'
                }
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
            name('should get order Items by order id')
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
                headers {
                    header 'Content-Type': 'application/json;charset=UTF-8'
                }
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
        },

        Contract.make {
            name('should pay order success')
            request {
                method POST()
                url ('http://localhost:8084/api/v1/orders/1/events') {
                }
                headers {
                    header 'Accept': 'application/json'
                    header 'Content-Type': 'application/json'
                }
                body """
                    {
                      "ticketId": 1,
                      "ticketName": "PaymentRecord"
                    }
                """
            }
            response {
                status(200)
                headers {
                    header 'Content-Type': 'application/json;charset=UTF-8'
                }
                body """
                    {
                        "data": {
                            "id": 1,
                            "attributes": {
                                "uri": "/orders/1"
                            }
                        }
                    }
                """
            }
        },

        Contract.make {
            name('should cancel order success')
            request {
                method POST()
                url ('http://localhost:8084/api/v1/orders/1/events') {
                }
                headers {
                    header 'Accept': 'application/json'
                    header 'Content-Type': 'application/json'
                }
                body """
                    {
                      "ticketId": 1,
                      "ticketName": "CancelAcceptedRecord"
                    }
                """
            }
            response {
                status(200)
                headers {
                    header 'Content-Type': 'application/json;charset=UTF-8'
                }
                body """
                    {
                        "data": {
                            "id": 1,
                            "attributes": {
                                "uri": "/orders/1"
                            }
                        }
                    }
                """
            }
        }

]