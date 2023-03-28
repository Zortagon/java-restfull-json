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