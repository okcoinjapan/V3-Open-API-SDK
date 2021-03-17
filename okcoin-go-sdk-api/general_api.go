package okcoin

/*
 Time of the server running OKCoin's REST API.
*/
func (client *Client) GetServerTime() (ServerTime, error) {
	var serverTime ServerTime
	_, err := client.Request(GET, OKCOIN_TIME_URI, nil, &serverTime)
	return serverTime, err
}
