OKCoin Japan V3 Open Api 利用方法
--------------
### 1.使用するライブラリ： okhttp3 + retrofit2

### 2.Javaアプリケーションにおける使用例
``` Java
 public static void main(String[] args) {

        APIConfiguration config = new APIConfiguration();
        config.setEndpoint(" https://www.okcoin.jp/");
        
        //My API画面で設定・取得したAPIKey,APISecret,PassPhraseを以下に記載
        config.setApiKey("");
        config.setSecretKey("");
        config.setPassphrase("");
        config.setPrint(true);
        config.setI18n(I18nEnum.ENGLISH);

        GeneralAPIService marketAPIService = new GeneralAPIServiceImpl(config);
        ServerTime time = marketAPIService.getServerTime();
        System.out.println(JSON.toJSONString(time));

        AccountAPIService accountAPIService = new AccountAPIServiceImpl(config);

        Transfer transfer = new Transfer();
        transfer.setFrom("1");
        transfer.setTo("6");
        transfer.setCurrency("JPY");
        transfer.setAmount("10000");
        
        JSONObject result = this.accountAPIService.transfer(transfer);
        System.out.println(JSON.toJSONString(result));
 }
```
### 3.Spring または Spring Bootでの使用例
``` Java
@RestController
public class TestOKCoinOpenApiV3 {

    @Autowired
    private GeneralAPIService generalAPIService;

    @GetMapping("/server-time")
    public ServerTime getServerTime() {
        return generalAPIService.getServerTime();
    }
    
    @Bean
    public APIConfiguration okcoinApiConfig() {
        APIConfiguration config = new APIConfiguration();
          config.setEndpoint(" https://www.okcoin.jp/");
            
            //My API画面で設定・取得したAPIKey,APISecret,PassPhraseを以下に記載
            config.setApiKey("");
            config.setSecretKey("");
            config.setPassphrase("");
            config.setPrint(true);
        return config;
    }

    @Bean
    public GeneralAPIService generalAPIService(APIConfiguration config) {
        return new GeneralAPIServiceImpl(config);
    }
}
