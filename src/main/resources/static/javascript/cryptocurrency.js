app.controller("cryptocurrency", ["$scope", "$filter", "$window", "global", function($scope, $filter, $window, global) {


    $scope.exchanger = {};

    $scope.filter = {
        "asset": {
            "option": [],
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "exchanger": {
            "option": [],
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "tradeInformation": {
            "option": [],
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "search": "",
            "value": []
        },
        "valid": {
            "asset": false,
            "exchanger": false
        }
    };

    $scope.list = [];

    $scope.tradeInformation = {};


    $scope.checkFilterAsset = function() {

        if($scope.filter.asset.value == "") {

            $scope.filter.asset.response.value = "Please select asset";
            $scope.filter.asset.response.class = "error";
            $scope.filter.asset.response.view = true;
            $scope.filter.valid.asset = false;

        } else {

            $scope.filter.asset.response.view = false;
            $scope.filter.valid.asset = true;

        }

    }


    $scope.checkFilterData = function() {

        $scope.checkFilterAsset();

        $scope.checkFilterExchanger();

    }


    $scope.checkFilterExchanger = function() {

        if($scope.filter.exchanger.value == "") {

            $scope.filter.exchanger.response.value = "Please select exchanger";
            $scope.filter.exchanger.response.class = "error";
            $scope.filter.exchanger.response.view = true;
            $scope.filter.valid.exchanger = false;

        } else {

            $scope.filter.exchanger.response.view = false;
            $scope.filter.valid.exchanger = true;

        }

    }


    $scope.initializeData = function() {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                angular.forEach(response.cryptocurrencyAssetList, function(value, key) {

                    $scope.filter.asset.option.push({
                        "disabled": false,
                        "name": value.name,
                        "value": value.name
                    });

                });

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
                        },
                        "ticker": {},
                        "websocket": {
                            "id": "",
                            "parameter": []
                        }
                    }

                    $scope.filter.exchanger.option.push({
                        "disabled": false,
                        "name": value.name,
                        "value": value.name
                    });

                });

                angular.forEach(response.cryptocurrencyBinanceExchangeInformationList, function(value, key) {

                    $scope.filter.tradeInformation.option.push({
                        "name": value.baseAsset + "/" + value.quoteAsset,
                        "value": value.symbol
                    });

                });

                angular.forEach(response.cryptocurrencyBinanceAveragePriceTickerList, function(value, key) {

                    var data = {
                        "symbol": value.symbol,
                        "lastPrice": 0,
                        "priceChange": 0,
                        "priceChangePercent": "0%",
                        "highPrice": 0,
                        "lowPrice": 0,
                        "volume": 0
                    };

                    if(value.lastPrice >= 0) {

                        data.lastPrice = "<span class=\"green\">" + library.numeral.initializeSeparator(value.lastPrice.toFixed(8), true) + "</span>";

                    } else {

                        data.lastPrice = "<span class=\"red\">" + library.numeral.initializeSeparator(value.lastPrice.toFixed(8), true) + "</span>";

                    }

                    if(value.priceChange >= 0) {

                        data.priceChange = "<span class=\"green\">" + library.numeral.initializeSeparator(value.priceChange.toFixed(8), true) + "</span>";

                    } else {

                        data.priceChange = "<span class=\"red\">" + library.numeral.initializeSeparator(value.priceChange.toFixed(8), true) + "</span>";

                    }

                    if(value.priceChangePercent >= 0) {

                        data.priceChangePercent = "<span class=\"green\">" + library.numeral.initializeSeparator(value.priceChangePercent, true) + "%</span>";

                    } else {

                        data.priceChangePercent = "<span class=\"red\">" + library.numeral.initializeSeparator(value.priceChangePercent, true) + "%</span>";

                    }

                    if(value.highPrice >= 0) {

                        data.highPrice = "<span class=\"green\">" + library.numeral.initializeSeparator(value.highPrice.toFixed(8), true) + "</span>";

                    } else {

                        data.highPrice = "<span class=\"red\">" + library.numeral.initializeSeparator(value.highPrice.toFixed(8), true) + "</span>";

                    }

                    if(value.lowPrice >= 0) {

                        data.lowPrice = "<span class=\"green\">" + library.numeral.initializeSeparator(value.lowPrice.toFixed(8), true) + "</span>";

                    } else {

                        data.lowPrice = "<span class=\"red\">" + library.numeral.initializeSeparator(value.lowPrice.toFixed(8), true) + "</span>";

                    }

                    if(value.volume >= 0) {

                        data.volume = "<span class=\"green\">" + library.numeral.initializeSeparator(value.volume.toFixed(8), true) + "</span>";

                    } else {

                        data.volume = "<span class=\"red\">" + library.numeral.initializeSeparator(value.volume.toFixed(8), true) + "</span>";

                    }

                    $scope.list.push(data);

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

    }


    $scope.initializeFilterInput = function() {

        var result = {
            "tradingInformation": []
        };

        angular.forEach($scope.filter.tradeInformation.value, function(value, key) {

            result.tradingInformation.push(value.value);

        });

        return result;

    }


    $scope.initializeInformation = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/initialize-information"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/";

            } else {

                $scope.loading.view = false;

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

            }

            $scope.hideResponse();

        });

        event.preventDefault();

    }


    $scope.loadList = function(event) {

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
                    "asset": $scope.filter.asset.value,
                    "exchanger": $scope.filter.exchanger.value,
                    "tradeInformationList": input.tradingInformation
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/load-list"
            };
            global.rest(rest, function(response) {

                if(response.result) {

                    if($scope.filter.exchanger.value == "Binance") {

                        $scope.list = [];

                        angular.forEach(response.cryptocurrencyBinanceAveragePriceTickerList, function(value, key) {

                            var data = {
                                "symbol": value.symbol,
                                "lastPrice": 0,
                                "priceChange": 0,
                                "priceChangePercent": "0%",
                                "highPrice": 0,
                                "lowPrice": 0,
                                "volume": 0
                            };

                            if(value.lastPrice >= 0) {

                                data.lastPrice = "<span class=\"green\">" + library.numeral.initializeSeparator(value.lastPrice.toFixed(8), true) + "</span>";

                            } else {

                                data.lastPrice = "<span class=\"red\">" + library.numeral.initializeSeparator(value.lastPrice.toFixed(8), true) + "</span>";

                            }

                            if(value.priceChange >= 0) {

                                data.priceChange = "<span class=\"green\">" + library.numeral.initializeSeparator(value.priceChange.toFixed(8), true) + "</span>";

                            } else {

                                data.priceChange = "<span class=\"red\">" + library.numeral.initializeSeparator(value.priceChange.toFixed(8), true) + "</span>";

                            }

                            if(value.priceChangePercent >= 0) {

                                data.priceChangePercent = "<span class=\"green\">" + library.numeral.initializeSeparator(value.priceChangePercent, true) + "%</span>";

                            } else {

                                data.priceChangePercent = "<span class=\"red\">" + library.numeral.initializeSeparator(value.priceChangePercent, true) + "%</span>";

                            }

                            if(value.highPrice >= 0) {

                                data.highPrice = "<span class=\"green\">" + library.numeral.initializeSeparator(value.highPrice.toFixed(8), true) + "</span>";

                            } else {

                                data.highPrice = "<span class=\"red\">" + library.numeral.initializeSeparator(value.highPrice.toFixed(8), true) + "</span>";

                            }

                            if(value.lowPrice >= 0) {

                                data.lowPrice = "<span class=\"green\">" + library.numeral.initializeSeparator(value.lowPrice.toFixed(8), true) + "</span>";

                            } else {

                                data.lowPrice = "<span class=\"red\">" + library.numeral.initializeSeparator(value.lowPrice.toFixed(8), true) + "</span>";

                            }

                            if(value.volume >= 0) {

                                data.volume = "<span class=\"green\">" + library.numeral.initializeSeparator(value.volume.toFixed(8), true) + "</span>";

                            } else {

                                data.volume = "<span class=\"red\">" + library.numeral.initializeSeparator(value.volume.toFixed(8), true) + "</span>";

                            }

                            $scope.list.push(data);

                        });

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

        } else {

            $scope.response.class = "error";
            $scope.response.message = "Please enter a valid data";
            $scope.response.view = true;

            $scope.hideResponse();

        }

        event.preventDefault();

    }


    $scope.searchFilterTradeInformation = function(search) {

        var result = [];

        if($scope.filter.exchanger.value == "Binance") {

            result = $filter("filter")($scope.filter.tradeInformation.option, {"value": search.replace("/", "")}, $scope.filterStartWith);

        }

        return result;

    }


    $scope.transformFilterTradeInformationChip = function(chip) {

        if(angular.isObject(chip)) {

            return chip;

        } else {

            return {
                "name": chip,
                "value": chip
            };

        }

    }


}]);
