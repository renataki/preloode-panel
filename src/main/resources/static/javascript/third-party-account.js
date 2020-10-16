app.controller("thirdPartyAccount", ["$scope", "$window", "global", function($scope, $window, global) {


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
        "provider": {
            "option": [
                {"id": "", "name": "Provider"}
            ]
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

    $scope.filter.provider.selected = $scope.filter.provider.option[0];

    $scope.filter.status.selected = $scope.filter.status.option[0];

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

    $scope.provider = {
        "option": [
            {"id": "", "name": "Provider"}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.provider.selected = $scope.provider.option[0];

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
        "password": false,
        "pin": false,
        "provider": false,
        "sequence": false,
        "username": false
    });


    $scope.checkData = function() {

        $scope.checkCompany();

        $scope.checkCompanyBranch();

        $scope.checkPassword();

        $scope.checkPin();

        $scope.checkProvider();

        $scope.checkSequence();

        $scope.checkStatus();

        $scope.checkUsername();

    }


    $scope.checkPassword = function() {

        if($scope.password.value != "") {

            if($scope.password.value.length < 3) {

                $scope.password.response.value = "Please enter more than 3 characters";
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

            if($scope.pin.value.length < 3) {

                $scope.pin.response.value = "Please enter more than 3 characters";
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


    $scope.checkProvider = function() {

        if($scope.provider.selected.id == "") {

            $scope.provider.response.value = "Please select provider";
            $scope.provider.response.class = "error";
            $scope.provider.response.view = true;
            $scope.valid.provider = false;

        } else {

            $scope.provider.response.view = false;
            $scope.valid.provider = true;

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

        if($scope.username.value.length < 3) {

            $scope.username.response.value = "Please enter more than 3 characters";
            $scope.username.response.class = "error";
            $scope.username.response.view = true;
            $scope.valid.username = false;

        } else {

            $scope.username.response.view = false;
            $scope.valid.username = true;

        }

    }


    $scope.delete = function(id, event) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/third-party/account/delete"
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
                    "description": $scope.description.value,
                    "password": $scope.password.value,
                    "pin": $scope.pin.value,
                    "provider": {
                        "id": $scope.provider.selected.id,
                        "name": $scope.provider.selected.name
                    },
                    "sequence": input.sequence,
                    "status": $scope.status.selected.value,
                    "username": $scope.username.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/third-party/account/update"
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
            "provider.id": ["equal", $scope.filter.provider.selected.id],
            "username": ["like", $scope.filter.username.value],
            "status": ["equal", ""]
        };

        if($scope.filter.status.selected.value != "Unknown") {

            data["status"][1] = $scope.filter.status.selected.value;

        }

        var rest = {
            "data": data,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/third-party/account/filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/third-party/account/";

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

            $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/third-party/account/page-" + $scope.site.page.number + "/";

        }

        event.preventDefault();

    }


    $scope.initializeData = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/third-party/account/initialize-data"
        }
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.thirdPartyAccount.id != null) {

                    for(var i = 0; i < response.thirdPartyAccount.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.thirdPartyAccount.company.idList[i]);
                        $scope.company.nameList.value.push(response.thirdPartyAccount.company.nameList[i]);

                    }

                    for(var i = 0; i < response.thirdPartyAccount.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.thirdPartyAccount.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.thirdPartyAccount.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.thirdPartyAccount.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.thirdPartyAccount.created.user.id;
                    $scope.created.user.username.value = response.thirdPartyAccount.created.user.username;

                    $scope.credit.value = library.numeral.initializeSeparator(response.thirdPartyAccount.credit, true);
                    $scope.description.value = response.thirdPartyAccount.description;
                    $scope.id.value = response.thirdPartyAccount.id;

                    var modifiedTimestamp = new Date(response.thirdPartyAccount.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.thirdPartyAccount.modified.user.id;
                    $scope.modified.user.username.value = response.thirdPartyAccount.modified.user.username;
                    $scope.password.value = response.thirdPartyAccount.password;
                    $scope.pin.value = response.thirdPartyAccount.pin;
                    $scope.sequence.value = library.numeral.initializeSeparator(response.thirdPartyAccount.sequence, true);
                    $scope.username.value = response.thirdPartyAccount.username;

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

                angular.forEach(response.thirdPartyProviderList, function(value, key) {

                    $scope.provider.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                    if(response.thirdPartyAccount.id != null) {

                        if(value.id == response.thirdPartyAccount.provider.id) {

                            $scope.provider.selected = $scope.provider.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.statusList, function(value, key) {

                    $scope.status.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.thirdPartyAccount.id != null) {

                        if(value == response.thirdPartyAccount.status) {

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/third-party/account/initialize-pagination"
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
                    $scope.filter.username.value = response.filter.username[1];

                }

                angular.forEach(response.thirdPartyProviderList, function(value, key) {

                    $scope.filter.provider.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                    if(Object.entries(response.filter).length > 0) {

                        if(value.id == response.filter.provider_id[1]) {

                            $scope.filter.provider.selected = $scope.filter.provider.option[(key + 1)];

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
                    "company": {
                        "branch": {
                            "idList": $scope.company.branch.idList.value,
                            "nameList": $scope.company.branch.nameList.value
                        },
                        "idList": $scope.company.idList.value,
                        "nameList": $scope.company.nameList.value
                    },
                    "description": $scope.description.value,
                    "password": $scope.password.value,
                    "pin": $scope.pin.value,
                    "provider": {
                        "id": $scope.provider.selected.id,
                        "name": $scope.provider.selected.name
                    },
                    "sequence": input.sequence,
                    "status": $scope.status.selected.value,
                    "username": $scope.username.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/third-party/account/insert"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/third-party/account/initialize-data"
        }
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.thirdPartyAccount.id != null) {

                    for(var i = 0; i < response.thirdPartyAccount.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.thirdPartyAccount.company.idList[i]);
                        $scope.company.nameList.value.push(response.thirdPartyAccount.company.nameList[i]);

                    }

                    for(var i = 0; i < response.thirdPartyAccount.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.thirdPartyAccount.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.thirdPartyAccount.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.thirdPartyAccount.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);

                    $scope.created.user.id.value = response.thirdPartyAccount.created.user.id;
                    $scope.created.user.username.value = response.thirdPartyAccount.created.user.username;

                    $scope.credit.value = library.numeral.initializeSeparator(response.thirdPartyAccount.credit, true);
                    $scope.description.value = response.thirdPartyAccount.description;
                    $scope.id.value = response.thirdPartyAccount.id;

                    var modifiedTimestamp = new Date(response.thirdPartyAccount.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);

                    $scope.modified.user.id.value = response.thirdPartyAccount.modified.user.id;
                    $scope.modified.user.username.value = response.thirdPartyAccount.modified.user.username;
                    $scope.password.value = response.thirdPartyAccount.password;
                    $scope.pin.value = response.thirdPartyAccount.pin;
                    $scope.provider.value = response.thirdPartyAccount.provider.name;
                    $scope.sequence.value = library.numeral.initializeSeparator(response.thirdPartyAccount.sequence, true);
                    $scope.status.value = $scope.camelCaseToStandardCase(response.thirdPartyAccount.status);
                    $scope.username.value = response.thirdPartyAccount.username;

                }

                $scope.popup.view = true;
                $scope.popup.thirdPartyAccount = true;

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/third-party/account/remove-filter-pagination"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/third-party/account/set-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/third-party/account/";

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


app.directive("thirdPartyAccountDetail", function() {


    var thirdPartyAccount = {};


    thirdPartyAccount.templateUrl = document.getElementById("global").getAttribute("data-base-url") + "/html/third-party-account-detail-popup.html";


    return thirdPartyAccount;


});
