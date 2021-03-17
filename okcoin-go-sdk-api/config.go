package okcoin

type Config struct {
	// Rest api endpoint url. eg: https://www.okcoin.jp/
	Endpoint string

	// Rest websocket api endpoint url. eg: wss://connect.okcoin.jp:443/ws/v3
	WSEndpoint string

	// The user's api key provided by OKCoin.
	ApiKey string
	// The user's secret key provided by OKCoin. The secret key used to sign your request data.
	SecretKey string
	// The Passphrase will be provided by you to further secure your API access.
	Passphrase string
	// Http request timeout.
	TimeoutSecond int
	// Whether to print API information
	IsPrint bool
	// Internationalization @see file: constants.go
	I18n string
}
