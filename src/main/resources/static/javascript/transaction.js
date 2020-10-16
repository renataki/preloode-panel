app.controller("transaction", ["$scope", "$filter", "$window", "global", function($scope, $filter, $window, global) {


    $scope.additionalCost = {
        "administration": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "service": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "tax": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.adjustmentType = {
        "option": [
            {"name": "Adjustment Type", "value": "Unknown"},
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.amount = {
        "main": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "promotion": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.archive = {
        "value": false
    };

    $scope.filter = {
        "createdDate": {
            "value": ""
        },
        "from": {
            "option": [],
            "search": "",
            "value": ""
        },
        "id": {
            "value": ""
        },
        "status": {
            "option": [
                {"name": "Status", "value": "Unknown"}
            ]
        },
        "ticketNumber": {
            "value": ""
        },
        "toPaymentMethod": {
            "option": [
                {"id": "", "name": "To Payment Method"}
            ]
        },
        "toPaymentAccount": {
            "option": [
                {"id": "", "name": "To Payment Account"}
            ]
        },
        "toUserAccount": {
            "option": [
                {"id": "", "name": "To User Account"}
            ]
        },
        "toUser": {
            "option": [
                {"id": "", "name": "To User"}
            ]
        },
        "type": {
            "option": [
                {"name": "Type", "value": "Unknown"}
            ]
        }
    };

    $scope.filter.status.selected = $scope.filter.status.option[0];

    $scope.filter.type.selected = $scope.filter.type.option[0];

    $scope.from = {
        "payment": {
            "account": {
                "option": [{
                    "credit": "",
                    "id": "",
                    "methodId": "",
                    "methodName": "",
                    "name": "From Payment Account",
                    "number": ""
                }],
                "response": {
                    "class": "",
                    "value": "",
                    "view": false
                },
                "value": ""
            },
            "method": {
                "option": [
                    {"id": "", "name": "From Payment Method", "type": null}
                ],
                "response": {
                    "class": "",
                    "value": "",
                    "view": false
                },
                "value": ""
            }
        },
        "user": {
            "account": {
                "option": [{
                    "credit": "",
                    "thirdPartyAccountId": "",
                    "thirdPartyAccountUsername": "",
                    "thirdPartyProviderId": "",
                    "thirdPartyProviderName": "",
                    "username": "From User Account"
                }],
                "response": {
                    "class": "",
                    "value": "",
                    "view": false
                },
                "value": ""
            },
            "option": [
                {"credit": "", "id": "", "username": "From User"}
            ],
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.from.payment.account.selected = $scope.from.payment.account.option[0];

    $scope.from.payment.method.selected = $scope.from.payment.method.option[0];

    $scope.from.user.selected = $scope.from.user.option[0];

    $scope.from.user.account.selected = $scope.from.user.account.option[0];

    $scope.product = {
        "cycleList": {
            "value": []
        },
        "idList": {
            "value": []
        },
        "nameList": {
            "value": []
        },
        "variantList": {
            "nameList": {
                "value": [[]]
            },
            "price": {
                "oneTime": {
                    "discountList": {
                        "value": [[]]
                    },
                    "normalList": {
                        "value": [[]]
                    }
                },
                "recurring": {
                    "discountList": {
                        "value": [[[]]]
                    },
                    "normalList": {
                        "value": [[[]]]
                    }
                }
            },
            "skuList": {
                "value": [[]]
            },
            "stockList": {
                "value": [[]]
            }
        }
    };

    $scope.to = {
        "payment": {
            "account": {
                "option": [{
                    "credit": "",
                    "id": "",
                    "methodId": "",
                    "methodName": "",
                    "name": "To Payment Account",
                    "number": ""
                }],
                "response": {
                    "class": "",
                    "value": "",
                    "view": false
                },
                "value": ""
            },
            "method": {
                "option": [
                    {"id": "", "name": "To Payment Method", "type": null}
                ],
                "response": {
                    "class": "",
                    "value": "",
                    "view": false
                },
                "value": ""
            }
        },
        "user": {
            "account": {
                "option": [{
                    "credit": "",
                    "thirdPartyAccountId": "",
                    "thirdPartyAccountUsername": "",
                    "thirdPartyProviderId": "",
                    "thirdPartyProviderName": "",
                    "username": "To User Account"
                }],
                "response": {
                    "class": "",
                    "value": "",
                    "view": false
                },
                "value": ""
            },
            "option": [
                {"credit": "", "id": "", "username": "To User"}
            ],
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.to.payment.account.selected = $scope.to.payment.account.option[0];

    $scope.to.payment.method.selected = $scope.to.payment.method.option[0];

    $scope.to.user.selected = $scope.to.user.option[0];

    $scope.to.user.account.selected = $scope.to.user.account.option[0];

    $scope.type = {
        "option": [
            {"name": "Type", "value": "Unknown"}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.type.selected = $scope.type.option[0];

    $scope.valid = Object.assign($scope.valid, {
        "additionalCostAdministration": false,
        "additionalCostService": false,
        "additionalCostTax": false,
        "adjustmentType": false,
        "amountMain": false,
        "amountPromotion": false,
        "company": false,
        "companyBranch": false,
        "fromPaymentAccount": false,
        "fromPaymentMethod": false,
        "fromUser": false,
        "fromUserAccount": false,
        "toPaymentAccount": false,
        "toPaymentMethod": false,
        "toUser": false,
        "toUserAccount": false,
        "type": false
    });


    $scope.checkAdditionalCostAdministration = function() {

        if($scope.additionalCost.administration.value != "") {

            if(!$scope.additionalCost.administration.value.match(/^[0-9,.]+$/)) {

                $scope.additionalCost.administration.response.value = "Please enter only number";
                $scope.additionalCost.administration.response.class = "error";
                $scope.additionalCost.administration.response.view = true;
                $scope.valid.additionalCostAdministration = false;

            } else {

                $scope.additionalCost.administration.response.view = false;
                $scope.valid.additionalCostAdministration = true;

            }

        } else {

            $scope.additionalCost.administration.response.view = false;
            $scope.valid.additionalCostAdministration = true;

        }

        $scope.additionalCost.administration.value = library.numeral.initializeSeparator($scope.additionalCost.administration.value, false);

    }


    $scope.checkAdditionalCostService = function() {

        if($scope.additionalCost.service.value != "") {

            if(!$scope.additionalCost.service.value.match(/^[0-9,.]+$/)) {

                $scope.additionalCost.service.response.value = "Please enter only number";
                $scope.additionalCost.service.response.class = "error";
                $scope.additionalCost.service.response.view = true;
                $scope.valid.additionalCostService = false;

            } else {

                $scope.additionalCost.service.response.view = false;
                $scope.valid.additionalCostService = true;

            }

        } else {

            $scope.additionalCost.service.response.view = false;
            $scope.valid.additionalCostService = true;

        }

        $scope.additionalCost.service.value = library.numeral.initializeSeparator($scope.additionalCost.service.value, false);

    }


    $scope.checkAdditionalCostTax = function() {

        if($scope.additionalCost.tax.value != "") {

            if(!$scope.additionalCost.tax.value.match(/^[0-9,.]+$/)) {

                $scope.additionalCost.tax.response.value = "Please enter only number";
                $scope.additionalCost.tax.response.class = "error";
                $scope.additionalCost.tax.response.view = true;
                $scope.valid.additionalCostTax = false;

            } else {

                $scope.additionalCost.tax.response.view = false;
                $scope.valid.additionalCostTax = true;

            }

        } else {

            $scope.additionalCost.tax.response.view = false;
            $scope.valid.additionalCostTax = true;

        }

        $scope.additionalCost.tax.value = library.numeral.initializeSeparator($scope.additionalCost.tax.value, false);

    }


    $scope.checkAdjustmentType = function() {

        if($scope.adjustmentType.selected.value == "Unknown") {

            $scope.adjustmentType.response.value = "Please select adjustment type";
            $scope.adjustmentType.response.class = "error";
            $scope.adjustmentType.response.view = true;
            $scope.valid.adjustmentType = false;

        } else {

            $scope.adjustmentType.response.view = false;
            $scope.valid.adjustmentType = true;

        }

    }


    $scope.checkAmountMain = function() {

        if(!$scope.amount.main.value.match(/^[0-9,.]+$/)) {

            $scope.amount.main.response.value = "Please enter only number";
            $scope.amount.main.response.class = "error";
            $scope.amount.main.response.view = true;
            $scope.valid.amountMain = false;

        } else {

            $scope.amount.main.response.view = false;
            $scope.valid.amountMain = true;

        }

        $scope.amount.main.value = library.numeral.initializeSeparator($scope.amount.main.value, false);

    }


    $scope.checkAmountPromotion = function() {

        if($scope.amount.promotion.value != "") {

            if(!$scope.amount.promotion.value.match(/^[0-9,.]+$/)) {

                $scope.amount.promotion.response.value = "Please enter only number";
                $scope.amount.promotion.response.class = "error";
                $scope.amount.promotion.response.view = true;
                $scope.valid.amountPromotion = false;

            } else {

                $scope.amount.promotion.response.view = false;
                $scope.valid.amountPromotion = true;

            }

        } else {

            $scope.amount.promotion.response.view = false;
            $scope.valid.amountPromotion = true;

        }

        $scope.amount.promotion.value = library.numeral.initializeSeparator($scope.amount.promotion.value, false);

    }


    $scope.checkCompany = function() {

        if($scope.company.selected.id == "") {

            $scope.company.response.value = "Please select company";
            $scope.company.response.class = "error";
            $scope.company.response.view = true;
            $scope.valid.company = false;

        } else {

            $scope.company.response.view = false;
            $scope.valid.company = true;

        }

    }


    $scope.checkCompanyBranch = function() {

        if($scope.company.branch.selected.id == "") {

            $scope.company.branch.response.value = "Please select company branch";
            $scope.company.branch.response.class = "error";
            $scope.company.branch.response.view = true;
            $scope.valid.companyBranch = false;

        } else {

            $scope.company.branch.response.view = false;
            $scope.valid.companyBranch = true;

        }

    }


    $scope.checkData = function() {

        $scope.checkAdditionalCostAdministration();

        $scope.checkAdditionalCostService();

        $scope.checkAdditionalCostTax();

        $scope.checkAmountMain();

        $scope.checkAmountPromotion();

        $scope.checkCompany();

        $scope.checkCompanyBranch();

        if($scope.type.selected.value == "Adjustment") {

            $scope.checkAdjustmentType();

            if($scope.adjustmentType.selected.value == "Payment") {

                if($scope.from.payment.account.selected.id == "" && $scope.from.payment.method.selected.id == "") {

                    $scope.checkToPaymentAccount();

                    $scope.checkToPaymentMethod();

                    $scope.valid.fromPaymentAccount = true;
                    $scope.valid.fromPaymentMethod = true;
                    $scope.valid.fromUser = true;
                    $scope.valid.fromUserAccount = true;
                    $scope.valid.toUser = true;
                    $scope.valid.toUserAccount = true;

                } else if($scope.to.payment.account.selected.id == "" && $scope.to.payment.method.selected.id == "") {

                    $scope.checkFromPaymentAccount();

                    $scope.checkFromPaymentMethod();

                    $scope.valid.fromUser = true;
                    $scope.valid.fromUserAccount = true;
                    $scope.valid.toPaymentAccount = true;
                    $scope.valid.toPaymentMethod = true;
                    $scope.valid.toUser = true;
                    $scope.valid.toUserAccount = true;

                }

                if($scope.from.payment.account.selected.id == "" && $scope.from.payment.method.selected.id == "" && $scope.to.payment.account.selected.id == "" && $scope.to.payment.method.selected.id == "") {

                    $scope.from.payment.method.response.value = "Please select either one from payment or to payment";
                    $scope.from.payment.method.response.class = "error";
                    $scope.from.payment.method.response.view = true;

                    $scope.valid.fromPaymentAccount = false;
                    $scope.valid.fromPaymentMethod = false;
                    $scope.valid.fromUser = false;
                    $scope.valid.fromUserAccount = false;
                    $scope.valid.toPaymentAccount = false;
                    $scope.valid.toPaymentMethod = false;
                    $scope.valid.toUser = false;
                    $scope.valid.toUserAccount = false;

                }

            } else if($scope.adjustmentType.selected.value == "User") {

                if($scope.from.user.selected.id == "" && $scope.from.user.account.selected.username == "From User Account") {

                    $scope.checkToUser();

                    $scope.checkToUserAccount();

                    $scope.valid.fromPaymentAccount = true;
                    $scope.valid.fromPaymentMethod = true;
                    $scope.valid.fromUser = true;
                    $scope.valid.fromUserAccount = true;
                    $scope.valid.toPaymentAccount = true;
                    $scope.valid.toPaymentMethod = true;

                } else if($scope.to.user.selected.id == "" && $scope.to.user.account.selected.username == "To User Account") {

                    $scope.checkFromUser();

                    $scope.checkFromUserAccount();

                    $scope.valid.fromPaymentAccount = true;
                    $scope.valid.fromPaymentMethod = true;
                    $scope.valid.toPaymentAccount = true;
                    $scope.valid.toPaymentMethod = true;
                    $scope.valid.toUser = true;
                    $scope.valid.toUserAccount = true;

                }

                if($scope.from.user.selected.id == "" && $scope.from.user.account.selected.username == "From User Account" && $scope.to.user.selected.id == "" && $scope.to.user.account.selected.username == "To User Account") {

                    $scope.from.user.username.response.value = "Please select either one from user or to user";
                    $scope.from.user.username.response.class = "error";
                    $scope.from.user.username.response.view = true;

                    $scope.valid.fromPaymentAccount = false;
                    $scope.valid.fromPaymentMethod = false;
                    $scope.valid.fromUser = false;
                    $scope.valid.fromUserAccount = false;
                    $scope.valid.toPaymentAccount = false;
                    $scope.valid.toPaymentMethod = false;
                    $scope.valid.toUser = false;
                    $scope.valid.toUserAccount = false;

                }

            }

        } else if($scope.type.selected.value == "Checkout") {

            $scope.checkFromUser();

            $scope.checkToPaymentAccount();

            $scope.checkToPaymentMethod();

            $scope.valid.adjustmentType = true;
            $scope.valid.fromPaymentAccount = true;
            $scope.valid.fromPaymentMethod = true;
            $scope.valid.fromUserAccount = true;
            $scope.valid.toUser = true;
            $scope.valid.toUserAccount = true;

        } else if($scope.type.selected.value == "Deposit") {

            $scope.checkToPaymentAccount();

            $scope.checkToPaymentMethod();

            $scope.checkToUser();

            $scope.valid.adjustmentType = true;
            $scope.valid.fromPaymentAccount = true;
            $scope.valid.fromPaymentMethod = true;
            $scope.valid.fromUser = true;
            $scope.valid.fromUserAccount = true;
            $scope.valid.toUserAccount = true;

        } else if($scope.type.selected.value == "Expense") {

            $scope.checkFromPaymentAccount();

            $scope.checkFromPaymentMethod();

            $scope.valid.adjustmentType = true;
            $scope.valid.fromUser = true;
            $scope.valid.fromUserAccount = true;
            $scope.valid.toPaymentAccount = true;
            $scope.valid.toPaymentMethod = true;
            $scope.valid.toUser = true;
            $scope.valid.toUserAccount = true;

        } else if($scope.type.selected.value == "Income") {

            $scope.checkToPaymentAccount();

            $scope.checkToPaymentMethod();

            $scope.valid.adjustmentType = true;
            $scope.valid.fromPaymentAccount = true;
            $scope.valid.fromPaymentMethod = true;
            $scope.valid.fromUser = true;
            $scope.valid.fromUserAccount = true;
            $scope.valid.toUser = true;
            $scope.valid.toUserAccount = true;

        } else if($scope.type.selected.value == "Internal") {

            $scope.checkFromPaymentAccount();

            $scope.checkFromPaymentMethod();

            $scope.checkToPaymentAccount();

            $scope.checkToPaymentMethod();

            $scope.valid.adjustmentType = true;
            $scope.valid.fromUser = true;
            $scope.valid.fromUserAccount = true;
            $scope.valid.toUser = true;
            $scope.valid.toUserAccount = true;

        } else if($scope.type.selected.value == "Transfer") {

            $scope.checkFromUser();

            $scope.checkFromUserAccount();

            $scope.checkToUser();

            $scope.checkToUserAccount();

            $scope.valid.adjustmentType = true;
            $scope.valid.fromPaymentAccount = true;
            $scope.valid.fromPaymentMethod = true;
            $scope.valid.toPaymentAccount = true;
            $scope.valid.toPaymentMethod = true;

        } else if($scope.type.selected.value == "Withdrawal") {

            $scope.checkFromPaymentAccount();

            $scope.checkFromPaymentMethod();

            $scope.checkFromUser();

            $scope.valid.adjustmentType = true;
            $scope.valid.fromUserAccount = true;
            $scope.valid.toPaymentAccount = true;
            $scope.valid.toPaymentMethod = true;
            $scope.valid.toUser = true;
            $scope.valid.toUserAccount = true;

        }

        $scope.checkStatus();

        $scope.checkType();

    }


    $scope.checkFromPaymentAccount = function() {

        if($scope.from.payment.account.selected.id == "") {

            $scope.from.payment.account.response.value = "Please select from payment account";
            $scope.from.payment.account.response.class = "error";
            $scope.from.payment.account.response.view = true;
            $scope.valid.fromPaymentAccount = false;

        } else {

            $scope.from.payment.account.response.view = false;
            $scope.valid.fromPaymentAccount = true;

        }

    }


    $scope.checkFromPaymentMethod = function() {

        if($scope.from.payment.method.selected.id == "") {

            $scope.from.payment.method.response.value = "Please select from payment method";
            $scope.from.payment.method.response.class = "error";
            $scope.from.payment.method.response.view = true;
            $scope.valid.fromPaymentMethod = false;

        } else {

            $scope.from.payment.method.response.view = false;
            $scope.valid.fromPaymentMethod = true;

            $scope.loading.view = true;

            var rest = {
                "data": {
                    "methodId": $scope.from.payment.method.selected.id,
                    "userId": $scope.to.user.selected.id
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/transaction/initialize-payment-account"
            };
            global.rest(rest, function(response) {

                if(response.result) {

                    $scope.from.payment.account.option = [{
                        "credit": 0,
                        "id": "",
                        "methodId": "",
                        "methodName": "",
                        "name": "From Payment Account",
                        "number": ""
                    }];

                    if($scope.to.user.selected.id == "") {

                        angular.forEach(response.paymentAccountList, function(value, key) {

                            $scope.from.payment.account.option.push({
                                "credit": value.credit,
                                "id": value.id,
                                "methodId": value.method.id,
                                "methodName": value.method.name,
                                "name": value.name,
                                "number": value.number
                            });

                        });

                    } else {

                        angular.forEach(response.user.payment.nameList, function(value, key) {

                            $scope.from.payment.account.option.push({
                                "credit": 0,
                                "id": "",
                                "methodId": response.user.payment.method.idList[key],
                                "methodName": response.user.payment.method.nameList[key],
                                "name": response.user.payment.nameList[key],
                                "number": response.user.payment.numberList[key]
                            });

                        });

                    }

                } else {

                    $scope.response.class = "error";
                    $scope.response.message = response.response;
                    $scope.response.view = true;

                }

                $scope.loading.view = false;

                $scope.hideResponse();

            });

        }

    }


    $scope.checkFromUser = function() {

        if($scope.from.user.selected.id == "") {

            $scope.from.user.response.value = "Please select from user";
            $scope.from.user.response.class = "error";
            $scope.from.user.response.view = true;
            $scope.valid.fromUser = false;

        } else {

            $scope.from.user.response.view = false;
            $scope.valid.fromUser = true;

            $scope.loading.view = true;

            var rest = {
                "data": $scope.from.user.selected.id,
                "url": document.getElementById("global").getAttribute("data-base-url") + "/transaction/initialize-user-account"
            };
            global.rest(rest, function(response) {

                if(response.result) {

                    $scope.from.user.account.option = [{
                        "credit": 0,
                        "thirdPartyAccountId": "",
                        "thirdPartyAccountUsername": "",
                        "thirdPartyProviderId": "",
                        "thirdPartyProviderName": "",
                        "username": "From User Account"
                    }];

                    angular.forEach(response.userList, function(value, key) {

                        for(var i = 0; i < value.account.usernameList.length; i++) {

                            $scope.from.user.account.option.push({
                                "credit": valueChild1.credit,
                                "thirdPartyAccountId": valueChild1.thirdParty.account.idList,
                                "thirdPartyAccountUsername": valueChild1.thirdParty.account.usernameList,
                                "thirdPartyProviderId": valueChild1.thirdParty.provider.idList,
                                "thirdPartyProviderName": valueChild1.thirdParty.provider.nameList,
                                "username": valueChild1.username
                            });

                        }

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

    }


    $scope.checkFromUserAccount = function() {

        if($scope.from.user.account.selected.id == "") {

            $scope.from.user.account.response.value = "Please select from user account";
            $scope.from.user.account.response.class = "error";
            $scope.from.user.account.response.view = true;
            $scope.valid.fromUserAccount = false;

        } else {

            $scope.from.user.account.response.view = false;
            $scope.valid.fromUserAccount = true;

        }

    }


    $scope.checkToPaymentAccount = function() {

        if($scope.to.payment.account.selected.id == "") {

            $scope.to.payment.account.response.value = "Please select to payment account";
            $scope.to.payment.account.response.class = "error";
            $scope.to.payment.account.response.view = true;
            $scope.valid.toPaymentAccount = false;

        } else {

            $scope.to.payment.account.response.view = false;
            $scope.valid.toPaymentAccount = true;

        }

    }


    $scope.checkToPaymentMethod = function() {

        if($scope.to.payment.method.selected.id == "") {

            $scope.to.payment.method.response.value = "Please select to payment method";
            $scope.to.payment.method.response.class = "error";
            $scope.to.payment.method.response.view = true;
            $scope.valid.toPaymentMethod = false;

        } else {

            $scope.to.payment.method.response.view = false;
            $scope.valid.toPaymentMethod = true;

            $scope.loading.view = true;

            var rest = {
                "data": {
                    "methodId": $scope.to.payment.method.selected.id,
                    "userId": $scope.from.user.selected.id
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/transaction/initialize-payment-account"
            };
            global.rest(rest, function(response) {

                if(response.result) {

                    $scope.to.payment.account.option = [{
                        "credit": 0,
                        "id": "",
                        "methodId": "",
                        "methodName": "",
                        "name": "To Payment Account",
                        "number": ""
                    }];

                    if($scope.from.user.selected.id == "") {

                        angular.forEach(response.paymentAccountList, function(value, key) {

                            $scope.to.payment.account.option.push({
                                "credit": value.credit,
                                "id": value.id,
                                "methodId": value.method.id,
                                "methodName": value.method.name,
                                "name": value.name,
                                "number": value.number
                            });

                        });

                    } else {

                        angular.forEach(response.user.payment.nameList, function(value, key) {

                            $scope.to.payment.account.option.push({
                                "credit": 0,
                                "id": "",
                                "methodId": response.user.payment.method.idList[key],
                                "methodName": response.user.payment.method.nameList[key],
                                "name": response.user.payment.nameList[key],
                                "number": response.user.payment.numberList[key]
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

        }

    }


    $scope.checkToUser = function() {

        if($scope.to.user.selected.id == "") {

            $scope.to.user.response.value = "Please select to user";
            $scope.to.user.response.class = "error";
            $scope.to.user.response.view = true;
            $scope.valid.toUser = false;

        } else {

            $scope.to.user.response.view = false;
            $scope.valid.toUser = true;

            $scope.loading.view = true;

            var rest = {
                "data": $scope.to.user.selected.id,
                "url": document.getElementById("global").getAttribute("data-base-url") + "/transaction/initialize-user-account"
            };
            global.rest(rest, function(response) {

                if(response.result) {

                    $scope.to.user.account.option = [{
                        "credit": 0,
                        "thirdPartyAccountId": "",
                        "thirdPartyAccountUsername": "",
                        "thirdPartyProviderId": "",
                        "thirdPartyProviderName": "",
                        "username": "To User Account"
                    }];

                    angular.forEach(response.userList, function(value, key) {

                        for(var i = 0; i < value.account.usernameList.length; i++) {

                            $scope.to.user.account.option.push({
                                "credit": value.account.creditList[i],
                                "thirdPartyAccountId": value.account.thirdParty.account.idList[i],
                                "thirdPartyAccountUsername": value.account.thirdParty.account.usernameList[i],
                                "thirdPartyProviderId": value.account.thirdParty.provider.idList[i],
                                "thirdPartyProviderName": value.account.thirdParty.provider.nameList[i],
                                "username": value.account.usernameList[i]
                            });

                        }

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

    }


    $scope.checkToUserAccount = function() {

        if($scope.to.user.account.selected.id == "") {

            $scope.to.user.account.response.value = "Please select to user third party account";
            $scope.to.user.account.response.class = "error";
            $scope.to.user.account.response.view = true;
            $scope.valid.toUserAccount = false;

        } else {

            $scope.to.user.account.response.view = false;
            $scope.valid.toUserAccount = true;

        }

    }


    $scope.checkType = function() {

        if($scope.type.selected.value == "Unknown") {

            $scope.type.response.value = "Please select type";
            $scope.type.response.class = "error";
            $scope.type.response.view = true;
            $scope.valid.type = false;

        } else {

            $scope.type.response.view = false;
            $scope.valid.type = true;

        }

    }


    $scope.delete = function(id, event) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/transaction/delete"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $scope.response.class = "success";

                document.getElementsByClassName(id)[0].remove();

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


    $scope.edit = function(event) {

        $scope.checkData();

        var valid = true;

        angular.forEach($scope.valid, function(value, key) {

            if(!value) {

                valid = false;

                return false;

            }

        });

        if(valid) {

            $scope.loading.view = true;

            var input = $scope.initializeInput();

            var rest = {
                "data": {
                    "id": $scope.id.value,
                    "additionalCost": {
                        "administration": input.additionalCost.administration,
                        "service": input.additionalCost.service,
                        "tax": input.additionalCost.tax
                    },
                    "amount": {
                        "main": input.amount.main,
                        "promotion": input.amount.promotion
                    },
                    "archive": $scope.archive.value,
                    "company": {
                        "branch": {
                            "id": $scope.company.branch.selected.id,
                            "name": $scope.company.branch.selected.name
                        },
                        "id": $scope.company.selected.id,
                        "name": $scope.company.selected.name
                    },
                    "from": {
                        "payment": {
                            "account": {
                                "id": $scope.from.payment.account.selected.id,
                                "name": input.from.payment.account.name,
                                "number": $scope.from.payment.account.selected.number
                            },
                            "method": {
                                "id": $scope.from.payment.method.selected.id,
                                "name": input.from.payment.method.name,
                                "type": $scope.from.payment.method.selected.type
                            }
                        },
                        "user": {
                            "account": {
                                "thirdParty": {
                                    "account": {
                                        "id": $scope.from.user.account.selected.thirdPartyAccountId,
                                        "username": $scope.from.user.account.selected.thirdPartyAccountUsername
                                    },
                                    "provider": {
                                        "id": $scope.from.user.account.selected.thirdPartyProviderId,
                                        "name": $scope.from.user.account.selected.thirdPartyProviderName
                                    }
                                },
                                "username": input.from.user.account.username
                            },
                            "id": $scope.from.user.selected.id,
                            "username": input.from.user.username
                        }
                    },
                    "imageList": $scope.imageList.value,
                    "product": {
                        "cycleList": $scope.product.cycleList.value,
                        "idList": $scope.product.idList.value,
                        "nameList": $scope.product.nameList.value,
                        "variantList": {
                            "nameList": $scope.product.variantList.nameList.value,
                            "price": {
                                "oneTime": {
                                    "discountList": $scope.product.variantList.price.oneTime.discountList.value,
                                    "normalList": $scope.product.variantList.price.oneTime.normalList.value
                                },
                                "recurring": {
                                    "discountList": $scope.product.variantList.price.recurring.discountList.value,
                                    "normalList": $scope.product.variantList.price.recurring.normalList.value
                                }
                            },
                            "skuList": $scope.product.variantList.skuList.value,
                            "stockList": $scope.product.variantList.stockList.value
                        }
                    },
                    "reference": $scope.reference.value,
                    "status": $scope.status.selected.value,
                    "to": {
                        "payment": {
                            "account": {
                                "id": $scope.to.payment.account.selected.id,
                                "name": input.to.payment.account.name,
                                "number": $scope.to.payment.account.selected.number
                            },
                            "method": {
                                "id": $scope.to.payment.method.selected.id,
                                "name": input.to.payment.method.name,
                                "type": $scope.to.payment.method.selected.type
                            }
                        },
                        "user": {
                            "account": {
                                "thirdParty": {
                                    "account": {
                                        "id": $scope.to.user.account.selected.thirdPartyAccountId,
                                        "username": $scope.to.user.account.selected.thirdPartyAccountUsername
                                    },
                                    "provider": {
                                        "id": $scope.to.user.account.selected.thirdPartyProviderId,
                                        "name": $scope.to.user.account.selected.thirdPartyProviderName
                                    }
                                },
                                "username": input.to.user.account.username
                            },
                            "id": $scope.to.user.selected.id,
                            "username": input.to.user.username
                        }
                    },
                    "type": $scope.type.selected.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/transaction/update"
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

        } else {

            $scope.response.class = "error";
            $scope.response.message = "Please enter a valid data";
            $scope.response.view = true;

            $scope.hideResponse();

        }

        event.preventDefault();

    }


    $scope.filterPagination = function(event) {

        $scope.loading.view = true;

        var filterCreatedDate = $scope.filter.createdDate.value.split(" to ");

        if(filterCreatedDate.length < 2) {

            filterCreatedDate.push("");

        }

        if(filterCreatedDate[0] != "") {

            filterCreatedDate[0] += " 00:00:00";

        }

        if(filterCreatedDate[1] != "") {

            filterCreatedDate[1] += " 23:59:59";

        }

        var data = {
            "id": ["equal", $scope.filter.id.value],
            "created.timestamp": ["between", "date", filterCreatedDate[0], filterCreatedDate[1]],
            "name": ["like", $scope.filter.name.value],
            "status": ["equal", ""]
        };

        if($scope.filter.status.selected.value != "Unknown") {

            data["status"][1] = $scope.filter.status.selected.value;

        }

        var rest = {
            "data": data,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/transaction/filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/transaction/";

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


    $scope.forceUploadImageList = function() {

        document.getElementsByClassName("transaction-image-list")[0].click();

    }


    $scope.goToPage = function(event) {

        if(event.which == 13) {

            $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/transaction/page-" + $scope.site.page.number + "/";

        }

        event.preventDefault();

    }


    $scope.initializeData = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/transaction/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.transaction.id != null) {

                    $scope.additionalCost.administration.value = library.numeral.initializeSeparator(response.transaction.additionalCost.administration, true);
                    $scope.additionalCost.service.value = library.numeral.initializeSeparator(response.transaction.additionalCost.service, true);
                    $scope.additionalCost.tax.value = library.numeral.initializeSeparator(response.transaction.additionalCost.tax, true);
                    $scope.amount.main.value = library.numeral.initializeSeparator(response.transaction.amount.main, true);
                    $scope.amount.promotion.value = library.numeral.initializeSeparator(response.transaction.amount.promotion, true);
                    $scope.archive.value = response.transaction.archive;

                    var createdTimestamp = new Date(response.transaction.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.transaction.created.user.id;
                    $scope.created.user.username.value = response.transaction.created.user.username;

                    if(response.transaction.imageList.length > 0) {

                        $scope.imageList.value = response.transaction.imageList;
                        $scope.imageList.upload.file = response.transaction.imageList;
                        $scope.imageList.upload.view = true;

                    }

                    $scope.id.value = response.transaction.id;

                    var modifiedTimestamp = new Date(response.transaction.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.transaction.modified.user.id;
                    $scope.modified.user.username.value = response.transaction.modified.user.username;
                    $scope.product.cycleList.value = response.transaction.product.cycleList;
                    $scope.product.idList.value = response.transaction.product.idList;
                    $scope.product.nameList.value = response.transaction.product.nameList;
                    $scope.product.variantList.nameList.value = response.transaction.product.variantList.nameList;
                    $scope.product.variantList.price.oneTime.discountList.value = response.transaction.product.variantList.price.oneTime.discountList;
                    $scope.product.variantList.price.oneTime.normalList.value = response.transaction.product.variantList.price.oneTime.normalList;
                    $scope.product.variantList.price.recurring.discountList.value = response.transaction.product.variantList.price.recurring.discountList;
                    $scope.product.variantList.price.recurring.normalList.value = response.transaction.product.variantList.price.recurring.normalList;
                    $scope.product.variantList.skuList.value = response.transaction.product.variantList.skuList;
                    $scope.product.variantList.stockList.value = response.transaction.product.variantList.stockList;
                    $scope.reference.value = response.transaction.reference;

                }

                angular.forEach(response.adjustmentTypeList, function(value, key) {

                    $scope.adjustmentType.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.transaction.id != null) {

                        if(value == "Payment" && (response.transaction.from.payment.account.id != null || response.transaction.to.payment.account.id != null)) {

                            $scope.adjustmentType.selected = $scope.adjustmentType.option[(key + 1)];

                        } else if(value == "User" && (response.transaction.from.user.account.username != "" || response.transaction.to.user.account.username != "")) {

                            $scope.adjustmentType.selected = $scope.adjustmentType.option[(key + 1)];

                        }

                    }

                });

                $scope.company.branch.option = [{
                    "id": "",
                    "name": "Branch"
                }];

                angular.forEach(response.companyBranchList, function(value, key) {

                    $scope.company.branch.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                    if(response.transaction.id != null) {

                        if(value.id == response.transaction.company.branch.id) {

                            $scope.company.branch.selected = $scope.company.branch.option[(key + 1)];

                        }

                    }

                });

                $scope.company.option = [{
                    "id": "",
                    "name": "Company"
                }];

                angular.forEach(response.companyList, function(value, key) {

                    $scope.company.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                    if(response.transaction.id != null) {

                        if(value.id == response.transaction.company.id) {

                            $scope.company.selected = $scope.company.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.paymentMethodList, function(value, key) {

                    $scope.from.payment.method.option.push({
                        "id": value.id,
                        "name": value.name,
                        "type": value.type
                    });

                    $scope.to.payment.method.option.push({
                        "id": value.id,
                        "name": value.name,
                        "type": value.type
                    });

                    if(response.transaction.id != null) {

                        if(value.id == response.transaction.from.payment.method.id) {

                            $scope.from.payment.method.selected = $scope.from.payment.method.option[(key + 1)];

                        }

                        if(value.id == response.transaction.to.payment.method.id) {

                            $scope.to.payment.method.selected = $scope.to.payment.method.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.paymentAccountList, function(value, key) {

                    if(value.method.id == $scope.from.payment.method.selected.id) {

                        $scope.from.payment.account.option.push({
                            "credit": value.credit,
                            "id": value.id,
                            "methodId": value.method.id,
                            "methodName": value.method.name,
                            "name": value.name,
                            "number": value.number
                        });

                    }

                    if(value.method.id == $scope.to.payment.method.selected.id) {

                        $scope.to.payment.account.option.push({
                            "credit": value.credit,
                            "id": value.id,
                            "methodId": value.method.id,
                            "methodName": value.method.name,
                            "name": value.name,
                            "number": value.number
                        });

                    }

                    if(response.transaction.id != null) {

                        if(value.id == response.transaction.from.payment.account.id) {

                            $scope.from.payment.account.selected = $scope.from.payment.account.option[($scope.from.payment.account.option.length - 1)];

                        }

                        if(value.id == response.transaction.to.payment.account.id) {

                            $scope.to.payment.account.selected = $scope.to.payment.account.option[($scope.to.payment.account.option.length - 1)];

                        }

                    }

                });

                angular.forEach(response.transactionStatusList, function(value, key) {

                    $scope.status.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.transaction.id != null) {

                        if(value == response.transaction.status) {

                            $scope.status.selected = $scope.status.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.transactionTypeList, function(value, key) {

                    $scope.type.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.transaction.id != null) {

                        if(value == response.transaction.type) {

                            $scope.type.selected = $scope.type.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.userList, function(value, key) {

                    $scope.from.user.option.push({
                        "credit": value.credit,
                        "id": value.id,
                        "username": value.username
                    });

                    $scope.to.user.option.push({
                        "credit": value.credit,
                        "id": value.id,
                        "username": value.username
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

    }


    $scope.initializeInput = function() {

        var result = {
            "additionalCost": {
                "administration": 0,
                "service": 0,
                "tax": 0
            },
            "amount": {
                "main": 0,
                "promotion": 0
            },
            "from": {
                "payment": {
                    "account": {
                        "name": ""
                    },
                    "method": {
                        "name": ""
                    }
                },
                "user": {
                    "account": {
                        "username": ""
                    },
                    "username": ""
                }
            },
            "to": {
                "payment": {
                    "account": {
                        "name": ""
                    },
                    "method": {
                        "name": ""
                    }
                },
                "user": {
                    "account": {
                        "username": ""
                    },
                    "username": ""
                }
            }
        };

        $scope.additionalCost.administration.value = library.numeral.initializeSeparator($scope.additionalCost.administration.value, true);
        $scope.additionalCost.service.value = library.numeral.initializeSeparator($scope.additionalCost.service.value, true);
        $scope.additionalCost.tax.value = library.numeral.initializeSeparator($scope.additionalCost.tax.value, true);
        $scope.amount.main.value = library.numeral.initializeSeparator($scope.amount.main.value, true);
        $scope.amount.promotion.value = library.numeral.initializeSeparator($scope.amount.promotion.value, true);

        result.additionalCost.administration = $scope.additionalCost.administration.value.replace(/[^0-9.]/g, "");
        result.additionalCost.service = $scope.additionalCost.service.value.replace(/[^0-9.]/g, "");
        result.additionalCost.tax = $scope.additionalCost.tax.value.replace(/[^0-9.]/g, "");
        result.amount.main = $scope.amount.main.value.replace(/[^0-9.]/g, "");
        result.amount.promotion = $scope.amount.promotion.value.replace(/[^0-9.]/g, "");

        if($scope.from.payment.account.selected.name != "From Payment Account") {

            result.from.payment.account.name = $scope.from.payment.account.selected.name;

        }

        if($scope.from.payment.method.selected.name != "From Payment Method") {

            result.from.payment.method.name = $scope.from.payment.method.selected.name;

        }

        if($scope.from.user.account.selected.username != "From User Account") {

            result.from.user.account.username = $scope.from.user.account.selected.username;

        }

        if($scope.from.user.selected.username != "From User") {

            result.from.user.username = $scope.from.user.selected.username;

        }

        if($scope.to.payment.account.selected.name != "To Payment Account") {

            result.to.payment.account.name = $scope.to.payment.account.selected.name;

        }

        if($scope.to.payment.method.selected.name != "To Payment Method") {

            result.to.payment.method.name = $scope.to.payment.method.selected.name;

        }

        if($scope.to.user.account.selected.username != "To User Account") {

            result.to.user.account.username = $scope.to.user.account.selected.username;

        }

        if($scope.to.user.selected.username != "To User") {

            result.to.user.username = $scope.to.user.selected.username;

        }

        return result;

    }


    $scope.initializePagination = function() {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/transaction/initialize-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(Object.entries(response.filter).length > 0) {

                    if(response.filter.created_timestamp.length > 3) {

                        if(response.filter.created_timestamp[2] != "" && response.filter.created_timestamp[3] != "") {

                            var startTimestamp = new Date(response.filter.created_timestamp[2]);
                            var endTimestamp = new Date(response.filter.created_timestamp[3]);
                            $scope.filter.createdDate.value = startTimestamp.getFullYear() + "-" + ("0" + (startTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + startTimestamp.getDate()).slice(-2) + " to " + endTimestamp.getFullYear() + "-" + ("0" + (endTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + endTimestamp.getDate()).slice(-2);

                        }

                    }

                    $scope.filter.id.value = response.filter.id[1];
                    $scope.filter.name.value = response.filter.name[1];

                }

                angular.forEach(response.paymentAccountList, function(value, key) {

                    $scope.filter.from.option.push({
                        "name": value.method.name + " - " + value.name + " - " + value.number,
                        "type": "Payment Account",
                        "value": value.id
                    });

                });

                angular.forEach(response.transactionStatusList, function(value, key) {

                    $scope.filter.status.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(Object.entries(response.filter).length > 0) {

                        if(value == response.filter.status[1]) {

                            $scope.filter.status.selected = $scope.filter.status.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.transactionTypeList, function(value, key) {

                    $scope.filter.type.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(Object.entries(response.filter).length > 0) {

                        if(value == response.filter.type[1]) {

                            $scope.filter.type.selected = $scope.filter.type.option[(key + 1)];

                        }

                    }

                });

                $scope.site.page.number = response.page;
                $scope.site.page.size = response.size;

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


    $scope.insert = function(event) {

        $scope.checkData();

        var valid = true;

        angular.forEach($scope.valid, function(value, key) {

            if(!value) {

                valid = false;

                return false;

            }

        });

        if(valid) {

            $scope.loading.view = true;

            var input = $scope.initializeInput();

            var rest = {
                "data": {
                    "additionalCost": {
                        "administration": input.additionalCost.administration,
                        "service": input.additionalCost.service,
                        "tax": input.additionalCost.tax
                    },
                    "amount": {
                        "main": input.amount.main,
                        "promotion": input.amount.promotion
                    },
                    "archive": $scope.archive.value,
                    "company": {
                        "branch": {
                            "id": $scope.company.branch.selected.id,
                            "name": $scope.company.branch.selected.name
                        },
                        "id": $scope.company.selected.id,
                        "name": $scope.company.selected.name
                    },
                    "from": {
                        "payment": {
                            "account": {
                                "id": $scope.from.payment.account.selected.id,
                                "name": input.from.payment.account.name,
                                "number": $scope.from.payment.account.selected.number
                            },
                            "method": {
                                "id": $scope.from.payment.method.selected.id,
                                "name": input.from.payment.method.name,
                                "type": $scope.from.payment.method.selected.type
                            }
                        },
                        "user": {
                            "account": {
                                "thirdParty": {
                                    "account": {
                                        "id": $scope.from.user.account.selected.thirdPartyAccountId,
                                        "username": $scope.from.user.account.selected.thirdPartyAccountUsername
                                    },
                                    "provider": {
                                        "id": $scope.from.user.account.selected.thirdPartyProviderId,
                                        "name": $scope.from.user.account.selected.thirdPartyProviderName
                                    }
                                },
                                "username": input.from.user.account.username
                            },
                            "id": $scope.from.user.selected.id,
                            "username": input.from.user.username
                        }
                    },
                    "imageList": $scope.imageList.value,
                    "product": {
                        "cycleList": $scope.product.cycleList.value,
                        "idList": $scope.product.idList.value,
                        "nameList": $scope.product.nameList.value,
                        "variantList": {
                            "nameList": $scope.product.variantList.nameList.value,
                            "price": {
                                "oneTime": {
                                    "discountList": $scope.product.variantList.price.oneTime.discountList.value,
                                    "normalList": $scope.product.variantList.price.oneTime.normalList.value
                                },
                                "recurring": {
                                    "discountList": $scope.product.variantList.price.recurring.discountList.value,
                                    "normalList": $scope.product.variantList.price.recurring.normalList.value
                                }
                            },
                            "skuList": $scope.product.variantList.skuList.value,
                            "stockList": $scope.product.variantList.stockList.value
                        }
                    },
                    "reference": $scope.reference.value,
                    "status": $scope.status.selected.value,
                    "to": {
                        "payment": {
                            "account": {
                                "id": $scope.to.payment.account.selected.id,
                                "name": input.to.payment.account.name,
                                "number": $scope.to.payment.account.selected.number
                            },
                            "method": {
                                "id": $scope.to.payment.method.selected.id,
                                "name": input.to.payment.method.name,
                                "type": $scope.to.payment.method.selected.type
                            }
                        },
                        "user": {
                            "account": {
                                "thirdParty": {
                                    "account": {
                                        "id": $scope.to.user.account.selected.thirdPartyAccountId,
                                        "username": $scope.to.user.account.selected.thirdPartyAccountUsername
                                    },
                                    "provider": {
                                        "id": $scope.to.user.account.selected.thirdPartyProviderId,
                                        "name": $scope.to.user.account.selected.thirdPartyProviderName
                                    }
                                },
                                "username": input.to.user.account.username
                            },
                            "id": $scope.to.user.selected.id,
                            "username": input.to.user.username
                        }
                    },
                    "type": $scope.type.selected.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/transaction/insert"
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

        } else {

            $scope.response.class = "error";
            $scope.response.message = "Please enter a valid data";
            $scope.response.view = true;

            $scope.hideResponse();

        }

        event.preventDefault();

    }


    $scope.loadDetail = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/transaction/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.transaction.id != null) {

                    $scope.content.value = response.shopCategory.content;

                    var createdTimestamp = new Date(response.shopCategory.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.description.value = response.shopCategory.description;
                    $scope.dislike.value = response.shopCategory.dislike;

                    if(response.shopCategory.imageList != "") {

                        $scope.imageList.upload.file = [];

                        angular.forEach(response.shopCategory.imageList, function(value, key) {

                            $scope.imageList.upload.file.push(document.getElementById("global").getAttribute("data-base-url") + "/resource/image/shop/category/" + value);

                        });

                        $scope.imageList.upload.view = true;

                    }

                    $scope.id.value = response.shopCategory.id;
                    $scope.like.value = response.shopCategory.like;

                    var modifiedTimestamp = new Date(response.shopCategory.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.meta.description.value = response.shopCategory.meta.description;
                    $scope.meta.keyword.value = response.shopCategory.meta.keyword;
                    $scope.meta.title.value = response.shopCategory.meta.title;
                    $scope.name.value = response.shopCategory.name;
                    $scope.og.description.value = response.shopCategory.og.description;
                    $scope.og.title.value = response.shopCategory.og.title;
                    $scope.parent.value = response.shopCategory.parent.name;
                    $scope.rate.value = response.shopCategory.rate;
                    $scope.sequence.value = response.shopCategory.sequence;
                    $scope.status.value = response.shopCategory.status;

                    if(response.shopCategory.thumbnailList != "") {

                        $scope.thumbnailList.upload.file = [];

                        angular.forEach(response.shopCategory.thumbnailList, function(value, key) {

                            $scope.thumbnailList.upload.file.push(document.getElementById("global").getAttribute("data-base-url") + "/resource/image/shop/category/" + value);

                        });

                        $scope.thumbnailList.upload.view = true;

                    }

                    $scope.url.value = response.shopCategory.url;
                    $scope.view.value = response.shopCategory.view;

                }

                $scope.popup.view = true;
                $scope.popup.shopCategory = true;

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

    }


    $scope.removeFilterPagination = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/transaction/remove-filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.reload();

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


    $scope.searchFilterFrom = function(search) {

        return $filter("filter")($scope.filter.from.option, {"name": search}, $scope.filterStartWith);

    }


    $scope.setPagination = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": $scope.site.page.number,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/transaction/set-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/transaction/";

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


    $scope.uploadImageList = function(index) {

        $scope.loading.view = true;

        var formData = new FormData();

        angular.forEach($scope.files, function(value) {

            formData.append("file", value);

        });

        var rest = {
            "data": formData,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/transaction/upload-image-list"
        };
        global.restMultipart(rest, function(response) {

            if(response.result) {

                $scope.response.class = "success";

                $scope.imageList.value = response.imageList;
                $scope.imageList.upload.file = response.imageList;
                $scope.imageList.upload.view = true;

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


app.directive("transactionDetail", function() {


    var transaction = {};


    transaction.templateUrl = document.getElementById("global").getAttribute("data-base-url") + "/html/transaction-detail-popup.html";


    return transaction;


});


app.directive("transactionImageListPreview", function() {


    var transaction = {};


    transaction.template = "<span ng-repeat=\"data in imageList.upload.file\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/transaction/{{data}}\" />" +
        "</span>";


    return transaction;


});
