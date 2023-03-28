# Java RESTful API - Country (JSON)
Dalam proses pengambilan data dari server, Java dapat menggunakan library `HttpURLConnection` untuk membuat request HTTP dan menerima response dalam format JSON. Kemudian, data JSON tersebut dapat di-parse ke dalam objek Java.

### Contoh struktur project Java

    └── java
        └── <packages>                      # package utama untuk aplikasi.
            ├── api                         # package untuk kelas-kelas yang terkait dengan API atau web service.
            │   └── CountryAPI.java         # kelas untuk melakukan koneksi ke web service untuk mengambil data JSON dan mengkonversinya ke objek Country.
            ├── service                     # package untuk kelas-kelas yang terkait dengan services.
            │   └── CountryService.java     # kelas untuk menyimpan data objek Country dan melakukan operasi pada data.
            ├── model                       # package untuk kelas-kelas model atau representasi dari data dalam aplikasi.
            │   └── Country.java            # kelas model untuk objek Country.
            └── Main.java                   # Main class pada program

> Opsi struktur folder dan konvensi penamaan pada project.
### Sumber Data
Mengambil data dari server yang memiliki format JSON. [(Source)](https://raw.githubusercontent.com/Zortagon/kapita-falcon-backend/main/raw/json_country_simple.json)
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
> Json dengan awalan `[]` menandakan bahwa data tersebut merupakan sebuah array.

# Code
### 1. Membuat Object Country
Dalam package utama buatlah package `model` dan buat sebuah class bernama `Country`
```java
public class Country {
    
    private final String name;
    private final String code;
    private int population;
    
    public Country(String name, String code, int population) {
        this.name = name;
        this.code = code;
        this.population = population;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public int getPopulation() {
        return this.population;
    }
    
}
```
> Nama field pada kelas disesuaikan pada data di JSON agar mempermudah kedepanya. `name`, `code`, `population`