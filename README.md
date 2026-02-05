## Aviation Route Planning System

Full-stack aviation route planning application with Spring Boot, PostgreSQL, and React.

### Database Configuration

#### PostgreSQL Connection Details

##### Application User (CRUD Operations)
```
Host: localhost
Port: 5432
Database: aviation_system
Username: aviation_app_user
Password: thy_password52
Connection Limit: 10
Permissions: SELECT, INSERT, UPDATE, DELETE
```

##### Read-Only User (Reports & Analytics)
```
Host: localhost
Port: 5432
Database: aviation_system
Username: aviation_readonly_user
Password: thy_read_only_password52
Connection Limit: 5
Permissions: SELECT only
```

#### Swagger

http://localhost:8080/swagger-ui/index.html


## Postman | Request Collection

### Request Collection

### 1. Locations (Batch Insert)
**URL:** `POST http://localhost:8080/api/locations/batch-insert-locations`

```json
[
  { "name": "Istanbul Airport", "country": "Turkey", "city": "Istanbul", "locationCode": "IST" },
  { "name": "Sabiha Gokcen", "country": "Turkey", "city": "Istanbul", "locationCode": "SAW" },
  { "name": "London Heathrow", "country": "UK", "city": "London", "locationCode": "LHR" },
  { "name": "London Gatwick", "country": "UK", "city": "London", "locationCode": "LGW" },
  { "name": "JFK International", "country": "USA", "city": "New York", "locationCode": "JFK" },
  { "name": "Paris Charles de Gaulle", "country": "France", "city": "Paris", "locationCode": "CDG" },
  { "name": "Ankara Esenboga", "country": "Turkey", "city": "Ankara", "locationCode": "ESB" },
  { "name": "Antalya Airport", "country": "Turkey", "city": "Antalya", "locationCode": "AYT" },
  { "name": "Taksim Square", "country": "Turkey", "city": "Istanbul", "locationCode": "TAKSIM" },
  { "name": "Kadikoy Pier", "country": "Turkey", "city": "Istanbul", "locationCode": "KADIKOY" },
  { "name": "Besiktas Center", "country": "Turkey", "city": "Istanbul", "locationCode": "BESIKTAS" },
  { "name": "London Piccadilly", "country": "UK", "city": "London", "locationCode": "PICAD" },
  { "name": "London Hyde Park", "country": "UK", "city": "London", "locationCode": "HYDE" },
  { "name": "Manhattan Times Sq", "country": "USA", "city": "New York", "locationCode": "MANHAT" }
]
```

### 2. Transportations (Batch Insert)
**URL:** `POST http://localhost:8080/api/transportations/batch-insert-transportations`

```json
[
  { "originLocationId": 10, "destinationLocationId": 2, "transportationType": "BUS", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 10, "destinationLocationId": 2, "transportationType": "UBER", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 10, "destinationLocationId": 2, "transportationType": "SUBWAY", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 10, "destinationLocationId": 1, "transportationType": "UBER", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 10, "destinationLocationId": 1, "transportationType": "BUS", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 9, "destinationLocationId": 1, "transportationType": "BUS", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 9, "destinationLocationId": 1, "transportationType": "SUBWAY", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 11, "destinationLocationId": 1, "transportationType": "BUS", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 11, "destinationLocationId": 1, "transportationType": "SUBWAY", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 11, "destinationLocationId": 1, "transportationType": "UBER", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 1, "destinationLocationId": 3, "transportationType": "FLIGHT", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 1, "destinationLocationId": 3, "transportationType": "FLIGHT", "operatingDays": "6,7" }, 
  { "originLocationId": 1, "destinationLocationId": 4, "transportationType": "FLIGHT", "operatingDays": "1,3,5,6" },
  { "originLocationId": 2, "destinationLocationId": 3, "transportationType": "FLIGHT", "operatingDays": "2,4,6" },
  { "originLocationId": 2, "destinationLocationId": 4, "transportationType": "FLIGHT", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 1, "destinationLocationId": 5, "transportationType": "FLIGHT", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 1, "destinationLocationId": 6, "transportationType": "FLIGHT", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 7, "destinationLocationId": 8, "transportationType": "FLIGHT", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 3, "destinationLocationId": 12, "transportationType": "SUBWAY", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 3, "destinationLocationId": 12, "transportationType": "UBER", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 4, "destinationLocationId": 12, "transportationType": "BUS", "operatingDays": "1,2,3,4,5,6,7" },
  { "originLocationId": 5, "destinationLocationId": 14, "transportationType": "UBER", "operatingDays": "1,2,3,4,5,6,7" }
]
```

