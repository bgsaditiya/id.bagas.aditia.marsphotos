package id.bagas.aditia.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.moshi.MoshiConverterFactory

//URL dasar untuk layanan web
private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//builder Retrofit untuk membuat objek Retrofit
private val retrofit = Retrofit.Builder()

        //Retrofit memiliki ScalarsConverter yang mendukung string dan jenis sederhana lainnya
        //memanggil addConverterFactory() pada builder dengan instance ScalarsConverterFactory
        .addConverterFactory(MoshiConverterFactory.create(moshi))

        //URI dasar untuk layanan web menggunakan metode baseUrl()
        .baseUrl(BASE_URL)
        .build()

//membuat interface MarsApiService
interface MarsApiService {

    //@GET untuk memberi tahu Retrofit bahwa ini adalah permintaan GET dengan endpoint photos
    @GET("photos")

    //fungsi getPhotos() untuk mendapatkan string respons dari layanan web
    //tipe data yang ditampilkan fungsi adalah String
    //suspend menjadikan getPhotos() sebagai fungsi penangguhan
    suspend fun getPhotos() : List<MarsPhoto>

    //fun getPhotos(): List<MarsPhoto>
}

//menambah kan singleton objek MarsApi untuk menginisialisasi layanan Retrofit
object MarsApi{

    //properti objek retrofit yang diinisialisasi dengan lambat bernama retrofitService dari jenis MarsApiService
    val retrofitService : MarsApiService by lazy {

        //Inisialisasi variabel retrofitService menggunakan metode retrofit.create() dengan interface MarsApiService
        retrofit.create(MarsApiService::class.java)
    }
}