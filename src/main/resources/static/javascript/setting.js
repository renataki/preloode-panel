app.controller("setting", ["$scope", "$window", "global", function($scope, $window, global) {


    $scope.addToCart = {
        "amount": {
            "maximum": {
                "value": ""
            },
            "minimum": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        },
        "averageTime": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.adjustment = {
        "amount": {
            "maximum": {
                "value": ""
            },
            "minimum": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        },
        "averageTime": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.checkout = {
        "amount": {
            "maximum": {
                "value": ""
            },
            "minimum": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        },
        "averageTime": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.city = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.contact = {
        "emailAddress": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "faxNumber": {
            "countryCode": {
                "option": []
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "lineId": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "phoneNumber": {
            "countryCode": {
                "option": []
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "wechatId": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "whatsappNumber": {
            "countryCode": {
                "option": []
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.country = {
        "option": [
            {"name": "Country", "value": "Unknown"}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.country.selected = $scope.country.option[0];

    $scope.deposit = {
        "amount": {
            "maximum": {
                "value": ""
            },
            "minimum": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        },
        "averageTime": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.expense = {
        "amount": {
            "maximum": {
                "value": ""
            },
            "minimum": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        },
        "averageTime": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.favicon = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "upload": {
            "file": "",
            "view": false
        },
        "value": ""
    };

    $scope.fee = {
        "amount": {
            "maximum": {
                "value": ""
            },
            "minimum": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        },
        "averageTime": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.internal = {
        "amount": {
            "maximum": {
                "value": ""
            },
            "minimum": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        },
        "averageTime": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.maintenance = {
        "finish": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "next": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.membership = {
        "password": {
            "name": "",
            "value": false
        }
    };

    $scope.name = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.registrationNumber = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.state = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.transfer = {
        "amount": {
            "maximum": {
                "value": ""
            },
            "minimum": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        },
        "averageTime": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.valid = Object.assign($scope.valid, {
        "addToCartAmount": false,
        "addToCartAverageTime": false,
        "adjustmentAmount": false,
        "adjustmentAverageTime": false,
        "checkoutAmount": false,
        "checkoutAverageTime": false,
        "city": false,
        "company": true,
        "companyBranch": true,
        "contactEmailAddress": false,
        "contactFaxNumber": false,
        "contactLineId": false,
        "contactPhoneNumber": false,
        "contactWechatId": false,
        "contactWhatsappNumber": false,
        "country": false,
        "depositAmount": false,
        "depositAverageTime": false,
        "expenseAmount": false,
        "expenseAverageTime": false,
        "feeAmount": false,
        "feeAverageTime": false,
        "internalAmount": false,
        "internalAverageTime": false,
        "name": false,
        "registrationNumber": false,
        "state": false,
        "transferAmount": false,
        "transferAverageTime": false,
        "withdrawalAmount": false,
        "withdrawalAverageTime": false,
        "zipCode": false
    });

    $scope.withdrawal = {
        "amount": {
            "maximum": {
                "value": ""
            },
            "minimum": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        },
        "averageTime": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.zipCode = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };


    $scope.checkAddToCartAmount = function() {

        if($scope.addToCart.amount.maximum.value != "" || $scope.addToCart.amount.minimum.value != "") {

            if(!$scope.addToCart.amount.maximum.value.match(/^[0-9,.]+$/) || !$scope.addToCart.amount.minimum.value.match(/^[0-9,.]+$/)) {

                $scope.addToCart.amount.response.value = "Please enter decimal amount";
                $scope.addToCart.amount.response.class = "error";
                $scope.addToCart.amount.response.view = true;
                $scope.valid.addToCartAmount = false;

            } else {

                $scope.addToCart.amount.response.view = false;
                $scope.valid.addToCartAmount = true;

            }

        } else {

            $scope.addToCart.amount.response.view = false;
            $scope.valid.addToCartAmount = true;

        }

        $scope.addToCart.amount.maximum.value = library.numeral.initializeSeparator($scope.addToCart.amount.maximum.value, false);
        $scope.addToCart.amount.minimum.value = library.numeral.initializeSeparator($scope.addToCart.amount.minimum.value, false);

    }


    $scope.checkAddToCartAverageTime = function() {

        if($scope.addToCart.averageTime.value != "") {

            if(!$scope.addToCart.averageTime.value.match(/^[0-9,]+$/) || !$scope.addToCart.averageTime.value.match(/^[0-9,]+$/)) {

                $scope.addToCart.averageTime.response.value = "Please enter number only";
                $scope.addToCart.averageTime.response.class = "error";
                $scope.addToCart.averageTime.response.view = true;
                $scope.valid.addToCartAverageTime = false;

            } else {

                $scope.addToCart.averageTime.response.view = false;
                $scope.valid.addToCartAverageTime = true;

            }

        } else {

            $scope.addToCart.averageTime.response.view = false;
            $scope.valid.addToCartAverageTime = true;

        }

        $scope.addToCart.averageTime.value = library.numeral.initializeSeparator($scope.addToCart.averageTime.value, false);

    }


    $scope.checkAdjustmentAmount = function() {

        if($scope.adjustment.amount.maximum.value != "" || $scope.adjustment.amount.minimum.value != "") {

            if(!$scope.adjustment.amount.maximum.value.match(/^[0-9,.]+$/) || !$scope.adjustment.amount.minimum.value.match(/^[0-9,.]+$/)) {

                $scope.adjustment.amount.response.value = "Please enter decimal amount";
                $scope.adjustment.amount.response.class = "error";
                $scope.adjustment.amount.response.view = true;
                $scope.valid.adjustmentAmount = false;

            } else {

                $scope.adjustment.amount.response.view = false;
                $scope.valid.adjustmentAmount = true;

            }

        } else {

            $scope.adjustment.amount.response.view = false;
            $scope.valid.adjustmentAmount = true;

        }

        $scope.adjustment.amount.maximum.value = library.numeral.initializeSeparator($scope.adjustment.amount.maximum.value, false);
        $scope.adjustment.amount.minimum.value = library.numeral.initializeSeparator($scope.adjustment.amount.minimum.value, false);

    }


    $scope.checkAdjustmentAverageTime = function() {

        if($scope.adjustment.averageTime.value != "") {

            if(!$scope.adjustment.averageTime.value.match(/^[0-9,]+$/) || !$scope.adjustment.averageTime.value.match(/^[0-9,]+$/)) {

                $scope.adjustment.averageTime.response.value = "Please enter number only";
                $scope.adjustment.averageTime.response.class = "error";
                $scope.adjustment.averageTime.response.view = true;
                $scope.valid.adjustmentAverageTime = false;

            } else {

                $scope.adjustment.averageTime.response.view = false;
                $scope.valid.adjustmentAverageTime = true;

            }

        } else {

            $scope.adjustment.averageTime.response.view = false;
            $scope.valid.adjustmentAverageTime = true;

        }

        $scope.adjustment.averageTime.value = library.numeral.initializeSeparator($scope.adjustment.averageTime.value, false);

    }


    $scope.checkCheckoutAmount = function() {

        if($scope.checkout.amount.maximum.value != "" || $scope.checkout.amount.minimum.value != "") {

            if(!$scope.checkout.amount.maximum.value.match(/^[0-9,.]+$/) || !$scope.checkout.amount.minimum.value.match(/^[0-9,.]+$/)) {

                $scope.checkout.amount.response.value = "Please enter decimal amount";
                $scope.checkout.amount.response.class = "error";
                $scope.checkout.amount.response.view = true;
                $scope.valid.checkoutAmount = false;

            } else {

                $scope.checkout.amount.response.view = false;
                $scope.valid.checkoutAmount = true;

            }

        } else {

            $scope.checkout.amount.response.view = false;
            $scope.valid.checkoutAmount = true;

        }

        $scope.checkout.amount.maximum.value = library.numeral.initializeSeparator($scope.checkout.amount.maximum.value, false);
        $scope.checkout.amount.minimum.value = library.numeral.initializeSeparator($scope.checkout.amount.minimum.value, false);

    }


    $scope.checkCheckoutAverageTime = function() {

        if($scope.checkout.averageTime.value != "") {

            if(!$scope.checkout.averageTime.value.match(/^[0-9,]+$/) || !$scope.checkout.averageTime.value.match(/^[0-9,]+$/)) {

                $scope.checkout.averageTime.response.value = "Please enter number only";
                $scope.checkout.averageTime.response.class = "error";
                $scope.checkout.averageTime.response.view = true;
                $scope.valid.checkoutAverageTime = false;

            } else {

                $scope.checkout.averageTime.response.view = false;
                $scope.valid.checkoutAverageTime = true;

            }

        } else {

            $scope.checkout.averageTime.response.view = false;
            $scope.valid.checkoutAverageTime = true;

        }

        $scope.checkout.averageTime.value = library.numeral.initializeSeparator($scope.checkout.averageTime.value, false);

    }


    $scope.checkCity = function() {

        if($scope.city.value != "") {

            if($scope.city.value.length > 20) {

                $scope.city.response.value = "Please enter maximum 20 characters";
                $scope.city.response.class = "error";
                $scope.city.response.view = true;
                $scope.valid.city = false;

            } else {

                $scope.city.response.view = false;
                $scope.valid.city = true;

            }

        } else {

            $scope.city.response.view = false;
            $scope.valid.city = true;

        }

    }


    $scope.checkContactEmailAddress = function() {

        if($scope.contact.emailAddress.value != "") {

            if(!$scope.contact.emailAddress.value.match(/^([0-9A-Za-z_\-\.]){1,}\@([0-9A-Za-z_\-\.]){1,}\.([A-Za-z]){2,}$/) || $scope.contact.emailAddress.value.length > 50) {

                $scope.contact.emailAddress.response.value = "Please enter valid email address";
                $scope.contact.emailAddress.response.class = "error";
                $scope.contact.emailAddress.response.view = true;
                $scope.valid.contactEmailAddress = false;

            } else {

                $scope.contact.emailAddress.response.view = false;
                $scope.valid.contactEmailAddress = true;

            }

        } else {

            $scope.contact.emailAddress.response.view = false;
            $scope.valid.contactEmailAddress = true;

        }

    }


    $scope.checkContactFaxNumber = function() {

        if($scope.contact.faxNumber.value != "") {

            if($scope.contact.faxNumber.value.startsWith("0")) {

                $scope.contact.faxNumber.response.value = "Please enter without leading zero";
                $scope.contact.faxNumber.response.class = "error";
                $scope.contact.faxNumber.response.view = true;
                $scope.valid.contactFaxNumber = false;

            } else if(!$scope.contact.faxNumber.value.match(/^[0-9]+$/) || $scope.contact.faxNumber.value.length < 9 || $scope.contact.faxNumber.value.length > 14) {

                $scope.contact.faxNumber.response.value = "Please enter valid fax number";
                $scope.contact.faxNumber.response.class = "error";
                $scope.contact.faxNumber.response.view = true;
                $scope.valid.contactFaxNumber = false;

            } else {

                $scope.contact.faxNumber.response.view = false;
                $scope.valid.contactFaxNumber = true;

            }

        } else {

            $scope.contact.faxNumber.response.view = false;
            $scope.valid.contactFaxNumber = true;

        }

    }


    $scope.checkContactLineId = function() {

        if($scope.contact.lineId.value != "") {

            if(/\s/.test($scope.contact.lineId.value) || $scope.contact.lineId.value.length < 3 || $scope.contact.lineId.value.length > 20) {

                $scope.contact.lineId.response.value = "Please enter valid line ID";
                $scope.contact.lineId.response.class = "error";
                $scope.contact.lineId.response.view = true;
                $scope.valid.contactLineId = false;

            } else {

                $scope.contact.lineId.response.view = false;
                $scope.valid.contactLineId = true;

            }

        } else {

            $scope.contact.lineId.response.view = false;
            $scope.valid.contactLineId = true;

        }

    }


    $scope.checkContactPhoneNumber = function() {

        if($scope.contact.phoneNumber.value != "") {

            if(!$scope.contact.phoneNumber.value.match(/^[0-9]+$/) || $scope.contact.phoneNumber.value.length < 9 || $scope.contact.phoneNumber.value.length > 14) {

                $scope.contact.phoneNumber.response.value = "Please enter valid phone number";
                $scope.contact.phoneNumber.response.class = "error";
                $scope.contact.phoneNumber.response.view = true;
                $scope.valid.contactPhoneNumber = false;

            } else {

                $scope.contact.phoneNumber.response.view = false;
                $scope.valid.contactPhoneNumber = true;

            }

        } else {

            $scope.contact.phoneNumber.response.view = false;
            $scope.valid.contactPhoneNumber = true;

        }

    }


    $scope.checkContactWechatId = function() {

        if($scope.contact.wechatId.value != "") {

            if(/\s/.test($scope.contact.wechatId.value) || $scope.contact.wechatId.value.length < 3 || $scope.contact.wechatId.value.length > 20) {

                $scope.contact.wechatId.response.value = "Please enter valid wechat ID";
                $scope.contact.wechatId.response.class = "error";
                $scope.contact.wechatId.response.view = true;
                $scope.valid.contactWechatId = false;

            } else {

                $scope.contact.wechatId.response.view = false;
                $scope.valid.contactWechatId = true;

            }

        } else {

            $scope.contact.wechatId.response.view = false;
            $scope.valid.contactWechatId = true;

        }

    }


    $scope.checkContactWhatsappNumber = function() {

        if($scope.contact.whatsappNumber.value != "") {

            if(!$scope.contact.whatsappNumber.value.match(/^[0-9]+$/) || $scope.contact.whatsappNumber.value.length < 9 || $scope.contact.whatsappNumber.value.length > 14) {

                $scope.contact.whatsappNumber.response.value = "Please enter valid whatsapp number";
                $scope.contact.whatsappNumber.response.class = "error";
                $scope.contact.whatsappNumber.response.view = true;
                $scope.valid.contactWhatsappNumber = false;

            } else {

                $scope.contact.whatsappNumber.response.view = false;
                $scope.valid.contactWhatsappNumber = true;

            }

        } else {

            $scope.contact.whatsappNumber.response.view = false;
            $scope.valid.contactWhatsappNumber = true;

        }

    }


    $scope.checkCountry = function() {

        if($scope.country.selected.value == "Unknown") {

            $scope.country.response.value = "Please select company country";
            $scope.country.response.class = "error";
            $scope.country.response.view = true;
            $scope.valid.country = false;

        } else {

            $scope.country.response.view = false;
            $scope.valid.country = true;

        }

    }


    $scope.checkData = function() {

        $scope.checkAddToCartAmount();

        $scope.checkAddToCartAverageTime();

        $scope.checkAdjustmentAmount();

        $scope.checkAdjustmentAverageTime();

        $scope.checkCheckoutAmount();

        $scope.checkCheckoutAverageTime();

        $scope.checkCity();

        $scope.checkContactEmailAddress();

        $scope.checkContactFaxNumber();

        $scope.checkContactLineId();

        $scope.checkContactPhoneNumber();

        $scope.checkContactWechatId();

        $scope.checkContactWhatsappNumber();

        $scope.checkCountry();

        $scope.checkDepositAmount();

        $scope.checkDepositAverageTime();

        $scope.checkExpenseAmount();

        $scope.checkExpenseAverageTime();

        $scope.checkFeeAmount();

        $scope.checkFeeAverageTime();

        $scope.checkInternalAmount();

        $scope.checkInternalAverageTime();

        $scope.checkName();

        $scope.checkRegistrationNumber();

        $scope.checkState();

        $scope.checkStatus();

        $scope.checkTransferAmount();

        $scope.checkTransferAverageTime();

        $scope.checkWithdrawalAmount();

        $scope.checkWithdrawalAverageTime();

        $scope.checkZipCode();

    }


    $scope.checkDepositAmount = function() {

        if($scope.deposit.amount.maximum.value != "" || $scope.deposit.amount.minimum.value != "") {

            if(!$scope.deposit.amount.maximum.value.match(/^[0-9,.]+$/) || !$scope.deposit.amount.minimum.value.match(/^[0-9,.]+$/)) {

                $scope.deposit.amount.response.value = "Please enter decimal amount";
                $scope.deposit.amount.response.class = "error";
                $scope.deposit.amount.response.view = true;
                $scope.valid.depositAmount = false;

            } else {

                $scope.deposit.amount.response.view = false;
                $scope.valid.depositAmount = true;

            }

        } else {

            $scope.deposit.amount.response.view = false;
            $scope.valid.depositAmount = true;

        }

        $scope.deposit.amount.maximum.value = library.numeral.initializeSeparator($scope.deposit.amount.maximum.value, false);
        $scope.deposit.amount.minimum.value = library.numeral.initializeSeparator($scope.deposit.amount.minimum.value, false);

    }


    $scope.checkDepositAverageTime = function() {

        if($scope.deposit.averageTime.value != "") {

            if(!$scope.deposit.averageTime.value.match(/^[0-9,]+$/) || !$scope.deposit.averageTime.value.match(/^[0-9,]+$/)) {

                $scope.deposit.averageTime.response.value = "Please enter number only";
                $scope.deposit.averageTime.response.class = "error";
                $scope.deposit.averageTime.response.view = true;
                $scope.valid.depositAverageTime = false;

            } else {

                $scope.deposit.averageTime.response.view = false;
                $scope.valid.depositAverageTime = true;

            }

        } else {

            $scope.deposit.averageTime.response.view = false;
            $scope.valid.depositAverageTime = true;

        }

        $scope.deposit.averageTime.value = library.numeral.initializeSeparator($scope.deposit.averageTime.value, false);

    }


    $scope.checkExpenseAmount = function() {

        if($scope.expense.amount.maximum.value != "" || $scope.expense.amount.minimum.value != "") {

            if(!$scope.expense.amount.maximum.value.match(/^[0-9,.]+$/) || !$scope.expense.amount.minimum.value.match(/^[0-9,.]+$/)) {

                $scope.expense.amount.response.value = "Please enter decimal amount";
                $scope.expense.amount.response.class = "error";
                $scope.expense.amount.response.view = true;
                $scope.valid.expenseAmount = false;

            } else {

                $scope.expense.amount.response.view = false;
                $scope.valid.expenseAmount = true;

            }

        } else {

            $scope.expense.amount.response.view = false;
            $scope.valid.expenseAmount = true;

        }

        $scope.expense.amount.maximum.value = library.numeral.initializeSeparator($scope.expense.amount.maximum.value, false);
        $scope.expense.amount.minimum.value = library.numeral.initializeSeparator($scope.expense.amount.minimum.value, false);

    }


    $scope.checkExpenseAverageTime = function() {

        if($scope.expense.averageTime.value != "") {

            if(!$scope.expense.averageTime.value.match(/^[0-9,]+$/) || !$scope.expense.averageTime.value.match(/^[0-9,]+$/)) {

                $scope.expense.averageTime.response.value = "Please enter number only";
                $scope.expense.averageTime.response.class = "error";
                $scope.expense.averageTime.response.view = true;
                $scope.valid.expenseAverageTime = false;

            } else {

                $scope.expense.averageTime.response.view = false;
                $scope.valid.expenseAverageTime = true;

            }

        } else {

            $scope.expense.averageTime.response.view = false;
            $scope.valid.expenseAverageTime = true;

        }

        $scope.expense.averageTime.value = library.numeral.initializeSeparator($scope.expense.averageTime.value, false);

    }


    $scope.checkFeeAmount = function() {

        if($scope.fee.amount.maximum.value != "" || $scope.fee.amount.minimum.value != "") {

            if(!$scope.fee.amount.maximum.value.match(/^[0-9,.]+$/) || !$scope.fee.amount.minimum.value.match(/^[0-9,.]+$/)) {

                $scope.fee.amount.response.value = "Please enter decimal amount";
                $scope.fee.amount.response.class = "error";
                $scope.fee.amount.response.view = true;
                $scope.valid.feeAmount = false;

            } else {

                $scope.fee.amount.response.view = false;
                $scope.valid.feeAmount = true;

            }

        } else {

            $scope.fee.amount.response.view = false;
            $scope.valid.feeAmount = true;

        }

        $scope.fee.amount.maximum.value = library.numeral.initializeSeparator($scope.fee.amount.maximum.value, false);
        $scope.fee.amount.minimum.value = library.numeral.initializeSeparator($scope.fee.amount.minimum.value, false);

    }


    $scope.checkFeeAverageTime = function() {

        if($scope.fee.averageTime.value != "") {

            if(!$scope.fee.averageTime.value.match(/^[0-9,]+$/) || !$scope.fee.averageTime.value.match(/^[0-9,]+$/)) {

                $scope.fee.averageTime.response.value = "Please enter number only";
                $scope.fee.averageTime.response.class = "error";
                $scope.fee.averageTime.response.view = true;
                $scope.valid.feeAverageTime = false;

            } else {

                $scope.fee.averageTime.response.view = false;
                $scope.valid.feeAverageTime = true;

            }

        } else {

            $scope.fee.averageTime.response.view = false;
            $scope.valid.feeAverageTime = true;

        }

        $scope.fee.averageTime.value = library.numeral.initializeSeparator($scope.fee.averageTime.value, false);

    }


    $scope.checkInternalAmount = function() {

        if($scope.internal.amount.maximum.value != "" || $scope.internal.amount.minimum.value != "") {

            if(!$scope.internal.amount.maximum.value.match(/^[0-9,.]+$/) || !$scope.internal.amount.minimum.value.match(/^[0-9,.]+$/)) {

                $scope.internal.amount.response.value = "Please enter decimal amount";
                $scope.internal.amount.response.class = "error";
                $scope.internal.amount.response.view = true;
                $scope.valid.internalAmount = false;

            } else {

                $scope.internal.amount.response.view = false;
                $scope.valid.internalAmount = true;

            }

        } else {

            $scope.internal.amount.response.view = false;
            $scope.valid.internalAmount = true;

        }

        $scope.internal.amount.maximum.value = library.numeral.initializeSeparator($scope.internal.amount.maximum.value);
        $scope.internal.amount.minimum.value = library.numeral.initializeSeparator($scope.internal.amount.minimum.value);

    }


    $scope.checkInternalAverageTime = function() {

        if($scope.internal.averageTime.value != "") {

            if(!$scope.internal.averageTime.value.match(/^[0-9,]+$/) || !$scope.internal.averageTime.value.match(/^[0-9,]+$/)) {

                $scope.internal.averageTime.response.value = "Please enter number only";
                $scope.internal.averageTime.response.class = "error";
                $scope.internal.averageTime.response.view = true;
                $scope.valid.internalAverageTime = false;

            } else {

                $scope.internal.averageTime.response.view = false;
                $scope.valid.internalAverageTime = true;

            }

        } else {

            $scope.internal.averageTime.response.view = false;
            $scope.valid.internalAverageTime = true;

        }

        $scope.internal.averageTime.value = library.numeral.initializeSeparator($scope.internal.averageTime.value);

    }


    $scope.checkName = function() {

        if($scope.name.value.length < 2) {

            $scope.name.response.value = "Please enter more than 2 characters";
            $scope.name.response.class = "error";
            $scope.name.response.view = true;
            $scope.valid.name = false;

        } else {

            $scope.name.response.view = false;
            $scope.valid.name = true;

        }

    }


    $scope.checkRegistrationNumber = function() {

        if($scope.registrationNumber.value != "") {

            if($scope.registrationNumber.value.length < 2) {

                $scope.registrationNumber.response.value = "Please enter more than 2 characters";
                $scope.registrationNumber.response.class = "error";
                $scope.registrationNumber.response.view = true;
                $scope.valid.registrationNumber = false;

            } else {

                $scope.registrationNumber.response.view = false;
                $scope.valid.registrationNumber = true;

            }

        } else {

            $scope.registrationNumber.response.view = false;
            $scope.valid.registrationNumber = true;

        }

    }


    $scope.checkState = function() {

        if($scope.state.value != "") {

            if($scope.state.value.length < 3 && $scope.state.value.length > 20) {

                $scope.state.response.value = "Please enter between 3 - 20 characters";
                $scope.state.response.class = "error";
                $scope.state.response.view = true;
                $scope.valid.state = false;

            } else {

                $scope.state.response.view = false;
                $scope.valid.state = true;

            }

        } else {

            $scope.state.response.view = false;
            $scope.valid.state = true;

        }

    }


    $scope.checkTransferAmount = function() {

        if($scope.transfer.amount.maximum.value != "" || $scope.transfer.amount.minimum.value != "") {

            if(!$scope.transfer.amount.maximum.value.match(/^[0-9,.]+$/) || !$scope.transfer.amount.minimum.value.match(/^[0-9,.]+$/)) {

                $scope.transfer.amount.response.value = "Please enter decimal amount";
                $scope.transfer.amount.response.class = "error";
                $scope.transfer.amount.response.view = true;
                $scope.valid.transferAmount = false;

            } else {

                $scope.transfer.amount.response.view = false;
                $scope.valid.transferAmount = true;

            }

        } else {

            $scope.transfer.amount.response.view = false;
            $scope.valid.transferAmount = true;

        }

        $scope.transfer.amount.maximum.value = library.numeral.initializeSeparator($scope.transfer.amount.maximum.value);
        $scope.transfer.amount.minimum.value = library.numeral.initializeSeparator($scope.transfer.amount.minimum.value);

    }


    $scope.checkTransferAverageTime = function() {

        if($scope.transfer.averageTime.value != "") {

            if(!$scope.transfer.averageTime.value.match(/^[0-9,]+$/) || !$scope.transfer.averageTime.value.match(/^[0-9,]+$/)) {

                $scope.transfer.averageTime.response.value = "Please enter number only";
                $scope.transfer.averageTime.response.class = "error";
                $scope.transfer.averageTime.response.view = true;
                $scope.valid.transferAverageTime = false;

            } else {

                $scope.transfer.averageTime.response.view = false;
                $scope.valid.transferAverageTime = true;

            }

        } else {

            $scope.transfer.averageTime.response.view = false;
            $scope.valid.transferAverageTime = true;

        }

        $scope.transfer.averageTime.value = library.numeral.initializeSeparator($scope.transfer.averageTime.value);

    }


    $scope.checkWithdrawalAmount = function() {

        if($scope.withdrawal.amount.maximum.value != "" || $scope.withdrawal.amount.minimum.value != "") {

            if(!$scope.withdrawal.amount.maximum.value.match(/^[0-9,.]+$/) || !$scope.withdrawal.amount.minimum.value.match(/^[0-9,.]+$/)) {

                $scope.withdrawal.amount.response.value = "Please enter decimal amount";
                $scope.withdrawal.amount.response.class = "error";
                $scope.withdrawal.amount.response.view = true;
                $scope.valid.withdrawalAmount = false;

            } else {

                $scope.withdrawal.amount.response.view = false;
                $scope.valid.withdrawalAmount = true;

            }

        } else {

            $scope.withdrawal.amount.response.view = false;
            $scope.valid.withdrawalAmount = true;

        }

        $scope.withdrawal.amount.maximum.value = library.numeral.initializeSeparator($scope.withdrawal.amount.maximum.value);
        $scope.withdrawal.amount.minimum.value = library.numeral.initializeSeparator($scope.withdrawal.amount.minimum.value);

    }


    $scope.checkWithdrawalAverageTime = function() {

        if($scope.withdrawal.averageTime.value != "") {

            if(!$scope.withdrawal.averageTime.value.match(/^[0-9,]+$/) || !$scope.withdrawal.averageTime.value.match(/^[0-9,]+$/)) {

                $scope.withdrawal.averageTime.response.value = "Please enter number only";
                $scope.withdrawal.averageTime.response.class = "error";
                $scope.withdrawal.averageTime.response.view = true;
                $scope.valid.withdrawalAverageTime = false;

            } else {

                $scope.withdrawal.averageTime.response.view = false;
                $scope.valid.withdrawalAverageTime = true;

            }

        } else {

            $scope.withdrawal.averageTime.response.view = false;
            $scope.valid.withdrawalAverageTime = true;

        }

        $scope.withdrawal.averageTime.value = library.numeral.initializeSeparator($scope.withdrawal.averageTime.value);

    }


    $scope.checkZipCode = function() {

        if($scope.zipCode.value != "") {

            if(!$scope.zipCode.value.match(/^[0-9-]+$/) || $scope.zipCode.value.length > 7) {

                $scope.zipCode.response.value = "Please enter valid zip code";
                $scope.zipCode.response.class = "error";
                $scope.zipCode.response.view = true;
                $scope.valid.zipCode = false;

            } else {

                $scope.zipCode.response.view = false;
                $scope.valid.zipCode = true;

            }

        } else {

            $scope.zipCode.response.view = false;
            $scope.valid.zipCode = true;

        }

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
                    "activation": {
                        "email": $scope.activation.email.value,
                        "sms": $scope.activation.sms.value
                    },
                    "addToCart": {
                        "averageTime": input.addToCart.averageTime,
                        "maximum": input.addToCart.amount.maximum,
                        "minimum": input.addToCart.amount.minimum
                    },
                    "adjustment": {
                        "averageTime": input.adjustment.averageTime,
                        "maximum": input.adjustment.amount.maximum,
                        "minimum": input.adjustment.amount.minimum
                    },
                    "checkout": {
                        "averageTime": input.checkout.averageTime,
                        "maximum": input.checkout.amount.maximum,
                        "minimum": input.checkout.amount.minimum
                    },
                    "city": $scope.city.value,
                    "contact": {
                        "emailAddress": $scope.contact.emailAddress.value,
                        "faxNumber": input.contact.faxNumber,
                        "lineId": $scope.contact.lineId.value,
                        "phoneNumber": input.contact.phoneNumber,
                        "wechatId": $scope.contact.wechatId.value,
                        "whatsappNumber": input.contact.whatsappNumber
                    },
                    "country": $scope.country.selected.value,
                    "deposit": {
                        "averageTime": input.deposit.averageTime,
                        "maximum": input.deposit.amount.maximum,
                        "minimum": input.deposit.amount.minimum
                    },
                    "description": $scope.description.value,
                    "expense": {
                        "averageTime": input.expense.averageTime,
                        "maximum": input.expense.amount.maximum,
                        "minimum": input.expense.amount.minimum
                    },
                    "favicon": $scope.favicon.value,
                    "fee": {
                        "averageTime": input.fee.averageTime,
                        "maximum": input.fee.amount.maximum,
                        "minimum": input.fee.amount.minimum
                    },
                    "internal": {
                        "averageTime": input.internal.averageTime,
                        "maximum": input.internal.amount.maximum,
                        "minimum": input.internal.amount.minimum
                    },
                    "like": {
                        "blog": $scope.like.blog.value,
                        "shop": $scope.like.shop.value
                    },
                    "logo": $scope.logo.value,
                    "maintenance": {
                        "finish": input.maintenance.finish,
                        "next": input.maintenance.next
                    },
                    "membership": {
                        "password": $scope.membership.password.value
                    },
                    "meta": {
                        "description": $scope.meta.description.value,
                        "keyword": $scope.meta.keyword.value,
                        "title": $scope.meta.title.value
                    },
                    "name": $scope.name.value,
                    "og": {
                        "description": $scope.og.description.value,
                        "title": $scope.og.title.value
                    },
                    "rating": {
                        "blog": $scope.rating.blog.value,
                        "shop": $scope.rating.shop.value
                    },
                    "registrationNumber": $scope.registrationNumber.value,
                    "review": {
                        "blog": $scope.review.blog.value,
                        "shop": $scope.review.shop.value
                    },
                    "state": $scope.state.value,
                    "status": $scope.status.selected.value,
                    "streetAddress": $scope.streetAddress.value,
                    "transfer": {
                        "averageTime": input.transfer.averageTime,
                        "maximum": input.transfer.amount.maximum,
                        "minimum": input.transfer.amount.minimum
                    },
                    "withdrawal": {
                        "averageTime": input.withdrawal.averageTime,
                        "maximum": input.withdrawal.amount.maximum,
                        "minimum": input.withdrawal.amount.minimum
                    },
                    "zipCode": $scope.zipCode.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/setting/update"
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


    $scope.forceUploadFavicon = function() {

        document.getElementsByClassName("setting-favicon")[0].click();

    }


    $scope.forceUploadLogo = function() {

        document.getElementsByClassName("setting-logo")[0].click();

    }


    $scope.initializeData = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/setting/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.setting.id != null) {

                    $scope.activation.email.value = response.setting.activation.email;
                    $scope.activation.sms.value = response.setting.activation.sms;
                    $scope.addToCart.amount.maximum.value = library.numeral.initializeSeparator(response.setting.addToCart.maximum, true);
                    $scope.addToCart.amount.minimum.value = library.numeral.initializeSeparator(response.setting.addToCart.minimum, true);
                    $scope.addToCart.averageTime.value = library.numeral.initializeSeparator(response.setting.addToCart.averageTime, true);
                    $scope.adjustment.amount.maximum.value = library.numeral.initializeSeparator(response.setting.adjustment.maximum, true);
                    $scope.adjustment.amount.minimum.value = library.numeral.initializeSeparator(response.setting.adjustment.minimum, true);
                    $scope.adjustment.averageTime.value = library.numeral.initializeSeparator(response.setting.adjustment.averageTime, true);
                    $scope.checkout.amount.maximum.value = library.numeral.initializeSeparator(response.setting.checkout.maximum, true);
                    $scope.checkout.amount.minimum.value = library.numeral.initializeSeparator(response.setting.checkout.minimum, true);
                    $scope.checkout.averageTime.value = library.numeral.initializeSeparator(response.setting.checkout.averageTime, true);
                    $scope.city.value = response.setting.city;
                    $scope.contact.emailAddress.value = response.setting.contact.emailAddress;
                    $scope.contact.lineId.value = response.setting.contact.lineId;
                    $scope.contact.wechatId.value = response.setting.contact.wechatId;

                    var createdTimestamp = new Date(response.setting.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.setting.created.user.id;
                    $scope.created.user.username.value = response.setting.created.user.username;
                    $scope.deposit.amount.maximum.value = library.numeral.initializeSeparator(response.setting.deposit.maximum, true);
                    $scope.deposit.amount.minimum.value = library.numeral.initializeSeparator(response.setting.deposit.minimum, true);
                    $scope.deposit.averageTime.value = library.numeral.initializeSeparator(response.setting.deposit.averageTime, true);
                    $scope.description.value = response.setting.description;
                    $scope.expense.amount.maximum.value = library.numeral.initializeSeparator(response.setting.expense.maximum, true);
                    $scope.expense.amount.minimum.value = library.numeral.initializeSeparator(response.setting.expense.minimum, true);
                    $scope.expense.averageTime.value = library.numeral.initializeSeparator(response.setting.expense.averageTime, true);

                    if(response.setting.favicon != "") {

                        $scope.favicon.value = response.setting.favicon;
                        $scope.favicon.upload.file = response.setting.favicon;
                        $scope.favicon.upload.view = true;

                    }

                    $scope.fee.amount.maximum.value = library.numeral.initializeSeparator(response.setting.fee.maximum, true);
                    $scope.fee.amount.minimum.value = library.numeral.initializeSeparator(response.setting.fee.minimum, true);
                    $scope.fee.averageTime.value = library.numeral.initializeSeparator(response.setting.fee.averageTime, true);
                    $scope.id.value = response.setting.id;
                    $scope.internal.amount.maximum.value = library.numeral.initializeSeparator(response.setting.internal.maximum, true);
                    $scope.internal.amount.minimum.value = library.numeral.initializeSeparator(response.setting.internal.minimum, true);
                    $scope.internal.averageTime.value = library.numeral.initializeSeparator(response.setting.internal.averageTime, true);
                    $scope.like.blog.value = response.setting.like.blog;
                    $scope.like.shop.value = response.setting.like.shop;

                    if(response.setting.logo != "") {

                        $scope.logo.value = response.setting.logo;
                        $scope.logo.upload.file = response.setting.logo;
                        $scope.logo.upload.view = true;

                    }

                    var maintenanceFinish = new Date(response.setting.maintenance.finish);
                    $scope.maintenance.finish.value = maintenanceFinish.getFullYear() + "-" + ("0" + (maintenanceFinish.getMonth() + 1)).slice(-2) + "-" + ("0" + maintenanceFinish.getDate()).slice(-2) + " " + ("0" + maintenanceFinish.getHours()).slice(-2) + ":" + ("0" + maintenanceFinish.getMinutes()).slice(-2) + ":" + ("0" + maintenanceFinish.getSeconds()).slice(-2);

                    var maintenanceNext = new Date(response.setting.maintenance.next);
                    $scope.maintenance.next.value = maintenanceNext.getFullYear() + "-" + ("0" + (maintenanceNext.getMonth() + 1)).slice(-2) + "-" + ("0" + maintenanceNext.getDate()).slice(-2) + " " + ("0" + maintenanceNext.getHours()).slice(-2) + ":" + ("0" + maintenanceNext.getMinutes()).slice(-2) + ":" + ("0" + maintenanceNext.getSeconds()).slice(-2);

                    $scope.membership.password.value = response.setting.membership.password;
                    $scope.meta.description.value = response.setting.meta.description;
                    $scope.meta.keyword.value = response.setting.meta.keyword;
                    $scope.meta.title.value = response.setting.meta.title;

                    var modifiedTimestamp = new Date(response.setting.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.setting.modified.user.id;
                    $scope.modified.user.username.value = response.setting.modified.user.username;
                    $scope.name.value = response.setting.name;
                    $scope.og.description.value = response.setting.og.description;
                    $scope.og.title.value = response.setting.og.title;
                    $scope.rating.blog.value = response.setting.rating.blog;
                    $scope.rating.shop.value = response.setting.rating.shop;
                    $scope.registrationNumber.value = response.setting.registrationNumber;
                    $scope.review.blog.value = response.setting.review.blog;
                    $scope.review.shop.value = response.setting.review.shop;
                    $scope.state.value = response.setting.state;
                    $scope.streetAddress.value = response.setting.streetAddress;
                    $scope.transfer.amount.maximum.value = library.numeral.initializeSeparator(response.setting.transfer.maximum, true);
                    $scope.transfer.amount.minimum.value = library.numeral.initializeSeparator(response.setting.transfer.minimum, true);
                    $scope.transfer.averageTime.value = library.numeral.initializeSeparator(response.setting.transfer.averageTime, true);
                    $scope.withdrawal.amount.maximum.value = library.numeral.initializeSeparator(response.setting.withdrawal.maximum, true);
                    $scope.withdrawal.amount.minimum.value = library.numeral.initializeSeparator(response.setting.withdrawal.minimum, true);
                    $scope.withdrawal.averageTime.value = library.numeral.initializeSeparator(response.setting.withdrawal.averageTime, true);
                    $scope.zipCode.value = response.setting.zipCode;

                }

                angular.forEach(response.applicationStatusList, function(value, key) {

                    $scope.status.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.setting.id != null) {

                        if(value == response.setting.status) {

                            $scope.status.selected = $scope.status.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.countryCodeList, function(value, key) {

                    $scope.contact.faxNumber.countryCode.option.push({
                        "number": value,
                        "value": value
                    });

                    $scope.contact.phoneNumber.countryCode.option.push({
                        "number": value,
                        "value": value
                    });

                    $scope.contact.whatsappNumber.countryCode.option.push({
                        "number": value,
                        "value": value
                    });

                    if(response.setting.id != null) {

                        if(response.setting.contact.faxNumber.startsWith(value)) {

                            $scope.contact.faxNumber.countryCode.selected = $scope.contact.faxNumber.countryCode.option[key];
                            $scope.contact.faxNumber.value = response.setting.contact.faxNumber.replace(value, "");

                        }

                        if(response.setting.contact.phoneNumber.startsWith(value)) {

                            $scope.contact.phoneNumber.countryCode.selected = $scope.contact.phoneNumber.countryCode.option[key];
                            $scope.contact.phoneNumber.value = response.setting.contact.phoneNumber.replace(value, "");

                        }

                        if(response.setting.contact.whatsappNumber.startsWith(value)) {

                            $scope.contact.whatsappNumber.countryCode.selected = $scope.contact.whatsappNumber.countryCode.option[key];
                            $scope.contact.whatsappNumber.value = response.setting.contact.whatsappNumber.replace(value, "");

                        }

                    } else {

                        $scope.contact.faxNumber.countryCode.selected = $scope.contact.faxNumber.countryCode.option[0];

                        $scope.contact.phoneNumber.countryCode.selected = $scope.contact.phoneNumber.countryCode.option[0];

                        $scope.contact.whatsappNumber.countryCode.selected = $scope.contact.whatsappNumber.countryCode.option[0];

                    }

                });

                angular.forEach(response.countryList, function(value, key) {

                    $scope.country.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.setting.id != null) {

                        if(value == response.setting.country) {

                            $scope.country.selected = $scope.country.option[(key + 1)];

                        }

                    }

                });

                $scope.initializeSwitch();

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
            "addToCart": {
                "amount": {
                    "maximum": 0,
                    "minimum": 0
                },
                "averageTime": 0
            },
            "adjustment": {
                "amount": {
                    "maximum": 0,
                    "minimum": 0
                },
                "averageTime": 0
            },
            "checkout": {
                "amount": {
                    "maximum": 0,
                    "minimum": 0
                },
                "averageTime": 0
            },
            "contact": {
                "faxNumber": "",
                "phoneNumber": "",
                "whatsappNumber": ""
            },
            "deposit": {
                "amount": {
                    "maximum": 0,
                    "minimum": 0
                },
                "averageTime": 0
            },
            "expense": {
                "amount": {
                    "maximum": 0,
                    "minimum": 0
                },
                "averageTime": 0
            },
            "fee": {
                "amount": {
                    "maximum": 0,
                    "minimum": 0
                },
                "averageTime": 0
            },
            "internal": {
                "amount": {
                    "maximum": 0,
                    "minimum": 0
                },
                "averageTime": 0
            },
            "maintenance": {
                "finish": "",
                "next": ""
            },
            "sequence": 0,
            "transfer": {
                "amount": {
                    "maximum": 0,
                    "minimum": 0
                },
                "averageTime": 0
            },
            "withdrawal": {
                "amount": {
                    "maximum": 0,
                    "minimum": 0
                },
                "averageTime": 0
            }
        };

        $scope.addToCart.averageTime.value = library.numeral.initializeSeparator($scope.addToCart.averageTime.value, true);
        $scope.addToCart.amount.maximum.value = library.numeral.initializeSeparator($scope.addToCart.amount.maximum.value, true);
        $scope.addToCart.amount.minimum.value = library.numeral.initializeSeparator($scope.addToCart.amount.minimum.value, true);
        $scope.adjustment.averageTime.value = library.numeral.initializeSeparator($scope.adjustment.averageTime.value, true);
        $scope.adjustment.amount.maximum.value = library.numeral.initializeSeparator($scope.adjustment.amount.maximum.value, true);
        $scope.adjustment.amount.minimum.value = library.numeral.initializeSeparator($scope.adjustment.amount.minimum.value, true);
        $scope.checkout.averageTime.value = library.numeral.initializeSeparator($scope.checkout.averageTime.value, true);
        $scope.checkout.amount.maximum.value = library.numeral.initializeSeparator($scope.checkout.amount.maximum.value, true);
        $scope.checkout.amount.minimum.value = library.numeral.initializeSeparator($scope.checkout.amount.minimum.value, true);
        $scope.deposit.averageTime.value = library.numeral.initializeSeparator($scope.deposit.averageTime.value, true);
        $scope.deposit.amount.maximum.value = library.numeral.initializeSeparator($scope.deposit.amount.maximum.value, true);
        $scope.deposit.amount.minimum.value = library.numeral.initializeSeparator($scope.deposit.amount.minimum.value, true);
        $scope.expense.averageTime.value = library.numeral.initializeSeparator($scope.expense.averageTime.value, true);
        $scope.expense.amount.maximum.value = library.numeral.initializeSeparator($scope.expense.amount.maximum.value, true);
        $scope.expense.amount.minimum.value = library.numeral.initializeSeparator($scope.expense.amount.minimum.value, true);
        $scope.fee.averageTime.value = library.numeral.initializeSeparator($scope.fee.averageTime.value, true);
        $scope.fee.amount.maximum.value = library.numeral.initializeSeparator($scope.fee.amount.maximum.value, true);
        $scope.fee.amount.minimum.value = library.numeral.initializeSeparator($scope.fee.amount.minimum.value, true);
        $scope.internal.averageTime.value = library.numeral.initializeSeparator($scope.internal.averageTime.value, true);
        $scope.internal.amount.maximum.value = library.numeral.initializeSeparator($scope.internal.amount.maximum.value, true);
        $scope.internal.amount.minimum.value = library.numeral.initializeSeparator($scope.internal.amount.minimum.value, true);
        $scope.transfer.averageTime.value = library.numeral.initializeSeparator($scope.transfer.averageTime.value, true);
        $scope.transfer.amount.maximum.value = library.numeral.initializeSeparator($scope.transfer.amount.maximum.value, true);
        $scope.transfer.amount.minimum.value = library.numeral.initializeSeparator($scope.transfer.amount.minimum.value, true);
        $scope.withdrawal.averageTime.value = library.numeral.initializeSeparator($scope.withdrawal.averageTime.value, true);
        $scope.withdrawal.amount.maximum.value = library.numeral.initializeSeparator($scope.withdrawal.amount.maximum.value, true);
        $scope.withdrawal.amount.minimum.value = library.numeral.initializeSeparator($scope.withdrawal.amount.minimum.value, true);

        result.addToCart.averageTime = $scope.addToCart.averageTime.value.replace(/[^0-9.]/g, "");
        result.addToCart.amount.maximum = $scope.addToCart.amount.maximum.value.replace(/[^0-9.]/g, "");
        result.addToCart.amount.minimum = $scope.addToCart.amount.minimum.value.replace(/[^0-9.]/g, "");
        result.adjustment.averageTime = $scope.adjustment.averageTime.value.replace(/[^0-9.]/g, "");
        result.adjustment.amount.maximum = $scope.adjustment.amount.maximum.value.replace(/[^0-9.]/g, "");
        result.adjustment.amount.minimum = $scope.adjustment.amount.minimum.value.replace(/[^0-9.]/g, "");
        result.checkout.averageTime = $scope.checkout.averageTime.value.replace(/[^0-9.]/g, "");
        result.checkout.amount.maximum = $scope.checkout.amount.maximum.value.replace(/[^0-9.]/g, "");
        result.checkout.amount.minimum = $scope.checkout.amount.minimum.value.replace(/[^0-9.]/g, "");
        result.deposit.averageTime = $scope.deposit.averageTime.value.replace(/[^0-9.]/g, "");
        result.deposit.amount.maximum = $scope.deposit.amount.maximum.value.replace(/[^0-9.]/g, "");
        result.deposit.amount.minimum = $scope.deposit.amount.minimum.value.replace(/[^0-9.]/g, "");
        result.expense.averageTime = $scope.expense.averageTime.value.replace(/[^0-9.]/g, "");
        result.expense.amount.maximum = $scope.expense.amount.maximum.value.replace(/[^0-9.]/g, "");
        result.expense.amount.minimum = $scope.expense.amount.minimum.value.replace(/[^0-9.]/g, "");
        result.fee.averageTime = $scope.fee.averageTime.value.replace(/[^0-9.]/g, "");
        result.fee.amount.maximum = $scope.fee.amount.maximum.value.replace(/[^0-9.]/g, "");
        result.fee.amount.minimum = $scope.fee.amount.minimum.value.replace(/[^0-9.]/g, "");
        result.internal.averageTime = $scope.internal.averageTime.value.replace(/[^0-9.]/g, "");
        result.internal.amount.maximum = $scope.internal.amount.maximum.value.replace(/[^0-9.]/g, "");
        result.internal.amount.minimum = $scope.internal.amount.minimum.value.replace(/[^0-9.]/g, "");
        result.transfer.averageTime = $scope.transfer.averageTime.value.replace(/[^0-9.]/g, "");
        result.transfer.amount.maximum = $scope.transfer.amount.maximum.value.replace(/[^0-9.]/g, "");
        result.transfer.amount.minimum = $scope.transfer.amount.minimum.value.replace(/[^0-9.]/g, "");
        result.withdrawal.averageTime = $scope.withdrawal.averageTime.value.replace(/[^0-9.]/g, "");
        result.withdrawal.amount.maximum = $scope.withdrawal.amount.maximum.value.replace(/[^0-9.]/g, "");
        result.withdrawal.amount.minimum = $scope.withdrawal.amount.minimum.value.replace(/[^0-9.]/g, "");

        if($scope.maintenance.finish.value != "") {

            result.maintenance.finish = $scope.maintenance.finish.value.replace(" ", "T") + ".000" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.maintenance.next.value != "") {

            result.maintenance.next = $scope.maintenance.next.value.replace(" ", "T") + ".000" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.contact.faxNumber.value != "") {

            result.contact.faxNumber = $scope.contact.faxNumber.countryCode.selected.value + $scope.contact.faxNumber.value;

        }

        if($scope.contact.phoneNumber.value != "") {

            result.contact.phoneNumber = $scope.contact.phoneNumber.countryCode.selected.value + $scope.contact.phoneNumber.value;

        }

        if($scope.contact.whatsappNumber.value != "") {

            result.contact.whatsappNumber = $scope.contact.whatsappNumber.countryCode.selected.value + $scope.contact.whatsappNumber.value;

        }

        return result;

    }


    $scope.initializeSwitch = function() {

        if($scope.activation.email.value) {

            $scope.activation.email.name = "Enabled";

        } else {

            $scope.activation.email.name = "Disabled";

        }

        if($scope.activation.sms.value) {

            $scope.activation.sms.name = "Enabled";

        } else {

            $scope.activation.sms.name = "Disabled";

        }

        if($scope.membership.password.value) {

            $scope.membership.password.name = "Enabled";

        } else {

            $scope.membership.password.name = "Disabled";

        }

    }


    $scope.uploadFavicon = function() {

        $scope.loading.view = true;

        var formData = new FormData();

        angular.forEach($scope.files, function(value) {

            formData.append("file", value);

        });

        var rest = {
            "data": formData,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/setting/upload-image"
        };
        global.restMultipart(rest, function(response) {

            if(response.result) {

                $scope.response.class = "success";

                $scope.favicon.value = response.imageList[0];
                $scope.favicon.upload.file = response.imageList[0];
                $scope.favicon.upload.view = true;

            } else {

                $scope.response.class = "error";

            }

            $scope.loading.view = false;

            $scope.response.message = response.response;
            $scope.response.view = true;

            $scope.hideResponse();

        });

    }


    $scope.uploadLogo = function() {

        $scope.loading.view = true;

        var formData = new FormData();

        angular.forEach($scope.files, function(value) {

            formData.append("file", value);

        });

        var rest = {
            "data": formData,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/setting/upload-image"
        };
        global.restMultipart(rest, function(response) {

            if(response.result) {

                $scope.response.class = "success";

                $scope.logo.value = response.imageList[0];
                $scope.logo.upload.file = response.imageList[0];
                $scope.logo.upload.view = true;

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


app.directive("settingFaviconPreview", function() {


    var setting = {};


    setting.template = "<span ng-if=\"favicon.upload.file != ''\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/setting/{{favicon.upload.file}}\" />" +
        "</span>";


    return setting;


});


app.directive("settingLogoPreview", function() {


    var setting = {};


    setting.template = "<span ng-if=\"logo.upload.file != ''\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/setting/{{logo.upload.file}}\" />" +
        "</span>";


    return setting;


});