### 3. Test Senaryoları ve Beklenen Sonuçlar

#### Senaryo 1: (10+ Rota)
**Hedef:** Kadıköy -> Londra (Piccadilly) - Cumartesi Günü
**Detay:** İstanbul'un 2 havalimanı ve Londra'nın 2 havalimanı arasındaki tüm çapraz uçuşlar ve yerel transferler.
**Hesaplama (Date: 2024-01-06 Cumartesi - Day 6):**
1.  **Yol A (Kadikoy->SAW->LHR->Picadilly):** 3 (Tr) * 1 (Fl) * 2 (Tr) = **6 Rota**
2.  **Yol B (Kadikoy->SAW->LGW->Picadilly):** 3 (Tr) * 1 (Fl) * 1 (Tr) = **3 Rota**
3.  **Yol C (Kadikoy->IST->LHR->Picadilly):** 2 (Tr) * 2 (Fl) * 2 (Tr) = **8 Rota**
4.  **Yol D (Kadikoy->IST->LGW->Picadilly):** 2 (Tr) * 1 (Fl) * 1 (Tr) = **2 Rota**
    **TOPLAM BEKLENEN:** 6 + 3 + 8 + 2 = **19 ADET ROTA**

```json
{ 
  "originCode": "KADIKOY", 
  "destinationCode": "PICAD", 
  "date": "2024-01-06" 
}
```

#### Senaryo 2: (~6 Rota)
**Hedef:** Beşiktaş -> Londra (Piccadilly) - Pazartesi Günü (Day 1)
**Hesaplama:**
1.  **Yol A (Besiktas -> IST -> LHR):** 3 (Tr) * 1 (Fl) * 2 (Tr) = **6 Rota**
2.  **Yol B (Besiktas -> IST -> LGW):** 3 (Tr) * 1 (Fl) * 1 (Tr) = **3 Rota**
    **TOPLAM BEKLENEN:** **9 ADET ROTA**

```json
{ 
  "originCode": "BESIKTAS", 
  "destinationCode": "PICAD", 
  "date": "2024-01-01" 
}
```

#### Senaryo 3: (1 Rota)
**Hedef:** Ankara -> Antalya - Herhangi bir gün
**Beklenen:** Sadece 1 adet direkt uçuş.
**Transfer:** Yok.

```json
{ 
  "originCode": "ESB", 
  "destinationCode": "AYT", 
  "date": "2024-01-01" 
}
```

#### Senaryo 4: (0 Rota)
**Hedef:** Ters İstikamet (Antalya -> Ankara)
**Beklenen:** 0 Rota (Çünkü dönüş uçuşu tanımlamadık).
```json
{ 
  "originCode": "AYT", 
  "destinationCode": "ESB", 
  "date": "2024-01-01" 
}
```


