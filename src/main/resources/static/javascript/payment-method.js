app.controller("paymentMethod", ["$scope", "$window", "global", function($scope, $window, global) {


    $scope.businessHour = {
        "friday": {
            "end": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "start": {
                "value": ""
            }
        },
        "monday": {
            "end": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "start": {
                "value": ""
            }
        },
        "saturday": {
            "end": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "start": {
                "value": ""
            }
        },
        "sunday": {
            "end": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "start": {
                "value": ""
            }
        },
        "thursday": {
            "end": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "start": {
                "value": ""
            }
        },
        "tuesday": {
            "end": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "start": {
                "value": ""
            }
        },
        "wednesday": {
            "end": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "start": {
                "value": ""
            }
        }
    };

    $scope.fee = {
        "provider": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "providerFix": {
            "name": "",
            "value": false
        },
        "service": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "serviceFix": {
            "name": "",
            "value": false
        }
    };

    $scope.filter = {
        "createdDate": {
            "value": ""
        },
        "id": {
            "value": ""
        },
        "name": {
            "value": ""
        },
        "status": {
            "option": [
                {"name": "Status", "value": "Unknown"}
            ]
        },
        "type": {
            "option": [
                {"name": "Type", "value": "Unknown"}
            ]
        }
    };

    $scope.filter.status.selected = $scope.filter.status.option[0];

    $scope.footer = {
        "display": {
            "name": "",
            "value": false
        },
        "logo": {
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

    $scope.payment = {
        "display": {
            "name": "",
            "value": false
        },
        "logo": {
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
        }
    };

    $scope.sequence = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.sidebar = {
        "display": {
            "name": "",
            "value": false
        },
        "logo": {
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
        }
    };

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
        "businessHourFriday": false,
        "businessHourMonday": false,
        "businessHourSaturday": false,
        "businessHourSunday": false,
        "businessHourThursday": false,
        "businessHourTuesday": false,
        "businessHourWednesday": false,
        "feeProvider": false,
        "feeService": false,
        "name": false,
        "sequence": false,
        "type": false
    });


    $scope.checkBusinessHourFriday = function() {

        $scope.businessHour.friday.end.value = document.getElementsByName("payment-method-business-hour-friday-end")[0].value;
        $scope.businessHour.friday.start.value = document.getElementsByName("payment-method-business-hour-friday-start")[0].value;

        if($scope.businessHour.friday.end.value != "" || $scope.businessHour.friday.start.value != "") {

            if(!$scope.businessHour.friday.end.value.match(/^([0-9]){1,}\:([0-9]){1,}$/) || !$scope.businessHour.friday.start.value.match(/^([0-9]){1,}\:([0-9]){1,}$/) || $scope.businessHour.friday.end.value.length != 5 || $scope.businessHour.friday.start.value.length != 5) {

                $scope.businessHour.friday.response.value = "Please enter valid time range";
                $scope.businessHour.friday.response.class = "error";
                $scope.businessHour.friday.response.view = true;
                $scope.valid.businessHourFriday = false;

            } else {

                $scope.businessHour.friday.response.view = false;
                $scope.valid.businessHourFriday = true;

            }

        } else {

            $scope.businessHour.friday.response.view = false;
            $scope.valid.businessHourFriday = true;

        }

    }


    $scope.checkBusinessHourMonday = function() {

        $scope.businessHour.monday.end.value = document.getElementsByName("payment-method-business-hour-monday-end")[0].value;
        $scope.businessHour.monday.start.value = document.getElementsByName("payment-method-business-hour-monday-start")[0].value;

        if($scope.businessHour.monday.end.value != "" || $scope.businessHour.monday.start.value != "") {

            if(!$scope.businessHour.monday.end.value.match(/^([0-9]){1,}\:([0-9]){1,}$/) || !$scope.businessHour.monday.start.value.match(/^([0-9]){1,}\:([0-9]){1,}$/) || $scope.businessHour.monday.end.value.length != 5 || $scope.businessHour.monday.start.value.length != 5) {

                $scope.businessHour.monday.response.value = "Please enter valid time range";
                $scope.businessHour.monday.response.class = "error";
                $scope.businessHour.monday.response.view = true;
                $scope.valid.businessHourMonday = false;

            } else {

                $scope.businessHour.monday.response.view = false;
                $scope.valid.businessHourMonday = true;

            }

        } else {

            $scope.businessHour.monday.response.view = false;
            $scope.valid.businessHourMonday = true;

        }

    }


    $scope.checkBusinessHourSaturday = function() {

        $scope.businessHour.saturday.end.value = document.getElementsByName("payment-method-business-hour-saturday-end")[0].value;
        $scope.businessHour.saturday.start.value = document.getElementsByName("payment-method-business-hour-saturday-start")[0].value;

        if($scope.businessHour.saturday.end.value != "" || $scope.businessHour.saturday.start.value != "") {

            if(!$scope.businessHour.saturday.end.value.match(/^([0-9]){1,}\:([0-9]){1,}$/) || !$scope.businessHour.saturday.start.value.match(/^([0-9]){1,}\:([0-9]){1,}$/) || $scope.businessHour.saturday.end.value.length != 5 || $scope.businessHour.saturday.start.value.length != 5) {

                $scope.businessHour.saturday.response.value = "Please enter valid time range";
                $scope.businessHour.saturday.response.class = "error";
                $scope.businessHour.saturday.response.view = true;
                $scope.valid.businessHourSaturday = false;

            } else {

                $scope.businessHour.saturday.response.view = false;
                $scope.valid.businessHourSaturday = true;

            }

        } else {

            $scope.businessHour.saturday.response.view = false;
            $scope.valid.businessHourSaturday = true;

        }

    }


    $scope.checkBusinessHourSunday = function() {

        $scope.businessHour.sunday.end.value = document.getElementsByName("payment-method-business-hour-sunday-end")[0].value;
        $scope.businessHour.sunday.start.value = document.getElementsByName("payment-method-business-hour-sunday-start")[0].value;

        if($scope.businessHour.sunday.end.value != "" || $scope.businessHour.sunday.start.value != "") {

            if(!$scope.businessHour.sunday.end.value.match(/^([0-9]){1,}\:([0-9]){1,}$/) || !$scope.businessHour.sunday.start.value.match(/^([0-9]){1,}\:([0-9]){1,}$/) || $scope.businessHour.sunday.end.value.length != 5 || $scope.businessHour.sunday.start.value.length != 5) {

                $scope.businessHour.sunday.response.value = "Please enter valid time range";
                $scope.businessHour.sunday.response.class = "error";
                $scope.businessHour.sunday.response.view = true;
                $scope.valid.businessHourSunday = false;

            } else {

                $scope.businessHour.sunday.response.view = false;
                $scope.valid.businessHourSunday = true;

            }

        } else {

            $scope.businessHour.sunday.response.view = false;
            $scope.valid.businessHourSunday = true;

        }

    }


    $scope.checkBusinessHourThursday = function() {

        $scope.businessHour.thursday.end.value = document.getElementsByName("payment-method-business-hour-thursday-end")[0].value;
        $scope.businessHour.thursday.start.value = document.getElementsByName("payment-method-business-hour-thursday-start")[0].value;

        if($scope.businessHour.thursday.end.value != "" || $scope.businessHour.thursday.start.value != "") {

            if(!$scope.businessHour.thursday.end.value.match(/^([0-9]){1,}\:([0-9]){1,}$/) || !$scope.businessHour.thursday.start.value.match(/^([0-9]){1,}\:([0-9]){1,}$/) || $scope.businessHour.thursday.end.value.length != 5 || $scope.businessHour.thursday.start.value.length != 5) {

                $scope.businessHour.thursday.response.value = "Please enter valid time range";
                $scope.businessHour.thursday.response.class = "error";
                $scope.businessHour.thursday.response.view = true;
                $scope.valid.businessHourThursday = false;

            } else {

                $scope.businessHour.thursday.response.view = false;
                $scope.valid.businessHourThursday = true;

            }

        } else {

            $scope.businessHour.thursday.response.view = false;
            $scope.valid.businessHourThursday = true;

        }

    }


    $scope.checkBusinessHourTuesday = function() {

        $scope.businessHour.tuesday.end.value = document.getElementsByName("payment-method-business-hour-tuesday-end")[0].value;
        $scope.businessHour.tuesday.start.value = document.getElementsByName("payment-method-business-hour-tuesday-start")[0].value;

        if($scope.businessHour.tuesday.end.value != "" || $scope.businessHour.tuesday.start.value != "") {

            if(!$scope.businessHour.tuesday.end.value.match(/^([0-9]){1,}\:([0-9]){1,}$/) || !$scope.businessHour.tuesday.start.value.match(/^([0-9]){1,}\:([0-9]){1,}$/) || $scope.businessHour.tuesday.end.value.length != 5 || $scope.businessHour.tuesday.start.value.length != 5) {

                $scope.businessHour.tuesday.response.value = "Please enter valid time range";
                $scope.businessHour.tuesday.response.class = "error";
                $scope.businessHour.tuesday.response.view = true;
                $scope.valid.businessHourTuesday = false;

            } else {

                $scope.businessHour.tuesday.response.view = false;
                $scope.valid.businessHourTuesday = true;

            }

        } else {

            $scope.businessHour.tuesday.response.view = false;
            $scope.valid.businessHourTuesday = true;

        }

    }


    $scope.checkBusinessHourWednesday = function() {

        $scope.businessHour.wednesday.end.value = document.getElementsByName("payment-method-business-hour-wednesday-end")[0].value;
        $scope.businessHour.wednesday.start.value = document.getElementsByName("payment-method-business-hour-wednesday-start")[0].value;

        if($scope.businessHour.wednesday.end.value != "" || $scope.businessHour.wednesday.start.value != "") {

            if(!$scope.businessHour.wednesday.end.value.match(/^([0-9]){1,}\:([0-9]){1,}$/) || !$scope.businessHour.wednesday.start.value.match(/^([0-9]){1,}\:([0-9]){1,}$/) || $scope.businessHour.wednesday.end.value.length != 5 || $scope.businessHour.wednesday.start.value.length != 5) {

                $scope.businessHour.wednesday.response.value = "Please enter valid time range";
                $scope.businessHour.wednesday.response.class = "error";
                $scope.businessHour.wednesday.response.view = true;
                $scope.valid.businessHourWednesday = false;

            } else {

                $scope.businessHour.wednesday.response.view = false;
                $scope.valid.businessHourWednesday = true;

            }

        } else {

            $scope.businessHour.wednesday.response.view = false;
            $scope.valid.businessHourWednesday = true;

        }

    }


    $scope.checkData = function() {

        $scope.checkBusinessHourFriday();

        $scope.checkBusinessHourMonday();

        $scope.checkBusinessHourSaturday();

        $scope.checkBusinessHourSunday();

        $scope.checkBusinessHourThursday();

        $scope.checkBusinessHourTuesday();

        $scope.checkBusinessHourWednesday();

        $scope.checkCompany();

        $scope.checkCompanyBranch();

        $scope.checkFeeProvider();

        $scope.checkFeeService();

        $scope.checkName();

        $scope.checkSequence();

        $scope.checkStatus();

        $scope.checkType();

    }


    $scope.checkFeeProvider = function() {

        if($scope.fee.provider.value != "") {

            if(!$scope.fee.provider.value.match(/^[0-9,.]+$/)) {

                $scope.fee.provider.response.value = "Please enter only number";
                $scope.fee.provider.response.class = "error";
                $scope.fee.provider.response.view = true;
                $scope.valid.feeProvider = false;

            } else {

                $scope.fee.provider.response.view = false;
                $scope.valid.feeProvider = true;

            }

        } else {

            $scope.fee.provider.response.view = false;
            $scope.valid.feeProvider = true;

        }

        $scope.fee.provider.value = library.numeral.initializeSeparator($scope.fee.provider.value, false);

    }


    $scope.checkFeeService = function() {

        if($scope.fee.service.value != "") {

            if(!$scope.fee.service.value.match(/^[0-9,.]+$/)) {

                $scope.fee.service.response.value = "Please enter only number";
                $scope.fee.service.response.class = "error";
                $scope.fee.service.response.view = true;
                $scope.valid.feeService = false;

            } else {

                $scope.fee.service.response.view = false;
                $scope.valid.feeService = true;

            }

        } else {

            $scope.fee.service.response.view = false;
            $scope.valid.feeService = true;

        }

        $scope.fee.service.value = library.numeral.initializeSeparator($scope.fee.service.value, false);

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


    $scope.checkSequence = function() {

        if($scope.sequence.value != "") {

            if(!$scope.sequence.value.match(/^[0-9,]+$/)) {

                $scope.sequence.response.value = "Please enter only number";
                $scope.sequence.response.class = "error";
                $scope.sequence.response.view = true;
                $scope.valid.sequence = false;

            } else {

                $scope.sequence.response.view = false;
                $scope.valid.sequence = true;

            }

        } else {

            $scope.sequence.response.view = false;
            $scope.valid.sequence = true;

        }

        $scope.sequence.value = library.numeral.initializeSeparator($scope.sequence.value, false);

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/method/delete"
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
                    "businessHour": {
                        "friday": {
                            "end": input.businessHour.friday.end,
                            "start": input.businessHour.friday.start
                        },
                        "monday": {
                            "end": input.businessHour.monday.end,
                            "start": input.businessHour.monday.start
                        },
                        "saturday": {
                            "end": input.businessHour.saturday.end,
                            "start": input.businessHour.saturday.start
                        },
                        "sunday": {
                            "end": input.businessHour.sunday.end,
                            "start": input.businessHour.sunday.start
                        },
                        "thursday": {
                            "end": input.businessHour.thursday.end,
                            "start": input.businessHour.thursday.start
                        },
                        "tuesday": {
                            "end": input.businessHour.tuesday.end,
                            "start": input.businessHour.tuesday.start
                        },
                        "wednesday": {
                            "end": input.businessHour.wednesday.end,
                            "start": input.businessHour.wednesday.start
                        }
                    },
                    "company": {
                        "branch": {
                            "idList": $scope.company.branch.idList.value,
                            "nameList": $scope.company.branch.nameList.value
                        },
                        "idList": $scope.company.idList.value,
                        "nameList": $scope.company.nameList.value
                    },
                    "description": $scope.description.value,
                    "fee": {
                        "provider": input.fee.provider,
                        "providerFix": $scope.fee.providerFix.value,
                        "service": input.fee.service,
                        "serviceFix": $scope.fee.serviceFix.value
                    },
                    "footer": {
                        "display": $scope.footer.display.value,
                        "logo": $scope.footer.logo.value
                    },
                    "name": $scope.name.value,
                    "payment": {
                        "display": $scope.payment.display.value,
                        "logo": $scope.payment.logo.value
                    },
                    "sequence": input.sequence,
                    "sidebar": {
                        "display": $scope.sidebar.display.value,
                        "logo": $scope.sidebar.logo.value
                    },
                    "status": $scope.status.selected.value,
                    "type": $scope.type.selected.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/method/update"
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
            "status": ["equal", ""],
            "type": ["equal", ""]
        };

        if($scope.filter.status.selected.value != "Unknown") {

            data["status"][1] = $scope.filter.status.selected.value;

        }

        if($scope.filter.type.selected.value != "Unknown") {

            data["type"][1] = $scope.filter.type.selected.value;

        }

        var rest = {
            "data": data,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/method/filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/payment/method/";

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


    $scope.forceUploadFooterLogo = function() {

        document.getElementsByClassName("payment-method-footer-logo")[0].click();

    }


    $scope.forceUploadPaymentLogo = function() {

        document.getElementsByClassName("payment-method-payment-logo")[0].click();

    }


    $scope.forceUploadSidebarLogo = function() {

        document.getElementsByClassName("payment-method-sidebar-logo")[0].click();

    }


    $scope.goToPage = function(event) {

        if(event.which == 13) {

            $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/payment/method/page-" + $scope.site.page.number + "/";

        }

        event.preventDefault();

    }


    $scope.initializeData = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/method/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.paymentMethod.id != null) {

                    var businessHourFridayEnd = new Date(response.paymentMethod.businessHour.friday.end);
                    $scope.businessHour.friday.end.value = ("0" + businessHourFridayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourFridayEnd.getMinutes()).slice(-2);

                    var businessHourFridayStart = new Date(response.paymentMethod.businessHour.friday.start);
                    $scope.businessHour.friday.start.value = ("0" + businessHourFridayStart.getHours()).slice(-2) + ":" + ("0" + businessHourFridayStart.getMinutes()).slice(-2);

                    var businessHourMondayEnd = new Date(response.paymentMethod.businessHour.monday.end);
                    $scope.businessHour.monday.end.value = ("0" + businessHourMondayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourMondayEnd.getMinutes()).slice(-2);

                    var businessHourMondayStart = new Date(response.paymentMethod.businessHour.monday.start);
                    $scope.businessHour.monday.start.value = ("0" + businessHourMondayStart.getHours()).slice(-2) + ":" + ("0" + businessHourMondayStart.getMinutes()).slice(-2);

                    var businessHourSaturdayEnd = new Date(response.paymentMethod.businessHour.saturday.end);
                    $scope.businessHour.saturday.end.value = ("0" + businessHourSaturdayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourSaturdayEnd.getMinutes()).slice(-2);

                    var businessHourSaturdayStart = new Date(response.paymentMethod.businessHour.saturday.start);
                    $scope.businessHour.saturday.start.value = ("0" + businessHourSaturdayStart.getHours()).slice(-2) + ":" + ("0" + businessHourSaturdayStart.getMinutes()).slice(-2);

                    var businessHourSundayEnd = new Date(response.paymentMethod.businessHour.sunday.end);
                    $scope.businessHour.sunday.end.value = ("0" + businessHourSundayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourSundayEnd.getMinutes()).slice(-2);

                    var businessHourSundayStart = new Date(response.paymentMethod.businessHour.sunday.start);
                    $scope.businessHour.sunday.start.value = ("0" + businessHourSundayStart.getHours()).slice(-2) + ":" + ("0" + businessHourSundayStart.getMinutes()).slice(-2);

                    var businessHourThursdayEnd = new Date(response.paymentMethod.businessHour.thursday.end);
                    $scope.businessHour.thursday.end.value = ("0" + businessHourThursdayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourThursdayEnd.getMinutes()).slice(-2);

                    var businessHourThursdayStart = new Date(response.paymentMethod.businessHour.thursday.start);
                    $scope.businessHour.thursday.start.value = ("0" + businessHourThursdayStart.getHours()).slice(-2) + ":" + ("0" + businessHourThursdayStart.getMinutes()).slice(-2);

                    var businessHourTuesdayEnd = new Date(response.paymentMethod.businessHour.tuesday.end);
                    $scope.businessHour.tuesday.end.value = ("0" + businessHourTuesdayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourTuesdayEnd.getMinutes()).slice(-2);

                    var businessHourTuesdayStart = new Date(response.paymentMethod.businessHour.tuesday.start);
                    $scope.businessHour.tuesday.start.value = ("0" + businessHourTuesdayStart.getHours()).slice(-2) + ":" + ("0" + businessHourTuesdayStart.getMinutes()).slice(-2);

                    var businessHourWednesdayEnd = new Date(response.paymentMethod.businessHour.wednesday.end);
                    $scope.businessHour.wednesday.end.value = ("0" + businessHourWednesdayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourWednesdayEnd.getMinutes()).slice(-2);

                    var businessHourWednesdayStart = new Date(response.paymentMethod.businessHour.wednesday.start);
                    $scope.businessHour.wednesday.start.value = ("0" + businessHourWednesdayStart.getHours()).slice(-2) + ":" + ("0" + businessHourWednesdayStart.getMinutes()).slice(-2);

                    for(var i = 0; i < response.paymentMethod.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.paymentMethod.company.idList[i]);
                        $scope.company.nameList.value.push(response.paymentMethod.company.nameList[i]);

                    }

                    for(var i = 0; i < response.paymentMethod.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.paymentMethod.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.paymentMethod.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.paymentMethod.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.paymentMethod.created.user.id;
                    $scope.created.user.username.value = response.paymentMethod.created.user.username;
                    $scope.description.value = response.paymentMethod.description;
                    $scope.fee.provider.value = library.numeral.initializeSeparator(response.paymentMethod.fee.provider, true);
                    $scope.fee.providerFix.value = response.paymentMethod.fee.providerFix;
                    $scope.fee.service.value = library.numeral.initializeSeparator(response.paymentMethod.fee.service, true);
                    $scope.fee.serviceFix.value = response.paymentMethod.fee.serviceFix;
                    $scope.footer.display.value = response.paymentMethod.footer.display;

                    if(response.paymentMethod.footer.logo != "") {

                        $scope.footer.logo.value = response.paymentMethod.footer.logo;
                        $scope.footer.logo.upload.file = response.paymentMethod.footer.logo;
                        $scope.footer.logo.upload.view = true;

                    }

                    $scope.id.value = response.paymentMethod.id;

                    var modifiedTimestamp = new Date(response.paymentMethod.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.paymentMethod.modified.user.id;
                    $scope.modified.user.username.value = response.paymentMethod.modified.user.username;
                    $scope.name.value = response.paymentMethod.name;
                    $scope.payment.display.value = response.paymentMethod.payment.display;

                    if(response.paymentMethod.payment.logo != "") {

                        $scope.payment.logo.value = response.paymentMethod.payment.logo;
                        $scope.payment.logo.upload.file = response.paymentMethod.payment.logo;
                        $scope.payment.logo.upload.view = true;

                    }

                    $scope.sequence.value = library.numeral.initializeSeparator(response.paymentMethod.sequence, true);
                    $scope.sidebar.display.value = response.paymentMethod.sidebar.display;

                    if(response.paymentMethod.sidebar.logo != "") {

                        $scope.sidebar.logo.value = response.paymentMethod.sidebar.logo;
                        $scope.sidebar.logo.upload.file = response.paymentMethod.sidebar.logo;
                        $scope.sidebar.logo.upload.view = true;

                    }

                }

                angular.forEach(response.companyBranchList, function(value, key) {

                    $scope.company.branch.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                });

                angular.forEach(response.companyList, function(value, key) {

                    $scope.company.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                });

                angular.forEach(response.paymentTypeList, function(value, key) {

                    $scope.type.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.paymentMethod.id != null) {

                        if(value == response.paymentMethod.type) {

                            $scope.type.selected = $scope.type.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.statusList, function(value, key) {

                    $scope.status.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.paymentMethod.id != null) {

                        if(value == response.paymentMethod.status) {

                            $scope.status.selected = $scope.status.option[(key + 1)];

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
            "businessHour": {
                "friday": {
                    "end": "",
                    "start": ""
                },
                "monday": {
                    "end": "",
                    "start": ""
                },
                "saturday": {
                    "end": "",
                    "start": ""
                },
                "sunday": {
                    "end": "",
                    "start": ""
                },
                "thursday": {
                    "end": "",
                    "start": ""
                },
                "tuesday": {
                    "end": "",
                    "start": ""
                },
                "wednesday": {
                    "end": "",
                    "start": ""
                }
            },
            "fee": {
                "provider": 0,
                "service": 0
            },
            "sequence": 0
        };

        $scope.fee.provider.value = library.numeral.initializeSeparator($scope.fee.provider.value, true);
        $scope.fee.service.value = library.numeral.initializeSeparator($scope.fee.service.value, true);
        $scope.sequence.value = library.numeral.initializeSeparator($scope.sequence.value, true);

        result.fee.provider = $scope.fee.provider.value.replace(/[^0-9.]/g, "");
        result.fee.service = $scope.fee.service.value.replace(/[^0-9.]/g, "");
        result.sequence = $scope.sequence.value.replace(/[^0-9]/g, "");

        if($scope.businessHour.friday.end.value != "") {

            result.businessHour.friday.end = "2000-01-01T" + $scope.businessHour.friday.end.value + ":59.999" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.businessHour.friday.start.value != "") {

            result.businessHour.friday.start = "2000-01-01T" + $scope.businessHour.friday.start.value + ":00.000" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.businessHour.monday.end.value != "") {

            result.businessHour.monday.end = "2000-01-01T" + $scope.businessHour.monday.end.value + ":59.999" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.businessHour.monday.start.value != "") {

            result.businessHour.monday.start = "2000-01-01T" + $scope.businessHour.monday.start.value + ":00.000" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.businessHour.saturday.end.value != "") {

            result.businessHour.saturday.end = "2000-01-01T" + $scope.businessHour.saturday.end.value + ":59.999" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.businessHour.saturday.start.value != "") {

            result.businessHour.saturday.start = "2000-01-01T" + $scope.businessHour.saturday.start.value + ":00.000" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.businessHour.sunday.end.value != "") {

            result.businessHour.sunday.end = "2000-01-01T" + $scope.businessHour.sunday.end.value + ":59.999" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.businessHour.sunday.start.value != "") {

            result.businessHour.sunday.start = "2000-01-01T" + $scope.businessHour.sunday.start.value + ":00.000" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.businessHour.thursday.end.value != "") {

            result.businessHour.thursday.end = "2000-01-01T" + $scope.businessHour.thursday.end.value + ":59.999" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.businessHour.thursday.start.value != "") {

            result.businessHour.thursday.start = "2000-01-01T" + $scope.businessHour.thursday.start.value + ":00.000" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.businessHour.tuesday.end.value != "") {

            result.businessHour.tuesday.end = "2000-01-01T" + $scope.businessHour.tuesday.end.value + ":59.999" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.businessHour.tuesday.start.value != "") {

            result.businessHour.tuesday.start = "2000-01-01T" + $scope.businessHour.tuesday.start.value + ":00.000" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.businessHour.wednesday.end.value != "") {

            result.businessHour.wednesday.end = "2000-01-01T" + $scope.businessHour.wednesday.end.value + ":59.999" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        if($scope.businessHour.wednesday.start.value != "") {

            result.businessHour.wednesday.start = "2000-01-01T" + $scope.businessHour.wednesday.start.value + ":00.000" + document.getElementById("global").getAttribute("data-datetime-offset");

        }

        return result;

    }


    $scope.initializePagination = function() {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/method/initialize-pagination"
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

                angular.forEach(response.paymentTypeList, function(value, key) {

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

                angular.forEach(response.statusList, function(value, key) {

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


    $scope.initializeSwitch = function() {

        if($scope.fee.providerFix.value) {

            $scope.fee.providerFix.name = "Enabled";

        } else {

            $scope.fee.providerFix.name = "Disabled";

        }

        if($scope.fee.serviceFix.value) {

            $scope.fee.serviceFix.name = "Enabled";

        } else {

            $scope.fee.serviceFix.name = "Disabled";

        }

        if($scope.footer.display.value) {

            $scope.footer.display.name = "Enabled";

        } else {

            $scope.footer.display.name = "Disabled";

        }

        if($scope.payment.display.value) {

            $scope.payment.display.name = "Enabled";

        } else {

            $scope.payment.display.name = "Disabled";

        }

        if($scope.sidebar.display.value) {

            $scope.sidebar.display.name = "Enabled";

        } else {

            $scope.sidebar.display.name = "Disabled";

        }

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
                    "businessHour": {
                        "friday": {
                            "end": input.businessHour.friday.end,
                            "start": input.businessHour.friday.start
                        },
                        "monday": {
                            "end": input.businessHour.monday.end,
                            "start": input.businessHour.monday.start
                        },
                        "saturday": {
                            "end": input.businessHour.saturday.end,
                            "start": input.businessHour.saturday.start
                        },
                        "sunday": {
                            "end": input.businessHour.sunday.end,
                            "start": input.businessHour.sunday.start
                        },
                        "thursday": {
                            "end": input.businessHour.thursday.end,
                            "start": input.businessHour.thursday.start
                        },
                        "tuesday": {
                            "end": input.businessHour.tuesday.end,
                            "start": input.businessHour.tuesday.start
                        },
                        "wednesday": {
                            "end": input.businessHour.wednesday.end,
                            "start": input.businessHour.wednesday.start
                        }
                    },
                    "company": {
                        "branch": {
                            "idList": $scope.company.branch.idList.value,
                            "nameList": $scope.company.branch.nameList.value
                        },
                        "idList": $scope.company.idList.value,
                        "nameList": $scope.company.nameList.value
                    },
                    "description": $scope.description.value,
                    "fee": {
                        "provider": input.fee.provider,
                        "providerFix": $scope.fee.providerFix.value,
                        "service": input.fee.service,
                        "serviceFix": $scope.fee.serviceFix.value
                    },
                    "footer": {
                        "display": $scope.footer.display.value,
                        "logo": $scope.footer.logo.value
                    },
                    "name": $scope.name.value,
                    "payment": {
                        "display": $scope.payment.display.value,
                        "logo": $scope.payment.logo.value
                    },
                    "sequence": input.sequence,
                    "sidebar": {
                        "display": $scope.sidebar.display.value,
                        "logo": $scope.sidebar.logo.value
                    },
                    "status": $scope.status.selected.value,
                    "type": $scope.type.selected.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/method/insert"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/method/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.paymentMethod.id != null) {

                    var businessHourFridayEnd = new Date(response.paymentMethod.businessHour.friday.end);
                    $scope.businessHour.friday.end.value = ("0" + businessHourFridayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourFridayEnd.getMinutes()).slice(-2);

                    var businessHourFridayStart = new Date(response.paymentMethod.businessHour.friday.start);
                    $scope.businessHour.friday.start.value = ("0" + businessHourFridayStart.getHours()).slice(-2) + ":" + ("0" + businessHourFridayStart.getMinutes()).slice(-2);

                    var businessHourMondayEnd = new Date(response.paymentMethod.businessHour.monday.end);
                    $scope.businessHour.monday.end.value = ("0" + businessHourMondayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourMondayEnd.getMinutes()).slice(-2);

                    var businessHourMondayStart = new Date(response.paymentMethod.businessHour.monday.start);
                    $scope.businessHour.monday.start.value = ("0" + businessHourMondayStart.getHours()).slice(-2) + ":" + ("0" + businessHourMondayStart.getMinutes()).slice(-2);

                    var businessHourSaturdayEnd = new Date(response.paymentMethod.businessHour.saturday.end);
                    $scope.businessHour.saturday.end.value = ("0" + businessHourSaturdayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourSaturdayEnd.getMinutes()).slice(-2);

                    var businessHourSaturdayStart = new Date(response.paymentMethod.businessHour.saturday.start);
                    $scope.businessHour.saturday.start.value = ("0" + businessHourSaturdayStart.getHours()).slice(-2) + ":" + ("0" + businessHourSaturdayStart.getMinutes()).slice(-2);

                    var businessHourSundayEnd = new Date(response.paymentMethod.businessHour.sunday.end);
                    $scope.businessHour.sunday.end.value = ("0" + businessHourSundayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourSundayEnd.getMinutes()).slice(-2);

                    var businessHourSundayStart = new Date(response.paymentMethod.businessHour.sunday.start);
                    $scope.businessHour.sunday.start.value = ("0" + businessHourSundayStart.getHours()).slice(-2) + ":" + ("0" + businessHourSundayStart.getMinutes()).slice(-2);

                    var businessHourThursdayEnd = new Date(response.paymentMethod.businessHour.thursday.end);
                    $scope.businessHour.thursday.end.value = ("0" + businessHourThursdayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourThursdayEnd.getMinutes()).slice(-2);

                    var businessHourThursdayStart = new Date(response.paymentMethod.businessHour.thursday.start);
                    $scope.businessHour.thursday.start.value = ("0" + businessHourThursdayStart.getHours()).slice(-2) + ":" + ("0" + businessHourThursdayStart.getMinutes()).slice(-2);

                    var businessHourTuesdayEnd = new Date(response.paymentMethod.businessHour.tuesday.end);
                    $scope.businessHour.tuesday.end.value = ("0" + businessHourTuesdayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourTuesdayEnd.getMinutes()).slice(-2);

                    var businessHourTuesdayStart = new Date(response.paymentMethod.businessHour.tuesday.start);
                    $scope.businessHour.tuesday.start.value = ("0" + businessHourTuesdayStart.getHours()).slice(-2) + ":" + ("0" + businessHourTuesdayStart.getMinutes()).slice(-2);

                    var businessHourWednesdayEnd = new Date(response.paymentMethod.businessHour.wednesday.end);
                    $scope.businessHour.wednesday.end.value = ("0" + businessHourWednesdayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourWednesdayEnd.getMinutes()).slice(-2);

                    var businessHourWednesdayStart = new Date(response.paymentMethod.businessHour.wednesday.start);
                    $scope.businessHour.wednesday.start.value = ("0" + businessHourWednesdayStart.getHours()).slice(-2) + ":" + ("0" + businessHourWednesdayStart.getMinutes()).slice(-2);

                    for(var i = 0; i < response.paymentMethod.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.paymentMethod.company.idList[i]);
                        $scope.company.nameList.value.push(response.paymentMethod.company.nameList[i]);

                    }

                    for(var i = 0; i < response.paymentMethod.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.paymentMethod.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.paymentMethod.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.paymentMethod.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.paymentMethod.created.user.id;
                    $scope.created.user.username.value = response.paymentMethod.created.user.username;
                    $scope.description.value = response.paymentMethod.description;
                    $scope.footer.display.value = response.paymentMethod.footer.display;

                    if(response.paymentMethod.footer.logo != "") {

                        $scope.footer.logo.upload.file = document.getElementById("global").getAttribute("data-image-url") + "/payment/method/" + response.paymentMethod.footer.logo;
                        $scope.footer.logo.upload.view = true;

                    }

                    $scope.id.value = response.paymentMethod.id;

                    var modifiedTimestamp = new Date(response.paymentMethod.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.paymentMethod.modified.user.id;
                    $scope.modified.user.username.value = response.paymentMethod.modified.user.username;
                    $scope.name.value = response.paymentMethod.name;
                    $scope.payment.display.value = response.paymentMethod.payment.display;

                    if(response.paymentMethod.payment.logo != "") {

                        $scope.payment.logo.upload.file = document.getElementById("global").getAttribute("data-image-url") + "/payment/method/" + response.paymentMethod.payment.logo;
                        $scope.payment.logo.upload.view = true;

                    }

                    $scope.sequence.value = library.numeral.initializeSeparator(response.paymentMethod.sequence, true);
                    $scope.sidebar.display.value = response.paymentMethod.sidebar.display;

                    if(response.paymentMethod.sidebar.logo != "") {

                        $scope.sidebar.logo.upload.file = document.getElementById("global").getAttribute("data-image-url") + "/payment/method/" + response.paymentMethod.sidebar.logo;
                        $scope.sidebar.logo.upload.view = true;

                    }

                    $scope.status.value = $scope.camelCaseToStandardCase(response.paymentMethod.status);
                    $scope.type.value = $scope.camelCaseToStandardCase(response.paymentMethod.type);

                    $scope.company.option = [];

                    angular.forEach(response.companyList, function(value, key) {

                        $scope.company.option.push({
                            "id": value.id,
                            "name": value.name
                        });

                    });

                    $scope.company.branch.option = [];

                    angular.forEach(response.companyBranchList, function(value, key) {

                        $scope.company.branch.option.push({
                            "id": value.id,
                            "name": value.name
                        });

                    });

                    $scope.initializeSwitch();

                }

                $scope.popup.view = true;
                $scope.popup.paymentMethod = true;

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/method/remove-filter-pagination"
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


    $scope.setPagination = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": $scope.site.page.size,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/method/set-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/payment/method/";

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


    $scope.uploadFooterLogo = function(index) {

        $scope.loading.view = true;

        var formData = new FormData();

        angular.forEach($scope.files, function(value) {

            formData.append("file", value);

        });

        var rest = {
            "data": formData,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/method/upload-image"
        };
        global.restMultipart(rest, function(response) {

            if(response.result) {

                $scope.response.class = "success";

                $scope.footer.logo.value = response.imageList[0];
                $scope.footer.logo.upload.file = response.imageList[0];
                $scope.footer.logo.upload.view = true;

            } else {

                $scope.response.class = "error";

            }

            $scope.loading.view = false;

            $scope.response.message = response.response;
            $scope.response.view = true;

            $scope.hideResponse();

        });

    }


    $scope.uploadPaymentLogo = function(index) {

        $scope.loading.view = true;

        var formData = new FormData();

        angular.forEach($scope.files, function(value) {

            formData.append("file", value);

        });

        var rest = {
            "data": formData,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/method/upload-image"
        };
        global.restMultipart(rest, function(response) {

            if(response.result) {

                $scope.response.class = "success";

                $scope.payment.logo.value = response.imageList[0];
                $scope.payment.logo.upload.file = response.imageList[0];
                $scope.payment.logo.upload.view = true;

            } else {

                $scope.response.class = "error";

            }

            $scope.loading.view = false;

            $scope.response.message = response.response;
            $scope.response.view = true;

            $scope.hideResponse();

        });

    }


    $scope.uploadSidebarLogo = function(index) {

        $scope.loading.view = true;

        var formData = new FormData();

        angular.forEach($scope.files, function(value) {

            formData.append("file", value);

        });

        var rest = {
            "data": formData,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/method/upload-image"
        };
        global.restMultipart(rest, function(response) {

            if(response.result) {

                $scope.response.class = "success";

                $scope.sidebar.logo.value = response.imageList[0];
                $scope.sidebar.logo.upload.file = response.imageList[0];
                $scope.sidebar.logo.upload.view = true;

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


app.directive("paymentMethodDetail", function() {


    var paymentMethod = {};


    paymentMethod.templateUrl = document.getElementById("global").getAttribute("data-base-url") + "/html/payment-method-detail-popup.html";


    return paymentMethod;


});


app.directive("paymentMethodFooterLogoPreview", function() {


    var paymentMethod = {};


    paymentMethod.template = "<span ng-if=\"footer.logo.upload.file != ''\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/payment/method/{{footer.logo.upload.file}}\" />" +
        "</span>";


    return paymentMethod;


});


app.directive("paymentMethodPaymentLogoPreview", function() {


    var paymentMethod = {};


    paymentMethod.template = "<span ng-if=\"payment.logo.upload.file != ''\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/payment/method/{{payment.logo.upload.file}}\" />" +
        "</span>";


    return paymentMethod;


});


app.directive("paymentMethodSidebarLogoPreview", function() {


    var paymentMethod = {};


    paymentMethod.template = "<span ng-if=\"sidebar.logo.upload.file != ''\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/payment/method/{{sidebar.logo.upload.file}}\" />" +
        "</span>";


    return paymentMethod;


});
