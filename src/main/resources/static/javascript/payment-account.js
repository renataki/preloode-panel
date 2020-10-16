app.controller("paymentAccount", ["$scope", "$window", "global", function($scope, $window, global) {


    $scope.branch = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.credit = {
        "value": 0
    };

    $scope.filter = {
        "createdDate": {
            "value": ""
        },
        "id": {
            "value": ""
        },
        "method": {
            "option": [
                {"id": "", "name": "Method"}
            ]
        },
        "name": {
            "value": ""
        },
        "number": {
            "value": ""
        },
        "status": {
            "option": [
                {"name": "Status", "value": "Unknown"}
            ]
        },
        "username": {
            "value": ""
        }
    };

    $scope.filter.method.selected = $scope.filter.method.option[0];

    $scope.filter.status.selected = $scope.filter.status.option[0];

    $scope.method = {
        "option": [
            {"id": "", "name": "Method"}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.method.selected = $scope.method.option[0];

    $scope.name = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.number = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.password = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.pin = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.qrCode = {
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

    $scope.sequence = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.username = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.valid = Object.assign($scope.valid, {
        "branch": false,
        "method": false,
        "name": false,
        "number": false,
        "password": false,
        "pin": false,
        "sequence": false,
        "username": false
    });


    $scope.checkBranch = function() {

        if($scope.branch.value != "") {

            if($scope.branch.value.length < 2) {

                $scope.branch.response.value = "Please enter more than 2 characters";
                $scope.branch.response.class = "error";
                $scope.branch.response.view = true;
                $scope.valid.branch = false;

            } else {

                $scope.branch.response.view = false;
                $scope.valid.branch = true;

            }

        } else {

            $scope.branch.response.view = false;
            $scope.valid.branch = true;

        }

    }


    $scope.checkData = function() {

        $scope.checkBranch();

        $scope.checkCompany();

        $scope.checkCompanyBranch();

        $scope.checkMethod();

        $scope.checkName();

        $scope.checkNumber();

        $scope.checkPassword();

        $scope.checkPin();

        $scope.checkSequence();

        $scope.checkStatus();

        $scope.checkUsername();

    }


    $scope.checkMethod = function() {

        if($scope.method.selected.id == "") {

            $scope.method.response.value = "Please select method";
            $scope.method.response.class = "error";
            $scope.method.response.view = true;
            $scope.valid.method = false;

        } else {

            $scope.method.response.view = false;
            $scope.valid.method = true;

        }

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


    $scope.checkNumber = function() {

        if($scope.number.value != "") {

            if(!$scope.number.value.match(/^[0-9]+$/)) {

                $scope.number.response.value = "Please enter only number";
                $scope.number.response.class = "error";
                $scope.number.response.view = true;
                $scope.valid.number = false;

            } else {

                $scope.number.response.view = false;
                $scope.valid.number = true;

            }

        } else {

            $scope.number.response.view = false;
            $scope.valid.number = true;

        }

    }


    $scope.checkPassword = function() {

        if($scope.password.value != "") {

            if($scope.password.value.length < 2) {

                $scope.password.response.value = "Please enter more than 2 characters";
                $scope.password.response.class = "error";
                $scope.password.response.view = true;
                $scope.valid.password = false;

            } else {

                $scope.password.response.view = false;
                $scope.valid.password = true;

            }

        } else {

            $scope.password.response.view = false;
            $scope.valid.password = true;

        }

    }


    $scope.checkPin = function() {

        if($scope.pin.value != "") {

            if($scope.pin.value.length < 2) {

                $scope.pin.response.value = "Please enter more than 2 characters";
                $scope.pin.response.class = "error";
                $scope.pin.response.view = true;
                $scope.valid.pin = false;

            } else {

                $scope.pin.response.view = false;
                $scope.valid.pin = true;

            }

        } else {

            $scope.pin.response.view = false;
            $scope.valid.pin = true;

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


    $scope.checkUsername = function() {

        if($scope.username.value != "") {

            if($scope.username.value.length < 2) {

                $scope.username.response.value = "Please enter more than 2 characters";
                $scope.username.response.class = "error";
                $scope.username.response.view = true;
                $scope.valid.username = false;

            } else {

                $scope.username.response.view = false;
                $scope.valid.username = true;

            }

        } else {

            $scope.username.response.view = false;
            $scope.valid.username = true;

        }

    }


    $scope.delete = function(id, event) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/account/delete"
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
                    "branch": $scope.branch.value,
                    "company": {
                        "branch": {
                            "idList": $scope.company.branch.idList.value,
                            "nameList": $scope.company.branch.nameList.value
                        },
                        "idList": $scope.company.idList.value,
                        "nameList": $scope.company.nameList.value
                    },
                    "description": $scope.description.value,
                    "method": {
                        "id": $scope.method.selected.id,
                        "name": $scope.method.selected.name,
                        "type": $scope.method.selected.type
                    },
                    "name": $scope.name.value,
                    "number": $scope.number.value,
                    "password": $scope.password.value,
                    "pin": $scope.pin.value,
                    "qrCode": $scope.qrCode.value,
                    "sequence": input.sequence,
                    "status": $scope.status.selected.value,
                    "username": $scope.username.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/account/update"
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
            "method.id": ["equal", $scope.filter.method.selected.id],
            "name": ["like", $scope.filter.name.value],
            "number": ["equal", $scope.filter.number.value],
            "status": ["equal", ""],
            "username": ["equal", $scope.filter.username.value]
        };

        if($scope.filter.status.selected.value != "Unknown") {

            data["status"][1] = $scope.filter.status.selected.value;

        }

        var rest = {
            "data": data,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/account/filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/payment/account/";

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


    $scope.forceUploadQrCode = function() {

        document.getElementsByClassName("payment-account-qr-code")[0].click();

    }


    $scope.goToPage = function(event) {

        if(event.which == 13) {

            $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/payment/account/page-" + $scope.site.page.number + "/";

        }

        event.preventDefault();

    }


    $scope.initializeData = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/account/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.paymentAccount.id != null) {

                    $scope.branch.value = response.paymentAccount.branch;

                    for(var i = 0; i < response.paymentAccount.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.paymentAccount.company.idList[i]);
                        $scope.company.nameList.value.push(response.paymentAccount.company.nameList[i]);

                    }

                    for(var i = 0; i < response.paymentAccount.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.paymentAccount.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.paymentAccount.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.paymentAccount.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.paymentAccount.created.user.id;
                    $scope.created.user.username.value = response.paymentAccount.created.user.username;
                    $scope.credit.value = library.numeral.initializeSeparator(response.paymentAccount.credit, false);
                    $scope.description.value = response.paymentAccount.description;
                    $scope.id.value = response.paymentAccount.id;

                    var modifiedTimestamp = new Date(response.paymentAccount.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.paymentAccount.modified.user.id;
                    $scope.modified.user.username.value = response.paymentAccount.modified.user.username;
                    $scope.name.value = response.paymentAccount.name;
                    $scope.number.value = response.paymentAccount.number;
                    $scope.password.value = response.paymentAccount.password;
                    $scope.pin.value = response.paymentAccount.pin;

                    if(response.paymentAccount.qrCode != "") {

                        $scope.qrCode.value = response.paymentAccount.qrCode;
                        $scope.qrCode.upload.file = response.paymentAccount.qrCode;
                        $scope.qrCode.upload.view = true;

                    }

                    $scope.sequence.value = library.numeral.initializeSeparator(response.paymentAccount.sequence, true);
                    $scope.username.value = response.paymentAccount.username;

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

                angular.forEach(response.paymentMethodList, function(value, key) {

                    $scope.method.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                    if(response.paymentAccount.id != null) {

                        if(value.id == response.paymentAccount.method.id) {

                            $scope.method.selected = $scope.method.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.statusList, function(value, key) {

                    $scope.status.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.paymentAccount.id != null) {

                        if(value == response.paymentAccount.status) {

                            $scope.status.selected = $scope.status.option[(key + 1)];

                        }

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


    $scope.initializeInput = function() {

        var result = {
            "sequence": 0
        };

        $scope.sequence.value = library.numeral.initializeSeparator($scope.sequence.value, true);

        result.sequence = $scope.sequence.value.replace(/[^0-9]/g, "");

        return result;

    }


    $scope.initializePagination = function() {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/account/initialize-pagination"
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
                    $scope.filter.number.value = response.filter.number[1];
                    $scope.filter.username.value = response.filter.username[1];

                }

                angular.forEach(response.paymentMethodList, function(value, key) {

                    $scope.filter.method.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                    if(Object.entries(response.filter).length > 0) {

                        if(value.id == response.filter.method_id[1]) {

                            $scope.filter.method.selected = $scope.filter.method.option[(key + 1)];

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
                    "branch": $scope.branch.value,
                    "company": {
                        "branch": {
                            "idList": $scope.company.branch.idList.value,
                            "nameList": $scope.company.branch.nameList.value
                        },
                        "idList": $scope.company.idList.value,
                        "nameList": $scope.company.nameList.value
                    },
                    "description": $scope.description.value,
                    "method": {
                        "id": $scope.method.selected.id,
                        "name": $scope.method.selected.name,
                        "type": $scope.method.selected.type
                    },
                    "name": $scope.name.value,
                    "number": $scope.number.value,
                    "password": $scope.password.value,
                    "pin": $scope.pin.value,
                    "qrCode": $scope.qrCode.value,
                    "sequence": input.sequence,
                    "status": $scope.status.selected.value,
                    "username": $scope.username.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/account/insert"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/account/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.paymentAccount.id != null) {

                    $scope.branch.value = response.paymentAccount.branch;

                    for(var i = 0; i < response.paymentAccount.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.paymentAccount.company.idList[i]);
                        $scope.company.nameList.value.push(response.paymentAccount.company.nameList[i]);

                    }

                    for(var i = 0; i < response.paymentAccount.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.paymentAccount.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.paymentAccount.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.paymentAccount.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);

                    $scope.created.user.id.value = response.paymentAccount.created.user.id;
                    $scope.created.user.username.value = response.paymentAccount.created.user.username;
                    $scope.credit.value = library.numeral.initializeSeparator(response.paymentAccount.credit, false);
                    $scope.description.value = response.paymentAccount.description;
                    $scope.id.value = response.paymentAccount.id;
                    $scope.method.value = response.paymentAccount.method.name;

                    var modifiedTimestamp = new Date(response.paymentAccount.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);

                    $scope.modified.user.id.value = response.paymentAccount.modified.user.id;
                    $scope.modified.user.username.value = response.paymentAccount.modified.user.username;
                    $scope.name.value = response.paymentAccount.name;
                    $scope.number.value = response.paymentAccount.number;
                    $scope.password.value = response.paymentAccount.password;
                    $scope.pin.value = response.paymentAccount.pin;

                    if(response.paymentAccount.qrCode != "") {

                        $scope.qrCode.upload.file = document.getElementById("global").getAttribute("data-image-url") + "/payment/account/" + response.paymentAccount.qrCode;
                        $scope.qrCode.upload.view = true;

                    }

                    $scope.sequence.value = library.numeral.initializeSeparator(response.paymentAccount.sequence, true);
                    $scope.status.value = $scope.camelCaseToStandardCase(response.paymentAccount.status);
                    $scope.username.value = response.paymentAccount.username;

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

                }

                $scope.popup.view = true;
                $scope.popup.paymentAccount = true;

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


    $scope.loadMutation = function(event) {

        $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/payment/account/mutation/" + $scope.id.value + "/";

    }


    $scope.removeFilterPagination = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/account/remove-filter-pagination"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/account/set-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/payment/account/";

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


    $scope.uploadQrCode = function() {

        $scope.loading.view = true;

        var formData = new FormData();

        angular.forEach($scope.files, function(value) {

            formData.append("file", value);

        });

        var rest = {
            "data": formData,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/payment/account/upload-qr-code"
        };
        global.restMultipart(rest, function(response) {

            if(response.result) {

                $scope.response.class = "success";

                $scope.qrCode.value = response.imageList[0];
                $scope.qrCode.upload.file = response.imageList[0];
                $scope.qrCode.upload.view = true;

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


app.directive("paymentAccountDetail", function() {


    var paymentAccount = {};


    paymentAccount.templateUrl = document.getElementById("global").getAttribute("data-base-url") + "/html/payment-account-detail-popup.html";


    return paymentAccount;


});


app.directive("paymentAccountQrCodePreview", function() {


    var paymentAccount = {};


    paymentAccount.template = "<span ng-if=\"qrCode.upload.file != ''\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/payment/account/{{qrCode.upload.file}}\" />" +
        "</span>";


    return paymentAccount;


});
