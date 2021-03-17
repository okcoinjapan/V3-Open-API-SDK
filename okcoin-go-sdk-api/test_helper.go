package okcoin

/* Get a http client */
func GetDefaultConfig() *Config {
	var config Config

	config.Endpoint = "https://www.okcoin.jp/"
	config.WSEndpoint = "wss://connect.okcoin.jp:443/ws/v3"

	config.TimeoutSecond = 45
	config.IsPrint = true
	config.I18n = ENGLISH

	// set your own ApiKey, SecretKey, Passphrase here
	config.ApiKey = ""
	config.SecretKey = ""
	config.Passphrase = ""

	return &config
}

func NewTestClient() *Client {
	// Set OKCoin API's config
	client := NewClient(*GetDefaultConfig())
	return client
}