### Postman Collection
```json
{
  "info": {
    "_postman_id": "bd337eb1-1b72-4d76-bcdd-e1b44e168938",
    "name": "aviation_system",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
    "_exporter_id": "51042467",
    "_collection_link": "https://go.postman.co/collection/51042467-bd337eb1-1b72-4d76-bcdd-e1b44e168938?source=collection_link"
  },
  "item": [
    {
      "name": "location",
      "item": [
        {
          "name": "get-location-by-with-id",
          "request": {
            "method": "POST",
            "header": [],
            "url": {
              "raw": "http://localhost:8080/api/locations/get-location-by-with-id?id=3",
              "protocol": "http",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "locations",
                "get-location-by-with-id"
              ],
              "query": [
                {
                  "key": "id",
                  "value": "3"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "insert-location",
          "request": {
            "method": "POST",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"name\": \"Istanbul Airport - Updated\",\n  \"country\": \"Turkey\",\n  \"city\": \"Istanbul\",\n  \"locationCode\": \"IST\"\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "http://localhost:8080/api/locations/insert-location",
              "protocol": "http",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "locations",
                "insert-location"
              ]
            }
          },
          "response": []
        },
        {
          "name": "get-location-by-with-location-code",
          "request": {
            "method": "POST",
            "header": [],
            "url": {
              "raw": "http://localhost:8080/api/locations/get-location-by-with-location-code?locationCode=\"Test\"",
              "protocol": "http",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "locations",
                "get-location-by-with-location-code"
              ],
              "query": [
                {
                  "key": "locationCode",
                  "value": "\"Test\""
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "delete-location",
          "request": {
            "method": "DELETE",
            "header": [],
            "url": {
              "raw": "http://localhost:8080/api/locations/delete-location?id=13",
              "protocol": "http",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "locations",
                "delete-location"
              ],
              "query": [
                {
                  "key": "id",
                  "value": "13"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "update-location",
          "request": {
            "method": "PUT",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"id\" : 1, \n  \"name\": \"Istanbul Airport\",\n  \"country\": \"Ankara\",\n  \"city\": \"Istanbul\",\n  \"locationCode\": \"IST02\"\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "http://localhost:8080/api/locations/update-location",
              "protocol": "http",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "locations",
                "update-location"
              ]
            }
          },
          "response": []
        },
        {
          "name": "get-all-locations",
          "request": {
            "method": "GET",
            "header": [],
            "url": {
              "raw": "http://localhost:8080/api/locations/get-all-locations",
              "protocol": "http",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "locations",
                "get-all-locations"
              ]
            }
          },
          "response": []
        },
        {
          "name": "batch-insert-locations",
          "request": {
            "method": "POST",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": "[\n    {\n        \"name\": \"Istanbul Airport\",\n        \"country\": \"Turkey\",\n        \"city\": \"Istanbul\",\n        \"locationCode\": \"IST\"\n    },\n    {\n        \"name\": \"Sabiha Gokcen\",\n        \"country\": \"Turkey\",\n        \"city\": \"Istanbul\",\n        \"locationCode\": \"SAW\"\n    },\n    {\n        \"name\": \"London Heathrow\",\n        \"country\": \"UK\",\n        \"city\": \"London\",\n        \"locationCode\": \"LHR\"\n    },\n    {\n        \"name\": \"London Gatwick\",\n        \"country\": \"UK\",\n        \"city\": \"London\",\n        \"locationCode\": \"LGW\"\n    },\n    {\n        \"name\": \"JFK International\",\n        \"country\": \"USA\",\n        \"city\": \"New York\",\n        \"locationCode\": \"JFK\"\n    },\n    {\n        \"name\": \"Paris Charles de Gaulle\",\n        \"country\": \"France\",\n        \"city\": \"Paris\",\n        \"locationCode\": \"CDG\"\n    },\n    {\n        \"name\": \"Ankara Esenboga\",\n        \"country\": \"Turkey\",\n        \"city\": \"Ankara\",\n        \"locationCode\": \"ESB\"\n    },\n    {\n        \"name\": \"Antalya Airport\",\n        \"country\": \"Turkey\",\n        \"city\": \"Antalya\",\n        \"locationCode\": \"AYT\"\n    },\n    {\n        \"name\": \"Taksim Square\",\n        \"country\": \"Turkey\",\n        \"city\": \"Istanbul\",\n        \"locationCode\": \"TAKSIM\"\n    },\n    {\n        \"name\": \"Kadikoy Pier\",\n        \"country\": \"Turkey\",\n        \"city\": \"Istanbul\",\n        \"locationCode\": \"KADIKOY\"\n    },\n    {\n        \"name\": \"Besiktas Center\",\n        \"country\": \"Turkey\",\n        \"city\": \"Istanbul\",\n        \"locationCode\": \"BESIKTAS\"\n    },\n    {\n        \"name\": \"London Piccadilly\",\n        \"country\": \"UK\",\n        \"city\": \"London\",\n        \"locationCode\": \"PICAD\"\n    },\n    {\n        \"name\": \"London Hyde Park\",\n        \"country\": \"UK\",\n        \"city\": \"London\",\n        \"locationCode\": \"HYDE\"\n    },\n    {\n        \"name\": \"Manhattan Times Sq\",\n        \"country\": \"USA\",\n        \"city\": \"New York\",\n        \"locationCode\": \"MANHAT\"\n    }\n]",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "http://localhost:8080/api/locations/batch-insert-locations",
              "protocol": "http",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "locations",
                "batch-insert-locations"
              ]
            }
          },
          "response": []
        }
      ]
    },
    {
      "name": "transportation",
      "item": [
        {
          "name": "insert-transportation",
          "request": {
            "method": "POST",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"originLocationId\": 4,\n  \"destinationLocationId\": 1,\n  \"transportationType\": \"BUS\",\n  \"operatingDays\": \"1,2,3,4,5,6,7\"\n}\n",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "http://localhost:8080/api/transportations/insert-transportation",
              "protocol": "http",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "transportations",
                "insert-transportation"
              ]
            }
          },
          "response": []
        },
        {
          "name": "update-transportation",
          "request": {
            "method": "PUT",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": "{\n    \"id\" : 2, \n    \"originLocationId\" : 1,\n    \"destinationLocationId\": 2,\n    \"transportationType\" : \"UBER\",\n    \"operatingDays\": \"1,2,3,4,5\"\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "http://localhost:8080/api/transportations/update-transportation",
              "protocol": "http",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "transportations",
                "update-transportation"
              ]
            }
          },
          "response": []
        },
        {
          "name": "batch-insert-transportations",
          "request": {
            "method": "POST",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": "[\n    {\n        \"originLocationId\": 10,\n        \"destinationLocationId\": 2,\n        \"transportationType\": \"BUS\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 10,\n        \"destinationLocationId\": 2,\n        \"transportationType\": \"UBER\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 10,\n        \"destinationLocationId\": 2,\n        \"transportationType\": \"SUBWAY\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 10,\n        \"destinationLocationId\": 1,\n        \"transportationType\": \"UBER\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 10,\n        \"destinationLocationId\": 1,\n        \"transportationType\": \"BUS\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 9,\n        \"destinationLocationId\": 1,\n        \"transportationType\": \"BUS\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 9,\n        \"destinationLocationId\": 1,\n        \"transportationType\": \"SUBWAY\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 11,\n        \"destinationLocationId\": 1,\n        \"transportationType\": \"BUS\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 11,\n        \"destinationLocationId\": 1,\n        \"transportationType\": \"SUBWAY\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 11,\n        \"destinationLocationId\": 1,\n        \"transportationType\": \"UBER\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 1,\n        \"destinationLocationId\": 3,\n        \"transportationType\": \"FLIGHT\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 1,\n        \"destinationLocationId\": 3,\n        \"transportationType\": \"FLIGHT\",\n        \"operatingDays\": \"6,7\"\n    },\n    {\n        \"originLocationId\": 1,\n        \"destinationLocationId\": 4,\n        \"transportationType\": \"FLIGHT\",\n        \"operatingDays\": \"1,3,5,6\"\n    },\n    {\n        \"originLocationId\": 2,\n        \"destinationLocationId\": 3,\n        \"transportationType\": \"FLIGHT\",\n        \"operatingDays\": \"2,4,6\"\n    },\n    {\n        \"originLocationId\": 2,\n        \"destinationLocationId\": 4,\n        \"transportationType\": \"FLIGHT\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 1,\n        \"destinationLocationId\": 5,\n        \"transportationType\": \"FLIGHT\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 1,\n        \"destinationLocationId\": 6,\n        \"transportationType\": \"FLIGHT\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 7,\n        \"destinationLocationId\": 8,\n        \"transportationType\": \"FLIGHT\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 3,\n        \"destinationLocationId\": 12,\n        \"transportationType\": \"SUBWAY\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 3,\n        \"destinationLocationId\": 12,\n        \"transportationType\": \"UBER\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 4,\n        \"destinationLocationId\": 12,\n        \"transportationType\": \"BUS\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    },\n    {\n        \"originLocationId\": 5,\n        \"destinationLocationId\": 14,\n        \"transportationType\": \"UBER\",\n        \"operatingDays\": \"1,2,3,4,5,6,7\"\n    }\n]",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "http://localhost:8080/api/transportations/batch-insert-transportations",
              "protocol": "http",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "transportations",
                "batch-insert-transportations"
              ]
            }
          },
          "response": []
        },
        {
          "name": "get-transportation-by-with-id",
          "request": {
            "method": "POST",
            "header": [],
            "url": {
              "raw": "http://localhost:8080/api/transportations/get-transportation-by-with-id?id=2",
              "protocol": "http",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "transportations",
                "get-transportation-by-with-id"
              ],
              "query": [
                {
                  "key": "id",
                  "value": "2",
                  "type": "text"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "delete-transportation",
          "request": {
            "method": "DELETE",
            "header": [],
            "url": {
              "raw": "http://localhost:8080/api/transportations/delete-transportation?id=1",
              "protocol": "http",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "transportations",
                "delete-transportation"
              ],
              "query": [
                {
                  "key": "id",
                  "value": "1",
                  "type": "text"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "get-all-transportations",
          "request": {
            "method": "GET",
            "header": [],
            "url": {
              "raw": "http://localhost:8080/api/transportations/get-all-transportations",
              "protocol": "http",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "transportations",
                "get-all-transportations"
              ]
            }
          },
          "response": []
        }
      ]
    },
    {
      "name": "routes",
      "item": [
        {
          "name": "search-routes",
          "request": {
            "method": "POST",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"originCode\": \"CCKAD\",\n  \"destinationCode\": \"CCPAR\"}\n",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "http://localhost:8080/api/routes/search-routes",
              "protocol": "http",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "routes",
                "search-routes"
              ]
            }
          },
          "response": []
        }
      ]
    }
  ]
}
```

