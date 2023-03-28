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
            │   └── CountryStatus.java      # kelas model untuk objek Status pada Country.
            └── Main.java                   # Main class pada program

> Opsi struktur folder dan konvensi penamaan pada project.
### Sumber Data
Mengambil data dari server yang memiliki format JSON. [(Source)](https://raw.githubusercontent.com/Zortagon/kapita-falcon-backend/main/raw/json_country_simple.json)
```json
[
    {
        "name": "Afghanistan",
        "code": "AF",
        "population": 25000,
        "status": {
            "connection": "Oke!",
            "peace": true,
            "latency": 10
        }
    },
    {
        "name": "Antarctica",
        "code": "AQ",
        "population": 13500,
        "status": {
            "connection": "Bad!",
            "peace": false,
            "latency": 5
        }
    },
    {
        "name": "Barbados",
        "code": "BB",
        "population": 13500,
        "status": {
            "connection": "Good!",
            "peace": true,
            "latency": -10
        }
    },
    {
        "name": "Cameroon",
        "code": "CM",
        "population": 8500,
        "status": {
            "connection": "Perfect!",
            "peace": false,
            "latency": 100
        }
    }
]
```
> Json dengan awalan `[]` menandakan bahwa data tersebut merupakan sebuah array.

# Code
Merubah beberapa code program anda agar dapat menggunakan data JSON diatas.
### 1. Membuat Object CountryStatus
Dalam package utama buatlah package `model` dan buat sebuah class bernama `CountryStatus`
```java
public class CountryStatus {
    private final String connection;
    private final boolean peace;
    private final int latency;

    public CountryStatus(String connection, boolean peace, int latency) {
        this.connection = connection;
        this.peace = peace;
        this.latency = latency;
    }

    public String getConnection() {
        return connection;
    }

    public boolean isPeace() {
        return peace;
    }

    public int getLatency() {
        return latency;
    }
}
```
> Nama field pada kelas disesuaikan pada data di JSON agar mempermudah kedepanya.
> Contoh diatas menggunakan nama `connection`, `peace` & `latency`
> 
> **Opsional**: Karena di class `Country` memiliki field yang final (Konstan) dan hanya memiliki _Getter_ method (`getConnection()`, `isPeace()` & `getLatency()`).
> Pada java 16 memperkenalkan fitur baru java yaitu **Record Class**
> 
> Anda dapat mengubah class diatas menjadi record class. Cukup isi `Country` class tersebut dengan code seperti dibawah ini.
```java
public record CountryStatus(String connection, boolean peace, int latency) { }
// Anda dapat mengganti code diatas dengan hanya seperti ini
```
### 2. Menambahkan Attribute Status pada Country Class
Tambahkan attribute/field dibawah ini pada Country class anda.
```java
private final CountryStatus status;
```
Jangan lupa untuk menambahkan **Getter** method
```java
public CountryStatus getStatus() {
    return this.status;
}
```
Atau jika anda menggunakan Record class anda dapat merubah code `Country` class seperti ini
```java
public record Country(String name, String code, int population, CountryStatus status) { }
```
### 3. Memperbarui Country API
Pada `CountryAPI` class tambahkan seperti ini pada bagian perulangan
```java
// ...
JSONObject status = jsonArray.getJSONObject(i).getJSONObject("status");
String connection   = status.getString("connection");
boolean peace       = status.getBoolean("peace");
int latency         = status.getInt("latency");

CountryStatus countryStatus = new CountryStatus(connection, peace, latency);

Country country = new Country(name, code, population, countryStatus);
// ...
```
### 4. Menjalakan Program
Untuk menjalakan program tambahakn code ini ke dalam `Main` class
```java
public class Main {
    public static void main(String[] args) throws IOException {

        for (Country country : CountryService.getCountries()) {
            System.out.printf("""
                    ----------------------------------
                    Nama: %s
                    Code: %s
                    Population: %s
                    Status:
                      - Connection: %s
                      - Peace: %s
                      - Latency: %s
                    %n""", country.name(), country.code(), country.population(), country.status().connection(), country.status().peace(), country.status().latency());
        }
    }
}
```
Jika berhasil maka akan mendapat output:
```
----------------------------------
Nama: Afghanistan
Code: AF
Population: 25000
Status:
  - Connection: Oke!
  - Peace: true
  - Latency: 10

----------------------------------
Nama: Antarctica
Code: AQ
Population: 13500
Status:
  - Connection: Bad!
  - Peace: false
  - Latency: 5

----------------------------------
Nama: Barbados
Code: BB
Population: 13500
Status:
  - Connection: Good!
  - Peace: true
  - Latency: -10

----------------------------------
Nama: Cameroon
Code: CM
Population: 8500
Status:
  - Connection: Perfect!
  - Peace: false
  - Latency: 100
```
> **Note**: Anda dapat melihat source code keselurhan dari repository ini diatas.