# Java RESTful API - Country (Simple JSON)
Dalam proses pengambilan data dari server, Java dapat menggunakan library `HttpURLConnection` untuk membuat request HTTP dan menerima response dalam format JSON. Kemudian, data JSON tersebut dapat di-parse ke dalam objek Java.

### Contoh struktur package Java

    .
    └── java
        └── <packages>
            ├── api
            ├── CountryAPI.java
            │   └── CountryService.java
            ├── model
            │   └── Country.java
            └── Main.java

> Opsi struktur folder dan konvensi penamaan pada project. `api` untuk mendapatkan data JSON dari web, dan dirubah menjadi object **Country** yang disimpan di `model`
### JSON Data
Contoh sumber data JSON yang digunakan `json_country_simple.json`
```json
[
    {
        "name": "Afghanistan",
        "code": "AF",
        "population": 25000
    },
    {
        "name": "Antarctica",
        "code": "AQ",
        "population": 13500
    },
    {
        "name": "Barbados",
        "code": "BB",
        "population": 13500
    },
    {
        "name": "Cameroon",
        "code": "CM",
        "population": 8500
    }
]
```