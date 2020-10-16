app.controller("cryptocurrencyArbitrage", ["$scope", "$filter", "$interval", "$window", "global", function($scope, $filter, $interval, $window, global) {


    $scope.exchanger = {
        "binance": {
            "fee": {
                "commission": 0,
                "deposit": {
                    "btc": 0,
                    "eur": 0
                },
                "withdrawal": {
                    "btc": 0,
                    "eur": 0
                }
            }
        },
        "kraken": {
            "fee": {
                "commission": 0,
                "deposit": {
                    "btc": 0,
                    "eur": 0
                },
                "withdrawal": {
                    "btc": 0,
                    "eur": 0
                }
            }
        }
    };

    $scope.filter = {
        "currency": {
            "option": [],
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "lotSize": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "minimumPercentage": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "valid": {
            "currency": false,
            "lotSize": false,
            "minimumPercentage": false
        }
    };

    $scope.list = [];

    $scope.status = {
        "class": "green",
        "interval": "",
        "text": "Stopped",
        "value": "Idle"
    };

    $scope.stomp = Stomp.client(document.getElementById("global").getAttribute("data-websocket-url"));
    $scope.stomp.debug = null;

    $scope.tradeInformation = {
        "binance": [],
        "bitfinex": [],
        "bittrex": [],
        "kraken": {},
        "symbol": []
    };


    $scope.checkAccount = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/triangular-arbitrage/binance/check-account"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $scope.response.class = "success";

            } else {

                $scope.response.class = "error";

            }

            $scope.loading.view = false;

            $scope.response.message = response.response;
            $scope.response.view = true;

            $scope.hideResponse();

        });

        event.preventDefault();

    }


    $scope.checkFilterCurrency = function() {

        if($scope.filter.currency.value == "") {

            $scope.filter.currency.response.value = "Please select currency";
            $scope.filter.currency.response.class = "error";
            $scope.filter.currency.response.view = true;
            $scope.filter.valid.currency = false;

        } else {

            $scope.filter.currency.response.view = false;
            $scope.filter.valid.currency = true;

        }

    }


    $scope.checkFilterData = function() {

        $scope.checkFilterCurrency();

        $scope.checkFilterLotSize();

        $scope.checkFilterMinimumPercentage();

    }


    $scope.checkFilterLotSize = function() {

        if(!$scope.filter.lotSize.value.match(/^[0-9,.]+$/)) {

            $scope.filter.lotSize.response.value = "Please enter only number";
            $scope.filter.lotSize.response.class = "error";
            $scope.filter.lotSize.response.view = true;
            $scope.filter.valid.lotSize = false;

        } else {

            $scope.filter.lotSize.response.view = false;
            $scope.filter.valid.lotSize = true;

        }

        $scope.filter.lotSize.value = library.numeral.initializeSeparator($scope.filter.lotSize.value, false);

    }


    $scope.checkFilterMinimumPercentage = function() {

        if($scope.filter.minimumPercentage.value != "") {

            if(!$scope.filter.minimumPercentage.value.match(/^[0-9,.]+$/)) {

                $scope.filter.minimumPercentage.response.value = "Please enter only number";
                $scope.filter.minimumPercentage.response.class = "error";
                $scope.filter.minimumPercentage.response.view = true;
                $scope.filter.valid.minimumPercentage = false;

            } else {

                $scope.filter.minimumPercentage.response.view = false;
                $scope.filter.valid.minimumPercentage = true;

            }

        } else {

            $scope.filter.minimumPercentage.response.view = false;
            $scope.filter.valid.minimumPercentage = true;

        }

        $scope.filter.minimumPercentage.value = library.numeral.initializeSeparator($scope.filter.minimumPercentage.value, false);

    }


    $scope.convertKrakenSymbol = function(symbol) {

        return symbol.replace("X", "XX").replace("BTC", "XXBT").replace("ETH", "XETH").replace("EUR", "ZEUR").replace("USD", "ZUSD");

    }


    $scope.initializeArbitrage = function(exchanger, baseAsset, quoteAsset, lotSize, buyExchanger, sellExchanger) {

        var arbitrage = {
            "buy": {
                "asset": {
                    "base": baseAsset.toUpperCase(),
                    "quote": quoteAsset.toUpperCase()
                },
                "credit": {
                    "end": 0,
                    "start": lotSize
                },
                "exchanger": buyExchanger,
                "fee": {
                    "commission": $scope.exchanger[buyExchanger.toLowerCase()].fee.commission,
                    "deposit": $scope.exchanger[buyExchanger.toLowerCase()].fee.deposit[quoteAsset],
                    "withdrawal": $scope.exchanger[buyExchanger.toLowerCase()].fee.withdrawal[baseAsset]
                },
                "price": exchanger[buyExchanger.toLowerCase()].price.bid,
                "quantity": lotSize,
                "symbol": exchanger[buyExchanger.toLowerCase()].symbol
            },
            "percentage": 0,
            "sell": {
                "asset": {
                    "base": baseAsset.toUpperCase(),
                    "quote": quoteAsset.toUpperCase()
                },
                "credit": {
                    "end": 0,
                    "start": 0
                },
                "exchanger": sellExchanger,
                "fee": {
                    "commission": $scope.exchanger[sellExchanger.toLowerCase()].fee.commission,
                    "deposit": $scope.exchanger[sellExchanger.toLowerCase()].fee.deposit[baseAsset],
                    "withdrawal": $scope.exchanger[sellExchanger.toLowerCase()].fee.withdrawal[quoteAsset]
                },
                "price": exchanger[sellExchanger.toLowerCase()].price.ask,
                "quantity": 0,
                "symbol": exchanger[sellExchanger.toLowerCase()].symbol
            }
        };

        var endCredit = (lotSize - $scope.exchanger[buyExchanger.toLowerCase()].fee.deposit[quoteAsset]) / exchanger[buyExchanger.toLowerCase()].price.bid;
        var commission = endCredit * $scope.exchanger[buyExchanger.toLowerCase()].fee.commission / 100;
        arbitrage.buy.credit.end = endCredit - commission;

        arbitrage.sell.credit.start = arbitrage.buy.credit.end - $scope.exchanger[buyExchanger.toLowerCase()].fee.withdrawal[baseAsset];
        arbitrage.sell.quantity = arbitrage.sell.credit.start;

        endCredit = arbitrage.sell.credit.start * exchanger[sellExchanger.toLowerCase()].price.ask;
        commission = endCredit * $scope.exchanger[sellExchanger.toLowerCase()].fee.commission / 100;
        arbitrage.sell.credit.end = endCredit - commission - $scope.exchanger[sellExchanger.toLowerCase()].fee.withdrawal[quoteAsset];

        arbitrage.percentage = ((arbitrage.sell.credit.end - arbitrage.buy.credit.start) / arbitrage.buy.credit.start * 100).toFixed(2);

        if(arbitrage.percentage > 0) {

            $scope.list.push(arbitrage)

        }

    }


    $scope.initializeArbitrageList = function(lotSize) {

        if($scope.filter.lotSize.value != "") {

            lotSize = $scope.filter.lotSize.value;

        }

        $scope.list = [];

        angular.forEach($scope.tradeInformation.symbol, function(value, key) {

            var exchanger = {
                "binance": {
                    "price": {
                        "ask": 0,
                        "bid": 0
                    },
                    "symbol": value,
                    "tradeInformation": []
                },
                "bitfinex": {
                    "price": {
                        "ask": 0,
                        "bid": 0
                    },
                    "symbol": "t" + value,
                    "tradeInformation": []
                },
                "bittrex": {
                    "price": {
                        "ask": 0,
                        "bid": 0
                    },
                    "symbol": value.slice(0, value.indexOf($scope.filter.currency.selected.value)) + "-" + value.slice(value.indexOf($scope.filter.currency.selected.value)),
                    "tradeInformation": []
                },
                "kraken": {
                    "price": {
                        "ask": 0,
                        "bid": 0
                    },
                    "symbol": $scope.convertKrakenSymbol(value),
                    "tradeInformation": []
                }
            };

            var baseAsset = value.slice(0, value.indexOf($scope.filter.currency.selected.value)).toLowerCase();
            var quoteAsset = value.slice(value.indexOf($scope.filter.currency.selected.value)).toLowerCase();

            exchanger.binance.tradeInformation = $filter("filter")($scope.tradeInformation.binance, {"symbol": exchanger.binance.symbol}, $scope.filterMatch);

            exchanger.bitfinex.tradeInformation = $filter("filter")($scope.tradeInformation.bitfinex, {"0": exchanger.bitfinex.symbol}, $scope.filterMatch);

            exchanger.bittrex.tradeInformation = $filter("filter")($scope.tradeInformation.bittrex, {"symbol": exchanger.bittrex.symbol}, $scope.filterMatch);

            if($scope.tradeInformation.kraken.hasOwnProperty(exchanger.kraken.symbol)) {

                exchanger.kraken.tradeInformation = $scope.tradeInformation.kraken[exchanger.kraken.symbol];

            }

            if(exchanger.binance.tradeInformation.length > 0) {

                exchanger.binance.price.ask = parseFloat(exchanger.binance.tradeInformation[0].price);
                exchanger.binance.price.bid = parseFloat(exchanger.binance.tradeInformation[0].price);

            }

            if(exchanger.bitfinex.tradeInformation.length > 0) {

                exchanger.bitfinex.price.ask = parseFloat(exchanger.bitfinex.tradeInformation[0][3]);
                exchanger.bitfinex.price.bid = parseFloat(exchanger.bitfinex.tradeInformation[0][1]);

            }

            if(exchanger.bittrex.tradeInformation.length > 0) {

                exchanger.bittrex.price.ask = parseFloat(exchanger.bittrex.tradeInformation[0].askRate);
                exchanger.bittrex.price.bid = parseFloat(exchanger.bittrex.tradeInformation[0].bidRate);

            }

            if(exchanger.kraken.tradeInformation.hasOwnProperty("b")) {

                exchanger.kraken.price.ask = parseFloat(exchanger.kraken.tradeInformation.a[0]);
                exchanger.kraken.price.bid = parseFloat(exchanger.kraken.tradeInformation.b[0]);

            }

            if(exchanger.binance.price.ask > exchanger.bitfinex.price.bid) {

                $scope.initializeArbitrage(exchanger, baseAsset, quoteAsset, lotSize, "Bitfinex", "Binance");

            }

            if(exchanger.bitfinex.price.ask > exchanger.binance.price.bid) {

                $scope.initializeArbitrage(exchanger, baseAsset, quoteAsset, lotSize, "Binance", "Bitfinex");

            }

            if(exchanger.binance.price.ask > exchanger.bittrex.price.bid) {

                $scope.initializeArbitrage(exchanger, baseAsset, quoteAsset, lotSize, "Bittrex", "Binance");

            }

            if(exchanger.bittrex.price.ask > exchanger.binance.price.bid) {

                $scope.initializeArbitrage(exchanger, baseAsset, quoteAsset, lotSize, "Binance", "Bittrex");

            }

            if(exchanger.binance.price.ask > exchanger.kraken.price.bid) {

                $scope.initializeArbitrage(exchanger, baseAsset, quoteAsset, lotSize, "Kraken", "Binance");

            }

            if(exchanger.kraken.price.ask > exchanger.binance.price.bid) {

                $scope.initializeArbitrage(exchanger, baseAsset, quoteAsset, lotSize, "Binance", "Kraken");

            }

            if(exchanger.bitfinex.price.ask > exchanger.bittrex.price.bid) {

                $scope.initializeArbitrage(exchanger, baseAsset, quoteAsset, lotSize, "Bittrex", "Bitfinex");

            }

            if(exchanger.bittrex.price.ask > exchanger.bitfinex.price.bid) {

                $scope.initializeArbitrage(exchanger, baseAsset, quoteAsset, lotSize, "Bitfinex", "Bittrex");

            }

            if(exchanger.bitfinex.price.ask > exchanger.kraken.price.bid) {

                $scope.initializeArbitrage(exchanger, baseAsset, quoteAsset, lotSize, "Kraken", "Bitfinex");

            }

            if(exchanger.kraken.price.ask > exchanger.bitfinex.price.bid) {

                $scope.initializeArbitrage(exchanger, baseAsset, quoteAsset, lotSize, "Bitfinex", "Kraken");

            }

            if(exchanger.bittrex.price.ask > exchanger.kraken.price.bid) {

                $scope.initializeArbitrage(exchanger, baseAsset, quoteAsset, lotSize, "Kraken", "Bittrex");

            }

            if(exchanger.kraken.price.ask > exchanger.bittrex.price.bid) {

                $scope.initializeArbitrage(exchanger, baseAsset, quoteAsset, lotSize, "Bittrex", "Kraken");

            }

        });

        if($scope.list.length > 0) {

            $scope.list = $filter("orderBy")($scope.list, "percentage", true);

        }

    }


    $scope.initializeData = function() {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/arbitrage/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                angular.forEach(response.cryptocurrencySymbolList, function(value, key) {

                    $scope.tradeInformation.symbol.push(value);

                });

                angular.forEach(response.cryptocurrencyCurrencyList, function(value, key) {

                    $scope.filter.currency.option.push({
                        "name": value,
                        "value": value
                    });

                });

                $scope.filter.currency.selected = $scope.filter.currency.option[0];

                angular.forEach(response.cryptocurrencyExchangerList, function(value, key) {

                    $scope.exchanger[value.name.toLowerCase()] = {
                        "fee": {
                            "commission": value.fee.commission,
                            "deposit": {
                                "btc": value.fee.deposit.btc,
                                "eth": value.fee.deposit.eth,
                                "eur": value.fee.deposit.eur
                            },
                            "withdrawal": {
                                "btc": value.fee.withdrawal.btc,
                                "eth": value.fee.withdrawal.eth,
                                "eur": value.fee.withdrawal.eur
                            }
                        }
                    }

                });

                $scope.tradeInformation.binance = response.cryptocurrencyBinancePriceTickerList;

                angular.forEach(response.cryptocurrencyBitfinexTickerList, function(value, key) {

                    $scope.tradeInformation.bitfinex.push(Object.assign({}, value));

                });

                $scope.tradeInformation.bittrex = response.cryptocurrencyBittrexMarketTickerList;
                $scope.tradeInformation.kraken = response.cryptocurrencyKrakenTickerInformationList;

                if($scope.tradeInformation.binance != null && $scope.tradeInformation.bitfinex != null && $scope.tradeInformation.bittrex != null && $scope.tradeInformation.kraken != null) {

                    $scope.initializeArbitrageList(response.cryptocurrencyLotSize);

                }

                $scope.loading.view = false;

            } else {

                $scope.loading.view = false;

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

                $scope.hideResponse();

            }

        });

    }


    $scope.initializeFilterInput = function() {

        var result = {
            "asset": [
                $scope.filter.asset.value
            ],
            "lotSize": 0,
            "minimumPercentage": 0,
            "params": []
        };

        result.lotSize = library.numeral.initializeSeparator($scope.filter.lotSize.value, true);
        result.minimumPercentage = library.numeral.initializeSeparator($scope.filter.minimumPercentage.value, true);

        angular.forEach($scope.filter.exchangeInformation.value, function(value, key) {

            result.params.push(value.value);

        });

        return result;

    }


    $scope.loadCoinInformation = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/arbitrage/load-coin-information"
        };
        global.rest(rest, function(response) {

            if(response.result) {


                $scope.loading.view = false;

            } else {

                $scope.loading.view = false;

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

                $scope.hideResponse();

            }

        });

        event.preventDefault();

    }


    $scope.loadDataStream = function(event) {

        $scope.checkFilterData();

        var valid = true;

        angular.forEach($scope.filter.valid, function(value, key) {

            if(!value) {

                valid = false;

                return false;

            }

        });

        if(valid) {

            $scope.loading.view = true;

            var input = $scope.initializeFilterInput();

            var rest = {
                "data": {
                    "asset": input.asset,
                    "method": "SUBSCRIBE",
                    "params": input.params,
                    "id": 1
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/triangular-arbitrage/binance/load-data-stream"
            };
            global.rest(rest, function(response) {

                if(response.result) {

                    $scope.stomp.connect({}, function() {

                        $scope.stomp.subscribe("/output-stream/cryptocurrency/binance/triangular-arbitrage", function(output) {

                            $scope.list = [];

                            var message = JSON.parse(output.body);
                            var inputMessage = JSON.parse(message.inputMessage);
                            var outputMessage = JSON.parse(message.outputMessage);

                            if(outputMessage.hasOwnProperty("data")) {

                                if(Array.isArray(outputMessage.data)) {

                                    $scope.initializeTriangularArbitrageList(outputMessage.data);

                                } else {

                                    if(inputMessage.params.length == 3) {

                                        $scope.initializeTriangularArbitrage(message.header.replace("baseAsset:", ""), inputMessage.params, outputMessage.data);

                                        if($scope.status.value == "Arbitrage") {

                                            $scope.makeOrder($scope.triangularArbitrage);

                                        }

                                    }

                                }

                                $scope.$digest();

                            }

                        });

                    });

                    $scope.loading.view = false;

                } else {

                    $scope.loading.view = false;

                    $scope.response.class = "error";
                    $scope.response.message = response.response;
                    $scope.response.view = true;

                    $scope.hideResponse();

                }

            });

        } else {

            $scope.response.class = "error";
            $scope.response.message = "Please enter a valid data";
            $scope.response.view = true;

            $scope.hideResponse();

        }

        event.preventDefault();

    }


    $scope.loadSymbolMinimumQuantity = function(symbol) {

        var result = 0;

        var exchangeInformation = $filter("filter")($scope.exchangeInformation, {"symbol": symbol});

        if(exchangeInformation.length > 0) {

            var filter = $filter("filter")(exchangeInformation[0].filters, {"filterType": "LOT_SIZE"});

            if(filter.length > 0) {

                result = filter[0].minQty;

            }

        }

        return result;

    }


    $scope.makeOrder = function(triangularArbitrage) {

        if($scope.triangularArbitrage.start.action != "" && $scope.triangularArbitrage.middle.action != "" && $scope.triangularArbitrage.end.action != "") {

            var minimumPercentage = $scope.filter.minimumPercentage.value;

            if(isNaN(parseFloat(minimumPercentage))) {

                minimumPercentage = 0;

            }

            if($scope.triangularArbitrage.percentage >= minimumPercentage) {

                $scope.loading.view = true;

                var rest = {
                    "data": triangularArbitrage,
                    "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/triangular-arbitrage/binance/make-order"
                };
                global.rest(rest, function(response) {

                    if(response.result) {

                        $scope.response.class = "success";

                    } else {

                        $scope.response.class = "error";

                    }

                    $scope.status.value = "Delay";

                    $scope.loading.view = false;

                    $scope.response.message = response.response;
                    $scope.response.view = true;

                    $scope.hideResponse();

                });

            }

        }

    }


    $scope.run = function(event) {

        $scope.loading.view = true;

        if($scope.status.value == "Idle") {

            $scope.status.class = "red";
            $scope.status.text = "Running";
            $scope.status.value = "Arbitrage";

            $scope.status.interval = $interval(function() {

                $scope.status.value = "Arbitrage";

            }, 650000);

            $scope.response.message = "Cryptocurrency triangular arbitrage ran";

        } else if($scope.status.value == "Arbitrage" || $scope.status.value == "Delay") {

            $interval.cancel($scope.status.interval);

            $scope.status.class = "green";
            $scope.status.text = "Stopped";
            $scope.status.value = "Idle";

            $scope.response.message = "Cryptocurrency triangular arbitrage stopped";

        }

        $scope.loading.view = false;

        $scope.response.class = "success";
        $scope.response.view = true;

        $scope.hideResponse();

        event.preventDefault();

    }


    $scope.testConnection = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/triangular-arbitrage/binance/test-connection"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $scope.response.class = "success";

            } else {

                $scope.response.class = "error";

            }

            $scope.loading.view = false;

            $scope.response.message = response.response;
            $scope.response.view = true;

            $scope.hideResponse();

        });

        event.preventDefault();

    }


    $scope.transformFilterExchangeInformationChip = function(chip) {

        if(angular.isObject(chip)) {

            return chip;

        } else {

            return {
                "name": chip,
                "type": "new"
            };

        }

    }


}]);
