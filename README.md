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
> Nama field pada kelas disesuaikan pada data di JSON agar mempermudah kedepanya.
> Contoh diatas menggunakan nama `name`, `code` & `population`
> 
> **Opsional**: Karena di class `Country` memiliki field yang final (Konstan) dan hanya memiliki _Getter_ method (`getName()`, `getCode()` & `getPopulation()`).
> Pada java 16 memperkenalkan fitur baru java yaitu **Record Class**
> 
> Anda dapat mengubah class diatas menjadi record class. Cukup isi `Country` class tersebut dengan code seperti dibawah ini.
```java
public record Country(String name, String code, int population) { }
// Anda dapat mengganti code diatas dengan hanya seperti ini
```
### 2. Membuat API
Dalam package utama buatlah package `api` dan buat sebuah class bernama `CountryAPI`
```java
import <package-utama>.model.Country;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CountryAPI {
    private static final String API_URL = "https://raw.githubusercontent.com/Zortagon/kapita-falcon-backend/main/raw/json_country_simple.json";

    public static List<Country> getCountries() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code " + conn.getResponseCode());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder response = new StringBuilder();
        String output;

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        JSONArray jsonArray = new JSONArray(new JSONTokener(response.toString()));
        List<Country> countries = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            String name = jsonArray.getJSONObject(i).getString("name");
            String code = jsonArray.getJSONObject(i).getString("code");
            int population = jsonArray.getJSONObject(i).getInt("population");

            Country country = new Country(name, code, population);
            countries.add(country);
        }

        conn.disconnect();
        return countries;
    }
}
```
### 3. Membuat Services
Dalam package utama buatlah package `service` dan buat sebuah class bernama `CountryService`
```java
import me.fernanbae.api.CountryAPI;
import me.fernanbae.model.Country;

import java.io.IOException;
import java.util.List;

public class CountryService {
    public static List<Country> getCountries() throws IOException {
        return CountryAPI.getCountries();
    }
}
```
> **Opsional**: Untuk mendeklarasikan `getCountries()` menjadi tidak boleh kosong/null dan terhindar dari `NullPointerException`. Anda dapat memberikan annotaion
> `@NotNull`

Import module tersebut:
```java
import org.jetbrains.annotations.NotNull;
```
Ubah pen-deklarasian method menjadi:
```java
public static @NotNull List<Country> getCountries() throws IOException
```
### 4. Menjalakan Program
Untuk menjalakan program tambahakn code ini ke dalam `Main` class
```java
import me.fernanbae.model.Country;
import me.fernanbae.service.CountryService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        for (Country country : CountryService.getCountries()) {
            System.out.println(country.name() + " " + country.code() + " " + country.population());
        }
    }
}
```
Jika berhasil maka akan mendapat output:
```
Afghanistan AF 25000
Antarctica AQ 13500
Barbados BB 13500
Cameroon CM 8500
```
> **Note**: Anda dapat melihat source code keselurhan dari repository ini diatas.