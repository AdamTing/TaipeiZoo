package api;

import android.util.Log;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {


    // 以Singleton模式建立
    private static RetrofitManager mInstance = new RetrofitManager();

    private PavilionAreaDataService pavilionAreaDataService;
    private AnimaDataService animaDataService;

    public String domain = API_URL.Companion.getTAIPEI_ZOO_DOMAIN();

    private RetrofitManager() {

        // 設置baseUrl即要連的網站，addConverterFactory用Gson作為資料處理Converter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(domain)
                .baseUrl(domain)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClientInstance())
                .build();

        pavilionAreaDataService = retrofit.create(PavilionAreaDataService.class);
        animaDataService = retrofit.create(AnimaDataService.class);
//        postHealthReport = retrofit.create(PostHealthReportService.class);
    }

    public static RetrofitManager getInstance() {
        return mInstance;
    }

    public PavilionAreaDataService getPavilionAreaDataService() {
        return pavilionAreaDataService;
    }
    public AnimaDataService getaAimaDataService() {
        return animaDataService;
    }
//    public PostHealthReportService getPostHealthReportService() {
//        return postHealthReport;
//    }

    //客户端不对服务器证书做任何验证
    public static SSLSocketFactory getSSLSocketFactory() throws Exception {
        //创建一个不验证证书链的证书信任管理器。
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts,
                new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext.getSocketFactory();
    }

    //获得无需验证任何证书的OkHttpClient实例对象
    public static OkHttpClient getOkHttpClientInstance(){
        try{
            return new okhttp3.OkHttpClient.Builder()
                    .sslSocketFactory(getSSLSocketFactory())
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build();
        }catch (Exception e){
            Log.e("OkHttpClientError", e.getMessage());
        }
        return null;
    }
}
