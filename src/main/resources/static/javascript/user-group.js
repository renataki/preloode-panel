app.controller("userGroup", ["$scope", "$window", "global", function($scope, $window, global) {


    $scope.deposit = {
        "amount": {
            "end": {
                "value": ""
            },
            "start": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        },
        "count": {
            "end": {
                "value": ""
            },
            "start": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
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

    $scope.filter.type.selected = $scope.filter.type.option[0];

    $scope.name = {
        "response": {
            "class": "",
            "value": "",
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

    $scope.transaction = {
        "amount": {
            "end": {
                "value": ""
            },
            "start": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        },
        "count": {
            "end": {
                "value": ""
            },
            "start": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
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
        "depositAmount": false,
        "depositCount": false,
        "name": false,
        "sequence": false,
        "transactionAmount": false,
        "transactionCount": false,
        "type": false,
        "withdrawalAmount": false,
        "withdrawalCount": false
    });

    $scope.withdrawal = {
        "amount": {
            "end": {
                "value": ""
            },
            "start": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        },
        "count": {
            "end": {
                "value": ""
            },
            "start": {
                "value": ""
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        }
    };


    $scope.checkData = function() {

        $scope.checkCompany();

        $scope.checkCompanyBranch();

        $scope.checkDepositAmount();

        $scope.checkDepositCount();

        $scope.checkName();

        $scope.checkSequence();

        $scope.checkStatus();

        $scope.checkTransactionAmount();

        $scope.checkTransactionCount();

        $scope.checkType();

        $scope.checkWithdrawalAmount();

        $scope.checkWithdrawalCount();

    }


    $scope.checkDepositAmount = function() {

        if($scope.deposit.amount.end.value != "" || $scope.deposit.amount.start.value != "") {

            if(!$scope.deposit.amount.end.value.match(/^[0-9,.]+$/) || !$scope.deposit.amount.start.value.match(/^[0-9,.]+$/)) {

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

        $scope.deposit.amount.end.value = library.numeral.initializeSeparator($scope.deposit.amount.end.value, false);
        $scope.deposit.amount.start.value = library.numeral.initializeSeparator($scope.deposit.amount.start.value, false);

    }


    $scope.checkDepositCount = function() {

        if($scope.deposit.count.end.value != "" || $scope.deposit.count.start.value != "") {

            if(!$scope.deposit.count.end.value.match(/^[0-9,]+$/) || !$scope.deposit.count.start.value.match(/^[0-9,]+$/)) {

                $scope.deposit.count.response.value = "Please enter number only";
                $scope.deposit.count.response.class = "error";
                $scope.deposit.count.response.view = true;
                $scope.valid.depositCount = false;

            } else {

                $scope.deposit.count.response.view = false;
                $scope.valid.depositCount = true;

            }

        } else {

            $scope.deposit.count.response.view = false;
            $scope.valid.depositCount = true;

        }

        $scope.deposit.count.end.value = library.numeral.initializeSeparator($scope.deposit.count.end.value, false);
        $scope.deposit.count.start.value = library.numeral.initializeSeparator($scope.deposit.count.start.value, false);

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


    $scope.checkTransactionAmount = function() {

        if($scope.transaction.amount.end.value != "" || $scope.transaction.amount.start.value != "") {

            if(!$scope.transaction.amount.end.value.match(/^[0-9,.]+$/) || !$scope.transaction.amount.start.value.match(/^[0-9,.]+$/)) {

                $scope.transaction.amount.response.value = "Please enter decimal amount";
                $scope.transaction.amount.response.class = "error";
                $scope.transaction.amount.response.view = true;
                $scope.valid.transactionAmount = false;

            } else {

                $scope.transaction.amount.response.view = false;
                $scope.valid.transactionAmount = true;

            }

        } else {

            $scope.transaction.amount.response.view = false;
            $scope.valid.transactionAmount = true;

        }

        $scope.transaction.amount.end.value = library.numeral.initializeSeparator($scope.transaction.amount.end.value, false);
        $scope.transaction.amount.start.value = library.numeral.initializeSeparator($scope.transaction.amount.start.value, false);

    }


    $scope.checkTransactionCount = function() {

        if($scope.transaction.count.end.value != "" || $scope.transaction.count.start.value != "") {

            if(!$scope.transaction.count.end.value.match(/^[0-9,]+$/) || !$scope.transaction.count.start.value.match(/^[0-9,]+$/)) {

                $scope.transaction.count.response.value = "Please enter number only";
                $scope.transaction.count.response.class = "error";
                $scope.transaction.count.response.view = true;
                $scope.valid.transactionCount = false;

            } else {

                $scope.transaction.count.response.view = false;
                $scope.valid.transactionCount = true;

            }

        } else {

            $scope.transaction.count.response.view = false;
            $scope.valid.transactionCount = true;

        }

        $scope.transaction.count.end.value = library.numeral.initializeSeparator($scope.transaction.count.end.value, false);
        $scope.transaction.count.start.value = library.numeral.initializeSeparator($scope.transaction.count.start.value, false);

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


    $scope.checkWithdrawalAmount = function() {

        if($scope.withdrawal.amount.end.value != "" || $scope.withdrawal.amount.start.value != "") {

            if(!$scope.withdrawal.amount.end.value.match(/^[0-9,.]+$/) || !$scope.withdrawal.amount.start.value.match(/^[0-9,.]+$/)) {

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

        $scope.withdrawal.amount.end.value = library.numeral.initializeSeparator($scope.withdrawal.amount.end.value, false);
        $scope.withdrawal.amount.start.value = library.numeral.initializeSeparator($scope.withdrawal.amount.start.value, false);

    }


    $scope.checkWithdrawalCount = function() {

        if($scope.withdrawal.count.end.value != "" || $scope.withdrawal.count.start.value != "") {

            if(!$scope.withdrawal.count.end.value.match(/^[0-9,]+$/) || !$scope.withdrawal.count.start.value.match(/^[0-9,]+$/)) {

                $scope.withdrawal.count.response.value = "Please enter number only";
                $scope.withdrawal.count.response.class = "error";
                $scope.withdrawal.count.response.view = true;
                $scope.valid.withdrawalCount = false;

            } else {

                $scope.withdrawal.count.response.view = false;
                $scope.valid.withdrawalCount = true;

            }

        } else {

            $scope.withdrawal.count.response.view = false;
            $scope.valid.withdrawalCount = true;

        }

        $scope.withdrawal.count.end.value = library.numeral.initializeSeparator($scope.withdrawal.count.end.value, false);
        $scope.withdrawal.count.start.value = library.numeral.initializeSeparator($scope.withdrawal.count.start.value, false);

    }


    $scope.delete = function(id, event) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/group/delete"
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
                    "company": {
                        "branch": {
                            "idList": $scope.company.branch.idList.value,
                            "nameList": $scope.company.branch.nameList.value
                        },
                        "idList": $scope.company.idList.value,
                        "nameList": $scope.company.nameList.value
                    },
                    "deposit": {
                        "amount": {
                            "end": input.deposit.amount.end,
                            "start": input.deposit.amount.start
                        },
                        "count": {
                            "end": input.deposit.count.end,
                            "start": input.deposit.count.start
                        }
                    },
                    "description": $scope.description.value,
                    "name": $scope.name.value,
                    "sequence": input.sequence,
                    "status": $scope.status.selected.value,
                    "transaction": {
                        "amount": {
                            "end": input.transaction.amount.end,
                            "start": input.transaction.amount.start
                        },
                        "count": {
                            "end": input.transaction.count.end,
                            "start": input.transaction.count.start
                        }
                    },
                    "type": $scope.type.selected.value,
                    "withdrawal": {
                        "amount": {
                            "end": input.withdrawal.amount.end,
                            "start": input.withdrawal.amount.start
                        },
                        "count": {
                            "end": input.withdrawal.count.end,
                            "start": input.withdrawal.count.start
                        }
                    }
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/user/group/update"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/group/filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/user/group/";

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


    $scope.goToPage = function(event) {

        if(event.which == 13) {

            $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/user/group/page-" + $scope.site.page.number + "/";

        }

        event.preventDefault();

    }


    $scope.initializeData = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/group/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.userGroup.id != null) {

                    for(var i = 0; i < response.userGroup.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.userGroup.company.idList[i]);
                        $scope.company.nameList.value.push(response.userGroup.company.nameList[i]);

                    }

                    for(var i = 0; i < response.userGroup.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.userGroup.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.userGroup.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.userGroup.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.userGroup.created.user.id;
                    $scope.created.user.username.value = response.userGroup.created.user.username;
                    $scope.deposit.amount.end.value = library.numeral.initializeSeparator(response.userGroup.deposit.amount.end, true);
                    $scope.deposit.amount.start.value = library.numeral.initializeSeparator(response.userGroup.deposit.amount.start, true);
                    $scope.deposit.count.end.value = library.numeral.initializeSeparator(response.userGroup.deposit.count.end, true);
                    $scope.deposit.count.start.value = library.numeral.initializeSeparator(response.userGroup.deposit.count.start, true);
                    $scope.description.value = response.userGroup.description;
                    $scope.id.value = response.userGroup.id;

                    var modifiedTimestamp = new Date(response.userGroup.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.userGroup.modified.user.id;
                    $scope.modified.user.username.value = response.userGroup.modified.user.username;
                    $scope.name.value = response.userGroup.name;
                    $scope.sequence.value = library.numeral.initializeSeparator(response.userGroup.sequence, true);
                    $scope.transaction.amount.end.value = library.numeral.initializeSeparator(response.userGroup.transaction.amount.end, true);
                    $scope.transaction.amount.start.value = library.numeral.initializeSeparator(response.userGroup.transaction.amount.start, true);
                    $scope.transaction.count.end.value = library.numeral.initializeSeparator(response.userGroup.transaction.count.end, true);
                    $scope.transaction.count.start.value = library.numeral.initializeSeparator(response.userGroup.transaction.count.start, true);
                    $scope.withdrawal.amount.end.value = library.numeral.initializeSeparator(response.userGroup.withdrawal.amount.end, true);
                    $scope.withdrawal.amount.start.value = library.numeral.initializeSeparator(response.userGroup.withdrawal.amount.start, true);
                    $scope.withdrawal.count.end.value = library.numeral.initializeSeparator(response.userGroup.withdrawal.count.end, true);
                    $scope.withdrawal.count.start.value = library.numeral.initializeSeparator(response.userGroup.withdrawal.count.start, true);

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

                angular.forEach(response.statusList, function(value, key) {

                    $scope.status.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.userGroup.id != null) {

                        if(value == response.userGroup.status) {

                            $scope.status.selected = $scope.status.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.userTypeList, function(value, key) {

                    $scope.type.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.userGroup.id != null) {

                        if(value == response.userGroup.type) {

                            $scope.type.selected = $scope.type.option[(key + 1)];

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
            "deposit": {
                "amount": {
                    "end": 0,
                    "start": 0
                },
                "count": {
                    "end": 0,
                    "start": 0
                }
            },
            "sequence": 0,
            "transaction": {
                "amount": {
                    "end": 0,
                    "start": 0
                },
                "count": {
                    "end": 0,
                    "start": 0
                }
            },
            "withdrawal": {
                "amount": {
                    "end": 0,
                    "start": 0
                },
                "count": {
                    "end": 0,
                    "start": 0
                }
            }
        };

        $scope.deposit.amount.end.value = library.numeral.initializeSeparator($scope.deposit.amount.end.value, true);
        $scope.deposit.amount.start.value = library.numeral.initializeSeparator($scope.deposit.amount.start.value, true);
        $scope.deposit.count.end.value = library.numeral.initializeSeparator($scope.deposit.count.end.value, true);
        $scope.deposit.count.start.value = library.numeral.initializeSeparator($scope.deposit.count.start.value, true);
        $scope.transaction.amount.end.value = library.numeral.initializeSeparator($scope.transaction.amount.end.value, true);
        $scope.transaction.amount.start.value = library.numeral.initializeSeparator($scope.transaction.amount.start.value, true);
        $scope.transaction.count.end.value = library.numeral.initializeSeparator($scope.transaction.count.end.value, true);
        $scope.transaction.count.start.value = library.numeral.initializeSeparator($scope.transaction.count.start.value, true);
        $scope.sequence.value = library.numeral.initializeSeparator($scope.sequence.value, true);
        $scope.withdrawal.amount.end.value = library.numeral.initializeSeparator($scope.withdrawal.amount.end.value, true);
        $scope.withdrawal.amount.start.value = library.numeral.initializeSeparator($scope.withdrawal.amount.start.value, true);
        $scope.withdrawal.count.end.value = library.numeral.initializeSeparator($scope.withdrawal.count.end.value, true);
        $scope.withdrawal.count.start.value = library.numeral.initializeSeparator($scope.withdrawal.count.start.value, true);

        result.deposit.amount.end = $scope.deposit.amount.end.value.replace(/[^0-9.]/g, "");
        result.deposit.amount.start = $scope.deposit.amount.start.value.replace(/[^0-9.]/g, "");
        result.deposit.count.end = $scope.deposit.count.end.value.replace(/[^0-9.]/g, "");
        result.deposit.count.start = $scope.deposit.count.start.value.replace(/[^0-9.]/g, "");
        result.transaction.amount.end = $scope.transaction.amount.end.value.replace(/[^0-9.]/g, "");
        result.transaction.amount.start = $scope.transaction.amount.start.value.replace(/[^0-9.]/g, "");
        result.transaction.count.end = $scope.transaction.count.end.value.replace(/[^0-9.]/g, "");
        result.transaction.count.start = $scope.transaction.count.start.value.replace(/[^0-9.]/g, "");
        result.sequence = $scope.sequence.value.replace(/[^0-9]/g, "");
        result.withdrawal.amount.end = $scope.withdrawal.amount.end.value.replace(/[^0-9.]/g, "");
        result.withdrawal.amount.start = $scope.withdrawal.amount.start.value.replace(/[^0-9.]/g, "");
        result.withdrawal.count.end = $scope.withdrawal.count.end.value.replace(/[^0-9.]/g, "");
        result.withdrawal.count.start = $scope.withdrawal.count.start.value.replace(/[^0-9.]/g, "");

        return result;

    }


    $scope.initializePagination = function() {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/group/initialize-pagination"
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

                angular.forEach(response.userTypeList, function(value, key) {

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
                    "company": {
                        "branch": {
                            "idList": $scope.company.branch.idList.value,
                            "nameList": $scope.company.branch.nameList.value
                        },
                        "idList": $scope.company.idList.value,
                        "nameList": $scope.company.nameList.value
                    },
                    "deposit": {
                        "amount": {
                            "end": input.deposit.amount.end,
                            "start": input.deposit.amount.start
                        },
                        "count": {
                            "end": input.deposit.count.end,
                            "start": input.deposit.count.start
                        }
                    },
                    "description": $scope.description.value,
                    "name": $scope.name.value,
                    "sequence": input.sequence,
                    "status": $scope.status.selected.value,
                    "transaction": {
                        "amount": {
                            "end": input.transaction.amount.end,
                            "start": input.transaction.amount.start
                        },
                        "count": {
                            "end": input.transaction.count.end,
                            "start": input.transaction.count.start
                        }
                    },
                    "type": $scope.type.selected.value,
                    "withdrawal": {
                        "amount": {
                            "end": input.withdrawal.amount.end,
                            "start": input.withdrawal.amount.start
                        },
                        "count": {
                            "end": input.withdrawal.count.end,
                            "start": input.withdrawal.count.start
                        }
                    }
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/user/group/insert"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/group/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.userGroup.id != null) {

                    for(var i = 0; i < response.userGroup.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.userGroup.company.idList[i]);
                        $scope.company.nameList.value.push(response.userGroup.company.nameList[i]);

                    }

                    for(var i = 0; i < response.userGroup.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.userGroup.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.userGroup.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.userGroup.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);

                    $scope.created.user.id.value = response.userGroup.created.user.id;
                    $scope.created.user.username.value = response.userGroup.created.user.username;
                    $scope.deposit.amount.end.value = library.numeral.initializeSeparator(response.userGroup.deposit.amount.end, true);
                    $scope.deposit.amount.start.value = library.numeral.initializeSeparator(response.userGroup.deposit.amount.start, true);
                    $scope.deposit.count.end.value = library.numeral.initializeSeparator(response.userGroup.deposit.count.end, true);
                    $scope.deposit.count.start.value = library.numeral.initializeSeparator(response.userGroup.deposit.count.start, true);
                    $scope.description.value = response.userGroup.description;
                    $scope.id.value = response.userGroup.id;

                    var modifiedTimestamp = new Date(response.userGroup.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);

                    $scope.modified.user.id.value = response.userGroup.modified.user.id;
                    $scope.modified.user.username.value = response.userGroup.modified.user.username;
                    $scope.name.value = response.userGroup.name;
                    $scope.sequence.value = library.numeral.initializeSeparator(response.userGroup.sequence, true);
                    $scope.status.value = $scope.camelCaseToStandardCase(response.userGroup.status);
                    $scope.transaction.amount.end.value = library.numeral.initializeSeparator(response.userGroup.transaction.amount.end, true);
                    $scope.transaction.amount.start.value = library.numeral.initializeSeparator(response.userGroup.transaction.amount.start, true);
                    $scope.transaction.count.end.value = library.numeral.initializeSeparator(response.userGroup.transaction.count.end, true);
                    $scope.transaction.count.start.value = library.numeral.initializeSeparator(response.userGroup.transaction.count.start, true);
                    $scope.withdrawal.amount.end.value = library.numeral.initializeSeparator(response.userGroup.withdrawal.amount.end, true);
                    $scope.withdrawal.amount.start.value = library.numeral.initializeSeparator(response.userGroup.withdrawal.amount.start, true);
                    $scope.withdrawal.count.end.value = library.numeral.initializeSeparator(response.userGroup.withdrawal.count.end, true);
                    $scope.withdrawal.count.start.value = library.numeral.initializeSeparator(response.userGroup.withdrawal.count.start, true);
                    $scope.type.value = $scope.camelCaseToStandardCase(response.userGroup.type);

                    $scope.company.branch.option = [];

                    angular.forEach(response.companyBranchList, function(value, key) {

                        $scope.company.branch.option.push({
                            "id": value.id,
                            "name": value.name
                        });

                    });

                    $scope.company.option = [];

                    angular.forEach(response.companyList, function(value, key) {

                        $scope.company.option.push({
                            "id": value.id,
                            "name": value.name
                        });

                    });

                }

                $scope.popup.view = true;
                $scope.popup.userGroup = true;

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/group/remove-filter-pagination"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/group/set-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/user/group/";

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


}]);


app.directive("userGroupDetail", function() {


    var userGroup = {};


    userGroup.templateUrl = document.getElementById("global").getAttribute("data-base-url") + "/html/user-group-detail-popup.html";


    return userGroup;


});
