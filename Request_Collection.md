# Postman Collection - Multi-Hub High Volume (Final)

## 1. Locations (Batch Insert)
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

## 2. Transportations (Batch Insert)
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

## 3. Test Senaryoları ve Beklenen Sonuçlar

### Senaryo 1: HIGH VOLUME (10+ Rota)
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

### Senaryo 2: MEDIUM VOLUME (~6 Rota)
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

### Senaryo 3: LOW VOLUME (1 Rota)
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

### Senaryo 4: NULL VOLUME (0 Rota)
**Hedef:** Ters İstikamet (Antalya -> Ankara)
**Beklenen:** 0 Rota (Çünkü dönüş uçuşu tanımlamadık).
```json
{ 
  "originCode": "AYT", 
  "destinationCode": "ESB", 
  "date": "2024-01-01" 
}
```
