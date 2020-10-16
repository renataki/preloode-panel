app.controller("cryptocurrencyTriangularArbitrage", ["$scope", "$filter", "$interval", "$window", "global", function($scope, $filter, $interval, $window, global) {


    $scope.data = {
        "id": "",
        "created": {
            "timestamp": "",
            "user": {
                "id": "",
                "username": ""
            }
        },
        "modified": {
            "timestamp": "",
            "user": {
                "id": "",
                "username": ""
            }
        },
        "end": {
            "action": "",
            "asset": {
                "base": "",
                "quote": ""
            },
            "credit": {
                "end": 0,
                "start": 0
            },
            "orderId": 0,
            "price": 0,
            "quantity": {
                "minimum": 0,
                "order": 0
            },
            "symbol": "",
            "type": "Market"
        },
        "exchanger": "",
        "middle": {
            "action": "",
            "asset": {
                "base": "",
                "quote": ""
            },
            "credit": {
                "end": 0,
                "start": 0
            },
            "orderId": 0,
            "price": 0,
            "quantity": {
                "minimum": 0,
                "order": 0
            },
            "symbol": "",
            "type": "Market"
        },
        "percentage": 0,
        "start": {
            "action": "",
            "asset": {
                "base": "",
                "quote": ""
            },
            "credit": {
                "end": 0,
                "start": 0
            },
            "orderId": 0,
            "price": 0,
            "quantity": {
                "minimum": 0,
                "order": 0
            },
            "symbol": "",
            "type": "Market"
        },
        "status": "Pending"
    };

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
            "exchanger": false,
            "lotSize": false,
            "minimumPercentage": false,
            "tradeInformation": false
        }
    };

    $scope.list = [];

    $scope.status = {
        "class": "green",
        "interval": 0,
        "text": "Stopped",
        "value": "Idle"
    };

    $scope.stomp = Stomp.client(document.getElementById("global").getAttribute("data-websocket-url"));

    $scope.stream = {
        "end": {
            "action": "",
            "asset": {
                "base": "",
                "quote": ""
            },
            "credit": {
                "end": 0,
                "start": 0
            },
            "price": {
                "enable": {
                    "name": "",
                    "value": false
                },
                "value": 0
            },
            "quantity": {
                "minimum": 0,
                "order": 0
            },
            "symbol": ""
        },
        "exchanger": "",
        "middle": {
            "action": "",
            "asset": {
                "base": "",
                "quote": ""
            },
            "credit": {
                "end": 0,
                "start": 0
            },
            "price": {
                "enable": {
                    "name": "",
                    "value": false
                },
                "value": 0
            },
            "quantity": {
                "minimum": 0,
                "order": 0
            },
            "symbol": ""
        },
        "percentage": 0,
        "start": {
            "action": "",
            "asset": {
                "base": "",
                "quote": ""
            },
            "credit": {
                "end": 0,
                "start": 0
            },
            "price": {
                "enable": {
                    "name": "",
                    "value": false
                },
                "value": 0
            },
            "quantity": {
                "minimum": 0,
                "order": 0
            },
            "symbol": ""
        }
    };

    $scope.tradeInformation = {};


    $scope.calculateExchangeableCredit = function(credit, size) {

        var result = Math.floor((credit / size)) * size;

        var creditPrecision = result.toString().split(".");
        var sizePrecision = size.toString().split(".");

        if(creditPrecision.length > 1) {

            if(creditPrecision[1].length > sizePrecision[1].length) {

                creditPrecision[1] = creditPrecision[1].slice(0, sizePrecision[1].length);

            }

            result = creditPrecision[0] + "." + creditPrecision[1];

        }

        result = parseFloat(result);

        return result;

    }


    $scope.calculateExchangeFee = function(exchanger, lotSize, minimumQuantity) {

        var result = lotSize - (lotSize * $scope.exchanger[exchanger.toLowerCase()].fee.commission / 100);
        result = $scope.calculateExchangeableCredit(result, minimumQuantity);

        return result;

    }


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

        $scope.checkFilterLotSize();

        $scope.checkFilterMinimumPercentage();

        $scope.checkFilterTradeInformation();

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


    $scope.checkFilterTradeInformation = function() {

        if($scope.filter.tradeInformation.value.length > 0) {

            if($scope.filter.tradeInformation.value.length != 3) {

                $scope.filter.tradeInformation.response.value = "Please select 3 trade information";
                $scope.filter.tradeInformation.response.class = "error";
                $scope.filter.tradeInformation.response.view = true;
                $scope.filter.valid.tradeInformation = false;

            } else {

                $scope.filter.tradeInformation.response.view = false;
                $scope.filter.valid.tradeInformation = true;

            }

        } else {

            $scope.filter.tradeInformation.response.view = false;
            $scope.filter.valid.tradeInformation = true;

        }

    }


    $scope.initializeData = function() {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/triangular-arbitrage/initialize-data"
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

                $scope.tradeInformation.binance = response.cryptocurrencyBinanceExchangeInformationList;

                angular.forEach($scope.tradeInformation.binance, function(value, key) {

                    $scope.filter.tradeInformation.option.push({
                        "name": value.baseAsset + "/" + value.quoteAsset,
                        "value": value.symbol
                    });

                });

                angular.forEach(response.cryptocurrencyTriangularArbitrageList, function(value, key) {

                    $scope.list.push({
                        "end": {
                            "action": value.end.action,
                            "asset": {
                                "base": value.end.asset.base,
                                "quote": value.end.asset.quote
                            },
                            "credit": {
                                "end": value.end.credit.end,
                                "start": value.end.credit.start
                            },
                            "price": library.numeral.initializeSeparator(value.end.price.toFixed(8), true),
                            "quantity": {
                                "minimum": value.end.quantity.minimum,
                                "order": library.numeral.initializeSeparator(value.end.quantity.order.toFixed(8), true)
                            },
                            "symbol": value.end.symbol,
                            "type": value.end.type
                        },
                        "exchanger": value.exchanger,
                        "middle": {
                            "action": value.middle.action,
                            "asset": {
                                "base": value.middle.asset.base,
                                "quote": value.middle.asset.quote
                            },
                            "credit": {
                                "end": value.middle.credit.end,
                                "start": value.middle.credit.start
                            },
                            "price": library.numeral.initializeSeparator(value.middle.price.toFixed(8), true),
                            "quantity": {
                                "minimum": value.middle.quantity.minimum,
                                "order": library.numeral.initializeSeparator(value.middle.quantity.order.toFixed(8), true)
                            },
                            "symbol": value.middle.symbol,
                            "type": value.middle.type
                        },
                        "percentage": library.numeral.initializeSeparator(value.percentage.toFixed(2), true),
                        "start": {
                            "action": value.start.action,
                            "asset": {
                                "base": value.start.asset.base,
                                "quote": value.start.asset.quote
                            },
                            "credit": {
                                "end": value.start.credit.end,
                                "start": value.start.credit.start
                            },
                            "price": library.numeral.initializeSeparator(value.start.price.toFixed(8), true),
                            "quantity": {
                                "minimum": value.start.quantity.minimum,
                                "order": library.numeral.initializeSeparator(value.start.quantity.order.toFixed(8), true)
                            },
                            "symbol": value.start.symbol,
                            "type": value.start.type
                        },
                        "status": value.status
                    });

                });

                $scope.filter.asset.value = response.cryptocurrencyTriangularArbitrageFilter.asset;
                $scope.filter.exchanger.value = response.cryptocurrencyTriangularArbitrageFilter.exchanger;
                $scope.filter.lotSize.value = library.numeral.initializeSeparator(response.cryptocurrencyTriangularArbitrageFilter.lotSize, true);
                $scope.filter.minimumPercentage.value = library.numeral.initializeSeparator(response.cryptocurrencyTriangularArbitrageFilter.minimumPercentage, true);

                if(response.cryptocurrencyTriangularArbitrageFilter.streamList != null) {

                    $scope.filter.tradeInformation.value = response.cryptocurrencyTriangularArbitrageFilter.streamList;

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
            "lotSize": 0,
            "minimumPercentage": 0,
            "tradingInformation": []
        };

        $scope.filter.lotSize.value = library.numeral.initializeSeparator($scope.filter.lotSize.value, true);
        $scope.filter.minimumPercentage.value = library.numeral.initializeSeparator($scope.filter.minimumPercentage.value, true);

        result.lotSize = $scope.filter.lotSize.value.replace(/[^0-9.]/g, "");
        result.minimumPercentage = $scope.filter.minimumPercentage.value.replace(/[^0-9.]/g, "");

        angular.forEach($scope.filter.tradeInformation.value, function(value, key) {

            result.tradingInformation.push(value.value);

        });

        return result;

    }


    $scope.initializeSwitch = function() {

        if($scope.stream.end.price.enable.value) {

            $scope.stream.end.price.enable.name = "Enabled";

        } else {

            $scope.stream.end.price.enable.name = "Disabled";

        }

        if($scope.stream.middle.price.enable.value) {

            $scope.stream.middle.price.enable.name = "Enabled";

        } else {

            $scope.stream.middle.price.enable.name = "Disabled";

        }

        if($scope.stream.start.price.enable.value) {

            $scope.stream.start.price.enable.name = "Enabled";

        } else {

            $scope.stream.start.price.enable.name = "Disabled";

        }

    }


    $scope.initializeTradeInput = function() {

        var result = {
            "end": {
                "credit": {
                    "end": 0,
                    "start": 0
                },
                "price": 0,
                "quantity": {
                    "minimum": 0,
                    "order": 0
                }
            },
            "middle": {
                "credit": {
                    "end": 0,
                    "start": 0
                },
                "price": 0,
                "quantity": {
                    "minimum": 0,
                    "order": 0
                }
            },
            "percentage": 0,
            "start": {
                "credit": {
                    "end": 0,
                    "start": 0
                },
                "price": 0,
                "quantity": {
                    "minimum": 0,
                    "order": 0
                }
            }
        };

        $scope.data.end.credit.end = library.numeral.initializeSeparator($scope.data.end.credit.end, true);
        $scope.data.end.credit.start = library.numeral.initializeSeparator($scope.data.end.credit.start, true);
        $scope.data.end.price = library.numeral.initializeSeparator($scope.data.end.price, true);
        $scope.data.end.quantity.minimum = library.numeral.initializeSeparator($scope.data.end.quantity.minimum, true);
        $scope.data.end.quantity.order = library.numeral.initializeSeparator($scope.data.end.quantity.order, true);
        $scope.data.middle.credit.end = library.numeral.initializeSeparator($scope.data.middle.credit.end, true);
        $scope.data.middle.credit.start = library.numeral.initializeSeparator($scope.data.middle.credit.start, true);
        $scope.data.middle.price = library.numeral.initializeSeparator($scope.data.middle.price, true);
        $scope.data.middle.quantity.minimum = library.numeral.initializeSeparator($scope.data.middle.quantity.minimum, true);
        $scope.data.middle.quantity.order = library.numeral.initializeSeparator($scope.data.middle.quantity.order, true);
        $scope.data.percentage = library.numeral.initializeSeparator($scope.data.percentage, true);
        $scope.data.start.credit.end = library.numeral.initializeSeparator($scope.data.start.credit.end, true);
        $scope.data.start.credit.start = library.numeral.initializeSeparator($scope.data.start.credit.start, true);
        $scope.data.start.price = library.numeral.initializeSeparator($scope.data.start.price, true);
        $scope.data.start.quantity.minimum = library.numeral.initializeSeparator($scope.data.start.quantity.minimum, true);
        $scope.data.start.quantity.order = library.numeral.initializeSeparator($scope.data.start.quantity.order, true);

        result.end.credit.end = $scope.data.end.credit.end.replace(/[^0-9.]/g, "");
        result.end.credit.start = $scope.data.end.credit.start.replace(/[^0-9.]/g, "");
        result.end.price = $scope.data.end.price.replace(/[^0-9.]/g, "");
        result.end.quantity.minimum = $scope.data.end.quantity.minimum.replace(/[^0-9.]/g, "");
        result.end.quantity.order = $scope.data.end.quantity.order.replace(/[^0-9.]/g, "");
        result.middle.credit.end = $scope.data.middle.credit.end.replace(/[^0-9.]/g, "");
        result.middle.credit.start = $scope.data.middle.credit.start.replace(/[^0-9.]/g, "");
        result.middle.price = $scope.data.middle.price.replace(/[^0-9.]/g, "");
        result.middle.quantity.minimum = $scope.data.middle.quantity.minimum.replace(/[^0-9.]/g, "");
        result.middle.quantity.order = $scope.data.middle.quantity.order.replace(/[^0-9.]/g, "");
        result.percentage = $scope.data.percentage.replace(/[^0-9.]/g, "");
        result.start.credit.end = $scope.data.start.credit.end.replace(/[^0-9.]/g, "");
        result.start.credit.start = $scope.data.start.credit.start.replace(/[^0-9.]/g, "");
        result.start.price = $scope.data.start.price.replace(/[^0-9.]/g, "");
        result.start.quantity.minimum = $scope.data.start.quantity.minimum.replace(/[^0-9.]/g, "");
        result.start.quantity.order = $scope.data.start.quantity.order.replace(/[^0-9.]/g, "");

        return result;

    }


    $scope.loadCoinInformation = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": $scope.filter.exchanger.value,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/triangular-arbitrage/load-coin-information"
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


    $scope.loadDataStream = function(index, event) {

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
                    "lotSize": input.lotSize,
                    "minimumPercentage": input.minimumPercentage,
                    "streamList": [$scope.list[index].start.symbol, $scope.list[index].middle.symbol, $scope.list[index].end.symbol]
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/triangular-arbitrage/load-data-stream"
            };
            global.rest(rest, function(response) {

                if(response.result) {

                    if($scope.filter.exchanger.value == "Binance") {

                        $scope.exchanger.binance.websocket.id = response.cryptocurrencyBinanceWebsocketId;
                        $scope.exchanger.binance.websocket.parameter = response.cryptocurrencyBinanceWebsocketParameter;

                        $scope.stomp.connect({}, function() {

                            $scope.stomp.subscribe("/output-stream/cryptocurrency/binance/stream", function(output) {

                                var message = JSON.parse(output.body);
                                var outputMessage = JSON.parse(message.outputMessage);

                                if(outputMessage.hasOwnProperty("data")) {

                                    $scope.stream.exchanger = "Binance";
                                    $scope.stream.start.symbol = $scope.list[index].start.symbol;
                                    $scope.stream.middle.symbol = $scope.list[index].middle.symbol;
                                    $scope.stream.end.symbol = $scope.list[index].end.symbol;

                                    $scope.loadTriangularArbitrageBinance(outputMessage.data.s, parseFloat(outputMessage.data.c));

                                }

                                $scope.$digest();

                            });

                        });

                    }

                    $scope.popup.view = true;
                    $scope.popup.cryptocurrencyTriangularArbitrage = true;

                    $scope.rebuild();

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
                    "lotSize": input.lotSize,
                    "minimumPercentage": input.minimumPercentage,
                    "streamList": input.tradingInformation
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/triangular-arbitrage/load-list"
            };
            global.rest(rest, function(response) {

                if(response.result) {

                    if($scope.filter.exchanger.value == "Binance") {

                        $scope.list = [];

                        angular.forEach(response.cryptocurrencyTriangularArbitrageList, function(value, key) {

                            $scope.list.push({
                                "end": {
                                    "action": value.end.action,
                                    "asset": {
                                        "base": value.end.asset.base,
                                        "quote": value.end.asset.quote
                                    },
                                    "credit": {
                                        "end": value.end.credit.end,
                                        "start": value.end.credit.start
                                    },
                                    "price": library.numeral.initializeSeparator(value.end.price.toFixed(8), true),
                                    "quantity": {
                                        "minimum": value.end.quantity.minimum,
                                        "order": library.numeral.initializeSeparator(value.end.quantity.order.toFixed(8), true)
                                    },
                                    "symbol": value.end.symbol,
                                    "type": value.end.type
                                },
                                "exchanger": value.exchanger,
                                "middle": {
                                    "action": value.middle.action,
                                    "asset": {
                                        "base": value.middle.asset.base,
                                        "quote": value.middle.asset.quote
                                    },
                                    "credit": {
                                        "end": value.middle.credit.end,
                                        "start": value.middle.credit.start
                                    },
                                    "price": library.numeral.initializeSeparator(value.middle.price.toFixed(8), true),
                                    "quantity": {
                                        "minimum": value.middle.quantity.minimum,
                                        "order": library.numeral.initializeSeparator(value.middle.quantity.order.toFixed(8), true)
                                    },
                                    "symbol": value.middle.symbol,
                                    "type": value.middle.type
                                },
                                "percentage": library.numeral.initializeSeparator(value.percentage.toFixed(2), true),
                                "start": {
                                    "action": value.start.action,
                                    "asset": {
                                        "base": value.start.asset.base,
                                        "quote": value.start.asset.quote
                                    },
                                    "credit": {
                                        "end": value.start.credit.end,
                                        "start": value.start.credit.start
                                    },
                                    "price": library.numeral.initializeSeparator(value.start.price.toFixed(8), true),
                                    "quantity": {
                                        "minimum": value.start.quantity.minimum,
                                        "order": library.numeral.initializeSeparator(value.start.quantity.order.toFixed(8), true)
                                    },
                                    "symbol": value.start.symbol,
                                    "type": value.start.type
                                },
                                "status": value.status
                            });

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


    $scope.loadMinimumQuantityBinance = function(symbol) {

        var result = 0;

        var tradeInformation = $filter("filter")($scope.tradeInformation.binance, {"symbol": symbol});

        if(tradeInformation.length > 0) {

            var filter = $filter("filter")(tradeInformation[0].filters, {"filterType": "LOT_SIZE"});

            if(filter.length > 0) {

                result = filter[0].minQty;

            }

        }

        return result;

    }


    $scope.loadTriangularArbitrageBinance = function(symbol, price) {

        if(symbol == $scope.stream.start.symbol && $scope.data.status == "Pending") {

            if(symbol.startsWith($scope.filter.asset.value)) {

                $scope.stream.start.action = "Ask";
                $scope.stream.start.asset.base = $scope.filter.asset.value;
                $scope.stream.start.asset.quote = symbol.replace($scope.filter.asset.value, "");

            } else if(symbol.endsWith($scope.filter.asset.value)) {

                $scope.stream.start.action = "Bid";
                $scope.stream.start.asset.base = symbol.replace($scope.filter.asset.value, "");
                $scope.stream.start.asset.quote = $scope.filter.asset.value;

            }

            if($scope.stream.start.price.enable.value) {

                $scope.stream.start.price.value = parseFloat($scope.data.start.price.replace(/[^0-9.]/g, ""));

            } else {

                $scope.stream.start.price.value = price;

            }

        } else if(symbol == $scope.stream.middle.symbol && ($scope.data.status == "Pending" || $scope.data.status == "Start")) {

            if($scope.stream.start.action != "") {

                if($scope.stream.middle.price.enable.value) {

                    $scope.stream.middle.price.value = parseFloat($scope.data.middle.price.replace(/[^0-9.]/g, ""));

                } else {

                    $scope.stream.middle.price.value = price;

                }

                if(symbol.startsWith($scope.stream.start.asset.base) || symbol.startsWith($scope.stream.start.asset.quote)) {

                    $scope.stream.middle.action = "Ask";
                    $scope.stream.middle.asset.base = $scope.stream.start.asset.quote;
                    $scope.stream.middle.asset.quote = symbol.replace($scope.stream.start.asset.quote, "");

                } else if(symbol.endsWith($scope.stream.start.asset.base) || symbol.endsWith($scope.stream.start.asset.quote)) {

                    $scope.stream.middle.action = "Bid";
                    $scope.stream.middle.asset.base = symbol.replace($scope.stream.start.asset.quote, "");
                    $scope.stream.middle.asset.quote = $scope.stream.start.asset.quote;

                }

            }

        } else if(symbol == $scope.stream.end.symbol && ($scope.data.status == "Pending" || $scope.data.status == "Start" || $scope.data.status == "Middle")) {

            if(symbol.startsWith($scope.filter.asset.value)) {

                $scope.stream.end.action = "Bid";
                $scope.stream.end.asset.base = symbol.replace($scope.filter.asset.value, "");
                $scope.stream.end.asset.quote = $scope.filter.asset.value;

            } else if(symbol.endsWith($scope.filter.asset.value)) {

                $scope.stream.end.action = "Ask";
                $scope.stream.end.asset.base = $scope.filter.asset.value;
                $scope.stream.end.asset.quote = symbol.replace($scope.filter.asset.value, "");

            }

            if($scope.stream.end.price.enable.value) {

                $scope.stream.end.price.value = parseFloat($scope.data.end.price.replace(/[^0-9.]/g, ""));

            } else {

                $scope.stream.end.price.value = price;

            }

        }

        if($scope.stream.start.action != "" && $scope.stream.middle.action != "" && $scope.stream.end.action != "") {

            var lotSize = parseFloat($scope.filter.lotSize.value);

            if($scope.stream.start.asset.base != $scope.filter.asset.value) {

                lotSize = parseFloat((lotSize / $scope.stream.start.price.value).toFixed(8));

            }

            $scope.stream.start.quantity.minimum = $scope.loadMinimumQuantityBinance($scope.stream.start.symbol);

            if($scope.stream.start.quantity.minimum < lotSize) {

                $scope.stream.start.credit.start = lotSize + (lotSize * $scope.exchanger[$scope.stream.exchanger.toLowerCase()].fee.commission / 100);
                $scope.stream.start.quantity.order = lotSize;

                if($scope.stream.start.action == "Ask") {

                    $scope.stream.start.credit.end = ($scope.stream.start.quantity.order * $scope.stream.start.price.value).toFixed(8);

                } else {

                    $scope.stream.start.credit.end = lotSize;

                }

                $scope.stream.middle.quantity.minimum = $scope.loadMinimumQuantityBinance($scope.stream.middle.symbol);
                $scope.stream.middle.credit.start = $scope.stream.start.credit.end;

                var exchangeableCredit = $scope.calculateExchangeableCredit($scope.stream.middle.credit.start, $scope.stream.middle.quantity.minimum);
                $scope.stream.middle.quantity.order = $scope.calculateExchangeFee($scope.stream.exchanger, exchangeableCredit, $scope.stream.middle.quantity.minimum);

                if($scope.stream.middle.action == "Ask") {

                    $scope.stream.middle.credit.end = ($scope.stream.middle.quantity.order * $scope.stream.middle.price.value).toFixed(8);

                } else {

                    $scope.stream.middle.credit.end = ($scope.stream.middle.quantity.order / $scope.stream.middle.price.value).toFixed(8);
                    $scope.stream.middle.quantity.order = $scope.calculateExchangeableCredit(($scope.stream.middle.quantity.order / $scope.stream.middle.price.value), $scope.stream.middle.quantity.minimum);

                }

                $scope.stream.end.quantity.minimum = $scope.loadMinimumQuantityBinance($scope.stream.end.symbol);
                $scope.stream.end.credit.start = $scope.stream.middle.credit.end;

                exchangeableCredit = $scope.calculateExchangeableCredit($scope.stream.end.credit.start, $scope.stream.end.quantity.minimum);
                $scope.stream.end.quantity.order = $scope.calculateExchangeFee($scope.stream.exchanger, exchangeableCredit, $scope.stream.end.quantity.minimum);

                if($scope.stream.end.action == "Ask") {

                    $scope.stream.end.credit.end = ($scope.stream.end.quantity.order * $scope.stream.end.price.value).toFixed(8);

                } else {

                    $scope.stream.end.credit.end = ($scope.stream.end.quantity.order / $scope.stream.end.price.value).toFixed(8);

                }

                $scope.stream.percentage = (($scope.stream.end.credit.end - $scope.stream.start.credit.start) / $scope.stream.start.credit.start * 100).toFixed(2);

            }

            $scope.data.end.action = $scope.stream.end.action;
            $scope.data.end.asset.base = $scope.stream.end.asset.base;
            $scope.data.end.asset.quote = $scope.stream.end.asset.quote;
            $scope.data.end.credit.end = $scope.stream.end.credit.end;
            $scope.data.end.credit.start = $scope.stream.end.credit.start;
            $scope.data.end.credit.start = $scope.stream.end.credit.start;

            if(!$scope.stream.end.price.enable.value) {

                $scope.data.end.price = library.numeral.initializeSeparator(parseFloat($scope.stream.end.price.value).toFixed(8), true);

            } else {

                $scope.data.end.type = "Limit";

            }

            $scope.data.end.quantity.minimum = $scope.stream.end.quantity.minimum;
            $scope.data.end.quantity.order = library.numeral.initializeSeparator($scope.stream.end.quantity.order.toFixed(8), true);
            $scope.data.end.symbol = $scope.stream.end.symbol;
            $scope.data.exchanger = $scope.stream.exchanger;
            $scope.data.middle.action = $scope.stream.middle.action;
            $scope.data.middle.asset.base = $scope.stream.middle.asset.base;
            $scope.data.middle.asset.quote = $scope.stream.middle.asset.quote;
            $scope.data.middle.credit.end = $scope.stream.middle.credit.end;
            $scope.data.middle.credit.start = $scope.stream.middle.credit.start;

            if(!$scope.stream.middle.price.enable.value) {

                $scope.data.middle.price = library.numeral.initializeSeparator(parseFloat($scope.stream.middle.price.value).toFixed(8), true);

            } else {

                $scope.data.middle.type = "Limit";

            }

            $scope.data.middle.quantity.minimum = $scope.stream.middle.quantity.minimum;
            $scope.data.middle.quantity.order = library.numeral.initializeSeparator($scope.stream.middle.quantity.order, true);
            $scope.data.middle.symbol = $scope.stream.middle.symbol;
            $scope.data.percentage = $scope.stream.percentage;
            $scope.data.start.action = $scope.stream.start.action;
            $scope.data.start.asset.base = $scope.stream.start.asset.base;
            $scope.data.start.asset.quote = $scope.stream.start.asset.quote;
            $scope.data.start.credit.end = $scope.stream.start.credit.end;
            $scope.data.start.credit.start = $scope.stream.start.credit.start;

            if(!$scope.stream.start.price.enable.value) {

                $scope.data.start.price = library.numeral.initializeSeparator(parseFloat($scope.stream.start.price.value).toFixed(8), true);

            } else {

                $scope.data.start.type = "Limit";

            }

            $scope.data.start.quantity.minimum = $scope.stream.start.quantity.minimum;
            $scope.data.start.quantity.order = library.numeral.initializeSeparator($scope.stream.start.quantity.order.toFixed(8), true);
            $scope.data.start.symbol = $scope.stream.start.symbol;

        }

        if($scope.status.value == "Trade") {

            $scope.trade();

        }

    }


    $scope.run = function(event) {

        $scope.loading.view = true;

        if($scope.status.value == "Idle") {

            $scope.status.class = "red";
            $scope.status.text = "Running";
            $scope.status.value = "Trade";

            $scope.response.message = "Cryptocurrency triangular arbitrage ran";

        } else if($scope.status.value == "Trade") {

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


    $scope.searchFilterTradeInformation = function(search) {

        var result = [];

        if($scope.filter.exchanger.value == "Binance") {

            result = $filter("filter")($scope.filter.tradeInformation.option, {"value": search.replace("/", "")}, $scope.filterStartWith);

        }

        return result;

    }


    $scope.trade = function() {

        if($scope.data.status != "Completed") {

            if($scope.data.percentage > $scope.filter.minimumPercentage.value) {

                $scope.loading.view = true;

                $scope.status.value = "Executing";

                var input = $scope.initializeTradeInput();

                var rest = {
                    "data": {
                        "end": {
                            "action": $scope.data.end.action,
                            "asset": {
                                "base": $scope.data.end.asset.base,
                                "quote": $scope.data.end.asset.quote
                            },
                            "credit": {
                                "end": input.end.credit.end,
                                "start": input.end.credit.start
                            },
                            "price": input.end.price,
                            "quantity": {
                                "minimum": input.end.quantity.minimum,
                                "order": input.end.quantity.order
                            },
                            "status": $scope.data.end.status,
                            "symbol": $scope.data.end.symbol,
                            "type": $scope.data.end.type
                        },
                        "exchanger": $scope.data.exchanger,
                        "middle": {
                            "action": $scope.data.middle.action,
                            "asset": {
                                "base": $scope.data.middle.asset.base,
                                "quote": $scope.data.middle.asset.quote
                            },
                            "credit": {
                                "end": input.middle.credit.end,
                                "start": input.middle.credit.start
                            },
                            "price": input.middle.price,
                            "quantity": {
                                "minimum": input.middle.quantity.minimum,
                                "order": input.middle.quantity.order
                            },
                            "status": $scope.data.middle.status,
                            "symbol": $scope.data.middle.symbol,
                            "type": $scope.data.middle.type
                        },
                        "percentage": $scope.data.percentage,
                        "start": {
                            "action": $scope.data.start.action,
                            "asset": {
                                "base": $scope.data.start.asset.base,
                                "quote": $scope.data.start.asset.quote
                            },
                            "credit": {
                                "end": input.start.credit.end,
                                "start": input.start.credit.start
                            },
                            "price": input.start.price,
                            "quantity": {
                                "minimum": input.start.quantity.minimum,
                                "order": input.start.quantity.order
                            },
                            "status": $scope.data.start.status,
                            "symbol": $scope.data.start.symbol,
                            "type": $scope.data.start.type
                        },
                        "status": $scope.data.status
                    },
                    "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/triangular-arbitrage/trade-binance"
                };
                global.rest(rest, function(response) {

                    if(response.result) {

                        $scope.data = response.cryptocurrencyTriangularArbitrage;

                        $scope.status.value = "Trade";

                        $scope.response.class = "success";

                    } else {

                        $scope.response.class = "error";

                    }

                    $scope.loading.view = false;

                    $scope.response.message = response.response;
                    $scope.response.view = true;

                    $scope.hideResponse();

                });

            }

        }

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


    $scope.unsubscribeStream = function() {

        $scope.loading.view = true;

        var exchanger = $scope.filter.exchanger.value.toLowerCase();
        var streamList = [];
        var websocketId = "";

        if($scope.exchanger.hasOwnProperty(exchanger)) {

            streamList = $scope.exchanger[exchanger].websocket.parameter;
            websocketId = $scope.exchanger[exchanger].websocket.id

        }

        var rest = {
            "data": {
                "exchanger": $scope.filter.exchanger.value,
                "streamList": streamList,
                "websocketId": websocketId
            },
            "url": document.getElementById("global").getAttribute("data-base-url") + "/cryptocurrency/triangular-arbitrage/unsubscribe-stream"
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

    }


}]);


app.directive("cryptocurrencyTriangularArbitrageDetail", function() {


    var cryptocurrencyTriangularArbitrage = {};


    cryptocurrencyTriangularArbitrage.templateUrl = document.getElementById("global").getAttribute("data-base-url") + "/html/cryptocurrency-triangular-arbitrage-detail-popup.html";


    return cryptocurrencyTriangularArbitrage;


});
